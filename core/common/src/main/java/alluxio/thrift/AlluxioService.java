/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package alluxio.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)")
public class AlluxioService {

  public interface Iface {

    /**
     * Returns the version of the master service.
     * NOTE: The version should be updated every time a backwards incompatible API change occurs.
     * 
     * @param options the method options
     */
    public GetServiceVersionTResponse getServiceVersion(GetServiceVersionTOptions options) throws alluxio.thrift.AlluxioTException, org.apache.thrift.TException;

  }

  public interface AsyncIface {

    public void getServiceVersion(GetServiceVersionTOptions options, org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException;

  }

  public static class Client extends org.apache.thrift.TServiceClient implements Iface {
    public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.thrift.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public GetServiceVersionTResponse getServiceVersion(GetServiceVersionTOptions options) throws alluxio.thrift.AlluxioTException, org.apache.thrift.TException
    {
      send_getServiceVersion(options);
      return recv_getServiceVersion();
    }

    public void send_getServiceVersion(GetServiceVersionTOptions options) throws org.apache.thrift.TException
    {
      getServiceVersion_args args = new getServiceVersion_args();
      args.setOptions(options);
      sendBase("getServiceVersion", args);
    }

    public GetServiceVersionTResponse recv_getServiceVersion() throws alluxio.thrift.AlluxioTException, org.apache.thrift.TException
    {
      getServiceVersion_result result = new getServiceVersion_result();
      receiveBase(result, "getServiceVersion");
      if (result.isSetSuccess()) {
        return result.success;
      }
      if (result.e != null) {
        throw result.e;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "getServiceVersion failed: unknown result");
    }

  }
  public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
    public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.thrift.async.TAsyncClientManager clientManager;
      private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void getServiceVersion(GetServiceVersionTOptions options, org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException {
      checkReady();
      getServiceVersion_call method_call = new getServiceVersion_call(options, resultHandler, this, ___protocolFactory, ___transport);
      this.___currentMethod = method_call;
      ___manager.call(method_call);
    }

    public static class getServiceVersion_call extends org.apache.thrift.async.TAsyncMethodCall {
      private GetServiceVersionTOptions options;
      public getServiceVersion_call(GetServiceVersionTOptions options, org.apache.thrift.async.AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.options = options;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("getServiceVersion", org.apache.thrift.protocol.TMessageType.CALL, 0));
        getServiceVersion_args args = new getServiceVersion_args();
        args.setOptions(options);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public GetServiceVersionTResponse getResult() throws alluxio.thrift.AlluxioTException, org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_getServiceVersion();
      }
    }

  }

  public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
    }

    protected Processor(I iface, Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      processMap.put("getServiceVersion", new getServiceVersion());
      return processMap;
    }

    public static class getServiceVersion<I extends Iface> extends org.apache.thrift.ProcessFunction<I, getServiceVersion_args> {
      public getServiceVersion() {
        super("getServiceVersion");
      }

      public getServiceVersion_args getEmptyArgsInstance() {
        return new getServiceVersion_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public getServiceVersion_result getResult(I iface, getServiceVersion_args args) throws org.apache.thrift.TException {
        getServiceVersion_result result = new getServiceVersion_result();
        try {
          result.success = iface.getServiceVersion(args.options);
        } catch (alluxio.thrift.AlluxioTException e) {
          result.e = e;
        }
        return result;
      }
    }

  }

  public static class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProcessor.class.getName());
    public AsyncProcessor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>>()));
    }

    protected AsyncProcessor(I iface, Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends AsyncIface> Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(Map<String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      processMap.put("getServiceVersion", new getServiceVersion());
      return processMap;
    }

    public static class getServiceVersion<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, getServiceVersion_args, GetServiceVersionTResponse> {
      public getServiceVersion() {
        super("getServiceVersion");
      }

      public getServiceVersion_args getEmptyArgsInstance() {
        return new getServiceVersion_args();
      }

      public AsyncMethodCallback<GetServiceVersionTResponse> getResultHandler(final AsyncFrameBuffer fb, final int seqid) {
        final org.apache.thrift.AsyncProcessFunction fcall = this;
        return new AsyncMethodCallback<GetServiceVersionTResponse>() { 
          public void onComplete(GetServiceVersionTResponse o) {
            getServiceVersion_result result = new getServiceVersion_result();
            result.success = o;
            try {
              fcall.sendResponse(fb,result, org.apache.thrift.protocol.TMessageType.REPLY,seqid);
              return;
            } catch (Exception e) {
              LOGGER.error("Exception writing to internal frame buffer", e);
            }
            fb.close();
          }
          public void onError(Exception e) {
            byte msgType = org.apache.thrift.protocol.TMessageType.REPLY;
            org.apache.thrift.TBase msg;
            getServiceVersion_result result = new getServiceVersion_result();
            if (e instanceof alluxio.thrift.AlluxioTException) {
                        result.e = (alluxio.thrift.AlluxioTException) e;
                        result.setEIsSet(true);
                        msg = result;
            }
             else 
            {
              msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
              msg = (org.apache.thrift.TBase)new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
            }
            try {
              fcall.sendResponse(fb,msg,msgType,seqid);
              return;
            } catch (Exception ex) {
              LOGGER.error("Exception writing to internal frame buffer", ex);
            }
            fb.close();
          }
        };
      }

      protected boolean isOneway() {
        return false;
      }

      public void start(I iface, getServiceVersion_args args, org.apache.thrift.async.AsyncMethodCallback<GetServiceVersionTResponse> resultHandler) throws TException {
        iface.getServiceVersion(args.options,resultHandler);
      }
    }

  }

  public static class getServiceVersion_args implements org.apache.thrift.TBase<getServiceVersion_args, getServiceVersion_args._Fields>, java.io.Serializable, Cloneable, Comparable<getServiceVersion_args>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("getServiceVersion_args");

    private static final org.apache.thrift.protocol.TField OPTIONS_FIELD_DESC = new org.apache.thrift.protocol.TField("options", org.apache.thrift.protocol.TType.STRUCT, (short)1);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new getServiceVersion_argsStandardSchemeFactory());
      schemes.put(TupleScheme.class, new getServiceVersion_argsTupleSchemeFactory());
    }

    private GetServiceVersionTOptions options; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      /**
       * the method options
       */
      OPTIONS((short)1, "options");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 1: // OPTIONS
            return OPTIONS;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.OPTIONS, new org.apache.thrift.meta_data.FieldMetaData("options", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, GetServiceVersionTOptions.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(getServiceVersion_args.class, metaDataMap);
    }

    public getServiceVersion_args() {
    }

    public getServiceVersion_args(
      GetServiceVersionTOptions options)
    {
      this();
      this.options = options;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getServiceVersion_args(getServiceVersion_args other) {
      if (other.isSetOptions()) {
        this.options = new GetServiceVersionTOptions(other.options);
      }
    }

    public getServiceVersion_args deepCopy() {
      return new getServiceVersion_args(this);
    }

    @Override
    public void clear() {
      this.options = null;
    }

    /**
     * the method options
     */
    public GetServiceVersionTOptions getOptions() {
      return this.options;
    }

    /**
     * the method options
     */
    public getServiceVersion_args setOptions(GetServiceVersionTOptions options) {
      this.options = options;
      return this;
    }

    public void unsetOptions() {
      this.options = null;
    }

    /** Returns true if field options is set (has been assigned a value) and false otherwise */
    public boolean isSetOptions() {
      return this.options != null;
    }

    public void setOptionsIsSet(boolean value) {
      if (!value) {
        this.options = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case OPTIONS:
        if (value == null) {
          unsetOptions();
        } else {
          setOptions((GetServiceVersionTOptions)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case OPTIONS:
        return getOptions();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case OPTIONS:
        return isSetOptions();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getServiceVersion_args)
        return this.equals((getServiceVersion_args)that);
      return false;
    }

    public boolean equals(getServiceVersion_args that) {
      if (that == null)
        return false;

      boolean this_present_options = true && this.isSetOptions();
      boolean that_present_options = true && that.isSetOptions();
      if (this_present_options || that_present_options) {
        if (!(this_present_options && that_present_options))
          return false;
        if (!this.options.equals(that.options))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_options = true && (isSetOptions());
      list.add(present_options);
      if (present_options)
        list.add(options);

      return list.hashCode();
    }

    @Override
    public int compareTo(getServiceVersion_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetOptions()).compareTo(other.isSetOptions());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetOptions()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.options, other.options);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getServiceVersion_args(");
      boolean first = true;

      sb.append("options:");
      if (this.options == null) {
        sb.append("null");
      } else {
        sb.append(this.options);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
      if (options != null) {
        options.validate();
      }
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class getServiceVersion_argsStandardSchemeFactory implements SchemeFactory {
      public getServiceVersion_argsStandardScheme getScheme() {
        return new getServiceVersion_argsStandardScheme();
      }
    }

    private static class getServiceVersion_argsStandardScheme extends StandardScheme<getServiceVersion_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, getServiceVersion_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // OPTIONS
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.options = new GetServiceVersionTOptions();
                struct.options.read(iprot);
                struct.setOptionsIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, getServiceVersion_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.options != null) {
          oprot.writeFieldBegin(OPTIONS_FIELD_DESC);
          struct.options.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class getServiceVersion_argsTupleSchemeFactory implements SchemeFactory {
      public getServiceVersion_argsTupleScheme getScheme() {
        return new getServiceVersion_argsTupleScheme();
      }
    }

    private static class getServiceVersion_argsTupleScheme extends TupleScheme<getServiceVersion_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, getServiceVersion_args struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetOptions()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetOptions()) {
          struct.options.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, getServiceVersion_args struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          struct.options = new GetServiceVersionTOptions();
          struct.options.read(iprot);
          struct.setOptionsIsSet(true);
        }
      }
    }

  }

  public static class getServiceVersion_result implements org.apache.thrift.TBase<getServiceVersion_result, getServiceVersion_result._Fields>, java.io.Serializable, Cloneable, Comparable<getServiceVersion_result>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("getServiceVersion_result");

    private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("success", org.apache.thrift.protocol.TType.STRUCT, (short)0);
    private static final org.apache.thrift.protocol.TField E_FIELD_DESC = new org.apache.thrift.protocol.TField("e", org.apache.thrift.protocol.TType.STRUCT, (short)1);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new getServiceVersion_resultStandardSchemeFactory());
      schemes.put(TupleScheme.class, new getServiceVersion_resultTupleSchemeFactory());
    }

    private GetServiceVersionTResponse success; // required
    private alluxio.thrift.AlluxioTException e; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      SUCCESS((short)0, "success"),
      E((short)1, "e");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 0: // SUCCESS
            return SUCCESS;
          case 1: // E
            return E;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, GetServiceVersionTResponse.class)));
      tmpMap.put(_Fields.E, new org.apache.thrift.meta_data.FieldMetaData("e", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(getServiceVersion_result.class, metaDataMap);
    }

    public getServiceVersion_result() {
    }

    public getServiceVersion_result(
      GetServiceVersionTResponse success,
      alluxio.thrift.AlluxioTException e)
    {
      this();
      this.success = success;
      this.e = e;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public getServiceVersion_result(getServiceVersion_result other) {
      if (other.isSetSuccess()) {
        this.success = new GetServiceVersionTResponse(other.success);
      }
      if (other.isSetE()) {
        this.e = new alluxio.thrift.AlluxioTException(other.e);
      }
    }

    public getServiceVersion_result deepCopy() {
      return new getServiceVersion_result(this);
    }

    @Override
    public void clear() {
      this.success = null;
      this.e = null;
    }

    public GetServiceVersionTResponse getSuccess() {
      return this.success;
    }

    public getServiceVersion_result setSuccess(GetServiceVersionTResponse success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been assigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public alluxio.thrift.AlluxioTException getE() {
      return this.e;
    }

    public getServiceVersion_result setE(alluxio.thrift.AlluxioTException e) {
      this.e = e;
      return this;
    }

    public void unsetE() {
      this.e = null;
    }

    /** Returns true if field e is set (has been assigned a value) and false otherwise */
    public boolean isSetE() {
      return this.e != null;
    }

    public void setEIsSet(boolean value) {
      if (!value) {
        this.e = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((GetServiceVersionTResponse)value);
        }
        break;

      case E:
        if (value == null) {
          unsetE();
        } else {
          setE((alluxio.thrift.AlluxioTException)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      case E:
        return getE();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      case E:
        return isSetE();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof getServiceVersion_result)
        return this.equals((getServiceVersion_result)that);
      return false;
    }

    public boolean equals(getServiceVersion_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      boolean this_present_e = true && this.isSetE();
      boolean that_present_e = true && that.isSetE();
      if (this_present_e || that_present_e) {
        if (!(this_present_e && that_present_e))
          return false;
        if (!this.e.equals(that.e))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      List<Object> list = new ArrayList<Object>();

      boolean present_success = true && (isSetSuccess());
      list.add(present_success);
      if (present_success)
        list.add(success);

      boolean present_e = true && (isSetE());
      list.add(present_e);
      if (present_e)
        list.add(e);

      return list.hashCode();
    }

    @Override
    public int compareTo(getServiceVersion_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(other.isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetSuccess()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, other.success);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = Boolean.valueOf(isSetE()).compareTo(other.isSetE());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetE()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.e, other.e);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
      }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("getServiceVersion_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("e:");
      if (this.e == null) {
        sb.append("null");
      } else {
        sb.append(this.e);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
      if (success != null) {
        success.validate();
      }
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class getServiceVersion_resultStandardSchemeFactory implements SchemeFactory {
      public getServiceVersion_resultStandardScheme getScheme() {
        return new getServiceVersion_resultStandardScheme();
      }
    }

    private static class getServiceVersion_resultStandardScheme extends StandardScheme<getServiceVersion_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, getServiceVersion_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 0: // SUCCESS
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.success = new GetServiceVersionTResponse();
                struct.success.read(iprot);
                struct.setSuccessIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 1: // E
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.e = new alluxio.thrift.AlluxioTException();
                struct.e.read(iprot);
                struct.setEIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, getServiceVersion_result struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.success != null) {
          oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
          struct.success.write(oprot);
          oprot.writeFieldEnd();
        }
        if (struct.e != null) {
          oprot.writeFieldBegin(E_FIELD_DESC);
          struct.e.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class getServiceVersion_resultTupleSchemeFactory implements SchemeFactory {
      public getServiceVersion_resultTupleScheme getScheme() {
        return new getServiceVersion_resultTupleScheme();
      }
    }

    private static class getServiceVersion_resultTupleScheme extends TupleScheme<getServiceVersion_result> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, getServiceVersion_result struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetSuccess()) {
          optionals.set(0);
        }
        if (struct.isSetE()) {
          optionals.set(1);
        }
        oprot.writeBitSet(optionals, 2);
        if (struct.isSetSuccess()) {
          struct.success.write(oprot);
        }
        if (struct.isSetE()) {
          struct.e.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, getServiceVersion_result struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(2);
        if (incoming.get(0)) {
          struct.success = new GetServiceVersionTResponse();
          struct.success.read(iprot);
          struct.setSuccessIsSet(true);
        }
        if (incoming.get(1)) {
          struct.e = new alluxio.thrift.AlluxioTException();
          struct.e.read(iprot);
          struct.setEIsSet(true);
        }
      }
    }

  }

}
