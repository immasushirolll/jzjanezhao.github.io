
public class MarkovChain {
	
	// private instance variables
	private Vector stateVector;
	private Matrix transitionMatrix;
	
	// initializes instance variables
	public MarkovChain(Vector sVector, Matrix tMatrix){
		stateVector = sVector;
		transitionMatrix = tMatrix;
	}
	
	// check if instance variables are valid and return boolean
	public boolean isValid() {
		// checks for correct dimensions
		Matrix flag = stateVector.multiply(transitionMatrix);
		if (flag == null) {
			return false;
		}
		
		// checks that stateVector probabilities sum to 1
		double probSum = 0;
		for (int i = 0; i < stateVector.getNumCols(); i++) {
			probSum += stateVector.getElement(i);
		}
		
		if (probSum > 1.01 || probSum < 0.99) {
			return false;
		}
		
		// checks that each row of Matrix probabilities sums to 1
		for (int i = 0; i < transitionMatrix.getNumRows(); i++) {
			double probSumRow = 0;
			for (int j = 0; j < transitionMatrix.getNumCols(); j++) {
				probSumRow += transitionMatrix.getElement(i, j);
			}
			if (probSumRow > 1.01 || probSumRow < 0.99) {
				return false;
			}
		}
		return true;
	}

	// computes probability matrix
	public Matrix computeProbabilityMatrix(int numSteps) {
		// call isValid()
		if (!isValid()) {
			return null;
		}
		
		// create new Matrix object with same dimensions as stateVector, 
		// populates with stateVector data
		Matrix currentState = new Vector(stateVector.getNumCols(), stateVector.getData()[0]);
		// multiplies by transitionMatrix numSteps times
		for (int i = 0; i < numSteps; i++) {
			currentState = currentState.multiply(transitionMatrix);
		}
		return currentState;
	}
}
