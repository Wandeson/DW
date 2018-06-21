package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Usuario;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		if (value != null && !value.isEmpty()) {
			return (Usuario) uiComponent.getAttributes().get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (value instanceof Usuario) {
			Usuario entity = (Usuario) value;
			if (entity != null && entity instanceof Usuario && entity.getCodigo() != 0) {
				uiComponent.getAttributes().put(Integer.toString(entity.getCodigo()), entity);
				return Integer.toString(entity.getCodigo());
			}
		}
		return "";
	}
}
