import java.io.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class MainPage extends JFrame implements ActionListener  { 

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
    
    File gen = new File ("C:\\Users\\thebestp9\\Desktop\\Tourism-Project\\Tourism-Project\\AirportGui\\src\\main\\java\\GeneralSOP.txt");
    BufferedReader br;
    
    // constructor, to initialize the components 
    // with default values. 
    public MainPage() 
    {
        setTitle("SOP FOR AIRPORT"); 
        setBounds(300, 200, 900, 600); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setResizable(false); 

        c = getContentPane(); 
        c.setLayout(null); 
      
        //the wide pink border colour
        bcolor = new JLabel();
        bcolor.setSize(900, 80); 
        bcolor.setLocation(0, 0);
        bcolor.setOpaque(true);
        bcolor.setBackground(new Color(255, 169, 195, 100));
        c.add(bcolor);
      
        //the thin black border colour
        bcolor1 = new JLabel();
        bcolor1.setSize(900, 3); 
        bcolor1.setLocation(0, 80);
        bcolor1.setOpaque(true);
        bcolor1.setBackground(new Color(0, 0, 0));
        c.add(bcolor1);
      
        //the air shape graphic
        image = new JLabel();
        image.setIcon(new ImageIcon("airlines2.png"));
        image.setSize(100, 80); 
        image.setLocation(700, 5);
        c.add(image);
        validate();
      
        //the air shape graphic
        image2 = new JLabel();
        image2.setIcon(new ImageIcon("airlines3.png"));
        image2.setSize(100, 80); 
        image2.setLocation(100, 5);
        c.add(image2);
        validate();
        
        //the colourful graphic
        image3 = new JLabel();
        image3.setIcon(new ImageIcon("Design.png"));
        image3.setSize(900, 600); 
        image3.setLocation(0, 0);
        c.add(image3);
        validate();
      
        //Text title in the front page
        title = new JLabel("SOP AIRPORT"); 
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30); 
        title.setLocation(355, 30); 
        title.setForeground(new Color(0, 0, 0));
        c.add(title);

        //Label of text in the front page
        inst = new JLabel("Choose Your Options"); 
        inst.setFont(new Font("Arial", Font.PLAIN, 48)); 
        inst.setSize(500, 100); 
        inst.setLocation(230, 100); 
        c.add(inst);
        
        //the india button
        ind = new JButton("India"); 
        ind.setFont(new Font("Arial", Font.BOLD, 30)); 
        ind.setSize(170, 35); 
        ind.setLocation(180, 300); 
        ind.addActionListener(this); 
        c.add(ind);
        
        //the malaysia button
        mal = new JButton("Malaysia"); 
        mal.setFont(new Font("Arial", Font.BOLD, 30)); 
        mal.setSize(170, 35); 
        mal.setLocation(560, 300); 
        mal.addActionListener(this); 
        c.add(mal);
        
        //the general button
        gene = new JButton("General"); 
        gene.setFont(new Font("Arial", Font.BOLD, 30)); 
        gene.setSize(170, 35); 
        gene.setLocation(370, 400); 
        gene.addActionListener(this); 
        c.add(gene);

        setVisible(true);
    } 

   // method actionPerformed() 
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //will show the general SOP
        if(e.getSource() == gene){
            try 
            {
                br = new BufferedReader(new FileReader(gen));
                String rt = "";
                String text = "";
                while ((rt = br.readLine()) != null)
                {
                    text += rt + "\n";
                }
                JOptionPane.showMessageDialog(this , text);

            } 
            
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
            catch (IOException ex) 
            {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //opening India JFrame
        if (e.getSource() == ind)
            new IndiaSOP();
       
        
        //opening Malaysia JFrame
        if (e.getSource() == mal) 
            new MalaySOP();
    } 
} 

// Driver Code 
class Airport { 

   public static void main(String[] args) throws Exception , FileNotFoundException , IOException  { 
       MainPage f = new MainPage(); 
       f.setLocationRelativeTo(null);
       Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");  
           f.setIconImage(icon);  
   } 
}
