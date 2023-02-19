public class ArrayDeque<T> {
    private T[] arr;
    private int len;
    private int clen;
    private int rear;
    private int prev;
    public ArrayDeque() {
        arr = (T[]) new Object[8];
        len = 8; 
        rear = 0;
        prev = 0;
    }

    private void resize(int n) {
        T[] temp = (T[]) new Object[n];
        int k = len - prev;
        System.arraycopy(temp, 0, arr, prev, k);
        System.arraycopy(temp, k + 1, arr, 0, clen - k);
        len = n;
        rear = len - 1;
        prev = len;
        arr = temp;
        clen = n / 2;
    }

    public int size() {
        return clen; 
    }

    public T get(int i) {
        return arr[(prev + i) % len];
    }

    public void addFirst(T item) {
        if (clen == len) {
            resize(2 * len);
        }
        if (prev == 0) {
            prev = len;
        }
        prev -= 1;
        arr[prev] = item;
        clen += 1;
    }

    public void addLast(T item) {
        if (clen == len) {
            resize(2 * len);
        }
        arr[rear] = item;
        if (rear == len-1) {
            rear = -1;
        }
        rear += 1;
        clen += 1;
    }

    public T removeFirst() {
        if (len > 8 && clen <= len/2) {
           resize(len / 2);
        }       
        T ret = arr[prev];
        arr[prev] = null;
        prev = (prev + 1) % len;
        clen -= 1;
        return ret;
    }

    public T removeLast() {
        if (len > 8 && clen <= len / 2) {
            resize(len / 2);
        }
        rear = (rear - 1) % len;
        T ret = arr[rear];
        arr[rear] = null;
        return ret;
    }

    public boolean isEmpty() {
        return clen == 0;
    }

    public void printDeque() {
        for (int i = prev; i % len <= rear; i += 1) {
            System.out.print(arr[i]);
            System.out.print(" ");
        }
    }

}
