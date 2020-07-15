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
import java.util.ArrayList;


/**
 *
 * @author user
 */




 
public class newSop extends Content{
private JPanel p1;
private JTable table;
private JButton b1,b2,b3,b4;
private JTextField tf1,tf2,tf3,tf4;
private JComboBox box,box2,box3,box4;

 ArrayList<SOPAirplane> SOPairplanelist;
 String header[] = new String[]{"Name","Temperature","Number of seat","Type of Airplane ","Mask"};
 DefaultTableModel dtm;
 int row,col;
    
    @Override
    public void buildUI(Client yes){
     SOPairplanelist = new ArrayList<>();
     dtm = new DefaultTableModel(header,0);
     
    
     
   
     JPanel p1 = new JPanel();
     setLayout(new BorderLayout()); 
     this.add(p1,BorderLayout.SOUTH);
     
     
     
    JButton b1 = new JButton("SOP AIR ASIA");
    JButton b2 = new JButton("SOP MAS");
    JButton b3 = new JButton("SOP MALINDO");
    JButton b12 = new JButton ("Save all");
     
    p1.add(b1);
    p1.add(b2);       
    p1.add(b3); 
    p1.add(b12);
    
  
 
     JLabel l1 = new JLabel("<<SOP AIRPLANE MALAYSIA>>");
     l1.setHorizontalAlignment(JLabel.CENTER);
     this.add(l1,BorderLayout.NORTH);
     
     
     
     JPanel p3 = new JPanel();
     p3.setLayout(new BorderLayout());
     JTable table = new JTable();
     
     
     
     table.setBackground(Color.WHITE);
     table.setForeground(Color.BLACK);
     Font font = new Font("",1,12);
     table.setFont(font);
     table.setRowHeight(30);
     p3.add(table);
     this.add(p3,BorderLayout.CENTER);
       table.setModel(dtm);

    


       
    JPanel p2 = new JPanel();
     p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
     JTextField tf1 = new JTextField();
     JLabel l2 = new JLabel("NAME:");
     p2.add(l2);
     p2.add(tf1);
     String[] ptemperature = {"","Below 37","Above 37"};
     JComboBox box2 = new JComboBox(ptemperature);
     JLabel l3 = new JLabel("Temperature:");
     p2.add(l3);
     p2.add(box2);
    
     String[] numberSeat = {"","1-10","10-20","20-30","30-40","40-50","50-60","60-70","70-80","80-90","100-110","110-120","120-130","130-140","140-150"};
     JComboBox box3 = new JComboBox(numberSeat);
     JLabel l4 = new JLabel("Number of Seat:");
     p2.add(l4);
     p2.add(box3);
     
     String[] typeAirplane = {"","Air Asia","MAS","Malindo"};
     JComboBox box4 = new JComboBox(typeAirplane);
     JLabel l9 = new JLabel("Type of Airplane:");
     p2.add(l9);
     p2.add(box4);
     
     String[] mask = {"","true","false"};
     JComboBox box = new JComboBox(mask);
     JLabel l5 = new JLabel("Mask:");
     p2.add(l5);
     p2.add(box);
     
     JLabel l6 = new JLabel("            ");
     p2.add(l6);
     
     JButton b4 = new JButton("Add");
    
     p2.add(b4);
     
       JLabel l12 = new JLabel("            ");
     p2.add(l12);
     
     JButton b10 = new JButton("Reset");
     p2.add(b10);
     
     
     
     
     JLabel l8 = new JLabel("            ");
     p2.add(l8);
     
     JButton b6 = new JButton("Edit");
     p2.add(b6);
     
    
     JLabel l11 = new JLabel("            ");
     p2.add(l11);
     
     JButton b8 = new JButton("Update");
     p2.add(b8);
     
      JLabel l10 = new JLabel("            ");
     p2.add(l10);
     
     JButton b7 = new JButton("Search");
     p2.add(b7);
     
     JLabel l7 = new JLabel("            ");
     p2.add(l7);
     
     JButton b5 = new JButton("Delete");
     p2.add(b5);
     
     
     
     
     
    
     
     this.add(p2,BorderLayout.WEST);
     
      
   
    
     b4.addActionListener(new ActionListener(){
         
         @Override
         public void actionPerformed(ActionEvent ae) {
         String temperature = box2.getSelectedItem().toString();
           String numberOfSeat = box3.getSelectedItem().toString();
           String typeOfAirplane   = box4.getSelectedItem().toString();
           String mask = box.getSelectedItem().toString();
           String name = tf1.getText();
           
           switch(typeOfAirplane){
            case "Air Asia" :
                if(typeOfAirplane.equals("")||name.equals("")||mask.equals("false")|| mask.equals("")|| numberOfSeat.equals("")||numberOfSeat.equals("120-130")|| numberOfSeat.equals("130-140")|| numberOfSeat.equals("140-150")|| temperature.equals("")||temperature.equals("Above 37")){
                JOptionPane.showMessageDialog(null,"Please check and follow SOP of the selected Airplane");
            }else{
       
             SOPairplanelist.add(new SOPAirplane(name,temperature,numberOfSeat,typeOfAirplane,mask));
             dtm.setRowCount(0);
  
            for(int i = 0; i < SOPairplanelist.size(); i++){
            Object[]objs = {SOPairplanelist.get(i).name,SOPairplanelist.get(i).temperature,SOPairplanelist.get(i).numberOfSeat,SOPairplanelist.get(i).typeOfAirplane,SOPairplanelist.get(i).mask,};
            dtm.addRow(objs);
         
         
            
       
        }     
            
                
                }
             break;
             
            case "MAS":
             if(typeOfAirplane.equals("")||name.equals("")||mask.equals("false")|| mask.equals("")|| numberOfSeat.equals("")|| numberOfSeat.equals("130-140")|| numberOfSeat.equals("140-150")|| temperature.equals("")||temperature.equals("Above 37")){
                JOptionPane.showMessageDialog(null,"Please check and follow SOP of the selected Airplane");
            }else{
       
             SOPairplanelist.add(new SOPAirplane(name,temperature,numberOfSeat,typeOfAirplane,mask));
             dtm.setRowCount(0);
  
           
           for(int i = 0; i < SOPairplanelist.size(); i++){
            Object[]objs = {SOPairplanelist.get(i).name,SOPairplanelist.get(i).temperature,SOPairplanelist.get(i).numberOfSeat,SOPairplanelist.get(i).typeOfAirplane,SOPairplanelist.get(i).mask,};
            dtm.addRow(objs);
       
         
                } 
                }
            break;
            case "Malindo":
             if(typeOfAirplane.equals("")||name.equals("")||mask.equals("false")|| mask.equals("")|| numberOfSeat.equals("")|| numberOfSeat.equals("140-150")|| temperature.equals("")||temperature.equals("Above 37")){
                JOptionPane.showMessageDialog(null,"Please check and follow SOP of the selected Airplane");
            }else{
       
             SOPairplanelist.add(new SOPAirplane(name,temperature,numberOfSeat,typeOfAirplane,mask));
             dtm.setRowCount(0);
     
           for(int i = 0; i < SOPairplanelist.size(); i++){
            Object[]objs = {SOPairplanelist.get(i).name,SOPairplanelist.get(i).temperature,SOPairplanelist.get(i).numberOfSeat,SOPairplanelist.get(i).typeOfAirplane,SOPairplanelist.get(i).mask,};
            dtm.addRow(objs);
            
         
                } 
                }
            }
          
           
         }
     });
   
     b5.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent ae) {
         int dialogButton = JOptionPane.YES_NO_OPTION;
    int dialogResult = JOptionPane.showConfirmDialog(null,"Delete this data","Delete",dialogButton);
    if(dialogResult == 0){
        dtm.removeRow(row);
        SOPairplanelist.remove(row);
        dtm.setRowCount(0);
        for(int i=0; i< SOPairplanelist.size(); i++){
           Object[]objs = {SOPairplanelist.get(i).name,SOPairplanelist.get(i).temperature,SOPairplanelist.get(i).numberOfSeat,SOPairplanelist.get(i).typeOfAirplane,SOPairplanelist.get(i).mask,};
            dtm.addRow(objs);
           
        }
     
    }else{
        
    }
    
          
         }


     });
     
     
      
     b1.addActionListener(new ActionListener (){
         @Override
         public void actionPerformed(ActionEvent ae) {
           JOptionPane.showMessageDialog(null,"1.Temperature cannot above 37 degree celcius\n"+
                                                 "2. Number of passenger cannot more than 110\n"+
                                                  "3. All passenger must wearing a mask\n"+
                                                  "4. Must fill in all of the data");
      
          
         }
     });
     
      b2.addActionListener(new ActionListener (){
         @Override
         public void actionPerformed(ActionEvent ae) {
           JOptionPane.showMessageDialog(null,"1.Temperature cannot above 37 degree celcius\n"+
                                                 "2. Number of passenger cannot more than 120\n"+
                                                  "3. All passenger must wearing a mask\n"+
                                                  "4. Must fill in all of the data");
      
       
         }
     });
     b3.addActionListener(new ActionListener (){
         @Override
         public void actionPerformed(ActionEvent ae) {
           JOptionPane.showMessageDialog(null,"1.Temperature cannot above 37 degree celcius\n"+
                                                 "2. Number of passenger cannot more than 140\n"+
                                                  "3. All passenger must wearing a mask\n"+
                                                  "4. Must fill in all of the data");
      
          
          
         }
     });
     
      b6.addActionListener(new ActionListener(){
         @Override
        
         public void actionPerformed(ActionEvent ae) {
         int k = table.getSelectedRow();
           if(k>=0){
        row = table.getSelectedRow();
        col = table.getComponentCount();
        System.out.println(row +"+"+col);
      
      tf1.setText(dtm.getValueAt(row,0).toString());
      String temperature = dtm.getValueAt(row,1).toString();
      String numberOfSeat  = dtm.getValueAt(row,2).toString();
      String typeOfAirplane  = dtm.getValueAt(row,3).toString();
       String mask  = dtm.getValueAt(row,4).toString();
      for(int i=0; i<(box2.getItemCount());i++){
        if(box2.getItemAt(i).equals(temperature)){
      box2.setSelectedIndex(i);
      
   }
} 
       for(int i=0; i<(box3.getItemCount());i++){
        if(box3.getItemAt(i).equals(numberOfSeat)){
      box3.setSelectedIndex(i);
      
   }
} 
        for(int i=0; i<(box4.getItemCount());i++){
        if(box4.getItemAt(i).equals(typeOfAirplane)){
      box4.setSelectedIndex(i);
      
   }
} 
         for(int i=0; i<(box.getItemCount());i++){
        if(box.getItemAt(i).equals(mask)){
      box.setSelectedIndex(i);
      
   }
} 
     
          
         
         }else{
             JOptionPane.showMessageDialog(null,"Please select a row to edit");   
           }
         }
     });
      
       b7.addActionListener(new ActionListener(){
         @Override
     
        
        public void actionPerformed(ActionEvent ae) {
       
        
     
    String input = JOptionPane.showInputDialog(null,"Search name");
    for(int i=0; i<SOPairplanelist.size();i++){
     if(SOPairplanelist.get(i).name.equalsIgnoreCase(input)){
      JOptionPane.showMessageDialog(b7,"Found!","Search name",2);
      JOptionPane.showMessageDialog(null,"1.Name:"+"   "+SOPairplanelist.get(i).name+"\n"+
                                                 "2. Temperature:"+ "   "+SOPairplanelist.get(i).temperature+"\n"+
                                                  "3. Number of Seat:"+"   "+SOPairplanelist.get(i).numberOfSeat+"\n"+
                                                  "4. Type of airplane:"+"   "+SOPairplanelist.get(i).typeOfAirplane+"\n"+
                                                    "5. Status of mask:"+"   "+SOPairplanelist.get(i).mask );
  
     
     
          return;
     }
    }
 JOptionPane.showMessageDialog(b7,"Not Found!","Search Plane", 2);
         
          
         }
     });
     
        b8.addActionListener(new ActionListener(){
         @Override
      
         public void actionPerformed(ActionEvent ae) {
       int k = table.getSelectedRow();
           if(k>=0){
       
        String editname = tf1.getText();
        String edittemperature= box2.getSelectedItem().toString();
        String editnumberOfSeat= box3.getSelectedItem().toString();
        String edittypeOfAirplane= box4.getSelectedItem().toString();
        String editMask= box.getSelectedItem().toString();
        
   
       
        row = table.getSelectedRow(); 
       SOPairplanelist.get(row).name= editname;
        SOPairplanelist.get(row).temperature= edittemperature;
         SOPairplanelist.get(row).numberOfSeat=editnumberOfSeat ;
          SOPairplanelist.get(row).typeOfAirplane= edittypeOfAirplane;
           SOPairplanelist.get(row).mask= editMask ;
       
      
       
       dtm.setRowCount(0);
          for(int i = 0; i < SOPairplanelist.size(); i++){
            Object[]objs = {SOPairplanelist.get(i).name,SOPairplanelist.get(i).temperature,SOPairplanelist.get(i).numberOfSeat,SOPairplanelist.get(i).typeOfAirplane,SOPairplanelist.get(i).mask,};
            dtm.addRow(objs);
        }    
         }else{
              JOptionPane.showMessageDialog(null,"Please select a row to update");     
           }
         }
     });
        
         b10.addActionListener(new ActionListener(){
         @Override
      
         public void actionPerformed(ActionEvent ae) {
         tf1.requestFocus();
        tf1.setText("");
        box.setSelectedIndex(0);
         box2.setSelectedIndex(0);
          box3.setSelectedIndex(0);
           box4.setSelectedIndex(0);        
        
         }
     });
         
          b12.addActionListener(new ActionListener(){
         @Override
      
         public void actionPerformed(ActionEvent ae) {
          int respone =  JOptionPane.showConfirmDialog(null,"Please make sure you have UPDATED each row of the information to get the ACURRATE FORMAT","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
           if(respone==JOptionPane.YES_OPTION){
          for(int i = 0; i < SOPairplanelist.size(); i++){
          
           
            creatFolder();
            readFile();
            countLines ();
            addData(SOPairplanelist.get(i).name,SOPairplanelist.get(i).temperature,SOPairplanelist.get(i).numberOfSeat,SOPairplanelist.get(i).typeOfAirplane,SOPairplanelist.get(i).mask);
             }  
           }else{
               JOptionPane.showMessageDialog(null,"Please update now"); 
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
                Logger.getLogger(newSop.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
    }
    
    void addData(String name,String temperature, String numberOfSeat,String typeOfAirplane,String mask){
        try {
            RandomAccessFile raf = new RandomAccessFile(f+"\\passengerInfo.txt","rw");
          for(int i=0;i<ln;i++){
              raf.readLine();
          }
          if(ln>0){
             raf.writeBytes("\r\n");
             raf.writeBytes("\r\n");
                       }
             raf.writeBytes("Name:"+name+"\r\n");
              raf.writeBytes("Temperature:"+temperature+"\r\n");
              raf.writeBytes("Number of seat:"+numberOfSeat+"\r\n");
               raf.writeBytes("Type of airplane:"+typeOfAirplane+"\r\n");
                 raf.writeBytes("Mask:"+mask+"\r\n");
                  
                  

                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(newSop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(newSop.class.getName()).log(Level.SEVERE, null, ex);
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
             Logger.getLogger(newSop.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(newSop.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
    

 }




