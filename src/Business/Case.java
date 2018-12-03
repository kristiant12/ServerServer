/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author nicol
 */
public class Case implements Serializable{
   static final long serialVersionUID = -7588980448693010399L;
    private String caseTitle;
    private String caseID;
    private String caseBudget;
    private String deadline;
    private String component;
    private boolean evaluated;
    private String freeText;
    private double bid;
    
    
    // skal tilføre billeder 
      public Case(String caseTitle, String caseID, String caseBudget, String deadline, String component, boolean evaluated, String freeText) {
        this.caseTitle = caseTitle;
        this.caseID = caseID;
        this.caseBudget = caseBudget;
        this.deadline = deadline;
        this.component = component;
        this.evaluated = evaluated;
        this.freeText = freeText;
    }
   
 
    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }
    

    
    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String i) {
        this.caseTitle = i;
    }

    public String getCaseBudget() {
        return caseBudget;
    }

 
    public void setCaseBudget(String i) {
        this.caseBudget = i;
    }

 
    public String getDeadline() {
        return deadline;
    }

   
    public void setDeadLine(String i) {
        this.deadline = i;
    }


    public String getComponent() {
        return component;
    }

    public void setComponent(String i) {
       this.component = i;
    }


    public boolean getEvaluated() {
        return evaluated;
    }


    public void setEvaluated(boolean i) {
        this.evaluated = i;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String i) {
        freeText = i;
    }


    public boolean verifyPayment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setId(String i) {
        this.caseID = i;
        }

    public String getId() {
        return caseID;
    }

    @Override
    public String toString() {
           return  "caseTitle=" + caseTitle;
    }
    
    
    

}