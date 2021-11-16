package _02_Chat_Application;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame{
	 JPanel panel= new JPanel();
	 JLabel label1= new JLabel();
	 JLabel label2= new JLabel();
	 JTextField tf1= new JTextField();
	 JTextField tf2= new JTextField();
	 JButton button= new JButton("SEND");
	 Server server;
	 Client client;
	 static String message="";
	 static String currentText="";

	
public static void main(String[] args) {
	new ChatApp();
}
ChatApp () {
	int response= JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);

	if(response == JOptionPane.YES_OPTION) {
		server= new Server(8080);
		JOptionPane.showMessageDialog(null, "Server started at: "+server.getIPAddress()+"\nPort: "+ server.getPort());
		panel.add(tf1);
		panel.add(button);
		add(panel);
		button.addActionListener((e)->{
			currentText=tf1.getText();
			message=" Server: "+currentText;
			tf1.setText("");
			server.sendMessage();
			});
		setVisible(true);
		setSize(400,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		server.start();
	}else {
		setTitle("Client");
		String ipStr= JOptionPane.showInputDialog("Enter the IP Address");
		String prtStr= JOptionPane.showInputDialog("Enter the port number");
		int port= Integer.parseInt(prtStr);
		client=new Client(ipStr, port);
		panel.add(tf2);
		panel.add(button);
		add(panel);
		button.addActionListener((e)->{
			tf2.setText("");
			client.sendMessage();
			System.out.println();
		});
	}
	
	
	
}}
