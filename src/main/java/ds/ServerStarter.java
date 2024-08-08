package ds;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;
import io.grpc.Server;
import io.grpc.ServerBuilder;
/**
 * @author Sylvia
 */
public class ServerStarter {
    public static void main(String[] args) {
        try {
            // Starting Security Server
            SecurityServer securityServer = new SecurityServer();
            Server security = ServerBuilder.forPort(50059)
                    .addService(securityServer)
                    .build()
                    .start();
            System.out.println("Security Server started on port 50059");

            // Starting Temperature Server
            TemperatureServer temperatureServer = new TemperatureServer();
            Server temperature = ServerBuilder.forPort(50060)
                    .addService(temperatureServer)
                    .build()
                    .start();
            System.out.println("Temperature Server started on port 50060");

            // Starting Registry Server
            RegistryServer registryServer = new RegistryServer();
            Server registry = ServerBuilder.forPort(50058)
                    .addService(registryServer)
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
            security.awaitTermination();
            temperature.awaitTermination();
            registry.awaitTermination();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
