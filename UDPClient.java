import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPClient {

 public static void main(String[] args) {
  byte[] receiveBytes = new byte[256];
  final String TERMINATE = "bye";
  final int SERVER_PORT = 8080;
  System.out.println("Client started. Type a message and hit Enter key to send the message to server.");
  try (Scanner sc = new Scanner(System.in); DatagramSocket ds = new DatagramSocket();) {
   // Get server address
   final InetAddress SERVER_ADDRESS = InetAddress.getLocalHost();
   DatagramPacket dataPacket = null;
   while (!ds.isClosed()) {
    String input = sc.nextLine();
    // Terminate the client if user says "bye"
    if (input.trim().equalsIgnoreCase(TERMINATE)) {
     break;
    }
    // Construct Datagram packet to send message
    dataPacket = new DatagramPacket(input.getBytes(), input.getBytes().length, SERVER_ADDRESS, SERVER_PORT);
    ds.send(dataPacket);
    // Construct Datagram packet to receive message
    dataPacket = new DatagramPacket(receiveBytes, receiveBytes.length);
    ds.receive(dataPacket);
    System.out.println("Server Says:" + new String(receiveBytes, "UTF-8"));
   }
  } catch (SocketException | UnknownHostException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
}
