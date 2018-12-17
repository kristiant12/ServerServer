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
     
    /**
     * Adds all users to a list and returns it
     * @return list of users
     */
     
     
     public List<User> getUser(){
        List<User> list = new ArrayList(); 
        list.clear(); 
        list.addAll(getAdmin());
        list.addAll(getCustomer());
        list.addAll(getEmployee());
        list.addAll(getManufacturer());
        
        return list;
    }
    
    /**
     * Adds all admins to a list and returns it
     * @return list of all admins
     */
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
     /**
      * Returns list of all manufaturers
      * @return list of manufacturers
      */
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
       /**
        * Returns list of all customers
        * @return list of customers
        */
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
    /**
     * Returns list of all employees
     * @return List of employees
     */
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
     /**
      * Adds all cases to a list
      * @return list of cases
      */   
        
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
        
    /**
     * Adds a list of acses to an arraylist
     * @return list of cases
     */
    public List<Case> getCases(){
            List<Case> list = new ArrayList();
            list = findCase();
            return list;
        }

    /**
     * ASsigns the caseID of a case to a
     * @param caseid is the ID of a case
     * @return a which has caseID assigned as value
     */
        public Case insertIntoDb(Case caseid){
            Case a = caseid;
            return a;
            
        }
        

    /**
     * Creates a new employee in the database
     * @param j is an employee
     */
        
        public void createEmployee(Employee j) {
        Statement a = null;
        ResultSet øv = null;
       
        try {
            a = db.createStatement();
            øv = a.executeQuery("insert into employee values('" + j.getPassword()+ "','" + j.getUserName()+ "')");
        } catch (Exception e) {

        }
    }
        
    /**
     * Creates a new admin in the database
     * @param j is an admin
     */
    public void createAdmin(Admin j) {
        Statement a = null;
        ResultSet øv = null;
       
        try {
            a = db.createStatement();
            øv = a.executeQuery("insert into admin values('" + j.getPassword()+ "','" + j.getUserName()+ "')");
        } catch (Exception e) {

        }
    }

    /**
     * Creates a new customer in the database
     * @param j is a customer
     */
          public void createCustumer(Customer j) {
        Statement a = null;
        ResultSet øv = null;
       
        try {
            a = db.createStatement();
            øv = a.executeQuery("insert into customer values('" + j.getPassword()+ "','" + j.getUserName()+ "','"+j.getAddress()+"','"+j.getNumber()+"','"+j.getEmail()+"','"+j.getFulName()+"')");
        } catch (Exception e) {

        }
    }

    /**
     * Creates a new manufacturer in the database
     * @param j is a manufacturer
     */
    public void createManufacturer(Manufacturer j) {
        Statement a = null;
        ResultSet øv = null;
       
        try {
            a = db.createStatement();
            øv = a.executeQuery("insert into manufacturer values('" + j.getPassword()+ "','" + j.getUserName()+ "','"+j.getFirmaddress()+"','"+j.getNumber()+"','"+j.getFirmaName()+"','"+j.getFirmaMail()+"')");
        } catch (Exception e) {

        }
    }
           
    /**
     * Creates a user in the database
     * @param i is a user
     */
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
           

    /**
     * Creates a case in the database
     * @param g is a case
     */
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
     
    /**
     * Checks if a mail already is in the database
     * @param g is a customer
     * @return boolean
     */
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
        
    /**
     * Links cases to a customer
     * @param aff is a customer
     * @param b is a case
     */
    public void insertInToPercas(Customer aff, Case b){
        Statement a = null;
        ResultSet øv = null;
        try {
            a = db.createStatement();
            øv = a.executeQuery("insert into creates values('" + b.getId() + "','" +aff.getEmail()+ "')");

        } catch (Exception e) {

        }
    }
        
    /**
     * Deletes a case in the database
     * @param g is a case
     */
    public void deleteCase(Case g){
            Statement a = null;
            ResultSet øv = null;
            
            try {
            a = db.createStatement();
            øv = a.executeQuery("delete from cases where caseID = ('"+g.getId()+"')");
            } catch (Exception e) {

           }
        }
        
    /**
     * deletes a case in the database
     * @param g is a case
     */
    public void deleteCaseFromCreates(Case g){
            Statement a = null;
            ResultSet øv = null;
            
            try {
            a = db.createStatement();
            øv = a.executeQuery("delete from creates where caseID = ('"+g.getId()+"')");
            } catch (Exception e) {

           }  
        }
        
    /**
     * Deletes a case in the database
     * @param g is a case
     */
    public void deleteCasefromAction(Case g){
            
              Statement a = null;
            ResultSet øv = null;
            
            try {
            a = db.createStatement();
            øv = a.executeQuery("delete from auction where caseID = ('"+g.getId()+"')");
            } catch (Exception e) {

           } 
            
        }
        
    /**
     * deletes a case in several tables in the database
     * @param g is a case
     */
    public void deleteCaseInCaseAndCreates(Case g){
            deleteCaseFromCreates(g);
            deleteCase(g);
            deleteCasefromAction(g);
        }
        
    /**
     * Updates the values for a specific case
     * @param g is a case
     */
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
        
    /**
     * checks if email exists, creates a case and inserts it into percas
     * @param a is a customer
     * @param b is a case
     */
    public void doStuff(Customer a, Case b){
          if(checkIfEmailExist(a)== true){
              createCase(b);
              insertInToPercas(a, b);
          } 
       }
        
    /**
     * Gets a list of cases for a specific user
     * @param per is a customer
     * @return list of cases
     */
    public List<Case> getSpecificUserCaseList(Customer per){
            List<Case> list = new ArrayList();
            list = getSpecificPersonCase(per);
            return list;
        }

    /**
     * Gets a specific case for a person
     * @param per is a customer
     * @return list of cases
     */
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
      
    /**
     * Returns a list of all cases not evaluated
     * @return list of cases
     */
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
           
    /**
     * Updates a value for a case so it is now validated
     * @param f is a case
     */
    public void EvaluateCase(Case f){
           Statement a = null;
           ResultSet øv = null;
          
           try{
            a = db.createStatement();
            øv = a.executeQuery("update cases set boolean = '1' where caseid = '"+f.getId()+"'");
//               addTooAuction(f);
            
           }catch (Exception ex) {
            
        }
      }

    /**
     * Evaluates a case and adds it to auction
     * @param f is a case
     */
    public void evaluateCaseAndAddToAuction(Case f){
          EvaluateCase(f);
          addToAction(f);
      }
      
    /**
     * adds a case to auction
     * @param f is a case
     */
    public void addToAction(Case f){
           Statement a = null;
           ResultSet øv = null;
          
           try{
            a = db.createStatement();
            øv = a.executeQuery("insert into auction values ('"+f.getCaseTitle()+"','"+f.getId()+"','"+f.getCaseBudget()+"','"+f.getDeadline()+"','"+f.getComponent()+"','"+f.getFreeText()+"')");
 
           }catch (Exception ex) {
            
        }
      }

    /**
     * Returns a list of all evaluated cases
     * @return list of cases
     */
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
      
      /**
       * Deletes all cases for a specific customer
       * @param ff is a customer
       */
        private void deleteAllCustomCases(Customer ff){
            List<Case> list = getSpecificUserCaseList(ff);
            for (int i = 0; i < list.size(); i++) {
                deleteCaseInCaseAndCreates(list.get(i));
           }     
      }

        
        
    /**
     * Deletes a customer user
     * @param f  is a customer
     */
      private void deleteCustumerUser(Customer f){
        Statement a = null;
        ResultSet øv = null;
      
             try{
            a = db.createStatement();
            øv = a.executeQuery("delete from customer where username = '"+f.getUserName()+"'");
 
           }catch (Exception ex) {
            
        }  
      }
      
     /**
      * Deletes an employee user
      * @param e is an employee
      */
      private void deleteEmployee(Employee e){
        Statement a = null;
        ResultSet øv = null;
      
             try{
            a = db.createStatement();
            øv = a.executeQuery("delete from employee where username = '"+e.getUserName()+"'");
 
           }catch (Exception ex) {
        }    
      }
      
      /**
       * Deletes a manufacturer
       * @param m is a manufacturer
       */
      private void deleteManufature(Manufacturer m){
        Statement a = null;
        ResultSet øv = null;
      
             try{
            a = db.createStatement();
            øv = a.executeQuery("delete from manufacturer where username = '"+m.getUserName()+"'");
 
           }catch (Exception ex) {
        }  
      }
      /**
       * Deletes an admin user
       * @param aa is an admin
       */
      private void deleteAdmin(Admin aa){
        Statement a = null;
        ResultSet øv = null;
      
             try{
            a = db.createStatement();
            øv = a.executeQuery("delete from admin where loginname = '"+aa.getUserName()+"'");
 
           }catch (Exception ex) {
        }    
      }
      
    /**
     * deletes a user
     * @param f is an user
     */
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
      
     /**
      * Creates a ticket in the database
      * @param t is a ticket
      */ 
      private void CreateTicket(Ticket t){
        Statement a = null;
        ResultSet øv = null;
      
             try{
            a = db.createStatement();
            øv = a.executeQuery("insert into tickets values ('"+t.getIssuenumber()+"','"+t.getIssueDescription()+"')");
 
           }catch (Exception ex) {
        } 
      }
      /**
       * Inserts a user specific ticket into a table
       * @param t is a ticket
       * @param c is a customer
       */
      private void insertIntoMakes(Ticket t, Customer c){
        Statement a = null;
        ResultSet øv = null;
      
             try{
            a = db.createStatement();
            øv = a.executeQuery("insert into makes values ('"+t.getIssuenumber()+"','"+c.getEmail()+"')");
 
           }catch (Exception ex) {
        }  
      }
      
    /**
     * Creates a ticket for a customer
     * @param t is a ticket
     * @param c is a customer
     */
    public void CustumerCreateTicket(Ticket t,Customer c){
          if(checkIfEmailExist(c) == true){
            CreateTicket(t);
            insertIntoMakes(t, c);  
          }
      }
      
    /**
     * Inserts a reply to a ticket from an employee
     * @param t is a ticket
     * @param e is an employee
     */
    public void insertIntoReply(Ticket t, Employee e){
        Statement a = null;
        ResultSet øv = null;
      
             try{
            a = db.createStatement();
            øv = a.executeQuery("insert into reply values ('"+t.getIssuenumber()+"','"+e.getUserName()+"')");
 
           }catch (Exception ex) {
        }  
      } 

    /**
     * Gets all tickets for a specific user
     * @param c is a customer
     * @return list of tickets
     */
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
      

    /**
     * edits the values for a manufacturer in the database
     * @param m is a manufacturer
     */
      
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
      
    /**
     * returns a list of all tickets
     * @return list of tickets
     */
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
      
    /**
     * Adds a reply to a ticket from an employee
     * @param t is a ticket
     */
    public void employeeReplyTicket(Ticket t){
            Statement a = null;
            ResultSet øv = null;
            
             try {
            a = db.createStatement();
            øv = a.executeQuery("update tickets set description ='"+t.getIssueDescription()+"\n----------------------\n"+t.getBackMessage()+"\n reply by "+t.getEmployeeName()+"' where ticketid = '"+t.getIssuenumber()+"'" );
            } catch (Exception e) {

           }
      }
      
    /**
     * Returns list of all cases in auction
     * @return list of cases
     */
    public List<Case> getAllCasesInAction(){
            Statement a = null;
            ResultSet øv = null;
            List<Case> caseList = new ArrayList();
            
            String title = "";
            String caseID = "";
            String budget = "";
            String deadline  = "";
            String component = "";
            String freeText = "";
            
            
            try {
            a = db.createStatement();
            øv = a.executeQuery("select * from auction" );
            
                
            while(øv.next()){
                    title = øv.getString(1);
                    caseID = øv.getString(2);
                    budget = øv.getString(3);
                    deadline = øv.getString(4);
                    component = øv.getString(5);
                    freeText = øv.getString(6);
                Case f = new Case(title, caseID, budget, deadline, component, true, freeText);
                caseList.add(f);
            }
            
            } catch (Exception e) {

           }
            return caseList;
      }
      /**
       * Updates a bid on a case
       * @param f is a case
       */
      private void updateBidInCases(Case f){
          Statement a = null;
            ResultSet øv = null;
            
             try {
            a = db.createStatement();
            øv = a.executeQuery("update cases set bid = "+f.getBid()+" where caseid = '"+f.getId()+"'");
            } catch (Exception e) {

           }
      }
      
      
      /**
       * Updates a bid on a case in auction
       * @param f is a case
       */
      private void updateBidInaucktion(Case f){
          Statement a = null;
            ResultSet øv = null;
            
             try {
            a = db.createStatement();
            øv = a.executeQuery("update auction set bid = "+f.getBid()+" where caseid = '"+f.getId()+"'");
            } catch (Exception e) {

           }
      }
      
    /**
     * updates bid on a case
     * @param f is a case
     */
    public void updateBid(Case f){
          updateBidInCases(f);
          updateBidInaucktion(f);
      }
      
    public static void main(String[] args) {
        Database a = new Database();
       
        Case test = new Case ("ding","Case ID 1232","sdsds","fuck jul","  dd",true,"test");
        test.setBid(1000);
        
        a.updateBid(test);
        
          
    }
        
}
