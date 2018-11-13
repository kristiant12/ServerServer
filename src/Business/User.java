/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.Serializable;


/**
 *
 * @author sebastian
 */
public class User implements Serializable {
//    public Database mainDatabase = new Database();
    private String password;
    private String userName; 
    static final long serialVersionUID = -7588980448693010399L;
    
    
    
    
    public User(String pass, String userNam){
      
        this.password = pass;
        this.userName = userNam;
}
  

   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    } 
//    
//    public void createCase(String caseTitle, int caseID,double caseBudget,String deadline, String component,boolean evaluated,String freeText){
//        Case SendCase = new Case(caseTitle, caseID,caseBudget,deadline,component,evaluated,freeText);
//        mainDatabase.sendCase(SendCase);
//        
//        
//        
//    }
        public boolean sammenligner(String password, String username, String ID){
        
        // getDatabaseInfo(ID);
        int i = password.compareTo(password);
        
        if(i == 0){
            i = username.compareTo(username);
        }if(i == 0){
            return true;
        }
        return false; 
    }

    @Override
    public String toString() {
        return "{" + "password=" + password + ", userName=" + userName + '}';
    }
        
     
    
}
