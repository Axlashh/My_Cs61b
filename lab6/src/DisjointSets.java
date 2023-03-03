public class DisjointSets {
    private int[] set;
    private int size;
    public DisjointSets(int n) {
        set = new int[n+1];
        size = n;
        for (int i = 0; i <= n; i += 1) {
            set[i] = i;
        }
    }

    public void validate(int n) {
        if (n > size) {
            throw new RuntimeException("it is not a valid index!");
        }
    }

    public int parent(int n) {
        return find(n);
    }

    public int find(int n) {
        return set[n] == n ? n :(set[n]=find(set[n]));
    }

    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    public void union(int v1, int v2) {
        set[v2] = find(v1);
    }
}