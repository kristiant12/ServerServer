 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Business.Admin;
import Business.Case;
import Business.Customer;
import Business.Employee;
import Business.Manufacturer;
import Business.Ticket;
import Business.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicol
 */
public class Database {
    
    Connection db = null;
    private final String url = "jdbc:postgresql://horton.elephantsql.com:5432/ddrapuye";
    private final String username = "ddrapuye";
    private final String password = "FdCxvGtgDcCFm0Jc2Oixndk3t9SGy8YF";
    
     public Database() {
        try {
            Class.forName("org.postgresql.Driver");
            db = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
        }
    }
     
     public String SQLTest(String i){
         Statement a = null;
         ResultSet øv = null;
         String test = "";
         try{
             a = db.createStatement();
             øv = a.executeQuery("select * from test where test = '" + i + "'");
             while(øv.next()){
                 test = øv.getString(2);
             }
         }
         catch (Exception e){
             
         }
         return test;
     }
       
     
     
     
     
    // har noget at gøre med at få user
     
     
     public List<User> getUser(){
        List<User> list = new ArrayList(); 
        list.clear(); 
        list.addAll(getAdmin());
        list.addAll(getCustomer());
        list.addAll(getEmployee());
        list.addAll(getManufacturer());
        
        return list;
    }
    
    
    private List<User> getAdmin(){
        Statement a = null;
        ResultSet øv = null;
        List<User> list = new ArrayList();
        String userName = "";
        String password = "";
        try{
            a = db.createStatement();
            øv = a.executeQuery("select * from admin");
            
            while(øv.next()){
                userName = øv.getString(1);
                password = øv.getString(2);
                list.add(new Admin(password, userName));
            }
            
            
        } catch (Exception ex) {

        }
        return list;
    }
     
       private List<User> getManufacturer(){
        Statement a = null;
        ResultSet øv = null;
        List<User> list = new ArrayList();
        String userName = "";
        String password = "";
        String firmaddress = "";
        int number =0;
        String firmName = "";
        String firmEmail = "";
        try{
            a = db.createStatement();
            øv = a.executeQuery("select * from manufacturer");
            
            while(øv.next()){
                userName = øv.getString(1);
                password = øv.getString(2);
                firmaddress = øv.getString(3);
                number = øv.getInt(4);
                firmName = øv.getString(5);
                firmEmail = øv.getString(6);
                
                list.add(new Manufacturer(password, userName, firmaddress, number, firmName, firmEmail));
            }
            
            
        } catch (Exception ex) {

        }
        return list;
    }
        private List<User> getCustomer(){
        Statement a = null;
        ResultSet øv = null;
        List<User> list = new ArrayList();
        String userName = "";
        String password = "";
        String address = "";
        int number = 0;
        String email = "";
        String fullName = "";
        
        try{
            a = db.createStatement();
            øv = a.executeQuery("select * from customer");
            
            while(øv.next()){
                userName = øv.getString(1);
                password = øv.getString(2);
                address = øv.getString(3);
                number = øv.getInt(4);
                email  = øv.getString(5);
                fullName = øv.getString(6);
                list.add(new Customer(password, userName, address, number, email, fullName) );
            }
            
            
        } catch (Exception ex) {

        }
        return list;
    }
    
        private List<User> getEmployee(){
        Statement a = null;
        ResultSet øv = null;
        List<User> list = new ArrayList();
        String userName = "";
        String password = "";
        try{
            a = db.createStatement();
            øv = a.executeQuery("select * from employee");
            
            while(øv.next()){
                userName = øv.getString(1);
                password = øv.getString(2);
                list.add(new Employee(password, userName));
            }
            
            
        } catch (Exception ex) {

        }
        return list;
    }
    
        
        
        
        
        
        
        
        // har får man cases
        
        
        private List<Case> findCase(){
            
            Statement a = null;
            ResultSet øv = null;
            List<Case> list = new ArrayList();
            
            String title = "";
            String caseID = "";
            String budget = "";
            String deadline  = "";
            String component = "";
            String freeText = "";
            String test = "";
            boolean redgjordt = false;
            
            
            try{
                a = db.createStatement();
                øv = a.executeQuery("select * from cases");
                
                while(øv.next()){
                    title = øv.getString(1);
                    caseID = øv.getString(2);
                    budget = øv.getString(3);
                    deadline = øv.getString(4);
                    component = øv.getString(5);
                    freeText = øv.getString(6);
                    test = øv.getString(7);
                    if(test.equals("1")){
                        redgjordt = true;
                    }else{
                        redgjordt = false;
                    }
                    
                    
                    list.add(new Case(title, caseID, budget, deadline, component, redgjordt, freeText));
                    
                }
                
            }catch (Exception ex){
                
            }
            return list;
        } 
        
        public List<Case> getCases(){
            List<Case> list = new ArrayList();
            list = findCase();
            return list;
        }


        
        
        
        
        // hved ikke lige hvad dennne gør
        public Case insertIntoDb(Case caseid){
            Case a = caseid;
            return a;
            
        }
        
        
        
        
        
        // her kan der oprettes burger
        
        public void createEmployee(Employee j) {
        Statement a = null;
        ResultSet øv = null;
       
        try {
            a = db.createStatement();
            øv = a.executeQuery("insert into employee values('" + j.getPassword()+ "','" + j.getUserName()+ "')");
        } catch (Exception e) {

        }
    }
        
    public void createAdmin(Admin j) {
        Statement a = null;
        ResultSet øv = null;
       
        try {
            a = db.createStatement();
            øv = a.executeQuery("insert into admin values('" + j.getPassword()+ "','" + j.getUserName()+ "')");
        } catch (Exception e) {

        }
    }
            //der skal byttes om på pass og navne
          public void createCustumer(Customer j) {
        Statement a = null;
        ResultSet øv = null;
       
        try {
            a = db.createStatement();
            øv = a.executeQuery("insert into customer values('" + j.getPassword()+ "','" + j.getUserName()+ "','"+j.getAddress()+"','"+j.getNumber()+"','"+j.getEmail()+"','"+j.getFulName()+"')");
        } catch (Exception e) {

        }
    }
        public void createManufacturer(Manufacturer j) {
        Statement a = null;
        ResultSet øv = null;
       
        try {
            a = db.createStatement();
            øv = a.executeQuery("insert into manufacturer values('" + j.getPassword()+ "','" + j.getUserName()+ "','"+j.getFirmaddress()+"','"+j.getNumber()+"','"+j.getFirmaName()+"','"+j.getFirmaMail()+"')");
        } catch (Exception e) {

        }
    }
           
           public void createUser(User i){
               if(i instanceof Employee){
                   createEmployee((Employee) i);
               }else if(i instanceof Manufacturer ){
                   createManufacturer((Manufacturer) i);
               }else if(i instanceof Admin){
                   createAdmin((Admin) i);
               }else if(i instanceof Customer){
                   createCustumer((Customer) i);
               }
               
           }
           
           
           
           
           
        
           
      
        // her oprettes der sager
        public void createCase(Case g) {
        Statement a = null;
        ResultSet øv = null;
        int test;
        if(g.getEvaluated() == true){
            test = 1;
        }else{
            test = 0;
        }
       
        try {
            a = db.createStatement();
            øv = a.executeQuery("insert into cases values('"+g.getCaseTitle()+"','"+g.getId()+"','"+g.getCaseBudget()+"','"+g.getDeadline()+"','"+g.getComponent()+"','"+g.getFreeText()+"','"+test+"')");
        } catch (Exception e) {

        }
    }
     
        
        public boolean checkIfEmailExist(Customer g){
            Statement a = null;
            ResultSet øv = null;
            
            try {
            a = db.createStatement();
            øv = a.executeQuery("select * from customer where email = ('"+g.getEmail()+"')");
             while(øv.next()){
                if(øv.getString(5).equals(g.getEmail())){
                    return true;
                }
             }   
             
            } catch (Exception e) {
    
        }
        
            return false;
    }        
        
        public void insertInToPercas(Customer aff, Case b){
        Statement a = null;
        ResultSet øv = null;
        try {
            a = db.createStatement();
            øv = a.executeQuery("insert into creates values('" + b.getId() + "','" +aff.getEmail()+ "')");

        } catch (Exception e) {

        }
    }
        
        
        
        
        public void deleteCase(Case g){
            Statement a = null;
            ResultSet øv = null;
            
            try {
            a = db.createStatement();
            øv = a.executeQuery("delete from cases where caseID = ('"+g.getId()+"')");
            } catch (Exception e) {

           }
        }
        
        public void deleteCaseFromCreates(Case g){
            Statement a = null;
            ResultSet øv = null;
            
            try {
            a = db.createStatement();
            øv = a.executeQuery("delete from creates where caseID = ('"+g.getId()+"')");
            } catch (Exception e) {

           }  
        }
        
        
        public void deleteCaseInCaseAndCreates(Case g){
            deleteCaseFromCreates(g);
            deleteCase(g);
        }
        
        
        public void editCase(Case g){
            Statement a = null;
            ResultSet øv = null;
            
             try {
            a = db.createStatement();
            øv = a.executeQuery("update cases set title = '"+g.getCaseTitle()+"', deadline = '"+g.getDeadline()+"', budget = '"+g.getCaseBudget()
                    +"', component = '"+g.getComponent()+"', text = '"+g.getFreeText()+"' where caseid = '"+g.getId()+"'" );
            } catch (Exception e) {

           }
            
        }
        
       public void doStuff(Customer a, Case b){
          if(checkIfEmailExist(a)== true){
              createCase(b);
              insertInToPercas(a, b);
          } 
       }
        
       
       
        public List<Case> getSpecificUserCaseList(Customer per){
            List<Case> list = new ArrayList();
            list = getSpecificPersonCase(per);
            return list;
        }

        
        public List<Case> getSpecificPersonCase(Customer per){
        Statement a = null;
        ResultSet øv = null;
        
        List<Case> test = new ArrayList();
        
        String title = "";
        String caseID = "";
        String budget = "";
        String deadline = "";
        String component = "";
        String freeText = "";
        int value = 0;
        boolean redgjordt = false;
        
        
        try{
            a = db.createStatement();
            øv = a.executeQuery("select * from cases,creates where cases.caseID = creates.caseid And creates.email = '"+per.getEmail()+"'");
 
            
            
            while(øv.next()){
                    title = øv.getString(1);
                    caseID = øv.getString(2);
                    budget = øv.getString(3);
                    deadline = øv.getString(4);
                    component = øv.getString(5);
                    freeText = øv.getString(6);
                    value = øv.getInt(7);
                    
                    if(value == 1){
                        redgjordt = true;
                    }else{
                        redgjordt = false;
                    }
                
                test.add(new Case(title, caseID, budget, deadline, component, redgjordt, freeText) );
            }
                
            
        } catch (Exception ex) {
            
        }
           return test;
           
       } 
      
      
      
      public List<Case> getNotEvaluadedCase(){
        Statement a = null;
        ResultSet øv = null;
      
        List<Case> list = new ArrayList();
            String title = "";
            String caseID = "";
            String budget = "";
            String deadline  = "";
            String component = "";
            String freeText = "";
            String test = "";
            boolean redgjordt = false;

        
         try{
            a = db.createStatement();
            øv = a.executeQuery("select * from cases where boolean = '"+0+"'");
 
            
            while(øv.next()){
                    title = øv.getString(1);
                    caseID = øv.getString(2);
                    budget = øv.getString(3);
                    deadline = øv.getString(4);
                    component = øv.getString(5);
                    freeText = øv.getString(6);
                    test = øv.getString(7);

                    list.add(new Case(title, caseID, budget, deadline, component, redgjordt, freeText));
                }
        } catch (Exception ex) {
            
        }
          return list;
      }
           
      public void EvaluateCase(Case f){
           Statement a = null;
           ResultSet øv = null;
          
           try{
            a = db.createStatement();
            øv = a.executeQuery("update cases set boolean = '1' where caseid = '"+f.getId()+"'");
               addTooAuction(f);
            
           }catch (Exception ex) {
            
        }
      }
      
      private void addTooAuction(Case f){
          Statement a = null;
           ResultSet øv = null;
          
           try{
            a = db.createStatement();
            øv = a.executeQuery("insert into approvedcase values('"+f.getCaseTitle()+"','"+f.getId()+"','"+f.getCaseBudget()+"','"+f.getDeadline()+"','"+f.getComponent()+"','"+f.getFreeText()+"')");
 
           }catch (Exception ex) {
            
        }
      }
      
      // dennne metode skal laves om så den ikke taqer en case da det giver ingen mening <
      public List<Case> getEvaluetaCase(){
           Statement a = null;
        ResultSet øv = null;
      
        List<Case> list = new ArrayList();
            String title = "";
            String caseID = "";
            String budget = "";
            String deadline  = "";
            String component = "";
            String freeText = "";
            
            boolean redgjordt = true;

        
         try{
            a = db.createStatement();
            øv = a.executeQuery("select * from cases where boolean = '"+1+"'");
 
            
            while(øv.next()){
                    title = øv.getString(1);
                    caseID = øv.getString(2);
                    budget = øv.getString(3);
                    deadline = øv.getString(4);
                    component = øv.getString(5);
                    freeText = øv.getString(6);
                  

                    list.add(new Case(title, caseID, budget, deadline, component, redgjordt, freeText));
                }
        } catch (Exception ex) {
            
        }
          return list;
          
      }
      
//      private void deleteAllCustomCasesFromCreates(Customer ff){
//        Statement a = null;
//        ResultSet øv = null;
//      
//             try{
//            a = db.createStatement();
//            øv = a.executeQuery("delete from creates where email = '"+ff.getEmail()+"'");
// 
//           }catch (Exception ex) {
//            
//        }
//          
//      }
        private void deleteAllCustomCases(Customer ff){
            List<Case> list = getSpecificUserCaseList(ff);
            for (int i = 0; i < list.size(); i++) {
                deleteCaseInCaseAndCreates(list.get(i));
           }     
      }

        
        
      
      private void deleteCustumerUser(Customer f){
        Statement a = null;
        ResultSet øv = null;
      
             try{
            a = db.createStatement();
            øv = a.executeQuery("delete from customer where username = '"+f.getUserName()+"'");
 
           }catch (Exception ex) {
            
        }  
      }
      
      private void deleteEmployee(Employee e){
        Statement a = null;
        ResultSet øv = null;
      
             try{
            a = db.createStatement();
            øv = a.executeQuery("delete from employee where username = '"+e.getUserName()+"'");
 
           }catch (Exception ex) {
        }    
      }
      
      
      private void deleteManufature(Manufacturer m){
        Statement a = null;
        ResultSet øv = null;
      
             try{
            a = db.createStatement();
            øv = a.executeQuery("delete from manufacturer where username = '"+m.getUserName()+"'");
 
           }catch (Exception ex) {
        }  
      }
      private void deleteAdmin(Admin aa){
        Statement a = null;
        ResultSet øv = null;
      
             try{
            a = db.createStatement();
            øv = a.executeQuery("delete from admin where loginname = '"+aa.getUserName()+"'");
 
           }catch (Exception ex) {
        }    
      }
      
      
      public void deleteUser(User f){
          
          if(f instanceof Customer){
              deleteAllCustomCases((Customer) f);
//              deleteAllCustomCasesFromCreates((Customer) f);
              deleteCustumerUser((Customer) f);
          }else if(f instanceof Manufacturer){
              deleteManufature((Manufacturer) f);
          }else if(f instanceof Employee){
              deleteEmployee((Employee) f);
          }else if(f instanceof Admin){
              deleteAdmin((Admin) f);
          }
      }
      
      
      private void CreateTicket(Ticket t){
        Statement a = null;
        ResultSet øv = null;
      
             try{
            a = db.createStatement();
            øv = a.executeQuery("insert into tickets values ('"+t.getIssuenumber()+"','"+t.getIssueDescription()+"')");
 
           }catch (Exception ex) {
        } 
      }

      private void insertIntoMakes(Ticket t, Customer c){
        Statement a = null;
        ResultSet øv = null;
      
             try{
            a = db.createStatement();
            øv = a.executeQuery("insert into makes values ('"+t.getIssuenumber()+"','"+c.getEmail()+"')");
 
           }catch (Exception ex) {
        }  
      }
      

      public void CustumerCreateTicket(Ticket t,Customer c){
          if(checkIfEmailExist(c) == true){
            CreateTicket(t);
            insertIntoMakes(t, c);  
          }
      }
      
      public void insertIntoReply(Ticket t, Employee e){
        Statement a = null;
        ResultSet øv = null;
      
             try{
            a = db.createStatement();
            øv = a.executeQuery("insert into reply values ('"+t.getIssuenumber()+"','"+e.getUserName()+"')");
 
           }catch (Exception ex) {
        }  
      }
      
//     public void updateTicket(){
//        Statement a = null;
//        ResultSet øv = null;
//      
//             try{
//            a = db.createStatement();
//            øv = a.executeQuery("insert into reply values ('"+t.getIssuenumber()+"','"+e.getUserName()+"')");
// 
//           }catch (Exception ex) {
//        }  
//     }
//      
      public List<Ticket> getAlleTickets(Customer c){
          Statement a = null;
          ResultSet øv = null;
          List<Ticket> list = new ArrayList();
          String idNumber = null;
          String desiption = null;
          
             try{
            a = db.createStatement();
            øv = a.executeQuery("select * from tickets,makes where makes.ticketid = tickets.ticketid AND makes.email = '"+c.getEmail()+ "'");
            while(øv.next()){
                idNumber = øv.getString(1);
                desiption = øv.getString(2);
                list.add(new Ticket(idNumber, desiption));
            }
           }catch (Exception ex) {
        }  
             return list;
             
      }
      
      
      
      
      
      
      // manufacture dellen 
      
         public void editManufatur(Manufacturer m){
            Statement a = null;
            ResultSet øv = null;
            
             try {
            a = db.createStatement();
            øv = a.executeQuery("update manufacturer set firmname = '"+m.getFirmaName()+"', password = '"+m.getPassword()+"', firmaddress = '"+m.getFirmaddress()
                    +"', number = '"+m.getNumber()+"', firmemail = '"+m.getFirmaMail()+"' where username = '"+m.getUserName()+"'" );
            } catch (Exception e) {

           }
      
         }
      
      
      
      
         
         public List<Ticket> getAllTicketsEmployee(){
          Statement a = null;
          ResultSet øv = null;
          List<Ticket> list = new ArrayList();
          String idNumber = null;
          String desiption = null;
          
             try{
            a = db.createStatement();
            øv = a.executeQuery("select * from tickets");
            while(øv.next()){
                idNumber = øv.getString(1);
                desiption = øv.getString(2);
                list.add(new Ticket(idNumber, desiption));
            }
           }catch (Exception ex) {
        }  
             return list;  
             
         }
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
       
      
      
      
      
      
      public static void main(String[] args) {
        Database a = new Database();
       
        Ticket t = new Ticket("ABC123","der er nogle fejl");
        
        a.CreateTicket(t);
          
          
    }
        
}
