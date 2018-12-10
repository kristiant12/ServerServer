/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.Serializable;

/**
 *
 * @author nicol
 */
public class HomeMadeMap implements Serializable{
    static final long serialVersionUID = -7588980448693010399L;

    private User user;
    private Case ting;
    private Customer customer;
    private Ticket ticket;
    
    public HomeMadeMap(User user,Case ting){
        this.ting = ting;
        this.user = user; 
    }
    public HomeMadeMap(Customer customer,Ticket ticket){
        this.customer = customer;
        this.ticket = ticket;
        
    }
    
    public User getUser(){
        return this.user;
    }
    
    public Case getCase(){
        return this.ting;
    }
    
    public Customer getCustomer(){
        return this.customer;
    }
    public Ticket getTicket(){
        return this.ticket;
    }
    
    
}
