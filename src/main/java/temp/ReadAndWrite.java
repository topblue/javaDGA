package temp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//當初只是為了，將weka中的答案放入
public class ReadAndWrite {
	String readFileName ="";
	String writeFileName ="";
	
	public static void main(String[] args) throws Exception {
		ReadAndWrite rw = new ReadAndWrite();
		rw.readFile("data/dgaDomain.txt","n");
		rw.readFile("data/normalDomain.txt","y");
	}
	
	void readFile(String readFileName,String ans) throws Exception{
		FileReader fr = new FileReader(readFileName);
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			String str = br.readLine();
			str = str+","+ans;
			appendWrite("data/domain.txt",str);
//			System.out.println(str);
		}
		fr.close();
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
	//這個檔案要小心，因為可能會將檔案清楚
	void cleanFile(String outputFilename){
		try {
			FileWriter fw = new FileWriter(outputFilename);
			fw.write("");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
