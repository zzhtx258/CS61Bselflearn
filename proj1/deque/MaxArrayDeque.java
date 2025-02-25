package deque;

import java.util.Comparator;
import java.util.Iterator;

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
        T data = super.removeLast();
        if (data!=null && cmp.compare(data, maxItem)>0){
            maxItem=this.max(cmp);
        }
        return data;
    }

    @Override
    public T removeFirst(){
        T data = super.removeFirst();
        if (data!=null && cmp.compare(data, maxItem)>0){
            maxItem=this.max(cmp);
        }
        return data;
    }

    public T max(){
        return maxItem;
    }

    public T max(Comparator<T> c){
        if (this.size()==0) return null;
        Iterator<T> it = this.iterator();
        T curmax = it.next();
        while (it.hasNext()){
            T now= it.next();
            if (c.compare(now, curmax)>0){
                curmax=now;
            }
        }
        it=null;
        return curmax;
    }

    @Override
    public Iterator<T> iterator(){
        return super.iterator();
    }

}
