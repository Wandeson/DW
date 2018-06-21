package model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 3493514249289846267L;

	private int codigo;
	private Integer codigoVerificacao;
	private Boolean contaVerificada;

	private String login;
	private String senha;
	private String email;

	private Calendar dataNascimento;
	private Calendar ultimoLogin;

	public Usuario() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// (Marcelo) melhor usar IDENTITY em vez de AUTO se tratando de MySQL
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Column(name = "codigo_verificacao")
	public Integer getCodigoVerificacao() {
		return codigoVerificacao;
	}

	public void setCodigoVerificacao(Integer codigoVerificacao) {
		this.codigoVerificacao = codigoVerificacao;
	}

	@Column(name = "conta_verificada")
	public Boolean getContaVerificada() {
		return contaVerificada;
	}

	public void setContaVerificada(Boolean contaVerificada) {
		this.contaVerificada = contaVerificada;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Calendar getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(Calendar ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", login=" + login + ", senha=" + senha + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if (codigo != other.codigo)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

}
