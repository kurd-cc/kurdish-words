# kurdish-words

 - One of the biggest set of Kurdish (Kurmanji) words
 - words.txt: from Rojava news websites (accurate)
 - words_rudaw.txt: from Rudaw website (less accurate)
 - The words currently are not filtered or catagorized

**Files**<br>
*SimpleSpellingCheker.java*: some basic operations to detect the possible alternatives for any word. Usage: <br>
`TreeSet possibleSuggestions= Checker.corrections(WORD_TO_CHECK, WORDS_SET_AS_HASHMAP);`

*SimpleRanker.java*: A very basic way to rank the close suggested words for any word in Kurdish (Kurmanji). Usage: <br>
`int score = Ranker.rank(WORD, COMPARER_WORD);`

*.db files*: some sample articles from Kurdish kurmanji websites (id, title, content, link)