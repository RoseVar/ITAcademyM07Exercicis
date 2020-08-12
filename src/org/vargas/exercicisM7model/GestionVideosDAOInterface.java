package org.vargas.exercicisM7model;

import java.util.List;

public interface GestionVideosDAOInterface {
	
	//Métodos de interés
	
	// Añadir un usuario
	public void addUser(Usuario user);
	
	//Añadir un video al listado de videos de un Usuario
	public void addVideoToUser(Video video, Usuario user);
	
	//Buscar un usuario por nombre y contraseña
	public Usuario searchUser(String name, String password);
	
	//Buscar todos los vídeos de un usuario
	public List<Video> searchVideosOfUser(Usuario user);
	

}
