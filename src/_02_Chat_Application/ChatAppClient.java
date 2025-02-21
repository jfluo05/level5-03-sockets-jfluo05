package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ChatAppClient {
	private String ip;
	private int port;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;
	 ChatApp capp;

	public ChatAppClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void start(ChatApp capp){
		this.capp=capp;

		try {

			connection = new Socket(ip, port);

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (connection.isConnected()) {
			try {
			//	JOptionPane.showMessageDialog(null, is.readObject());
				String robj= (String) is.readObject();
				System.out.println(robj);
				capp.finalMessage= this.capp.jta2.getText()+"\n"+robj;
				this.capp.jta2.setText(capp.finalMessage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendClick(String text) {
		try {
			if (os != null) {
				//System.out.println("SEND CLICK METHOD");
				os.writeObject(text);
				os.flush();
				this.capp.jta2.setText(text);
			//	this.capp.jta1.setText(" Client: "+ text);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
