// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Transfer.proto

package com.znjt.proto;

public interface SyncMulImgRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.znjt.proto.SyncMulImgRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.com.znjt.proto.DataType data_type = 1;</code>
   */
  int getDataTypeValue();
  /**
   * <code>.com.znjt.proto.DataType data_type = 1;</code>
   */
  com.znjt.proto.DataType getDataType();

  /**
   * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
   */
  java.util.List<com.znjt.proto.GPSRecord> 
      getGpsRecordList();
  /**
   * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
   */
  com.znjt.proto.GPSRecord getGpsRecord(int index);
  /**
   * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
   */
  int getGpsRecordCount();
  /**
   * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
   */
  java.util.List<? extends com.znjt.proto.GPSRecordOrBuilder> 
      getGpsRecordOrBuilderList();
  /**
   * <code>repeated .com.znjt.proto.GPSRecord gps_record = 3;</code>
   */
  com.znjt.proto.GPSRecordOrBuilder getGpsRecordOrBuilder(
      int index);
}