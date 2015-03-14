package tool.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

public class DomainStringFormat {

	public void checkDomainSize(String str){
		String[] size = str.split("\\.");
		if(size.length>4){
			System.out.println(str);
		}
	}
	public HashMap<String,Integer> splitChart(String str){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		String[] s = str.split("");
		for(int i=0;i<s.length;i++){
			String ele = s[i];
			if(map.containsKey(ele)){
				int j = map.get(ele);
				++j;
				map.put(ele, j);
			}else{
				map.put(ele, 1);
			}
//			System.out.println("\t"+ele+"\t"+map.containsKey(ele));
		}
		return map;
	} 
	public HashMap<Double,Double> domainSize(ArrayList<Double> arr){
		
		HashMap<Double,Double> map = new HashMap<Double,Double>();
		for(double nameSize:arr){
			if(map.containsKey(nameSize)){
				double j = map.get(nameSize);
				++j;
				map.put(nameSize, j);
			}else{
				map.put(nameSize, 1.0);
			}
		}
//		System.out.println("jsonobject："+new JSONObject(map));
		return map;
	} 
	
}
