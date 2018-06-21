package webService;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Consumer {

	private Client client;

	private WebResource webResource;

	private ClientResponse response;

	private String url;

	private String cotacao;

	public Consumer(String url) {
		this.url = url;
	}

	public String consume() {
		try {
			client = Client.create();
			webResource = client.resource(url);
			response = webResource.accept("application/json").get(ClientResponse.class);
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			cotacao = response.getEntity(String.class);

			return cotacao;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

}