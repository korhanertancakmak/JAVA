package JAVA.MyList;

import java.util.Arrays;

public class MyList<T> {

    private int capacity;
    private int size = 0;
    private Object[] arr;

    public MyList() {
        this.capacity = 10;
        this.arr = new Object[10];
    }

    public MyList(int capacity) {
        this.capacity = capacity;
        this.arr = new Object[this.capacity];
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int size() {
        return this.size;
    }

    public void add (T data) {
        if (this.size == this.capacity - 1) {
            this.capacity *= 2;
            this.arr = Arrays.copyOf(arr, this.capacity);
        }
        arr[size] = data;
        this.size++;
    }

    @SuppressWarnings("unchecked")
    public T get (int index) {
        return (T) this.arr[index];
    }

    public void remove (int index) {
        for (int i = index; i <= this.size; i++) {
            this.arr[i] = this.arr[i + 1];
        }
        this.size--;
    }

    public void set (int index, T data) {
        arr[index] = data;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        if (this.size != 0) {
            res.append("[");
            for (int i = 0; i < size; i++) {
                if (this.arr[i] != null && i != size - 1) {
                    res.append(this.arr[i]).append(",");
                } else if (i == size - 1) {
                    res.append(this.arr[i]).append("]");
                }
            }
        } else {
            res = new StringBuilder("[]");
        }
        return res.toString();
    }

    public int indexOf(T data) {
        for (int i = 0; i < size; i++) {
            if (this.arr[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(T data) {
        for (int i = size - 1; i >= 0; i--) {
            if (this.arr[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.arr[0] == null;
    }

    public Object[] toArray() {
        Object[] newArr = new Object[this.size];
        System.arraycopy(this.arr, 0, newArr, 0, size);
        return newArr;
    }

    public void clear() {
        for (int i = size - 1; i >= 0; i--) {
            remove(i);
        }
        this.capacity = 10;
    }

    public MyList<T> subList(int start, int finish) {
        int sublistSize = finish - start;
        MyList<T> sublist = new MyList<>(sublistSize);

        for (int i = start; i < finish; i++) {
            sublist.add(get(i));
        }

        return sublist;
    }

    public boolean contains(T data) {
        for (int i = 0; i < size; i++) {
            if (this.arr[i].equals(data)) {
                return true;
            }
        }
        return false;
    }
}

class Main {
    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();
        // Returns whether the list is empty or not
        System.out.println("List status : " + (list.isEmpty() ? "Empty" : "Not empty"));
        System.out.println("The number of elements in array : " + list.size());
        System.out.println("The capacity of array : " + list.getCapacity());
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        System.out.println("The number of elements in array : " + list.size());
        System.out.println("The capacity of array : " + list.getCapacity());
        list.add(50);
        list.add(60);
        list.add(70);
        list.add(80);
        list.add(90);
        list.add(100);
        list.add(110);
        System.out.println("The number of elements in array : " + list.size());
        System.out.println("The capacity of array : " + list.getCapacity());
        int index = 2;
        System.out.println("The value at " + index + ". index : " + list.get(index));
        index = 10;
        System.out.println("The value at " + index + ". index : " + list.get(index));
        list.remove(2);
        index = 2;
        System.out.println("The value at " + index + ". index : " + list.get(index));
        index = 10;
        System.out.println("The value at " + index + ". index : " + list.get(index));
        index = 9;
        System.out.println("The value at " + index + ". index : " + list.get(index));
        index = 0;
        System.out.println("The value at " + index + ". index : " + list.get(index));
        list.set(0, 100);
        System.out.println("The value at " + index + ". index : " + list.get(index));
        System.out.println(list);

        // Empties the list completely and returns it to its default size
        list.clear();
        System.out.println(list);
        
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(20);
        list.add(50);
        list.add(60);
        list.add(70);
    
        list.set(5, 20);
        System.out.println("The value at " + index + ". index : " + list.get(index));
        System.out.println(list);

        // Returns the first index it finds
        System.out.println("Index : " + list.indexOf(20));
        // If not found it returns -1
        System.out.println("Index :" + list.indexOf(120));
        // Returns the last index it finds
        System.out.println("Index : " + list.lastIndexOf(20));
        // Returns whether the list is empty or not
        System.out.println("List status : " + (list.isEmpty() ? "Empty" : "Not empty"));
        // Returns the list as an Object[] array.
        Object[] array = list.toArray();
        System.out.println("The first element of the Object array :" + array[0]);
        // Creating a sublist of list data type
        MyList<Integer> subMyArray = list.subList(0, 3);
        System.out.println(subMyArray.toString());
        // Queried whether the value is in the list
        System.out.println("Value 20 on my list : " + list.contains(20));
        System.out.println("Value 120 on my list : " + list.contains(120));

        // Empties the list completely and returns it to its default size
        list.clear();
        System.out.println(list);
    }
}