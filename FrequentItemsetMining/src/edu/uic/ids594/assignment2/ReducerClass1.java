package edu.uic.ids594.assignment2;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 * @author himansubadhai
 *
 */
public class ReducerClass1 extends MapReduceBase
implements Reducer<Text, IntWritable, Text, IntWritable>{

	@Override
	public void reduce(Text key, Iterator<IntWritable> values,
			OutputCollector<Text, IntWritable> output1, Reporter reporter)
					throws IOException {
		// TODO Auto-generated method stub

		int sum = 0;

		// loop to calculate the count of each unique pair
		while (values.hasNext()) {
			values.next();
			sum++;
		}
		System.out.println(key + "\t" + sum);
		output1.collect(key, new IntWritable(sum));

	}

}
