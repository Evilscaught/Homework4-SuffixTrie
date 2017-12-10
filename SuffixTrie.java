import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.TrieST;

/*Author:               Scott McKay
 *Date of Creation:     Thursday, December 7th, 2017 @4:21 p.m. MST
 *Purpose of Program:   Search for repetitive strings in a file.
 *Course:               Data Structures [CSCI-323 Section 00]
 *Semester:             Autumn Semester 2017
 *Notes:            
 *Collaboration:        None
 */


public class SuffixTrie
{   
    private TrieST<Integer> trie;
    
    public SuffixTrie(String words, String query)
    {
        if ( (words == null) || (query == null) )
        {
            throw new NullPointerException("File paths cannot be empty");
        }
        trie = new TrieST<Integer>();
        
        populateTrie(words);
        printQueries(trie, query);
    }
    
    //Populate a Trie with suffixes
    private void populateTrie(String words)
    {   
        In in = new In(words);
        String[] suffixes = suffixes(in.readLine());
        
        //Populate trie
        for (int index = 0; index < suffixes.length; index++)
        {
            //StdOut.println(index + "\t" + suffixes[index]);
            trie.put(suffixes[index], index);
        }
    }
    
    //Print occurrences of search queries
    private void printQueries(TrieST<Integer> trie, String query)
    {
        MinPQ<Integer> minPQ = new MinPQ<Integer>();
        In in = new In(query);
        while (in.hasNextLine())
        {
            boolean firstExec = true;
            String prefix = in.readString();
            StdOut.print(prefix + "\t");
            
            Iterable<String> keys = trie.keysWithPrefix(prefix);
            if (keys.iterator().hasNext())
            {
                StdOut.print("true (");
            }
            else
            {
                StdOut.print("false");
            }
           
            for (String key : keys)
            {
                minPQ.insert(trie.get(key)); 
            }
                
            while (!minPQ.isEmpty())
            {
                if (firstExec)
                {
                    StdOut.print(minPQ.delMin());
                    firstExec = false;
                }
                else
                {
                    StdOut.print(", " + minPQ.delMin());
                }
            }
            
            if (keys.iterator().hasNext())
            {
                System.out.print(")");
            }
            
            StdOut.println();
        }
    }
    
    //Create suffix array
    private String[] suffixes(String words)
    {   
        int length        = words.length();      
        String[] suffixes = new String[length];
        
        //Create suffixes array
        for (int index = 0; index < length; index++)
        {
            suffixes[index] = words.substring(index, length);
        }
        //Arrays.sort(suffixes);
        
        return suffixes;
    }
    
    //Constructor takes two arguments:
    //     1.] Name of the file that contains a long string of S letters (no spaces)
    //     2.] Name of a file that contains search strings
    @SuppressWarnings("unused")
    public static void main(String[] args)
    {
            SuffixTrie suffixTrie = new SuffixTrie(args[0], args[1]);
    }
}