package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {
        String[] strings_1 = {"text 11", "text 12", "text 13", "text 14", "text 15", "--- 1 ---"};
        String[] strings_2 = {"text 21", "text 22", "text 23", "text 24", "text 25", "--- 2 ---"};

        ArrayIterator<String> itr_1 = new ArrayIterator<String>(strings_1);
        ArrayIterator<String> itr_2 = new ArrayIterator<String>(strings_2);

        ConcatIterator<String> itr_0 = new ConcatIterator<String>(itr_1, itr_2);

        for (int i = 0; i<10; i++){
            System.out.println(itr_0.next());
        }
    }

}

// Итератор
class ArrayIterator<T> implements Iterator<T>{

    private T[] array;
    private int index = 0;

    public ArrayIterator(T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public T next() {
        if(!hasNext())
            throw new NoSuchElementException();
        return array[index++];
    }

    @Override
    public void remove() {
    }
}

// Итератор с итераторами
class ConcatIterator<T> implements Iterator<T> {

    private Iterator<T> innerIterator1;
    private Iterator<T> innerIterator2;

    public ConcatIterator (Iterator<T> innerIterator1, Iterator<T> innerIterator2) {
        this.innerIterator1 = innerIterator1;
        this.innerIterator2 = innerIterator2;
    }

    @Override
    public boolean hasNext() {
        while (innerIterator1.hasNext()) return true;
        while (innerIterator2.hasNext()) return true;
        return false;
    }

    @Override
    public T next() {
        if(!hasNext())
            throw new NoSuchElementException();

        while (innerIterator1.hasNext()) return innerIterator1.next();
        while (innerIterator2.hasNext()) return innerIterator2.next();
        return null;
    }

    @Override
    public void remove() {
    }
}