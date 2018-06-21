package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VolumeId implements Serializable{

	private static final long serialVersionUID = 9104043445314648584L;

	private int moedaPrincipalCodigo;
	private int moedaRelacionadaCodigo;
	
	public VolumeId() {}
	
	public VolumeId(int moedaPrincipalCodigo, int moedaRelacionadaCodigo) {
		this.moedaPrincipalCodigo = moedaPrincipalCodigo;
		this.moedaRelacionadaCodigo = moedaRelacionadaCodigo;
	}

	@Column(name = "moeda_principal_codigo")
	public int getMoedaPrincipalCodigo() {
		return moedaPrincipalCodigo;
	}
	
	public void setMoedaPrincipalCodigo(int moedaPrincipalCodigo) {
		this.moedaPrincipalCodigo = moedaPrincipalCodigo;
	}
	
	@Column(name = "moeda_relacionada_codigo")
	public int getMoedaRelacionadaCodigo() {
		return moedaRelacionadaCodigo;
	}
	
	public void setMoedaRelacionadaCodigo(int moedaRelacionadaCodigo) {
		this.moedaRelacionadaCodigo = moedaRelacionadaCodigo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + moedaPrincipalCodigo;
		result = prime * result + moedaRelacionadaCodigo;
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
		VolumeId other = (VolumeId) obj;
		if (moedaPrincipalCodigo != other.moedaPrincipalCodigo)
			return false;
		if (moedaRelacionadaCodigo != other.moedaRelacionadaCodigo)
			return false;
		return true;
	}
}
