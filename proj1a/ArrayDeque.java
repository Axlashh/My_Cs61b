public class ArrayDeque<Type> {
    Type[] arr;
    int len;
    int clen;
    int rear;
    int prev;
    public ArrayDeque(){
       arr = (Type[]) new Object[8];
       len = 8; 
       rear = 0;
       prev = 0;
    }

    private void resize(int n){
        Type[] temp = (Type[]) new Object[n];
        int k = len - prev;
        System.arraycopy(temp, 0, arr, prev, k);
        System.arraycopy(temp, k+1, arr, 0, clen-k);
        rear = len - 1;
        prev = 0;
        len = n;
        arr = temp;
        clen = n / 2;
    }

    public int size(){
        return clen; 
    }

    public Type get(int i){
        return arr[(prev + i) % len];
    }

    public void addFirst(Type item){
        if(clen == len){
            resize(2*len);
        }
        if(prev == 0){
            prev = len;
        }
        prev -= 1;
        arr[prev] = item;
        clen += 1;
    }

    public void addLast(Type item){
        if(clen == len){
            resize(2*len);
        }
        if(rear == len-1){
            rear = -1;
        }
        rear += 1;
        arr[rear] = item;
        clen += 1;
    }

    public Type removeFirst(){
        if(len > 8 && clen < len/2){
           resize(len/2);
        }       
        Type ret = arr[prev];
        arr[prev] = null;
        prev = (prev+1) % len;
        clen -= 1;
        return ret;
    }

    public Type removeLast(){
        if(len > 8 && clen < len/2){
            resize(len/2);
        }
        Type ret = arr[rear];
        arr[rear] = null;
        rear = (rear-1)%len;
        return ret;
    }

    public boolean isEmpty(){
        return clen == 0;
    }

    public void printDeque(){
        for(int i = prev;i%len <= rear;i += 1){
            System.out.print(arr[i]);
            System.out.print(" ");
        }
    }

}
