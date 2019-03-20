package com.znjt.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.19.0)",
    comments = "Source: Transfer.proto")
public final class TransferServiceGrpc {

  private TransferServiceGrpc() {}

  public static final String SERVICE_NAME = "com.znjt.proto.TransferService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.znjt.proto.SyncDataRequest,
      com.znjt.proto.SyncDataResponse> getTransporterByStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "transporterByStream",
      requestType = com.znjt.proto.SyncDataRequest.class,
      responseType = com.znjt.proto.SyncDataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.znjt.proto.SyncDataRequest,
      com.znjt.proto.SyncDataResponse> getTransporterByStreamMethod() {
    io.grpc.MethodDescriptor<com.znjt.proto.SyncDataRequest, com.znjt.proto.SyncDataResponse> getTransporterByStreamMethod;
    if ((getTransporterByStreamMethod = TransferServiceGrpc.getTransporterByStreamMethod) == null) {
      synchronized (TransferServiceGrpc.class) {
        if ((getTransporterByStreamMethod = TransferServiceGrpc.getTransporterByStreamMethod) == null) {
          TransferServiceGrpc.getTransporterByStreamMethod = getTransporterByStreamMethod = 
              io.grpc.MethodDescriptor.<com.znjt.proto.SyncDataRequest, com.znjt.proto.SyncDataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.znjt.proto.TransferService", "transporterByStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.znjt.proto.SyncDataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.znjt.proto.SyncDataResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TransferServiceMethodDescriptorSupplier("transporterByStream"))
                  .build();
          }
        }
     }
     return getTransporterByStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.znjt.proto.SyncDataRequest,
      com.znjt.proto.SyncDataResponse> getTransporterBySyncMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "transporterBySync",
      requestType = com.znjt.proto.SyncDataRequest.class,
      responseType = com.znjt.proto.SyncDataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.znjt.proto.SyncDataRequest,
      com.znjt.proto.SyncDataResponse> getTransporterBySyncMethod() {
    io.grpc.MethodDescriptor<com.znjt.proto.SyncDataRequest, com.znjt.proto.SyncDataResponse> getTransporterBySyncMethod;
    if ((getTransporterBySyncMethod = TransferServiceGrpc.getTransporterBySyncMethod) == null) {
      synchronized (TransferServiceGrpc.class) {
        if ((getTransporterBySyncMethod = TransferServiceGrpc.getTransporterBySyncMethod) == null) {
          TransferServiceGrpc.getTransporterBySyncMethod = getTransporterBySyncMethod = 
              io.grpc.MethodDescriptor.<com.znjt.proto.SyncDataRequest, com.znjt.proto.SyncDataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.znjt.proto.TransferService", "transporterBySync"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.znjt.proto.SyncDataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.znjt.proto.SyncDataResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TransferServiceMethodDescriptorSupplier("transporterBySync"))
                  .build();
          }
        }
     }
     return getTransporterBySyncMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TransferServiceStub newStub(io.grpc.Channel channel) {
    return new TransferServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TransferServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TransferServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TransferServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TransferServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TransferServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<com.znjt.proto.SyncDataRequest> transporterByStream(
        io.grpc.stub.StreamObserver<com.znjt.proto.SyncDataResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getTransporterByStreamMethod(), responseObserver);
    }

    /**
     */
    public void transporterBySync(com.znjt.proto.SyncDataRequest request,
        io.grpc.stub.StreamObserver<com.znjt.proto.SyncDataResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTransporterBySyncMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getTransporterByStreamMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.znjt.proto.SyncDataRequest,
                com.znjt.proto.SyncDataResponse>(
                  this, METHODID_TRANSPORTER_BY_STREAM)))
          .addMethod(
            getTransporterBySyncMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.znjt.proto.SyncDataRequest,
                com.znjt.proto.SyncDataResponse>(
                  this, METHODID_TRANSPORTER_BY_SYNC)))
          .build();
    }
  }

  /**
   */
  public static final class TransferServiceStub extends io.grpc.stub.AbstractStub<TransferServiceStub> {
    private TransferServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TransferServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransferServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TransferServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.znjt.proto.SyncDataRequest> transporterByStream(
        io.grpc.stub.StreamObserver<com.znjt.proto.SyncDataResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getTransporterByStreamMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void transporterBySync(com.znjt.proto.SyncDataRequest request,
        io.grpc.stub.StreamObserver<com.znjt.proto.SyncDataResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTransporterBySyncMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TransferServiceBlockingStub extends io.grpc.stub.AbstractStub<TransferServiceBlockingStub> {
    private TransferServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TransferServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransferServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TransferServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.znjt.proto.SyncDataResponse transporterBySync(com.znjt.proto.SyncDataRequest request) {
      return blockingUnaryCall(
          getChannel(), getTransporterBySyncMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TransferServiceFutureStub extends io.grpc.stub.AbstractStub<TransferServiceFutureStub> {
    private TransferServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TransferServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransferServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TransferServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.znjt.proto.SyncDataResponse> transporterBySync(
        com.znjt.proto.SyncDataRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTransporterBySyncMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TRANSPORTER_BY_SYNC = 0;
  private static final int METHODID_TRANSPORTER_BY_STREAM = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TransferServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TransferServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TRANSPORTER_BY_SYNC:
          serviceImpl.transporterBySync((com.znjt.proto.SyncDataRequest) request,
              (io.grpc.stub.StreamObserver<com.znjt.proto.SyncDataResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TRANSPORTER_BY_STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.transporterByStream(
              (io.grpc.stub.StreamObserver<com.znjt.proto.SyncDataResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class TransferServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TransferServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.znjt.proto.TransferProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TransferService");
    }
  }

  private static final class TransferServiceFileDescriptorSupplier
      extends TransferServiceBaseDescriptorSupplier {
    TransferServiceFileDescriptorSupplier() {}
  }

  private static final class TransferServiceMethodDescriptorSupplier
      extends TransferServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TransferServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TransferServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TransferServiceFileDescriptorSupplier())
              .addMethod(getTransporterByStreamMethod())
              .addMethod(getTransporterBySyncMethod())
              .build();
        }
      }
    }
    return result;
  }
}
