package logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Moves/Rotates block based on player action and reads a highscore file, 
 * compares highscore with the new score and writes a new highscore to the file
 * if the new score is higher than the old score.
 */
public class Player {
	private String action;
	private String fullLine;
    private String line;
    private int score;
    private String highScore;
    private String playerName;
    private File highscoreFile;
 
    /**
     * Constructor
     * @param aPlayerName
     */
    public Player(String aPlayerName) {
    	playerName = aPlayerName;
    	highscoreFile = new File("highscore.txt");
    	try {
    		if (!highscoreFile.exists()) {
    		    PrintWriter writer = new PrintWriter("highscore.txt", "UTF-8");
    		    writer.print("PlayerName 0");
    		    writer.close();
    		}
    	}
    	catch (IOException e) {
    		System.out.print(e.getMessage());
    	}
    	action = "q";
    }
    
    /**
     * Getter for action
     * @return
     */
    public String getAction() {
    	return action;
    }
    
    /**
     * Getter for score
     * @return score
     */
    public int getScore() {
    	return score;
    }
    
    /**
     * Getter for highScore
     * @return highScore
     */
    public String getHighScore() {
    	return highScore;
    }
    
    /**
     * Setter for action
     * @param newAction
     */
    public void setAction(String newAction) {
    	action = newAction;
    }
    
    /**
     * Setter for highscore
     * @param newHighScore
     */
    public void setHighScore(String newHighScore) {
    	highScore = newHighScore;
    }
    
	/**
	 * Adds to the current score
	 * @param value the amount of score that will be added
	 */
	public void addScore(int value) {
		score += value;
	}

	/**
	 * Handles player action for moving the block left or right or 
	 * rotating the block or moving the block down
	 * @param aShape
	 * @param tetrisGrid
	 */
	public boolean processInput(Shape aShape, Grid tetrisGrid, boolean blockActive) {
		switch (action) 
		{
		case "a":
			aShape.decrementCol();
			tetrisGrid.checkMoveLeft(aShape);
			break;
		case "d":
			aShape.incrementCol();
			tetrisGrid.checkMoveRight(aShape);
			
			break;
		case "r":
			if (aShape.getCol() > Grid.COLUMN_NUM - aShape.getYSize()){
				aShape.setCol(aShape.getCol() - (aShape.getCol() + aShape.getYSize() - Grid.COLUMN_NUM));
			}
			if (aShape.getRow() > Grid.ROW_NUM - aShape.getXSize()){
				aShape.setRow(aShape.getRow() - (aShape.getRow() + aShape.getXSize() - Grid.ROW_NUM));
			}
			aShape.rotateShape();
			if (!tetrisGrid.isValidMovement(aShape)){
				aShape.rotateShape();
				aShape.rotateShape();
				aShape.rotateShape();
			}
			break;
		case "s":
			if (aShape.getRow() == Grid.ROW_NUM - aShape.getYSize()){ 
				blockActive = false;
			}
			else {
				aShape.incrementRow();
				blockActive = tetrisGrid.checkMoveDown(aShape);
			}
			break;
		default:
		}
		return blockActive;
	}
	
	/**
	 * Gets highscore by reading a file from the specified path and compares the highscore
	 * in the file with the new score, writes the higher score in the file.
	 */
    public void replaceHighScores() throws IOException, FileNotFoundException { 	
		FileReader fileReader = new FileReader("highscore.txt");
		FileWriter fileWriter = new FileWriter("highscore.txt", true);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		PrintWriter printWriter = new PrintWriter(bufferedWriter);
		fullLine = bufferedReader.readLine();
		
		line = fullLine.substring(11);
		System.out.println("score in file: " + line);
		int oldScore = Integer.parseInt(line);
		String space = " ";
		if(playerName.length() < 10){
			while(playerName.length() < 10){
				playerName = playerName + space;
			}
		}
		else {
			playerName = playerName.substring(0,10);
		}
		if(oldScore < score){
			fileWriter = new FileWriter("highscore.txt"); 
			printWriter.print(playerName + space + score);
			printWriter.close();
			if (playerName.substring(0,4).equals("Play")){
                playerName = "UNKNOWN";
            }
            highScore = playerName.substring(0,3) + space + Integer.toString(score);
		}
		else{
			if (playerName.substring(0,4).equals("Play")){
	            playerName = "UNKNOWN";
	        }
			highScore = fullLine.substring(0,3) + space + Integer.toString(oldScore);
		}
		fileReader.close();
		fileWriter.close();
		bufferedReader.close();
		bufferedWriter.close();
		
    }
}