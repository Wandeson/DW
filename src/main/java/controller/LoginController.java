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
public class LoginController implements Serializable {

	private static final long serialVersionUID = -6145851669264292116L;

	@Inject
	private UsuarioServices usuarioServices;

	private Usuario usuario;

	@PostConstruct
	public void init() {
		insert();
	}

	public void insert() {
		this.usuario = new Usuario();
	}

	public void login() {
		try {
			// Todas as verificacoes são feitas na services:
			usuario = usuarioServices.findUsuario(usuario);

			FacesMessager.mensagemDeSucesso("Login realizado com sucesso!");
			redirecionar("/paginas/cotacao.xhtml");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuario);
		} catch (Exception e) {
			FacesMessager.mensagemDeErro(e.getMessage());
		}
	}

	public void novoCadastro() {
		redirecionar("/paginas/cadastro.xhtml");
	}

	private void redirecionar(String url) {
		try {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
