package logic;
/**
 * TwoByTwo is a subclass of Shapes which handles all shapes that
 * are 2x2 in size. This includes:
 * <li>Creating the shape
 * <li>Rotating the shape 
 */

public class TwoByTwo extends Shape {
	/**
	 * Class constructor 
	 */
	public TwoByTwo () {
		super(2);
	}
	
	/**
	 * Creates a fully filled 2x2 shape.
	 * No empty spots are needed.
	 */
	@Override
	public void makeShape() {
		setYSize(2);
		setXSize(2);
		fillShape();
	}
	
	/**
	 * Does nothing since rotating the Square shape is not useful.
	 */
	@Override
	public void rotateShape() {		
	}
}