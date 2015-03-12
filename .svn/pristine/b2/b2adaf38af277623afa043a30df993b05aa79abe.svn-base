package virus.es;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteResponse;
import org.json.JSONException;
import org.json.JSONObject;

import tool.function.ElasticsearchConnect;
import tool.function.ElasticsearchFunction;

public class Main_parserToES {

	ElasticsearchFunction es = new ElasticsearchFunction();
	
	String inputFile = "virusData/unzipFileTotal.dns";
	
	public static void main(String[] args) {
		Main_parserToES mainClass = new Main_parserToES();
		mainClass.readFile();
	}
	void readFile() {
		try {
			JSONObject js = new JSONObject();
			FileReader fr = new FileReader(this.inputFile);
			BufferedReader br = new BufferedReader(fr);
			int i = 0;
			String filename = "";
			while(br.ready()){
				String name = br.readLine();
				if(name.indexOf(".pcap") > 0 ){
					filename = name.substring(0,name.indexOf(".pcap"));
//					System.out.println(filename);
					i++;
				}else{
					js.put("domainName", name);
					js.put("fileName", filename);
					es.insertDomainToES(js);
//					System.out.println(filename);
				}
			}
			System.out.println(i);
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("____finish_____");
	}



}
