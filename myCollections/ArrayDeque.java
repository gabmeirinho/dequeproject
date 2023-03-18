/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myCollections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @param <E>
 */
public class ArrayDeque<E> implements Deque<E> {
    private static final int CAPACITY=1000; // default array capacity
    // instance variables
    private E[ ] data; // generic array used for storage
    private int f = 0; // index of the front element
    private int sz = 0; // current number of elements
    // constructors
    public ArrayDeque( ) {this(CAPACITY);} // constructs deque with default capacity
    public ArrayDeque(int capacity) { // constructs deque with given capacity
    data = (E[ ]) new Object[capacity]; // safe cast; compiler may give warning
    }
    // methods
    @Override
    public int size( ) { return sz; }
    /** Tests whether the deque is empty.
     * @return  */
    @Override
    public boolean isEmpty( ) { return (sz == 0); }
    /** Inserts an element at the rear of the deque. */

    @Override
    public void addLast(E e) throws IllegalStateException {
        if (sz == data.length) throw new IllegalStateException("Deque is full");
        int avail = (f + sz) % data.length; // use modular arithmetic
        data[avail] = e;
        sz++;
    }
    
    @Override
    public void addFirst(E e) throws IllegalStateException {
        if (sz == data.length) throw new IllegalStateException("Deque is full");
        f = (f + data.length -1) % data.length; // use modular arithmetic
        data[f] = e;
        sz++;
    }

    /** Returns, but does not remove, the first element of the deque (null if empty). */
    @Override
    public E first( ) {
        if (isEmpty( )) return null;
        return data[f];
    }
    
    /** Returns, but does not remove, the last element of the deque (null if empty). */
    @Override
    public E last( ) {
        if (isEmpty( )) return null;
        return data[(f+sz-1)%data.length];
    }
    /** Removes and returns the first element of the deque (null if empty). */
    @Override
    public E removeFirst( ){
        if (isEmpty( )) return null;
        E answer = data[f];
        data[f] = null; // dereference to help garbage collection
        f = (f + 1) % data.length;
        sz--;
        return answer;
    }   
    
    @Override
    public E removeLast( ){
        if (isEmpty( )) return null;
        int r = (f+sz-1) % data.length;
        E answer = data[r];
        data[r] = null; // dereference to help garbage collection
        sz--;
        return answer;
    }
    
    
    /** Devolve um iterador para os elementos guardados no ArrayDeque. */
    @Override
    public Iterator<E> iterator(){
        return new ArrayIterator(); //cria e devolve uma nova instância da classe interna
    }    
   
    //---------------- classe interna ArrayIterator ----------------
    private class ArrayIterator implements Iterator<E> {
        private int j; //posição do elemento a devolver pelo next()
        public ArrayIterator(){
            if(sz==0) j=-1; //j sinaliza, desde logo, fim de iteração
            else j=f;  //iterador começa por referenciar o 1º elemento da fila
        }
        //Se j for igual a -1 (posição inválida) significa
        //que já se chegou ao fim da iteração, e daí não haver um próximo elemento
        @Override
        public boolean hasNext( ) { return j != -1; }
        //Avança-se o j, mas caso o avanço seja do último elemento para a posição
        //seguinte, força-se a que o j assuma um valor inválido (-1) para que não hajam
        //dúvidas de que se chegou ao fim na próxima invocação do hasNext()
        @Override
        public E next( ){
            if (j == -1)  throw new NoSuchElementException("Sem próximo elemento");
            int j_atual=j;
            j=(j+1)%data.length; //incremento circular do j 
            if(j==(f+sz)%data.length) j=-1; //Sendo j -1, não haverá dúvidas de que
                                            //se chegou ao fim ...
            return data[j_atual];//devolve o elemento da posição j antes de incrementado
        }
        @Override
        public void remove() { throw new UnsupportedOperationException(); }   
    } //------------ fim da classe interna ArrayIterator ------------
    
} //------------ fim da classe externa ArrayDeque------------

