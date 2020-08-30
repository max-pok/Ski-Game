package ui;

import game.GameEngine;
import game.arena.ArenaFactory;
import game.arena.IArena;
import game.competition.Competition;
import game.competition.CompetitionBuilder;
import game.competition.Competitor;
import game.entities.sportsman.*;
import game.enums.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainWindow implements Observer {
    private Competitor competitor;
    private IArena arena;
    private Competition competition;
    private ClassLoader classLoader = MainWindow.class.getClassLoader();
    private JFrame mainFrame;
    private JPanel toolBar;
    private JPanel wallpaperPanel;
    private JPanel gamePanel;
    private JTextField arenaLengthTextField;
    private JComboBox arenaTypeComboBox;
    private JLabel arenaTypeLabel;
    private JComboBox snowConditionComboBox;
    private JComboBox weatherConditionComboBox;
    private JButton buildArenaButton;
    private JComboBox chooseCompetitionComboBox;
    private JTextField maxCompetitorsTextField;
    private JComboBox disciplineComboBox;
    private JComboBox leagueComboBox;
    private JComboBox genderComboBox;
    private JButton createCompetitionButton;
    private JButton createDefaultCompetitionButton;
    private JTextField nameTextField;
    private JTextField ageTextField;
    private JTextField maxSpeedTextField;
    private JTextField accelerationTextField;
    private JButton addCompetitorButton;
    private JButton cloneOReditCompetitorButton;
    private JButton startCompetitionButton;
    private JButton showInfoButton;
    private JLabel addCompetitorLabel;
    private JLabel createCompetitionLabel;
    private JLabel buildArenaLabel;
    private String competitorType;
    private Boolean compete;
    private JComboBox colorComboBox;

    public MainWindow() {
        //----Arena Length Text Field----//
        arenaLengthTextField = new JTextField("700");
        //----Arena Type Field----//
        arenaTypeLabel = new JLabel("Arena Type");
        //----Arena Type ComboBox Init----//
        arenaTypeComboBox = new JComboBox(new String[]{"Winter", "Summer"});
        //----Snow Surface ComboBox Init----//
        snowConditionComboBox = new JComboBox(new String[]{"Powder", "Crud", "Ice"});
        //----Weather Condition ComboBox Init----//
        weatherConditionComboBox = new JComboBox(new String[]{"Sunny", "Cloudy", "Stormy"});
        //----Build Arena Field----//
        buildArenaLabel = new JLabel(" BUILD ARENA");
        buildArenaLabel.setForeground(Color.blue);
        buildArenaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //----Build Arena Button Init----//
        buildArenaButton = new JButton("Build Arena");
        //----Create Competition Label----//
        createCompetitionLabel = new JLabel(" CREATE COMPETITION");
        createCompetitionLabel.setForeground(Color.blue);
        createCompetitionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //----Choose Competition ComboBox Init----//
        chooseCompetitionComboBox = new JComboBox(new String[]{"Ski", "Snowboard"});
        //----Max Competitors Number Init----//
        maxCompetitorsTextField = new JTextField("10");
        //----Discipline ComboBox Init----//
        disciplineComboBox = new JComboBox(new String[]{"Slalom", "Giant_Slalom", "DownHill", "Freestyle"});
        //----League ComboBox Init----//
        leagueComboBox = new JComboBox(new String[]{"Junior", "Adult", "Senior"});
        //----Gender ComboBox Init----//
        genderComboBox = new JComboBox(new String[]{"Male", "Female"});
        //----Create Competition Button Init----//
        createCompetitionButton = new JButton("Create Competition");
        //----Create Default Compotition Button Init----//
        createDefaultCompetitionButton = new JButton("Create Default Competition");
        //----Add Competitor Label Init----//
        addCompetitorLabel = new JLabel(" ADD COMPETITOR");
        addCompetitorLabel.setForeground(Color.blue);
        addCompetitorLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //----Name Text Field Init----//
        nameTextField = new JTextField();
        //----Age Text Field Init----//
        ageTextField = new JTextField();
        //----Max Speed Text Field Init----//
        maxSpeedTextField = new JTextField();
        //----Acceleration Text Field Init-----//
        accelerationTextField = new JTextField();
        //----Color Button Init----//
        colorComboBox = new JComboBox(new String[] { "Blue" , "Green", "Yellow", "Red","White", "Orange", "Black", "Pink"});
        //----Add Competitor Button Init----//
        addCompetitorButton = new JButton("Add Competitor");
        //----Clone Competitor Button Init----//
        cloneOReditCompetitorButton = new JButton("Clone/Edit");
        //----Start Competition Button Init----//
        startCompetitionButton = new JButton("Start Competition");
        //----showInfoButton Init----//
        showInfoButton = new JButton("Show Info");
        //----toolBar Init----//
        toolBar = new JPanel();
        toolBar.setLayout(new GridLayout(44, 2));
        toolBar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        toolBar.setPreferredSize(new Dimension(200, 780));
    }

    /**
     * Adds all the buttons, labels, combo boxes and text field to the toolbar
     * Adds the toolbar to the frame
     */
    public void initToolbar() {
        toolBar.add(buildArenaLabel);
        toolBar.add(new JLabel(" Arena Length"));
        toolBar.add(arenaLengthTextField);
        toolBar.add(arenaTypeLabel);
        toolBar.add(arenaTypeComboBox);
        toolBar.add(new JLabel(" Snow Surface"));
        toolBar.add(snowConditionComboBox);
        toolBar.add(new JLabel(" Weather Condition"));
        toolBar.add(weatherConditionComboBox);
        toolBar.add(buildArenaButton);
        toolBar.add(new JLabel(""));
        toolBar.add(new JSeparator(JSeparator.HORIZONTAL));
        toolBar.add(createCompetitionLabel);
        toolBar.add(new JLabel(" Choose Competition"));
        toolBar.add(chooseCompetitionComboBox);
        toolBar.add(new JLabel(" Max Competitors Number"));
        toolBar.add(maxCompetitorsTextField);
        toolBar.add(new JLabel(" Discipline"));
        toolBar.add(disciplineComboBox);
        toolBar.add(new JLabel(" League"));
        toolBar.add(leagueComboBox);
        toolBar.add(new JLabel(" Gender"));
        toolBar.add(genderComboBox);
        toolBar.add(createCompetitionButton);
        toolBar.add(createDefaultCompetitionButton);
        toolBar.add(new JLabel(""));
        toolBar.add(new JSeparator(JSeparator.HORIZONTAL));
        toolBar.add(addCompetitorLabel);
        toolBar.add(new JLabel(" Name"));
        toolBar.add(nameTextField);
        toolBar.add(new JLabel(" Age"));
        toolBar.add(ageTextField);
        toolBar.add(new JLabel(" Max Speed"));
        toolBar.add(maxSpeedTextField);
        toolBar.add(new JLabel(" Acceleration"));
        toolBar.add(accelerationTextField);
        toolBar.add(new JLabel(" Color"));
        toolBar.add(colorComboBox);
        toolBar.add(addCompetitorButton);
        toolBar.add(cloneOReditCompetitorButton);
        toolBar.add(new JLabel(""));
        toolBar.add(new JSeparator(JSeparator.HORIZONTAL));
        toolBar.add(startCompetitionButton);
        toolBar.add(showInfoButton);
        toolBar.setVisible(true);
        mainFrame = new JFrame(" Competition");
        mainFrame.setSize(new Dimension(1000,780));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.add(toolBar, BorderLayout.EAST);
        mainFrame.revalidate();
        mainFrame.repaint();
        initListeners();
    }

    /**
     * Creates all the ActionListeners
     */
    public void initListeners() {

        //----Arena Length Text Field Listener----//
        ((AbstractDocument) arenaLengthTextField.getDocument()).setDocumentFilter(new DocumentFilter() {
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if (!matcher.matches()) {
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        //----Max Competitors Text Field Listener----//
        ((AbstractDocument) maxCompetitorsTextField.getDocument()).setDocumentFilter(new DocumentFilter() {
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if (!matcher.matches()) {
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        //----Age Text Field Listener----//
        ((AbstractDocument) ageTextField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text,
                                     AttributeSet attr) throws BadLocationException {
                super.insertString(fb, offset, revise(text), attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text,
                                AttributeSet attrs) throws BadLocationException {
                super.replace(fb, offset, length, revise(text), attrs);
            }

            private String revise(String text) {
                StringBuilder builder = new StringBuilder(text);
                int index = 0;
                while (index < builder.length()) {
                    if (accept(builder.charAt(index))) {
                        index++;
                    } else {
                        builder.deleteCharAt(index);
                    }
                }
                return builder.toString();
            }
            public boolean accept(final char c) {
                return Character.isDigit(c) || c == '.';
            }
        });

        //----Max Speed Text Field Listener----//
        ((AbstractDocument) maxSpeedTextField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text,
                                     AttributeSet attr) throws BadLocationException {
                super.insertString(fb, offset, revise(text), attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text,
                                AttributeSet attrs) throws BadLocationException {
                super.replace(fb, offset, length, revise(text), attrs);
            }

            private String revise(String text) {
                StringBuilder builder = new StringBuilder(text);
                int index = 0;
                while (index < builder.length()) {
                    if (accept(builder.charAt(index))) {
                        index++;
                    } else {
                        builder.deleteCharAt(index);
                    }
                }
                return builder.toString();
            }
            public boolean accept(final char c) {
                return Character.isDigit(c) || c == '.';
            }
        });

        //----Acceleration Text Field Listener----//
        ((AbstractDocument) accelerationTextField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text,
                                     AttributeSet attr) throws BadLocationException {
                super.insertString(fb, offset, revise(text), attr);
            }
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text,
                                AttributeSet attrs) throws BadLocationException {
                super.replace(fb, offset, length, revise(text), attrs);
            }
            private String revise(String text) {
                StringBuilder builder = new StringBuilder(text);
                int index = 0;
                while (index < builder.length()) {
                    if (accept(builder.charAt(index))) {
                        index++;
                    } else {
                        builder.deleteCharAt(index);
                    }
                }
                return builder.toString();
            }
            public boolean accept(final char c) {
                return Character.isDigit(c) || c == '.';
            }
        });

        //----Build Arena Button Listener----//
        buildArenaButton.addActionListener(e -> {
            if (arenaLengthTextField.getText().equals("") || snowConditionComboBox.getSelectedItem().equals("") || weatherConditionComboBox.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Missing Values.", "Message", 0);
            } else if (!(Double.parseDouble(arenaLengthTextField.getText()) >= 300 && Double.parseDouble(arenaLengthTextField.getText()) <= 900)) {
                JOptionPane.showMessageDialog(null, "Arena length must be between 700 and 900.", "Message", 0);
            } else {
                arena = new ArenaFactory().getArena(arenaTypeComboBox.getSelectedItem().toString(),Double.parseDouble(arenaLengthTextField.getText()),
                        SnowSurface.valueOf(snowConditionComboBox.getSelectedItem().toString().toUpperCase()),
                        WeatherCondition.valueOf(weatherConditionComboBox.getSelectedItem().toString().toUpperCase()));

                competition = null;
                competitor = null;
                System.out.println(arena + " Created");
                if (wallpaperPanel != null) {
                    mainFrame.remove(wallpaperPanel);
                }
                updateWallpaper(weatherConditionComboBox.getSelectedItem().toString() + ".jpg");
            }
        });

        //----Create Competition Button----//
        createCompetitionButton.addActionListener(e -> {
            if (arena == null) {
                JOptionPane.showMessageDialog(null, "First create an arena.", "Message", 0);
            } else if (chooseCompetitionComboBox.getSelectedItem().equals("") || maxCompetitorsTextField.getText().equals("") ||
                    disciplineComboBox.getSelectedItem().equals("") || leagueComboBox.getSelectedItem().equals("") || genderComboBox.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Missing Values.", "Message", 0);
            } else if (Integer.parseInt(maxCompetitorsTextField.getText()) < 1 || Integer.parseInt(maxCompetitorsTextField.getText()) > 20) {
                JOptionPane.showMessageDialog(null, "Max competitors must be between 1 and 20.", "Message", 0);
            } else {
                try {
                    competition = null;
                    competitor = null; /* disables the clone option */
                    Class competitionClass = classLoader.loadClass("game.competition." + chooseCompetitionComboBox.getSelectedItem().toString() + "Competition");
                    Class[] cls = new Class[]{ IArena.class, int.class, Discipline.class, League.class, Gender.class };
                    Constructor constructor = competitionClass.getConstructor(cls);
                    competition = (Competition) constructor.newInstance(arena, Integer.parseInt(maxCompetitorsTextField.getText()),
                            Discipline.valueOf(disciplineComboBox.getSelectedItem().toString().toUpperCase()),
                            League.valueOf(leagueComboBox.getSelectedItem().toString().toUpperCase()),
                            Gender.valueOf(genderComboBox.getSelectedItem().toString().toUpperCase()));

                    competitorType = chooseCompetitionComboBox.getSelectedItem().toString() + genderComboBox.getSelectedItem().toString() + ".png";
                    compete = false;
                    if (gamePanel != null) {
                        wallpaperPanel.remove(gamePanel);
                        mainFrame.repaint();
                        mainFrame.revalidate();
                    }
                    System.out.println("Competition Created");
                } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //----Create Default Competition Listener----//
        createDefaultCompetitionButton.addActionListener(e -> {
            competition = null;
            competitor = null; /* disables the clone option */
            compete = false;
            CompetitionBuilder cb = new CompetitionBuilder();
            competition = cb.DefaultCompetitionBuilder(10);
            arena = cb.getArena();
            competitor = cb.getCompetitor();
            updateWallpaper("Sunny.jpg");
            updateGamePanel("SkiMale.png");
            gamePanel.repaint();
            gamePanel.revalidate();
            System.out.println("Default Competition Created");
        });

        //----Add Competitors Button----//
        addCompetitorButton.addActionListener(e -> {
            if (competition == null || arena == null) {
                JOptionPane.showMessageDialog(null, "First create an arena and a competition.", "Message", 0);
            } else if (compete) {
                JOptionPane.showMessageDialog(null, "Create a new competition.", "Message", 0);
            } else if (nameTextField.getText().equals("") || ageTextField.getText().equals("") || maxSpeedTextField.getText().equals("")
                    || accelerationTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Missing Values.", "Message", 0);

            } else {
                try {
                    Class competitionClass = classLoader.loadClass("game.entities.sportsman." + chooseCompetitionComboBox.getSelectedItem().toString() + "er");
                    Class[] cls = new Class[]{
                            String.class, double.class, Gender.class, double.class, double.class, Discipline.class};
                    Constructor constructor = competitionClass.getConstructor(cls);
                    competitor = (Competitor) constructor.newInstance(nameTextField.getText(), Double.parseDouble(ageTextField.getText()),
                            Gender.valueOf(genderComboBox.getSelectedItem().toString().toUpperCase()), Double.parseDouble(accelerationTextField.getText()),
                            Double.parseDouble(maxSpeedTextField.getText()), Discipline.valueOf(disciplineComboBox.getSelectedItem().toString().toUpperCase()));

                    ((Sportsman) competitor).setColor(CompetitorColor.valueOf(colorComboBox.getSelectedItem().toString()));
                    competition.addCompetitor(competitor);

                    if (competition.getActiveCompetitors().size() > 10) { /* Changes the size of the screen based on the competitors amount */
                        mainFrame.setSize(1500, mainFrame.getHeight());
                    }
                    System.out.println(competitor + " - Added");
                    updateGamePanel(competitorType);
                } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //----Clone/Edit Competitor Button Listener----//
        cloneOReditCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (competitor == null) {
                    JOptionPane.showMessageDialog(null, "No competitors to clone, first add a competitor then try again.", "Message", 0);
                } else if (compete) {
                    JOptionPane.showMessageDialog(null, "Create a new competition.", "Message", 0);
                } else {
                    new CompetitorsListWindow(mainFrame,competition);
                }
            }
        });


        //----Start Competition Button Listener----//
        startCompetitionButton.addActionListener(e -> {
            if (compete) {
                JOptionPane.showMessageDialog(null, "Create a new competition.", "Message", 0);
            } else if (competition == null) {
                JOptionPane.showMessageDialog(null, "First add an arena, competition and competitors.", "Message", 0);
            } else if (competitor == null) {
                JOptionPane.showMessageDialog(null, "There are no competitors at the competition, add competitors then try again.", "Message", 0);
            } else {
                for (int i = 0 ; i < competition.getActiveCompetitors().size() ; i++) { /* adds GUI observer for each competitor */
                    ((Sportsman) competition.getActiveCompetitors().get(i)).addObserver(this);
                }
                compete = true;
                GameEngine.getInstance().startRace(competition);
            }
        });

        //----Show Info Button----//
        showInfoButton.addActionListener(e -> {
            if (competition != null) {
                new ShowInfoWindow(competition);
            }
        });

    }

    public void updateGamePanel(String imageName) {
        gamePanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                setPreferredSize(wallpaperPanel.getPreferredSize());
                ImageIcon img = new ImageIcon(imageName);
                Image i = img.getImage();
                ImageIcon fall = new ImageIcon("dead.png");
                Image i2 = fall.getImage();
                ImageIcon injured = new ImageIcon("injured.png");
                Image i3 = injured.getImage();
                g.setFont(new Font("TimesRoman", Font.PLAIN, 11));
                if (!competition.getActiveCompetitors().isEmpty()) {
                    for (Competitor k : competition.getActiveCompetitors()) {
                        g.setColor(Color.getColor(String.valueOf(((Sportsman) k).getColor())));
                        g.drawImage(i, (int) k.getLocation().getX(), (int) k.getLocation().getY(), 45, 45, null);
                        if (((Sportsman) k).getState().toString().equals("Injured")) {
                            g.drawString(((Sportsman) k).getName() + "-Injured", (int) k.getLocation().getX(), (int) k.getLocation().getY());
                            g.drawImage(i3, (int) k.getLocation().getX(), (int) k.getLocation().getY(), 20, 23, null);
                        } else {
                            g.drawString(((Sportsman) k).getName(), (int) k.getLocation().getX(), (int) k.getLocation().getY());
                        }
                    }
                }
                if (!competition.getFinishedCompetitors().isEmpty()) {
                    for (Competitor f : competition.getFinishedCompetitors()) {
                        g.drawImage(i, (int) f.getLocation().getX(), (int) f.getLocation().getY(), 45, 45, null);
                        g.drawString(((Sportsman) f).getName(), (int) f.getLocation().getX(), (int) f.getLocation().getY());
                    }
                }
                if (!competition.getInactiveCompetitors().isEmpty()) {
                    for (Competitor d : competition.getInactiveCompetitors()) {
                        g.drawImage(i, (int) d.getLocation().getX(), (int) d.getLocation().getY(), 45, 45, null);
                        g.drawImage(i2, (int) d.getLocation().getX(), (int) d.getLocation().getY(), 20, 23, null);
                        g.drawString(((Sportsman) d).getName() + "-Disabled", (int) d.getLocation().getX(), (int) d.getLocation().getY());
                    }
                }
            }
        };
        gamePanel.setOpaque(false);
        gamePanel.setVisible(true);
        wallpaperPanel.setVisible(true);
        wallpaperPanel.add(gamePanel);
        mainFrame.add(wallpaperPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void updateWallpaper(String wallpaperName) {
        wallpaperPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                wallpaperPanel.setLayout(new OverlayLayout(wallpaperPanel));
                ImageIcon img = new ImageIcon(wallpaperName);
                Image i = img.getImage();
                g.drawImage(i, 0, 0, this.getWidth(), this.getHeight(), this);
                setVisible(true);
            }
        };
        wallpaperPanel.setSize(1000,Integer.parseInt(arenaLengthTextField.getText()) + 80);
        toolBar.setSize(200,Integer.parseInt(arenaLengthTextField.getText()) + 80);
        mainFrame.add(wallpaperPanel);
        mainFrame.setSize(1000,Integer.parseInt(arenaLengthTextField.getText()) + 80);
        mainFrame.setVisible(true);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}




