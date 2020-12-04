/**
 * @author Jagar Yousef
 * This is a simple algorithm to calculate the score of the word and where should it
 * be placed within the suggested words
 * */
public class Ranker {

    /**
     * This method will give a score for the word depending on a number of process
     * @param  word the entered word
     * @param  comparer the compared word (from the dictionary
     * @return the final score of the comparer
     * */
    public static int rank(String word, String comparer){
        int score = 0;


        char[] kuUniqueChars = "êîûşç".toCharArray(); // for every Kurdish special char we add 5
        for (char chComparer : kuUniqueChars){
            if (comparer.contains(String.valueOf(chComparer))){
                score += 2;
            }
        }

        if (word.length() == comparer.length()){ // If the comparer length is the same as the word one we add 5
            score += 4;
        }



        return score;
    } // end of rank()
}
