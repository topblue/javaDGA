package virus.es;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import tool.function.AlgorithmString;
import tool.function.DomainStringFormat;
import tool.function.ElasticsearchFunction;
import tool.function.FeatureBeans;

public class Main_characteristic {

	ElasticsearchFunction es = new ElasticsearchFunction();
	FeatureBeans featurebeans = new FeatureBeans();
	final String  esIndex = "dga",esType = "CRIME";
	ArrayList<String> domainList = new ArrayList<String>();
	
	public static void main(String[] args) throws JSONException {
		String fileName = "BIN_CitadelPacked_2012-05";
		Main_characteristic mainClass = new Main_characteristic();
		AlgorithmString algo = new AlgorithmString();
		mainClass.setDomainFromES(fileName);
//		ArrayList<String> arr = mainClass.es.queryTotalData("dga", "CRIME");
//		ArrayList<String> malwareList = mainClass.es.queryDistinctElement("dga", "CRIME", "fileName");
//		mainClass.es.queryStringByJsonobject("dga", "CRIME", template_params);

//		mainClass.features1(fileName);
//		mainClass.features2(fileName);
		
		ClassF2 f2Class = mainClass.features2(fileName);
		
//		System.out.println("mean:"+mean);
		
//		mainClass.features3(fileName,f2Class);
		
//		mainClass.features4(fileName);
		
//		mainClass.features5(fileName);
//		mainClass.queryIdToDelete();
		
//		ClassF2 f2 = new ClassF2();
		
	}
	
	
	/**
	 * 將Eleasticsearch中的domain name放到 domaArrayList<String> domainListinList
	 * @param String malwareFilename
	 * **/
	void setDomainFromES(String malwareFilename){
		this.domainList = es.queryStringDomain(this.esIndex, this.esType, malwareFilename);
	}
	
	/**
	 * @param 惡意軟體名稱
	 * @see 每個惡意軟體的亂度
	 * @return double:亂度
	 * */
	double features1(String fileName){
		DomainStringFormat domainstringformat = new DomainStringFormat();
		AlgorithmString algo = new AlgorithmString();
		String mergeDomain = "";
//		ArrayList<String> list = es.queryStringDomain(this.esIndex, this.esType, fileName);
		for(String domain:this.domainList){
			mergeDomain = mergeDomain+domain;
		}
		double domainSize = new Double(mergeDomain.length()); 
		System.out.println("domainSize:"+domainSize);
		System.out.println(mergeDomain);
		
		HashMap<String,Integer> map = domainstringformat.splitChart(mergeDomain);	//將字串皆成字元，並計算後放到HashMap之中
		Iterator iter = map.entrySet().iterator();
		double features1_result = 0.0;
		while (iter.hasNext()) {
		    Map.Entry entry = (Map.Entry) iter.next();
		    String key = entry.getKey().toString();
		    double val = Double.parseDouble(entry.getValue().toString());
		    double result = algo.entropy(val, domainSize);	//entropy
		    features1_result = algo.double_add(features1_result, result);
		}
		System.out.println("features1_result："+features1_result+"\tfeatures1_result*1："+features1_result*(-1));
		return features1_result*(-1);
	}
	
	/**
	 * @param 惡意軟體名稱
	 * @see 每個惡意軟體網域名稱亂度平均數
	 * 
	 * **/
	ClassF2 features2(String fileName){
		DomainStringFormat domainstringformat = new DomainStringFormat();
		AlgorithmString algo = new AlgorithmString();
//		String mergeDomain = "";
		double features2_total=0.0,features2_size =0.0;
//		ArrayList<String> list = es.queryStringDomain(this.esIndex, this.esType, fileName);
		ArrayList<Double> entropyList = new ArrayList<Double>();
		for(String domain:this.domainList){
//			domainstringformat.checkDomainSize(domain);
			double domainSize = new Double(domain.length()); 
			HashMap<String,Integer> map = domainstringformat.splitChart(domain);	//將字串皆成字元，並計算後放到HashMap之中
			Iterator iter = map.entrySet().iterator();
			double domain_entropy = 0.0;
			while (iter.hasNext()) {
			    Map.Entry entry = (Map.Entry) iter.next();
//			    String key = entry.getKey().toString();
			    double val = Double.parseDouble(entry.getValue().toString());
			    double result = algo.entropy(val, domainSize);	//entropy
			    domain_entropy = algo.double_add(domain_entropy, result);
			}
			domain_entropy = domain_entropy * (-1);
			entropyList.add(domain_entropy);
			features2_size++;
			features2_total =algo.double_add(features2_total, domain_entropy);
		}
		double mean = (features2_total/features2_size);
		System.out.println(mean);
		ClassF2 classf2 = new ClassF2(features2_size, mean, entropyList);
		return classf2;
		
	}
	/**@category double size 
	 * @category double mean 
	 * @category ArrayList<Double> entropyList
	 * **/
	private static class ClassF2 { 
		private double size, mean;
		private ArrayList<Double> entropyList;
		public ClassF2(double size, double mean,ArrayList<Double> entropyList) { 
			this.size = size; 
			this.mean = mean;
			this.entropyList = entropyList;
		}
	} 
	
	/**
	 * @see 每個惡意軟體網域名稱亂度變異數
	 * @param eatures2計算好的：亂數陣列(entropy)，陣列大小數值，平均數
	 * **/
	double features3(ClassF2 f2Class){
		DomainStringFormat domainstringformat = new DomainStringFormat();
		AlgorithmString algo = new AlgorithmString();
		double domain_entropy = 0.0;
		double result = algo.variance(f2Class.entropyList, f2Class.size, f2Class.mean);
		return result;
	}
	
//	void features3(String fileName,double mean){
//		DomainStringFormat domainstringformat = new DomainStringFormat();
//		AlgorithmString algo = new AlgorithmString();
////		ArrayList<String> list = es.queryStringDomain(this.esIndex, this.esType, fileName);
//		ArrayList<Double> arr = new ArrayList<Double>();
//		for(String domain:this.domainList){
////			domainstringformat.checkDomainSize(domain);
//			double domainSize = new Double(domain.length()); 
//			HashMap<String,Integer> map = domainstringformat.splitChart(domain);	//將字串皆成字元，並計算後放到HashMap之中
//			Iterator iter = map.entrySet().iterator();
//			double domain_entropy = 0.0;
//			while (iter.hasNext()) {
//			    Map.Entry entry = (Map.Entry) iter.next();
////			    String key = entry.getKey().toString();
//			    double val = Double.parseDouble(entry.getValue().toString());
//			    double result = algo.entropy(val, domainSize);	//entropy
//			    domain_entropy = algo.double_add(domain_entropy, result);
//			}
//			domain_entropy = domain_entropy * (-1);
//			arr.add(domain_entropy);
////			features3_size++;
////			features3_total =algo.double_add(features3_total, domain_entropy);
//		}
////		double mean = (features2_total/features2_size);
//		System.out.println("變異數："+algo.variance(arr, arr.size(), mean));
//		System.out.println(mean);
//	}
	
	/**
	 * @see 每個惡意軟體網域名稱長度的平均數的亂度
	 * @param 惡意軟體名稱
	 * **/
	double features4(String fileName){
		DomainStringFormat domainstringformat = new DomainStringFormat();
		AlgorithmString algo = new AlgorithmString();
//		ArrayList<String> list = es.queryStringDomain(this.esIndex, this.esType, fileName);
		ArrayList<Double> list_strSize = new ArrayList<Double>();
		double avg=0.0,entropy_total=0.0,result=0.0;
		for(String domain:this.domainList){
			int domainSize = domain.split("").length;
			list_strSize.add((double)domainSize);
//			domainstringformat.domainSize(String.valueOf(domainSize));
//			System.out.println(domain + "\t:\t" + domainSize);
		}
		avg = algo.average_int(list_strSize);	//平均數
//		System.out.println(" avg : "+avg);
		HashMap<Double,Double> map = domainstringformat.domainSize(list_strSize);
		Iterator iter = map.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry) iter.next();
			double val = Double.parseDouble(entry.getValue().toString());
//			System.out.println("entropy:"+algo.entropy(val, avg));
			entropy_total = entropy_total + algo.entropy(val, avg);
		}
//		System.out.println(map.size()+" entropy_total："+entropy_total);
		result = algo.div(map.size(), entropy_total);
//		System.out.println(result);
		return result;
	}

	/**
	 * @see 每個惡意軟體網域名稱長度的亂度變異數
	 * @param 惡意軟體名稱
	 * **/
	void features5(String fileName,double mean){
		ArrayList<String> list = es.queryStringDomain(this.esIndex, this.esType, fileName);
		for(String domain:list){
			
		}
		
		
	}

	
	
	
	
	void queryIdToDelete(){
		String str = "nologo1093.com.hsd1.va.comcast.net";
		String index = "dga" ; String type = "CRIME";
		ArrayList<String> arr = es.queryStringByJsonobject(index, type, str);
		for(String data:arr){
//			es.deleteES(index, type, data);
			System.out.println(data);
		}
		
	}
	
}
