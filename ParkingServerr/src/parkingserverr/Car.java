/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkingserverr;

import java.io.Serializable;

/**
 *
 * @author 1
 */
public class Car implements Serializable{
    String name;
    int id;

    public Car() {
    }
    
    public Car(String name, int id) {
        this.name = name;
        this.id = id;
        System.out.println("car"+this.id+"is waiting to parking");
    }
    @Override
    public String toString(){
        return "car num:"+this.id+" "+this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
