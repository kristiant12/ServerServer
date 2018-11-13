/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author nicol
 */
public class test {
    
    
    test(){
    try {  
    Socket server = new Socket("foo.bar.com", 1234);  
    InputStream in = server.getInputStream( );  
    OutputStream out = server.getOutputStream( );  
  
    // write a byte  
    out.write(42);  
  
    // write a newline or carriage return delimited string 
    PrintWriter pout = new PrintWriter( out, true );  
    pout.println("Hello!");  
  
    // read a byte  
    byte back = (byte)in.read( );  
  
    // read a newline or carriage return delimited string  
    BufferedReader bin = 
      new BufferedReader( new InputStreamReader( in ) ); 
    String response = bin.readLine( );  
  
    // send a serialized Java object 
    ObjectOutputStream oout = new ObjectOutputStream( out ); 
    oout.writeObject( new java.util.Date( ) ); 
    oout.flush( ); 
 
    server.close( );  
}   
catch (IOException e ) { 
        }
    }
}
