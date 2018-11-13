/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.Serializable;


/**
 *
 * @author rober
 */
public class Customer extends User implements Serializable{

  private String address;
  private int number;
  private String email;
  private String fulName;

    public Customer(String pass, String userNam,String address,int number,String email,String fulName) {
        super(pass, userNam);
        this.address = address;
        this.email = email;
        this.fulName = fulName;
        this.number = number;
        
    }

    

//    @Override
//    public void createCase(String caseTitle, int caseID, double caseBudget, String deadline, String component, boolean evaluated, String freeText) {
//        Case SendCase = new Case(caseTitle, caseID, caseBudget, deadline, component, evaluated, freeText);
//
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFulName() {
        return fulName;
    }

    public void setFulName(String fulName) {
        this.fulName = fulName;
    }

    @Override
    public String toString() {
        return "Customer{" +super.toString()+ "address=" + address + ", number=" + number + ", email=" + email + ", fulName=" + fulName + '}';
    }
    
    
}
