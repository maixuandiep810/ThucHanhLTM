package UDP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Chat_Server {

	private final static int PORT = 2019;
	
	public static void main(String[] args) {		
		try {	
			DatagramSocket serverSocket = new DatagramSocket(PORT);
			System.out.println("SERVER IS STARTED");
			byte[] receiveData;
			byte[] sendData;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
			
			while(true) {	
				receiveData  = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);	
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				String receiveString = new String(receivePacket.getData()).trim();	
				System.out.println("Client: " + receiveString);
				
				while(br.ready()) {
					br.readLine();
				}
				System.out.print(">");
				String sendString = br.readLine();
				sendData = new byte[1024];
				sendData = sendString.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				serverSocket.send(sendPacket);
			}	
		} catch (Exception e) {
			System.out.println("Xay ra loi: " + e.getMessage());
		}
	}
	/*
	 * 
	 */
	public static String ChuoiHoa(String str) {	
		// neu RONG thi tra ve y cu
		if (str.length() == 0)
			return str;
		// COPY string -- mang char 
		char[] charStr = new char[str.length()];
		charStr = str.toCharArray();
		// VIET HOA - Chi can xet chu cai (khong xet dau, @,...)
		for (int i = 0; i < charStr.length; i++) {
			if (charStr[i] >= 'a' && charStr[i] <= 'z') {
				charStr[i] -= 32;
			}
		}
		return String.copyValueOf(charStr);
	}
	/*
	 * 
	 */
	public static String ChuoiThuong(String str) {
		// neu RONG thi tra ve y cu
		if (str.length() == 0)
			return str;
		//
		char[] charStr = new char[str.length()];
		charStr = str.toCharArray();
		// VIET THUONG - Chi can xet chu cai (khong xet dau, @,...)
		for (int i = 0; i < charStr.length; i++) {
			if (charStr[i] >= 'A' && charStr[i] <= 'Z') {
				charStr[i] += 32;
			}
		}
		return String.copyValueOf(charStr);
	}
	/*
	 * 
	 */
	public static int SoTuCuaChuoi(String str) {
		char[] charStr = new char[str.length()];
		charStr = str.toCharArray();
		int count = 0;
		for (int i = 0; i < charStr.length; i++) {
			while (charStr[i] == 32) {
				i++;
				if (i == charStr.length)
					break;
			}
			if (i < charStr.length) {
				while (charStr[i] != 32) {
					i++;
					if (i == charStr.length)
						break;
				}
				count++;
			}
		}
		return count;
	}

}

