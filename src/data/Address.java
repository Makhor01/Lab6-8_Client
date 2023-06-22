package data;

import java.io.Serializable;

public class Address implements Serializable {
    public Address(){
        street = " ";
    }
    private String street; //Поле не может быть null
    public void setStreet(String street){
        this.street = street;
    }

    public String getStreet() {
        return street;
    }
}
