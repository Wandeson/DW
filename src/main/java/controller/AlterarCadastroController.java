package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Usuario;
import services.UsuarioServices;
import util.FacesMessager;

@Named
@RequestScoped
public class AlterarCadastroController implements Serializable {

	private static final long serialVersionUID = -4285904347456620573L;

	@Inject
	private UsuarioServices usuarioServices;

	private Usuario usuario;

	private String repitaSenha, email, senha;

	@PostConstruct
	public void init() {
		insert();
	}

	public void insert() {
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	}

	public void alterarCadastro() {

		try {
			usuarioServices.alterarCadastro(usuario, email, senha, repitaSenha);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuario);
			FacesMessager.mensagemDeSucesso("Dados atualizados com sucesso!");
			redirecionar("/paginas/cotacao.xhtml");
		} catch (Exception e) {
			FacesMessager.mensagemDeErro(e.getMessage());
		}

	}

	public void voltarCotacao() {
		redirecionar("/paginas/cotacao.xhtml");
	}

	private void redirecionar(String url) {
		try {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.getFlash().setKeepMessages(true);
			ec.redirect(ec.getRequestContextPath() + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public UsuarioServices getUsuarioServices() {
		return usuarioServices;
	}

	public void setUsuarioServices(UsuarioServices usuarioServices) {
		this.usuarioServices = usuarioServices;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getRepitaSenha() {
		return repitaSenha;
	}

	public void setRepitaSenha(String repitaSenha) {
		this.repitaSenha = repitaSenha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
