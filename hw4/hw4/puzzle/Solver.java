package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.Iterator;


public class Solver {
    private Node finalNode;
    private WorldState[] queue;
    private int num;
    private MinPQ<Node> pq;
    public Solver(WorldState initial) {
        pq = new MinPQ<>();
        pq.insert(new Node(initial, 0, null));
        find2();
        queue = new WorldState[num + 1];
        Node temp = finalNode;
        for (int i = num; i >= 0; i -= 1) {
            queue[i] = temp.word;
            temp = temp.prev;
        }
    }



    public int moves() {
        return num;
    }

    public Iterable<WorldState> solution() {
        return new ret();
    }

    private void find2() {
        while (true) {
            Node cur = pq.delMin();
            if (cur.word.isGoal()) {
                finalNode = cur;
                num = cur.num;
                return;
            } else {
                for (WorldState i : cur.word.neighbors()) {
                    if (cur.prev == null || !i.equals(cur.prev.word)) {
                        pq.insert(new Node(i, cur.num + 1, cur));
                    }
                }
            }
        }
    }

    private void find() {
        Node cur = pq.delMin();
        if (cur.word.isGoal()) {
            finalNode = cur;
            num = cur.num;
            return;
        }
        for (WorldState i : cur.word.neighbors()) {
            Node temp = new Node(i, cur.num + 1, cur);
            if (cur.prev != null && i.equals(cur.prev.word)) {
                continue;
            }
            pq.insert(temp);
        }
        find();
    }

    private class ret implements Iterable<WorldState> {
        public Iterator<WorldState> iterator() {
            return new myIterator(num, queue);
        }

        private class myIterator implements Iterator<WorldState> {
            private int max;
            private int num;
            private WorldState[] queue;

            public myIterator(int n, WorldState[] q) {
                max = n;
                num = 0;
                queue = q;
            }

            public boolean hasNext() {
                return num < max;
            }

            public WorldState next() {
                num += 1;
                return queue[num - 1];
            }

        }
    }
    private static class Node implements Comparable<Node>{
        public WorldState word;
        public int num;
        public Node prev;

        public Node(WorldState w, int n, Node p) {
            word = w;
            num = n;
            prev = p;
        }
        public int compareTo(Node o) {
            return this.num + this.word.estimatedDistanceToGoal() - o.num - o.word.estimatedDistanceToGoal();
        }
    }
}