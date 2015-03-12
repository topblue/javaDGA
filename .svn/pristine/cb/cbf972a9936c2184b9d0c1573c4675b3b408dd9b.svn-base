package n.gram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;

public class MainClass {
	String fileName = "data/domain.txt";	//要處理的檔案名稱，檔案格式只有domain name名稱
	Integer getMainDNIndex = 2;		//取最後倒數第二個，就是不娶tw,com,net，這個的前一個。www.google.com，只取google，直就給2
	Integer ngramLimit = 2; //給2是2-gram
//	String ans = "m";	//是分類中的要放入的檔案名稱
	
	String outputFilename = "data/output.txt";	//計算好的陣列名稱
	String dictionaryFilename = "data/dictionary.txt";	//字典資料記錄
	
	public static void main(String[] args)  throws IOException {
		MainClass readfile = new MainClass();
		nGramData ng= new nGramData();
//		第一次讀取檔案是為了建立字典陣列
		FileReader fr1 = new FileReader(readfile.fileName);
		BufferedReader br1 = new BufferedReader(fr1);
		while (br1.ready()) {
			String mainDN = readfile.reciprocalMainDN(br1.readLine());
			ng.dataFormat(mainDN,readfile.ngramLimit);
		}
		fr1.close();
		JSONArray jsarr = new JSONArray(ng.dictionaryList);
		ng.resultToWrite(readfile.dictionaryFilename, jsarr.toString());
		ng.resultToWrite(readfile.dictionaryFilename, String.valueOf(jsarr.length()));
		
//		利用建立好的字典陣列，將每一行資料轉換為二維陣列
		FileReader fr2 = new FileReader(readfile.fileName);
		BufferedReader br2 = new BufferedReader(fr2);
//		這行要特別小心，因為可能會將檔案清楚，所以記得把output的資料放入，不要把input資料
		ng.cleanFile(readfile.outputFilename);
		while (br2.ready()) {
			String line = br2.readLine();
			String ans = readfile.getAns(line);
			String mainDN = readfile.reciprocalMainDN(line);
			HashMap<String,Integer> map = ng.reduceDomain(mainDN, readfile.ngramLimit);
//			System.out.println(mainDN+":::"+readfile.getAns(mainDN));
			ng.writeToFileUseMapDictionary(mainDN, readfile.ngramLimit, map,ans,readfile.outputFilename);
		}
	}
	
	String reciprocalMainDN(String str){	//取最後倒數第二個，就是不娶tw,com,net，這個的前一個。www.google.com，只取google
		String[] strarr = str.split(",");
		int getMainIndex = strarr.length- getMainDNIndex;
		return strarr[getMainIndex];
	}
	String getAns(String str){	//取得檔案
		String[] strarr = str.split(",");
		int getMainIndex = strarr.length-1;
		return strarr[getMainIndex];
	}
}
