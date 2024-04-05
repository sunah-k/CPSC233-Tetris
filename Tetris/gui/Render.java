package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Class that uses JPanel methods to paint squares based on the given 2D array grid 
 * Can paint squares in 8 different colors
 * Can change the size of the squares and number of rows and columns
 */
public class Render extends JPanel{
	private final int squareSize = 45;
	private static final int BOARD_ROW = 20;
	private static final int BOARD_COL = 10;
	private int[][] grid;
	private Color[] colors;

	/**
	 *Default constructor
	 */
	public Render(int [][] aGrid) {
		grid = aGrid;
	}
	
	/**
	 * This mutator method changes the grid
	 * @param aGrid the game grid
	 */
	public void setGrid(int [][] aGrid){
		grid = aGrid;
	}
	
	@Override
	/**
	 * This method paints the squares
	 */
	public void paint(Graphics pen) { 
			super.paint(pen);
				pen.setColor(Color.BLACK);
				pen.fillRect(0, 0, 500, 1000);
			
				for (int i = 0; i < BOARD_ROW; i++) 
					for (int j = 0; j < BOARD_COL; j++) 
							drawSquare(pen, i *squareSize, j*squareSize, grid[i][j] );			
     }
	
	  /**
	  * Draws an individual square. Each square can have a different color
	  * @param pen the drawing tool
	  * @param yCoord the vertical coordinate of the square being drawn
	  * @param xCoord the horizontal coordinate of square being drawn
	  * @param aShapeNum the shape number that determines the color of the square
	  */
	public void drawSquare(Graphics pen, int yCoord, int xCoord, int aShapeNum){
        colors = new Color[]{ Color.BLACK, Color.GREEN, Color.BLUE, Color.RED, 
        		Color.ORANGE, Color.YELLOW, Color.PINK, Color.CYAN};

        Color color = colors[aShapeNum];
        pen.setColor(color);
        pen.fillRect(xCoord + 1, yCoord + 1, squareSize - 2, squareSize - 2);
        
		// Draws the outlines for each square
    	pen.setColor(new Color (0,191,255));
    	pen.drawLine(xCoord, yCoord + squareSize - 1, xCoord, yCoord);
        pen.drawLine(xCoord, yCoord, xCoord + squareSize - 1, yCoord);
        
        pen.setColor(new Color (0,191,255));
        pen.drawLine(xCoord + 1, yCoord + squareSize - 1, xCoord + squareSize - 1, yCoord + squareSize - 1);
        pen.drawLine(xCoord + squareSize - 1, yCoord + squareSize - 1, xCoord + squareSize - 1, yCoord + 1);

	}
}