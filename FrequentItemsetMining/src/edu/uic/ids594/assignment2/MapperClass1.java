package edu.uic.ids594.assignment2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 * @author himansubadhai
 *
 */
public class MapperClass1 extends MapReduceBase
implements Mapper<Text, Text, Text, IntWritable>{

	// initialize variables
	private Text items = new Text();
	public static IntWritable one = new IntWritable(1);

	@Override
	public void map(Text key, Text value,
			OutputCollector<Text, IntWritable> output1, Reporter reporter)
					throws IOException {
		// TODO Auto-generated method stub

		System.out.println("Key: "+key);

		String lineReader = value.toString();
		String[] tokenizer = lineReader.split(" ");

		for(int i=0;i<tokenizer.length;i++)
		{
			items.set("("+tokenizer[i]+")");
			output1.collect(items, one);

			System.out.println("singleton: "+items);

			for(int j=i+1;j<tokenizer.length;j++)
			{
				items.set("("+tokenizer[i]+","+tokenizer[j]+")");
				output1.collect(items, one);

				System.out.println("doubleton: "+items);

				for (int k=j+1;k<tokenizer.length;k++)
				{
					this.items.set("("+tokenizer[i]+","+tokenizer[j]+","+tokenizer[k]+")");
					output1.collect(items, one);

					System.out.println("tripleton: "+items);
				}
			}

		}

	}

}