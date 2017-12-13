package oome;

import java.util.Vector;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class FactoryError {

	private int iteracao;

	public void generateOOM() {

		int iteratorValue = 20;

		System.out.println("\n=================> OOM Início teste..\n");

		for (int outerIterator = 1; outerIterator < iteracao; outerIterator++) {

			System.out.println("Iteration " + outerIterator + " Free Mem: " + Runtime.getRuntime().freeMemory());

			int loop1 = 5;

			String[] memoryFillIntVar = new String[iteratorValue];

			do {
				memoryFillIntVar[loop1] = "outofmemoryerror outofmemoryerror outofmemoryerror outofmemoryerror outofmemoryerror";
				loop1--;
			} while (loop1 > 0);

			iteratorValue = iteratorValue * 5;

			System.out.println("\nRequired Memory for next loop: " + iteratorValue);

		}
	}

	/**
	 * Out of memory causado por threads
	 */
	public void ooomeByThread() {

		for (int i = 0; true; ++i) {
			new Thread() {
				public void run() {
					try {
						Thread.sleep(1000000);
					} catch (InterruptedException e) {
					}
				}
			}.start();

			System.out.println("Thread " + i + " created");
		}

	}
	
	public void ooomeByVector() {

		Vector v = new Vector();
		while (true) {
			byte b[] = new byte[1048576];
			v.add(b);
		}

	}

	public int getIteracao() {
		return iteracao;
	}

	public void setIteracao(int iteracao) {
		this.iteracao = iteracao;
	}

}
