package services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import dao.MoedaDAO;
import model.Moeda;

public class MoedaServices implements Serializable {

	private static final long serialVersionUID = -7788136131699265378L;
	
	@Inject
	private MoedaDAO moedaDAO;

	public void save(Moeda moeda) throws Exception {
		moedaDAO.save(moeda);
	}

	public void delete(Moeda moeda) throws Exception {
		moedaDAO.delete(moeda);
	}

	public List<Moeda> getAll(){
		return moedaDAO.getAll();
	}

}
