package MultiPlay;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MultiServer {
	private HashMap<String, DataOutputStream> clients;
	private ServerSocket serverSocket;

	MultiServer() {
		clients = new HashMap<String, DataOutputStream>();
		Collections.synchronizedMap(clients); // ø©∑Ø Ω∫∑πµÂø°º≠ ¡¢±Ÿ
	}

	public void start() {
		try {
			Socket socket;
			serverSocket = new ServerSocket(1234);
			System.out.println("Server Works");

			while (true) {
				socket = serverSocket.accept();
				ServerReceiver receiver = new ServerReceiver(socket);
				receiver.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class ServerReceiver extends Thread {
		private Socket socket;
		private DataInputStream input;
		private DataOutputStream output;

		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				input = new DataInputStream(socket.getInputStream());
				output = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
        public void run() {
			String name = "";
            try {
            	name = input.readUTF();
            	sendToAll("#" + name + "¥‘¿Ã ¡¢º”«œºÃΩ¿¥œ¥Ÿ.");
            	
            	clients.put(name, output);
            	System.out.println(name + "¥‘¿Ã ¡¢º”«œºÃΩ¿¥œ¥Ÿ.");
            	
                while (input != null) {
                    sendToAll(input.readUTF());
                }
            } catch (IOException e) {
            } finally {
            	clients.remove(name);
                sendToAll("#" + name + "¥‘¿Ã ¡¢º”¿ª ≤˜¿∏ºÃΩ¿¥œ¥Ÿ..");
                System.out.println("#" + name + "¥‘¿Ã ¡¢º”¿ª ≤˜¿∏ºÃΩ¿¥œ¥Ÿ..");
            }
        }
		
		public void sendToAll(String message) {
            Iterator<String> it = clients.keySet().iterator();
 
            while (it.hasNext()) {
                try {
                    DataOutputStream dos = clients.get(it.next());
                    dos.writeUTF(message);
                } catch (Exception e) {
                }
            }
        }
	}
}
