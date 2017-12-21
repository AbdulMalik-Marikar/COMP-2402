package comp2402a2;

import java.util.ArrayList;

/**
 */
public class Table<T> implements AbstractTable<T> {
	/**
	 * You decide on the instance variables you need.
	 */
	ArrayList<ArrayList<T>> Matrix;
	int nrows, ncols;

	public Table(Class<T> t) {
		// Put your own code here
		Matrix = new ArrayList<ArrayList<T>>();
		nrows=0;
		ncols=0;
	}

	public int rows() {
		// Put your own code here instead of throwing this exception
		return Matrix.size();
	}

	public int cols() {
		// Put your own code here instead of throwing this exception
		return ncols;
	}

	public T get(int i, int j) {
		if (i < 0 || i > rows() - 1 || j < 0 || j > cols())
			throw new IndexOutOfBoundsException();
		// Put your own code here instead of throwing this exception
		return Matrix.get(i).get(j);
	}

	public T set(int i, int j, T x) {
		if (i < 0 || i > rows() - 1 || j < 0 || j > cols())
			throw new IndexOutOfBoundsException();
		// Put your own code here instead of throwing this exception
		return Matrix.get(i).set(j,x);

	}

	public void addRow(int i) {
		if (i < 0 || i > rows()) throw new IndexOutOfBoundsException();
		// Put your own code here instead of throwing this exception
		nrows++;
		ArrayList<T>newRow=new ArrayList<T>();
		for(int j=0;j<cols();j++){
			newRow.add(null);
		}Matrix.add(i,newRow);
	}

	public void removeRow(int i) {
		if (i < 0 || i > rows() - 1) throw new IndexOutOfBoundsException();
		// Put your own code here instead of throwing this exception
		nrows--;
		Matrix.remove(i);
	}

	public void addCol(int j) {
		if (j < 0 || j > cols()) throw new IndexOutOfBoundsException();
		// Put your own code here instead of throwing this exception
		ncols++;
		for(int i=0; i<rows();i++)
			Matrix.get(i).add(j,null);
	}

	public void removeCol(int j) {
		if (j < 0 || j > cols() - 1) throw new IndexOutOfBoundsException();
		// Put your own code here instead of throwing this exception
		ncols--;
		for(int i=0; i<rows();i++){
			Matrix.get(i).remove(j);
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rows(); i++) {
			for (int j = 0; j < cols(); j++) {
				sb.append(String.valueOf(get(i, j)));
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/* Here is the expected output from this main function:
		1111 null null null null null
		null 2222 null null null null
		null null 3333 null null null
		null null null 4444 null null
		null null null null 5555 null
		null null null null null 6666
		7777 null null null null null
		null 8888 null null null null
		null null 9999 null null null

		1111 null null null null null null
		null 2222 null null null null null
		null null null 3333 null null null
		null null null null null null null
		null null null null 4444 null null
		null null null null null 5555 null
		null null null null null null 6666
		7777 null null null null null null
		null 8888 null null null null null
		null null null 9999 null null null
	*/
	public static void main(String[] args) {
		int nrows = 9, ncols = 6;
		Table<Integer> t = new Table<Integer>(Integer.class);
		for (int i = 0; i < ncols; i++) {
			t.addCol(t.cols());
		}
		for (int i = 0; i < nrows; i++) {
			t.addRow(t.rows());
		}
		for (int i = 1; i <= nrows; i++) {
			t.set(i-1, (i-1)%t.cols(), 1111*i);
		}
		System.out.println(t.toString());
		t.addCol(2);
		t.addRow(3);
		System.out.println(t.toString());
	}
}
