package com.pyramidacceptors.ptalk.api;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/**
 * Created by catix on 11/13/2015.
 *
 * This was the only class we were using from ACC so let's eliminate
 * a dependency and protect from VU#576313 (though that didn't really affect us)
 */
final class CircularFifoQueue<T> {

    private final Queue<T> delegate;
    private final int maxSize;

    /**
     * Creates a new circular first-in, first-out queue. Once
     * maxSize elements have been added, the older element will
     * be dropped from the queue
     * @param maxSize number of elements to accept before evicting old elements
     */
    public CircularFifoQueue(int maxSize) {
        this.maxSize = maxSize;
        this.delegate = new ArrayDeque<>(maxSize);
    }

    /**
     * Adds item to the queue. If the size of the queue
     * has meet maxsize, the oldest element will be evicted.
     * @param item element to add to queue
     * @return true always
     */
    public boolean offer(T item) {
        if(delegate.size() == maxSize)
        {
            delegate.remove();
        }
        delegate.add(item);
        return true;
    }

    /**
     * Returns an iterator for the current state of the queue
     * @return Iterator
     */
    public Iterator<T> getIterator() {
        return delegate.iterator();
    }

}
