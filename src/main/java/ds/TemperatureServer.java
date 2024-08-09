package ds;
import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import generated.Temperature.*;
import io.grpc.Metadata;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
/**
 * @author Sylvia
 */
public class TemperatureServer extends TemperatureServiceGrpc.TemperatureServiceImplBase{

    public static void main(String[] args) {
        //Server Definition
        TemperatureServer temperatureServer=new TemperatureServer();
        int port = 50060;
        try {
            //Create and Start Server
            Server server = ServerBuilder.forPort(port)
                    .addService(temperatureServer)
                    .build()
                    .start();
            System.out.println(LocalTime.now().toString() + ": Temperature Server started, listening on " + port);

            // jMDNS Registration
            //Create a jmdns instance
            JmDNS jmDNS=JmDNS.create(InetAddress.getLocalHost());
            System.out.println("Registration: InetAddress.getLocalHost():" + InetAddress.getLocalHost());
            //Register a service
            ServiceInfo serviceInfo=ServiceInfo.create("_temperatureservice_grpc.tcp.local.","Temperature Service",port,"gRPC Temperature Service");
            jmDNS.registerService(serviceInfo);
            System.out.println("Service has been registered with JMNDS" + serviceInfo.getName());

            //Terminating server
            server.awaitTermination();
            //De-registering the services after shutdown
            jmDNS.unregisterAllServices();


        //Error handling
        } catch (IOException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
    //Method to get temperature
    public void getTemperature(SwitchRequest request, StreamObserver<SwitchRequestResponse> responseObserver) {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(2); // Setting waiting time to 2 seconds for the purposes of testing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int currentTemperature = getCurrentTemperature(); // Get current temperature
            SwitchRequestResponse response = SwitchRequestResponse.newBuilder()
                    .setDegrees(currentTemperature)
                    .setMeasure("Celsius")
                    .build();
            responseObserver.onNext(response); // Send temperature update
        }
        responseObserver.onCompleted(); // Complete the streaming
    }

    // Method to get the current temperature and generate random numbers between 10 and 30
    private Random random = new Random();
    private int getCurrentTemperature() {
        //Min and Max Temp
        int minTemp =10;
        int maxTemp=30;
        // Obtain temperature
        return random.nextInt(maxTemp-minTemp+1)+ minTemp;
    }

}
