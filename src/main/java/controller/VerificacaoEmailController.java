package controller;

import java.io.IOException;
import java.io.Serializable;

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
public class VerificacaoEmailController implements Serializable {
	private static final long serialVersionUID = 8436702934991757315L;

	@Inject
	private UsuarioServices usuarioServices;

	private Usuario usuario;

	private String codigoVerificacao = "";

	@PostConstruct
	public void init() {
		insert();
	}

	public void insert() {
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	}

	public void verificarEmail() {

		try {
			usuarioServices.verificarEmail(usuario, codigoVerificacao);
			FacesMessager.mensagemDeSucesso("Email verificado com sucesso!");
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

	public String getCodigoVerificacao() {
		return codigoVerificacao;
	}

	public void setCodigoVerificacao(String codigoVerificacao) {
		this.codigoVerificacao = codigoVerificacao;
	}
}
