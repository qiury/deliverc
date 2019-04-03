/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.znjt.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2019-03-27")
public class GPSImgRecord implements org.apache.thrift.TBase<GPSImgRecord, GPSImgRecord._Fields>, java.io.Serializable, Cloneable, Comparable<GPSImgRecord> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("GPSImgRecord");

  private static final org.apache.thrift.protocol.TField CLIENT_RECORD_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("client_record_id", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField DATA_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("dataId", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField SERV_OPS_RES_FIELD_DESC = new org.apache.thrift.protocol.TField("serv_ops_res", org.apache.thrift.protocol.TType.BOOL, (short)3);
  private static final org.apache.thrift.protocol.TField IMG_DATAS_FIELD_DESC = new org.apache.thrift.protocol.TField("img_datas", org.apache.thrift.protocol.TType.LIST, (short)4);
  private static final org.apache.thrift.protocol.TField FILE_ERR_FIELD_DESC = new org.apache.thrift.protocol.TField("file_err", org.apache.thrift.protocol.TType.BOOL, (short)5);
  private static final org.apache.thrift.protocol.TField LOSTED_SIZE_FIELD_DESC = new org.apache.thrift.protocol.TField("losted_size", org.apache.thrift.protocol.TType.I32, (short)6);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new GPSImgRecordStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new GPSImgRecordTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable String client_record_id; // required
  public @org.apache.thrift.annotation.Nullable String dataId; // required
  public boolean serv_ops_res; // required
  public @org.apache.thrift.annotation.Nullable java.util.List<java.nio.ByteBuffer> img_datas; // required
  public boolean file_err; // required
  public int losted_size; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CLIENT_RECORD_ID((short)1, "client_record_id"),
    DATA_ID((short)2, "dataId"),
    SERV_OPS_RES((short)3, "serv_ops_res"),
    IMG_DATAS((short)4, "img_datas"),
    FILE_ERR((short)5, "file_err"),
    LOSTED_SIZE((short)6, "losted_size");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // CLIENT_RECORD_ID
          return CLIENT_RECORD_ID;
        case 2: // DATA_ID
          return DATA_ID;
        case 3: // SERV_OPS_RES
          return SERV_OPS_RES;
        case 4: // IMG_DATAS
          return IMG_DATAS;
        case 5: // FILE_ERR
          return FILE_ERR;
        case 6: // LOSTED_SIZE
          return LOSTED_SIZE;
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
    @org.apache.thrift.annotation.Nullable
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
  private static final int __SERV_OPS_RES_ISSET_ID = 0;
  private static final int __FILE_ERR_ISSET_ID = 1;
  private static final int __LOSTED_SIZE_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CLIENT_RECORD_ID, new org.apache.thrift.meta_data.FieldMetaData("client_record_id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DATA_ID, new org.apache.thrift.meta_data.FieldMetaData("dataId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SERV_OPS_RES, new org.apache.thrift.meta_data.FieldMetaData("serv_ops_res", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.IMG_DATAS, new org.apache.thrift.meta_data.FieldMetaData("img_datas", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING            , true))));
    tmpMap.put(_Fields.FILE_ERR, new org.apache.thrift.meta_data.FieldMetaData("file_err", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.LOSTED_SIZE, new org.apache.thrift.meta_data.FieldMetaData("losted_size", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(GPSImgRecord.class, metaDataMap);
  }

  public GPSImgRecord() {
  }

  public GPSImgRecord(
    String client_record_id,
    String dataId,
    boolean serv_ops_res,
    java.util.List<java.nio.ByteBuffer> img_datas,
    boolean file_err,
    int losted_size)
  {
    this();
    this.client_record_id = client_record_id;
    this.dataId = dataId;
    this.serv_ops_res = serv_ops_res;
    setServ_ops_resIsSet(true);
    this.img_datas = img_datas;
    this.file_err = file_err;
    setFile_errIsSet(true);
    this.losted_size = losted_size;
    setLosted_sizeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GPSImgRecord(GPSImgRecord other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetClient_record_id()) {
      this.client_record_id = other.client_record_id;
    }
    if (other.isSetDataId()) {
      this.dataId = other.dataId;
    }
    this.serv_ops_res = other.serv_ops_res;
    if (other.isSetImg_datas()) {
      java.util.List<java.nio.ByteBuffer> __this__img_datas = new java.util.ArrayList<java.nio.ByteBuffer>(other.img_datas);
      this.img_datas = __this__img_datas;
    }
    this.file_err = other.file_err;
    this.losted_size = other.losted_size;
  }

  public GPSImgRecord deepCopy() {
    return new GPSImgRecord(this);
  }

  @Override
  public void clear() {
    this.client_record_id = null;
    this.dataId = null;
    setServ_ops_resIsSet(false);
    this.serv_ops_res = false;
    this.img_datas = null;
    setFile_errIsSet(false);
    this.file_err = false;
    setLosted_sizeIsSet(false);
    this.losted_size = 0;
  }

  @org.apache.thrift.annotation.Nullable
  public String getClient_record_id() {
    return this.client_record_id;
  }

  public GPSImgRecord setClient_record_id(@org.apache.thrift.annotation.Nullable String client_record_id) {
    this.client_record_id = client_record_id;
    return this;
  }

  public void unsetClient_record_id() {
    this.client_record_id = null;
  }

  /** Returns true if field client_record_id is set (has been assigned a value) and false otherwise */
  public boolean isSetClient_record_id() {
    return this.client_record_id != null;
  }

  public void setClient_record_idIsSet(boolean value) {
    if (!value) {
      this.client_record_id = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public String getDataId() {
    return this.dataId;
  }

  public GPSImgRecord setDataId(@org.apache.thrift.annotation.Nullable String dataId) {
    this.dataId = dataId;
    return this;
  }

  public void unsetDataId() {
    this.dataId = null;
  }

  /** Returns true if field dataId is set (has been assigned a value) and false otherwise */
  public boolean isSetDataId() {
    return this.dataId != null;
  }

  public void setDataIdIsSet(boolean value) {
    if (!value) {
      this.dataId = null;
    }
  }

  public boolean isServ_ops_res() {
    return this.serv_ops_res;
  }

  public GPSImgRecord setServ_ops_res(boolean serv_ops_res) {
    this.serv_ops_res = serv_ops_res;
    setServ_ops_resIsSet(true);
    return this;
  }

  public void unsetServ_ops_res() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SERV_OPS_RES_ISSET_ID);
  }

  /** Returns true if field serv_ops_res is set (has been assigned a value) and false otherwise */
  public boolean isSetServ_ops_res() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SERV_OPS_RES_ISSET_ID);
  }

  public void setServ_ops_resIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SERV_OPS_RES_ISSET_ID, value);
  }

  public int getImg_datasSize() {
    return (this.img_datas == null) ? 0 : this.img_datas.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<java.nio.ByteBuffer> getImg_datasIterator() {
    return (this.img_datas == null) ? null : this.img_datas.iterator();
  }

  public void addToImg_datas(java.nio.ByteBuffer elem) {
    if (this.img_datas == null) {
      this.img_datas = new java.util.ArrayList<java.nio.ByteBuffer>();
    }
    this.img_datas.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<java.nio.ByteBuffer> getImg_datas() {
    return this.img_datas;
  }

  public GPSImgRecord setImg_datas(@org.apache.thrift.annotation.Nullable java.util.List<java.nio.ByteBuffer> img_datas) {
    this.img_datas = img_datas;
    return this;
  }

  public void unsetImg_datas() {
    this.img_datas = null;
  }

  /** Returns true if field img_datas is set (has been assigned a value) and false otherwise */
  public boolean isSetImg_datas() {
    return this.img_datas != null;
  }

  public void setImg_datasIsSet(boolean value) {
    if (!value) {
      this.img_datas = null;
    }
  }

  public boolean isFile_err() {
    return this.file_err;
  }

  public GPSImgRecord setFile_err(boolean file_err) {
    this.file_err = file_err;
    setFile_errIsSet(true);
    return this;
  }

  public void unsetFile_err() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __FILE_ERR_ISSET_ID);
  }

  /** Returns true if field file_err is set (has been assigned a value) and false otherwise */
  public boolean isSetFile_err() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __FILE_ERR_ISSET_ID);
  }

  public void setFile_errIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __FILE_ERR_ISSET_ID, value);
  }

  public int getLosted_size() {
    return this.losted_size;
  }

  public GPSImgRecord setLosted_size(int losted_size) {
    this.losted_size = losted_size;
    setLosted_sizeIsSet(true);
    return this;
  }

  public void unsetLosted_size() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __LOSTED_SIZE_ISSET_ID);
  }

  /** Returns true if field losted_size is set (has been assigned a value) and false otherwise */
  public boolean isSetLosted_size() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __LOSTED_SIZE_ISSET_ID);
  }

  public void setLosted_sizeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __LOSTED_SIZE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable Object value) {
    switch (field) {
    case CLIENT_RECORD_ID:
      if (value == null) {
        unsetClient_record_id();
      } else {
        setClient_record_id((String)value);
      }
      break;

    case DATA_ID:
      if (value == null) {
        unsetDataId();
      } else {
        setDataId((String)value);
      }
      break;

    case SERV_OPS_RES:
      if (value == null) {
        unsetServ_ops_res();
      } else {
        setServ_ops_res((Boolean)value);
      }
      break;

    case IMG_DATAS:
      if (value == null) {
        unsetImg_datas();
      } else {
        setImg_datas((java.util.List<java.nio.ByteBuffer>)value);
      }
      break;

    case FILE_ERR:
      if (value == null) {
        unsetFile_err();
      } else {
        setFile_err((Boolean)value);
      }
      break;

    case LOSTED_SIZE:
      if (value == null) {
        unsetLosted_size();
      } else {
        setLosted_size((Integer)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public Object getFieldValue(_Fields field) {
    switch (field) {
    case CLIENT_RECORD_ID:
      return getClient_record_id();

    case DATA_ID:
      return getDataId();

    case SERV_OPS_RES:
      return isServ_ops_res();

    case IMG_DATAS:
      return getImg_datas();

    case FILE_ERR:
      return isFile_err();

    case LOSTED_SIZE:
      return getLosted_size();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case CLIENT_RECORD_ID:
      return isSetClient_record_id();
    case DATA_ID:
      return isSetDataId();
    case SERV_OPS_RES:
      return isSetServ_ops_res();
    case IMG_DATAS:
      return isSetImg_datas();
    case FILE_ERR:
      return isSetFile_err();
    case LOSTED_SIZE:
      return isSetLosted_size();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof GPSImgRecord)
      return this.equals((GPSImgRecord)that);
    return false;
  }

  public boolean equals(GPSImgRecord that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_client_record_id = true && this.isSetClient_record_id();
    boolean that_present_client_record_id = true && that.isSetClient_record_id();
    if (this_present_client_record_id || that_present_client_record_id) {
      if (!(this_present_client_record_id && that_present_client_record_id))
        return false;
      if (!this.client_record_id.equals(that.client_record_id))
        return false;
    }

    boolean this_present_dataId = true && this.isSetDataId();
    boolean that_present_dataId = true && that.isSetDataId();
    if (this_present_dataId || that_present_dataId) {
      if (!(this_present_dataId && that_present_dataId))
        return false;
      if (!this.dataId.equals(that.dataId))
        return false;
    }

    boolean this_present_serv_ops_res = true;
    boolean that_present_serv_ops_res = true;
    if (this_present_serv_ops_res || that_present_serv_ops_res) {
      if (!(this_present_serv_ops_res && that_present_serv_ops_res))
        return false;
      if (this.serv_ops_res != that.serv_ops_res)
        return false;
    }

    boolean this_present_img_datas = true && this.isSetImg_datas();
    boolean that_present_img_datas = true && that.isSetImg_datas();
    if (this_present_img_datas || that_present_img_datas) {
      if (!(this_present_img_datas && that_present_img_datas))
        return false;
      if (!this.img_datas.equals(that.img_datas))
        return false;
    }

    boolean this_present_file_err = true;
    boolean that_present_file_err = true;
    if (this_present_file_err || that_present_file_err) {
      if (!(this_present_file_err && that_present_file_err))
        return false;
      if (this.file_err != that.file_err)
        return false;
    }

    boolean this_present_losted_size = true;
    boolean that_present_losted_size = true;
    if (this_present_losted_size || that_present_losted_size) {
      if (!(this_present_losted_size && that_present_losted_size))
        return false;
      if (this.losted_size != that.losted_size)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetClient_record_id()) ? 131071 : 524287);
    if (isSetClient_record_id())
      hashCode = hashCode * 8191 + client_record_id.hashCode();

    hashCode = hashCode * 8191 + ((isSetDataId()) ? 131071 : 524287);
    if (isSetDataId())
      hashCode = hashCode * 8191 + dataId.hashCode();

    hashCode = hashCode * 8191 + ((serv_ops_res) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((isSetImg_datas()) ? 131071 : 524287);
    if (isSetImg_datas())
      hashCode = hashCode * 8191 + img_datas.hashCode();

    hashCode = hashCode * 8191 + ((file_err) ? 131071 : 524287);

    hashCode = hashCode * 8191 + losted_size;

    return hashCode;
  }

  @Override
  public int compareTo(GPSImgRecord other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetClient_record_id()).compareTo(other.isSetClient_record_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetClient_record_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.client_record_id, other.client_record_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDataId()).compareTo(other.isSetDataId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDataId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dataId, other.dataId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetServ_ops_res()).compareTo(other.isSetServ_ops_res());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetServ_ops_res()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.serv_ops_res, other.serv_ops_res);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetImg_datas()).compareTo(other.isSetImg_datas());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetImg_datas()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.img_datas, other.img_datas);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFile_err()).compareTo(other.isSetFile_err());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFile_err()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.file_err, other.file_err);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLosted_size()).compareTo(other.isSetLosted_size());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLosted_size()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.losted_size, other.losted_size);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("GPSImgRecord(");
    boolean first = true;

    sb.append("client_record_id:");
    if (this.client_record_id == null) {
      sb.append("null");
    } else {
      sb.append(this.client_record_id);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("dataId:");
    if (this.dataId == null) {
      sb.append("null");
    } else {
      sb.append(this.dataId);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("serv_ops_res:");
    sb.append(this.serv_ops_res);
    first = false;
    if (!first) sb.append(", ");
    sb.append("img_datas:");
    if (this.img_datas == null) {
      sb.append("null");
    } else {
      org.apache.thrift.TBaseHelper.toString(this.img_datas, sb);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("file_err:");
    sb.append(this.file_err);
    first = false;
    if (!first) sb.append(", ");
    sb.append("losted_size:");
    sb.append(this.losted_size);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class GPSImgRecordStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public GPSImgRecordStandardScheme getScheme() {
      return new GPSImgRecordStandardScheme();
    }
  }

  private static class GPSImgRecordStandardScheme extends org.apache.thrift.scheme.StandardScheme<GPSImgRecord> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, GPSImgRecord struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CLIENT_RECORD_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.client_record_id = iprot.readString();
              struct.setClient_record_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DATA_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.dataId = iprot.readString();
              struct.setDataIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SERV_OPS_RES
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.serv_ops_res = iprot.readBool();
              struct.setServ_ops_resIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // IMG_DATAS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list16 = iprot.readListBegin();
                struct.img_datas = new java.util.ArrayList<java.nio.ByteBuffer>(_list16.size);
                @org.apache.thrift.annotation.Nullable java.nio.ByteBuffer _elem17;
                for (int _i18 = 0; _i18 < _list16.size; ++_i18)
                {
                  _elem17 = iprot.readBinary();
                  struct.img_datas.add(_elem17);
                }
                iprot.readListEnd();
              }
              struct.setImg_datasIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // FILE_ERR
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.file_err = iprot.readBool();
              struct.setFile_errIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // LOSTED_SIZE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.losted_size = iprot.readI32();
              struct.setLosted_sizeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, GPSImgRecord struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.client_record_id != null) {
        oprot.writeFieldBegin(CLIENT_RECORD_ID_FIELD_DESC);
        oprot.writeString(struct.client_record_id);
        oprot.writeFieldEnd();
      }
      if (struct.dataId != null) {
        oprot.writeFieldBegin(DATA_ID_FIELD_DESC);
        oprot.writeString(struct.dataId);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(SERV_OPS_RES_FIELD_DESC);
      oprot.writeBool(struct.serv_ops_res);
      oprot.writeFieldEnd();
      if (struct.img_datas != null) {
        oprot.writeFieldBegin(IMG_DATAS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.img_datas.size()));
          for (java.nio.ByteBuffer _iter19 : struct.img_datas)
          {
            oprot.writeBinary(_iter19);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(FILE_ERR_FIELD_DESC);
      oprot.writeBool(struct.file_err);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(LOSTED_SIZE_FIELD_DESC);
      oprot.writeI32(struct.losted_size);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class GPSImgRecordTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public GPSImgRecordTupleScheme getScheme() {
      return new GPSImgRecordTupleScheme();
    }
  }

  private static class GPSImgRecordTupleScheme extends org.apache.thrift.scheme.TupleScheme<GPSImgRecord> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, GPSImgRecord struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetClient_record_id()) {
        optionals.set(0);
      }
      if (struct.isSetDataId()) {
        optionals.set(1);
      }
      if (struct.isSetServ_ops_res()) {
        optionals.set(2);
      }
      if (struct.isSetImg_datas()) {
        optionals.set(3);
      }
      if (struct.isSetFile_err()) {
        optionals.set(4);
      }
      if (struct.isSetLosted_size()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetClient_record_id()) {
        oprot.writeString(struct.client_record_id);
      }
      if (struct.isSetDataId()) {
        oprot.writeString(struct.dataId);
      }
      if (struct.isSetServ_ops_res()) {
        oprot.writeBool(struct.serv_ops_res);
      }
      if (struct.isSetImg_datas()) {
        {
          oprot.writeI32(struct.img_datas.size());
          for (java.nio.ByteBuffer _iter20 : struct.img_datas)
          {
            oprot.writeBinary(_iter20);
          }
        }
      }
      if (struct.isSetFile_err()) {
        oprot.writeBool(struct.file_err);
      }
      if (struct.isSetLosted_size()) {
        oprot.writeI32(struct.losted_size);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, GPSImgRecord struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.client_record_id = iprot.readString();
        struct.setClient_record_idIsSet(true);
      }
      if (incoming.get(1)) {
        struct.dataId = iprot.readString();
        struct.setDataIdIsSet(true);
      }
      if (incoming.get(2)) {
        struct.serv_ops_res = iprot.readBool();
        struct.setServ_ops_resIsSet(true);
      }
      if (incoming.get(3)) {
        {
          org.apache.thrift.protocol.TList _list21 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.img_datas = new java.util.ArrayList<java.nio.ByteBuffer>(_list21.size);
          @org.apache.thrift.annotation.Nullable java.nio.ByteBuffer _elem22;
          for (int _i23 = 0; _i23 < _list21.size; ++_i23)
          {
            _elem22 = iprot.readBinary();
            struct.img_datas.add(_elem22);
          }
        }
        struct.setImg_datasIsSet(true);
      }
      if (incoming.get(4)) {
        struct.file_err = iprot.readBool();
        struct.setFile_errIsSet(true);
      }
      if (incoming.get(5)) {
        struct.losted_size = iprot.readI32();
        struct.setLosted_sizeIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
