package TCP;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TinhBieuThuc_Client {

	public final static String HOST = "localhost";
	public final static int PORT = 2019;
	
	public static void main(String[] args) {
		
		Socket socket = null;	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {		
			socket = new Socket(HOST, PORT);

			DataInputStream din = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			System.out.print("ClientInput: ");
			String clientInput = br.readLine();
			dos.writeUTF(clientInput);
			System.out.println(din.readUTF());
			System.out.println(din.readUTF());
			System.out.println(din.readUTF());
			
		} catch (IOException e) {
			System.out.println("Khong xac dinh Server");
		}
		
	}

}
