package controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import model.Usuario;

@Named
@RequestScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = 5889281109606217582L;

	private ExternalContext ec;
	private boolean status = true;
	@PostConstruct
	public void init() {
		carregaContexto();
		verificarStatusUsuario();
	}

	public void openCotacao()  {
		try {
			ec.redirect(ec.getRequestContextPath() + "/paginas/cotacao.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void openAtualizarCadastro()  {
		try {
			ec.redirect(ec.getRequestContextPath() + "/paginas/alterarCadastro.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void openGrafo()  {
		try {
			ec.redirect(ec.getRequestContextPath() + "/paginas/grafo.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void openContato()  {
		try {
			ec.redirect(ec.getRequestContextPath() + "/paginas/contato.xhtml");
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void openSobre()  {
		try {
			ec.redirect(ec.getRequestContextPath() + "/paginas/sobre.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void openVerificarEmail()  {
		try {
			ec.redirect(ec.getRequestContextPath() + "/paginas/verificacaoEmail.xhtml");
			//verificarStatusUsuario();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sair()  {
		try {
			ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioLogado");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void carregaContexto() {
		ec = FacesContext.getCurrentInstance().getExternalContext();
	}
	public void verificarStatusUsuario() {
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
		if(usuario.getContaVerificada()) {
			status = false;
		}
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
