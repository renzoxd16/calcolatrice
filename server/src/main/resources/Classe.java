package com.example;
class Classe implements Runnable {
    private final Socket s;

    public ClientHandler(Socket socket) {
        this.s = socket;
    }

    @Override
    public void run() {
        
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true)
        ) {
            double numero1;
            double numero2;
            String operazione;
            double risultato;

            while (true) {
                String temp1 = in.readLine();
                String temp2 = in.readLine();
                operazione = in.readLine();

               
                if (temp1 == null || temp2 == null || operazione == null) {
                    break;
                }

                if (operazione.equalsIgnoreCase("exit")) {
                    out.println("programma terminato");
                    break; 
                }

                numero1 = Double.parseDouble(temp1);
                numero2 = Double.parseDouble(temp2);

                if (numero2 == 0 && (operazione.equals("divisione") || operazione.equals("/"))) {
                    out.println("Errore: divisione per zero");
                } else if (operazione.equals("addizione") || operazione.equals("+")) {
                    risultato = numero1 + numero2;
                    out.println(risultato);
                } else if (operazione.equals("sottrazione") || operazione.equals("-")) {
                    risultato = numero1 - numero2;  
                    out.println(risultato);             
                } else if (operazione.equals("moltiplicazione") || operazione.equals("*")) {
                    risultato = numero1 * numero2;
                    out.println(risultato);
                } else if (operazione.equals("divisione") || operazione.equals("/")) {
                    risultato = numero1 / numero2;
                    out.println(risultato);
                } else {
                    out.println("Operazione non riconosciuta");
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Connessione interrotta o dati non validi per il client: " + s.getRemoteSocketAddress());
        } finally {
            try {
                s.close();
                System.out.println("Connessione chiusa con il client.");
            } catch (IOException e) {
                System.err.println("Errore nella chiusura del socket: " + e.getMessage());
            }
        }
    }
}


