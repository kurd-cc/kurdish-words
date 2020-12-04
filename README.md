# kurdish-words

 - With more than 21K Kurdish (Kurmanji) words, this is the biggest
   current set of Kurdish words in the world.  
 - These words were crawled from some of the best Kurdish news websites.  
 - The words currently are not filtered or catagorized.

**Files**<br>
*SimpleSpellingCheker.java*: some basic operations to detect the possible alternatives for any word. Usage: <br>
`TreeSet possibleSuggestions= Checker.corrections(WORD_TO_CHECK, WORDS_SET_AS_HASHMAP);`


*SimpleRanker.java*: A very basic way to rank the close suggested words for any word in Kurdish (Kurmanji). Usage: <br>
`int score = Ranker.rank(WORD, COMPARER_WORD);`
