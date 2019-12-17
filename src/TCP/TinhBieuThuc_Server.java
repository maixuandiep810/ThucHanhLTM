package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class TinhBieuThuc_Server {
	
	private final static int PORT = 2019;
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(PORT);
			System.out.println("Server is started");

			while (true) {				
				System.out.println("Waiting...");
				Socket socket = ss.accept();
				System.out.println("Accept a client...");
				// DataOutputStream la 1 loai luong RA
				DataInputStream din = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
									
				String clientMess = din.readUTF();
				String infix = BtBaLanUtil.FormatExpresion(clientMess);
				String postfix = BtBaLanUtil.InfixToPostfix(infix);
				float evaluate = BtBaLanUtil.EvaluatePostfix(postfix);
				dos.writeUTF("Bieu thuc: " + clientMess);
				dos.writeUTF("Bieu thuc Ba Lan dao nguoc RPN: " + postfix);
				dos.writeUTF("Ket qua: " + infix + " = " + evaluate);
			}
		} catch (Exception e) {
			System.out.println("Xay ra loi: " + e.getMessage());
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
	}
}
