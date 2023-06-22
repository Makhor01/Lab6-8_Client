package data;

import java.io.Serializable;
import java.time.LocalDate;

public class Product implements Serializable {
    public Product(String name, Coordinates coordinates, double price, LocalDate localDate, UnitOfMeasure unitOfMeasure, Organization organization){
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = localDate;
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = organization;
    }
    public Product(Integer id, String name, Coordinates coordinates, double price, LocalDate localDate, UnitOfMeasure unitOfMeasure, Organization organization){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = localDate;
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = organization;
    }
    public Product(String name, Coordinates coordinates, double price, UnitOfMeasure unitOfMeasure, Organization organization){
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = organization;
    }
    private Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates ; //Поле не может быть null
    private LocalDate creationDate = LocalDate.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double price; //Значение поля должно быть больше 0
    private UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private Organization manufacturer; //Поле не может быть null



    public void setId(Integer ID){
        this.id = ID;
    }
    public void setName(String n){
        this.name = n;
    }
    public void setDate(){
        creationDate = LocalDate.now();
    }
    public void setXCoordinate(Long x){
        this.coordinates.setX(x);
    }
    public void setYCoordinate(double y){
        this.coordinates.setY(y);
    }
    public void setPrice(double price){
        this.price=price;
    }
    public void setUnitOfMeasure(UnitOfMeasure unit){
        this.unitOfMeasure = unit;
    }
    public void setManufacturerID(Long id){
        this.manufacturer.setId(id);
    }

    public Integer getId() {
        return id;
    }
    public String getName() { return name; }
    public LocalDate getCreationDate() { return creationDate; }
    public Coordinates getCoordinates() { return coordinates; }

    public Organization getManufacturer() {
        return manufacturer;
    }
    public double getPrice() {
        return price;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID='" + id + '\'' +
                "name='" + name + '\'' +
                ", coordinates='" + coordinates.getX()  + ',' + coordinates.getY() + '\''+
                ", date='" + creationDate + "\'"+
                ", price='" + price + '\'' +
                ", unit of measure='" + unitOfMeasure + '\'' +
                ", organozation name='" + manufacturer.getName() + '\'' +
                ", organization fullname='" + manufacturer.getFullName() + '\'' +
                ", organization type='" + manufacturer.getType() + '\'' +
                ", organization address='" + manufacturer.getPostalAddress().getStreet() + '\'' +
                '}';
    }
}
