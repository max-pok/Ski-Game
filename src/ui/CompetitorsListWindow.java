package ui;

import game.competition.Competition;
import game.competition.Competitor;
import game.entities.sportsman.*;
import game.enums.CompetitorColor;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CompetitorsListWindow extends MainWindow {
    private JFrame mainFrame;
    private JFrame editFrame;
    private Competition competition;
    private Object[] columnNames = {"ID", "Age", "Name", "Max Speed", "Acceleration", "Color"};
    private JTable table;
    private Object[][] data;
    private int index = 0;
    private DefaultTableModel model;
    private JFrame mainWindow;
    private JLabel accelerationLabel;
    private JTextField accelerationTextField;
    private JLabel colorLabel;
    private JComboBox colorComboBox;
    private JPanel panel;
    private JButton updateButton;
    private MainWindow m;

    /**
     *  Constructor
     * @param main           - Main Game JFrame
     * @param competition    - competition object
     */
    public CompetitorsListWindow(JFrame main, Competition competition) {
        this.competition = competition;
        this.mainWindow = main;
        editFrame = new JFrame("Edit Competitor");
        editFrame.setLayout(new SpringLayout());
        editFrame.setSize(500, 70);
        editFrame.setLocation(350, 340);
        //editFrame.setVisible(true);
        panel = new JPanel();
        accelerationLabel = new JLabel("Acceleration: ");
        panel.add(accelerationLabel);

        accelerationTextField = new JTextField(10);
        panel.add(accelerationTextField);

        panel.add(new JLabel("\n"));

        colorLabel = new JLabel("Color: ");
        panel.add(colorLabel);

        colorComboBox = new JComboBox(new String[] { "Blue" , "Green", "Yellow", "Red","White", "Orange", "Black", "Pink"});
        panel.add(colorComboBox);

        updateButton = new JButton("Update");
        updateButton.setBackground(Color.getColor(colorComboBox.getSelectedItem().toString()));
        panel.add(updateButton);
        editFrame.add(panel);

        mainFrame = new JFrame("Competitors");
        mainFrame.setSize(500, 300);
        mainFrame.setLocation(350, 250);
        mainFrame.setVisible(true);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);

        data = new Object[competition.getMaxCompetitors()][6];
        for (int i = 0; i < this.competition.getActiveCompetitors().size(); i++) {
            data[index][0] = (String.valueOf(((Sportsman) competition.getActiveCompetitors().get(i)).getID()));
            data[index][1] = String.valueOf(((Sportsman) competition.getActiveCompetitors().get(i)).getAge());
            data[index][2] = String.valueOf(((Sportsman)competition.getActiveCompetitors().get(i)).getName());
            data[index][3] = String.valueOf(((Sportsman)competition.getActiveCompetitors().get(i)).getMaxSpeed());
            data[index][4] = String.valueOf(((Sportsman) competition.getActiveCompetitors().get(i)).getAcceleration());
            data[index++][5] = String.valueOf(((Sportsman) competition.getActiveCompetitors().get(i)).getColor());
            model.addRow(data[index-1]);
        }

        table.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(table);
        mainFrame.add(scrollPane);
        JButton cloneButton = new JButton("Clone");
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(arg0 -> editButtonActionListener());
        cloneButton.addActionListener(arg0 -> cloneButtonActionListener());
        JPanel southPanel = new JPanel();
        southPanel.add(cloneButton);
        southPanel.add(editButton);
        mainFrame.add(southPanel, BorderLayout.SOUTH);
    }

     /* ----Edit Button Listener---- */
    public void editButtonActionListener() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            editFrame.setVisible(true);
            updateButton.addActionListener(e -> updateButtonListener(competition.getActiveCompetitors().get(row),row));
        }
    }

    /* ----Clone Button Listener---- */
    public void cloneButtonActionListener() {
        int row = table.getSelectedRow();
        if (row >= 0 && row < this.competition.getActiveCompetitors().size()) {
            try {
                Competitor c = (Competitor) ((WinterSportsman) this.competition.getActiveCompetitors().get(row)).clone();
                ((Sportsman) c).generateID();
                this.competition.addCompetitor(c);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        data[index][0] = String.valueOf(((Sportsman) competition.getActiveCompetitors().get(competition.getActiveCompetitors().size() - 1)).getID());
        data[index][1] = String.valueOf(((Sportsman) competition.getActiveCompetitors().get(competition.getActiveCompetitors().size() - 1)).getAge());
        data[index][2] = String.valueOf(((Sportsman) competition.getActiveCompetitors().get(competition.getActiveCompetitors().size() - 1)).getName());
        data[index][3] = String.valueOf(((Sportsman) competition.getActiveCompetitors().get(competition.getActiveCompetitors().size() - 1)).getMaxSpeed());
        data[index][4] = String.valueOf(((Sportsman) competition.getActiveCompetitors().get(competition.getActiveCompetitors().size() - 1)).getAcceleration());
        data[index++][5] = String.valueOf(((Sportsman) competition.getActiveCompetitors().get(competition.getActiveCompetitors().size() - 1)).getColor());
        model.addRow(data[index - 1]);
        table.revalidate();
        table.repaint();
        mainWindow.repaint();
        mainWindow.revalidate();
    }

    /* ----Update Button Listener---- */
    public void updateButtonListener(Competitor competitor,int row) {
        new ColoredSportsman((IWinterSportsman) competitor).setColor(CompetitorColor.valueOf(colorComboBox.getSelectedItem().toString()));
        new SpeedySportsman((IWinterSportsman) competitor).setAcceleration(Double.parseDouble(accelerationTextField.getText()));
        model.setValueAt(String.valueOf(((Sportsman) competition.getActiveCompetitors().get(row)).getAcceleration()),row,4);
        model.setValueAt(String.valueOf(((Sportsman) competition.getActiveCompetitors().get(row)).getColor()),row,5);
        model.fireTableCellUpdated(row,4);
        model.fireTableCellUpdated(row,5);
        editFrame.dispose();
    }
}


