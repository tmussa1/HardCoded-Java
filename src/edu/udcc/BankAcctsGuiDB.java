
package edu.udcc;

import edu.udc.bank.AcctType;
import static edu.udc.bank.AcctType.ACCT_BUS;
import static edu.udc.bank.AcctType.ACCT_CC;
import static edu.udc.bank.AcctType.ACCT_CD;
import static edu.udc.bank.AcctType.ACCT_CHECKING;
import static edu.udc.bank.AcctType.ACCT_SAVINGS;
import edu.udc.bank.BankAcctInterface;
import edu.udc.dbobj.BankImplement;
import edu.udc.dbobj.BankServer;
import edu.udc.dbobj.DBServer;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Tofik Mussa and Connor Ireland
 */

//This is out GUI

public class BankAcctsGuiDB extends javax.swing.JFrame {

    private customTableModel CTM;
    private BankServer bvr;
    List<BankAcctInterface> listbac;
    
    public BankAcctsGuiDB() {
        initComponents();
        
        bvr = BankServer.getInstance();
        initControls();
    }

    private void initControls(){
        CTM = new customTableModel();
        jTable1.setModel(CTM);
        //jTable1.setAutoResizeMode(WIDTH);
        
        bvr.connect("\\\\TOSHIBA\\Users\\TOSHIBAPC\\Desktop\\bank_db");
        //DBServer.createDBData("\\\\TOSHIBA\\Users\\TOSHIBAPC\\Desktop\\bank_db");
        listbac = bvr.getAllAccts();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BankAcctsGuiDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BankAcctsGuiDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BankAcctsGuiDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BankAcctsGuiDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BankAcctsGuiDB().setVisible(true);
            }
        });
    }
    
    //Our custom table model
    
    class customTableModel extends AbstractTableModel{
       
        
        customTableModel(){
            
            //Collections.sort(listbac, new BankComparator());
        }
        
        String [] columnNames = new String [] {"Acct #", "Acct Type", "Holder Name", "$ Value",
            "Special Info", "Street Address", "Postal Code" ,"Date Opened"};
        @Override
        public String getColumnName(int colIndex){
            return columnNames[colIndex];
        }
        @Override
        public int getRowCount() {
            return listbac.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int row, int col) {
            BankAcctInterface bac = listbac.get(row);
           
            switch(col){
                    case 0:
                        return bac.getUniqueID();
                        
                    case 1:
                        if (bac.getAcctType()== ACCT_BUS) {return "Business";                }
                        else if (bac.getAcctType() == ACCT_CC) { return "Credit Card";                }
                        else if (bac.getAcctType() == ACCT_CD) { return "Certificate";                }
                        else if (bac.getAcctType() == ACCT_CHECKING) { return "Checking";                }
                        else if (bac.getAcctType() == ACCT_SAVINGS) { return "Savings";                }
                        else { return "Unspecified";}
                
                    case 2:
                        return bac.getAcctHolder();
                    case 3:
                       NumberFormat usFormat = NumberFormat.getCurrencyInstance();
                       return usFormat.format(bac.getAcctValue());
                    case 4:
                       return bac.getSpecialInfo();
                    case 5:
                        return bac.getDepositor().getStreetAddr();
                    case 6:
                        return bac.getDepositor().getPostalCode();
                       
            }
        return new SimpleDateFormat("MMM d, yyyy").format(bac.getDateOpened());
        }
        
        //Updating logic here
        
        public boolean isCellEditable(int row, int col){
            return col == 2;
        }
       
        public void setValueAt(Object ob, int row, int col){
            BankImplement bac = (BankImplement) listbac.get(row);
            
            
            if(col == 2){
                bac.AcctHolder = (String) ob;
            }
            
            try{
                int rowsaffected = bvr.updateAcct(bac);
            }
            
            catch(Exception sqe){
                 System.out.println("Updating error " + sqe);
            }
        }
        
      
    }
    
    //Our comparator
    
    class BankComparator implements Comparator<BankAcctInterface>{

        @Override
        public int compare(BankAcctInterface t, BankAcctInterface t1) {
            return (int) (t1.getAcctValue() - t.getAcctValue());
        }   
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
