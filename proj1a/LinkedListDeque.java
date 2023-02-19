public class LinkedListDeque<Type> {
    private class Node<Type> {
        Node prev;
        Node next;
        Type element;
        public Node(Type a,Node nprev,Node nnext) {
           element = a;
           prev = nprev;
           next = nnext; 
        }
    }

    private int len;
    private Node lld;

    public LinkedListDeque(LinkedListDeque other){
        Type k = null;
        lld = new Node<Type>(k,null,null);
        len = 0;
        int l = other.size();
        for(int i=0;i<l;i += 1){
            this.addLast((Type)other.get(i+1));
        }
    }

    public LinkedListDeque(){
        Type k = null;
        lld = new Node<Type>(k,null,null);
        lld.next = lld;
        lld.prev = lld;
        len = 0;
    }

    public boolean isEmpty(){
        if(len == 0){
            return true;
        }
        return false;
    }

    public void printDeque(){
        int i = 0;
        Node temp = lld;
        while(i<len){
            temp = temp.next;
            i += 1;
            System.out.print(temp.element);
            System.out.print(" ");
        }
    }

    public void addLast(Type a){
        Node temp = new Node<Type>(a,lld.prev,lld);
        lld.prev = temp;
        temp.prev.next = temp;
        len += 1;
    }

    public void addFirst(Type item){
        Node temp = new Node<Type>(item,lld.next,lld);
        lld.next = temp;
        temp.next.prev = temp;
        len += 1;
    }    

    public Type removeLast(){
        Type ret = lld.prev.element;
        if(len <= 0){
            System.out.println("Error!");
        }
        len -= 1;
        lld.prev.prev.next = lld;
        lld.prev = lld.prev.prev;
        return ret;
    }

    public Type removeFirst(){
        Type ret = lld.next.element;
        if(len <= 0){
            System.out.println("Error!");
        }
        len -= 1;
        lld.next.next.prev = lld;
        lld.next = lld.next.next;
        return ret;
    }
    public Type get(int i){
        if(i > len){
            System.out.println("Error!");
        }
        Node temp = lld;
        for(int j=0; j<i; j += 1){
            temp = temp.next;
        }
        return (Type)temp.element;
    }

    public int size(){
        return len;
    }
}