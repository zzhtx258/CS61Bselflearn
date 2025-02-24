package deque;

import java.util.Comparator;

public class MaxArrayDeque <T> extends ArrayDeque <T>{
    private T maxItem;
    private Comparator<T> cmp;

    public MaxArrayDeque (Comparator<T> c){
        super();
        cmp=c;
        maxItem=null;
    }

    @Override
    public void addFirst(T x){
        if (maxItem==null) maxItem=x;
        else if (cmp.compare(maxItem,x)<0) maxItem=x;
        super.addFirst(x);
    }

    @Override
    public void addLast(T x){
        if (maxItem==null) maxItem=x;
        else if (cmp.compare(maxItem,x)<0) maxItem=x;
        super.addLast(x);
    }

    @Override
    public T removeLast(){
        maxItem=this.max();
        return super.removeLast();
    }

    @Override
    public T removeFirst(){
        maxItem=this.max();
        return super.removeFirst();
    }

    public T max(){
        return maxItem;
    }

    public T max(Comparator<T> c){
        if (size()==0) return null;
        T newmax = get(1);
        for (int i=0; i<size(); i++){
            if (cmp.compare(newmax,get(i))<0) newmax=get(i);
        }
        return newmax;
    }

}
