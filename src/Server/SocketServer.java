/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Business.Case;
import Business.Customer;
import Business.User;
import Server.Database;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketServer extends Thread {

    public static final int PORT_NUMBER = 8081;
    private Database db;
    protected Socket socket;
//    private Map<String, String> map;
      private ObjectInputStream oin;
    private ObjectOutputStream mapObjectOutputStream;
    private List<User> listUser;
    private List<Case> listCase;
    private List<Case> listToSpecifikUserOfCases;
    private Map<User,Case> mapOfUserAndCase;
    private SocketServer(Socket socket, Database db) {
        this.db = db;
        this.socket = socket;
        System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
        start();
    }
    
    public void run() {
  //      map = new HashMap();
        OutputStream out = null;
        InputStream in = null;
        mapObjectOutputStream = null;
        oin = null;
        listCase = new ArrayList();
        listUser = new ArrayList<>();
         listToSpecifikUserOfCases = new ArrayList<>();
        try {
            //denne sender til clieneten
            out = socket.getOutputStream();
            mapObjectOutputStream = new ObjectOutputStream(out);
            oin = new ObjectInputStream(socket.getInputStream());

            //disse modter fra clieentet
            in = socket.getInputStream();
            Scanner inp = new Scanner(in);
           // PrintStream outp = new PrintStream(out);

            // BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String request;
            
            //sendListOfUseres();
//            sendListOfCases();
//            sendListOfUseres();
            
            
            //String test = null;
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
                else if(request.equals("15")){
                    getAllEvaluatetCases();
                    
                }else if(request.equals("16")){
                    deleteUser();
                }
                
            
                

                
                
                System.out.println("Message received:" + request);
                
            }
//                if(request.equals("1")){
//               sendListOfUseres();
//                }else if(request.equals("2")){
//                    sendListOfCases();
//                }
//            }
//             while ((request = inp.nextLine()) != null) {
//                System.out.println("Message received:" + request);
//                test.add(request);
//                //test.add(request+" 3");
//                
//              outp.println(test);
//
//            }
//         // detter virke så  
//            map.putAll(db.getUser());
//            mapObjectOutputStream.writeObject(map);
                
//            test = db.test();
//            mapObjectOutputStream.writeObject(test);
            
        } catch (IOException ex) {
            System.out.println("Unable to get streams from client");
        } catch (ClassNotFoundException ex) {
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
    
    public void sendListOfUseres () throws IOException{
      List<User> test = new ArrayList();
        test.addAll(db.getUser());
        mapObjectOutputStream.writeObject(test);
        mapObjectOutputStream.flush();
        //listUser.clear();

//        map.putAll(db.getUser());
//            mapObjectOutputStream.writeObject(map);
    }
    
    public void sendListOfCases() throws IOException{
        List<Case> test = new ArrayList();
        test.addAll(db.getCases());
        mapObjectOutputStream.writeObject(listCase);
        mapObjectOutputStream.flush();
        listCase.clear();


    }
    
    public void sendCaseFromAUser() throws IOException, ClassNotFoundException{
        Customer a = (Customer) oin.readObject();
        List<Case> test = new ArrayList();
        
        test.addAll(db.getSpecificUserCaseList(a));
        mapObjectOutputStream.writeObject(test);
        mapObjectOutputStream.flush();
        
//        listToSpecifikUserOfCases.addAll(db.getSpecificUserCaseList((Customer) a));
//        mapObjectOutputStream.writeObject(listToSpecifikUserOfCases);
        
    }
    
    
    
    public void getUser() throws IOException, ClassNotFoundException{
        User a = (User) oin.readObject();
        db.createUser(a);
        System.out.println(a.toString());
        
        }
    
    
    public void getCase() throws IOException, ClassNotFoundException{
        Case a = (Case) oin.readObject();
        
        System.out.println(a.toString());
        
    }
       public void DeleteCase() throws IOException, ClassNotFoundException{
        Case a = (Case) oin.readObject();
        db.deleteCaseInCaseAndCreates(a);
       // System.out.println(a.toString());
    }

       public void getMapOfUserAndCase() throws IOException, ClassNotFoundException{
          HashMap<User,Case> a = (HashMap<User,Case>) oin.readObject();
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
       
       public void edidtCase() throws IOException, ClassNotFoundException{
           Case a = (Case) oin.readObject();
           db.editCase(a);
           
           
           
       }
       
       public void getAllNotEvaluatetCases() throws IOException{
           List<Case> notEval = db.getNotEvaluadedCase();
           mapObjectOutputStream.writeObject(notEval);
           
           
           
       }  
       
       public void EvaluateCase() throws IOException, ClassNotFoundException{
           Case a = (Case) oin.readObject();
           db.EvaluateCase(a);
       }
       
       public void getAllEvaluatetCases() throws IOException, ClassNotFoundException{
           Case a = (Case) oin.readObject();
           List<Case> eval = db.getEvaluetaCase(a);
           mapObjectOutputStream.writeObject(eval);
       } 
       
       public void deleteUser() throws IOException, ClassNotFoundException{
           User a = (User) oin.readObject();
           db.deleteUser(a);
           
           
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
