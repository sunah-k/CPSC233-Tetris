package logic;

/**
 * ThreeByTwo is a subclass of Shapes which handles all shapes that
 * are 3x2 or 2x3 in size. This includes:
 * <li>Creating the shape
 * <li>Rotating the shape
 */
public class ThreeByTwo extends Shape {
	/**
	 * Class constructor 
	 * @param aShapeNum
	 */
	public ThreeByTwo(int aShapeNum) {
		super(aShapeNum);
	}
	
	/**
	 * Creates the desired shape by emptying specific indices of the shape.
	 * The type of shape depends on the shapeNum that is passed in
	 */
	@Override
	public void makeShape() {
		setYSize(3);
		setXSize(2);
		fillShape();

		int shapeNum = getShapeNum();
		int[][] shape = getShape();
		if (shapeNum == 3) { // J shape
			shape[0][0] = 0;
			shape[1][0] = 0;
		}
		else if (shapeNum == 4 ) { // L shape
			shape[0][1] = 0;
			shape[1][1] = 0;
		}
		else if (shapeNum == 5) { // S shape
			shape[0][1] = 0;
			shape[2][0] = 0;
		}
		else if (shapeNum == 6) { // Z shape
			shape[0][0] = 0;
			shape[2][1] = 0;
		}
		else if (shapeNum == 7) { //T shape
			shape[0][1] = 0;
			shape[2][1] = 0;
		}
		setShape(shape);
	}
	
	/**
	 * Rotates the shape clockwise.
	 */
	@Override
	public void rotateShape() {
		int[][] tempshape = getShape();
		int[][] newShape = new int[getXSize()][getYSize()];
		if (getYSize() == 2){
			for (int yIndex = 0; yIndex < getYSize(); yIndex++){
				for (int xIndex = 0; xIndex < getXSize(); xIndex++){
					int yNum;
					if (yIndex == 0)
						yNum = 1;
					else
						yNum = 0;
					newShape[xIndex][yNum] = tempshape[yIndex][xIndex];
				}
			}
		}
		else {
			for (int yIndex = 0; yIndex < getYSize(); yIndex++){
				for (int xIndex = 0; xIndex < getXSize(); xIndex++){
					int yNum = 1;
					if (yIndex == 0)
						yNum = 2;
					else if (yIndex == 2)
						yNum = 0;
					newShape[xIndex][yNum] = tempshape[yIndex][xIndex];
				}
			}
		}
		int tempY = getYSize();
		setYSize(getXSize());
		setXSize(tempY);
		setShape(newShape);
	}
}