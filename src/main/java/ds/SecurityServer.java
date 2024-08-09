package ds;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import generated.Security.*;
import io.grpc.Context;
import io.grpc.Deadline;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

/**
 * @author Sylvia
 */
public class SecurityServer extends SecurityServiceGrpc.SecurityServiceImplBase {
    public static void main(String[] args) {
        SecurityServer securityServer = new SecurityServer();

        int port = 50059;

        try {
            // Creating and starting the server
            Server server = ServerBuilder.forPort(port)
                    .addService(securityServer)
                    .build()
                    .start();
            System.out.println(LocalTime.now().toString() + ": Security Server started, listening on " + port);

            // JMDNS Service Registration
            JmDNS jmDNS = JmDNS.create(InetAddress.getLocalHost());
            ServiceInfo serviceInfo = ServiceInfo.create("_securityservice_grpc.tcp.local.", "Security Service", port, "gRPC Security Service");
            jmDNS.registerService(serviceInfo);
            System.out.println("Service has been registered with JMDNS " + serviceInfo.getName());

            // Add shutdown hook to deregister JmDNS services and stop the server gracefully
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down Security Server...");
                server.shutdown();
                jmDNS.unregisterAllServices();
                System.out.println("Server shut down.");
            }));

            // Await server termination
            server.awaitTermination();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public StreamObserver<ClockInRequest> clockInquery(StreamObserver<ClockInResponse> responseObserver) {
        Logger logger = Logger.getLogger(SecurityServer.class.getName());

        return new StreamObserver<ClockInRequest>() {
            @Override
            public void onNext(ClockInRequest request) {
                // Check if the request context is cancelled
                if (Context.current().isCancelled()) {
                    responseObserver.onError(new RuntimeException("Request was cancelled by the client."));
                    return;
                }

                // Check if the deadline has been exceeded
                Deadline deadline = Context.current().getDeadline();
                if (deadline != null && deadline.isExpired()) {
                    responseObserver.onError(new RuntimeException("Deadline exceeded for the request."));
                    return;
                }

                // Simulate some processing time (optional)
                try {
                    // Simulating processing delay
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    responseObserver.onError(new RuntimeException("Processing interrupted."));
                    return;
                }

                // Creating a response for each clock-in request.
                ClockInResponse response = ClockInResponse.newBuilder()
                        .setStaffId(request.getStaffId())
                        .setName("Staff Name")
                        .setClockInTime(LocalTime.now().toString()) // current timestamp.
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
                System.out.println(LocalTime.now().toString() + " : receiving clock in query completed ");
                // Completing the response stream.
                responseObserver.onCompleted();
            }
        };
    }

    public void lockDoor(LockRequest request, StreamObserver<LockRequestResponse> responseObserver) {
        // Check if the request context is cancelled
        if (Context.current().isCancelled()) {
            responseObserver.onError(new RuntimeException("Request was cancelled by the client."));
            return;
        }

        // Check if the deadline has been exceeded
        Deadline deadline = Context.current().getDeadline();
        if (deadline != null && deadline.isExpired()) {
            responseObserver.onError(new RuntimeException("Deadline exceeded for the request."));
            return;
        }

        // Giving processing time
        try {
            // Simulating processing delay
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            responseObserver.onError(new RuntimeException("Processing interrupted."));
            return;
        }

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
