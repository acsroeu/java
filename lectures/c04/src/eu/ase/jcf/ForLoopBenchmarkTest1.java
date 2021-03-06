package eu.ase.jcf;

//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Iterator;
import java.util.*;
import java.util.stream.*;

//compile ONLY with JDK 8
public class ForLoopBenchmarkTest1
{
    private static List<Integer> list = new ArrayList<>();
    private static long startTime;
    private static long endTime;
    static
    {
        for(int i=0; i < 10_000_001; i++)
        {
            list.add(i);
        }
    }
    @SuppressWarnings("unused")
    public static void main(String[] args)
    {
        //Type 1
        startTime = Calendar.getInstance().getTimeInMillis();
	int k = 0;
        for(Integer i : list)
        {
            if (k == 10000000)
		System.out.println("for each i = " + i);
		
	    k++;
        }
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("For each loop :: " + (endTime - startTime) + " ms");

	//Type 2
	int j = 0;
	startTime = Calendar.getInstance().getTimeInMillis();
	for(Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
		j = it.next();
		if(j == 10000000)
			System.out.println("for interator j = " + j);
	}
	endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("For iterator loop :: " + (endTime - startTime) + " ms");

	int x = 0;
        //Type 3
        startTime = Calendar.getInstance().getTimeInMillis();
        for(j = 0; j < list.size() ; j++)
        {
            x = list.get(j);
            if (x == 10000000)
		System.out.println("for int x = " + x);
        }
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Using collection.size() :: " + (endTime - startTime) + " ms");

        //Type 4
        startTime = Calendar.getInstance().getTimeInMillis();
        int size = list.size();
        for(j = 0; j < size ; j++)
        {
	    x = list.get(j);
            if (x == 10000000)
		System.out.println("for int x = " + x);
        }
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Using [int size = list.size(); int j = 0; j < size ; j++] :: " + (endTime - startTime) + " ms");

        //Type 5
        startTime = Calendar.getInstance().getTimeInMillis();
	
        for(j = (list.size() - 1); j >= 0 ; j--)
        {
	    x = list.get(j);
            if (x == 1)
		System.out.println("for int x = " + x);
        }
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Using [int j = list.size(); j >= 0 ; j--] :: " + (endTime - startTime) + " ms");

	//Type 6
        startTime = Calendar.getInstance().getTimeInMillis();
        IntStream.range(0, 10000001).filter(y -> y == 1000000).forEach(System.out::println);
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Using LamdaExpression :: " + (endTime - startTime) + " ms");

    }
}








