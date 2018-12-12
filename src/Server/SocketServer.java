/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Business.Case;
import Business.Customer;
import Business.EnccryptionDecryption;
import Business.HomeMadeMap;
import Business.Manufacturer;
import Business.Ticket;
import Business.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SealedObject;

public class SocketServer extends Thread {

    public static final int PORT_NUMBER = 8081;
    private Database db;
    protected Socket socket;
    private ObjectInputStream oin;
    private ObjectOutputStream mapObjectOutputStream;
    private Map<User,Case> mapOfUserAndCase;
    private EnccryptionDecryption encryp;
    private SocketServer(Socket socket, Database db) {
        this.db = db;
        this.socket = socket;
        System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
        start();
    }
    
    public void run() {
        OutputStream out = null;
        encryp = new EnccryptionDecryption();
        InputStream in = null;
        mapObjectOutputStream = null;
        oin = null;
        try {
            //denne sender til clieneten
            out = socket.getOutputStream();
            mapObjectOutputStream = new ObjectOutputStream(out);
            oin = new ObjectInputStream(socket.getInputStream());
            in = socket.getInputStream();
            Scanner inp = new Scanner(in);
            String request;
            
            while ((request = inp.nextLine()) != null) {
                

                if(request.equals("1")){
                    sendListOfUseres();
                }else if(request.equals("2")){
                    sendListOfCases();
                }else if(request.equals("3")){
                    getUser();
                }else if(request.equals("4")){
                    getCase();
                }else if(request.equals("5")){
                    DeleteCase();
                }
//                else if(request.equals("6")){
//                    createCaseToPerson();
//                }
                else if(request.equals("7")){
                    getMapOfUserAndCase();
                }
                
                else if(request.equals("10")){
                    sendCaseFromAUser();
                }
                else if(request.equals("11")){
                    edidtCase();
                }
                else if(request.equals("12")){
                   getAllNotEvaluatetCases();
                }
                
                
                
                else if(request.equals("13")){
                    EvaluateCase();
                }
                else if(request.equals("14")){
                    getAllTicketsforEmployee();
                }
                
                
                else if(request.equals("15")){
                    getAllEvaluatetCases();
                    
                }
                else if(request.equals("16")){
                    deleteUser();
                }
                else if(request.equals("17")){
                    createTicket();
                }
                else if(request.equals("18")){
                    sendAllCustumerTickets();
                }
                else if(request.equals("19")){
                    updateManufactor();
                }
                else if(request.equals("20")){
                    employeeReplyTicket();
                }
                else if(request.equals("21")){
                   updateBid();
                }
                
                else if(request.equals("22")){
                    getAllStufInauction();
                    
                }else if(request.equals("30")){
                    sendkey();
                }
                    
                 
                
            
                

                
                
                System.out.println("Message received:" + request);
                
            }
            
        } catch (IOException ex) {
            System.out.println("Unable to get streams from client");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
                System.out.println("den er slukket");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    public void sendkey() throws IOException{
        mapObjectOutputStream.writeObject(encryp.getKey());
    }
    
    public void sendListOfUseres () throws IOException, IllegalBlockSizeException{
        List<User> test = new ArrayList();
        test.addAll(db.getUser());
        List<SealedObject> test2 = encryp.encryptUserList(test);
        mapObjectOutputStream.writeObject(test2);
        mapObjectOutputStream.flush();
    }
    
    public void sendListOfCases() throws IOException, IllegalBlockSizeException{
        List<Case> test = new ArrayList();
        test.addAll(db.getCases());
        List<SealedObject> test2 = encryp.encryptCaseList(test);
        mapObjectOutputStream.writeObject(test2);
        mapObjectOutputStream.flush();
       // listCase.clear();

        
//        mapObjectOutputStream.writeObject(listCase);
//        mapObjectOutputStream.flush();
//        listCase.clear();


    }
    
    public void sendCaseFromAUser() throws IOException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException{
        SealedObject a = (SealedObject) oin.readObject();
        Customer b = encryp.deccryptCustumer(a);
        List<Case> test = new ArrayList();
        test.addAll(db.getSpecificUserCaseList(b));
        List<SealedObject> test2 = encryp.encryptCaseList(test);
        mapObjectOutputStream.writeObject(test2);
        mapObjectOutputStream.flush();
//        Customer a = (Customer) oin.readObject();
//        List<Case> test = new ArrayList();
//        
//        test.addAll(db.getSpecificUserCaseList(a));
//        mapObjectOutputStream.writeObject(test);
//        mapObjectOutputStream.flush();
        
//        listToSpecifikUserOfCases.addAll(db.getSpecificUserCaseList((Customer) a));
//        mapObjectOutputStream.writeObject(listToSpecifikUserOfCases);
        
    }
    
    
    
    public void getUser() throws IOException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException{
        
        SealedObject a = (SealedObject) oin.readObject();
        
        User b = encryp.decryptUser(a);
        db.createUser(b);
        System.out.println(b.toString());
        
//        User a = (User) oin.readObject();
//        db.createUser(a);
//        System.out.println(a.toString());
        
        }
    
   // skal mulig vis slettes da den ikke rigtig gøre noget 
    public void getCase() throws IOException, ClassNotFoundException{
        SealedObject a = (SealedObject) oin.readObject();
        
        
//        Case a = (Case) oin.readObject();
//        
//        System.out.println(a.toString());
        
    }
       public void DeleteCase() throws IOException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException{
            SealedObject a = (SealedObject) oin.readObject();
            Case b = encryp.decryptCase(a);
            db.deleteCaseInCaseAndCreates(b);
//        Case a = (Case) oin.readObject();
//        db.deleteCaseInCaseAndCreates(a);
       // System.out.println(a.toString());
    }

       public void getMapOfUserAndCase() throws IOException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException{
          SealedObject ting = (SealedObject) oin.readObject();
          HomeMadeMap m = encryp.decryptHomeMadeMap(ting);
          HashMap<User,Case> a = new HashMap();
          a.put(m.getUser(), m.getCase());
          User us = null;
          Case ca = null;
          for(User test : a.keySet()){
              us = test;
              System.out.println(us.toString());
          }
        for (Case dd : a.values()) {
            ca = dd;
            System.out.println(ca.toString());

        }
          db.doStuff((Customer) us, ca);
       }
//        HashMap<User,Case> a = (HashMap<User,Case>) oin.readObject();
//          User us = null;
//          Case ca = null;
//          for(User test : a.keySet()){
//              us = test;
//              System.out.println(us.toString());
//          }
//        for (Case dd : a.values()) {
//            ca = dd;
//            System.out.println(ca.toString());
//
//        }
//          db.doStuff((Customer) us, ca);
//       }
       
       public void edidtCase() throws IOException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException{
          SealedObject d = (SealedObject) oin.readObject();
          Case a = encryp.decryptCase(d);
            // Case a = (Case) oin.readObject();
           db.editCase(a);
       }
       
       
       public void getAllNotEvaluatetCases() throws IOException, IllegalBlockSizeException{
           List<Case> notEval = db.getNotEvaluadedCase();
           List<SealedObject> ting = encryp.encryptCaseList(notEval);
           mapObjectOutputStream.writeObject(ting);
       }  
       
       public void EvaluateCase() throws IOException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException{
           SealedObject ting = (SealedObject) oin.readObject();
           Case a = encryp.decryptCase(ting);
        // Case a = (Case) oin.readObject();
           db.evaluateCaseAndAddToAuction(a);
       }
       
       public void getAllEvaluatetCases() throws IOException, ClassNotFoundException, IllegalBlockSizeException{
           List<Case> eval = db.getEvaluetaCase();
           List<SealedObject> ting = encryp.encryptCaseList(eval);
           mapObjectOutputStream.writeObject(ting);
       } 
       
       public void deleteUser() throws IOException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException{
           SealedObject ting = (SealedObject) oin.readObject();
           User a = encryp.decryptUser(ting);
           db.deleteUser(a);
       }
       
       public void createTicket() throws IOException, ClassNotFoundException, BadPaddingException, IllegalBlockSizeException{
            SealedObject ting = (SealedObject) oin.readObject();
            HomeMadeMap noget = encryp.decryptHomeMadeMap(ting);
            HashMap<Customer,Ticket> crTicket = new HashMap();
            crTicket.put(noget.getCustomer(), noget.getTicket());
            Customer us = null;
            Ticket ca = null;
          for(Customer test : crTicket.keySet()){
              us = test;
              System.out.println(us.toString());
          }
         for (Ticket dd : crTicket.values()) {
            ca = dd;
            System.out.println(ca.toString());

        }
             db.CustumerCreateTicket(ca, us); 
//            HashMap<Customer,Ticket> crTicket = (HashMap<Customer,Ticket>) oin.readObject();
//            Customer us = null;
//            Ticket ca = null;
//          for(Customer test : crTicket.keySet()){
//              us = test;
//              System.out.println(us.toString());
//          }
//         for (Ticket dd : crTicket.values()) {
//            ca = dd;
//            System.out.println(ca.toString());
//
//        }
//          db.CustumerCreateTicket(ca, us);  
       }
       
      public void sendAllCustumerTickets() throws IOException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException{
          SealedObject ting = (SealedObject) oin.readObject();
          Customer cu = encryp.deccryptCustumer(ting);
//          Customer cu = (Customer) oin.readObject();
           List<Ticket> ti = db.getAlleTickets(cu);
           List<SealedObject> to = encryp.encryptTicketList(ti);
          mapObjectOutputStream.writeObject(to);
      } 
      
       
      public void updateManufactor() throws IOException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException{
          SealedObject ting = (SealedObject) oin.readObject();
          Manufacturer m = encryp.decryptManufacturer(ting);
          db.editManufatur(m);
      }
       
      public void getAllTicketsforEmployee() throws IOException, IllegalBlockSizeException{
          
          List<Ticket> a = db.getAllTicketsEmployee();
          List<SealedObject> ting = encryp.encryptTicketList(a);
          mapObjectOutputStream.writeObject(ting);
          
          
      }
      
      
      public void employeeReplyTicket() throws IOException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException{
          SealedObject ting = (SealedObject) oin.readObject();
//          Ticket t = (Ticket) oin.readObject();
           Ticket t  = encryp.decryptTicket(ting);
          db.employeeReplyTicket(t);
      }
   
       
      public void getAllStufInauction() throws IOException, IllegalBlockSizeException{
          List<SealedObject>ting = encryp.encryptCaseList(db.getAllCasesInAction());
          mapObjectOutputStream.writeObject(ting);
          
      }
      
      
      public void updateBid() throws IOException, ClassNotFoundException{
          Case s = (Case)oin.readObject();
          db.updateBid(s);
      }
    
//    public void createCaseToPerson()throws IOException, ClassNotFoundException{
//        User a = (User) oin.readObject();
//        Case b = (Case) oin.readObject();
//        
//        
//        db.doStuff((Customer) a, b);
//        
//        
//    }
//       
       
    
    
    
    public static void main(String[] args) {
        System.out.println("SocketServer Example");
        Database db = new Database();
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT_NUMBER);
            while (true) {
                /**
                 * create a new {@link SocketServer} object for each connection
                 * this will allow multiple client connections
                 */
                new SocketServer(server.accept(), db);
            }
        } catch (IOException ex) {
            System.out.println("Unable to start server.");
        } finally {
            try {
                if (server != null) {
                    server.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
