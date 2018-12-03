package Business;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

public class EnccryptionDecryption {

    private static Cipher ecipher;
    private static Cipher dcipher;
    private static SecretKey key;

    public static void main(String[] args) {

        try {

            // generate secret key using DES algorithm
            key = KeyGenerator.getInstance("DES").generateKey();

            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");

            // initialize the ciphers with the given key 
            ecipher.init(Cipher.ENCRYPT_MODE, key);

            dcipher.init(Cipher.DECRYPT_MODE, key);

            // create a sealed object
            SealedObject sealed = new SealedObject((new Case(null, null, null, null, null, true, null)), ecipher);

            // get the algorithm with the object has been sealed
            String algorithm = sealed.getAlgorithm();

            System.out.println("Algorithm " + algorithm);

            // unseal (decrypt) the object
            Case o = (Case) sealed.getObject(dcipher);
            
            System.out.println("Original Object: " + o);
            
            System.out.println(key);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm:" + e.getMessage());
            return;
        } catch (NoSuchPaddingException e) {
            System.out.println("No Such Padding:" + e.getMessage());
            return;
        } catch (BadPaddingException e) {
            System.out.println("Bad Padding:" + e.getMessage());
            return;
        } catch (InvalidKeyException e) {
            System.out.println("Invalid Key:" + e.getMessage());
            return;
        } catch (IllegalBlockSizeException e) {
            System.out.println("Illegal Block:" + e.getMessage());
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found:" + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("I/O Error:" + e.getMessage());
            return;
        }

    }

    public static class SecretObject implements Serializable {

        private static final long serialVersionUID = -1335351770906357695L;

        private final String message;

        public SecretObject(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "SecretObject [message=" + message + "]";
        }

    }

}