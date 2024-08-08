package ds;


import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Logger;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import generated.Security.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

/**
 * @author Sylvia
 */
public class SecurityServer extends SecurityServiceGrpc.SecurityServiceImplBase {
    public static void main(String[] args) {
        SecurityServer securityServer=new SecurityServer();

        int port=50059;

        try {
            //Creating and starting the server
            Server server = ServerBuilder.forPort(port)
                    .addService(securityServer)
                    .build()
                    .start();
            System.out.println(LocalTime.now().toString() + ": Security Server started, listening on " + port);

            //JMDNS Service Registration
            //Create a jmdns instance
            JmDNS jmDNS=JmDNS.create(InetAddress.getLocalHost());
            System.out.println("Registration: InetAddress.getLocalHost():" + InetAddress.getLocalHost());
            //Register a service
            ServiceInfo serviceInfo=ServiceInfo.create("_securityservice_grpc.tcp.local.","Security Service",port,"gRPC Security Service");
            jmDNS.registerService(serviceInfo);
            System.out.println("Service has been registered with JMDNS " + serviceInfo.getName());

            //Terminating the server
            server.awaitTermination();

            //De-registering JMDNS services upon shutdown
            jmDNS.unregisterAllServices();


        } catch (IOException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    public StreamObserver<ClockInRequest> clockInquery(StreamObserver<ClockInResponse> responseObserver) {
        Logger logger = Logger.getLogger(SecurityServiceImpl.class.getName());
        // Returning a new StreamObserver to handle incoming clock-in requests.
        return new StreamObserver<ClockInRequest>() {
            @Override
            public void onNext(ClockInRequest request) {
                // Creating a response for each clock-in request.
                ClockInResponse response = ClockInResponse.newBuilder()
                        .setStaffId(request.getStaffId())
                        .setName("Staff Name")
                        .setClockInTime("Clock In Time") // current timestamp.
                        .build();

                // Sending the response back to the client.
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable t) {
                // Logging the error.
                logger.warning("Error in clockInquery: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println(LocalTime.now().toString()+" : receiving clock in query completed ");
                // Completing the response stream.
                responseObserver.onCompleted();

            }
        };
    }

    public void lockDoor(LockRequest request, StreamObserver<LockRequestResponse> responseObserver) {
        // Creating a status message indicating that the door is locked.
        String status = "Door Locked at location: " + request.getLocation();

        // Building the response.
        LockRequestResponse response = LockRequestResponse.newBuilder()
                .setStatus(status)
                .build();

        // Sending the response back to the client.
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
