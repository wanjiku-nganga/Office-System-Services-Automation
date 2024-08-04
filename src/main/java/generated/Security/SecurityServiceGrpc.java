package generated.Security;

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
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: Security.proto")
public final class SecurityServiceGrpc {

  private SecurityServiceGrpc() {}

  public static final String SERVICE_NAME = "Security.SecurityService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.Security.LockRequest,
      generated.Security.LockRequestResponse> getLockDoorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "lockDoor",
      requestType = generated.Security.LockRequest.class,
      responseType = generated.Security.LockRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Security.LockRequest,
      generated.Security.LockRequestResponse> getLockDoorMethod() {
    io.grpc.MethodDescriptor<generated.Security.LockRequest, generated.Security.LockRequestResponse> getLockDoorMethod;
    if ((getLockDoorMethod = SecurityServiceGrpc.getLockDoorMethod) == null) {
      synchronized (SecurityServiceGrpc.class) {
        if ((getLockDoorMethod = SecurityServiceGrpc.getLockDoorMethod) == null) {
          SecurityServiceGrpc.getLockDoorMethod = getLockDoorMethod = 
              io.grpc.MethodDescriptor.<generated.Security.LockRequest, generated.Security.LockRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Security.SecurityService", "lockDoor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Security.LockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Security.LockRequestResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SecurityServiceMethodDescriptorSupplier("lockDoor"))
                  .build();
          }
        }
     }
     return getLockDoorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Security.UnlockRequest,
      generated.Security.UnlockRequestResponse> getUnlockDoorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "unlockDoor",
      requestType = generated.Security.UnlockRequest.class,
      responseType = generated.Security.UnlockRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Security.UnlockRequest,
      generated.Security.UnlockRequestResponse> getUnlockDoorMethod() {
    io.grpc.MethodDescriptor<generated.Security.UnlockRequest, generated.Security.UnlockRequestResponse> getUnlockDoorMethod;
    if ((getUnlockDoorMethod = SecurityServiceGrpc.getUnlockDoorMethod) == null) {
      synchronized (SecurityServiceGrpc.class) {
        if ((getUnlockDoorMethod = SecurityServiceGrpc.getUnlockDoorMethod) == null) {
          SecurityServiceGrpc.getUnlockDoorMethod = getUnlockDoorMethod = 
              io.grpc.MethodDescriptor.<generated.Security.UnlockRequest, generated.Security.UnlockRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Security.SecurityService", "unlockDoor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Security.UnlockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Security.UnlockRequestResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SecurityServiceMethodDescriptorSupplier("unlockDoor"))
                  .build();
          }
        }
     }
     return getUnlockDoorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Security.ClockInRequest,
      generated.Security.ClockInResponse> getClockInqueryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "clockInquery",
      requestType = generated.Security.ClockInRequest.class,
      responseType = generated.Security.ClockInResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<generated.Security.ClockInRequest,
      generated.Security.ClockInResponse> getClockInqueryMethod() {
    io.grpc.MethodDescriptor<generated.Security.ClockInRequest, generated.Security.ClockInResponse> getClockInqueryMethod;
    if ((getClockInqueryMethod = SecurityServiceGrpc.getClockInqueryMethod) == null) {
      synchronized (SecurityServiceGrpc.class) {
        if ((getClockInqueryMethod = SecurityServiceGrpc.getClockInqueryMethod) == null) {
          SecurityServiceGrpc.getClockInqueryMethod = getClockInqueryMethod = 
              io.grpc.MethodDescriptor.<generated.Security.ClockInRequest, generated.Security.ClockInResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "Security.SecurityService", "clockInquery"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Security.ClockInRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Security.ClockInResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SecurityServiceMethodDescriptorSupplier("clockInquery"))
                  .build();
          }
        }
     }
     return getClockInqueryMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SecurityServiceStub newStub(io.grpc.Channel channel) {
    return new SecurityServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SecurityServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SecurityServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SecurityServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SecurityServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class SecurityServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void lockDoor(generated.Security.LockRequest request,
        io.grpc.stub.StreamObserver<generated.Security.LockRequestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLockDoorMethod(), responseObserver);
    }

    /**
     */
    public void unlockDoor(generated.Security.UnlockRequest request,
        io.grpc.stub.StreamObserver<generated.Security.UnlockRequestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnlockDoorMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.Security.ClockInRequest> clockInquery(
        io.grpc.stub.StreamObserver<generated.Security.ClockInResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getClockInqueryMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLockDoorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.Security.LockRequest,
                generated.Security.LockRequestResponse>(
                  this, METHODID_LOCK_DOOR)))
          .addMethod(
            getUnlockDoorMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.Security.UnlockRequest,
                generated.Security.UnlockRequestResponse>(
                  this, METHODID_UNLOCK_DOOR)))
          .addMethod(
            getClockInqueryMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                generated.Security.ClockInRequest,
                generated.Security.ClockInResponse>(
                  this, METHODID_CLOCK_INQUERY)))
          .build();
    }
  }

  /**
   */
  public static final class SecurityServiceStub extends io.grpc.stub.AbstractStub<SecurityServiceStub> {
    private SecurityServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SecurityServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SecurityServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SecurityServiceStub(channel, callOptions);
    }

    /**
     */
    public void lockDoor(generated.Security.LockRequest request,
        io.grpc.stub.StreamObserver<generated.Security.LockRequestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLockDoorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void unlockDoor(generated.Security.UnlockRequest request,
        io.grpc.stub.StreamObserver<generated.Security.UnlockRequestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnlockDoorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<generated.Security.ClockInRequest> clockInquery(
        io.grpc.stub.StreamObserver<generated.Security.ClockInResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getClockInqueryMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class SecurityServiceBlockingStub extends io.grpc.stub.AbstractStub<SecurityServiceBlockingStub> {
    private SecurityServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SecurityServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SecurityServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SecurityServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public generated.Security.LockRequestResponse lockDoor(generated.Security.LockRequest request) {
      return blockingUnaryCall(
          getChannel(), getLockDoorMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Security.UnlockRequestResponse unlockDoor(generated.Security.UnlockRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnlockDoorMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SecurityServiceFutureStub extends io.grpc.stub.AbstractStub<SecurityServiceFutureStub> {
    private SecurityServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SecurityServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SecurityServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SecurityServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Security.LockRequestResponse> lockDoor(
        generated.Security.LockRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLockDoorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Security.UnlockRequestResponse> unlockDoor(
        generated.Security.UnlockRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnlockDoorMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOCK_DOOR = 0;
  private static final int METHODID_UNLOCK_DOOR = 1;
  private static final int METHODID_CLOCK_INQUERY = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SecurityServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SecurityServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOCK_DOOR:
          serviceImpl.lockDoor((generated.Security.LockRequest) request,
              (io.grpc.stub.StreamObserver<generated.Security.LockRequestResponse>) responseObserver);
          break;
        case METHODID_UNLOCK_DOOR:
          serviceImpl.unlockDoor((generated.Security.UnlockRequest) request,
              (io.grpc.stub.StreamObserver<generated.Security.UnlockRequestResponse>) responseObserver);
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
        case METHODID_CLOCK_INQUERY:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.clockInquery(
              (io.grpc.stub.StreamObserver<generated.Security.ClockInResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SecurityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SecurityServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.Security.SecurityServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SecurityService");
    }
  }

  private static final class SecurityServiceFileDescriptorSupplier
      extends SecurityServiceBaseDescriptorSupplier {
    SecurityServiceFileDescriptorSupplier() {}
  }

  private static final class SecurityServiceMethodDescriptorSupplier
      extends SecurityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SecurityServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SecurityServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SecurityServiceFileDescriptorSupplier())
              .addMethod(getLockDoorMethod())
              .addMethod(getUnlockDoorMethod())
              .addMethod(getClockInqueryMethod())
              .build();
        }
      }
    }
    return result;
  }
}
