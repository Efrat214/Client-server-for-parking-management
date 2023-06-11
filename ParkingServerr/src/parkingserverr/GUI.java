/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkingserverr;

import java.awt.BorderLayout;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author 1
 */
public class GUI extends JFrame {
    public static int numCar = 0;
    Server server = new Server();
    JTextArea txta = new JTextArea();
    public GUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new JScrollPane(txta), BorderLayout.CENTER);
        setTitle("MultiThreadServer");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        txta.append("MultiThreadServer started at " + new Date() + '\n');
        numCar = 1;
        while (true) {
            //מאזין ללקוחות שיתחברו
            Socket socket = server.Accept();
            //הצגת פרטי הלקוח שהתחבר 
            txta.append("starting thread for  client no " + numCar);
            InetAddress address = socket.getInetAddress();
            txta.append("Car " + numCar + "'s host name is " + address.getHostName() + "\n");
            txta.append("Client " + numCar + "'s IP Address is " + address.getHostAddress() + "\n");
            Thread t = new Thread(new ClientHandler(socket));
            t.start();
        }
    }
}
