package it.tozzi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ServerThread {
    private static int id_counter = 0;
    private int thread_id;
    private Socket socket;
    ArrayList<String> clientNotes = new ArrayList<String>();


    public ServerThread(Socket socket) {
        this.socket = socket;
        this.thread_id = id_counter;
        id_counter++;
        System.out.println("Thread creato: " + thread_id);
    }

    public void run() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());

            int count = 0;
            String result = "";

            Random random = new Random();
            int number = random.nextInt(100);

            do{
                String s = in.readLine();
                count ++;
                int attempt = Integer.parseInt(s);

                if(attempt < number){

                    result = "TOO_LOW";

                } else if (attempt > number){

                    result = "TOO_HIGH";

                } else if (attempt == number){

                    result = "CORRECT";
                    System.out.println("Ci sono voluti " + count + "tentativi");

                }

                out.writeBytes(result + "\n");

            } while (result != "CORRECT");

            
            System.out.println("TERMINE COMUNCAZIONE");

        } catch (IOException e) {
            System.out.println("ERRORE NELLA COMUNICAZIONE");
        }
    
    }

    public void start() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}
