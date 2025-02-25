package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque <T>, Iterable<T> {
    private int size;
    private Tnode sentinel;
    public class Tnode{
        public Tnode prev, next;
        public T item;
        public Tnode(T i, Tnode p, Tnode n){
            item=i;
            prev=p;
            next=n;
        }
    }

    public LinkedListDeque (){
        Tnode t=new Tnode(null,null,null);
        t.prev=t;
        t.next=t;
        sentinel=t;
        size=0;
    }

    public LinkedListDeque (LinkedListDeque other){
        Tnode t=new Tnode(null,null,null);
        t.prev=t;
        t.next=t;
        sentinel=t;
        size=0;
        for (int i=0; i<other.size(); i++) {
            this.addLast((T) other.get(i));
        }
    }

    public T getRecursive (int i){
        if (this.isEmpty()) return null;
        if (i==0) return sentinel.next.item;
        else{
            LinkedListDeque temp= new LinkedListDeque(this);
            temp.removeFirst();
            return (T) temp.getRecursive(i-1);
        }
    }

    @Override
    public T get (int index){
        if (this.isEmpty()) return null;
        Tnode p = sentinel;
        while (index>=0){
            p = p.next;
            index--;
        }
        return p.item;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Deque)) return false;
        if (this==o) return true;
        Deque<T> other = (Deque<T>) o;
        boolean f=true;
        if (this.size()!=other.size()) {
            return false;
        }
        for (int i=0; i<this.size(); i++){
            if (this.get(i)!=other.get(i)) return false;
        }
        return true;
    }

    @Override
    public void addFirst (T x){
        size++;
        Tnode p =sentinel;
        Tnode temp = new Tnode(x, p, p.next);
        p.next.prev =temp;
        p.next =temp;
    }

    @Override
    public void addLast (T x){
        size++;
        Tnode p =sentinel.prev, s=sentinel;
        Tnode temp = new Tnode(x, p, s);
        p.next=temp;
        p=temp;
        s.prev=p;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        if (this.isEmpty()) return;
        Tnode s=sentinel.next;
        while (s != sentinel){
            if (s.next!= sentinel) System.out.print(s.item+" ");
            else System.out.println(s.item);
            s=s.next;
        }
    }

    @Override
    public T removeFirst(){
        if (this.isEmpty()) return null;
        Tnode s=sentinel;
        Tnode first=s.next;
        s.next=first.next;
        first.next.prev=s;
        size--;
        return first.item;
    }

    @Override
    public T removeLast(){
        if (this.isEmpty()) return null;
        Tnode l=sentinel.prev;
        Tnode s=sentinel;
        l.prev.next=s;
        s.prev=l.prev;
        T ans = l.item;
        l=l.prev;
        size--;
        return ans;
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        //LinkedListDeque<T> l2 = new LinkedListDeque();
        private Tnode current = sentinel.next; // Start from the head

        @Override
        public boolean hasNext() {
            return current != sentinel;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T data = current.item;
            current = current.next; // Move to the next node
            return data;
        }

    }

}
