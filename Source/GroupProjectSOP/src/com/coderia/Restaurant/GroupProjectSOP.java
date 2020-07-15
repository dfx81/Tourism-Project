package com.coderia.Restaurant;

import com.coderia.frontend.Content;
import com.coderia.backend.Client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupProjectSOP extends Content{
    private JPanel panel1 , panel2, panel3;
    private JLabel label1, label2, label3, label4, label5;
    private JTable table;
    private JScrollPane scrollPane;
    int row,col;
    
    private JWindow window;
    String[] header = new String [] {"Date", "Customer Name", "Handphone number","Celsius Degree"};
    DefaultTableModel dtm;
    
    @Override
    public void buildUI(Client yes) {
        
        ArrayList<soplist> listofcust;       
        listofcust = new ArrayList<>();
        
        panel1 = new JPanel();
        setLayout(new BorderLayout());
        this.add(panel1,BorderLayout.SOUTH);
        
        JButton b1 = new JButton("SOP");
        JButton b2 = new JButton("ADD");
        JButton b3 = new JButton("SEARCH");
        JButton b4 = new JButton("DELETE");
        JButton b5 = new JButton("EDIT");
        JButton b6 = new JButton("UPDATE");
        
        panel1.add(b1);
        panel1.add(b2);
        panel1.add(b3);
        panel1.add(b4);
        panel1.add(b5);
        panel1.add(b6);
        
        label5 = new JLabel("SOP RESTAURANT");
        label5.setSize(200, 60);
        label5.setHorizontalAlignment(JLabel.CENTER);
        this.add(label5,BorderLayout.NORTH);
        
        panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        
        
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel(){
            
            
        
        @Override
        public boolean isCellEditable(int row, int column) {
        return false;
        }
        };
        
        model.addColumn("Date");
        model.addColumn("Name");
        model.addColumn("Contact Number");
        model.addColumn("Celcrius Degree");
        model.setColumnIdentifiers(header);
        table.setModel(model);
        
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setGridColor(Color.black);
        table.setFont(new Font("Taloma",Font.PLAIN,15));
        table.setRowHeight(30);     
        panel2.add(table);
        this.add(panel2,BorderLayout.CENTER);
       
        //int i = table.getSelectedRow();
        
        
        b1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "1. Wear a mask before entering the restaurant\n"+
                                           "2. Each table only able to sit 1-4 person with 1 meter distance\n"+
                                           "3. Customer should check their temperature (below 37degree celcius)\n"+
                                           "4. Customer who has 37degree celcius is not allowed to enter the restaurant\n"+
                                           "5. Customer should record personal details that has been requested.\n"+
                                           "6. Customer should use saintizer that has been prepared before entering the restaurant");
 
            }
    });    
        
        b2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int y= 0;
                
                    String inDate = JOptionPane.showInputDialog(null,"Please enter date");
                    String inName = JOptionPane.showInputDialog(null,"Please enter your name");
                    String inContactNumber = JOptionPane.showInputDialog(null,"Please enter your contact number");
                    String temperature  = JOptionPane.showInputDialog(null,"Please enter your temperature(ceclius degree)");
                    double inTemperature = Double.parseDouble(temperature);
                    
                    listofcust.add(new soplist(inDate ,inName, inContactNumber,inTemperature));
                    model.setRowCount(0);
                    
                    JOptionPane.showMessageDialog(null, "Your data has been sucessfully recorded.");
                    if(inTemperature>=37.0){
                        JOptionPane.showMessageDialog(null, "You are not allowed to enter due to high temperature");
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "You are allowed to enter.");
                        
                    }
                    
                    for (int i = 0; i<listofcust.size(); i++){
                        Object [] objs = {listofcust.get(i).inDate, listofcust.get(i).inName, listofcust.get(i).inContactNumber, listofcust.get(i).inTemperature};
                        model.addRow(objs);
                    }
                } 
               
                        
          
    }); 
        
        b3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String search = JOptionPane.showInputDialog(null,"Please enter the details you wish to search");
                for(int i=0; i<listofcust.size(); i++ ){
                    if(listofcust.get(i).inName.equalsIgnoreCase(search) ||listofcust.get(i).inDate.equalsIgnoreCase(search) || listofcust.get(i).inContactNumber.equalsIgnoreCase(search) ){
                    JOptionPane.showMessageDialog(b3, "Your data is found!!!", "Search Data", 2);
                    
                    String data = listofcust.get(i).inDate+"\n"+listofcust.get(i).inName +"\n"+
                                  listofcust.get(i).inContactNumber +" \n" + listofcust.get(i).inTemperature + "Celclius Degree";
                    JOptionPane.showMessageDialog(null, data , "Search Data", 3);
                    
                    }JOptionPane.showMessageDialog(b3, "Your  is not found!!!", "Search Data", 2);
                    
            }
            }    
    }); 
        b4.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                int choice = JOptionPane.showOptionDialog(null, "Are you sure want to delete this data? ", "Delete Data", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                int i = table.getSelectedRow();
                if(i>=0){ 
                    if(choice == JOptionPane.YES_OPTION){
                        model.removeRow(i);
                        listofcust.remove(i);
                    }else
                        JOptionPane.showMessageDialog(null,"Your request has been canceled.");
                }else{
                    JOptionPane.showMessageDialog(null,"Please select a row.");
                    
                }
            
            }
    }); 
        b5.addActionListener(new ActionListener(){
            int row = table.getRowCount();
            @Override
            public void actionPerformed(ActionEvent e) {
               String updatedate = JOptionPane.showInputDialog(null,"Please enter date");
               String updatename = JOptionPane.showInputDialog(null,"Please enter your name");
               String updatecontactnumber = JOptionPane.showInputDialog(null,"Please enter your contact number");
               String updatetemperature = JOptionPane.showInputDialog(null,"Please enter your temperature(ceclius degree)");
               listofcust.get(row).inDate = updatedate ;
               listofcust.get(row).inName = updatename ; 
               listofcust.get(row).inContactNumber = updatecontactnumber ;
               double updateintemperature = Double.parseDouble(updatetemperature);
               listofcust.get(row).inTemperature = updateintemperature ;
              
               model.setRowCount(0);
               
              for(int i=0; i<listofcust.size(); i++ ){
                Object[] objs = {listofcust.get(i).inDate, listofcust.get(i).inName, listofcust.get(i).inContactNumber ,listofcust.get(i).inTemperature};
                model.addRow(objs);
        }  
            }
    });
         b6.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               for(int i=0; i<listofcust.size(); i++ ){
                createFolder();
                readFile();
                addData(listofcust.get(i).inDate, listofcust.get(i).inName, listofcust.get(i).inContactNumber, listofcust.get(i).inTemperature);
                countLines();
               }
            }
    });
                 }                 
      File f = new File("/Users/HP/Desktop/customerList.txt");
     int ln;
   void createFolder(){
        if(!f.exists()){
            f.mkdirs();
        }
    }
   void readFile(){
        try {
            FileReader fr = new FileReader(f);
            System.out.println("file exists! ");
        } catch (FileNotFoundException ex) {
            try {
                FileWriter fw = new FileWriter(f);
                System.out.println("File Created!");
            } catch (IOException ex1) {
                Logger.getLogger(GroupProjectSOP.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
    }
    
    void addData(String inputdate, String inputname, String inputcontactnumber, double inputtemperature){
        
        try {
            RandomAccessFile raf = new RandomAccessFile(f,"rw");
          for(int i=0;i<ln;i++){
              raf.readLine();
          }
          if(ln>0){
             raf.writeBytes("\r\n");
             raf.writeBytes("\r\n");
          }
          
             raf.writeBytes("Date:"+inputdate+"\r\n");
              raf.writeBytes("Name:"+inputname+"\r\n");
               raf.writeBytes("Contact Number:"+inputcontactnumber+"\r\n");
                raf.writeBytes("Temperature:"+inputtemperature+"\r\n");

                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GroupProjectSOP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GroupProjectSOP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void countLines (){
         try {  
             ln=0;
             RandomAccessFile raf = new RandomAccessFile(f,"rw");
             for(int i=0;raf.readLine()!=null;i++){
                ln++;
             }
             System.out.println("number of lines:"+ln);
         } catch (FileNotFoundException ex) {
             Logger.getLogger(GroupProjectSOP.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(GroupProjectSOP.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
}    
    

