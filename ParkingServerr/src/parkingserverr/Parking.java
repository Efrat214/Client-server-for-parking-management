/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkingserverr;

import java.io.Serializable;
import java.util.concurrent.Semaphore;

/**
 *
 * @author 1
 */


public class Parking implements  Serializable{
  //  private Semaphore semaphore;
    Boolean status;
    Car car;
    static int num=0;
    int numParking;

    public Parking() {
    }
    
    public Parking(boolean status,Car car) {
        //this.semaphore = new Semaphore(1, true);
        this.status=status;
        this.car=car;
        this.numParking=++num;
    }
     @Override
    public String toString(){
        return "parking num:"+this.numParking+" "+this.car.toString();
    }
  //public void enter(Car car){
    //    try{
      //      semaphore.acquire();
        //    System.out.println(car.toString() + " is now coming to the parking");
          //  Thread.sleep(1000);
            //System.out.println(car.toString() + " is Done crossing the bridge");
            // release the lock
            //semaphore.release();
       // }catch (InterruptedException ex){
         //   ex.printStackTrace();
        //}

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        Parking.num = num;
    }

    public int getNumParking() {
        return numParking;
    }

    public void setNumParking(int numParking) {
        this.numParking = numParking;
    }
    
}




