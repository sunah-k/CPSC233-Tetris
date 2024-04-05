package logic;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import gui.Gui;
import gui.StartScreen;

/**
 * Creates a Tetris game that has a player, a grid, a shape, a gui, and a start window.
 * TetrisGame can create a new shape that is a block, set the player action,
 * and updates the grid and gui based on the player action.
 */
public class TetrisGame implements KeyListener, ActionListener {
	private boolean gameEnd;
	private boolean blockActive;
	private Player player;
	private Grid tetrisGrid;
	private Shape aShape;
	private Gui tetrisGui;
	private StartScreen startWindow;
	
	/**
	 * Default constructor
	 */
	public TetrisGame(String playerName) {
		gameEnd = false;
		blockActive = true;
        player = new Player(playerName);
        tetrisGrid = new Grid();
    	tetrisGrid.initializeGrid();
        aShape = new TwoByTwo();
        tetrisGui = new Gui(this, "Tetris Game", 720, 990, tetrisGrid.getBoard());  
	}
	
	/**
	 * Getter for BlockActive
	 * @return blockActive
	 */
	public boolean getBlockActive() {
		return blockActive;
	}
	
	/**
	 * Getter for gameEnd
	 * @return gameEnd
	 */
	public boolean getGameEnd() {
		return gameEnd;
	}
	
	/**
	 * Changes the action to move the block down
	 */
	public void moveBlockDown() {
		player.setAction("s");
	}
	
	/**
	 * Updates the Tetris grid according to the action of the player or dropping of the block
	 */
	public void updateGrid() {
		tetrisGrid.eraseShape(aShape);
		blockActive = player.processInput(aShape, tetrisGrid, blockActive); 
		gameEnd = tetrisGrid.isTop(aShape, blockActive, gameEnd);
		tetrisGrid.placeShape(aShape);
	}
	
	/**
	 * Updates the Gui according to the changes from player action or drop of the block
	 */
	public void updateGui() {
		tetrisGui.rePaint(tetrisGrid.getBoard());
	}
	
	/**
	 * Updates the score of the player and modifies Tetris grid based on getting a filled row 
	 * in grid.
	 * Updates the score in gui 
	 */
	public void updateScore() {
		if (!blockActive){
			int numEmptyRow = tetrisGrid.eraseFullRow(player);
			player.addScore(numEmptyRow * 10);
			if (numEmptyRow == 4){
				player.addScore(60);
			}
			while (numEmptyRow>0){
				tetrisGrid.dropRows();
				numEmptyRow--;
			}
		}
		tetrisGui.updateScore(Integer.toString(player.getScore()));
	}
	
	/**
	 * Ends the game after the last block reaches to top of the Tetris window
	 */
	public void gameOver() {
		try{
			player.replaceHighScores();
	    }
	    catch(IOException ioe){
	    	System.out.println("Error: problem with the highscore. Sorry :(");
	    	player.setHighScore("Error with highscore");
	    }finally {
	    	tetrisGui.creatGameOverWindow(player.getScore(), player.getHighScore());
	    }
	}

	/** 
	 * Creates a new Tetris block and places it on the Tetris grid
	 */
	public void newTetrisBlock() {
		blockActive = true;
		Shape.generateShapeNum();
		
		if (aShape.getShapeNum() == 1)
			aShape = new FourByOne();
		else if (aShape.getShapeNum() == 2)
			aShape = new TwoByTwo();
		else 
			aShape = new ThreeByTwo(aShape.getShapeNum()); 

		tetrisGrid.setShapeSpawn(aShape); 
		if (!tetrisGrid.isValidMovement(aShape)){
			gameEnd = true; 
		}
		tetrisGrid.placeShape(aShape);
	}
	
	/** 
	 * Handles the key typed event in the main Tetris game
	 * @param keyboardInput
	 */
	public void keyTyped(KeyEvent keyboardInput) {
		if (keyboardInput.getKeyCode() == KeyEvent.VK_LEFT) {}
		else if (keyboardInput.getKeyCode() == KeyEvent.VK_RIGHT) {}
		else if (keyboardInput.getKeyCode() == KeyEvent.VK_UP){}
		else if (keyboardInput.getKeyCode() == KeyEvent.VK_DOWN){}
	}
	
	/** 
	 * Handles the key pressed event in the main Tetris game
	 * @param keyboardInput
	 */
	public void keyPressed(KeyEvent keyboardInput) {
		if (keyboardInput.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setAction("a");
			updateGrid();
			updateGui();
		}
		else if (keyboardInput.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.setAction("d");
			updateGrid();
			updateGui();
		}
		else if (keyboardInput.getKeyCode() == KeyEvent.VK_UP) {
			player.setAction("r");
			updateGrid();
			updateGui();
		}
		else if (keyboardInput.getKeyCode() == KeyEvent.VK_DOWN) {
			if (blockActive){
				player.setAction("s");
				updateGrid();
				updateGui();
			}
		}
	}
	
	/** 
	 * Handles the key released event in the main Tetris game
	 * @param keyboardInput
	 */
	public void keyReleased(KeyEvent keyboardInput) {
		if (keyboardInput.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setAction("reset");
		}
		else if (keyboardInput.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.setAction("reset");
		}
		else if (keyboardInput.getKeyCode() == KeyEvent.VK_UP) {
			player.setAction("reset");
		}
		else if (keyboardInput.getKeyCode() == KeyEvent.VK_DOWN) {
			player.setAction("reset");
		}
	}
	
	/** 
	 * Handles the start button pressed event in the start screen
	 * @param startScreenEvent
	 */
	public void actionPerformed(ActionEvent startScreenEvent) {
	     if (startScreenEvent.getActionCommand().equals("Start")) {
	            startWindow.closeWindow();
	     }
	}
}