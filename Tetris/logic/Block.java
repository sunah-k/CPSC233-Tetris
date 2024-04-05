package logic;

/**
 * Determines the location of the Block in relation to the Grid
 */
public class Block {	
	private int row;
	private int col;

	/**
	 * Default Constructor
	 */
	public Block() {
		row = 0;
		col = 0;
	}
	
	/**
	 * Copy Constructor
	 */
	public Block(Block aBlock){
		row = aBlock.getRow();
		col = aBlock.getCol();
	}
	
	/**
	 * Getter for the row 
	 * @return row of block
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Getter for the column
	 * @return col of block
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Setter for row 
	 * @param newRow
	 */
	public void setRow(int newRow){
		row = newRow;
	}
	
	/**
	 * Setter for col
	 * @param newCol
	 */
	public void setCol(int newCol){
		col = newCol;
	}

	/** 
	 * Increments row
	 */
	public void incrementRow() {
		row++;
	}
	
	/**
	 * Decrements row
	 */
	public void decrementRow() {
		row--;
	}
	
	/**
	 * Increments column
	 */
	public void incrementCol() {
		col++;
	}
	
	/**
	 * Decrements column
	 */
	public void decrementCol() {
		col--;
	}
}