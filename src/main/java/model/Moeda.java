package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "Moeda")
@Table(name = "moeda")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Moeda implements Serializable {

	private static final long serialVersionUID = -868902270350362080L;

	private int codigo;

	private String name;
	private String symbol;

	private List<Cotacao> cotacoes;

	private Set<Volume> moedasPrincipais = new HashSet<Volume>();
	private Set<Volume> moedasRelacionadas = new HashSet<Volume>();

	public Moeda() {
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

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "symbol")
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@OneToMany(mappedBy = "moeda", cascade = CascadeType.MERGE)
	public List<Cotacao> getCotacoes() {
		return cotacoes;
	}

	public void setCotacoes(List<Cotacao> cotacoes) {
		this.cotacoes = cotacoes;
	}

	public void addCotacao(Cotacao c) {
		c.setMoeda(this);
		cotacoes.add(c);
	}

	public void removeCotacao(Cotacao c) {
		cotacoes.remove(c);
		c.setMoeda(null);
	}

	@OneToMany(mappedBy = "moedaPrincipal", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Volume> getMoedasPrincipais() {
		return moedasPrincipais;
	}

	public void setMoedasPrincipais(Set<Volume> moedasPrincipais) {
		this.moedasPrincipais = moedasPrincipais;
	}

	@OneToMany(mappedBy = "moedaRelacionada", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Volume> getMoedasRelacionadas() {
		return moedasRelacionadas;
	}

	public void setMoedasRelacionadas(Set<Volume> moedasRelacionadas) {
		this.moedasRelacionadas = moedasRelacionadas;
	}
	
	public void addMoedaRelacionada(Moeda moedaRelacionada) {
		Volume volume = new Volume(this, moedaRelacionada);
		moedasRelacionadas.add(volume);
		moedaRelacionada.getMoedasPrincipais().add(volume);
	}
	
	public void removeMoedaRelacionada(Moeda moedaRelacionada) {
		for(Iterator<Volume> iterator = moedasRelacionadas.iterator(); iterator.hasNext(); ) {
			Volume volume = iterator.next();
			if(volume.getMoedaPrincipal().equals(this) && volume.getMoedaRelacionada().equals(moedaRelacionada)) {
				iterator.remove();
				volume.getMoedaRelacionada().getMoedasPrincipais().remove(volume);
				volume.setMoedaPrincipal(null);
				volume.setMoedaRelacionada(null);
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moeda other = (Moeda) obj;
		if (codigo != other.codigo)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

}
