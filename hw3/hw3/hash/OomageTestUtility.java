package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] bucket = new int[M];
        for (Oomage i : oomages) {
            bucket[(i.hashCode() & 0x7FFFFFFF) % M] += 1;
        }
        for (int i : bucket) {
            if (i <= (double) oomages.size() / 50 || i > (double) oomages.size() / 2.5) {
                return false;
            }
        }
        return true;
    }
}
