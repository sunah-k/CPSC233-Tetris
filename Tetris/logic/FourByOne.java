package logic;

/**
 * FourByOne is a subclass of Shapes which handles all shapes that
 * are 4x1 or 1x4 in size. This includes:
 * <li>Creating the shape
 * <li>Rotating the shape
 */
public class FourByOne extends Shape {
	/**
	 * Class constructor 
	 */
	public FourByOne() {
		super(1);
	}
	
	/**
	 * Creates a fully filled shape that is 4x1 in size.
	 */
	@Override
	public void makeShape() {
		setYSize(4);
		setXSize(1);
		fillShape();
	}
	
	/**
	 * Changes a 4x1 shape to 1x4, and conversely a 1x4 shape to 4x1.
	 */
	@Override
	public void rotateShape() {
		int[][] tempshape = getShape();
		int[][] newShape = new int[getXSize()][getYSize()];
		
		for (int yIndex = 0; yIndex < getYSize(); yIndex++){
			for (int xIndex = 0; xIndex < getXSize(); xIndex++){
				newShape[xIndex][yIndex] = tempshape[yIndex][xIndex];
			}
		}
		int tempY = getYSize();
		setYSize(getXSize());
		setXSize(tempY);
		setShape(newShape);
	}
}