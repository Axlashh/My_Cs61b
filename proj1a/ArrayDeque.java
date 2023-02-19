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

    private void resizeb(int n) {
        T[] temp = (T[]) new Object[n];
        int k = len - prev;
        System.arraycopy(temp, 0, arr, prev, k);
        System.arraycopy(temp, k, arr, 0, clen - k);
        len = n;
        clen = n / 2;
        rear = clen;
        prev = 0;
        arr = temp;
    }

    private void resizes(int n) {
        T[] temp = (T[]) new Object[n];
        if (prev < rear) {
            System.arraycopy(temp, 0, arr, prev % len, clen);
        } else {
            System.arraycopy(temp, 0, arr, prev, len - prev);
            System.arraycopy(temp, len - prev, arr, 0, clen - len + prev);
        }
        len = n;
        prev = 0;
        rear = 0;
        arr = temp;
    }

    public int size() {
        return clen; 
    }

    public T get(int i) {
        return arr[(prev + i) % len];
    }

    public void addFirst(T item) {
        if (clen == len) {
            resizeb(2 * len);
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
            resizeb(2 * len);
        }
        arr[rear] = item;
        if (rear == len - 1) {
            rear = -1;
        }
        rear = (rear + 1) % len;
        clen += 1;
    }

    public T removeFirst() {
        if (!isEmpty()) {
            if (len > 8 && clen <= len / 2) {
                resizes(len / 2);
            }       
            T ret = arr[prev];
            arr[prev] = null;
            prev = (prev + 1) % len;
            clen -= 1;
            return ret;
        }
        return null;
    }

    public T removeLast() {
        if (!isEmpty()) {
            if (len > 8 && clen <= len / 2) {
                resizes(len / 2);
            }
            rear = (rear - 1) % len;
            T ret = arr[rear];
            arr[rear] = null;
            clen -= 1;
            return ret;
        }
        return null;
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
