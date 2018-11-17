/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;



/**
 *
 * @author rober
 */
public class Ticket{
    
    private String issueNumber;
    private String issueDescription;

    
    public Ticket(String issueDescript,String issueNumber){
        this.issueDescription = issueDescript;
        this.issueNumber = issueNumber;
    }

    public String getIssuenumber() {
      return issueNumber;
    }

    public void setIssuenumber(String issueNumber) {
       this.issueNumber = issueNumber;

    }

    public String getIssueDescription() {
         return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
         this.issueDescription = issueDescription;

    }
    public String toString(){
          return issueNumber + issueDescription;

    }
    
}

