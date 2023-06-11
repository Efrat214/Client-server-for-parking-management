/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkingserverr;

import java.awt.Color;
import java.awt.Label;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static parkingserverr.Server.parking;

/**
 *
 * @author 1
 */
public class ClientHandler extends JFrame implements Runnable {

    int y = 1;
    Socket socket;
    //אוביקט לכתיבה
    private ObjectOutputStream outputToClient;
    //אוביקט לקריאה  
    private ObjectInputStream inputFromClient;
    int[] a = new int[3];

    static int numPark = 0;
    public static Vector<ObjectOutputStream> colorOutput = new Vector<ObjectOutputStream>();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Car car = new Car();
        try {
            outputToClient = new ObjectOutputStream(socket.getOutputStream());
            inputFromClient = new ObjectInputStream(socket.getInputStream());
            colorOutput.add(outputToClient);
            outputToClient.writeObject(parking);
            while (true) {
                try {
                    //השרת מקבל את הרכב של הלקוח
                    car = (Car) inputFromClient.readObject();
                    int x = findParking(car);
                    System.out.println("x:" + x);
                    int numForClient = x * y;
                    outputToClient.writeObject(numForClient + 20);
                    SendMessageToClients(numForClient);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void SendMessageToClients(int x) throws IOException {
        for (int i = 0; i < colorOutput.size(); i++) {

            System.out.println("send..." + x);
            colorOutput.elementAt(i).writeObject(x);
        }
    }

    public synchronized int findParking(Car car) {
        for (Parking item : parking) {
            System.out.println("\nparking " + item.toString());
            if ((item.getCar().getName()).equals(car.getName()) && item.getCar().getId() == car.getId()) {
                exit(car);
                return -1;
            }
        }
        for (Parking c : parking) {
            if (c.status == false)
                synchronized (c) {
                c.status = true;
                c.car = car;
                numPark++;
                System.out.println("in the find functioin" + c.numParking);
                y = 1;
                return c.numParking;
            }
        }
        return 0;
    }

    synchronized private void exit(Car car) {
        for (Parking item : parking) {
            if ((item.getCar().getName()).equals(car.getName()) && item.getCar().getId() == car.getId()) {
                y = item.numParking;
                item.status = false;
            }
        }
        numPark--;
        System.out.println("num park: " + numPark);
    }
}
