/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package myCollections;

/**
 *
 * @param <E>
 */
public interface Deque<E> extends Iterable<E>{
    /**Returns the numer of elements in the dequ
     * @return e*/
    int size();
    /*Tests whether the deque is empty*/
    boolean isEmpty();
    /**Returns, but does not remove, the last element of the deque(null if empty)
     * @return .*/
    E first();
    /**Returns, but does not remove, the last element of the deque(null if empty)
     * @return .*/
    E last();
    /**Inserts an element at the back of the deque
     * @param e.*/
    void addFirst(E e);
    /**Inserts an element at the back of the deque
     * @param e.*/
    void addLast(E e);
    /**Returns, but does not remove, the last element of the deque(null if empty)
     * @return .*/
    E removeFirst();
    /**Returns, but does not remove, the last element of the deque(null if empty)
     * @return .*/
    E removeLast();
}
