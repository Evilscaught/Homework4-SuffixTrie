After implementing this substring search tool using the TrieST class, you should have noticed 
that this isn't a particularly efficient way to go about solving the problem: there's a lot of 
wasted space in the data structure, and a lot of wasted calculation in the search step. 

- Please describe the approach you've used. What is the run time in terms of the size of S, 
and in terms of the length of each q?  I recommend testing (doing experiments!) with some 
large strings, for confirmation. 

- What approaches could you take to improve on this clever idea?


ANSWER (Runtime and Approach):
First I constructed a suffixes array of sample.in, then I created a populated a Trie of the suffixes array.
When finding the pattern queries in the suffix array, it takes compares proportional to the length of each 
pattern we're searching for, (I've been using the method keysWithPrefix() provided by the TrieST API).

ANSWER (Improvements):
Instead of using a Suffix Trie, I could use a Suffix Tree (A compressed representation of a Trie).  
