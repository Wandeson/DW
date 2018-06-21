package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "Cotacao")
@Table(name = "cotacao")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cotacao implements Serializable {

	private static final long serialVersionUID = -9060951025012118636L;

	private int codigo;

	private Moeda moeda;

	private String symbol;
	
	private Exchange exchange;

	private BigDecimal valor;

	private BigDecimal price_usd;

	private BigDecimal price_btc;

	private Calendar timestamp;

	public Cotacao() {
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "moeda_codigo")
	public Moeda getMoeda() {
		return moeda;
	}

	public void setMoeda(Moeda moeda) {
		this.moeda = moeda;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exchange_codigo")
	public Exchange getExchange() {
		return exchange;
	}

	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	@Column(name = "valor")
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public BigDecimal getPrice_usd() {
		return price_usd;
	}

	public void setPrice_usd(BigDecimal price_usd) {
		this.price_usd = price_usd;
	}

	public BigDecimal getPrice_btc() {
		return price_btc;
	}

	public void setPrice_btc(BigDecimal price_btc) {
		this.price_btc = price_btc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp")
	public Calendar getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
