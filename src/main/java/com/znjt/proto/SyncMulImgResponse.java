// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Transfer.proto

package com.znjt.proto;

/**
 * <pre>
 *请求响应对象
 * </pre>
 *
 * Protobuf type {@code com.znjt.proto.SyncMulImgResponse}
 */
public  final class SyncMulImgResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.znjt.proto.SyncMulImgResponse)
    SyncMulImgResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SyncMulImgResponse.newBuilder() to construct.
  private SyncMulImgResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SyncMulImgResponse() {
    dataType_ = 0;
    gpsRecord_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SyncMulImgResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {
            int rawValue = input.readEnum();

            dataType_ = rawValue;
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              gpsRecord_ = new java.util.ArrayList<com.znjt.proto.GPSRecord>();
              mutable_bitField0_ |= 0x00000002;
            }
            gpsRecord_.add(
                input.readMessage(com.znjt.proto.GPSRecord.parser(), extensionRegistry));
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        gpsRecord_ = java.util.Collections.unmodifiableList(gpsRecord_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.znjt.proto.TransferProto.internal_static_com_znjt_proto_SyncMulImgResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.znjt.proto.TransferProto.internal_static_com_znjt_proto_SyncMulImgResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.znjt.proto.SyncMulImgResponse.class, com.znjt.proto.SyncMulImgResponse.Builder.class);
  }

  private int bitField0_;
  public static final int DATA_TYPE_FIELD_NUMBER = 1;
  private int dataType_;
  /**
   * <code>.com.znjt.proto.DataType data_type = 1;</code>
   */
  public int getDataTypeValue() {
    return dataType_;
  }
  /**
   * <code>.com.znjt.proto.DataType data_type = 1;</code>
   */
  public com.znjt.proto.DataType getDataType() {
    @SuppressWarnings("deprecation")
    com.znjt.proto.DataType result = com.znjt.proto.DataType.valueOf(dataType_);
    return result == null ? com.znjt.proto.DataType.UNRECOGNIZED : result;
  }

  public static final int GPS_RECORD_FIELD_NUMBER = 3;
  private java.util.List<com.znjt.proto.GPSRecord> gpsRecord_;
  /**
   * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
   */
  public java.util.List<com.znjt.proto.GPSRecord> getGpsRecordList() {
    return gpsRecord_;
  }
  /**
   * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
   */
  public java.util.List<? extends com.znjt.proto.GPSRecordOrBuilder> 
      getGpsRecordOrBuilderList() {
    return gpsRecord_;
  }
  /**
   * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
   */
  public int getGpsRecordCount() {
    return gpsRecord_.size();
  }
  /**
   * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
   */
  public com.znjt.proto.GPSRecord getGpsRecord(int index) {
    return gpsRecord_.get(index);
  }
  /**
   * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
   */
  public com.znjt.proto.GPSRecordOrBuilder getGpsRecordOrBuilder(
      int index) {
    return gpsRecord_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (dataType_ != com.znjt.proto.DataType.T_INI.getNumber()) {
      output.writeEnum(1, dataType_);
    }
    for (int i = 0; i < gpsRecord_.size(); i++) {
      output.writeMessage(3, gpsRecord_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (dataType_ != com.znjt.proto.DataType.T_INI.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(1, dataType_);
    }
    for (int i = 0; i < gpsRecord_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, gpsRecord_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.znjt.proto.SyncMulImgResponse)) {
      return super.equals(obj);
    }
    com.znjt.proto.SyncMulImgResponse other = (com.znjt.proto.SyncMulImgResponse) obj;

    boolean result = true;
    result = result && dataType_ == other.dataType_;
    result = result && getGpsRecordList()
        .equals(other.getGpsRecordList());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + DATA_TYPE_FIELD_NUMBER;
    hash = (53 * hash) + dataType_;
    if (getGpsRecordCount() > 0) {
      hash = (37 * hash) + GPS_RECORD_FIELD_NUMBER;
      hash = (53 * hash) + getGpsRecordList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.znjt.proto.SyncMulImgResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.znjt.proto.SyncMulImgResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.znjt.proto.SyncMulImgResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.znjt.proto.SyncMulImgResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.znjt.proto.SyncMulImgResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.znjt.proto.SyncMulImgResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.znjt.proto.SyncMulImgResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.znjt.proto.SyncMulImgResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.znjt.proto.SyncMulImgResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.znjt.proto.SyncMulImgResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.znjt.proto.SyncMulImgResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.znjt.proto.SyncMulImgResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.znjt.proto.SyncMulImgResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   *请求响应对象
   * </pre>
   *
   * Protobuf type {@code com.znjt.proto.SyncMulImgResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.znjt.proto.SyncMulImgResponse)
      com.znjt.proto.SyncMulImgResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.znjt.proto.TransferProto.internal_static_com_znjt_proto_SyncMulImgResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.znjt.proto.TransferProto.internal_static_com_znjt_proto_SyncMulImgResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.znjt.proto.SyncMulImgResponse.class, com.znjt.proto.SyncMulImgResponse.Builder.class);
    }

    // Construct using com.znjt.proto.SyncMulImgResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getGpsRecordFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      dataType_ = 0;

      if (gpsRecordBuilder_ == null) {
        gpsRecord_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        gpsRecordBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.znjt.proto.TransferProto.internal_static_com_znjt_proto_SyncMulImgResponse_descriptor;
    }

    @java.lang.Override
    public com.znjt.proto.SyncMulImgResponse getDefaultInstanceForType() {
      return com.znjt.proto.SyncMulImgResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.znjt.proto.SyncMulImgResponse build() {
      com.znjt.proto.SyncMulImgResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.znjt.proto.SyncMulImgResponse buildPartial() {
      com.znjt.proto.SyncMulImgResponse result = new com.znjt.proto.SyncMulImgResponse(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.dataType_ = dataType_;
      if (gpsRecordBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          gpsRecord_ = java.util.Collections.unmodifiableList(gpsRecord_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.gpsRecord_ = gpsRecord_;
      } else {
        result.gpsRecord_ = gpsRecordBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.znjt.proto.SyncMulImgResponse) {
        return mergeFrom((com.znjt.proto.SyncMulImgResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.znjt.proto.SyncMulImgResponse other) {
      if (other == com.znjt.proto.SyncMulImgResponse.getDefaultInstance()) return this;
      if (other.dataType_ != 0) {
        setDataTypeValue(other.getDataTypeValue());
      }
      if (gpsRecordBuilder_ == null) {
        if (!other.gpsRecord_.isEmpty()) {
          if (gpsRecord_.isEmpty()) {
            gpsRecord_ = other.gpsRecord_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureGpsRecordIsMutable();
            gpsRecord_.addAll(other.gpsRecord_);
          }
          onChanged();
        }
      } else {
        if (!other.gpsRecord_.isEmpty()) {
          if (gpsRecordBuilder_.isEmpty()) {
            gpsRecordBuilder_.dispose();
            gpsRecordBuilder_ = null;
            gpsRecord_ = other.gpsRecord_;
            bitField0_ = (bitField0_ & ~0x00000002);
            gpsRecordBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getGpsRecordFieldBuilder() : null;
          } else {
            gpsRecordBuilder_.addAllMessages(other.gpsRecord_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.znjt.proto.SyncMulImgResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.znjt.proto.SyncMulImgResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int dataType_ = 0;
    /**
     * <code>.com.znjt.proto.DataType data_type = 1;</code>
     */
    public int getDataTypeValue() {
      return dataType_;
    }
    /**
     * <code>.com.znjt.proto.DataType data_type = 1;</code>
     */
    public Builder setDataTypeValue(int value) {
      dataType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.com.znjt.proto.DataType data_type = 1;</code>
     */
    public com.znjt.proto.DataType getDataType() {
      @SuppressWarnings("deprecation")
      com.znjt.proto.DataType result = com.znjt.proto.DataType.valueOf(dataType_);
      return result == null ? com.znjt.proto.DataType.UNRECOGNIZED : result;
    }
    /**
     * <code>.com.znjt.proto.DataType data_type = 1;</code>
     */
    public Builder setDataType(com.znjt.proto.DataType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      dataType_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.com.znjt.proto.DataType data_type = 1;</code>
     */
    public Builder clearDataType() {
      
      dataType_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<com.znjt.proto.GPSRecord> gpsRecord_ =
      java.util.Collections.emptyList();
    private void ensureGpsRecordIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        gpsRecord_ = new java.util.ArrayList<com.znjt.proto.GPSRecord>(gpsRecord_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.znjt.proto.GPSRecord, com.znjt.proto.GPSRecord.Builder, com.znjt.proto.GPSRecordOrBuilder> gpsRecordBuilder_;

    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public java.util.List<com.znjt.proto.GPSRecord> getGpsRecordList() {
      if (gpsRecordBuilder_ == null) {
        return java.util.Collections.unmodifiableList(gpsRecord_);
      } else {
        return gpsRecordBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public int getGpsRecordCount() {
      if (gpsRecordBuilder_ == null) {
        return gpsRecord_.size();
      } else {
        return gpsRecordBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public com.znjt.proto.GPSRecord getGpsRecord(int index) {
      if (gpsRecordBuilder_ == null) {
        return gpsRecord_.get(index);
      } else {
        return gpsRecordBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public Builder setGpsRecord(
        int index, com.znjt.proto.GPSRecord value) {
      if (gpsRecordBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureGpsRecordIsMutable();
        gpsRecord_.set(index, value);
        onChanged();
      } else {
        gpsRecordBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public Builder setGpsRecord(
        int index, com.znjt.proto.GPSRecord.Builder builderForValue) {
      if (gpsRecordBuilder_ == null) {
        ensureGpsRecordIsMutable();
        gpsRecord_.set(index, builderForValue.build());
        onChanged();
      } else {
        gpsRecordBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public Builder addGpsRecord(com.znjt.proto.GPSRecord value) {
      if (gpsRecordBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureGpsRecordIsMutable();
        gpsRecord_.add(value);
        onChanged();
      } else {
        gpsRecordBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public Builder addGpsRecord(
        int index, com.znjt.proto.GPSRecord value) {
      if (gpsRecordBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureGpsRecordIsMutable();
        gpsRecord_.add(index, value);
        onChanged();
      } else {
        gpsRecordBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public Builder addGpsRecord(
        com.znjt.proto.GPSRecord.Builder builderForValue) {
      if (gpsRecordBuilder_ == null) {
        ensureGpsRecordIsMutable();
        gpsRecord_.add(builderForValue.build());
        onChanged();
      } else {
        gpsRecordBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public Builder addGpsRecord(
        int index, com.znjt.proto.GPSRecord.Builder builderForValue) {
      if (gpsRecordBuilder_ == null) {
        ensureGpsRecordIsMutable();
        gpsRecord_.add(index, builderForValue.build());
        onChanged();
      } else {
        gpsRecordBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public Builder addAllGpsRecord(
        java.lang.Iterable<? extends com.znjt.proto.GPSRecord> values) {
      if (gpsRecordBuilder_ == null) {
        ensureGpsRecordIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, gpsRecord_);
        onChanged();
      } else {
        gpsRecordBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public Builder clearGpsRecord() {
      if (gpsRecordBuilder_ == null) {
        gpsRecord_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        gpsRecordBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public Builder removeGpsRecord(int index) {
      if (gpsRecordBuilder_ == null) {
        ensureGpsRecordIsMutable();
        gpsRecord_.remove(index);
        onChanged();
      } else {
        gpsRecordBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public com.znjt.proto.GPSRecord.Builder getGpsRecordBuilder(
        int index) {
      return getGpsRecordFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public com.znjt.proto.GPSRecordOrBuilder getGpsRecordOrBuilder(
        int index) {
      if (gpsRecordBuilder_ == null) {
        return gpsRecord_.get(index);  } else {
        return gpsRecordBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public java.util.List<? extends com.znjt.proto.GPSRecordOrBuilder> 
         getGpsRecordOrBuilderList() {
      if (gpsRecordBuilder_ != null) {
        return gpsRecordBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(gpsRecord_);
      }
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public com.znjt.proto.GPSRecord.Builder addGpsRecordBuilder() {
      return getGpsRecordFieldBuilder().addBuilder(
          com.znjt.proto.GPSRecord.getDefaultInstance());
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public com.znjt.proto.GPSRecord.Builder addGpsRecordBuilder(
        int index) {
      return getGpsRecordFieldBuilder().addBuilder(
          index, com.znjt.proto.GPSRecord.getDefaultInstance());
    }
    /**
     * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
     */
    public java.util.List<com.znjt.proto.GPSRecord.Builder> 
         getGpsRecordBuilderList() {
      return getGpsRecordFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.znjt.proto.GPSRecord, com.znjt.proto.GPSRecord.Builder, com.znjt.proto.GPSRecordOrBuilder> 
        getGpsRecordFieldBuilder() {
      if (gpsRecordBuilder_ == null) {
        gpsRecordBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.znjt.proto.GPSRecord, com.znjt.proto.GPSRecord.Builder, com.znjt.proto.GPSRecordOrBuilder>(
                gpsRecord_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        gpsRecord_ = null;
      }
      return gpsRecordBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.znjt.proto.SyncMulImgResponse)
  }

  // @@protoc_insertion_point(class_scope:com.znjt.proto.SyncMulImgResponse)
  private static final com.znjt.proto.SyncMulImgResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.znjt.proto.SyncMulImgResponse();
  }

  public static com.znjt.proto.SyncMulImgResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SyncMulImgResponse>
      PARSER = new com.google.protobuf.AbstractParser<SyncMulImgResponse>() {
    @java.lang.Override
    public SyncMulImgResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new SyncMulImgResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SyncMulImgResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SyncMulImgResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.znjt.proto.SyncMulImgResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

