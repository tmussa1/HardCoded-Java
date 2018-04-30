
package edu.udc.dbobj;

import edu.udc.bank.AcctType;
import edu.udc.bank.BankAcctInterface;
import edu.udc.bank.Depositor;
import java.util.Date;

/**
 *
 * @author Tofik Mussa and Connor Ireland
 */

//This class implements all the abstract methods of 
// Bank Account Interface
//Again no setters

public class BankImplement implements BankAcctInterface{
    public Depositor depo;
    public String specialInfo;
    public AcctType AcctEnum;
    public Date DateOpen;
    public String AcctHolder;
    public String UniqueId;
    public double AcctValue;
    public String howAdded;
    
    BankImplement(Depositor depo, String specialInfo, AcctType AcctEnum,Date DateOpen, 
            String AcctHolder,String UniqueId,double AcctValue){
        this.depo = depo;
        this.specialInfo = specialInfo;
        this.AcctEnum = AcctEnum;
        this.DateOpen = DateOpen;
        this.AcctHolder = AcctHolder;
        this.UniqueId = UniqueId;
        this.AcctValue = AcctValue;
    }
    BankImplement(){}
    
    @Override
    public Depositor getDepositor() {
        return depo;
    }

    @Override
    public String getSpecialInfo() {
        return specialInfo;
    }

    @Override
    public AcctType getAcctType() {
        return AcctEnum;
    }

    @Override
    public Date getDateOpened() {
        return DateOpen;
    }

    @Override
    public String getAcctHolder() {
        return AcctHolder;
    }

    @Override
    public String getUniqueID() {
        return UniqueId;
    }

    @Override
    public double getAcctValue() {
        return AcctValue;
    }
    
}
