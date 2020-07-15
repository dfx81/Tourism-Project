package airport;

import java.io.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.coderia.backend.Client;
//import com.coderia.frontend.Content;

class MalaySOP extends JFrame implements ActionListener {
    private JLabel bcolor1;
    private JLabel bcolor;
    private JLabel image;
    private JLabel image2;
    private JLabel image3;
    private JLabel title;
    private Container d;
    private JTextArea ssop;
    private JButton save; 
    private JButton show;
    private JScrollPane pane;
    private JLabel inst;
    private Client client;
    
    //File malgen = new File ("C:\\Users\\thebestp9\\Desktop\\Tourism-Project\\Tourism-Project\\Airport\\src\\airport\\SopMalaysia.txt");
    //FileWriter wr;
    //PrintWriter pr;
    //BufferedReader br;
    
public MalaySOP(Client client){
    this.client = client;
    
    setTitle("SOP FOR MALAYSIA"); 
    setBounds(300, 200, 900, 600); 
    setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
    setResizable(false);
    
    d = getContentPane(); 
    d.setLayout(null);
    
    bcolor = new JLabel();
    bcolor.setSize(900, 80); 
    bcolor.setLocation(0, 0);
    bcolor.setOpaque(true);
    bcolor.setBackground(new Color(255, 169, 195, 100));
    d.add(bcolor);
      
    //the thin black border colour
    bcolor1 = new JLabel();
    bcolor1.setSize(900, 3); 
    bcolor1.setLocation(0, 80);
    bcolor1.setOpaque(true);
    bcolor1.setBackground(new Color(0, 0, 0));
    d.add(bcolor1);
      
    //the air shape graphic
    image = new JLabel();
    image.setIcon(new ImageIcon("airlines2.png"));
    image.setSize(100, 80); 
    image.setLocation(700, 5);
    d.add(image);
    validate();
      
    //the air shape graphic
    image2 = new JLabel();
    image2.setIcon(new ImageIcon("airlines3.png"));
    image2.setSize(100, 80); 
    image2.setLocation(100, 5);
    d.add(image2);
    validate();
        
    //the colourful graphic
    image3 = new JLabel();
    image3.setIcon(new ImageIcon("Design.png"));
    image3.setSize(900, 600); 
    image3.setLocation(0, 0);
    d.add(image3);
    validate();
    
    //The title for Malaysia Frame
    title = new JLabel("Malaysia SOP"); 
    title.setFont(new Font("Arial", Font.PLAIN, 30));
    title.setSize(300, 30); 
    title.setLocation(355, 30); 
    title.setForeground(new Color(0, 0, 0));
    d.add(title);
    
    //The to-do title just to make it more realistic
    inst = new JLabel("Edit Your SOP"); 
    inst.setFont(new Font("Arial", Font.PLAIN, 48)); 
    inst.setSize(500, 100); 
    inst.setLocation(300, 100); 
    d.add(inst);
    
    //The text area will be a panel
    ssop = new JTextArea(); 
    ssop.setFont(new Font("Arial", Font.PLAIN, 15));  
    
    pane = new JScrollPane(ssop);
    pane.setBounds(200, 210, 500, 170);
    pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    d.add(pane); 
    
    //the save button
    save = new JButton("Save"); 
    save.setFont(new Font("Arial", Font.BOLD, 30)); 
    save.setSize(170, 35); 
    save.setLocation(200, 450); 
    save.addActionListener(this); 
    d.add(save);
        
    //the show SOP button
    show = new JButton("Show SOP"); 
    show.setFont(new Font("Arial", Font.BOLD, 30)); 
    show.setSize(200, 35); 
    show.setLocation(500, 450); 
    show.addActionListener(this); 
    d.add(show);
    
    setVisible(true);
    setLocationRelativeTo(null);

}
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //This function will show all of the sop in the text area
        if (e.getSource() == show){
        try 
            {
                //br = new BufferedReader(new FileReader(malgen));
                String rtx = "";
                String text = "";
                /*while ((rtx = br.readLine()) != null)
                {
                    text += rtx + "\n";
                    System.out.println(rtx);
                }*/
                
                client.sendRequest("/airport-msop-r");
                rtx = client.getResponse();
                
                while (!rtx.equals("/eof")) {
                    text += rtx + "\n";
                    rtx = client.getResponse();
                }
                
                ssop.setText(text);
                
            } catch (Exception err) {
                System.out.println(err);
                err.printStackTrace();
                System.exit(-1);
            }
            
            /*catch (FileNotFoundException ex) 
            {
                Logger.getLogger(Airport.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
            catch (IOException ex) 
            {
                Logger.getLogger(Airport.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
        
        //This function will save whatever data in the text area into the text
        //file SOPMalaysia.txt
        if(e.getSource() == save){
            try
            {
                String save = ssop.getText();
                //pr = new PrintWriter (new File("C:\\Users\\thebestp9\\Desktop\\Tourism-Project\\Tourism-Project\\Airport\\src\\airport\\SopMalaysia.txt"));
                //pr.write(save);
                //pr.close();
                
                client.sendRequest(save);
                client.sendRequest("/airport-msop-w");
            }
            catch (Exception err) 
        {
            JOptionPane.showMessageDialog(null, err + "");
        }
        
        }
        
    }
}

