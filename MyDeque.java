import java.util.*;

public class MyDeque<E> {
    private E[] data;
    private int size;
    private int start;
    private int end;

    public MyDeque() {
        @SuppressWarnings("unchecked")
        E[] d = (E[]) new Object[10];
        data = d;
        size = 0;
        start = 0;
        end = 0;
    }

    public MyDeque(int initialCapacity) {
        @SuppressWarnings("unchecked")
        E[] d = (E[]) new Object[initialCapacity];
        data = d;
        size = 0;
        start = 0;
        end = 0;
    }

    public int size() {
        return size;
    }

    public String toString() {
        if(size == 0) return "[]";
        String result = "[";
        if(start > end){ //add start segment, then end segment
            for(int i = start; i < data.length; i++){
                result += data[i] + ", ";
            }
            for(int i = 0; i <= end; i++){
                result += data[i];
                if(i < end) result += ", ";
            }
        } else { //start = end or start < end
            for(int i = start; i <= end; i++){
                result += data[i];
                if(i < end) result += ", ";
            }
        }
        result += "]";
        return result;
    }

    public void addFirst(E element) {
        if(element == null) throw new NullPointerException();
        if (end == start || size == data.length || data.length == 0) {
            resize();
        }
        if(size == 0){ //first element
            data[start] = element;
            size++;
        } else if(start == 0){
            start = data.length-1;
            data[start] = element;
            size++;
        } else {
            start--;
            data[start] = element;
            size++;
        }
    }

    public void addLast(E element) {
        if(element == null) throw new NullPointerException();
        if (end == start || size == data.length || data.length == 0) {
            resize();
        }
        end++;
        if(end == data.length) {
            end = 0;
            data[end] = element;
        } else {
            data[end] = element;
        }
        size++;
    }

    public E removeFirst() {
        if(size == 0) throw new NoSuchElementException();
        E removed = data[start];
        data[start] = null;
        if(start == data.length - 1){
            start = 0;
            size--;
        } else {
            start++;
            size--;
        }
        return removed;
    }

    public E removeLast() {
        if(size == 0) throw new NoSuchElementException();
        E removed = data[end];
        data[end] = null;
        if(end == 0) {
            end = data.length - 1;
            size--;
        } else {
            end--;
            size--;
        }
        return removed;
    }

    public E getFirst() {
        if(size == 0) throw new NoSuchElementException();
        return data[start];
    }

    public E getLast() {
        if(size == 0) throw new NoSuchElementException();
        return data[end];
    }

    private void resize() {
        // creating new array
        @SuppressWarnings("unchecked")
        E[] move = (E[]) new Object[size * size + 1];

        if(data.length == 0){
            //do nothing
        } else if(start == end){ //not sure if this is necessary but whatever
            move[0] = data[start];
            start = 0;
            end = 0;
        } else if(start > end){
            int ind = 0;
            for(int i = start; i < data.length; i++){
                move[ind] = data[i];
                ind++;
            }
            for(int i = 0; i <= end; i++){
                move[ind] = data[i];
                ind++;
            }
            start = 0;
            end = size - 1;
        } else {
            int ind = 0;
            for(int i = start; i <= end; i++){
                move[ind] = data[i];
                ind++;
            }
            start = 0;
            end = size - 1;
        }

        //copies move to data
        data = Arrays.copyOf(move, move.length);
    }
}
