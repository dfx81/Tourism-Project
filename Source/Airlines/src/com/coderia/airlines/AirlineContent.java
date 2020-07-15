package com.coderia.airlines;

import com.coderia.backend.Client;
import com.coderia.frontend.Content;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
//import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
//import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class AirlineContent extends Content {

    //File file;
    //Scanner in;
    ArrayList<Airline> airlines;
    Client in;

    JLabel data;

    private final String[] types = {"Normal Airline", "Tiered Airline"};
    private JComponent[] inputs;
    private JTextField n;
    private JSlider r;
    private JComboBox t;
    private JFormattedTextField s;
    private JFormattedTextField p;
    private JFormattedTextField d;
    private JCheckBox dom;

    @Override
    public void buildUI(Client client) {
        airlines = new ArrayList<>();
        in = client;

        load();
        
        JLabel title = new JLabel("List of Airlines");
        data = new JLabel();

        Dimension dim = new Dimension(480 - 24, 480 - 48);
        setSize(dim);
        setPreferredSize(dim);
        setMinimumSize(dim);
        setMaximumSize(dim);
        
        JButton sop = new JButton("SOP");
        JButton add = new JButton("ADD");
        JButton srch = new JButton("SEARCH");
        JButton edit = new JButton("EDIT");
        JButton del = new JButton("DEL");

        JPanel panel = new JPanel();
        
        panel.add(sop);
        panel.add(add);
        panel.add(srch);
        panel.add(edit);
        panel.add(del);

        // Set action listeners
        sop.addActionListener((ActionEvent e) -> showSOP());
        add.addActionListener((ActionEvent e) -> addRecord());
        srch.addActionListener((ActionEvent e) -> search());
        edit.addActionListener((ActionEvent e) -> editRecord());
        del.addActionListener((ActionEvent e) -> deleteRecord());

        // Set the Content layout to Borderlayout
        this.setLayout(new BorderLayout());

        // Set the horizontalAlignment of the JLabel
        data.setHorizontalAlignment(JLabel.CENTER);
        
        update(data, false);

        JScrollPane table = new JScrollPane(data);
        table.getVerticalScrollBar().setUnitIncrement(16);
        table.getHorizontalScrollBar().setUnitIncrement(16);

        // Add the JLabel to the Content
        this.add(title, BorderLayout.NORTH);
        this.add(table, BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);
    }

    private void load() {
        /*try {
            file = new File("res/data.txt");
            in = new Scanner(file);
        } catch (Exception err) {
            err.printStackTrace();
        }

        // read
        while (in.hasNext()) {
            String name = in.nextLine();
            int rating = in.nextInt();
            int type = in.nextInt();
            int staff = in.nextInt();
            int passengers = in.nextInt();
            double dist = in.nextDouble();
            boolean domestic = in.nextBoolean();

            if (type == 1) {
                int tier = in.nextInt();

                int[] arr = new int[in.nextInt()];

                for (int i = 0; i != arr.length; i++) {
                    arr[i] = in.nextInt();
                }

                airlines.add(new TierAirline(name, rating, staff,
                        passengers, dist, domestic, tier, arr));
            } else if (type == 0) {
                airlines.add(new Airline(name, rating, type, staff,
                        passengers, dist, domestic));
            }

            in.nextLine();
        }*/

        in.sendRequest("/airlines-r");
        String req = in.getResponse();

        while (!req.equals("/eof")) {
            String name = req;
            System.out.println(name);
            int rating = Integer.parseInt(in.getResponse());
            System.out.println(rating);
            int type = Integer.parseInt(in.getResponse());
            System.out.println(type);
            int staff = Integer.parseInt(in.getResponse());
            System.out.println(staff);
            int passengers = Integer.parseInt(in.getResponse());
            System.out.println(passengers);
            double dist = Double.parseDouble(in.getResponse());
            System.out.println(dist);
            boolean domestic = Boolean.parseBoolean(in.getResponse());
            System.out.println(domestic);

            if (type == 1) {
                int tier = Integer.parseInt(in.getResponse());
                System.out.println(tier);

                int[] arr = new int[Integer.parseInt(in.getResponse())];
                System.out.println(arr.length);

                for (int i = 0; i != arr.length; i++) {
                    arr[i] = Integer.parseInt(in.getResponse());
                    System.out.println(arr[i]);
                }

                airlines.add(new TierAirline(name, rating, staff,
                        passengers, dist, domestic, tier, arr));
            } else if (type == 0) {
                airlines.add(new Airline(name, rating, type, staff,
                        passengers, dist, domestic));
            }
            
            req = in.getResponse();
        }
    }

    private void update(JLabel data, boolean overwrite) {
        String text = "<html><style>table, th, td {border: 1px solid black}</style><table>";
        text += "<tr><th>Name</th>";
        text += "<th>Rating</th>";
        text += "<th>Airline Type</th>";
        text += "<th>Max Passengers</th>";
        text += "<th>Distance</th>";
        text += "<th>Domestic Only</th>";
        text += "<th>Valid</th></tr>";

        String newData = "";

        for (int i = 0; i != airlines.size(); i++) {
            text += "<tr><td>" + airlines.get(i).getName() + "</td>";
            newData += airlines.get(i).getName() + "\n";
            text += "<td>" + airlines.get(i).getRating() + "/5</td>";
            newData += airlines.get(i).getRating() + " ";
            text += "<td>" + ((airlines.get(i).getType() == 0) ? "Normal" : "Tiered") + "</td>";
            newData += airlines.get(i).getType() + " ";
            newData += airlines.get(i).getStaff() + " ";
            newData += airlines.get(i).getPassenger() + " ";
            text += "<td>" + airlines.get(i).getMax() + " passengers</td>";
            text += "<td>" + airlines.get(i).getDistance() + "m</td>";
            newData += airlines.get(i).getDistance() + " ";
            text += "<td>" + airlines.get(i).isDomestic() + "</td>";
            newData += airlines.get(i).isDomestic() + " ";
            text += "<td>" + airlines.get(i).checkValid() + "</td></tr>";

            if (airlines.get(i).getType() == 1) {
                newData += ((TierAirline) airlines.get(i)).getTier() + " ";
                newData += ((TierAirline) airlines.get(i)).getPassengerPerTier().length + " ";

                for (int j : ((TierAirline) airlines.get(i)).getPassengerPerTier()) {
                    newData += j + " ";
                }
            }

            newData += "\n";
        }

        text += "</table></html>";

        data.setText(text);
        
        if (overwrite) {
            System.out.println(newData);
            in.sendRequest(newData);
            in.sendRequest("/airlines-w");
        }
    }

    private void addRecord() {
        String newName;
        int newRating;
        int newStaff;
        int newPassenger;
        double newDist;
        boolean newDomestic;
        int[] newTierPassengers;

        n = new JTextField();
        r = new JSlider(JSlider.HORIZONTAL, 0, 5, 3);
        r.setMajorTickSpacing(1);
        r.setPaintTicks(true);
        r.setPaintLabels(true);
        t = new JComboBox(types);
        s = new JFormattedTextField(NumberFormat.getIntegerInstance());
        p = new JFormattedTextField(NumberFormat.getIntegerInstance());
        d = new JFormattedTextField(NumberFormat.getNumberInstance());
        dom = new JCheckBox("Domestic flights only");

        inputs = new JComponent[]{
            new JLabel("Airline name: "), n, new JLabel("Airline rating: "), r,
            new JLabel("Airline type: "), t, new JLabel("Number of staff: "), s,
            new JLabel("Minimum distance (m): "), d, dom
        };

        int result = JOptionPane.showConfirmDialog(null, inputs, "New Record", JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            if (n.getText().equals("") || s.getText().equals("") || d.getText().equals("")) {
                return;
            }
            newName = n.getText();
            newRating = r.getValue();
            newStaff = Integer.parseInt(s.getText());
            newDist = Double.parseDouble(d.getText());
            newDomestic = dom.isSelected();

            if (t.getSelectedIndex() == 1) {
                JFormattedTextField nTier = new JFormattedTextField(NumberFormat.getIntegerInstance());
                inputs = new JComponent[]{
                    new JLabel("Enter the number of tiers: "),
                    nTier
                };

                result = JOptionPane.showConfirmDialog(null, inputs, "New Tiered Airline", JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION && !(nTier.getText().equals(""))) {
                    int num = Integer.parseInt(nTier.getText());

                    inputs = new JComponent[num * 2];
                    newTierPassengers = new int[num];

                    for (int i = 0; i != inputs.length; i += 2) {
                        inputs[i] = new JLabel("Tier " + (i / 2) + " max passengers: ");
                        inputs[i + 1] = new JFormattedTextField(NumberFormat.getIntegerInstance());
                    }

                    result = JOptionPane.showConfirmDialog(null, inputs, "New Tiered Airline", JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        for (int i = 1; i < inputs.length; i += 2) {
                            if (!((JTextField) inputs[i]).getText().equals("")) {
                                newTierPassengers[i / 2] = Integer.parseInt(((JTextField) inputs[i]).getText());
                            } else {
                                return;
                            }
                        }
                    }

                    newPassenger = 0;

                    for (int i : newTierPassengers) {
                        newPassenger += i;
                    }

                    airlines.add(new TierAirline(newName, newRating, newStaff,
                            newPassenger, newDist, newDomestic, 0, newTierPassengers));
                }
            } else {
                inputs = new JComponent[]{
                    new JLabel("Number of passenger: "), p
                };

                result = JOptionPane.showConfirmDialog(null, inputs, "New Airline", JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION && !(p.getText().equals(""))) {
                    newPassenger = Integer.parseInt(p.getText());
                } else {
                    return;
                }

                airlines.add(new Airline(newName, newRating, newStaff, newPassenger, newDist, newDomestic));
            }
        }

        update(data, true);
    }

    private void editRecord() {
        String newName;
        int newRating;
        int newStaff;
        int newPassenger;
        double newDist;
        boolean newDomestic;
        int[] newTierPassengers;

        String[] names = new String[airlines.size()];

        for (int i = 0; i != names.length; i++) {
            names[i] = airlines.get(i).getName();
        }

        JComboBox list = new JComboBox(names);
        int result = JOptionPane.showConfirmDialog(null, new JComponent[]{
            new JLabel("Select the Airline to edit: "), list}, "Edit Record",
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION && airlines.size() > 0) {
            int select = list.getSelectedIndex();
            n = new JTextField(airlines.get(select).getName());
            r = new JSlider(JSlider.HORIZONTAL, 0, 5, airlines.get(select).getRating());
            r.setMajorTickSpacing(1);
            r.setPaintTicks(true);
            r.setPaintLabels(true);
            s = new JFormattedTextField(NumberFormat.getIntegerInstance());
            s.setText(String.valueOf(airlines.get(select).getStaff()));
            d = new JFormattedTextField(NumberFormat.getNumberInstance());
            d.setText(String.valueOf(airlines.get(select).getDistance()));
            dom = new JCheckBox("Domestic flights only");
            dom.setSelected(airlines.get(select).isDomestic());

            if (airlines.get(select).getType() == 0) {
                p = new JFormattedTextField(NumberFormat.getIntegerInstance());
                p.setText(String.valueOf(airlines.get(select).getPassenger()));

                inputs = new JComponent[]{
                    new JLabel("New name: "), n, new JLabel("New rating: "),
                    r, new JLabel("New number of staff: "), s,
                    new JLabel("New number of passengers: "), p,
                    new JLabel("New minimum distance (m): "), d, dom
                };

                result = JOptionPane.showConfirmDialog(null, inputs, "Edit Airline", JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION && (!n.getText().equals("")
                        || !s.getText().equals("") || !d.getText().equals("")
                        || !p.getText().equals(""))) {
                    newName = n.getText();
                    newRating = r.getValue();
                    newStaff = Integer.parseInt(s.getText());
                    newDist = Double.parseDouble(d.getText());
                    newDomestic = dom.isSelected();
                    newPassenger = Integer.parseInt(p.getText());

                    airlines.set(select, new Airline(newName, newRating,
                            newStaff, newPassenger, newDist, newDomestic));
                }
            } else {
                JFormattedTextField[] tierList = new JFormattedTextField[((TierAirline) airlines
                        .get(select))
                        .getPassengerPerTier().length];

                for (int i = 0; i != tierList.length; i++) {
                    tierList[i] = new JFormattedTextField(NumberFormat.getIntegerInstance());
                    tierList[i].setText(String.valueOf(((TierAirline) airlines
                            .get(select)).getPassengerPerTier()[i]));
                }

                inputs = new JComponent[]{
                    new JLabel("New name: "), n, new JLabel("New rating: "), r,
                    new JLabel("New number of staff: "), s, new JLabel("New minimum distance (m): "),
                    d, dom
                };

                result = JOptionPane.showConfirmDialog(null, inputs,
                        "Edit Airline", JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION && (!n.getText().equals("")
                        || !s.getText().equals("") || !d.getText().equals(""))) {
                    newName = n.getText();
                    newRating = r.getValue();
                    newStaff = Integer.parseInt(s.getText());
                    newDist = Double.parseDouble(d.getText());
                    newDomestic = dom.isSelected();
                    newTierPassengers = new int[tierList.length];

                    inputs = new JComponent[tierList.length * 2];

                    for (int i = 0; i != inputs.length; i += 2) {
                        inputs[i] = new JLabel("Tier " + (i / 2) + " passengers: ");
                        inputs[i + 1] = tierList[i / 2];
                    }

                    result = JOptionPane.showConfirmDialog(null, inputs,
                            "New Airline", JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        for (int i = 0; i != newTierPassengers.length; i++) {
                            if (!tierList[i].getText().equals("")) {
                                newTierPassengers[i] = Integer.parseInt(
                                        tierList[i].getText());
                            } else {
                                return;
                            }
                        }
                    }

                    newPassenger = 0;

                    for (int i : newTierPassengers) {
                        newPassenger += i;
                    }

                    airlines.set(select, new TierAirline(newName, newRating,
                            newStaff, newPassenger, newDist, newDomestic, 0, newTierPassengers));
                }
            }

            update(data, true);
        }
    }

    private void deleteRecord() {
        String[] names = new String[airlines.size()];

        for (int i = 0; i != names.length; i++) {
            names[i] = airlines.get(i).getName();
        }

        JComboBox list = new JComboBox(names);

        int result = JOptionPane.showConfirmDialog(null, new JComponent[]{
            new JLabel("Select the Airline to delete: "), list}, "Delete Record",
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION && airlines.size() > 0) {
            airlines.remove(list.getSelectedIndex());
        }

        update(data, true);
    }
    
    private void showSOP() {
        SOP ssop = new SOP();
        JOptionPane.showMessageDialog(null, new JLabel("<html>"
                + "<h1>SOP</h1>"
                + "<ul>"
                + "<li>Maximum people on board: " + ssop.getMaxOnBoard() + "</li>"
                + "<li>Minimum distance per person (m): " + ssop.getMinDistance()
                + "</li></ul></html>"));
    }
    
    private void search() {
        JTextField tf = new JTextField();
        int result = JOptionPane.showConfirmDialog(null, new JComponent[]{
            new JLabel("Enter name of airline: "), tf}, "Search",
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            boolean found = false;
            for (Airline a : airlines) {
                if (a.getName().equals(tf.getText())) {
                    JOptionPane.showMessageDialog(null, new JLabel("<html>"
                    + "<h1>Found!</h1>"
                    + "<p>Name: " + a.getName() + "<br/>"
                    + "People on board: " + a.getMax() + "<br/>"
                    + "Distance per person (m): " + a.getDistance()
                    + "</p></html>"));
                    found = true;
                }
            }
            
            if (!found) {
                JOptionPane.showMessageDialog(null, "Airline not found");
            }
        }
    }
}
