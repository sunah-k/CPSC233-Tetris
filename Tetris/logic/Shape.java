package logic;

import java.util.Random;

/**
 * Shape class determines the types of shapes the Block can take on.
 */ 
public abstract class Shape extends Block {
	private int ySize;
	private int xSize;
	private static int shapeNum;
	private int[][] shape;
	
	/**
	 * Class constructor.
	 */
	public Shape(){
		super();
		ySize = 2;
		xSize = 2;
		shapeNum = 0;
		shape = new int[ySize][xSize];
		makeShape();
	}
	
	/**
	 * Class constructor specifying the type of shape to create.
	 * @param aShapeNum
	 */
	public Shape(int aShapeNum) {
		super();
		shapeNum = aShapeNum;
		makeShape();
	}
	
	/**
	 * Copy Constructor
	 * @return
	 */
	public Shape(Shape aShape) {
		super(aShape);
		ySize = aShape.getYSize();
		xSize = aShape.getXSize();
		shapeNum = aShape.getShapeNum();
		shape = aShape.getShape();
		
	}
	
	/**
	 * Getter for shapeNum
	 * @return shapeNum
	 */
	public int getShapeNum() {
		return shapeNum;
	}
	
	/**
	 * Getter for shape
	 * @return copyShape a copy of shape
	 */
	public int[][] getShape() {
		int[][] copyShape = new int[getYSize()][getXSize()];
		for (int yIndex  = 0; yIndex < getYSize(); yIndex++) {
			for(int xIndex = 0; xIndex < getXSize(); xIndex++) {
				copyShape[yIndex][xIndex] = shape[yIndex][xIndex];
			}
		}
		return copyShape;
	}
	
	/**
	 * Getter for ySize
	 * @return ySize
	 */
	public int getYSize() {
		return ySize;
	}
	
	/**
	 * Getter for xSize
	 * @return xSize
	 */
	public int getXSize() {
		return xSize;
	}
	
	/**
	 * Setter for shapeNum
	 * @param newNum
	 */
	public void setShapeNum(int newNum) {
		shapeNum = newNum;
	}

	/**
	 * Setter for shape
	 * @param aShape
	 */
	public void setShape(int[][] aShape) {
		shape = aShape;
	}
	
	/**
	 * Setter for ySize
	 * @param newYSize
	 */
	public void setYSize(int newYSize) {
		ySize = newYSize;
	}
	
	/**
	 * Setter for xSize
	 * @param newXSize
	 */
	public void setXSize(int newXSize) {
		xSize = newXSize;
	}
	
	/**
	 * Randomly generates a shape number, 
	 * use when a random shape is needed.
	 */
	public static void generateShapeNum() {
		Random rand = new Random();
		shapeNum = (rand.nextInt(7)+1);
	}
	
	/**
	 * Fills all elements of a shape.
	 * Use before makeShape.
	 */
	public void fillShape() {
		shape = new int[ySize][xSize];
		for(int yIndex = 0; yIndex < ySize; yIndex++) {
			for(int xIndex = 0; xIndex < xSize; xIndex++) {
				shape[yIndex][xIndex] = shapeNum;
			}
		}
	}
	
	/**
	 * Creates the desired shape by emptying specific indices of the shape.
	 */
	public abstract void makeShape();
	
	/**
	 * Rotates the shape clockwise.
	 */
	public abstract void rotateShape();
	
	/**
	 * Prints out the shape in text format in the console.
	 * For testing.
	 */
	public void printShape() {
		for (int yIndex  = 0; yIndex < ySize; yIndex++) {
			for(int xIndex = 0; xIndex < xSize; xIndex++) {
				System.out.print("["+ shape[yIndex][xIndex] +"]");
			}
			System.out.println(); 
		}
		System.out.println();
	}
}