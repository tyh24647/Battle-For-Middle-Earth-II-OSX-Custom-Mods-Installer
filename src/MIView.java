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
        setVisible(false);
    }

    /**
     * Static initializer
     * @return
     */
    @NotNull
    public static MIView init() {
        MIView view = new MIView();
        view.setVisible(true);
        return view;
    }

    private void setupMainFrame() {
        setSize(DEFAULT_WINDOW_SIZE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(DEFAULT_WINDOW_SIZE);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        setResizable(true);
        pack();
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
        eastPanel.setPreferredSize(new Dimension(DEFAULT_SIDE_PANEL_THICKNESS, mainPanel.getHeight()));
        westPanel.setPreferredSize(new Dimension(DEFAULT_SIDE_PANEL_THICKNESS, mainPanel.getHeight()));

        configureNorthPanel();
        configureSouthPanel();
        configureCenterPanel();
        configureMainPanel();
        
        add(northPanel);
        add(southPanel);
        add(eastPanel);
        add(westPanel);
        add(centerPanel);
    }

    private void configureCenterPanel() {
        int combinedSidePanelThickness = 2 * DEFAULT_SIDE_PANEL_THICKNESS;
        centerPanel.setPreferredSize(new Dimension(
                mainPanel.getWidth() - combinedSidePanelThickness,
                mainPanel.getHeight() - combinedSidePanelThickness
        ));

        // add 'apply' button
        JPanel btnLayout = new JPanel(new BorderLayout());
        btnLayout.setPreferredSize(new Dimension(
                centerPanel.getWidth(),
                centerPanel.getHeight() / 3
        ));

        applyBtn = new JButton("Apply");
        btnLayout.add(applyBtn, BorderLayout.CENTER);

        // add app selector
        JPanel appSelectorPanel = new JPanel(new BorderLayout());
        appSelectorPanel.setPreferredSize(new Dimension(
                centerPanel.getWidth(),
                centerPanel.getHeight() / 3
        ));

        appPathChooser = new JFileChooser("/Applications");
        appSelectorPanel.add(appPathChooser, BorderLayout.CENTER);

        // add mod selector
        JPanel modSelectorPanel = new JPanel(new BorderLayout());
        modSelectorPanel.setPreferredSize(new Dimension(
                centerPanel.getWidth(),
                centerPanel.getHeight() / 3
        ));

        modFileChooser = new JFileChooser("~/Downloads");
        modSelectorPanel.add(modFileChooser, BorderLayout.CENTER);

        // add items to center panel
        centerPanel.add(btnLayout, BorderLayout.SOUTH);
        centerPanel.add(appSelectorPanel, BorderLayout.CENTER);
        centerPanel.add(modSelectorPanel, BorderLayout.SOUTH);
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
        spacer.setPreferredSize(new Dimension(
                northPanel.getWidth() / 3,
                northPanel.getHeight()
        ));

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

        southPanel.setPreferredSize(new Dimension(
                mainPanel.getWidth(),
                DEFAULT_SIDE_PANEL_THICKNESS
        ));

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
