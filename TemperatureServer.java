package ds;


import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

import generated.Temperature.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

/**
 * @author Sylvia
 */
public class TemperatureServer extends TemperatureServiceGrpc.TemperatureServiceImplBase{
    public static void main(String[] args) {
        //Server Definition
        TemperatureServer temperatureServer=new TemperatureServer();
        int port = 50060;
        try {


            Server server = ServerBuilder.forPort(port)
                    .addService(temperatureServer)
                    .build()
                    .start();


            System.out.println(LocalTime.now().toString() + ": Temperature Server started, listening on " + port);

            server.awaitTermination();


        } catch (IOException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
    public void getTemperature(SwitchRequest request, StreamObserver<SwitchRequestResponse> responseObserver) {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(2); // Setting waiting time to 2 seconds for the purposes of testing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int currentTemperature = getCurrentTemperature(); // Get current temperature dynamically
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
