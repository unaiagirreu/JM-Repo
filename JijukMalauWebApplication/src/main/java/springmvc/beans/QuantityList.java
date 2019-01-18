package springmvc.beans;

import java.util.ArrayList;
import java.util.List;

public class QuantityList {
	List<String> lista;
	
	public QuantityList(){
		lista=new ArrayList<>();
	}

	public List<String> getLista() {
		return lista;
	}

	public void setLista(List<String> lista) {
		this.lista = lista;
	}
	

}
