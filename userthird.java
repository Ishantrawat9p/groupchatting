package groupchatting;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;

import java.io.*;
import java.net.*;

import javax.swing.*;

public class userthird extends JFrame implements ActionListener, Runnable{
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    
    BufferedWriter writer;
    BufferedReader reader;

    userthird(){
        
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(Color.RED);
        p1.setBounds(0, 0, 450, 70);
        add(p1);
        
       ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("groupchatting/icons/cross.png"));
       Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
       ImageIcon i3 = new ImageIcon(i2);
       JLabel l1 = new JLabel(i3);
       l1.setBounds(5, 17, 30, 30);
       p1.add(l1);
       
       l1.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent ae){
               System.exit(0);
           }
       });
       
       ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("groupchatting/icons/group1.jpg"));
       Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
       ImageIcon i6 = new ImageIcon(i5);
       JLabel l2 = new JLabel(i6);
       l2.setBounds(40, 5, 60, 60);
       p1.add(l2);
       
       
       
       JLabel l3 = new JLabel("CHATGRP");
       l3.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
       l3.setForeground(Color.WHITE);
       l3.setBounds(110, 15, 100, 18);
       p1.add(l3);   
       
       
       JLabel l4 = new JLabel("userone,usertwo,userthree");
       l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
       l4.setForeground(Color.WHITE);
       l4.setBounds(110, 35, 160, 20);
       p1.add(l4);   
       
       
       a1 = new JTextArea();
       a1.setBounds(5, 75, 440, 570);
       a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       a1.setEditable(false);
       a1.setLineWrap(true);
       a1.setWrapStyleWord(true);                       
       add(a1);
       
       
       t1 = new JTextField();
       t1.setBounds(5, 655, 310, 40);
       t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       add(t1);
       
       b1 = new JButton("Send");
       b1.setBounds(320, 655, 123, 40);
       b1.setBackground(Color.GREEN);
       b1.setForeground(Color.WHITE);
       b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       b1.addActionListener(this);
       add(b1);
        
       getContentPane().setBackground(Color.WHITE);
       setLayout(null);
       setSize(450, 700);
       setLocation(910, 20); 
       setUndecorated(true);
       setVisible(true);
       
       try{
           
           Socket socketClient = new Socket("localhost", 2016);
           writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
           reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
       }catch(Exception e){}
       
        
    }
    
    public void actionPerformed(ActionEvent ae){
        String str = "user third\n"+t1.getText();
        try{
            writer.write(str);
            writer.write("\r\n\n");
            writer.flush();
        }catch(Exception e){}
        t1.setText("");
    }
    
    public void run(){
        try{
            String msg = "";
            while((msg = reader.readLine()) != null){
                a1.append(msg + "\n");
            }
        }catch(Exception e){}
    }
    
    public static void main(String[] args){
        userthird one = new userthird();
        Thread t1 = new Thread(one);
        t1.start();
    }
    
}