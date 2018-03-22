import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class Autocomplete {

    private final Term[] terms;

    //Initializes the data structure for the given array of terms
    public Autocomplete(Term[] terms){
        if(terms == null) throw new NullPointerException("terms is null");

        this.terms = terms;
        Arrays.sort(terms);
    }

    //Returns all terms that start with the given prefix, in descending order of weight
    public Term[] allMatches(String prefix){
        if(prefix == null) throw new NullPointerException("terms is null");

        //save prefix
        Term prefixTerm = new Term(prefix, 0);

        //get all matching terms
        int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));
        if(firstIndex == -1) return new Term[0]; //if theres no term
        int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));

        //initialize matches array and sorts it by reverse weight order
        Term[] matches = new Term[lastIndex - firstIndex + 1];
        for(int i = 0; i < matches.length; i++){
            matches[i] = terms[firstIndex++];
        }
        Arrays.sort(matches, Term.byReverseWeightOrder());

        return matches;
    }

    //Returns the number of terms that start with the given prefix
    public int numberOfMatches(String prefix){
        if(prefix == null) throw new NullPointerException("terms is null");

        Term prefixTerm = new Term(prefix, 0);

        //get all matching terms
        int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));
        if(firstIndex == -1) return 0; //if no term exists
        int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));

        //return difference
        return lastIndex - firstIndex + 1;

    }

    //unit testing
    public static void main(String[] args){
        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }

}
