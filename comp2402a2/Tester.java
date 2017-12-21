package comp2402a2;
import java.util.List;


/**
 */
public class Tester {

	public static boolean testPart1(List<Integer> ell) {
		int n = 65536;
		Stopwatch s = new Stopwatch();
		System.out.flush();


		s.start();
		for (int i=0; i<n;i++){
			ell.add(i);
		}
		s.stop();
		System.out.flush();


		s.start();
		for (int i=0; i<512; i++){
			ell.add(0,i);
		}
		s.stop();
		System.out.flush();


		s.start();
		for (int i=0; i<512; i++){
			ell.add(ell.size()/2,i);
		}
		s.stop();
		System.out.flush();


		s.start();
		for (int i=0; i<n;i++){
			ell.get(i);
		}
		s.stop();
		System.out.flush();


		s.start();
		for(int i=0;i<ell.size();i++){
			ell.set(i,0);
		}
		System.out.flush();


		s.start();
		for (int i=0;i<512;i++){
			ell.remove(ell.size()-1);
		}
		s.stop();
		System.out.flush();


		s.start();
		for (int i=0;i<512;i++){
			ell.remove(0);
		}
		s.stop();
		System.out.flush();


		s.start();
		for (int i = 0; i < n; i++) {
			ell.remove(ell.size()/2);
		}
		s.stop();

		if (s.elapsedSeconds()>2){
			return false;
		}

		if (ell.size()!=0){
			return false;
		}
		return true;
	}

	public static boolean testPart2(List<Integer> ell) {

		int n = 65536;

		Stopwatch s = new Stopwatch();
		System.out.flush();


		s.start();
		for (int i=0; i<n;i++){
			ell.add(i);
		}
		s.stop();
		System.out.flush();


		s.start();
		for (int i=0; i<512; i++){
			ell.add(0,i);
		}
		s.stop();
		System.out.flush();


		s.start();
		for (int i=0; i<512; i++){
			ell.add(ell.size()/2,i);
		}
		s.stop();
		System.out.flush();


		s.start();
		for (int i=0; i<n;i++){
			ell.get(i);
		}
		s.stop();
		System.out.flush();


		s.start();
		for(int i=0;i<ell.size();i++){
			ell.set(i,0);
		}
		s.stop();
		System.out.flush();


		s.start();
		for (int i=0;i<512;i++){
			ell.remove(0);
		}
		s.stop();
		System.out.flush();


		s.start();
		for (int i = 0; i < 512; i++) {
			ell.remove(ell.size()/2);
		}
		s.stop();


		s.start();
		int j = ell.size();
		s.stop();
		System.out.flush();


		s.start();
		while(ell.size()!=0){
			ell.remove(ell.size()-1);
		}
		s.stop();
		System.out.flush();

		if (s.elapsedSeconds()>2){
			return false;
		}

		if (ell.size()!=0){
			return false;
		}
		return true;
	}

	public static boolean testPart3(AbstractTable<Integer> ell) {

		int nrows = 512, ncols = 512;


		for (int i=0;i<ncols;i++) {
			ell.addCol(ell.cols());
		}
		for (int i=0;i<nrows;i++) {
			ell.addRow(ell.rows());
		}

		for (int i=0;i<ell.rows();i++){
			for(int j=0;j<ell.cols();j++){
				ell.set(i,j,i+j);
			}
		}
		System.out.println(ell.toString());


		ell.addCol(511);
		ell.addCol(256);
		ell.addCol(0);


		ell.addRow(511);
		ell.addRow(256);
		ell.addRow(0);


		ell.removeRow(511);
		ell.removeRow(256);
		ell.removeRow(0);

	
		ell.removeCol(511);
		ell.removeRow(256);
		ell.removeCol(0);

		ell.set(2,3,1000);
		System.out.println(ell.toString());
		return true;
	}

	public static void main(String[] args){
		testPart1(new Treque<Integer>(Integer.class));
		testPart2(new RootishArrayDeque<Integer>(Integer.class));
		testPart3(new Table<Integer>(Integer.class));

	}


}
