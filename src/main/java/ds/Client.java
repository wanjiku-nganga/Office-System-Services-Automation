package ds;
import generated.Security.*;
import generated.Temperature.SwitchRequest;
import generated.Temperature.SwitchRequestResponse;
import generated.Temperature.TemperatureServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import generated.Registry.UploadRequest;
import generated.Registry.UploadResponse;
import generated.Registry.RegistryServiceGrpc;

import java.time.LocalTime;

/**
 * @author Sylvia
 */
public class Client {
    private static RegistryServiceGrpc.RegistryServiceBlockingStub registryblockingStub;
    public static RegistryServiceGrpc.RegistryServiceStub registryasyncStub;
    public static SecurityServiceGrpc.SecurityServiceStub securityasyncStub;
    public static SecurityServiceGrpc.SecurityServiceBlockingStub securityBlockingStub;
    public static TemperatureServiceGrpc.TemperatureServiceStub tempasyncStub;

    public static void main(String[] args) {
        ManagedChannel registryChannel = ManagedChannelBuilder
                .forAddress("localhost", 50058)
                .usePlaintext()
                .build();
        ManagedChannel securityChannel = ManagedChannelBuilder.forAddress("localhost", 50059)
                .usePlaintext()
                .build();
        ManagedChannel tempChannel=ManagedChannelBuilder.forAddress("localhost",50060)
                .usePlaintext()
                .build();

        // Non-blocking stub for client streaming, Bidirectional and server streaming rpcs
        registryasyncStub = RegistryServiceGrpc.newStub(registryChannel);
        securityasyncStub=SecurityServiceGrpc.newStub(securityChannel);
        tempasyncStub=TemperatureServiceGrpc.newStub(tempChannel);

        //Blocking Stubs for Unary RPC
        securityBlockingStub=SecurityServiceGrpc.newBlockingStub(securityChannel);

        // Testing the different method implementations
        uploadDocuments(registryasyncStub);//Client Streaming method
        clockInQuery(securityasyncStub);//Bi-directional streaming
        lockDoor(securityBlockingStub);//Unary
       getTemperature(tempasyncStub);//Server-Streaming

        // Shutting down channels
       registryChannel.shutdown();
       securityChannel.shutdown();
       tempChannel.shutdown();
    }

    //Client Streaming Method that allows for the uploading of multiple documents all at once to the registry
    public static void uploadDocuments(RegistryServiceGrpc.RegistryServiceStub asyncStub) {
        StreamObserver<UploadResponse> responseObserver = new StreamObserver<UploadResponse>() {

            @Override
            public void onNext(UploadResponse value) {
                System.out.println("Registry Server response: " + value.getValidation());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("Upload complete. \n Thank you");
            }
        };

        StreamObserver<UploadRequest> requestObserver = Client.registryasyncStub.uploadDocuments(responseObserver);
        try {
            requestObserver.onNext(UploadRequest.newBuilder().setRegistryID(456).setFileName("Road Investment Plan 2024").build());
            Thread.sleep(700);
            requestObserver.onNext(UploadRequest.newBuilder().setRegistryID(476).setFileName("Road Maintenance Index 2023").build());
            Thread.sleep(700);
            requestObserver.onNext(UploadRequest.newBuilder().setRegistryID(466).setFileName("Financial Audit 2024").build());
            Thread.sleep(700);
            requestObserver.onNext(UploadRequest.newBuilder().setRegistryID(476).setFileName("Policy and Planning Directorate").build());
            Thread.sleep(700);
            requestObserver.onNext(UploadRequest.newBuilder().setRegistryID(456).setFileName("Highways Directorate").build());
            Thread.sleep(700);
            requestObserver.onNext(UploadRequest.newBuilder().setRegistryID(466).setFileName("Urban and Park Roads Directorate").build());
            Thread.sleep(700);
            // Tell the server that there are no more requests
            requestObserver.onCompleted();
            Thread.sleep(700);

        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Bidirectional Streaming that allows the HR to query the time stamps for generation of punctuality report
    public static void clockInQuery(SecurityServiceGrpc.SecurityServiceStub asyncStub){
        StreamObserver<ClockInResponse> responseObserver= new StreamObserver<ClockInResponse>() {
            @Override
            public void onNext(ClockInResponse value) {
                System.out.println("Security Server Response \n  Received Staff ID "+value.getStaffId()+" Staff Name : "+value.getName() + " Clock in Time : "+value.getClockInTime());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                System.out.println(LocalTime.now().toString() + " : stream is completed .... \n Thank you. ");

            }
        };
         StreamObserver<ClockInRequest> requestObserver = Client.securityasyncStub.clockInquery(responseObserver);
         try {
             requestObserver.onNext(ClockInRequest.newBuilder().setStaffId(456773).build());
             Thread.sleep(500);
             requestObserver.onNext(ClockInRequest.newBuilder().setStaffId(56783).build());
             Thread.sleep(500);
             requestObserver.onNext(ClockInRequest.newBuilder().setStaffId(89736).build());
             Thread.sleep(500);
             requestObserver.onNext(ClockInRequest.newBuilder().setStaffId(134767782).build());
             Thread.sleep(500);

             //Telling server to stop  when request are complete
             requestObserver.onCompleted();
             Thread.sleep(500);

         } catch (RuntimeException e) {
             throw new RuntimeException(e);
         }catch (InterruptedException e){
             throw new RuntimeException(e);
         }


    }

    //Unary RPC for locking the door
    public static void lockDoor(SecurityServiceGrpc.SecurityServiceBlockingStub blockingStub) {
        // Single request to lock a door
        try {
            // Create the request object
            String location = "Server Room Door - Private Floor";
            LockRequest request = LockRequest.newBuilder().setLocation(location).build();

            // Call the blocking method and get the response directly
            LockRequestResponse response = blockingStub.lockDoor(request);

            // Handle the server's response
            System.out.println("Lock Door Response: " + response.getStatus());
        } catch (Exception e) {
            // Handle errors
            e.printStackTrace();
        }
    }


    // Server Streaming temperature that allows for hourly temperature updates during the day
    public static void getTemperature(TemperatureServiceGrpc.TemperatureServiceStub tempasyncStub) {

        SwitchRequest request = SwitchRequest.newBuilder().build();

        // Create the stream observer for the responses
        StreamObserver<SwitchRequestResponse> responseObserver = new StreamObserver<SwitchRequestResponse>() {
            @Override
            public void onNext(SwitchRequestResponse response) {
                // Print the received temperature update
                System.out.println("Received temperature: " + response.getDegrees() + " " + response.getMeasure());
            }

            @Override
            public void onError(Throwable t) {
                // Handle any errors
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                // Notify when the stream has been completed
                System.out.println("Temperature streaming completed.");
            }
        };

        // Call the getTemperature method to start receiving updates
        tempasyncStub.getTemperature(request, responseObserver);

        // Keep the client alive to receive responses
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
