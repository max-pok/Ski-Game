package ui;

import game.competition.Competition;
import game.entities.sportsman.Sportsman;

import javax.swing.*;

public class ShowInfoWindow extends MainWindow {
    private JFrame mainFrame;
    private JTable infoTable;
    private String[] columnNames = { "ID", "Name" , "Speed" , "Max Speed" , " Location" , "Status" };
    Competition competition;

    public ShowInfoWindow(Competition competition) {
        this.competition = competition;
        mainFrame = new JFrame("Competitors Information");
        addInformationToTable();
        mainFrame.setSize(500, 300);
        mainFrame.setLocation(350, 250);
        mainFrame.setVisible(true);
    }

    /**
     * Adds the competitors information to the table
     */
    public void addInformationToTable() {
        String[][] data = new String[20][6];
        int index = 0;
        if (!competition.getFinishedCompetitors().isEmpty()) {
            for (int i = 0; i < competition.getFinishedCompetitors().size(); i ++) {
                data[index][0] = String.valueOf(((Sportsman) competition.getFinishedCompetitors().get(i)).getID());
                data[index][1] = ((Sportsman) competition.getFinishedCompetitors().get(i)).getName();
                data[index][2] = String.valueOf(((Sportsman)competition.getFinishedCompetitors().get(i)).getSpeed());
                data[index][3] = String.valueOf(((Sportsman)competition.getFinishedCompetitors().get(i)).getMaxSpeed());
                data[index][4] = String.valueOf(competition.getFinishedCompetitors().get(i).getLocation().getY());
                data[index++][5] = String.valueOf(((Sportsman) competition.getFinishedCompetitors().get(i)).getState());
            }
        }
        if (!competition.getActiveCompetitors().isEmpty()) {
            for (int i = 0; i < competition.getActiveCompetitors().size(); i++) {
                data[index][0] = String.valueOf(((Sportsman) competition.getActiveCompetitors().get(i)).getID());
                data[index][1] = ((Sportsman) competition.getActiveCompetitors().get(i)).getName();
                data[index][2] = String.valueOf(((Sportsman)competition.getActiveCompetitors().get(i)).getSpeed());
                data[index][3] = String.valueOf(((Sportsman)competition.getActiveCompetitors().get(i)).getMaxSpeed());
                data[index][4] = String.valueOf(competition.getActiveCompetitors().get(i).getLocation().getY());
                data[index++][5] = String.valueOf(((Sportsman) competition.getActiveCompetitors().get(i)).getState());
            }
        }
        if (!competition.getInactiveCompetitors().isEmpty()) {
            for (int i = 0; i < competition.getInactiveCompetitors().size(); i++) {
                data[index][0] = String.valueOf(((Sportsman) competition.getInactiveCompetitors().get(i)).getID());
                data[index][1] = ((Sportsman) competition.getInactiveCompetitors().get(i)).getName();
                data[index][2] = String.valueOf(((Sportsman)competition.getInactiveCompetitors().get(i)).getSpeed());
                data[index][3] = String.valueOf(((Sportsman)competition.getInactiveCompetitors().get(i)).getMaxSpeed());
                data[index][4] = String.valueOf(competition.getInactiveCompetitors().get(i).getLocation().getY());
                data[index++][5] = String.valueOf(((Sportsman) competition.getInactiveCompetitors().get(i)).getState());
            }
        }
        infoTable = new JTable(data, columnNames);
        infoTable.setDefaultEditor(Object.class, null);
        JScrollPane sp = new JScrollPane(infoTable);
        mainFrame.add(sp);
    }
}
