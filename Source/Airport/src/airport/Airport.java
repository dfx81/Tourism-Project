package airport;

import java.io.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import com.coderia.backend.Client;
import com.coderia.frontend.Content;


public class Airport extends Content implements ActionListener  { 

    // Components of the Form 
    private JLabel bcolor1;
    private JLabel bcolor;
    private JLabel image;
    private JLabel image2;
    private JLabel image3;
    private JLabel title;
    private JLabel inst;
    private Container c;
    private JButton ind; 
    private JButton mal; 
    private JButton gene;
    
    private Client client;
    
    //File gen = new File ("C:\\Users\\thebestp9\\Desktop\\Tourism-Project\\Tourism-Project\\Airport\\src\\airport\\GeneralSOP.txt");
    //BufferedReader br;
    
    // constructor, to initialize the components 
    // with default values. 
    
    
    public void buildUI(Client client) 
    {
        /*setTitle("SOP FOR AIRPORT"); 
        setBounds(300, 200, 900, 600); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setResizable(false); */
        
        this.client = client;

        //c = getContentPane(); 
        setLayout(null); 
      
        //the wide pink border colour
        bcolor = new JLabel();
        bcolor.setSize(480, 64); 
        bcolor.setLocation(0, 0);
        bcolor.setOpaque(true);
        bcolor.setBackground(new Color(255, 169, 195, 100));
        add(bcolor);
      
        //the thin black border colour
        bcolor1 = new JLabel();
        bcolor1.setSize(480, 2); 
        bcolor1.setLocation(0, 64);
        bcolor1.setOpaque(true);
        bcolor1.setBackground(new Color(0, 0, 0));
        add(bcolor1);
      
        //the air shape graphic
        image = new JLabel();
        image.setIcon(new ImageIcon("airlines2.png"));
        image.setSize(53, 64); 
        image.setLocation(373, 4);
        add(image);
        validate();
      
        //the air shape graphic
        image2 = new JLabel();
        image2.setIcon(new ImageIcon("airlines3.png"));
        image2.setSize(53, 64); 
        image2.setLocation(53, 4);
        add(image2);
        validate();
        
        //the colourful graphic
        image3 = new JLabel();
        image3.setIcon(new ImageIcon("Design.png"));
        image3.setSize(480, 480); 
        image3.setLocation(0, 0);
        add(image3);
        validate();
      
        //Text title in the front page
        title = new JLabel("SOP AIRPORT"); 
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setSize(160, 24); 
        title.setLocation(189, 24); 
        title.setForeground(new Color(0, 0, 0));
        add(title);

        //Label of text in the front page
        inst = new JLabel("Choose Your Options"); 
        inst.setFont(new Font("Arial", Font.PLAIN, 20)); 
        inst.setSize(266, 80); 
        inst.setLocation(122, 80); 
        add(inst);
        
        //the india button
        ind = new JButton("India"); 
        ind.setFont(new Font("Arial", Font.BOLD, 12)); 
        ind.setSize(90, 28); 
        ind.setLocation(96, 240); 
        ind.addActionListener(this); 
        add(ind);
        
        //the malaysia button
        mal = new JButton("Malaysia"); 
        mal.setFont(new Font("Arial", Font.BOLD, 12)); 
        mal.setSize(90, 28); 
        mal.setLocation(298, 240); 
        mal.addActionListener(this); 
        add(mal);
        
        //the general button
        gene = new JButton("General"); 
        gene.setFont(new Font("Arial", Font.BOLD, 12)); 
        gene.setSize(90, 28); 
        gene.setLocation(197, 320); 
        gene.addActionListener(this); 
        add(gene);

        //setVisible(true);
    } 

   // method actionPerformed() 
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //will show the general SOP
        if(e.getSource() == gene){
            try 
            {
                /*br = new BufferedReader(new FileReader(gen));
                String rt = "";
                String text = "";
                while ((rt = br.readLine()) != null)
                {
                    text += rt + "\n";
                }
                JOptionPane.showMessageDialog(this , text);*/
                
                client.sendRequest("/airport-sop-r");
                String rt = client.getResponse();
                String text = "";
                
                while (!rt.equals("/eof")) {
                    text += rt + "\n";
                    rt = client.getResponse();
                }
                
                JOptionPane.showMessageDialog(this , text);
            } catch (Exception err) {
                System.out.println(err);
                err.printStackTrace();
                System.exit(-1);
            }
            //} 
            
            /*catch (FileNotFoundException ex) 
            {
                Logger.getLogger(Airport.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
            catch (IOException ex) 
            {
                Logger.getLogger(Airport.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
        }
        
        //opening India JFrame
        if (e.getSource() == ind)
            new IndiaSOP(client);
       
        
        //opening Malaysia JFrame
        if (e.getSource() == mal) 
            new MalaySOP(client);
    } 
} 

// Driver Code 
class AirportGUI { 

   public static void main(String[] args) throws Exception , FileNotFoundException , IOException  { 
       Airport f = new Airport();
   } 
}


