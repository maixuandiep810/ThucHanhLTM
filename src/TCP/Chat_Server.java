package TCP;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Chat_Server {

	private final static int PORT = 2019;

	public static void main(String[] args) throws IOException {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(PORT);
			System.out.println("Server is started");
			System.out.println("Waiting...");
			Socket socket = ss.accept();
			System.out.println("Accept a client...");
			DataInputStream din = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			dos.writeUTF("Hello");
			while (true) {
				System.out.println("Client: "+ din.readUTF());	
				while(br.ready()) {
					br.readLine();
				}
				System.out.print(">");
				String serverMess = br.readLine();
				dos.writeUTF(serverMess);
			}
		} catch (IOException e) {
			System.out.println("Xay ra loi: " + e.getMessage());
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
	}
}
