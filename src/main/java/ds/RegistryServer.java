package ds;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

import  generated.Registry.RegistryServiceGrpc;
import generated.Registry.UploadRequest;
import generated.Registry.UploadResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

/**
 * @author Sylvia
 */
public class RegistryServer extends RegistryServiceGrpc.RegistryServiceImplBase {
    public static void main(String[] args) {
        RegistryServer registryServer = new RegistryServer();

        int port = 50058;

        try {


            Server server = ServerBuilder.forPort(port)
                    .addService(registryServer)
                    .build()
                    .start();


            System.out.println(LocalTime.now().toString() + ": Registry Server started, listening on " + port);

            server.awaitTermination();


        } catch (IOException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    //Upload Documents Service Method Definition
    public StreamObserver<UploadRequest> uploadDocuments(StreamObserver<UploadResponse> responseObserver) {
        return new StreamObserver<UploadRequest>() {
            //Method to receive the upload requests and add the
            // file name to the array list
            ArrayList <String> documents = new ArrayList<>();
            public void onNext(UploadRequest request) {
                System.out.println("Received file: " + request.getFileName() + " for registryID: " + request.getRegistryID());
                documents.add(request.getFileName());
            }
            //In the case of any error,this is caught
            public void onError(Throwable t) {
                t.printStackTrace();
            }
            //Method to send back a response to the client when
            //Upload is completed successfully


            public void onCompleted() {
                UploadResponse response = UploadResponse.newBuilder().setValidation("Upload Success").build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }

}
