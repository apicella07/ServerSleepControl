/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author gabri
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerConnection {
    
    //atributes of the class
    //private int portNumber;
    //private ServerSocket serversocket;
    private ArrayList<Patient>  patients;
    private ArrayList<Users> users;
    
    //para cuando hayan varios clientes habra que hacer este código 
    //private ArrayList<Socket> clients;
    
    public static void main(String[] args) throws ClassNotFoundException {
        InputStream is = null;
        ObjectInputStream ois = null;
        ServerSocket serversocket = null;
        Socket socket = null;
        
        try{
            serversocket = new ServerSocket(9000); //podría poner socket.getPort();
            socket = serversocket.accept();
            is = socket.getInputStream();
            System.out.println("The connection established from the address" + socket.getInetAddress()); 
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        try{
            ois = new ObjectInputStream(is);
            Object newpat;
            while((newpat= ois.readObject())!= null){
                Patient patientconnected = (Patient) newpat;
                System.out.println(patientconnected.toString());  
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        finally{
            releaseResources(ois,serversocket,socket);
        }
        
    }   
        
        private static void releaseResources(ObjectInputStream ois, ServerSocket serversocket, Socket socket){
            
            try{
                ois.close();
            }catch(IOException ex){
                Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try{
                serversocket.close();
            }catch(IOException ex){
                Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try{
                socket.close();
            }catch(IOException ex){
                Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    
    
    
    

