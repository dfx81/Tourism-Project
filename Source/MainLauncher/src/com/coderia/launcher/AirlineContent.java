package com.coderia.launcher;

import com.coderia.backend.Client;
import com.coderia.frontend.Content;
import com.coderia.airlines.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AirlineContent extends Content {
    File file;
    Scanner in;
    ArrayList<Airline> airlines;

    @Override
    public void buildUI(Client client) {
        airlines = new ArrayList<>();

        load();
        
        JLabel title = new JLabel("List of Airlines");
        JLabel data = new JLabel();

        Dimension d = new Dimension(480 - 24, 480 - 48);
        setSize(d);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
        
        update(data);

        JButton add = new JButton("ADD");
        JButton edit = new JButton("EDIT");
        JButton del = new JButton("DEL");

        JPanel panel = new JPanel();

        panel.add(add);
        panel.add(edit);
        panel.add(del);

        // Set the Content layout to Borderlayout
        this.setLayout(new BorderLayout());

        // Set the horizontalAlignment of the JLabel
        data.setHorizontalAlignment(JLabel.CENTER);

        // Add the JLabel to the Content
        this.add(title, BorderLayout.NORTH);
        this.add(new JScrollPane(data), BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);
    }
    
    private void load() {
        try {
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
            int max = in.nextInt();
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

                airlines.add(new TierAirline(name, rating, max, staff,
                        passengers, dist, domestic, tier, arr));
            } else if (type == 0) {
                airlines.add(new Airline(name, rating, type, max, staff,
                        passengers, dist, domestic));
            }

            in.nextLine();
        }
    }
    
    private void update(JLabel data) {
        String text = "<html><style>table, th, td {border: 1px solid black}</style><table>";
        text += "<tr><th>Name</th>";
        text += "<th>Rating</th>";
        text += "<th>Airline Type</th>";
        text += "<th>Max Passengers</th>";
        text += "<th>Distance</th>";
        text += "<th>Domestic Only</th>";
        text += "<th>Valid</th></tr>";

        for (int i = 0; i != airlines.size(); i++) {
            text += "<tr><td>" + airlines.get(i).getName() + "</td>";
            text += "<td>" + airlines.get(i).getRating() + "/5</td>";
            text += "<td>" + ((airlines.get(i).getType() == 0)? "Normal" : "Tiered") + "</td>";
            text += "<td>" + airlines.get(i).getMax() + " passengers</td>";
            text += "<td>" + airlines.get(i).getDistance() + "m</td>";
            text += "<td>" + airlines.get(i).isDomestic() + "</td>";
            text += "<td>" + airlines.get(i).checkValid() + "</td></tr>";
        }

        text += "</table></html>";

        data.setText(text);
    }
}
