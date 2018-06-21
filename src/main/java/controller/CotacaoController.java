package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import enuns.EnumExchange;
import model.Cotacao;
import services.CotacaoServices;

@Named
@ApplicationScoped
public class CotacaoController {

	private List<Cotacao> cotacaoMC;

	private List<Cotacao> cotacaoBinance;

	private List<Cotacao> cotacaoPoloniex;

	private List<Cotacao> cotacaoCoinBase;
	
	private List<Cotacao> cotacaoBraziliex;
	
	private List<Cotacao> cotacaoBittrex;
	
	@Inject
	private CotacaoServices cotacaoServices;

	@PostConstruct
	public void init() {
		setCotacaoMC(new ArrayList<Cotacao>());
		setCotacaoBinance(new ArrayList<Cotacao>());
		setCotacaoPoloniex(new ArrayList<Cotacao>());
		setCotacaoCoinBase(new ArrayList<Cotacao>());
		/*setCotacaoBraziliex(new ArrayList<Cotacao>());*/
		setCotacaoBittrex(new ArrayList<Cotacao>());
	}

	public void update() {
		cotacaoBittrex =  cotacaoServices.get(EnumExchange.BITTREX);
		cotacaoBinance = cotacaoServices.get(EnumExchange.BINANCE);
		cotacaoMC = cotacaoServices.get(EnumExchange.COIN_MARKET_CAP);
		cotacaoPoloniex = cotacaoServices.get(EnumExchange.POLONIEX);
		cotacaoCoinBase = cotacaoServices.get(EnumExchange.COIN_MARKET_CAP);
		/*cotacaoBraziliex = cotacaoServices.get(EnumExchange.BRAZILIEX);*/
		
	}

	public List<Cotacao> getCotacaoMC() {
		return cotacaoMC;
	}

	public void setCotacaoMC(List<Cotacao> cotacaoMC) {
		this.cotacaoMC = cotacaoMC;
	}

	public List<Cotacao> getCotacaoBinance() {
		return cotacaoBinance;
	}

	public void setCotacaoBinance(List<Cotacao> cotacaoBinance) {
		this.cotacaoBinance = cotacaoBinance;
	}

	public List<Cotacao> getCotacaoPoloniex() {
		return cotacaoPoloniex;
	}

	public void setCotacaoPoloniex(List<Cotacao> cotacaoPoloniex) {
		this.cotacaoPoloniex = cotacaoPoloniex;
	}

	public List<Cotacao> getCotacaoCoinBase() {
		return cotacaoCoinBase;
	}

	public void setCotacaoCoinBase(List<Cotacao> cotacaoCoinBase) {
		this.cotacaoCoinBase = cotacaoCoinBase;
	}

	public List<Cotacao> getCotacaoBraziliex() {
		return cotacaoBraziliex;
	}

	public void setCotacaoBraziliex(List<Cotacao> cotacaoBraziliex) {
		this.cotacaoBraziliex = cotacaoBraziliex;
	}

	public List<Cotacao> getCotacaoBittrex() {
		return cotacaoBittrex;
	}

	public void setCotacaoBittrex(List<Cotacao> cotacaoBittrex) {
		this.cotacaoBittrex = cotacaoBittrex;
	}

}
