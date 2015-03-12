package temp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReduceTrainingTesting {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ReduceTrainingTesting rtt = new ReduceTrainingTesting();
		String trainingFile = "data/tarining.txt";
		String testingFile = "data/testing.txt";
//		String testingFile = "data/testingAns.txt";
		String outputFile = "data/output.txt";

		FileReader fr = new FileReader(outputFile);
		BufferedReader br = new BufferedReader(fr);
		int i=0;
		while(br.ready()){
			String str = br.readLine();
			if(i<=1300){
//				rtt.appendWrite(trainingFile, str);
			}else{
//				rtt.appendWrite(testingFile, str);
				
				String[] strarr = str.split(",");
				String strstr = "";
				for(int si=0;si<(strarr.length-1);si++){
					strstr = strarr[si]+","+strstr;
				}
				strstr = strstr+"?";
				rtt.appendWrite(testingFile, strstr);
				
			}
			i++;
		}
	}
	//連續寫入檔案
	void appendWrite(String outputFilename,String data){
		try {
			FileWriter fw = new FileWriter(outputFilename, true);
			fw.write(data);
			fw.write('\r');
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
