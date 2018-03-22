import java.util.Comparator;

public class BinarySearchDeluxe {

    //Returns index of first key in a[] that equals the search key, or -1 if no such key
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator){

        if(a == null || key == null || comparator == null) throw new NullPointerException("one or multiple arguments null");
        if(a.length == 0) return -1;

        int start = 0;
        int end = a.length - 1;

        //binary search - if key is in mid or less, set end = mid, otherwise
        //set start = mid. this will allow end to be set to the first
        //instance of the key, and start will converge to end until
        //they're next to each other
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(comparator.compare(key, a[mid]) <= 0) end = mid;
            else start = mid;
        }

        //return index
        if(comparator.compare(key, a[start]) == 0) return start;
        if(comparator.compare(key, a[end]) == 0) return end;

        //return if no such key
        return -1;
    }

    //Returns index of the last key in a[] that equals the search key, or -1 if no such key
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator){

        if(a == null || key == null || comparator == null) throw new NullPointerException("one or multiple arguments null");
        if(a.length == 0) return -1;

        int start = 0;
        int end = a.length - 1;

        //binary search - if key is less than mid, set end = mid, otherwise
        //set start = mid. this will allow start to be set to the last
        //instance of the key, and end will converge to start until
        //they're next to each other
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(comparator.compare(key, a[mid]) < 0) end = mid;
            else start = mid;
        }

        //return index
        if(comparator.compare(key, a[start]) == 0) return start;
        if(comparator.compare(key, a[end]) == 0) return end;

        //return if no such key
        return -1;
    }

    //unit testing
    public static void main(String[] args){

    }

}
