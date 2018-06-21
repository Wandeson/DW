package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Volume")
@Table(name = "volume")
public class Volume implements Serializable{

	private static final long serialVersionUID = 8438895266460925308L;

    private VolumeId codigo;
	
	private Moeda moedaPrincipal;
	private Moeda moedaRelacionada;
	
	private BigDecimal volume;
	
	private Calendar timestamp;

	public Volume() {
		timestamp = Calendar.getInstance();
	}
	
	public Volume(Moeda moedaPrincipal, Moeda moedaRelacionada) {
		this.moedaPrincipal = moedaPrincipal;
		this.moedaRelacionada = moedaRelacionada;
		this.codigo = new VolumeId(moedaPrincipal.getCodigo(), moedaRelacionada.getCodigo());
		timestamp = Calendar.getInstance();
	}
	
	@EmbeddedId
	public VolumeId getCodigo() {
		return codigo;
	}

	public void setCodigo(VolumeId codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("moedaPrincipalCodigo")
	public Moeda getMoedaPrincipal() {
		return moedaPrincipal;
	}

	public void setMoedaPrincipal(Moeda moedaPrincipal) {
		this.moedaPrincipal = moedaPrincipal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("moedaRelacionadaCodigo")
	public Moeda getMoedaRelacionada() {
		return moedaRelacionada;
	}

	public void setMoedaRelacionada(Moeda moedaRelacionada) {
		this.moedaRelacionada = moedaRelacionada;
	}
	
	@Column(name = "valor")
	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp")
	public Calendar getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((moedaPrincipal == null) ? 0 : moedaPrincipal.hashCode());
		result = prime * result + ((moedaRelacionada == null) ? 0 : moedaRelacionada.hashCode());
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
		Volume other = (Volume) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (moedaPrincipal == null) {
			if (other.moedaPrincipal != null)
				return false;
		} else if (!moedaPrincipal.equals(other.moedaPrincipal))
			return false;
		if (moedaRelacionada == null) {
			if (other.moedaRelacionada != null)
				return false;
		} else if (!moedaRelacionada.equals(other.moedaRelacionada))
			return false;
		return true;
	}
	
}
