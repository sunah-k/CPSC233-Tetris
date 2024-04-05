package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class that creates the start window for the Tetris game and gets the player name.
 * This window has two main components; a TextField to get the player name
 * and a start button to store the user name and to start the game
 */
public class StartScreen {
	private JFrame startWindow;
	private JPanel startMainPanel;
	private JTextField startGameText;
	private JLabel userLabel;
	private String playerName;
	private JButton startButton;
	private JLabel backgroundPic;
	
	/**
	 * Default constructor
	 * @param anAction
	 */
	public StartScreen (ActionListener anAction) {
		creatStartWindow(anAction);
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	/**
     * This method creates the start window to get player name and to start the Tetris game
     * @param anAction 
     */
	public void creatStartWindow(ActionListener anAction) {
		startWindow = new JFrame();
		startWindow.setSize(800, 500);
		startWindow.setTitle("Start Game!");
		startWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startWindow.setResizable(false);
		startWindow.setLocationRelativeTo(null);
		
		startMainPanel = new JPanel(new GridBagLayout());
		startMainPanel.setSize(800, 500);
		startMainPanel.setBackground(Color.lightGray);
		
		creatStartwindowComponenet(anAction);
		GridBagConstraints component = new GridBagConstraints();
		component.insets = new Insets(20, 20, 20, 20);
		
		//adding components to the start window
		component.gridx = 1;
		component.gridy = 0;
		startMainPanel.add(backgroundPic, component);
		
		component.gridx = 0;
		component.gridy = 1;
		startMainPanel.add(userLabel, component);
		
		component.gridx = 2;
		component.gridy = 1;
		startMainPanel.add(startGameText, component);
		
		component.gridx = 1;
		component.gridy = 2;
		startMainPanel.add(startButton, component);
		
		startWindow.add(startMainPanel);
		startWindow.setVisible(true);
	}
	
	/**
	 * This method creates the start window components
	 * @param anAction
	 */
	public void creatStartwindowComponenet(ActionListener anAction) {	
		ImageIcon icon = new ImageIcon("Tetris.png"); 
		backgroundPic = new JLabel();
		backgroundPic.setIcon(icon);
		
		startGameText = new JTextField("PlayerName");
		startGameText.setBackground(Color.white);
		startGameText.setLayout(null);
		
		// Change the font of the JLabel
		userLabel = new JLabel("Player Name");
		Font f = userLabel.getFont();
		Font f1 = new Font(f.getFontName(), f.getStyle(), f.getSize()+10);
		userLabel.setFont(f1);
		
		// Change the font of the JTextField
		startGameText.setFont(f1);
		startGameText.setForeground(Color.BLUE);

		startButton = new JButton("Start");
		Font f2 = startButton.getFont();
		Font f3 = new Font(f2.getFontName(), f2.getStyle(), f2.getSize()+5);
		startButton.setFont(f3);
		startButton.addActionListener(anAction);
	}
	
	/**
	 * This method closes the start window and stores the player name
	 */
	public void closeWindow() {
		playerName = startGameText.getText();
		startWindow.dispose();
	}
}