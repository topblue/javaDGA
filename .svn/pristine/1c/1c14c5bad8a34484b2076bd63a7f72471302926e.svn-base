package temp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;

public class WekaDataFormat_arff_Title {

	public static void main(String[] args) throws IOException, JSONException {
		WekaDataFormat_arff_Title wat = new WekaDataFormat_arff_Title();
		String readFileName="data/dictionary.txt";
		String outputWekaAffFileName="data/wekaTitle.arff";
		FileReader fr = new FileReader(readFileName);
		BufferedReader br = new BufferedReader(fr);
		while(br.ready()){
			JSONArray jsarr = new JSONArray(br.readLine());
			for(int i=0;i<jsarr.length();i++){
				String d = "@attribute "+jsarr.getString(i)+" real";
				wat.appendWrite(outputWekaAffFileName, d);
			}
			break;
		}
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
}
