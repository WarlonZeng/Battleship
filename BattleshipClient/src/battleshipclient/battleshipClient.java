package BattleshipClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.*;
import javax.swing.*;

public class battleshipClient extends JPanel {
	static String username;
	static String serverName;
	static Socket s;
	static PrintStream ps;
	static JFrame window = new JFrame("Battleship");
	
	static JPanel pnlNewGame = new JPanel(), 
			pnlSouth = new JPanel();
	static JLabel turn = new JLabel();
	static int turnCount = 0, numHits = 0;
	static boolean inGame = false;
	final int X = 500, Y = 450, color = 190;
	static JButton btnEmpty[] = new JButton[100];
	
	static char positions[];
	
	public battleshipClient() { // Setting game properties and layout and sytle...
		// Setting window properties:
		window.setSize(X, Y);
		window.setLocation(450, 260);
		window.setResizable(true);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Setting Panel layouts and properties
		pnlNewGame.setLayout(new GridLayout(10,10));
		
		for (int i = 0; i < 100; i++) {

			btnEmpty[i] = new JButton();
			btnEmpty[i].setSize(50,50);
			btnEmpty[i].setBackground(Color.white);
			btnEmpty[i].addActionListener(new ButtonPress());
			pnlNewGame.add(btnEmpty[i]);
		}
		window.add(pnlNewGame, BorderLayout.NORTH);
		
		turn.setText("Turn: " + String.valueOf(turnCount));
		pnlSouth.add(turn);
		window.add(pnlSouth, BorderLayout.SOUTH);
		window.setVisible(true);
	}
	static class ButtonPress implements ActionListener{
		public void actionPerformed(ActionEvent click) {
	
			for (int i = 0; i < 100; i++) {
				if (btnEmpty[i] == click.getSource()) {
					turnCount++;
					turn.setText("Turn: " + String.valueOf(turnCount));
					
					if (checkHit(i)) {
						btnEmpty[i].setText("O");
						numHits++;
					} else {
						btnEmpty[i].setText("X");
					}
					if (numHits == 16) {
						win();
						break;
					}
				}
			}
			
		}
		public boolean checkHit(int i) {
			if (positions[i] != 'X') {
				return true;
			}
			return false;
		}
		
		public void win() {
			// send connection 
			// send turn count
			int option = JOptionPane.showConfirmDialog(null,
					"You Win!", "Exit Game",
					JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION)
				System.exit(0);
//			try {
//				Socket s = new Socket(serverName, 5190);
//				PrintStream ps = new PrintStream(s.getOutputStream());
//				ps.print(username + ": " + turnCount + "\r\n");
//			} catch (IOException e) {
//				
//			}
		}
		
	}
	
	public static void main(String[] args) {
		serverName = JOptionPane.showInputDialog("server ip or name: ");
        username = JOptionPane.showInputDialog("new username: ");
        
        try{
            Socket s = new Socket(serverName, 5190);
            Scanner sin = new Scanner(s.getInputStream());
            PrintStream ps = new PrintStream(s.getOutputStream());
            ps.print(username + "\r\n");
            new battleshipClient();
            String string = sin.nextLine();
            positions = string.toCharArray();
        }
        catch(IOException e){
            System.out.println("Connection to google failed. frown emoticon");
        }
	}
}