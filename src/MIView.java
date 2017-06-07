import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.awt.Font.PLAIN;

/**
 * The primary view object which acts as a delegate between the user input and the
 * application data
 *
 * Created by tyhostager on 6/7/17.
 */
public class MIView extends JFrame {

    // static vars
    private static int DEFAULT_SIDE_PANEL_THICKNESS = 60;   // in pixels
    private static Dimension DEFAULT_WINDOW_SIZE = new Dimension(800, 600);

    private JPanel mainPanel, northPanel, southPanel, eastPanel, westPanel, centerPanel;
    private JFileChooser appPathChooser, modFileChooser;
    private JButton applyBtn;

    /**
     * Constructor
     */
    public MIView() {
        setupMainFrame();
        createPanels();
        configurePanels();
        setVisible(false);  // don't start displaying anything until after initialization
    }

    /**
     * Constructs and displays the views
     *
     * @return  The generated JFrame
     */
    @NotNull
    public static MIView init() {
        MIView view = new MIView();
        view.setVisible(true);
        return view;
    }

    private void setupMainFrame() {
        setSize(DEFAULT_WINDOW_SIZE);
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void createPanels() {
        mainPanel = this.mainPanel == null ? new JPanel(new BorderLayout()) : this.mainPanel;
        northPanel = this.northPanel == null ? new JPanel(new FlowLayout()) : this.northPanel;
        southPanel = this.southPanel == null ? new JPanel(new FlowLayout()) : this.southPanel;
        eastPanel = this.eastPanel == null ? new JPanel() : this.eastPanel;
        westPanel = this.westPanel == null ? new JPanel() : this.westPanel;
        centerPanel = this.centerPanel == null ? new JPanel(new BorderLayout()) : this.centerPanel;
    }

    private void configurePanels() {
        eastPanel.setSize(DEFAULT_SIDE_PANEL_THICKNESS, mainPanel.getHeight());
        westPanel.setSize(DEFAULT_SIDE_PANEL_THICKNESS, mainPanel.getHeight());

        configureNorthPanel();
        configureSouthPanel();
        configureCenterPanel();
        configureMainPanel();
    }

    private void configureCenterPanel() {
        int combinedSidePanelThickness = 2 * DEFAULT_SIDE_PANEL_THICKNESS;
        centerPanel.setSize(
                mainPanel.getWidth() - combinedSidePanelThickness,
                mainPanel.getHeight() - combinedSidePanelThickness
        );

        JPanel btnLayout = new JPanel(new BorderLayout());
        applyBtn = new JButton("Apply");
        btnLayout.add(applyBtn, BorderLayout.CENTER);
        JPanel appSelectorPanel = new JPanel();
        appPathChooser = new JFileChooser("/Applications");
        centerPanel.add(btnLayout, BorderLayout.SOUTH);
    }

    private void configureMainPanel() {
        mainPanel.setSize(DEFAULT_WINDOW_SIZE);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        mainPanel.add(eastPanel, BorderLayout.EAST);
        mainPanel.add(westPanel, BorderLayout.WEST);
    }

    private void configureNorthPanel() {
        if (northPanel == null) {   // base case--should never be hit
            createPanels();
            configurePanels();      // initialize and pass back to re-configure nonnull panels
        }

        northPanel.setSize(this.getWidth(), DEFAULT_SIDE_PANEL_THICKNESS);
        JLabel spacer = new JLabel();
        spacer.setSize(northPanel.getWidth() / 3, northPanel.getHeight());
        northPanel.add(new JLabel());
        northPanel.add(new JLabel() {
            @Override
            public void setText(String text) {
                super.setText("BFME2 Mods Installer For MacOS");
            }

            @Override
            public void setFont(Font font) {
                super.setFont(font == null ? this.getFont().deriveFont(PLAIN, 48) : font.deriveFont(PLAIN, 48));
            }

            @Override
            public void setForeground(Color fg) {
                super.setForeground(fg == null ? BLACK : fg);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(WHITE);
            }
        });

        spacer = new JLabel();
        spacer.setSize(northPanel.getWidth() / 3, northPanel.getHeight());

        northPanel.add(new JLabel());
        northPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        northPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        northPanel.repaint();
    }

    private void configureSouthPanel() {
        if (southPanel == null) {
            createPanels();
            configurePanels();
        }

        southPanel.setSize(mainPanel.getWidth(), DEFAULT_SIDE_PANEL_THICKNESS);
        JLabel createdByLbl = new JLabel("(c) tylero056, 2017. All rights reserved.");
        createdByLbl.setFont(this.getFont().deriveFont(PLAIN, 36));
        southPanel.add(createdByLbl);
        southPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        southPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        southPanel.repaint();
    }

    public JButton getApplyBtn() {
        return this.applyBtn;
    }

    public JFileChooser getAppPathChooser() {
        return this.appPathChooser;
    }

    public JFileChooser getModFileChooser() {
        return  this.modFileChooser;
    }
}
