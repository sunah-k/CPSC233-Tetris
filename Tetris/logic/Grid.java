package logic;
import java.util.Random;

/**
 * Creates a grid based on ROW_NUM and COLUMN_NUM,
 * can check the location of the shape and keep it in grid boundaries,
 * can check if there is a row filled and modifies grid according to if there is a row filled or not,
 * can erase and place the shape information on the grid,
 * and can generate a new location for a shape.
 */
public class Grid {
	public static final int ROW_NUM = 20;
	public static final int COLUMN_NUM = 10;
	public static final int MAX_ROW_INDEX = ROW_NUM - 1;
	public static final int MIN_ROW_INDEX = 0;
	public static final int MAX_COL_INDEX = COLUMN_NUM - 1;
	public static final int MIN_COL_INDEX = 0;
	public static final int EMPTY = 0;
	
	private int[][] board;
	
	/**
	 * Constructor for Grid
	 */
	public Grid() {
		board = new int[ROW_NUM][COLUMN_NUM];
	}
	
	/**
	 * Getter for board
	 * @return copy of the board
	 */
	public int[][] getBoard() {
		int[][] copyBoard = new int[ROW_NUM][COLUMN_NUM];
		for (int row  = 0; row < ROW_NUM; row++) {
			for(int col = 0; col < COLUMN_NUM; col++) {
				copyBoard[row][col] = board[row][col];}
		}
		return copyBoard;
	}
	
	/**
	 * Setter for board
	 * @param aBoard 
	 */
	public void setBoard(int[][] aBoard) {
		for (int row  = 0; row < ROW_NUM; row++) {
			for(int col = 0; col < COLUMN_NUM; col++) {
				board[row][col] = aBoard[row][col];}
		}
	}
	
	/**
	 * This method initializes the grid with EMPTY(0)
	 */
	public void initializeGrid() {
		for (int row  = 0; row < ROW_NUM; row++) {
			for(int col = 0; col < COLUMN_NUM; col++) {
				board[row][col] = EMPTY;}
		}
	}
	
	/**
	 * This method prints out the grid
	 */
	public void displayGrid() {
		for (int row  = 0; row < ROW_NUM; row++) {
			for(int col = 0; col < COLUMN_NUM; col++) {
				System.out.print("["+ board[row][col] +"]");
			}
			System.out.println(); 
		}
	}
	
	/**
	 * Increments shape column if shape is outside grid boundaries (on the left side)
	 * or if the shape overlaps with another shape on the grid
	 * @param aShape 
	 */
	public void checkMoveLeft(Shape aShape) {
		if (aShape.getCol() < MIN_COL_INDEX){ 
			aShape.incrementCol();
		}
		else if (!isValidMovement(aShape)){
			aShape.incrementCol();
		}
	}
	
	/**
	 * Decrements shape column if shape is outside grid boundaries (on the right side)
	 * or if the shape overlaps with another shape on the grid
	 * @param aShape
	 */
	public void checkMoveRight(Shape aShape){
		if (aShape.getCol() > COLUMN_NUM - aShape.getXSize()){
			aShape.decrementCol();
		}
		else if (!isValidMovement(aShape)){
			aShape.decrementCol();
		}
	}
	
	/**
	 * Returns true if the shape does not stack on top of another shape
	 * @param aShape
	 * @return canMoveDown
	 */
	public boolean checkMoveDown(Shape aShape) {	
		boolean canMoveDown = true;
		if (!isValidMovement(aShape)){
			aShape.decrementRow();
			canMoveDown = false;
		}
		return canMoveDown;
	}
	
	/**
	 * This method returns true if the location of the shape does not overlap
	 * another shape on the grid 
	 * @param aShape 
	 * @return canMove 
	 */
	public boolean isValidMovement(Shape aShape) {
		boolean canMove = true;
		int[][] tempShape = aShape.getShape();
		for (int row = 0; row < aShape.getYSize(); row++){ 
			for (int col = 0; col < aShape.getXSize(); col++){ 
				if (tempShape[row][col] != EMPTY && 
					board[row+aShape.getRow()][col+aShape.getCol()] != EMPTY){
					canMove = false;
				}
			}
		}
		return canMove;
	}

	/**
	 * Returns true if shape has reached the top of the grid and cannot move down
	 * @param aShape 
	 * @param canMoveDown which is the current state of the shape; if it can move down the Grid or not
	 * @param gameEnd which is the current state of the game; if game is over or not 
	 * @return gameEnd 
	 */
	public boolean isTop(Shape aShape, boolean canMoveDown, boolean gameEnd) {
		if ((aShape.getRow() == MIN_ROW_INDEX) &&
				!canMoveDown) {
			gameEnd = true;
		}
		return gameEnd;
	}
	
	/**
	 * This method counts the number of filled rows and erases any filled rows 
	 * @param canMoveDown
	 */
	public int eraseFullRow(Player player) {
		int numColFilled = 0;
		int numEmptyRow = 0;
		
		for (int row = 0; row <= MAX_ROW_INDEX; row++){
			numColFilled = 0;
			for (int col = MIN_COL_INDEX; col <= MAX_COL_INDEX; col++){
				if (board[row][col] != EMPTY) {
					numColFilled++;
				}
			}
			if (numColFilled == COLUMN_NUM){
				numEmptyRow++;
				for (int col = MIN_COL_INDEX; col <= MAX_COL_INDEX; col++){
					board[row][col] = EMPTY; 
				}
			}
		}	
		return numEmptyRow;
	}
	
	/**
	 * This method determines if a row is empty and changes the empty row to be the same as the 
	 * row above it and makes the row above it empty (Drops the row down one row). This is done for all 
	 * the rows of the grid from the bottom row of the grid to the top row.
	 */
	public void dropRows() {
		int numColEmpty = 0;
		for (int row = MAX_ROW_INDEX; row > MIN_ROW_INDEX; row--){
			numColEmpty = 0;
			for (int col = MIN_COL_INDEX; col <= MAX_COL_INDEX; col++){
				if (board[row][col] == EMPTY)
					numColEmpty++;
			}
			if (numColEmpty == COLUMN_NUM){
				for (int col = MIN_COL_INDEX; col <= MAX_COL_INDEX; col++){
					board[row][col] = board[row-1][col];
					board[row-1][col] = EMPTY;
				}
			}
		}
	}
	
	/**
	 * This method generates and sets the location where a new shape will be spawned
	 * @param aShape the shape that will spawn on the grid
	 */
	public void setShapeSpawn(Shape aShape) {
		Random randomGenerator = new Random();
		aShape.setCol(randomGenerator.nextInt(Grid.COLUMN_NUM - aShape.getXSize()+1));	
	}
	
	/**
	 * This method erases the shape information in the grid
	 * @param aShape the shape that is being erased from the grid
	 */
	public void eraseShape(Shape aShape) {
		for (int row  = 0; row < aShape.getYSize(); row++) {
			for(int col = 0; col < aShape.getXSize(); col++) {
				if (aShape.getShape()[row][col] != EMPTY){
					board[row + aShape.getRow()][col + aShape.getCol()] = EMPTY;
				}
			}
		}
	}
	
	/**
	 * This method puts the shape information in the grid
	 * @param aShape the shape that is being placed on the grid
	 */
	public void placeShape(Shape aShape){
		int [][] tempShape = aShape.getShape();
		for (int row  = 0; row < aShape.getYSize(); row++) {
			for(int col = 0; col < aShape.getXSize(); col++) {
				if (tempShape[row][col] != EMPTY){
					board[row + aShape.getRow()][col + aShape.getCol()] = aShape.getShapeNum();
				}
			}
		}
	}
}
