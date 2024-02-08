import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements Iterable<T>{

    private int size;
    private T[] array;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        this.size = size;
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public int size(){
        return this.size();
    }

    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private void grow() {
        if(size == array.length){
            int capacity = array.length * 2;
            array = Arrays.copyOf(array, capacity);
        }
    }

    public T add(T element) {
        grow();
        array[size++] = element;
        return element;
    }

    private void remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Индекс выходит за границы массива");
            return;
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    public void sort(Comparator<Route> comparator){
        int index = 0;
        while (index < this.size) {
            if (index == 0) {
                index++;
            }
            if (comparator.compare((Route) array[index], (Route) array[index - 1]) >= 0) {
                index++;
            } else {
                T temp = (T) array[index];
                array[index] = array[index-1];
                array[index-1]=temp;
                index--;
            }
        }
    }

    public int indexOf(T value){
        for(int i = 0;i < array.length; i++){
            if(array[i].equals(value)){
                return i;
            }
        }
        return -1;
    }

    public T get(int index){
        if(index < 0 || index >= size){
            System.out.println("Индекс выходит за границы массива");
        }
        return (T) array[index];
    }

    public class ArrayListIterator implements Iterator<T> {

        private int index = 0;
        @Override
        public boolean hasNext() {
            return index < size;
        }
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[index++];
        }
    }
}