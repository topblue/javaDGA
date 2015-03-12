package n.gram;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class nGramData {
	
	ArrayList<String> dictionaryList = new ArrayList<String>();		//字典陣列
	
	void dataFormat(String mainDN,int n){
//		System.out.println(mainDN);
		String[] strarr = mainDN.split("");
		for(int i=1;i<=strarr.length;i=i+n){
			String s = "";		//依照n-gream來取字串
			for(int j=i;j<i+n;j++){
				try{
					s=s+strarr[j];
				}catch(Exception e){
//					System.out.println("Exception:"+j);
				}
			}
			String toLowerCase = s.toLowerCase();
			if(!dictionaryList.contains(toLowerCase) && toLowerCase!=""){		//條件成立就回傳true，可是加了!，所以false就會不存在，才會將它加入
				dictionaryList.add(toLowerCase);
			}
		}
	}
	HashMap<String,Integer> reduceDomain(String mainDN,int n){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		String[] strarr = mainDN.split("");
		for(int i=1;i<=strarr.length;i=i+n){
			String s = "";		//依照n-gream來取字串
			for(int j=i;j<i+n;j++){
				try{
					s=s+strarr[j];
				}catch(Exception e){
//					System.out.println("Exception:"+j);
				}
			}
			String toLowerCase = s.toLowerCase();
			if(map.containsKey(toLowerCase)){
				map.put(toLowerCase, map.get(toLowerCase)+1);
			}else{
				map.put(toLowerCase, 1);
			}
		}
		return map;
	}
	void writeToFileUseMapDictionary(String mainDN,int n,HashMap<String,Integer> map,String ans,String outputFilename){
		String data = "";
		for(int i=0;i<dictionaryList.size();i++){
			if(map.containsKey(dictionaryList.get(i))){
				data = map.get(dictionaryList.get(i))+","+data;
			}else{
				data = 0+","+data;
			}
		}
		data = data+ans;
		resultToWrite(outputFilename,data);
	}
//	寫入檔案
	void resultToWrite(String outputFilename,String data){
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
	
/***
	public static void main(String[] args)  throws Exception {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("a1");
		arr.add("7L");
		arr.add("Nq-+");
		for(String ar:arr){
			System.out.println(ar.toLowerCase());
		}
		System.out.println("______________");
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("a1", 1);
		map.put("7L", 1);
		System.out.println();
		if(map.containsKey("a1")){
			map.put("a1", map.get("a1")+1);
		}
		for (String name:map.keySet()) {
			System.out.print(name+":::"+map.get(name)+"\t");
		}
	}
	***/
}
