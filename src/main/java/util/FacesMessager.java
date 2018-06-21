package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesMessager {
	public static void mensagemDeSucesso(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", message)); 
	}
	
	public static void mensagemDeErro(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", message)); 
	}
	
	public static void mensagemDeAviso(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,	"Atenção!", message)); 
	}
}
