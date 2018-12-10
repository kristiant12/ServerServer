/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Business.Case;
import Business.EnccryptionDecryption;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SealedObject;



/**
 *
 * @author nicol
 */
public class testMian {
    public static void main(String[] args) throws IOException, IllegalBlockSizeException, ClassNotFoundException, BadPaddingException {
 
        EnccryptionDecryption a = new EnccryptionDecryption();
        
        
             List<Case> adds = new ArrayList();
        
       Case d = new Case("dd", "sss", "sdsds", "sasa", "ass", true, "sdds");
      Case e = new Case("dsssssd", "dddsss", "sdsds", "sasa", "ass", true, "sdds");
        Case f = new Case("dd", "ddsss", "ssssdsds", "sasa", "ass", true, "sdds");
       Case g = new Case("dd", "ssggss", "sdsds", "sassggsa", "asgsgss", true, "sddsgsgs");
       adds.add(d);
       adds.add(e);
       adds.add(f);
       adds.add(g);
       
       List<SealedObject> ss = new ArrayList();
               ss.addAll(a.encryptCaseList(adds));
       System.out.println(ss.toString());
       List<Case> ssd = a.decryptCaseList(ss);
        System.out.println(ssd.toString());
       
    
    }
    
}
