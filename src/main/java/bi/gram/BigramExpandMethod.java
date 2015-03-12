package bi.gram;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BigramExpandMethod {
	ArrayList<String> dictionaryList = new ArrayList<String>();		//字典陣列
	ArrayList<String> domainNameList = new ArrayList<String>();		//字典陣列

	void crateDictionaryAndDomainList(String mainDN,int n){
		String[] strarr = mainDN.split("");
		for(int i=0;i<=strarr.length;i++){
			String s = "";		//依照n-gream來取字串
			try{
				s=strarr[i]+strarr[i+1];
			}catch(Exception e){
//					System.out.println("Exception:"+j);
			}
			String toLowerCase = s.toLowerCase();
			if(!dictionaryList.contains(toLowerCase) && toLowerCase!=""){		//條件成立就回傳true，可是加了!，所以false就會不存在，才會將它加入
				dictionaryList.add(toLowerCase);
			}
//			System.out.println(s);
		}
		if(!domainNameList.contains(mainDN) && mainDN !=""){	//網域名稱還沒有加入才可以加入到ArrayList
			domainNameList.add(mainDN);
		}
	}
	
	HashMap<String,Integer> singleDomainBigramToMap(String mainDN,int n){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		String[] strarr = mainDN.split("");
		for(int i=0;i<=strarr.length;i++){
			String s = "";		//依照n-gream來取字串
			try{
				s=strarr[i]+strarr[i+1];
			}catch(Exception e){			}
			String toLowerCase = s.toLowerCase();
			if(map.containsKey(toLowerCase)){
				map.put(toLowerCase, map.get(toLowerCase)+1);
			}else{
				map.put(toLowerCase, 1);
			}
		}
		return map;
	}
	
	
	
	
//	寫入檔案
	public void resultToWrite(String outputFilename,String data){
		try {
			FileWriter fw = new FileWriter(outputFilename, true);
			fw.write(data);
			fw.write('\r');
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	這個檔案要小心，因為可能會將檔案清楚
	public void cleanFile(String outputFilename){
		try {
			FileWriter fw = new FileWriter(outputFilename);
			fw.write("");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
