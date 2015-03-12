package tool.function;

import java.util.ArrayList;
import java.util.Map;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.search.SearchPhaseExecutionException;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.json.JSONObject;



public class ElasticsearchFunction {
	
	ElasticsearchConnect es = new ElasticsearchConnect();

	/**
	 * 
	 * **/
	public void test(){
//		QueryBuilder qb = termQuery("multi", "test");
		int i = 0;
		SearchResponse scrollResp = es.client.prepareSearch("dga")
		        .setSearchType(SearchType.SCAN)
				.setTypes("CRIME")
		        .setScroll(new TimeValue(60000))
//		        .setQuery(qb)
		        .setSize(100).execute().actionGet(); //100 hits per shard will be returned for each scroll
		//Scroll until no hits are returned
		while (true) {
		    for (SearchHit hit : scrollResp.getHits().getHits()) {
		        //Handle the hit...
		    	i++;
		    }
		    scrollResp = es.client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
		    //Break condition: No hits are returned
		    if (scrollResp.getHits().getHits().length == 0) {
		        break;
		    }
		}
		System.out.println("total scroll："+i);
	}
	
	/**
	 * @category query
	 * @param String index,String type
	 * @see 依照index,type就可以查詢所以有的內容，並放到ArrayList後回傳
	 * @return ArrayList<String>
	 * */
	public ArrayList<String> queryTotalData(String index,String type){
		ArrayList<String> dataList = new ArrayList<String>();
		SearchResponse scrollResp = es.client.prepareSearch(index)
				.setSearchType(SearchType.SCAN).setTypes(type)
				.setScroll(new TimeValue(60000)).setSize(100).execute().actionGet();
		int i = 0;
		while (true) {
			for (SearchHit hit : scrollResp.getHits().getHits()) {
				dataList.add(hit.getSourceAsString());
				i++;
			}
			scrollResp = es.client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
			if (scrollResp.getHits().getHits().length == 0) {break;}
			
		}
		System.out.println("queryTotalData()	____	"+i);
		return dataList;
	}
	/**
	 * @category query
	 * @param String index,String type,String element
	 * @see 依照index,type就可以查詢，只取指定的element的資料，並且去除重複放到ArrayList後回傳
	 * @return ArrayList<String>
	 * */
	public ArrayList<String> queryDistinctElement(String index,String type,String element){
		ArrayList<String> arr = new ArrayList<String>();
		SearchResponse response = es.getClient().prepareSearch(index).setTypes(type)
				.setScroll(new TimeValue(10000)).setSize(50)
				.execute().actionGet();
		while(true){
			for(SearchHit hit:response.getHits().getHits()){
				String data = hit.getSource().get(element).toString();
				if(!arr.contains(data)){	
					arr.add(data);
				}
			}
			response = es.getClient().prepareSearchScroll(response.getScrollId()).
					setScroll(new TimeValue(10000)).execute().actionGet();
			if(response.getHits().getHits().length==0){break;}
		}
		return arr;
	}
	/**
	 * @category query
	 * @param String index,String type,String fileName
	 * @see 依照index,type,過濾條件就是用惡意軟體名稱
	 * @return ArrayList<String>
	 * **/
	public ArrayList<String> queryStringDomain(String index,String type,String fileName ){
		ArrayList<String> arr = new ArrayList<String>();
//		QueryBuilder qb
		try{
			SearchResponse response = es.getClient()
					.prepareSearch(index).setTypes(type).setQuery(QueryBuilders.matchQuery("fileName",fileName))
					.setScroll(new TimeValue(10000)).setSize(50)
					.execute().actionGet();
			while(true){
				for(SearchHit hit:response.getHits().getHits()){
					String data = hit.getSource().get("domainName").toString();
					arr.add(data);
				}
				response = es.getClient().prepareSearchScroll(response.getScrollId())
						.setScroll(new TimeValue(10000)).execute().actionGet();
				if(response.getHits().getHits().length == 0){break;}
				
			}
		}catch(SearchPhaseExecutionException e){
			System.out.println("====="+e.getMessage());
		}
		return arr;
	}
	/**
	 * @category query
	 * @param String index,String type,string str
	 * @see 依照index,type和domainName名字來搜尋str的內容
	 * **/
	public ArrayList<String> queryStringByJsonobject(String index,String type,String str){
		ArrayList<String> arr = new ArrayList<String>();
		SearchResponse response = es.getClient().prepareSearch(index) 
//				.setTypes(type) .setSearchType(SearchType.SCAN) 
//				.setSource(js.toString()) // <-- Query string in JSON format 
//				.setQuery(QueryBuilders.queryString("domainName:\"nologo1093.com.hsd1.va.comcast.net\""))
				.setQuery(QueryBuilders.queryString("domainName:\""+str+"\""))
				.setSize(10000).execute().actionGet(); 
		
		for(SearchHit hit:response.getHits().getHits()){
//			System.out.println(hit.getId()+":"+hit.getSource().get("domainName"));hit.getSourceAsString();
			arr.add(hit.getId());
		}
		return arr;
	}
	
	
	/**
	 * @category insert
	 * @param JSONObject data和String id
	 * @see  給要塞入的json和id就可以塞入Elasticsearch，一給定的id塞入
	 * @category 但index和type是固定的，index:dga，type:CRIME。
	 * */
	public void insertDomainToES_defineID(JSONObject data,String id){
		try {
			es.getClient().prepareIndex("dga","CRIME",id ).setSource(data)
			.execute().actionGet();
		} catch (ElasticsearchException e) {	e.printStackTrace();	}
	}
	/**
	 * @category insert
	 * @see  給json就可以塞入Elasticsearch，id是隨機產生的
	 * @category 但index和type是固定的，index:dga，type:CRIME。
	 * */
	public void insertDomainToES(JSONObject data){
		try {
			es.getClient().prepareIndex("dga","CRIME").setSource(data.toString())
			.execute().actionGet();
		} catch (ElasticsearchException e) {	e.printStackTrace();	}
	}
	/**
	 * @category delete
	 * @see 依id刪除Elasticsearch裡面的資料
	 * **/
	public void deleteES(String index,String type,String id){
		DeleteResponse response = es.getClient().prepareDelete(index , type , id)
		        .setOperationThreaded(false).execute().actionGet();
	}
}
