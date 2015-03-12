package bi.gram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONArray;

public class MainSparseMatrix {
	String rawDomain = "data/domain.txt";	//要處理的檔案名稱，檔案格式只有domain name名稱
	Integer getMainDNIndex = 2;		//取最後倒數第二個，就是不娶tw,com,net，這個的前一個。www.google.com，只取google，直就給2
	Integer ngramLimit = 2; //給2是2-gram

	String outputFilename = "data/bigram/bigramOutput.txt";	//計算好的陣列名稱
	String outputTransposeMatrixFilename = "data/bigram/bigramOutputTransposeMatrix.txt";	//計算好的陣列名稱
	String dictionaryFilename = "data/bigram/dictionary.txt";	//字典資料記錄
	
	public static void main(String[] args) throws Exception {
		MainSparseMatrix byMySelf = new MainSparseMatrix();
		BigramExpandMethod method = new BigramExpandMethod();
		
		BufferedReader br = new BufferedReader(new FileReader(byMySelf.rawDomain));
		while(br.ready()){
			String line = br.readLine();
			String mainDN = byMySelf.reciprocalMainDN(line);
			method.crateDictionaryAndDomainList(mainDN,byMySelf.ngramLimit);
//			System.out.println(mainDN);
		}
		br.close();

//		這行要特別小心，因為可能會將檔案清楚，所以記得把output的資料放入，不要把input資料
		method.cleanFile(byMySelf.dictionaryFilename);
		method.resultToWrite(byMySelf.dictionaryFilename, "Bigram Dictionary List , Value:Bigram , Use:Index");
		method.resultToWrite(byMySelf.dictionaryFilename, method.dictionaryList.toString());
		method.resultToWrite(byMySelf.dictionaryFilename, String.valueOf(method.dictionaryList.size()));
		method.resultToWrite(byMySelf.dictionaryFilename, "Unique Domain List");
		method.resultToWrite(byMySelf.dictionaryFilename, method.domainNameList.toString());
		method.resultToWrite(byMySelf.dictionaryFilename, String.valueOf(method.domainNameList.size()));

//		這行要特別小心，因為可能會將檔案清楚，所以記得把output的資料放入，不要把input資料加入喔，會很慘
		method.cleanFile(byMySelf.outputFilename);
		method.cleanFile(byMySelf.outputTransposeMatrixFilename);
		
		br = new BufferedReader(new FileReader(byMySelf.rawDomain));
		for(String mainDN:method.domainNameList){
			String domainNameListIndex = String.valueOf(method.domainNameList.indexOf(mainDN)+1);
//			domain name 切割bigram並計算重複的累加
			HashMap<String,Integer> singleDomainBigramMap = method.singleDomainBigramToMap(mainDN, byMySelf.ngramLimit);
//			接下來就將整理好的資料轉index寫入
			for(Entry<String,Integer> entry : singleDomainBigramMap.entrySet()){
//				System.out.println(entry.getKey());
				int bigramIndex = method.dictionaryList.indexOf(entry.getKey());
				if(bigramIndex>=0){
					int count = entry.getValue();
					bigramIndex++;
					String resultData = domainNameListIndex+" "+bigramIndex+" "+count;	//domain和bigram轉成index用逗號分格，加讓出現的次數
					method.resultToWrite(byMySelf.outputFilename, resultData);
					String resultDataTransposeMatrix = bigramIndex+" "+domainNameListIndex+" "+count;	//做sparse matrix transpose，domain和bigram轉成index用逗號分格，加讓出現的次數
					method.resultToWrite(byMySelf.outputTransposeMatrixFilename, resultDataTransposeMatrix);
				}
			}
		}
		br.close();
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
