/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleshipserver;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author kurt
 */
public class BattleShipServer {
    //public static ArrayList<UserConn> conns;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        battleshipserver.ServerGui s = new battleshipserver.ServerGui();
        s.setVisible(true);
    }
    
}


//class UserConn extends Thread {
//   
//    public Socket socket;
//    public String username;
//    UserConn(Socket conn) {socket = conn;}
//    @Override
//    public void run() {
//        try {
//            Scanner sin = new Scanner(socket.getInputStream());
//            String user = sin.nextLine();
//            username = user;
//            while (true) {
//                if (sin.hasNextLine()) {
//                    String msg = sin.nextLine();
//                    for(UserConn u : d) {
//                        u.sendMessage(msg, this.username);
//                    }
//                }
//            }
//        }
//        catch (IOException ex){ System.out.println(ex.toString());}
//    }
//    void sendMessage(String msg, String username) {
//        try {
//            PrintStream ps = new PrintStream(socket.getOutputStream());
//            ps.println(username + ": " + msg);
//        }
//        catch(IOException ex) {
//            System.out.println(ex.toString());
//        }
//    }
//}
