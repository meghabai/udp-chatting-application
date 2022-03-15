import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class UDPServer {

 public static void main(String[] args) {
  byte[] receiveBytes = new byte[256];
  final int SERVER_PORT = 8080;
  System.out.println("Server started. Waiting for client message...");
  try (DatagramSocket ds = new DatagramSocket(SERVER_PORT); Scanner sc = new Scanner(System.in);) {
   while (!ds.isClosed()) {
    // Construct Datagram packet to receive message
    DatagramPacket dp = new DatagramPacket(receiveBytes, receiveBytes.length);
    ds.receive(dp);
    String dataString = new String(dp.getData(), "UTF-8");
    System.out.println("Client Says:" + dataString);
    String input = sc.nextLine();
    // Construct Datagram packet to send message
    DatagramPacket sendPacket = new DatagramPacket(input.getBytes(), input.getBytes().length,
      dp.getAddress(), dp.getPort());
    ds.send(sendPacket);
   }
   ds.close();
  } catch (Exception e) {
   e.printStackTrace();
  }
 }
}