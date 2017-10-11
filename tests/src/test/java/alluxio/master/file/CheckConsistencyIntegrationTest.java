/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.master.file;

import alluxio.AlluxioURI;
import alluxio.LocalAlluxioClusterResource;
import alluxio.PropertyKey;
import alluxio.BaseIntegrationTest;
import alluxio.client.WriteType;
import alluxio.client.file.FileSystem;
import alluxio.client.file.options.CreateDirectoryOptions;
import alluxio.client.file.options.CreateFileOptions;
import alluxio.exception.InvalidPathException;
import alluxio.master.file.options.CheckConsistencyOptions;
import alluxio.security.authentication.AuthenticatedClientUser;
import alluxio.underfs.UfsDirectoryStatus;
import alluxio.underfs.UfsFileStatus;
import alluxio.underfs.UnderFileSystem;
import alluxio.underfs.options.DeleteOptions;
import alluxio.underfs.local.LocalUnderFileSystem;

import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Integration test for
 * {@link FileSystemMaster#checkConsistency(AlluxioURI, CheckConsistencyOptions)}.
 */
//@RunWith(PowerMockRunner.class)
public class CheckConsistencyIntegrationTest extends BaseIntegrationTest {
  private static final AlluxioURI DIRECTORY = new AlluxioURI("/dir");
  private static final AlluxioURI FILE = new AlluxioURI("/dir/file");
  private static final String TEST_USER = System.getProperties().getProperty("user.name");

  @Rule
  public LocalAlluxioClusterResource mLocalAlluxioClusterResource =
      new LocalAlluxioClusterResource.Builder().setProperty(PropertyKey.SECURITY_LOGIN_USERNAME,
          TEST_USER).build();

  private FileSystemMaster mFileSystemMaster;
  private FileSystem mFileSystem;

  private void setupUserGroupMocks(String username, String groups)
      throws Exception {
    UfsFileStatus ufsFileStatus = PowerMockito.mock(UfsFileStatus.class);
    UfsDirectoryStatus ufsDirectoryStatus = PowerMockito.mock(UfsDirectoryStatus.class);

    PowerMockito.whenNew(UfsFileStatus.class).withAnyArguments().thenReturn(ufsFileStatus);
    PowerMockito.whenNew(UfsDirectoryStatus.class).withAnyArguments().thenReturn(ufsDirectoryStatus);

    PowerMockito.when(ufsFileStatus.getOwner()).thenReturn(username);
    PowerMockito.when(ufsFileStatus.getGroup()).thenReturn(groups);
    PowerMockito.when(ufsDirectoryStatus.getOwner()).thenReturn(username);
    PowerMockito.when(ufsDirectoryStatus.getGroup()).thenReturn(groups);
  }

  @Before
  public final void before() throws Exception {
    mFileSystemMaster =
        mLocalAlluxioClusterResource.get().getLocalAlluxioMaster().getMasterProcess()
            .getMaster(FileSystemMaster.class);
    AuthenticatedClientUser.set(TEST_USER);
    mFileSystem = FileSystem.Factory.get();
    CreateDirectoryOptions dirOptions =
        CreateDirectoryOptions.defaults().setWriteType(WriteType.CACHE_THROUGH);
    CreateFileOptions fileOptions =
        CreateFileOptions.defaults().setWriteType(WriteType.CACHE_THROUGH);
    mFileSystem.createDirectory(DIRECTORY, dirOptions);
    mFileSystem.createFile(FILE, fileOptions).close();
  }

  @After
  public final void after() {
    AuthenticatedClientUser.remove();
  }

  /**
   * Tests the {@link FileSystemMaster#checkConsistency(AlluxioURI, CheckConsistencyOptions)} method
   * when all files are consistent.
   */
  @Test
  public void consistent() throws Exception {
    Assert.assertEquals(new ArrayList<AlluxioURI>(), mFileSystemMaster.checkConsistency(
        new AlluxioURI("/"), CheckConsistencyOptions.defaults()));
  }

  /**
   * Tests the {@link FileSystemMaster#checkConsistency(AlluxioURI, CheckConsistencyOptions)} method
   * when no files are consistent.
   */
  @Test
  public void inconsistent() throws Exception {
    String ufsDirectory = mFileSystem.getStatus(DIRECTORY).getUfsPath();
    UnderFileSystem ufs = UnderFileSystem.Factory.create(ufsDirectory);
    ufs.deleteDirectory(ufsDirectory, DeleteOptions.defaults().setRecursive(true));

    List<AlluxioURI> expected = Lists.newArrayList(FILE, DIRECTORY);
    List<AlluxioURI> result =
        mFileSystemMaster.checkConsistency(new AlluxioURI("/"), CheckConsistencyOptions.defaults());
    Collections.sort(expected);
    Collections.sort(result);
    Assert.assertEquals(expected, result);
  }

  /**
   * Tests the {@link FileSystemMaster#checkConsistency(AlluxioURI, CheckConsistencyOptions)} method
   * when some files are consistent.
   */
  @Test
  public void partiallyInconsistent() throws Exception {
    String ufsFile = mFileSystem.getStatus(FILE).getUfsPath();
    UnderFileSystem ufs = UnderFileSystem.Factory.create(ufsFile);
    ufs.deleteFile(ufsFile);
    List<AlluxioURI> expected = Lists.newArrayList(FILE);
    Assert.assertEquals(expected, mFileSystemMaster
        .checkConsistency(new AlluxioURI("/"), CheckConsistencyOptions.defaults()));
  }

  /**
   * Tests the {@link FileSystemMaster#checkConsistency(AlluxioURI, CheckConsistencyOptions)} method
   * when some files are consistent in a larger inode tree.
   */
  @Test
  public void largeTree() throws Exception {
    CreateDirectoryOptions dirOptions =
        CreateDirectoryOptions.defaults().setWriteType(WriteType.CACHE_THROUGH);
    CreateFileOptions fileOptions =
        CreateFileOptions.defaults().setWriteType(WriteType.CACHE_THROUGH);
    AlluxioURI nestedDir = DIRECTORY.join("/dir2");
    AlluxioURI topLevelFile = new AlluxioURI("/file");
    AlluxioURI thirdLevelFile = nestedDir.join("/file");
    mFileSystem.createDirectory(nestedDir, dirOptions);
    mFileSystem.createFile(topLevelFile, fileOptions).close();
    mFileSystem.createFile(thirdLevelFile, fileOptions).close();
    String ufsDirectory = mFileSystem.getStatus(nestedDir).getUfsPath();
    UnderFileSystem ufs = UnderFileSystem.Factory.create(ufsDirectory);
    ufs.deleteDirectory(ufsDirectory, DeleteOptions.defaults().setRecursive(true));

    List<AlluxioURI> expected = Lists.newArrayList(nestedDir, thirdLevelFile);
    List<AlluxioURI> result =
        mFileSystemMaster.checkConsistency(new AlluxioURI("/"), CheckConsistencyOptions.defaults());
    Collections.sort(expected);
    Collections.sort(result);
    Assert.assertEquals(expected, result);
  }

  /**
   * Tests the {@link FileSystemMaster#checkConsistency(AlluxioURI, CheckConsistencyOptions)} method
   * when a file is not the correct size.
   */
  @Test
  public void incorrectFileSize() throws Exception {
    String ufsFile = mFileSystem.getStatus(FILE).getUfsPath();
    UnderFileSystem ufs = UnderFileSystem.Factory.create(ufsFile);
    ufs.deleteFile(ufsFile);
    OutputStream out = ufs.create(ufsFile);
    out.write(1);
    out.close();
    List<AlluxioURI> expected = Lists.newArrayList(FILE);
    Assert.assertEquals(expected, mFileSystemMaster
        .checkConsistency(new AlluxioURI("/"), CheckConsistencyOptions.defaults()));
  }

  /**
   * Tests the {@link FileSystemMaster#checkConsistency(AlluxioURI, CheckConsistencyOptions)} method
   * when a directory does not exist as a directory in the under storage.
   */
  @Test
  public void notADirectory() throws Exception {
    String ufsDirectory = mFileSystem.getStatus(DIRECTORY).getUfsPath();
    UnderFileSystem ufs = UnderFileSystem.Factory.create(ufsDirectory);
    ufs.deleteDirectory(ufsDirectory, DeleteOptions.defaults().setRecursive(true));
    ufs.create(ufsDirectory).close();
    List<AlluxioURI> expected = Lists.newArrayList(DIRECTORY, FILE);
    List<AlluxioURI> result =
        mFileSystemMaster.checkConsistency(new AlluxioURI("/"), CheckConsistencyOptions.defaults());
    Collections.sort(expected);
    Collections.sort(result);
    Assert.assertEquals(expected, result);
  }

  /**
   * Tests the {@link FileSystemMaster#checkConsistency(AlluxioURI, CheckConsistencyOptions)} method
   * when a file does not exist as a file in the under storage.
   */
  @Test
  public void notAFile() throws Exception {
    String ufsFile = mFileSystem.getStatus(FILE).getUfsPath();
    UnderFileSystem ufs = UnderFileSystem.Factory.create(ufsFile);
    ufs.deleteFile(ufsFile);
    ufs.mkdirs(ufsFile);
    List<AlluxioURI> expected = Lists.newArrayList(FILE);
    Assert.assertEquals(expected, mFileSystemMaster
        .checkConsistency(new AlluxioURI("/"), CheckConsistencyOptions.defaults()));
  }

  /**
   * Tests the {@link FileSystemMaster#checkConsistency(AlluxioURI, CheckConsistencyOptions)} method
   * when running on a file that is inconsistent.
   */
  @Test
  public void inconsistentFile() throws Exception {
    String ufsFile = mFileSystem.getStatus(FILE).getUfsPath();
    UnderFileSystem ufs = UnderFileSystem.Factory.create(ufsFile);
    ufs.deleteFile(ufsFile);
    List<AlluxioURI> expected = Lists.newArrayList(FILE);
    Assert.assertEquals(expected, mFileSystemMaster
        .checkConsistency(FILE, CheckConsistencyOptions.defaults()));
  }

  /**
   * Tests the {@link FileSystemMaster#checkConsistency(AlluxioURI, CheckConsistencyOptions)} method
   * when file or directory mode are changed.
   */
  @Test
  public void chMode() throws Exception {
    String ufsDirectory = mFileSystem.getStatus(DIRECTORY).getUfsPath();
    String ufsFile = mFileSystem.getStatus(FILE).getUfsPath();
    UnderFileSystem ufs = UnderFileSystem.Factory.create(ufsDirectory);
    ufs.setMode(ufsDirectory,(short)511);
    ufs.setMode(ufsFile,(short)511);

    List<AlluxioURI> expected = Lists.newArrayList(FILE, DIRECTORY);
    List<AlluxioURI> result =
        mFileSystemMaster.checkConsistency(new AlluxioURI("/"), CheckConsistencyOptions.defaults());
    Collections.sort(expected);
    Collections.sort(result);
    Assert.assertEquals(expected, result);
  }

  /**
   * Tests the {@link FileSystemMaster#checkConsistency(AlluxioURI, CheckConsistencyOptions)} method
   * when file or directory user and group are changed.
   */
  @Test
  public void chUserAndGroup() throws Exception {
    String userName = "alluxio-user1";
    String userGroup = "alluxio-user1-group1";
    setupUserGroupMocks(userName,userGroup);

    List<AlluxioURI> expected = Lists.newArrayList(FILE, DIRECTORY);
    List<AlluxioURI> result =
        mFileSystemMaster.checkConsistency(new AlluxioURI("/"), CheckConsistencyOptions.defaults());
    Collections.sort(expected);
    Collections.sort(result);
    Assert.assertEquals(expected, result);
  }
}
