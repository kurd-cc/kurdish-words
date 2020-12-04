/**
 * @author Jagar Yousef
 */

import java.util.*;

public class Checker {
    /**
     * This function check the possible suggestions of a bad word an return the suggestions in a TreeSet
     * @param badWord the bad word to check its suggestions
     * @param dictionary the dictionary to check the possible correct words from
     * @return The possible corrections of the bad word
     * */
    public static TreeSet corrections(String badWord, HashSet<String> dictionary) {
        TreeSet resultSet = new TreeSet<String>();

        //Check all possibilities, if they are empty they won't be add to the result set
        TreeSet deletedLetters = deleteAnyLetter(badWord, dictionary);
        TreeSet changedLetters = changeAnyLetter(badWord, dictionary);
        TreeSet insertedLetters = insertAnyLetter(badWord, dictionary);
        TreeSet swappedLetters = swapNeighborLetter(badWord, dictionary);
        TreeSet insertedSpaces = insertSpace(badWord, dictionary);
        TreeSet containedWords = contained(badWord, dictionary);
        TreeSet uniqueKurdishLetters = uniqueKurdishLetters(badWord, dictionary);



        if	(!deletedLetters.isEmpty()) {
            resultSet.addAll(deletedLetters);
        }
        if	(!changedLetters.isEmpty()) {
            resultSet.addAll(changedLetters);
        }
        if	(!insertedLetters.isEmpty()) {
            resultSet.addAll(insertedLetters);
        }
        if	(!swappedLetters.isEmpty()) {
            resultSet.addAll(swappedLetters);
        }
        if	(!insertedSpaces.isEmpty()) {
            resultSet.addAll(insertedSpaces);
        }
        if (!containedWords.isEmpty()){
            resultSet.addAll(containedWords);
        }
        if (!uniqueKurdishLetters.isEmpty()){
            resultSet.addAll(uniqueKurdishLetters);
        }


        return resultSet;

    }

    /**
     *  Insert a space at any point in the misspelled word (and check that both of the words that are produced are in the dictionary)
     * @param badWord the word to check
     * @param dictionary the dictionary to compare with
     * @return the result if there is any
     * */
    static TreeSet insertSpace(String badWord, HashSet dictionary) {
        TreeSet resultSet = new TreeSet<String>(); // Set to return results
        for (int i = 0; i < badWord.length(); i++) { // Every character in the word
            String word1 = badWord.substring(0, i); // First word before the space
            String word2 = badWord.substring(i); // Second word after the space
            String testWord = word1 + " " + word2; // The resulted word
            testWord = testWord.toLowerCase(); // All db entries should be in lowercase
            if	(dictionary.contains(word1) && dictionary.contains(word2)) { //Both are in the dictionary
                resultSet.add(testWord);
            }
        }
        return resultSet;
    }

    /**
     * Swap any two neighboring characters every characters in the word and check if the dictionary contains the new word
     * @param badWord the word to check
     * @param dictionary the dictionary to compare with
     * @return the result if there is any
     * */
    static TreeSet swapNeighborLetter(String badWord, HashSet dictionary) {
        TreeSet resultSet = new TreeSet<String>(); // Set to return results
        for (int i = 1; i < badWord.length(); i++) { // Every character in the word, starting with 1 since it will be a swap
            String subStr1 = badWord.substring(0, i - 1); // First part before the current character
            char ch1 = badWord.charAt(i - 1); // The current character
            char ch2 = badWord.charAt(i); // The next character
            String subStr2 = badWord.substring(i + 1); // Second part after the second character
            String testWord = subStr1 + ch2 + ch1 + subStr2; // The resulted string
            testWord = testWord.toLowerCase(); // All db entries should be in lowercase
            if (dictionary.contains(testWord))
                resultSet.add(testWord);
        }
        return resultSet;
    }

    /**
     * Insert every character in every place in the word and check if the dictionary contains the new word
     * @param badWord the word to check
     * @param dictionary the dictionary to compare with
     * @return the result if there is any
     * */
    static TreeSet insertAnyLetter(String badWord, HashSet dictionary) {
        char[] kuChars = "abcçdeêfghiîjklmnopqrsştuûvwxyz".toCharArray();
        TreeSet resultSet = new TreeSet<String>(); // Set to return results
        for (int i = 0; i < badWord.length(); i++) { // Every character in the word
            for	(char ch : kuChars) { // Insert in every place, every character
                String testWord = badWord.substring(0, i) + ch + badWord.substring(i); // Inserting
                testWord = testWord.trim().toLowerCase(); // All db entries should be in lowercase
                if	(dictionary.contains(testWord)) { // If it is in the dictionary add it to the results
                    resultSet.add(testWord);
                }
            }
        }
        return resultSet;
    }


    /**
     * Change every character in the word and check if the dictionary contains the new word
     * @param badWord the word to check
     * @param dictionary the dictionary to compare with
     * @return the result if there is any
     * */
    static TreeSet changeAnyLetter(String badWord, HashSet dictionary) {
        char[] kuChars = "abcçdeêfghiîjklmnopqrsştuûvwxyz".toCharArray();
        TreeSet resultSet = new TreeSet<String>(); // Set to return results
        for (int i = 0; i < badWord.length(); i++) { // Every character in the word
            for	(char ch : kuChars) { // Change it with every character
                String testWord = badWord.substring(0, i) + ch + badWord.substring(i+1); // Changing
                testWord = testWord.toLowerCase();
                if	(dictionary.contains(testWord)) { // If it is in the dictionary add it to the results
                    resultSet.add(testWord);
                }
            }
        }
        return resultSet;
    }

    /**
     * Delete every character in the word and check if the dictionary contains the new word
     * @param badWord the word to check
     * @param dictionary the dictionary to compare with
     * @return the result if there is any
     * */
    static TreeSet deleteAnyLetter(String badWord, HashSet dictionary) {
        TreeSet resultSet = new TreeSet<String>(); // Set to return results
        for (int i = 0; i < badWord.length(); i++) { // Every character in the word
            String testWord = badWord.substring(0, i) + "" + badWord.substring(i+1);  // Remove it
            if	(dictionary.contains(testWord)) { // If it is in the dictionary add it to the results
                resultSet.add(testWord);

            }

        }
        return resultSet;
    }


    /**
     * Check if any of the dictionary words contains this badWord
     * @param badWord the word to check
     * @param dictionary the dictionary to compare with
     * @return the result if there is any
     * */
    static TreeSet contained(String badWord, HashSet dictionary) {
        TreeSet resultSet = new TreeSet<String>(); // Set to return results
        if (badWord.length() >= 3){ // Only for words more than or equals 3 letters
            for (Object word : dictionary){ // We will go through the dictionary to check any word apply
                if (((String) word).contains(badWord)){
                    resultSet.add(word.toString());
                }
            }
        }
        return resultSet;
    }


    /**
     * Check if there are unique Kurdish letters written in English (e.g. sh --> ş)
     * @param badWord the word to check
     * @param dictionary the dictionary to compare with
     * @return the result if there is any
     * */
    static TreeSet uniqueKurdishLetters(String badWord, HashSet dictionary) {
        TreeSet resultSet = new TreeSet<String>(); // Set to return results
        if (badWord.contains("ch")){ // ch could be ç
            String word = badWord.replaceAll("ch", "ç");
            if (dictionary.contains(word)){
                resultSet.add(word);
            }
        }
        if (badWord.contains("sh")){ // sh could be ş
            String word = badWord.replaceAll("sh", "ş");
            if (dictionary.contains(word)){
                resultSet.add(word);
            }
        }

        String[] possibleDuplicates = {"ee", "ei", "ie", "ii", "ae", "ea", "ai", "ia"}; // Famous Kurdish spelling mistakes
        String[] possibleMeanings = {"î", "ê", "iya", "iyê", "iyan", "a", "e", "i"}; // Possible meanings of them
        for (String str : possibleDuplicates){
            if (badWord.contains(str)){
                for (String corrected : possibleMeanings){
                    String word = badWord.replace(str, corrected);
                    if (dictionary.contains(word)){
                        resultSet.add(word);
                    }
                }
            }
        }
        return resultSet;
    }
}
