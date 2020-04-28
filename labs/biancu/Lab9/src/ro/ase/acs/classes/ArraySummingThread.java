package ro.ase.acs.classes;

public class ArraySummingThread extends Thread {
	private int[] array;
	private int startIndex;
	private int endIndex;
	private long sum;
	
	public ArraySummingThread(int[] array, int startIndex,
			int endIndex) {
		this.array = array;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	@Override
	public void run() {
		super.run();
		for(int i = startIndex; i < endIndex; i++) {
			sum += array[i];
		}
	}
	
	public long getSum() {
		return sum;
	}
}
