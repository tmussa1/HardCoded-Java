
package edu.udc.dbobj;

import edu.udc.bank.AcctType;
import static edu.udc.bank.AcctType.ACCT_BUS;
import static edu.udc.bank.AcctType.ACCT_CC;
import static edu.udc.bank.AcctType.ACCT_CD;
import static edu.udc.bank.AcctType.ACCT_CHECKING;
import static edu.udc.bank.AcctType.ACCT_LOAN;
import static edu.udc.bank.AcctType.ACCT_SAVINGS;
import edu.udc.bank.BankAcctInterface;
import edu.udc.bank.Depositor;
import edu.udc.util.Utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tofik Mussa and Connor Ireland
 */

//This class popultates data
//from the database into two pojo classes

public class BankServer {
   
   public static BankServer bs;
   
   private BankServer() {}
   private Connection connection = null;
   
   public static BankServer getInstance(){
       if(bs == null)
           bs = new BankServer();
       return bs;
   }
   
   public void connect(String dbName){
    if(connection != null)
        return;
    try 
    {
        String dbUrl = "jdbc:derby:"+dbName;
        connection = DriverManager.getConnection(dbUrl);
        if(connection == null)
            System.out.println("conn null");
    }
    catch(SQLException sqe)
    {
        System.out.println("connect Error " + sqe);
    }
}
   /*
   CREATE TABLE BankAcct(

ba_id int not null generated by default as identity(start with 100, increment by 3),

ba_holder varchar(70) not null,

ba_value double not null,

ba_type VARCHAR(3) not null,  --ch,sav,cc,cd,ln,bu

ba_dtopen date not null,

ba_special_info VARCHAR(100),

ba_howadded varchar(10)

) ;

 

CREATE TABLE depositor(

dp_id int not null generated by default as identity(start with 693, increment by 2),

dp_ba_id int not null,

dp_addr varchar(30) not null,

dp_postal_code varchar(12) ,

dp_pic blob(1M)

) ;
 */  
   public ArrayList<BankAcctInterface> getAllAccts(){
       Statement st = null;
       ResultSet rs = null;
       
       ArrayList<BankAcctInterface> baList = new ArrayList<BankAcctInterface>();
       
       String sql = "Select ba_id,ba_value,ba_holder,ba_type,ba_dtopen,ba_special_info, ba_howadded, dp_id,"
               + " dp_ba_id, dp_addr, dp_postal_code, dp_pic from " +
               "BankAcct left join depositor on ba_id = dp_ba_id";
        try
        {
        st = connection.createStatement();
        rs = st.executeQuery(sql);
        
        while(rs.next()){
         BankImplement baImp = new BankImplement();
         DepositorImplement deImp = new DepositorImplement();
         
         baImp.UniqueId = rs.getString("ba_id");
         baImp.AcctValue = rs.getDouble("ba_value");
         baImp.AcctHolder = rs.getString("ba_holder");
         baImp.DateOpen = rs.getDate("ba_dtopen");
         baImp.specialInfo = rs.getString("ba_special_info");
         baImp.howAdded = rs.getString("ba_howadded");
         String acctType= rs.getString("ba_type");
         
         if(acctType == "ch"){
            baImp.AcctEnum = ACCT_CHECKING;
         } else if (acctType == "sav"){
             baImp.AcctEnum = ACCT_SAVINGS;
         } else if(acctType == "cc"){
             baImp.AcctEnum = ACCT_CC;
         } else if(acctType == "cd"){
             baImp.AcctEnum = ACCT_CD;
         } else if(acctType == "ln"){
             baImp.AcctEnum = ACCT_LOAN;
         } else if(acctType == "bu"){
             baImp.AcctEnum = ACCT_BUS;
         }
         
         
         deImp.personID = rs.getString("dp_id");
         deImp.postalCode = rs.getString("dp_postal_code");
         deImp.streetAddr = rs.getString("dp_addr");
         
         baImp.depo = deImp;
         
         
         baList.add(baImp);
            
        }
        
        } catch(SQLException sqe){
            System.out.println("Error " + sqe);
        }
        return baList;
   }
   
   //This updates the database successfully
   
   public int updateAcct(BankAcctInterface ba) throws SQLException{
       String sql = "Update BankAcct Set ba_holder =? where ba_id = ?";
       
       PreparedStatement pst = connection.prepareStatement(sql);
       
       pst.setString(1,ba.getAcctHolder());
       pst.setString(2, ba.getUniqueID());
       pst.executeUpdate();
       
       return 1;
   }
}
