package ds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Sylvia
 */
public class OfficeSystemGUI extends JFrame {
    public OfficeSystemGUI() {
        // Set up the frame
        setTitle("Office Services Panel");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1, 10, 10));
        add(mainPanel);

        // Upload Document Panel
        JPanel uploadPanel = new JPanel();
        uploadPanel.setBorder(BorderFactory.createTitledBorder("1. Upload Document"));
        uploadPanel.setLayout(new BorderLayout());
        JTextField filePathField = new JTextField();
        JButton browseButton = new JButton("Browse...");
        JButton uploadButton = new JButton("Upload");
        JPanel uploadControls = new JPanel(new BorderLayout());
        uploadControls.add(filePathField, BorderLayout.CENTER);
        uploadControls.add(browseButton, BorderLayout.EAST);
        uploadPanel.add(uploadControls, BorderLayout.NORTH);
        uploadPanel.add(uploadButton, BorderLayout.SOUTH);
        mainPanel.add(uploadPanel);

        // Temperature Control Panel
        JPanel tempPanel = new JPanel();
        tempPanel.setBorder(BorderFactory.createTitledBorder("2. Temperature Control"));
        tempPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel tempStatusLabel = new JLabel("Click to get Temperature Updates");
        JButton tempOnButton = new JButton("Get Temperature");
        tempPanel.add(tempStatusLabel);
        tempPanel.add(tempOnButton);
        mainPanel.add(tempPanel);

        // Door Lock Control Panel
        JPanel doorPanel = new JPanel();
        doorPanel.setBorder(BorderFactory.createTitledBorder("3. Door Lock Control"));
        doorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel doorStatusLabel = new JLabel("Current Status: Unlocked");
        JButton lockButton = new JButton("Lock Door");
        JButton unlockButton = new JButton("Unlock Door");
        doorPanel.add(doorStatusLabel);
        doorPanel.add(lockButton);
        doorPanel.add(unlockButton);
        mainPanel.add(doorPanel);

        // Clock-In Time Query Panel
        JPanel clockPanel = new JPanel();
        clockPanel.setBorder(BorderFactory.createTitledBorder("4. Clock-In Time Query"));
        clockPanel.setLayout(new BorderLayout());
        JTextField employeeIdField = new JTextField();
        JButton queryButton = new JButton("Query");
        JLabel lastClockInLabel = new JLabel("Last Clock-In: ");
        clockPanel.add(new JLabel("Enter Employee ID:"), BorderLayout.NORTH);
        clockPanel.add(employeeIdField, BorderLayout.CENTER);
        clockPanel.add(queryButton, BorderLayout.EAST);
        clockPanel.add(lastClockInLabel, BorderLayout.SOUTH);
        mainPanel.add(clockPanel);


        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        mainPanel.add(exitButton);

        // Event Handlers
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(OfficeSystemGUI.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    filePathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = filePathField.getText();
                if (!filePath.isEmpty()) {
                    // Call the uploadDocuments method
                    Client.uploadDocuments(Client.registryasyncStub);
                    JOptionPane.showMessageDialog(OfficeSystemGUI.this, "Documents uploaded: " + filePath);
                } else {
                    JOptionPane.showMessageDialog(OfficeSystemGUI.this, "Please select a file to upload.");
                }
            }
        });

        tempOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the getTemperature method
                Client.getTemperature(Client.tempasyncStub);
                tempStatusLabel.setText("Receiving temperature updates...");
            }
        });


        lockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calling the  lockDoor method
                Client.lockDoor(Client.securityBlockingStub);
                doorStatusLabel.setText("Current Status: Locked");
            }
        });



        unlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                doorStatusLabel.setText("Current Status: Unlocked");
            }
        });

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeId = employeeIdField.getText();
                if (!employeeId.isEmpty()) {
                    // Call the clockInQuery method with the employee ID
                    Client.clockInQuery(Client.securityasyncStub);
                    lastClockInLabel.setText("Query sent for Employee ID: " + employeeId);
                } else {
                    JOptionPane.showMessageDialog(OfficeSystemGUI.this, "Please enter an Employee ID.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OfficeSystemGUI().setVisible(true);
            }
        });
    }
}
