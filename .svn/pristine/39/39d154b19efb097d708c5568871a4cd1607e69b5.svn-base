package tool.function;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

}
