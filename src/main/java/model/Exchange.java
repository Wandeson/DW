package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enuns.EnumExchange;
import webService.ExchangeInterface;

@Entity
@Table(name = "exchange")
public abstract class Exchange implements Serializable, ExchangeInterface {

	private static final long serialVersionUID = -5221291397174715035L;

	private int codigo;

	private String nome;

	protected List<Cotacao> cotacoes;

	protected EnumExchange enumExchange;

	private String endPoint;
	private String urlDocumentacaoApi;
	private String urlExhange;

	public Exchange() {
		cotacoes = new ArrayList<Cotacao>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Column(name = "nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Enumerated(EnumType.STRING)
	public EnumExchange getEnumExchange() {
		return enumExchange;
	}

	public void setEnumExchange(EnumExchange enumExchange) {
		this.enumExchange = enumExchange;
	}

	@OneToMany(mappedBy = "exchange", cascade = CascadeType.MERGE)
	public List<Cotacao> getCotacoes() {
		return cotacoes;
	}

	public void setCotacoes(List<Cotacao> cotacoes) {
		this.cotacoes = cotacoes;
	}

	public void addCotacao(Cotacao c) {
		c.setExchange(this);
		cotacoes.add(c);
	}

	public void removeCotacao(Cotacao c) {
		cotacoes.remove(c);
		c.setExchange(null);
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getUrlDocumentacaoApi() {
		return urlDocumentacaoApi;
	}

	public void setUrlDocumentacaoApi(String urlDocumentacaoApi) {
		this.urlDocumentacaoApi = urlDocumentacaoApi;
	}

	public String getUrlExhange() {
		return urlExhange;
	}

	public void setUrlExhange(String urlExhange) {
		this.urlExhange = urlExhange;
	}

}
