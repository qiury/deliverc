// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Transfer.proto

package com.znjt.proto;

public interface SyncDataResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.znjt.proto.SyncDataResponse)
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
   * <code>.com.znjt.proto.INIRecord ini_record = 2;</code>
   */
  boolean hasIniRecord();
  /**
   * <code>.com.znjt.proto.INIRecord ini_record = 2;</code>
   */
  com.znjt.proto.INIRecord getIniRecord();
  /**
   * <code>.com.znjt.proto.INIRecord ini_record = 2;</code>
   */
  com.znjt.proto.INIRecordOrBuilder getIniRecordOrBuilder();

  /**
   * <code>.com.znjt.proto.GPSRecord gps_record = 3;</code>
   */
  boolean hasGpsRecord();
  /**
   * <code>.com.znjt.proto.GPSRecord gps_record = 3;</code>
   */
  com.znjt.proto.GPSRecord getGpsRecord();
  /**
   * <code>.com.znjt.proto.GPSRecord gps_record = 3;</code>
   */
  com.znjt.proto.GPSRecordOrBuilder getGpsRecordOrBuilder();

  public com.znjt.proto.SyncDataResponse.RecordCase getRecordCase();
}
