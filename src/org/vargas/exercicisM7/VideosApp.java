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
					System.out.println("Esta opci�n a�n no existe");
			}
		} while (control);

	}

	
	/**
	 * M�todo para mostrar el men� recogiendo el n�mero de opci�n deseada por el usuario
	 * @return el n�mero elegido
	 */
	private static int mostrarMenu() {
		System.out.println();
		System.out.println("--------- Bienvenido/a la app para la gesti�n de v�deos -------");
		System.out.println();
		
		int opcion= 0;
		boolean control= true;
		
		do {
			Scanner scan= new Scanner (System.in);;
			System.out.println("Estas son las opciones que tienes:");
			System.out.println("0 - Salir");
			System.out.println("1 - Crear usuario");
			System.out.println("2 - A�adir v�deo a un usuario");
			System.out.println("3 - Ver todos los v�deos de un usuario");
			System.out.println("Marca el n�mero de tu opci�n deseada:");
			try{
				opcion= scan.nextInt();
				if (opcion>3 || opcion<0) { //si no es un n�mero dentro de las opciones... volvemos al bucle
					System.out.print("La opci�n que has marcado no est� dentro de las posibilidades. ");
					control=true;
				} else { //Si s� es n�mero dentro de opciones, salimos del bucle
					control=false;
				}
			} catch (InputMismatchException e) { //si no es un n�mero, seguimos en bucle
				System.out.print("Lo que has escrito no es un n�mero. ");
				control=true;
			}
		} while (control);
		return opcion;
	}

	/**
	 * M�todo para crear un usuario y a�adirlo a los datos 
	 * @param myDao Dao con el que gestionamos el acceso y manejo de datos
	 */
	private static void crearUsuario(GestionVideosDAO myDao) {
		Usuario prov= UsuarioFormularioEntrada.pedirDatosUsuario();
		if (prov==null) {
			System.out.println("Ha habido alg�n error en la creaci�n");
		} else {
			myDao.addUser(prov);
			System.out.print("El usuario ha sido creado: ");			
			System.out.println(myDao.searchUser(prov.getNom(), prov.getPassword()).toString());
		}
		
	}
	
	/**
	 * M�todo para mostrar los videos de un usuario
	 * @param myDao Dao con el que gestionamos el acceso a los datos
	 */
	private static void verVideosDeUsuario(GestionVideosDAO myDao) {
		List<Video> myVideos=null;
		Usuario user= null;
		user= pedirUsuario(myDao);
		if (user==null) {
			System.out.println("No existe ning�n usuario con esos datos");
		} else {
			myVideos= myDao.searchVideosOfUser(user);
			if (myVideos==null) {
				System.out.println("Ha habido un problema a la hora de cargar tus v�deos.");
			} else if (myVideos.isEmpty()) {
				System.out.println("A�n no has guardado ning�n v�deo.");
			} else {
				System.out.println("Estos son tus v�deos (un total de "+ myVideos.size() + "):");
				for (Video v: myVideos) {
					System.out.println(v.toString());
				}
			}
		}
			
	}
	
	/**
	 * M�todo para pedir por consola nombre y password y devolver un usuario si existe en los datos guardados
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
		System.out.println("Para verificar que eres el usuario necesitamos saber tu contrase�a: ");
		password= scan.nextLine();
		
		prov= myDao.searchUser(nombre, password);
		
		return prov;
	}

	/**
	 * M�todo para a�adir 1 v�deo a la colecci�nd de 1 usuario
	 * @param myDao DAO con el que gestionar el acceso de los datos
	 */
	private static void anadirVideoAUsuario(GestionVideosDAO myDao) {
		Usuario user= null;
		Video prov =null;
		user= pedirUsuario(myDao);
		if (user==null) {
			System.out.println("No existe ning�n usuario con esos datos");
		} else {
			prov= VideoFormularioEntrada.pedirDatosVideo();
			if (prov==null) {
				System.out.println("Ha habido alg�n error en la creaci�n");
			} else {
				myDao.addVideoToUser(prov, user);
				System.out.print("El video ha sido creado: ");
				System.out.println(prov.toString());
			}
			
		}
		
	}

}
