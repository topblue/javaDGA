package tool.function;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReadWrite {
	
	/**
	 * @see 給檔案路徑，就可以讀取檔案內容
	 * @return ArrayList<String>
	 * ***/
	ArrayList<String> readFile(String readFileName) throws Exception{
		ArrayList<String> arr = new ArrayList<String>();
		FileReader fr = new FileReader(readFileName);
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			String str = br.readLine();
			arr.add(str);
		}
		fr.close();
		return arr;
	}
	/**
	 * @see 連續寫入檔案
	 * **/
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
	/**
	 * @see 只要給檔案路徑就可以"清除檔案"的內容
	 * **/
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
