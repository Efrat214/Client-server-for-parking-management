/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkingclientt;

import parkingserverr.Car;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import parkingserverr.Parking;

/**
 *
 * @author 1
 */
public class GUI extends JFrame {

    Client c = new Client();
    JPanel pMain = new JPanel();
    Vector<Parking> p = new Vector<Parking>();
    JLabel lablename = new JLabel("Enter name car");
    JTextField nameCar = new JTextField();
    JLabel lableid = new JLabel("Enter num car");
    JTextField idCar = new JTextField();
    JButton benter = new JButton("manage");
    JButton bexit = new JButton("exit");
    JPanel pParking = new JPanel();
    int numParking = 4;
    int i = 0;
    String[][] mat = new String[numParking][numParking];
    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            p = c.readFromServer2();
            int i = 0;
            for (Parking item : p) {
                JLabel lbl = new JLabel();
                lbl.setText("" + (++i));
                lbl.setFont(new Font("Calibri", Font.BOLD, 16)); // Set the font size to 16, style to bold, and font family to Calibri

// Set the text color to white
                lbl.setForeground(Color.WHITE);
                lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                lbl.setHorizontalAlignment(JLabel.CENTER);
                lbl.setOpaque(true);
                if (item.getStatus().booleanValue() == true) {
                    lbl.setBackground(new Color(0, 0, 139));
                } else {
                    lbl.setBackground(new Color(0x5CE1E6));
                }
                lbl.setPreferredSize(new Dimension(60, 60));
                pParking.add(lbl);

            }

            while (true) {
                boolean flag = false;
                int parking = c.readFromserver();
                if (parking > 9) {
                    parking = parking - 20;
                    flag = true;
                }

                if (parking == 0 && flag) {
                    JOptionPane.showMessageDialog(null, "no parking");
                } else {
                    for (Component c : pParking.getComponents()) {
                        JLabel lb = new JLabel();
                        if (c instanceof JLabel) {
                            lb = (JLabel) c;
                        }
                        if (parking < 0) {
                            if (Integer.parseInt(lb.getText().trim()) == (parking * -1)) {
                                lb.setBackground(new Color(0x5CE1E6));
                                if (flag) {
                                    JOptionPane.showMessageDialog(null, "exit from parking " + parking * -1);
                                }
                            }

                        } else if (Integer.parseInt(lb.getText()) == parking) {
                            System.out.println("" + parking);
                            lb.setBackground(new Color(0, 0, 139));
                            if (flag) {
                                JOptionPane.showMessageDialog(null, "the parking number is  " + parking);
                            }
                        }

                    }

                }
            }
        }
    });

    public GUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        idCar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInput();
            }

            private void validateInput() {
                String input = idCar.getText();
                if (!input.matches("\\d*")) {
                    // Non-digit characters found
                    // Display an error message or highlight the text field to indicate the invalid input
                    JOptionPane.showMessageDialog(null, "Please enter only numeric characters.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    idCar.setBackground(Color.PINK); // Set background color to indicate the invalid input
                } else {
                    idCar.setBackground(Color.WHITE); // Set background color back to normal
                }
            }
        });
        nameCar.setPreferredSize(new Dimension(200, 10));
        idCar.setPreferredSize(new Dimension(200, 10));
        benter.setPreferredSize(new Dimension(500, 30));
        bexit.setPreferredSize(new Dimension(90, 30));
        pMain.add(lablename);
        pMain.add(nameCar);
        pMain.add(lableid);
        pMain.add(idCar);
        // pMain.add(benter);
        pMain.add(benter, 1, 4);
        //  pMain.add(bexit);
        add(pMain);
        pMain.setLayout(new GridLayout(3, 2));
        t.start();
        this.add(pParking);
        this.setLayout(new GridLayout(2, 1));
        benter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    c.writeToserver(new Car(nameCar.getText(), Integer.parseInt(idCar.getText())));
                    //c.writeToserver1(new Car(nameCar.getText(),Integer.parseInt(idCar.getText())));
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void readFromServer(int parking) {
        System.out.println("the input from servr" + "" + parking);
        if (parking > 0) {
            JOptionPane.showMessageDialog(null, "the parking number is  " + parking);
            //   l[parking-1].setBackground(Color.red);
        } else {
            if (parking == 0) {
                JOptionPane.showMessageDialog(null, "no parking");
            } else {
                JOptionPane.showMessageDialog(null, "exit from parking" + parking * -1);
            }
        }

    }
}

