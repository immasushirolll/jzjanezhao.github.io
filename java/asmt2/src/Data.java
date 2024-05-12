/* Represents the records to be stored in HashDictionary.
 * Each record stored in dictionary consists of a configuration and an integer score.
 * Each board configuration represents a string, where blue = X, red = O, recorded in a left to right, top-down manner.
 */

public class Data {
    String treeString;
    int treeScore; 

    /* constructor, initializes Data object with a config and score */
    public Data(String config, int score) {
        treeString = config;
        treeScore = score;
    }

    /* returns configuration stored in this Data object */
    public String getConfiguration() {
        return this.treeString;
    }

    /* return score in this Data object */
    public int getScore() {
        return this.treeScore;
    }
}