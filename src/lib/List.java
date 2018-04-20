package lib;

public class List<T> {
	private class ElmList<T> {
		private T data;
		private ElmList next;
		
		public ElmList(T data){
			this.data = data;
			this.next = null;
		}
		
		public T getData(){
			return this.data;
		}
		
		public ElmList getNext(){
			return this.next;
		}
		
		public void setData(T data){
			this.data = data;
		}
		
		public void setNext(ElmList next){
			this.next = next;
		}
	}
	
	private ElmList<T> first;
	private ElmList<T> last;
	private int size;
	
	public List(){
		first = null;
		last = null;
		size = 0;
	}
	
	public T getFirst(){
		return first.getData();
	}
	
	public T getLast(){
		return last.getData();
	}
	
	public int getSize(){
		return this.size;
	}
	
	public int find(T data){
		ElmList d = first;
		int index = 0;
		boolean found = d.getData().equals(data);
		while (!found && !d.getData().equals(last.getData())){
			d = d.getNext();
			found = d.getData().equals(data);
			index++;
		}
		if (found)
			return index;
		else
			return -1;
	}
	
	public boolean isEmpty(){
		return (this.size == 0);
	}
	
	public void append(T data){
		ElmList<T> d = new ElmList<T>(data);
		if (isEmpty()){
			first = d;
			last = d;
			last.setNext(null);
		}
		else{
			last.setNext(d);
			last = last.getNext();
			last.setNext(null);
		}
		size++;
	}
	
	public void remove(T data){
		ElmList<T> d = first;
		ElmList<T> dTemp = null;
  		boolean found = d.getData().equals(data);
  		if (found){
  			d = d.getNext();
  			first = d;
  			this.size--;
  		}
  		else{
  			while (!found && !(d == null)){
  				dTemp = d;
  				d = d.getNext();
  				found = d.getData().equals(data);
  			}
  			if (found) {
  				if (d == last){
  					last = dTemp;
  				}
  				else{
					dTemp.setNext(d.getNext());
  				}
  				this.size--;
  			}
  		}
  	}
	
	public void removeAt(int i){
		ElmList<T> d = first;
		ElmList<T> prev = null;
		int index = 0;
		if ((i >= 0) && (i <= size)) {
			if (i == 0) {
				first = first.getNext();
			}
			else {
				while (!(index == i)) {
					prev = d;
					d = d.getNext();
					index++;
				}
				prev.setNext(d.getNext());
			}
			this.size--;
		}
	}
	
	public T get(int i){
  		ElmList<T> d = first;
  		int index = 0;
  		while (!(index == i)){
			d = d.getNext();
  			index++;
  		}
  		return d.getData();
  	}
}
