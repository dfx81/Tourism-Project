/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderia.launcher;

import com.coderia.backend.Client;
import com.coderia.frontend.Content;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;



/**
 *
 * @author user
 */




 
public class sop extends Content{
private JPanel p1;
private JTable table;
private JButton b1,b2,b3,b4;
private JTextField tf1,tf2,tf3,tf4;
private JComboBox box,box2,box3,box4;
int ro,col;

   
    @Override
    public void buildUI(Client yes){
     
     JPanel p1 = new JPanel();
     setLayout(new BorderLayout()); 
     this.add(p1,BorderLayout.SOUTH);
     
     
     
    JButton b1 = new JButton("SOP AIR ASIA");
    JButton b2 = new JButton("SOP MAS");
    JButton b3 = new JButton("SOP MALINDO");
     
    p1.add(b1);
    p1.add(b2);       
    p1.add(b3);   
    
  
 
     JLabel l1 = new JLabel("<<SOP AIRPLANE MALAYSIA>>");
     l1.setHorizontalAlignment(JLabel.CENTER);
     this.add(l1,BorderLayout.NORTH);
     
     
     
     JPanel p3 = new JPanel();
     p3.setLayout(new BorderLayout());
     JTable table = new JTable();
     
     Object[] columns = {"k","Temperature","Number of seat","Type of Airplane ","Mask"};
     DefaultTableModel model = new DefaultTableModel();
     model.setColumnIdentifiers(columns);
     table.setModel(model);
     
     table.setBackground(Color.WHITE);
     table.setForeground(Color.BLACK);
     Font font = new Font("",1,12);
     table.setFont(font);
     table.setRowHeight(30);
     p3.add(table);
     this.add(p3,BorderLayout.CENTER);
      

    

     
       
    JPanel p2 = new JPanel();
     p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
     JTextField tf1 = new JTextField();
     JLabel l2 = new JLabel("NAME:");
     p2.add(l2);
     p2.add(tf1);
     String[] temperature = {"Below 37","Above 37"};
     JComboBox box2 = new JComboBox(temperature);
     JLabel l3 = new JLabel("Temperature:");
     p2.add(l3);
     p2.add(box2);
    
     String[] NumberofSeat = {"1-10","10-20","20-30","30-40","40-50","50-60","60-70","70-80","80-90","100-110","110-120","120-130","1300-140","140-150"};
     JComboBox box3 = new JComboBox(NumberofSeat);
     JLabel l4 = new JLabel("Number of Seat:");
     p2.add(l4);
     p2.add(box3);
     
     String[] TypeofAirplane = {"Air Asia","MAS","Malindo"};
     JComboBox box4 = new JComboBox(TypeofAirplane);
     JLabel l9 = new JLabel("Type of Airplane:");
     p2.add(l9);
     p2.add(box4);
     
     String[] mask = {"true","false"};
     JComboBox box = new JComboBox(mask);
     JLabel l5 = new JLabel("Mask:");
     p2.add(l5);
     p2.add(box);
     
     JLabel l6 = new JLabel("            ");
     p2.add(l6);
     
     JButton b4 = new JButton("Add and Save");
    
     p2.add(b4);
     
     JLabel l7 = new JLabel("            ");
     p2.add(l7);
     
     JButton b5 = new JButton("Delete");
     p2.add(b5);
     
     JLabel l8 = new JLabel("            ");
     p2.add(l8);
     
     JButton b6 = new JButton("Submit");
     p2.add(b6);
     
     
     
    
     
     this.add(p2,BorderLayout.WEST);
     
      
     Object[] row = new Object[5];
    
     b4.addActionListener(new ActionListener(){
         
         @Override
         public void actionPerformed(ActionEvent ae) {
            String temp = box2.getSelectedItem().toString();
           String ns = box3.getSelectedItem().toString();
           String ap   = box4.getSelectedItem().toString();
           String mask = box.getSelectedItem().toString();
           String name = tf1.getText();
          
            if(name.equals("")||ap.equals("MAS")|| ap.equals("Malindo")||temp.equals("mask")||ns.equals("120-130")|| ns.equals("130-140")|| ns.equals("140-150")|| temp.equals("Above 37")){
                JOptionPane.showMessageDialog(null,"Please check and follow SOP of the selected Airplane");
            }else{
            
           row[0] =tf1.getText();
            row[1] =box2.getSelectedItem().toString();
             row[2] =box3.getSelectedItem().toString();
              row[3] =box4.getSelectedItem().toString();
              row[4] =box.getSelectedItem().toString();
              model.addRow(row);
            creatFolder();
            readFile();
            countLines ();
            addData(tf1.getText(),box2.getSelectedItem().toString(),box3.getSelectedItem().toString(),box4.getSelectedItem().toString(),box.getSelectedItem().toString());
            JOptionPane.showMessageDialog(null,"Successfully saved");
            }
         }
     });
  
     b5.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent ae) {
           int i = table.getSelectedRow();
           if(i>=0){
               model.removeRow(i);
           }else{
            JOptionPane.showMessageDialog(null,"Please select a row to delete");
           }
          
         }
     });
     
     
      
     b1.addActionListener(new ActionListener (){
         @Override
         public void actionPerformed(ActionEvent ae) {
           JOptionPane.showMessageDialog(null,"1.Temperature cannot above 37 degree celcius\n"+
                                                 "2. Number of passenger cannot more than 110\n"+
                                                  "3. All passenger must wearing a mask\n"+
                                                  "4. Passenger must fill in thier name");
      
          
         }
     });
     
      b2.addActionListener(new ActionListener (){
         @Override
         public void actionPerformed(ActionEvent ae) {
           JOptionPane.showMessageDialog(null,"1.Temperature cannot above 37 degree celcius\n"+
                                                 "2. Number of passenger cannot more than 120\n"+
                                                  "3. All passenger must wearing a mask\n"+
                                                  "4. Passenger must fill in thier name");
      
       
         }
     });
     b3.addActionListener(new ActionListener (){
         @Override
         public void actionPerformed(ActionEvent ae) {
           JOptionPane.showMessageDialog(null,"1.Temperature cannot above 37 degree celcius\n"+
                                                 "2. Number of passenger cannot more than 140\n"+
                                                  "3. All passenger must wearing a mask\n"+
                                                  "4. Passenger must fill in thier name");
      
          
          
         }
     });
     
      b6.addActionListener(new ActionListener(){
         @Override
         
         public void actionPerformed(ActionEvent ae) {
          String name = tf1.getText();
         if(name.equals("")){
               JOptionPane.showMessageDialog(null,"Please complete list");
           }else{
            JOptionPane.showMessageDialog(null,"List has been submited");
           }  
          
         }
     });
     
     
     
      
}
    File f = new File("/Users/user/Desktop/passengerInfo.txt");
     int ln;
   void creatFolder(){
        if(!f.exists()){
            f.mkdirs();
        }
    }
   void readFile(){
        try {
            FileReader fr = new FileReader(f+"\\passengerInfo.txt");
            System.out.println("file exists! ");
        } catch (FileNotFoundException ex) {
            try {
                FileWriter fw = new FileWriter(f+"\\passengerInfo.txt");
                System.out.println("File Created!");
            } catch (IOException ex1) {
                Logger.getLogger(sop.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
    }
    
    void addData(String nm,String temp, String ns,String ap,String ms){
        try {
            RandomAccessFile raf = new RandomAccessFile(f+"\\passengerInfo.txt","rw");
          for(int i=0;i<ln;i++){
              raf.readLine();
          }
          if(ln>0){
             raf.writeBytes("\r\n");
             raf.writeBytes("\r\n");
          }
             raf.writeBytes("Name:"+nm+"\r\n");
              raf.writeBytes("Temperature:"+temp+"\r\n");
               raf.writeBytes("Number of seat:"+ns+"\r\n");
                raf.writeBytes("Type of airplane:"+ap+"\r\n");
                 raf.writeBytes("Mask:"+ms+"\r\n");
                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(sop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(sop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void countLines (){
         try {  
             ln=0;
             RandomAccessFile raf = new RandomAccessFile(f+"\\passengerInfo.txt","rw");
             for(int i=0;raf.readLine()!=null;i++){
                ln++;
             }
             System.out.println("number of lines:"+ln);
         } catch (FileNotFoundException ex) {
             Logger.getLogger(sop.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(sop.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
    

 }


