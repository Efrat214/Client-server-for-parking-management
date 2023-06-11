/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkingclientt;

/**
 *
 * @author 1
 */

import parkingserverr.Car;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import parkingserverr.Parking;
public class Client {
    private Socket socket;//ערוץ התקשורת
    private  ObjectInputStream fromServer; //מאפשר קריאה של נתונים מהשרת
    private  ObjectOutputStream toServer;//מאפשר שליחת נתונים לשרת
     public static Vector<Car> Waiting = new Vector<Car>();
    public Client() {
        try {
            socket = new Socket("localhost",8080);//לאיזה כתובת איי פי להתחבר ולאיזה פורט
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());
            System.out.println("the old client connection sucsses");  
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeToserver(Car car) throws IOException {
        //כתיבה לשרת
        toServer.writeObject(car);
        toServer.flush();
    }
        public int readFromserver() {
        //קריאה מהשרת
        try {
            return (int) fromServer.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
        }
        public Vector<Parking> readFromServer2(){
            Vector<Parking> arr=new Vector<Parking>();
        try {
            arr=(Vector<Parking>)fromServer.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
             
        }
}