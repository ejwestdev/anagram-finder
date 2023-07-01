# anagram-finder
This program takes a file path as input and finds all the anagrams in the specified file. It uses a hashmap to store the anagrams, where the key is the sorted form of the word, and the value is a list of words that match the sorted form. The method reads each word from the file, converts it to lowercase, sorts its characters using the quicksort algorithm, and adds it to the hashmap. Finally, it prints the sets of anagrams found in the file.
