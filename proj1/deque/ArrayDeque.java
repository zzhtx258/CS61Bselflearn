package deque;

public class ArrayDeque <any> {
    private int size, length, start, end, Rfactor;
    private any[] items;

    public ArrayDeque(){
        Rfactor=2;
        length=8;
        items= (any[]) new Object[length];
        size=0;
        start=0;
        end=0;
    }

    public ArrayDeque(ArrayDeque other){
        items= (any[]) new Object[other.length];
        size=other.size();
        start=other.start;
        end=other.end;
        Rfactor=other.Rfactor;
        for (int i=0; i<size; i++){
            items[i]= (any) other.items[i];
        }
    }

    public any get(int index){
        return items[(index+start)%length];
    }

    public void resize(){
        any[] newitems;
        if (size==length) newitems = (any[]) new Object[length*Rfactor];
        else newitems = (any[]) new Object[length/2];
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

    public void addLast(any x){
        if (size==length) resize();
        if (!this.isEmpty()) end=(end+1)%length;
        items[end]=x;
        size++;
    }

    public void addFirst(any x){
        if (size==length) resize();
        if (!this.isEmpty()) start=(start-1)%length;
        items[start]=x;
        size++;
    }

    public any removeLast(){
        if (size==0) return null;
        any ans=items[end];
        end=(end-1)%length;
        size--;
        if (((double) size/ (double)length)<0.25 && length>8) {
            resize();
        }
        return ans;
    }

    public any removeFirst(){
        if (size==0) return null;
        any ans=items[start];
        start=(start+1)%length;
        size--;
        if (((double) size/ (double)length)<0.25 && length>8) {
            resize();
        }
        return ans;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

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

}
