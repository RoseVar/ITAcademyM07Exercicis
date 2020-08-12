package org.vargas.exercicisM7model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GestionVideosDAO implements GestionVideosDAOInterface{
	
	Map<Usuario, List<Video>> elArmarioDeVideos;
	
	public GestionVideosDAO() {
		elArmarioDeVideos= new HashMap <Usuario, List<Video>>();
		//cargar datos de prueba
		cargarDatosTest();
	}

	private void cargarDatosTest() {
		Usuario anna= new Usuario("Anna", "Perez", "1234", new GregorianCalendar());
		addUser(anna);
		Video v1=null;
		List<String> tags = new ArrayList<String>();
		tags.add("relajacion");
		tags.add("relax");
		Video v2=null;
		List<String> tags2 = new ArrayList<String>();
		tags2.add("heavy");
		tags2.add("rock");
		//Las pongo las 2 dentro del mismo try, aunque deberían estar por separado (por si peta la primera
		//que pueda añadir la segunda), pero como esto es para datos de prueba, estará bien y no saltará el catch
		try {
			v1 = new Video(new URL("https://youtu.be/EjFRulacSCs"), "Musica Chamana", tags);
			v2= new Video(new URL("https://youtu.be/23Cca-7UX-E"), "If I could fly", tags2);
		} catch (MalformedURLException e) {
			//e.printStackTrace();
		}
		if (v1!=null) {
			addVideoToUser(v1, anna);
		}
		if (v2!=null) {
			addVideoToUser(v2, anna);
		}
		
		
	}

	@Override
	public void addUser(Usuario user) {
		elArmarioDeVideos.put(user, new ArrayList<Video>());
		
	}

	@Override
	public void addVideoToUser(Video video, Usuario user) {
		elArmarioDeVideos.get(user).add(video);
		
	}

	@Override
	public Usuario searchUser(String name, String password) {
		Usuario prov= null;
		Iterator<Usuario> it = elArmarioDeVideos.keySet().iterator();
		while(it.hasNext()){
		  Usuario key = it.next();
		  if (key.getNom().equalsIgnoreCase(name) & key.getPassword().equals(password)) {
			  prov= key;
		  }
		}
		return prov;
	}

	@Override
	public List<Video> searchVideosOfUser(Usuario user) {
		List<Video> prov= null;
		prov= elArmarioDeVideos.get(user);
		return prov;
	}
	

}
