/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkingserverr;

/**
 *
 * @author 1
 */
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server implements Serializable{
    private ServerSocket server;
      public static int numParking=4;
      public static Vector<Parking> parking = new Vector<Parking>();
      //  public static Vector<Car> waiting = new Vector<Car>();
    public Server() {
        initParking();
        try {
            server=new ServerSocket(8080);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Socket Accept(){
        try {
            return server.accept();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     public void initParking(){
        for (int i = 0; i < numParking; i++) {
            parking.add(new Parking(false,new Car("",0)));
        }
    }

}
