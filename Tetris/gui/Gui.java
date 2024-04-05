package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Creates two Gui and displays them 
 * Gaming Gui
 * this window has three components; top panel which displays player score,
 * game window which displays moving block and side panel which displays player options
 * Game over Gui
 * at the end of the game the user score and highest score will be displayed 
 */
public class Gui {
	private int width ;
	private int height;
	private String gameTitle;
	private Render square;
	private JFrame gameWindow;
	private JTextArea menu;
	private JLabel scoreLabel;
	private JPanel sidePanel;
	private JPanel topPanel;
	private JFrame gameOverWindow;
	private JPanel gameOverMainPanel;
	private JTextArea endGameText;
	private JTextArea scoreArea;	
	
    /**
     * Creates a Gui 
     * @param aKeyListener the player input (keyboard input)
     * @param aGameTitle the title of game
     * @param aWidth the width of the game Window
     * @param aHeight the height of the game window
     */
	public Gui (KeyListener aKeyListener, String aGameTitle, int aWidth, int aHeight, int[][] aGrid) {
		gameTitle = aGameTitle;
		width = aWidth;
		height = aHeight;
		initGui(aGrid, aKeyListener);
	}
	
	 /** 
	  * Initialize the Gui window 
	  * @param aGrid the game grid
	  * @param aKeyListener the player input (keyboard input)
      */
	public void initGui(int[][] aGrid, KeyListener aKeyListener) {
		creatTopPanel();
		creatSidePanel();
		creatWindow(aGrid, aKeyListener);
	}

	 /** 
	  * Creates and displays GUI window based on JFrame
	  * this window has three components; two JPanels and Tetris display section
	  * @param aGrid the game grid
	  * @param aKeyListener the player input (keyboard input)
      */
	public void creatWindow(int[][] aGrid, KeyListener l) {
		gameWindow = new JFrame();
		gameWindow.setSize(width, height);
		gameWindow.setTitle(gameTitle);
		gameWindow.setLayout(new BorderLayout());
		
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setResizable(false);
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setFocusable(true);
		
		gameWindow.add(topPanel, BorderLayout.NORTH);	
		gameWindow.add(sidePanel, BorderLayout.EAST);
		square = new Render (aGrid);
		gameWindow.add(square);
		gameWindow.setVisible(true);
		gameWindow.addKeyListener(l);
	}
	
	 /** 
	  * Creates a part of the game window, the side panel, which displays player options
      */	
	public JPanel creatSidePanel() {
		sidePanel = new JPanel();
		sidePanel.setSize(400, 940);
		sidePanel.setBackground(Color.lightGray);
		// Add menu to the side panel	
		menu = new JTextArea();
		menu.setBackground(Color.lightGray);
	
		// get the current font
		Font f = menu.getFont();
		Font f1 = new Font(f.getFontName(), f.getStyle(), f.getSize()+8);
		menu.setForeground(Color.blue);

		// set the new font in the editing area
		menu.setFont(f1);
		menu.setText(" ------- CONTROLS -------\n\n" + "   Left Key -> Move Left\n" + 
		"   Right Key -> Move Right\n" + "   Up Key -> Rotate Shape\n\n\n" + 
				"  60 bonus score for\n  disappearing four rows\n  at once");
		
		Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize()+10);
		menu.setFont(f2);
		sidePanel.add(menu);
		return sidePanel;	
	}
	
	 /** 
	  * Creates a top panel which displays player score
     */	
	public JPanel creatTopPanel() {
		topPanel = new JPanel();
		topPanel.setSize(650, 50);
		topPanel.setBackground(Color.LIGHT_GRAY);
		
		scoreLabel = new JLabel("Score: 0");
		Font f = scoreLabel.getFont();
		Font f1 = new Font(f.getFontName(), f.getStyle(), f.getSize()+10);
		scoreLabel.setFont(f1);
		topPanel.add(scoreLabel);
		
		return topPanel;
	}
	
	/** 
	  * Repaints the block on the screen after each move based on the grid
	  *  @param aGrid the game grid
	  */
	public void rePaint(int[][] aGrid){
		square.setGrid(aGrid);
		square.repaint();
	}
	
	/** 
	  *Updates the player score on the screen 
	  * @param score the player score
	  */
	public void updateScore(String score){
		scoreLabel.setText("Score: " + score);
	}
	
	/** 
	  * Creates a new screen at the end of the game to show game over message along with player and highest score
	  * @param playerScore
	  * @param highScore
      */
	public void creatGameOverWindow(int playerScore, String highScore) {
		gameOverWindow = new JFrame();
		gameOverWindow.setSize(500, 500);
		gameOverWindow.setTitle("Game Over!");
		gameOverWindow.setLayout(null);
		gameOverWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOverWindow.setResizable(false);
		gameOverWindow.setLocationRelativeTo(gameWindow);

		gameOverWindow.add(creatGameOverComponenets(playerScore, highScore));
		gameOverWindow.setVisible(true);
	}
	
	 /** 
	  * Creates components of game over window 
	  * @param playerScore
	  * @param highScore
	  */	
	public JPanel creatGameOverComponenets(int playerScore, String highScore) {
		gameWindow.setVisible(false);
		// main game over panel
		gameOverMainPanel = new JPanel();
		gameOverMainPanel.setLayout(null);
		gameOverMainPanel.setLocation(0, 0);
		gameOverMainPanel.setSize(500, 500);
		gameOverMainPanel.setBackground(Color.white);
		
		// creating an area to diplay game over messegae
		endGameText = new JTextArea();
		endGameText.setBackground(Color.white);
		endGameText.setLayout(null);
		endGameText.setLocation(80,80);
		endGameText.setSize(400,100);

		Font f = endGameText.getFont();
		Font f1 = new Font(f.getFontName(), f.BOLD, f.getSize()+50);
		endGameText.setFont(f1);
		endGameText.setForeground(Color.RED);
		endGameText.setText("Game Over!");
		gameOverMainPanel.add(endGameText);
		
		// creating an area to diplay player and highest score
		scoreArea = new JTextArea();
		scoreArea.setBackground(Color.white);
		scoreArea.setLayout(null);
		scoreArea.setLocation(50,250);
		scoreArea.setSize(400,300);
		
		// Modify the font of score area
		Font f2 = scoreArea.getFont();
		Font f3 = new Font(f2.getFontName(), f2.BOLD, f2.getSize() + 15);
		scoreArea.setFont(f3);
		scoreArea.setForeground(Color.BLUE);
		scoreArea.setText("Your Score: " + playerScore +"\n\n"+ "HighScore: " + highScore);
		
		gameOverMainPanel.add(scoreArea);
		gameOverMainPanel.setVisible(true);
				
		return gameOverMainPanel;	
	}		
}

	