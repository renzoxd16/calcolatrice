package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    /**
     * @param args
     * @return 
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {
        System.out.println("server");


        ServerSocket ss = new ServerSocket(3000);
        Socket s = ss.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
    
      
        int numero1;
        int numero2;
        float risultato = 0;
        String operazione;
        String temp1=in.readLine();
        String temp2=in.readLine();
    
        
             while (true) {

            
                numero1 = Integer.parseInt(temp1);
                numero2 = Integer.parseInt(temp2);
                operazione = in.readLine();

                 if( numero2 == 0 ||operazione=="divisione" && operazione=="/"){
                    throw new Exception("Errore divsione per zero");     
                }
    
                
                if(operazione.equalsIgnoreCase("exit")){

                    break;
                }
              
                if(operazione=="adizione"){
                    
                    risultato=numero1+numero2;
                
               }

              
               if(operazione=="sottrazione"){

                 risultato=numero1-numero2;
                    
                
               }

               if(operazione=="moltiplicazione"){

                risultato=numero1*numero2;

                
               }

               if(operazione=="divisione"){

                 risultato=numero1/numero2;
                
                   
                
               }
                System.out.println(risultato);

            }
             
             
    }
       

}
           

  


 
