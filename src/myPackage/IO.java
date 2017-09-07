/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class IO {
   private String file_name;
   private File file;

    public IO(String file_name) {
        this.file_name = file_name;
        file = new File(file_name); 
    }
   
   public boolean check_exist(){
       return file.exists();
   }
    
   public void ghi_ra_file(Vector<Account> vacc){
       try {
           ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
           oos.writeObject(vacc);
           oos.flush();
           oos.close();
       } catch (IOException ex) {
           
       }
   }
   
   
   public Vector<Account> doc_tu_file(){
       try {
           ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
           Vector<Account> v = (Vector<Account>)ois.readObject();
           ois.close();
           return v;
       } catch (IOException ex) {
           ex.printStackTrace();
       } catch (ClassNotFoundException ex) {
          ex.printStackTrace();
       }
       return null;
   }
   
}



