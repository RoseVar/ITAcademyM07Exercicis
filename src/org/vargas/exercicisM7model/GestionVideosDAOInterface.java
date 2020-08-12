package org.vargas.exercicisM7model;

import java.util.List;

public interface GestionVideosDAOInterface {
	
	//M�todos de inter�s
	
	// A�adir un usuario
	public void addUser(Usuario user);
	
	//A�adir un video al listado de videos de un Usuario
	public void addVideoToUser(Video video, Usuario user);
	
	//Buscar un usuario por nombre y contrase�a
	public Usuario searchUser(String name, String password);
	
	//Buscar todos los v�deos de un usuario
	public List<Video> searchVideosOfUser(Usuario user);
	

}
