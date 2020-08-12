package org.vargas.exercicisM7;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import org.vargas.exercicisM7model.Usuario;
import org.vargas.exercicisM7model.Video;

public class UsuarioFormularioEntrada {
	
	
	public static Usuario pedirDatosUsuario() {
		Usuario prov= null;
		Boolean control=true;
		Scanner scan = new Scanner (System.in);
		
		do {
			String nom;
			String cognom;
			String password;
			GregorianCalendar dataRegistre;
			
			System.out.println("Vamos a crear un usuario nuevo, para eso necesitamos los siguientes datos: ");
			try {
				System.out.println("Introduce tu nombre: ");
				nom= scan.nextLine();			
				if (nom.isEmpty()) { throw new NullPointerException("No ha puesto nombre en el formulario");}; 	
				
				System.out.println("Introduce tu apellido: ");				
				cognom= scan.nextLine();
				if (cognom.isEmpty()) { throw new NullPointerException("No ha puesto apellido en el formulario");}; 
				
				System.out.println("Introduce tu contraseña: ");				
				password= scan.nextLine();
				if (password.isEmpty()) { throw new NullPointerException("No ha puesto contraseña en el formulario");};
				
				dataRegistre= new GregorianCalendar();
								
				prov= new Usuario(nom, cognom, password, dataRegistre);	
				control=false;
				
			}catch (NullPointerException ex) {
				System.out.println("Falta algún dato en el formulario. Intentalo de nuevo y no te saltes nada.");				
			}
			
		} while (control);
		
		return prov;
	}
	
}
