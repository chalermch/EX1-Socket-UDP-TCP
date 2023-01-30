
//EchoThread.java
import java.io.*;
import java.net.*;
import java.util.*;

public class EchoThread extends Thread {
   private Socket connectionSocket;

   public EchoThread(Socket connectionSocket) {
      this.connectionSocket = connectionSocket;
   }

   public void run() {
      Scanner inFromClient = null;
      DataOutputStream outToClient = null;
      try {
         inFromClient = new Scanner(connectionSocket.getInputStream());
         outToClient = new DataOutputStream(connectionSocket.getOutputStream());
         while(true){
            String clientSentence = inFromClient.nextLine();
            String[] arrNumber = clientSentence.split(",",0);
            int num1 = Integer.parseInt(arrNumber[0]);
            int num2 = Integer.parseInt(arrNumber[1]);
            int sum = num1 + num2;
            String capitalizedSentence = String.valueOf(sum) + '\n';
            outToClient.writeBytes(capitalizedSentence);
            outToClient.flush();
         }
      } catch (IOException e) {
         System.err.println("Closing Socket connection");
      } finally {
         try {
            if (inFromClient != null)
               inFromClient.close();
            if (outToClient != null)
               outToClient.close();
            if (connectionSocket != null)
               connectionSocket.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
}
