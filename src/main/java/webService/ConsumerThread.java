package webService;

import java.util.ArrayList;
import java.util.List;

public class ConsumerThread extends Thread {

	private String cotacoes;

	private String url;
	
	private int delay;
	
	private List<String> cotacaoList;
	
	private List<String> urls;
	
	public ConsumerThread(String url, int delay, ArrayList<String> urls) {
		this.url = url;
		this.delay = delay;
		this.urls = urls;
		this.cotacaoList = new ArrayList<String>();
	}

	@Override
	public void run() {

		while (true) {
			
			try {
				
				if(urls == null) { // caso deseje fazer apenas uma requisicao
					cotacoes = new Consumer(url).consume();
					sleep(delay);
				}
				else { // caso deseje fazer várias requisicoes
					
					for(String value : urls) { // 
						String retorno = new Consumer(value).consume();
						cotacaoList.add(retorno);
						sleep(delay);
					}
							
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

	}

	public String getCotacoes() {
		return cotacoes;
	}

	public void setCotacoes(String cotacoes) {
		this.cotacoes = cotacoes;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public List<String> getCotacaoList() {
		return cotacaoList;
	}

	public void setCotacaoList(List<String> cotacaoList) {
		this.cotacaoList = cotacaoList;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

}
