package ds;

import generated.Registry.*;
import generated.Security.*;
import generated.Temperature.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class OfficeSystemGUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JTextArea registryArea;
    private JTextArea securityArea;
    private JTextArea temperatureArea;
    private JButton uploadDocumentsButton;
    private JButton clockInQueryButton;
    private JButton startTemperatureButton;
    private JButton lockDoorButton;

    private ManagedChannel registryChannel;
    private ManagedChannel securityChannel;
    private ManagedChannel tempChannel;
    private RegistryServiceGrpc.RegistryServiceStub registryasyncStub;
    private SecurityServiceGrpc.SecurityServiceStub securityasyncStub;
    private SecurityServiceGrpc.SecurityServiceBlockingStub securityBlockingStub;
    private TemperatureServiceGrpc.TemperatureServiceStub tempasyncStub;

    public OfficeSystemGUI() {
        // Setup GUI components
        setTitle("Office System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        // Creating nd customising  text areas and buttons
        registryArea = new JTextArea(10, 60);
        securityArea = new JTextArea(10, 60);
        temperatureArea = new JTextArea(10, 60);

        registryArea.setEditable(false);
        securityArea.setEditable(false);
        temperatureArea.setEditable(false);

        JScrollPane registryScroll = new JScrollPane(registryArea);
        JScrollPane securityScroll = new JScrollPane(securityArea);
        JScrollPane temperatureScroll = new JScrollPane(temperatureArea);

        uploadDocumentsButton = new JButton("Upload Documents");
        clockInQueryButton = new JButton("Clock In Query");
        startTemperatureButton = new JButton("Start Temperature Updates");
        lockDoorButton = new JButton("Lock Door");

        uploadDocumentsButton.addActionListener(this);
        clockInQueryButton.addActionListener(this);
        startTemperatureButton.addActionListener(this);
        lockDoorButton.addActionListener(this);

        JPanel buttonPanel=new JPanel();
        buttonPanel.setLayout(new GridLayout(2,2,10,10));
        buttonPanel.add(uploadDocumentsButton);
        buttonPanel.add(clockInQueryButton);
        buttonPanel.add(startTemperatureButton);
        buttonPanel.add(lockDoorButton);


        JPanel registryPanel = new JPanel(new BorderLayout());
        registryPanel.setBorder(BorderFactory.createTitledBorder("Registry Service Responses"));
        registryPanel.add(registryScroll, BorderLayout.CENTER);

        JPanel securityPanel = new JPanel(new BorderLayout());
        securityPanel.setBorder(BorderFactory.createTitledBorder("Security Service Responses"));
        securityPanel.add(securityScroll, BorderLayout.CENTER);

        JPanel temperaturePanel = new JPanel(new BorderLayout());
        temperaturePanel.setBorder(BorderFactory.createTitledBorder("Temperature Service Updates"));
        temperaturePanel.add(temperatureScroll, BorderLayout.CENTER);

        // Add components to the frame
        add(buttonPanel, BorderLayout.BEFORE_FIRST_LINE);
        add(registryPanel, BorderLayout.CENTER);
        add(securityPanel, BorderLayout.AFTER_LINE_ENDS);
        add(temperaturePanel, BorderLayout.SOUTH);



        // Initialize GRPC Channels and Stubs
        registryChannel = ManagedChannelBuilder.forAddress("localhost", 50058).usePlaintext().build();
        securityChannel = ManagedChannelBuilder.forAddress("localhost", 50059).usePlaintext().build();
        tempChannel = ManagedChannelBuilder.forAddress("localhost", 50060).usePlaintext().build();

        registryasyncStub = RegistryServiceGrpc.newStub(registryChannel);
        securityasyncStub = SecurityServiceGrpc.newStub(securityChannel);
        securityBlockingStub = SecurityServiceGrpc.newBlockingStub(securityChannel);
        tempasyncStub = TemperatureServiceGrpc.newStub(tempChannel);

        // Set the frame to be visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String label = button.getText();

        switch (label) {
            case "Upload Documents":
                uploadDocuments();
                break;
            case "Clock In Query":
                clockInQuery();
                break;
            case "Start Temperature Updates":
                startTemperatureUpdates();
                break;
            case "Lock Door":
                lockDoor();
                break;
            default:
                break;
        }
    }

    private void uploadDocuments() {
        StreamObserver<UploadResponse> responseObserver = new StreamObserver<UploadResponse>() {
            @Override
            public void onNext(UploadResponse value) {
                registryArea.append("Registry Server response: " + value.getValidation() + "\n");
            }

            @Override
            public void onError(Throwable t) {
                registryArea.append("Error during upload: " + t.getMessage() + "\n");
            }

            @Override
            public void onCompleted() {
                registryArea.append("Upload complete.\n");
            }
        };

        StreamObserver<UploadRequest> requestObserver = registryasyncStub.uploadDocuments(responseObserver);
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
            requestObserver.onCompleted();
        } catch (RuntimeException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void clockInQuery() {
        StreamObserver<ClockInResponse> responseObserver = new StreamObserver<ClockInResponse>() {
            @Override
            public void onNext(ClockInResponse value) {
                securityArea.append("Received Staff ID: " + value.getStaffId() + " Name: " + value.getName() + " Clock In Time: " + value.getClockInTime() + "\n");
            }

            @Override
            public void onError(Throwable t) {
                securityArea.append("Error during clock in query: " + t.getMessage() + "\n");
            }

            @Override
            public void onCompleted() {
                securityArea.append("Clock In Query completed.\n");
            }
        };

        StreamObserver<ClockInRequest> requestObserver = securityasyncStub.clockInquery(responseObserver);
        try {
            requestObserver.onNext(ClockInRequest.newBuilder().setStaffId(456773).build());
            Thread.sleep(500);
            requestObserver.onNext(ClockInRequest.newBuilder().setStaffId(56783).build());
            Thread.sleep(500);
            requestObserver.onNext(ClockInRequest.newBuilder().setStaffId(89736).build());
            Thread.sleep(500);
            requestObserver.onNext(ClockInRequest.newBuilder().setStaffId(134767782).build());
            Thread.sleep(500);
            requestObserver.onCompleted();
        } catch (RuntimeException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void startTemperatureUpdates() {
        SwitchRequest request = SwitchRequest.newBuilder().build();

        StreamObserver<SwitchRequestResponse> responseObserver = new StreamObserver<SwitchRequestResponse>() {
            @Override
            public void onNext(SwitchRequestResponse response) {
                temperatureArea.append("Received temperature: " + response.getDegrees() + " " + response.getMeasure() + "\n");
            }

            @Override
            public void onError(Throwable t) {
                temperatureArea.append("Error receiving temperature updates: " + t.getMessage() + "\n");
            }

            @Override
            public void onCompleted() {
                temperatureArea.append("Temperature streaming completed.\n");
            }
        };

        tempasyncStub.getTemperature(request, responseObserver);

        // Keep the client alive to receive responses
        new Timer(600000, e -> tempChannel.shutdown()).start(); // Shutdown channel after 10 minutes
    }

    private void lockDoor() {
        String location = "Server Room Door - Private Floor";
        LockRequest request = LockRequest.newBuilder().setLocation(location).build();

        try {
            LockRequestResponse response = securityBlockingStub.lockDoor(request);
            securityArea.append("Lock Door Response: " + response.getStatus() + "\n");
        } catch (Exception e) {
            securityArea.append("Error locking door: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OfficeSystemGUI::new);
    }
}
