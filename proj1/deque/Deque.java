package deque;

import java.util.Iterator;
import java.util.Objects;

public interface Deque <T>{
    public void addFirst(T item);
    public void addLast(T item);
    default public boolean isEmpty(){
        return size()==0;
    }
    public int size();
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
    public Iterator<T> iterator();
    default public boolean equals (Deque o) {
        boolean f=true;
        if (this.size()!=o.size()) {
            return false;
        }
        Iterator<T> l1=this.iterator();
        Iterator<T> l2=o.iterator();
        while (l1.hasNext() && l2.hasNext()){
            if (l1.next()!=l2.next()){
                f=false;
                break;
            }
        }
        return f;
    }
}
