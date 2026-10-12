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
    
      
        double numero1;
        double numero2;
        String operazione = " ";
        double risultato = 0.0;
        
        
             while (true) {

                String temp1=in.readLine();
                String temp2=in.readLine();
                operazione = in.readLine();

                

            
                numero1 = Double.parseDouble(temp1);
                numero2 = Double.parseDouble(temp2);
                
                if(operazione.toLowerCase().equals("exit")){
                    out.println("programma terminato");
                }else if( numero2 == 0 && (operazione.equals("divisione") || operazione.equals("/"))){
                    throw new Exception("Errore divisione per zero");
                }else if(operazione.equals("addizione")|| operazione.equals("+")){
                    risultato=numero1+numero2;
                    out.println(risultato);
                }else if(operazione.equals("sottrazione") || operazione.equals("-")){
                    risultato=numero1-numero2;  
                    out.println(risultato);             
                }else if(operazione.equals("moltiplicazione" ) || operazione.equals("*")){
                    risultato=numero1*numero2;
                    out.println(risultato);
                }else if(operazione.equals("divisione") || operazione.equals("/")){
                    risultato=numero1/numero2;
                    out.println(risultato);
                }



            }
             
             
    }
       
}
