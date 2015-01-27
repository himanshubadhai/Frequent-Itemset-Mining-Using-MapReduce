package edu.uic.ids594.assignment2;

import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Partitioner;

public class PartitionerClass implements Partitioner<Text, IntWritable>{

	@Override
	public void configure(JobConf arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPartition(Text key, IntWritable value, int numOfReduceTask) {
		// TODO Auto-generated method stub

		if(numOfReduceTask==0){
			return 0;
		}
		//parse the key to store in integer
		StringTokenizer tokens = new StringTokenizer(key.toString(),"( ) ,");

		// conditions of partition
		if(tokens.countTokens()==1)
		{
			return 0;
		}

		else if(tokens.countTokens()==2)
		{
			return 1%numOfReduceTask;
		}
		else
		{
			return 2%numOfReduceTask;
		}

	}

}
