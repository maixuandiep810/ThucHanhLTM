package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class XuLyChuoi_Server {
	
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
				dos.writeUTF("Chuoi goc: " + clientMess);
				dos.writeUTF("Chuoi in hoa: " + ChuoiHoa(clientMess));
				dos.writeUTF("Chuoi in thuong: " + ChuoiThuong(clientMess));
				dos.writeUTF("So tu cua chuoi: " + SoTuCuaChuoi(clientMess));
			}
		} catch (IOException e) {
			System.out.println("Xay ra loi: " + e.getMessage());
		} finally {
			if (ss != null) {
				ss.close();
			}
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
