package ds;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import io.grpc.*;

/**
 * @author Sylvia
 */
/*
* This class has been created for the sole purpose
* of running all the servers at once.
* The server have been registered using JmDNS and the
* discovery method defined in th GUI
* */
public class ServerStarter {
    public static void main(String[] args) throws IOException {
        //Creating an instance of the authenticator
        AuthInterceptor authInterceptor = new AuthInterceptor();
        try {
            // Starting Security Server
            SecurityServer securityServer = new SecurityServer();
            Server security = ServerBuilder.forPort(50059)
                    .addService(ServerInterceptors.intercept(securityServer, authInterceptor))
                    .build()
                    .start();
            System.out.println("Security Server started on port 50059");

            // Starting Temperature Server
            TemperatureServer temperatureServer = new TemperatureServer();
            Server temperature = ServerBuilder.forPort(50060)
                    .addService(ServerInterceptors.intercept(temperatureServer, authInterceptor))
                    .build()
                    .start();
            System.out.println("Temperature Server started on port 50060");

            // Starting Registry Server
            RegistryServer registryServer = new RegistryServer();
            Server registry = ServerBuilder.forPort(50058)
                    .addService(ServerInterceptors.intercept(registryServer, authInterceptor))
                    .build()
                    .start();
            System.out.println("Registry Server started on port 50058");

            // Register all services with JmDNS
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            ServiceInfo securityServiceInfo = ServiceInfo.create("_grpc._tcp.local.", "SecurityService", 50059, "Security gRPC service");
            jmdns.registerService(securityServiceInfo);

            ServiceInfo temperatureServiceInfo = ServiceInfo.create("_grpc._tcp.local.", "TemperatureService", 50060, "Temperature gRPC service");
            jmdns.registerService(temperatureServiceInfo);

            ServiceInfo registryServiceInfo = ServiceInfo.create("_grpc._tcp.local.", "RegistryService", 50058, "Registry gRPC service");
            jmdns.registerService(registryServiceInfo);

            System.out.println("All services have been registered with JmDNS");

            // Keep servers running
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Services have been stopped \n Shutting down servers...");

                if (securityServer != null) {
                    security.shutdown();  // Initiate graceful shutdown
                    try {
                        // Wait for 30 seconds max for the server to terminate
                        if (!security.awaitTermination(30, TimeUnit.SECONDS)) {
                            security.shutdownNow();  // Force shutdown if not terminated in time
                        }
                    } catch (InterruptedException e) {
                        security.shutdownNow();
                    }
                }

                if (temperature != null) {
                    temperature.shutdown();
                    try {
                        if (!temperature.awaitTermination(30, TimeUnit.SECONDS)) {
                            temperature.shutdownNow();
                        }
                    } catch (InterruptedException e) {
                        temperature.shutdownNow();
                    }
                }

                if (registry != null) {
                    registry.shutdown();
                    try {
                        if (!registry.awaitTermination(30, TimeUnit.SECONDS)) {
                            registry.shutdownNow();
                        }
                    } catch (InterruptedException e) {
                        registry.shutdownNow();
                    }
                }

            }));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //Inteceptor Implementation to add authentication to the grpc request
    public static class AuthInterceptor implements ServerInterceptor {

        //Defining authorisation key and token to be used
        private static final String AUTHORIZATION_HEADER = "Authorization";
        private static final String EXPECTED_TOKEN = "Office-System-token";

        @Override
        public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
                ServerCall<ReqT, RespT> call,
                Metadata headers,
                ServerCallHandler<ReqT, RespT> next) {

            String authHeader = headers.get(Metadata.Key.of(AUTHORIZATION_HEADER, Metadata.ASCII_STRING_MARSHALLER));
            if (authHeader == null || !authHeader.equals(EXPECTED_TOKEN)) {
                call.close(Status.UNAUTHENTICATED.withDescription("Invalid credentials"), new Metadata());
                return new ServerCall.Listener<ReqT>() {};
            }

            return next.startCall(call, headers);
        }
    }
}



