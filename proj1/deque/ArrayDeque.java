package deque;

import java.util.Iterator;

public class ArrayDeque <T> implements Deque <T> {
    private int size, length, start, end, Rfactor;
    private T[] items;

    public ArrayDeque(){
        Rfactor=2;
        length=8;
        items= (T[]) new Object[length];
        size=0;
        start=0;
        end=0;
    }

    public ArrayDeque(ArrayDeque other){
        items= (T[]) new Object[other.length];
        size=other.size();
        start=other.start;
        end=other.end;
        Rfactor=other.Rfactor;
        for (int i=0; i<size; i++){
            items[i]= (T) other.items[i];
        }
    }

    @Override
    public T get(int index){
        return items[(index+start)%length];
    }

    public void resize(){
        T[] newitems;
        if (size==length) newitems = (T[]) new Object[length*Rfactor];
        else newitems = (T[]) new Object[length/2];
        if (start<end){
            for (int i=start; i<=end; i++){
                newitems[i-start]=items[i];
            }
        }
        else{
            for (int i=0; i+start<length; i++){
                newitems[i]=items[start+i];
            }
            for (int i=0; i<=end; i++){
                newitems[length-start+i]=items[i];
            }
        }
        length=length*Rfactor;
        start=0;
        end=size-1;
        items=newitems;
        newitems=null;
    }

    @Override
    public void addLast(T x){
        if (size==length) resize();
        if (!this.isEmpty()) end=(end+1)%length;
        items[end]=x;
        size++;
    }

    @Override
    public void addFirst(T x){
        if (size==length) resize();
        if (!this.isEmpty()) start=(start-1+length)%length;
        items[start]=x;
        size++;
    }

    @Override
    public T removeLast(){
        if (size==0) return null;
        T ans=items[end];
        end=(end-1+length)%length;
        size--;
        return ans;
    }

    @Override
    public T removeFirst(){
        if (size==0) return null;
        T ans=items[start];
        start=(start+1)%length;
        size--;
        return ans;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        int cnt=0;
        if (start<end){
            for (int i=start; i<=end; i++){
                System.out.print(items[i]+" ");
                cnt++;
                if (cnt==size-1) break;
            }
        }
        else{
            for (int i=0; i+start<length; i++){
                System.out.print(items[i+start]+" ");
                cnt++;
                if (cnt==size-1) break;
            }
            for (int i=0; i<=end; i++){
                System.out.print(items[i]+" ");
                cnt++;
                if (cnt==size-1) break;
            }
        }
        System.out.println(items[end]);
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index = 0; // Start from the front
        private int elementsReturned = 0; // Count of elements returned

        @Override
        public boolean hasNext() {
            return elementsReturned < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T element = (T) items[(start + index) % length];
            index++;
            elementsReturned++;
            return element;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }

}
