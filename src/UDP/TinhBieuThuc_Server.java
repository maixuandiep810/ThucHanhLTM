package UDP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import TCP.BtBaLanUtil;


public class TinhBieuThuc_Server {
	
	private final static int PORT = 2019;

	public static void main(String[] args) {		
		try {		
			DatagramSocket serverSocket = new DatagramSocket(PORT);
			System.out.println("SERVER IS STARTED");		
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			
			while(true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				
				String receiveString = new String(receivePacket.getData()).trim();
				String[] stringArray = new String[3];			
				String infix = BtBaLanUtil.FormatExpresion(receiveString);
				String postfix = BtBaLanUtil.InfixToPostfix(infix);
				float evaluate = BtBaLanUtil.EvaluatePostfix(postfix);
				stringArray[0] = "Bieu thuc: " + receiveString;
				stringArray[1] = "Bieu thuc Ba Lan dao nguoc RPN: " + postfix;
				stringArray[2] = "Ket qua: " + infix + " = " + evaluate;
				
				for (int i = 0; i < stringArray.length; i++) {
					System.out.println(stringArray[i]);
					sendData = new byte[1024];
					sendData = stringArray[i].getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					serverSocket.send(sendPacket);
				}
			}			
		} catch (Exception e) {
			System.out.println("Xay ra loi: " + e.getMessage());
		}
	}
}
