import java.util.Comparator;

public class Term implements Comparable<Term> {

    private final String query;
    private final long weight;

    //Initializes a term with the given query string and weight
    public Term(String query, long weight) {

        if(query == null) throw new NullPointerException("query is null");
        if(weight < 0) throw new IllegalArgumentException("weight is negative");

        this.query = query;
        this.weight = weight;
    }

    //Compares the two terms in descending order by weight
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }

        /**
         * PRIVATE CLASS
         */
        private static final class ReverseWeightOrder implements Comparator<Term>{

            public int compare(Term term1, Term term2){
                if(term1.weight > term2.weight) return -1;
                if(term1.weight == term2.weight) return 0;
                return 1;
            }

        }

    // Compares the two terms in lexicographic order but using only the first
    // r characters of each query
    public static Comparator<Term> byPrefixOrder(int r){
        if(r < 0) throw new IllegalArgumentException("r is negative");
        return new PrefixOrder(r);
    }

        /**
         * PRIVATE CLASS
         */
        private static final class PrefixOrder implements Comparator<Term>{

            private int r;

            PrefixOrder(int r){
                this.r = r;
            }

            public int compare(Term term1, Term term2){
                String string1 = term1.query;
                String string2 = term2.query;
                int min;

                //get min
                if(string1.length() < string2.length()){
                    min = string1.length();
                } else {
                    min = string2.length();
                }

                //compares
                if(min >= r){
                    return string1.substring(0,r).compareTo(string2.substring(0,r)); //just compare
                } else if (string1.substring(0,min).compareTo(string2.substring(0,min)) == 0){
                    if(string1.length() == min) return -1; // if first string is smaller, then it'll be less than string2
                    else return 1; //second is smaller

                } else return string1.substring(0,min).compareTo(string2.substring(0,min));

            }
        }

    //Compares the two terms in lexicographic order by query
    public int compareTo(Term that){
        String string1 = this.query;
        String string2 = that.query;
        return string1.compareTo(string2);
    }

    //Returns a string rep. of this term in following format:
    //the weight, followed by a tab, followed by the query
    public String toString(){
        return Long.toString(weight) + "\t" + query;
    }

    //unit testing
    public static void main(String[] args){

    }

}