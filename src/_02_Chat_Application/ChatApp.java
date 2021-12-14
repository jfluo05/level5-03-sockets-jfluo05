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
import javax.swing.JTextArea;
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
	 JTextField tf1= new JTextField(40);
	 JTextField tf2= new JTextField(40);
	 String finalMessage= "";
	 JTextArea jta1= new JTextArea(3,35);
	 JTextArea jta2= new JTextArea(3,35);
	 JButton button= new JButton("SEND");
	 ChatAppServer server;
	 ChatAppClient client;
	 static String message="";
	 static String currentText="";

	
public static void main(String[] args) {
	new ChatApp();
	
}
ChatApp () {
	int response= JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);

	if(response == JOptionPane.YES_OPTION) {
		server= new ChatAppServer(8080);
		JOptionPane.showMessageDialog(null, "Server started at: "+server.getIPAddress()+"\nPort: "+ server.getPort());
		panel.add(tf1);
		panel.add(jta1);
		panel.add(button);
		add(panel);
		button.addActionListener((e)->{
			currentText=tf1.getText();
			//message=" Server: "+currentText;
			server.sendClick(tf1.getText());
			tf1.setText("");

			});
		setVisible(true);
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Server");
		server.start(this);

	}else {
		setVisible(true);
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Client");
		String ipStr= "54.176.69.157";//JOptionPane.showInputDialog("Enter the IP Address");
		String prtStr= "8080";//JOptionPane.showInputDialog("Enter the port number");
		int port= Integer.parseInt(prtStr);
		client=new ChatAppClient(ipStr, port);

		panel.add(tf2);
		panel.add(jta2);
		panel.add(button);
		add(panel);
		button.addActionListener((e)->{
			finalMessage=jta2.getText()+tf2.getText();//ADDED
			client.sendClick(finalMessage);
			tf2.setText("");
		});
		client.start(this);
	}
	
	
	
}}
