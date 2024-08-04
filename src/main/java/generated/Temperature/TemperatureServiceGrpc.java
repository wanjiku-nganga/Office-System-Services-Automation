package generated.Temperature;

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
    comments = "Source: Temperature.proto")
public final class TemperatureServiceGrpc {

  private TemperatureServiceGrpc() {}

  public static final String SERVICE_NAME = "Temperature.TemperatureService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.Temperature.SwitchRequest,
      generated.Temperature.SwitchRequestResponse> getGetTemperatureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getTemperature",
      requestType = generated.Temperature.SwitchRequest.class,
      responseType = generated.Temperature.SwitchRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<generated.Temperature.SwitchRequest,
      generated.Temperature.SwitchRequestResponse> getGetTemperatureMethod() {
    io.grpc.MethodDescriptor<generated.Temperature.SwitchRequest, generated.Temperature.SwitchRequestResponse> getGetTemperatureMethod;
    if ((getGetTemperatureMethod = TemperatureServiceGrpc.getGetTemperatureMethod) == null) {
      synchronized (TemperatureServiceGrpc.class) {
        if ((getGetTemperatureMethod = TemperatureServiceGrpc.getGetTemperatureMethod) == null) {
          TemperatureServiceGrpc.getGetTemperatureMethod = getGetTemperatureMethod = 
              io.grpc.MethodDescriptor.<generated.Temperature.SwitchRequest, generated.Temperature.SwitchRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "Temperature.TemperatureService", "getTemperature"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Temperature.SwitchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Temperature.SwitchRequestResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TemperatureServiceMethodDescriptorSupplier("getTemperature"))
                  .build();
          }
        }
     }
     return getGetTemperatureMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.Temperature.SetRequest,
      generated.Temperature.SetRequestResponse> getSetTemperatureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setTemperature",
      requestType = generated.Temperature.SetRequest.class,
      responseType = generated.Temperature.SetRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Temperature.SetRequest,
      generated.Temperature.SetRequestResponse> getSetTemperatureMethod() {
    io.grpc.MethodDescriptor<generated.Temperature.SetRequest, generated.Temperature.SetRequestResponse> getSetTemperatureMethod;
    if ((getSetTemperatureMethod = TemperatureServiceGrpc.getSetTemperatureMethod) == null) {
      synchronized (TemperatureServiceGrpc.class) {
        if ((getSetTemperatureMethod = TemperatureServiceGrpc.getSetTemperatureMethod) == null) {
          TemperatureServiceGrpc.getSetTemperatureMethod = getSetTemperatureMethod = 
              io.grpc.MethodDescriptor.<generated.Temperature.SetRequest, generated.Temperature.SetRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Temperature.TemperatureService", "setTemperature"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Temperature.SetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Temperature.SetRequestResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new TemperatureServiceMethodDescriptorSupplier("setTemperature"))
                  .build();
          }
        }
     }
     return getSetTemperatureMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TemperatureServiceStub newStub(io.grpc.Channel channel) {
    return new TemperatureServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TemperatureServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TemperatureServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TemperatureServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TemperatureServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TemperatureServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getTemperature(generated.Temperature.SwitchRequest request,
        io.grpc.stub.StreamObserver<generated.Temperature.SwitchRequestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTemperatureMethod(), responseObserver);
    }

    /**
     */
    public void setTemperature(generated.Temperature.SetRequest request,
        io.grpc.stub.StreamObserver<generated.Temperature.SetRequestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetTemperatureMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetTemperatureMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                generated.Temperature.SwitchRequest,
                generated.Temperature.SwitchRequestResponse>(
                  this, METHODID_GET_TEMPERATURE)))
          .addMethod(
            getSetTemperatureMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.Temperature.SetRequest,
                generated.Temperature.SetRequestResponse>(
                  this, METHODID_SET_TEMPERATURE)))
          .build();
    }
  }

  /**
   */
  public static final class TemperatureServiceStub extends io.grpc.stub.AbstractStub<TemperatureServiceStub> {
    private TemperatureServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TemperatureServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TemperatureServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TemperatureServiceStub(channel, callOptions);
    }

    /**
     */
    public void getTemperature(generated.Temperature.SwitchRequest request,
        io.grpc.stub.StreamObserver<generated.Temperature.SwitchRequestResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetTemperatureMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setTemperature(generated.Temperature.SetRequest request,
        io.grpc.stub.StreamObserver<generated.Temperature.SetRequestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetTemperatureMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TemperatureServiceBlockingStub extends io.grpc.stub.AbstractStub<TemperatureServiceBlockingStub> {
    private TemperatureServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TemperatureServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TemperatureServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TemperatureServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<generated.Temperature.SwitchRequestResponse> getTemperature(
        generated.Temperature.SwitchRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetTemperatureMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Temperature.SetRequestResponse setTemperature(generated.Temperature.SetRequest request) {
      return blockingUnaryCall(
          getChannel(), getSetTemperatureMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TemperatureServiceFutureStub extends io.grpc.stub.AbstractStub<TemperatureServiceFutureStub> {
    private TemperatureServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TemperatureServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TemperatureServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TemperatureServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Temperature.SetRequestResponse> setTemperature(
        generated.Temperature.SetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSetTemperatureMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_TEMPERATURE = 0;
  private static final int METHODID_SET_TEMPERATURE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TemperatureServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TemperatureServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_TEMPERATURE:
          serviceImpl.getTemperature((generated.Temperature.SwitchRequest) request,
              (io.grpc.stub.StreamObserver<generated.Temperature.SwitchRequestResponse>) responseObserver);
          break;
        case METHODID_SET_TEMPERATURE:
          serviceImpl.setTemperature((generated.Temperature.SetRequest) request,
              (io.grpc.stub.StreamObserver<generated.Temperature.SetRequestResponse>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class TemperatureServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TemperatureServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.Temperature.TemperatureServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TemperatureService");
    }
  }

  private static final class TemperatureServiceFileDescriptorSupplier
      extends TemperatureServiceBaseDescriptorSupplier {
    TemperatureServiceFileDescriptorSupplier() {}
  }

  private static final class TemperatureServiceMethodDescriptorSupplier
      extends TemperatureServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TemperatureServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TemperatureServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TemperatureServiceFileDescriptorSupplier())
              .addMethod(getGetTemperatureMethod())
              .addMethod(getSetTemperatureMethod())
              .build();
        }
      }
    }
    return result;
  }
}
