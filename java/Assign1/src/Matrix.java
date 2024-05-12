
public class Matrix {
	// private instance variables
	private int numRows;
	private int numCols;
	private double[][] data;
	
	// constructor
	public Matrix(int r, int c) { 
		// initialize instance variables numRows and numCols
		numRows = r;
		numCols = c;
		// initialize data to have r rows and c columns
		data = new double[r][c];
	}
	
	// populates 2D array with elements in linArr
	public Matrix(int r, int c, double[] linArr) { // constructor
		numRows = r;
		numCols = c;
		data = new double[r][c];
		int x = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				data[i][j] = linArr[x];
				x++;
			}
		}
	}
	
	// returns number of rows
	public int getNumRows() {
		return numRows;
	}
	
	// returns number of columns
	public int getNumCols() {
		return numCols;
	}
	
	// returns 2D array containing matrix data
	public double[][] getData() {
		return data;
	}
	
	// return value from data at row r and column c
	public double getElement(int r, int c) {
		return data[r][c];
	}
	
	// store value at row r and column c of data
	public void setElement(int r, int c, double value) {
		data[r][c] = value;
	}
	
	public void transpose() {
		// transposes matrix and stores as new instance of "this" matrix
		int n = this.numRows, m = this.numCols;		// number of rows n, number of columns m
		double[][] transposed = new double[m][n];		// empty matrix of size m*n

		for (int i = 0; i < m; i++) {		// traverse matrix
			for (int j = 0; j < n; j++) {
				transposed[i][j] = data[j][i];
			}
		}
			
		this.data = transposed;
		int tmp = numRows;
		numRows = numCols;
		numCols = tmp;
	}
	
	// creates new Matrix object, multiplies each element by scalar amount, and returns it
	public Matrix multiply(double scalar) {
		Matrix other = new Matrix(numRows, numCols);
		// insert the resulting values into the new Matrix object
		for (int i = 0; i < numRows; i++) 
			for (int j = 0; j < numCols; j++) 
				other.setElement(i, j, scalar*data[i][j]);
			
		return other;
	}
	
	public Matrix multiply(Matrix other) {
		// check if matrices are compatible
		if (other.numRows != numCols) {
			return null;
		}
		// create new Matrix object m
		Matrix m = new Matrix(this.numRows, other.getNumCols());
		// multiply matrices
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < other.getNumCols(); j++) {
				double tmp = 0;
				for (int k = 0; k < this.numCols; k++) {
					tmp += data[i][k]*other.getElement(k,j);
				}
				// inserts multiplied values into new matrix object
				m.setElement(i, j, tmp);
			}
		}
		return m;
	}
	
	public String toString() {
		// if matrix is empty, return "Empty matrix"
		if (numRows == 0 || numCols == 0) {
			return "Empty matrix";
		}
		// print string in a specific format
		String output = new String();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				output = output + String.format("%8.3f", data[i][j]);
			}
			if (i != numRows - 1) {
				output = output + "\n";
			}
		}
		return output;
	}
}
