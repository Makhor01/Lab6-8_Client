package data;

import java.io.Serializable;

public enum OrganizationType implements Serializable {
    COMMERCIAL,
    GOVERNMENT,
    PRIVATE_LIMITED_COMPANY,
    OPEN_JOINT_STOCK_COMPANY;

    @Override
    public String toString() {
        return "OrganizationType:"+"\'"
                +"COMMERCIAL" +"\'"
                +"GOVERNMENT"+ "\'"
                +"PRIVATE_LIMITED_COMPANY" +'\''
                +"OPEN_JOINT_STOCK_COMPANY" +'\'';
    }
}

