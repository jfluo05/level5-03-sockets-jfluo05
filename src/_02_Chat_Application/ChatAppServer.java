package _02_Chat_Application;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ChatAppServer {
	ChatApp capp;
	private int port;

	private ServerSocket server;
	private Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public ChatAppServer(int port) {
		this.port = port;
	}

	public void start(ChatApp capp){
	this.capp=capp;
		try {
			server = new ServerSocket(port, 100);

			connection = server.accept();

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			while (connection.isConnected()) {
				try {
					String robj= (String) is.readObject();
					System.out.println(robj);
					capp.finalMessage= this.capp.jta1.getText()+"\n"+robj;
					this.capp.jta1.setText(capp.finalMessage);
					//JOptionPane.showMessageDialog(null, is.readObject());
				}catch(EOFException e) {
					JOptionPane.showMessageDialog(null, "Connection Lost");
					System.exit(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}

	public int getPort() {
		return port;
	}

	public void sendClick(String text2) {
		try {
			if (os != null) {
				os.writeObject(text2);
				os.flush();
				this.capp.jta1.setText(text2);
			//	this.capp.jta2.setText(" Server: "+ text2);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
