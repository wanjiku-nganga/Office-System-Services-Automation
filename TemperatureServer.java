package ds;


import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
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

}
