import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        String[] sentence;
        String modifiedSentence;
        Scanner inFromUser = null;
        Socket clientSocket = null;
        DataOutputStream outToServer = null;
        Scanner inFromServer = null;
        try {
            inFromUser = new Scanner(System.in);
            clientSocket = new Socket("localhost", 1667);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new Scanner(clientSocket.getInputStream());
            int i = 0;
            int j = 0;
            int count = 0;
            sentence = new String[2];
            while(true){
                System.out.print("enter number " + (j+1) + " (to end just press enter): ");
                sentence[i] = inFromUser.nextLine();
                if(sentence[i].equals(""))break;
                j++;
                if(i == 1){
                    String concat = sentence[0] + "," + sentence[1];
                    outToServer.writeBytes(concat + '\n');
                    modifiedSentence = inFromServer.nextLine();
                    System.out.println("The result is " + modifiedSentence);
                    j = 0;
                    i = -1;
                }
                i++;
                
            }

        } catch (IOException e) {
            System.out.println("Error occurred: Closing the connection");
            e.printStackTrace();
        } finally {
            try {
                if (inFromServer != null)
                    inFromServer.close();
                if (outToServer != null)
                    outToServer.close();
                if (clientSocket != null)
                    clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}