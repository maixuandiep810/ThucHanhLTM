package UDP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class XuLyChuoi_Client {

	private final static int PORT = 2019;
	
	public static void main(String[] args) {	
		try {	
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName("localhost");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
			byte[] receiveData;
			byte[] sendData;
			
			System.out.print("sendString: ");
			String sendString = br.readLine();
			sendData = new byte[1024];
			sendData = sendString.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, PORT);
			clientSocket.send(sendPacket);
			
			for (int i = 0; i < 3; i++) {
				receiveData = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				clientSocket.receive(receivePacket);
				String receiveString = new String(receivePacket.getData());		
				System.out.println(receiveString);
			}	
			clientSocket.close();			
		} catch (Exception e) {
			System.out.println("Xay ra loi: " + e.getMessage());
		}
	}

}
