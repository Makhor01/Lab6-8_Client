package data;

import java.io.Serializable;

public enum UnitOfMeasure implements Serializable {
    METERS(100000),
    CENTIMETERS(1000),
    LITERS(100),
    MILLIGRAMS(10);
    private int value;
    UnitOfMeasure(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }



    /**
     * Generates a beautiful list of enum string values.
     * @return String with all enum values splitted by comma.
     */

    public static String nameList() {
        String nameList = "";
        for(UnitOfMeasure unitOfMeasure : values()) {
            nameList += unitOfMeasure.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
    public String toString() {
        return "UnitOfMeasure:"+"\'"
                +"METERS" +"\'"
                +"CENTIMETERS"+ "\'"
                +"LITERS" +'\''
                +"MILLIGRAMS" +'\'';
    }
}
