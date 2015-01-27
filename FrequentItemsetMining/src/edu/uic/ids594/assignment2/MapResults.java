package edu.uic.ids594.assignment2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.FileUtils;


public class MapResults {
	private File folder=new File("/Users/himansubadhai/Documents/input/sample/intermediate_output");
	private PrintWriter printWriter;
	private String content="";
	private String associativity;
	private double confidence;
	private String doubleTon;
	private String singleTon;

	static HashMap<String,Double> map=new HashMap<String,Double>();

	public static void main(String args[])
	{
		MapResults mapResults=new MapResults();
		mapResults.addValues();
		mapResults.calculateConfidence(map);
	}
	public void addValues()
	{
		File[] listOfFiles=folder.listFiles();
		for(int i=0;i<listOfFiles.length;i++)
		{
			File file=listOfFiles[i];
			if (file.isFile() && file.getName().startsWith("p")) {
				try {
					content = content+FileUtils.readFileToString(file);
					//System.out.println(content);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} 
		}
		String contents[]=content.trim().split("\n");
		for(int a=0;a<contents.length;a++)
		{
			String values[]=contents[a].split("\t");

			System.out.println(values[0]);
			map.put(values[0].trim().substring(1, values[0].toString().trim().length()-1), Double.parseDouble(values[1].trim()));

		}

	}

	//this method will fetch the value of singletn, doubleton or tripleton
	public Double findValue(String key)
	{
		return map.get(key);
	}

	public void calculateConfidence(HashMap hashMap)
	{
		Iterator iterator = hashMap.entrySet().iterator();

		File file = new File("Final_output.txt");

		try{
			printWriter = new PrintWriter(file);
		}

		catch(Exception e){

		}


		while (iterator.hasNext()) {
			Map.Entry pairs = (Map.Entry)iterator.next();
			String values[]=pairs.getKey().toString().trim().split(",");
			for(int i=0;i<values.length;i++)
			{
				if(values.length==2)
				{
					associativity="["+values[i]+"=>"+values[1-(i)]+"]";
					double a1=findValue(pairs.getKey().toString().trim());
					double a2=findValue(values[i].trim());
					//confidence=addKey.findValue(key.toString().trim().substring(1, key.toString().trim().length()-1))/addKey.findValue(values[i].trim());
					confidence=a1/a2;
					System.out.println(associativity+" "+confidence);
					printWriter.println(associativity+" "+confidence);

				}

				if(values.length==3)
				{
					//this for loop is to calculate the confidence of tripleton
					for(int j=i+1;j<values.length;j++)
					{
						doubleTon=values[i].trim()+","+values[j].trim();
						singleTon=values[3-(i+j)].trim();
						associativity="["+doubleTon+"=>"+singleTon+"]";
						confidence=findValue(pairs.getKey().toString().trim())/findValue(doubleTon);
						System.out.println(associativity+" "+confidence);
						printWriter.println(associativity+" "+confidence);

					}
				}

			}
		}
		iterator.remove(); // avoids a ConcurrentModificationException
		printWriter.close();
	}
}