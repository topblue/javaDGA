package tool.function;

import java.util.Properties;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;



public class ElasticsearchConnect {

	
	Settings settings;
	
	Client client;
	
	public ElasticsearchConnect(){
		this.settings=ImmutableSettings.settingsBuilder()
				.put("cluster.name","chu_elasticsearch")
				.put("client.transport.ping_timeout","30s")
				.put("node.client",true)
				.put("client.transport.sniff",true).build();
//				.put("username","security")
//				.put("password","social@analysis").build();
		this.client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("localhost",9300));
//		this.client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("220.133.36.129",9300));
	}
	public void closeClient(){
		client.close();
	}
	
	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	

}
