package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.reflections.Reflections;

public class ReflectionClassesFinder {

	private Set<Class<?>> exchanges;

	public ReflectionClassesFinder() {
		
		// indica o caminho onde se deve procurar pelas classes
		Reflections ref = new Reflections("");
		
		// pega as classes encontradas que tem a anotacao ExchangeAnnotation
		exchanges = ref.getTypesAnnotatedWith(ExchangeAnnotation.class);
		

	}

	// instancia um objeto da classe recebida como parametro
	public static Object createNewInstance(Class<?> classe) {
		Constructor<?> construtor;
		try {
			construtor = classe.getConstructors()[0];
			Object object = construtor.newInstance();
			return object;
		} catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Set<Class<?>> getExchanges() {
		return exchanges;
	}

	public void setExchanges(Set<Class<?>> exchanges) {
		this.exchanges = exchanges;
	}
}
