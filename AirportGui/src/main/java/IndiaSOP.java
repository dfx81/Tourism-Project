import java.io.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

    class IndiaSOP extends JFrame implements ActionListener {
    private JLabel bcolor1;
    private JLabel bcolor;
    private JLabel image;
    private JLabel image2;
    private JLabel image3;
    private JLabel title;
    private Container d;
    private JTextArea ssop;
    private JButton add; 
    private JButton del;
    private JButton sub;
    private JButton back;
    private JTextField tget;
    private JScrollPane pane;
    private JLabel inst;
    private JLabel ainst;
    
    File indgen = new File ("C:\\Users\\thebestp9\\Desktop\\Tourism-Project\\Tourism-Project\\AirportGui\\src\\main\\java\\SopIndia.txt");
    FileWriter wr;
    PrintWriter pr;
    BufferedReader br;
    
public IndiaSOP(){
    
    /*setTitle("SOP FOR INDIA"); 
    setBounds(300, 200, 900, 600); 
    setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
    setResizable(false);*/
    
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
    
    title = new JLabel("India SOP"); 
    title.setFont(new Font("Arial", Font.PLAIN, 30));
    title.setSize(300, 30); 
    title.setLocation(385, 30); 
    title.setForeground(new Color(0, 0, 0));
    d.add(title);
    
    inst = new JLabel("Pick Your Choice"); 
    inst.setFont(new Font("Arial", Font.PLAIN, 48)); 
    inst.setSize(500, 100); 
    inst.setLocation(275, 100); 
    d.add(inst);
    
    ainst = new JLabel("Add A New SOP"); 
    ainst.setFont(new Font("Arial", Font.PLAIN, 48)); 
    ainst.setSize(500, 100); 
    ainst.setLocation(275, 100); 
    d.add(ainst);
    ainst.setVisible(false);
    
    ssop = new JTextArea(); 
    ssop.setFont(new Font("Arial", Font.PLAIN, 15)); 
    ssop.setSize(400, 150); 
    ssop.setLocation(240, 400); 
    
    pane = new JScrollPane(ssop);
    pane.setBounds(240, 400, 400, 150);
    pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    d.add(pane); 
    
    add = new JButton("Add"); 
    add.setFont(new Font("Arial", Font.BOLD, 30)); 
    add.setSize(170, 35); 
    add.setLocation(180, 300); 
    add.addActionListener(this); 
    d.add(add);
        
        //the malaysia button
    del = new JButton("Show SOP"); 
    del.setFont(new Font("Arial", Font.BOLD, 30)); 
    del.setSize(200, 35); 
    del.setLocation(560, 300); 
    del.addActionListener(this); 
    d.add(del);
    
    sub = new JButton("Submit"); 
    sub.setFont(new Font("Arial", Font.BOLD, 30)); 
    sub.setSize(170, 35); 
    sub.setLocation(560, 300); 
    sub.addActionListener(this); 
    d.add(sub);
    sub.setVisible(false);
    
    back = new JButton("Back"); 
    back.setFont(new Font("Arial", Font.BOLD, 30)); 
    back.setSize(170, 35); 
    back.setLocation(180, 300); 
    back.addActionListener(this); 
    d.add(back);
    back.setVisible(false);
    
    tget = new JTextField(); 
    tget.setFont(new Font("Arial", Font.PLAIN, 20)); 
    tget.setSize(500, 30); 
    tget.setLocation(200, 225); 
    d.add(tget); 
    tget.setVisible(false);
    
    //setVisible(true);
    //setLocationRelativeTo(null);

}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == del){
        try 
            {
                br = new BufferedReader(new FileReader(indgen));
                String rtx = "";
                String text = "";
                while ((rtx = br.readLine()) != null)
                {
                    text += rtx + "\n";
                    System.out.println(rtx);
                }
                ssop.setText(text);
                
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
            
        if (e.getSource() == add) {
            add.setVisible(false);
            del.setVisible(false);
            sub.setVisible(true);
            tget.setVisible(true);
            back.setVisible(true);
            inst.setVisible(false);
            ainst.setVisible(true);
        }
        
        if(e.getSource() == sub){
            try
            {
                String save = ssop.getText();
                pr = new PrintWriter (new File("C:\\Users\\thebestp9\\Desktop\\Tourism-Project\\Tourism-Project\\AirportGui\\src\\main\\java\\SopIndia.txt"));
                pr.write(save);
                pr.close();
            }
            catch (Exception ea) 
        {
            JOptionPane.showMessageDialog(null, e + "");
        }
        
        }
        
        if (e.getSource() == back) {
            add.setVisible(true);
            del.setVisible(true);
            sub.setVisible(false);
            tget.setVisible(false);
            back.setVisible(false);
            inst.setVisible(true);
            ainst.setVisible(false);
        }
        
    }
}



