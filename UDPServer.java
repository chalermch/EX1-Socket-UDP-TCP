import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class UDPServer {
  public static void main(String args[]) throws Exception {

    DatagramSocket serverSocket = new DatagramSocket(9876);
    byte[] receiveData = new byte[1024];
    byte[] sendData = new byte[1024];
    while (true) {
      System.out.println("The server is waiting ");
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      serverSocket.receive(receivePacket);
      String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
      InetAddress IPAddress = receivePacket.getAddress();
      int port = receivePacket.getPort();
      sendData = date.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
      serverSocket.send(sendPacket);
    }
  }
}