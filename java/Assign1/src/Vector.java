
public class Vector extends Matrix {
	// call Matrix constructor with 1 as value of r
	public Vector(int c) {
		super(1, c);
	}
	
	public Vector(int c, double[] linArr) {
		// call Matrix constructor with 1 as value of r
		super(1, c, linArr);
	}
	
	public double getElement(int c) {
		// return value at row 0, column c
		return getData()[0][c];
	}
}
