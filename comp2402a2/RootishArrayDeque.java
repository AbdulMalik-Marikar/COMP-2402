package comp2402a2;

import java.util.AbstractList;
import java.util.List;
import java.util.ArrayList;
import comp2402a2.RootishArrayStack;
import java.util.Collections;

/**
 */
public class RootishArrayDeque<T> extends AbstractList<T> {
	/**
	 * You decide on the instance variables you need.
	 */
	protected RootishArrayStack<T> front;
	protected RootishArrayStack<T> back;
	protected Factory<T> f;

	 protected RootishArrayStack<T> newRootishArrayStack(){
		 return new RootishArrayStack<T>(f.type());
	 }


	public RootishArrayDeque(Class<T> t) {
		// Put your own code here
		f=new Factory<T>(t);
		front= newRootishArrayStack();
		back = newRootishArrayStack();
		//throw new UnsupportedOperationException("Constructor not yet implemented");
	}

	public T get(int i) {
		if (i < 0 || i > size() - 1) throw new IndexOutOfBoundsException();
		// Put your own code here instead of throwing this exception
		if (i < front.size()) {
			return front.get(front.size()-i-1);
		} else {
			return back.get(i-front.size());
		}
	}
		//throw new UnsupportedOperationException("get(i) not yet implemented");


	public T set(int i, T x) {
		if (i < 0 || i > size() - 1) throw new IndexOutOfBoundsException();
		// Put your own code here instead of throwing this exception
		if (i < front.size()) {
			return front.set(front.size()-i-1, x);

		} else {
			return back.set(i-front.size(), x);
		}
		//throw new UnsupportedOperationException("set(i,x) not yet implemented");
	}

	public void balance(){
		int n = size();
		if (3*front.size() < back.size()) {
			int s = n/2 - front.size();
			RootishArrayStack<T> l1 = newRootishArrayStack();
			RootishArrayStack<T> l2 = newRootishArrayStack();
			l1.addAll(back.subList(0,s));
			Collections.reverse(l1);
			l1.addAll(front);
			l2.addAll(back.subList(s, back.size()));
			front = l1;
			back = l2;
		} else if (3*back.size() < front.size()) {
			int s = front.size() - n/2;
			RootishArrayStack<T> l1 = newRootishArrayStack();
			RootishArrayStack<T> l2 = newRootishArrayStack();
			l1.addAll(front.subList(s, front.size()));
			l2.addAll(front.subList(0, s));
			Collections.reverse(l2);
			l2.addAll(back);
			front = l1;
			back = l2;
		}

	}

	public void add(int i, T x) {
		if (i < 0 || i > size()) throw new IndexOutOfBoundsException();
		// Put your own code here
		if (i < front.size()) {
			front.add(front.size()-i, x);
		} else {
			back.add(i-front.size(), x);
		}
		balance();
		//throw new UnsupportedOperationException("add(i,x) not yet implemented");
		// set(i, x);
	}

	public T remove(int i) {
		if (i < 0 || i > size() - 1) throw new IndexOutOfBoundsException();
		// Put your own code here
		T x;
		if (i < front.size()) {
			x = front.remove(front.size()-i-1);
		} else {
			x = back.remove(i-front.size());
		}
		balance();
		return x;
		//throw new UnsupportedOperationException("size(i) not yet implemented");
	}

	public int size() {
		// Put your own code here
		return front.size()+back.size();
		//throw new UnsupportedOperationException("size() not yet implemented");
	}

	public static void main(String[] args) {
		//List<Integer> rad = new ArrayDeque<Integer>(Integer.class);
		List<Integer> rad = new RootishArrayDeque<Integer>(Integer.class);
		int K = 1000000;
		Stopwatch s = new Stopwatch();
		System.out.print("Appending " + K + " items...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			rad.add(i);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");

		System.out.print("Prepending " + K + " items...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			rad.add(0, i);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");

		System.out.print("Removing " + K + " items from the back...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			rad.remove(rad.size()-1);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");

		System.out.print("Removing " + K + " items from the front...");
		System.out.flush();
		s.start();
		for (int i = 0; i < K; i++) {
			rad.remove(0);
		}
		s.stop();
		System.out.println("done (" + s.elapsedSeconds() + "s)");
	}



}
