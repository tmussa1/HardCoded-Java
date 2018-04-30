
package edu.udc.dbobj;

import edu.udc.bank.Depositor;
import edu.udc.bank.PhoneInterface;

/**
 *
 * @author Tofik Mussa and Connor Ireland
 */
//This class implements
//Depositor and all its abstract methods
//we made all the atributes public and don't have setters when we load data into them

public class DepositorImplement implements Depositor{
    public String personID;
    public String streetAddr;
    public String postalCode;
    
    DepositorImplement(String personID, String streetAddr,String postalCode){
        this.personID = personID;
        this.streetAddr = streetAddr;
        this.postalCode = postalCode;
    }
    
    DepositorImplement(){}
    
    @Override
    public String getPersonID() {
        return personID;
    }

    @Override
    public String getStreetAddr() {
        return streetAddr;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public PhoneInterface[] getPhones() {
        return null;
    }
}