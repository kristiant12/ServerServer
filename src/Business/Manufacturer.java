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
public class Manufacturer extends User implements Serializable{
    
    private String firmaddress;
    private int number;
    private String firmaName;
    private String firmaMail;
    
    
    
    public Manufacturer(String pass, String userNam, String firmaddress,int number,String firmaName,String firmaMail) {
        super(pass, userNam);
        this.firmaMail = firmaMail;
        this.firmaName = firmaName;
        this.firmaddress = firmaddress;
        this.number = number;
        
    }

    public String getFirmaddress() {
        return firmaddress;
    }

    public void setFirmaddress(String firmaddress) {
        this.firmaddress = firmaddress;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFirmaName() {
        return firmaName;
    }

    public void setFirmaName(String firmaName) {
        this.firmaName = firmaName;
    }

    public String getFirmaMail() {
        return firmaMail;
    }

    public void setFirmaMail(String firmaMail) {
        this.firmaMail = firmaMail;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +super.toString()+ '}';
    }
    
    
    
    
}
