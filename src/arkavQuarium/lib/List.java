package arkavquarium.lib;

/**
 * The type List.
 *
 * @param <T> the type parameter
 */
public class List<T> {
    private class ElmList<T> {
        private T data;
        private ElmList next;

        /**
         * Instantiates a new Elm list.
         *
         * @param info the info
         */
        ElmList(final T info) {
            this.data = info;
            this.next = null;
        }

        /**
         * Gets data.
         *
         * @return the data
         */
        public T getData() {
            return this.data;
        }

        /**
         * Gets next.
         *
         * @return the next
         */
        public ElmList getNext() {
            return this.next;
        }

        /**
         * Sets data.
         *
         * @param info the info
         */
        public void setData(final T info) {
            this.data = info;
        }

        /**
         * Sets next.
         *
         * @param n the n
         */
        public void setNext(final ElmList n) {
            this.next = n;
        }
    }

    private ElmList<T> first;
    private ElmList<T> last;
    private int size;

    /**
     * Instantiates a new List.
     */
    public List() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Gets first.
     *
     * @return the first
     */
    public T getFirst() {
        return first.getData();
    }

    /**
     * Gets last.
     *
     * @return the last
     */
    public T getLast() {
        return last.getData();
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Find int.
     *
     * @param data the data
     * @return the int
     */
    public int find(final T data) {
        ElmList d = first;
        int index = 0;
        boolean found = d.getData().equals(data);
        while (!found && !d.getData().equals(last.getData())) {
            d = d.getNext();
            found = d.getData().equals(data);
            index++;
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /**
     * Append.
     *
     * @param data the data
     */
    public void append(final T data) {
        ElmList<T> d = new ElmList<T>(data);
        if (isEmpty()) {
            first = d;
            last = d;
            last.setNext(null);
        } else {
            last.setNext(d);
            last = last.getNext();
            last.setNext(null);
        }
        size++;
    }

    /**
     * Remove.
     *
     * @param data the data
     */
    public void remove(final T data) {
        ElmList<T> d = first;
        ElmList<T> dTemp = null;
        boolean found = d.getData().equals(data);
        if (found) {
            d = d.getNext();
            first = d;
            this.size--;
        } else {
            while (!found && !(d == null)) {
                dTemp = d;
                d = d.getNext();
                found = d.getData().equals(data);
            }
            if (found) {
                if (d == last) {
                    last = dTemp;
                } else {
                    dTemp.setNext(d.getNext());
                }
                this.size--;
            }
        }
    }

    /**
     * Remove at.
     *
     * @param i the
     */
    public void removeAt(final int i) {
        ElmList<T> d = first;
        ElmList<T> prev = null;
        int index = 0;
        if ((i >= 0) && (i <= size)) {
            if (i == 0) {
                first = first.getNext();
            } else {
                while (!(index == i)) {
                    prev = d;
                    d = d.getNext();
                    index++;
                }
                prev.setNext(d.getNext());
                if (index == size - 1) {
                    last = prev;
                }
            }
            this.size--;
        }
    }

    /**
     * Get t.
     *
     * @param i the
     * @return the t
     */
    public T get(final int i) {
        ElmList<T> d = first;
        int index = 0;
        while (!(index == i) && d != null) {
            d = d.getNext();
            index++;
        }
        if (d == null) {
            return null;
        } else {
            return d.getData();
        }
    }
}
