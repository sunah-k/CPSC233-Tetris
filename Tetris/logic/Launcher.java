package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import gui.StartScreen;

/**
 * Launcher starts a Tetris game and keeps the game going using a timer until the game ends.
 */
public class Launcher implements ActionListener {
	private StartScreen startWindow;
	private String playerName;
	
	/**
	 * Class constructor
	 */
	public Launcher() {
		startWindow = new StartScreen(this);
	}
	
	/**
	 * Closes the start window and starts the game when the start button is pressed.
	 */
	public void actionPerformed(ActionEvent startScreenEvent) {
		if (startScreenEvent.getActionCommand().equals("Start")) {
			System.out.print("start Button pressed");
			startWindow.closeWindow();
			playerName = startWindow.getPlayerName();
			startGame(playerName);
		}
	}
	
	/**
	 * Runs the game.
	 * @param aPlayerName the user name for the new Tetris game.
	 */
	public void startGame(String aPlayerName) {
		TetrisGame game = new TetrisGame(aPlayerName);
		Timer timer = new Timer();
		game.newTetrisBlock();
		
		if (game.getBlockActive()){
			timer.schedule(new TimerTask(){
				@Override
				public void run() {
					if (!game.getBlockActive()){
						game.moveBlockDown();
						game.updateGrid();
					}
					game.updateScore(); 
					if (!game.getBlockActive()){
						game.newTetrisBlock();
					}
					game.updateGui();
					game.moveBlockDown();
					game.updateGrid();
					if (game.getGameEnd()){
						timer.cancel();
						game.gameOver();
					}
				}
			}, 1000, 500);	
		}
	}
	
	public static void main(String[] args) {
		Launcher launchTetris = new Launcher();		
	}
}