public class main {
    public static void main(String[] args) {
        DisjointSets a = new DisjointSets(8);
        a.union(4,8);
        a.union(5,4);
        System.out.print(a.connected(5,8));
    }
}
