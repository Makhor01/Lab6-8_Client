package data;

import java.io.Serializable;

public class Organization implements Serializable {

    private Long id ; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String fullName; //Длина строки не должна быть больше 1256, Поле может быть null
    private OrganizationType type; //Поле может быть null

    private Address postalAddress = new Address(); //Поле не может быть null
    public Organization(Long id, String name,String fullName, OrganizationType type, Address adress){
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.type=type;
        this.postalAddress = adress;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public void setOrganizationType(OrganizationType type){
        this.type = type;
    }
    public void setAdress(String Adress){
        this.postalAddress.setStreet(Adress);
    }
    public Long getId(){ return id;}

    public String getName() {
        return name;
    }
    public String getFullName(){
        return  fullName;
    }
    public OrganizationType getType() {
        return type;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }
}
