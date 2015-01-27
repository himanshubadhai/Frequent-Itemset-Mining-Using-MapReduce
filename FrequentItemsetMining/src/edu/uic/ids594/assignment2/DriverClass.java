package edu.uic.ids594.assignment2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.KeyValueTextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author himansubadhai
 *
 */
public class DriverClass extends Configured
implements Tool{

	@Override
	public int run(String[] arg0) throws Exception {
		// TODO Auto-generated method stub

		// creating configuration object for job-1
		JobConf config1 = new JobConf(getConf(), DriverClass.class);
		config1.setJobName("MapReduceJob1");

		//setting configuration parameters for job-1
		config1.setOutputKeyClass(Text.class);
		config1.setOutputValueClass(IntWritable.class);
		config1.setMapOutputKeyClass(Text.class);
		config1.setMapOutputValueClass(IntWritable.class);

		// setting classes for job-1
		config1.setJarByClass(DriverClass.class);
		config1.setMapperClass(MapperClass1.class);
		config1.setReducerClass(ReducerClass1.class);
		config1.setPartitionerClass(PartitionerClass.class);
		config1.setNumReduceTasks(3);

		//setting input and output file path for job-1

		config1.set("key.value.separator.in.input.line", " ");
		config1.setInputFormat(KeyValueTextInputFormat.class);
		
		//KeyValueTextInputFormat.addInputPath(config1, new Path("/Users/himansubadhai/Documents/input/retail.dat.txt"));
		KeyValueTextInputFormat.addInputPath(config1, new Path("input/sample.txt"));
		
		//FileOutputFormat.setOutputPath(config1, new Path("/Users/himansubadhai/Documents/input/intermediate_output"));
		FileOutputFormat.setOutputPath(config1, new Path("intermediate_output"));
		
		JobClient.runJob(config1);

		return 0;
	}

	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new DriverClass(), args);
		System.exit(res);
	}

}
