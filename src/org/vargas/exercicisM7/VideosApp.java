package org.vargas.exercicisM7;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.vargas.exercicisM7model.GestionVideosDAO;
import org.vargas.exercicisM7model.Usuario;
import org.vargas.exercicisM7model.Video;

public class VideosApp {

	public static void main(String[] args) {

		GestionVideosDAO myDao= new GestionVideosDAO();
		
		Boolean control= true;
		do {
			int opcion= mostrarMenu();	
			switch (opcion) {
				case 0:
					System.out.println("Gracias por usar el servicio");
					control=false;
					break;
				case 1:
					crearUsuario(myDao);
					break;
				case 2:
					anadirVideoAUsuario(myDao);
					break;
				case 3:
					verVideosDeUsuario(myDao);
					break;
	
				default:
					System.out.println("Esta opción aún no existe");
			}
		} while (control);

	}

	
	/**
	 * Método para mostrar el menú recogiendo el número de opción deseada por el usuario
	 * @return el número elegido
	 */
	private static int mostrarMenu() {
		System.out.println();
		System.out.println("--------- Bienvenido/a la app para la gestión de vídeos -------");
		System.out.println();
		
		int opcion= 0;
		boolean control= true;
		
		do {
			Scanner scan= new Scanner (System.in);;
			System.out.println("Estas son las opciones que tienes:");
			System.out.println("0 - Salir");
			System.out.println("1 - Crear usuario");
			System.out.println("2 - Añadir vídeo a un usuario");
			System.out.println("3 - Ver todos los vídeos de un usuario");
			System.out.println("Marca el número de tu opción deseada:");
			try{
				opcion= scan.nextInt();
				if (opcion>3 || opcion<0) { //si no es un número dentro de las opciones... volvemos al bucle
					System.out.print("La opción que has marcado no está dentro de las posibilidades. ");
					control=true;
				} else { //Si sí es número dentro de opciones, salimos del bucle
					control=false;
				}
			} catch (InputMismatchException e) { //si no es un número, seguimos en bucle
				System.out.print("Lo que has escrito no es un número. ");
				control=true;
			}
		} while (control);
		return opcion;
	}

	/**
	 * Método para crear un usuario y añadirlo a los datos 
	 * @param myDao Dao con el que gestionamos el acceso y manejo de datos
	 */
	private static void crearUsuario(GestionVideosDAO myDao) {
		Usuario prov= UsuarioFormularioEntrada.pedirDatosUsuario();
		if (prov==null) {
			System.out.println("Ha habido algún error en la creación");
		} else {
			myDao.addUser(prov);
			System.out.print("El usuario ha sido creado: ");			
			System.out.println(myDao.searchUser(prov.getNom(), prov.getPassword()).toString());
		}
		
	}
	
	/**
	 * Método para mostrar los videos de un usuario
	 * @param myDao Dao con el que gestionamos el acceso a los datos
	 */
	private static void verVideosDeUsuario(GestionVideosDAO myDao) {
		List<Video> myVideos=null;
		Usuario user= null;
		user= pedirUsuario(myDao);
		if (user==null) {
			System.out.println("No existe ningún usuario con esos datos");
		} else {
			myVideos= myDao.searchVideosOfUser(user);
			if (myVideos==null) {
				System.out.println("Ha habido un problema a la hora de cargar tus vídeos.");
			} else if (myVideos.isEmpty()) {
				System.out.println("Aún no has guardado ningún vídeo.");
			} else {
				System.out.println("Estos son tus vídeos (un total de "+ myVideos.size() + "):");
				for (Video v: myVideos) {
					System.out.println(v.toString());
				}
			}
		}
			
	}
	
	/**
	 * Método para pedir por consola nombre y password y devolver un usuario si existe en los datos guardados
	 * @param myDao Dao con el que gestionamos el acceso a datos 
	 * @return usuario encontrado o null si no hay ninguno que corresponda a los datos entrados
	 */
	private static Usuario pedirUsuario(GestionVideosDAO myDao) {
		Usuario prov= null;
		String nombre;
		String password; 
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Para verificar que eres el usuario necesitamos saber tu nombre: ");
		nombre= scan.nextLine();
		System.out.println("Para verificar que eres el usuario necesitamos saber tu contraseña: ");
		password= scan.nextLine();
		
		prov= myDao.searchUser(nombre, password);
		
		return prov;
	}

	/**
	 * Método para añadir 1 vídeo a la colecciónd de 1 usuario
	 * @param myDao DAO con el que gestionar el acceso de los datos
	 */
	private static void anadirVideoAUsuario(GestionVideosDAO myDao) {
		Usuario user= null;
		Video prov =null;
		user= pedirUsuario(myDao);
		if (user==null) {
			System.out.println("No existe ningún usuario con esos datos");
		} else {
			prov= VideoFormularioEntrada.pedirDatosVideo();
			if (prov==null) {
				System.out.println("Ha habido algún error en la creación");
			} else {
				myDao.addVideoToUser(prov, user);
				System.out.print("El video ha sido creado: ");
				System.out.println(prov.toString());
			}
			
		}
		
	}

}
