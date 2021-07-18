package MultiPlay;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import SinglePlay.*;

public class MultiClient {
	private String Nickname;
	private Socket socket;
	private String ip = "localhost";

	private Login l;
	private mySQL my;

	@SuppressWarnings("static-access")
	public void start() {
		my = l.getMySQL();

		try {
			socket = new Socket(ip, 1234);
			System.out.println("Connected");

			ClientReceiver clientReceiver = new ClientReceiver(socket);
			ClientSender clientSender = new ClientSender(socket);

			clientReceiver.start();
			clientSender.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream input;

		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				input = new DataInputStream(socket.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while (input != null) {
				try {
					System.out.println(input.readUTF());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	class ClientSender extends Thread {
		Socket socket;
		DataOutputStream output;

		public ClientSender(Socket socket) {
			this.socket = socket;
			try {
				output = new DataOutputStream(socket.getOutputStream());
				output.writeUTF(my.getNickname(my.getStringID()));
				System.out.println("대화방에 입장하였습니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 @Override
	        public void run() {
	            String msg = "";
	 
	            while (output != null) {
	                try {
	                    
	                    if(msg.equals("exit"))
	                        System.exit(0);
	                     
	                   // output.writeUTF();
	                } catch (Exception e) {
	                	e.printStackTrace();
	                }
	            }
		 }
	}
}
