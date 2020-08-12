package org.vargas.exercicisM7;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.vargas.exercicisM7model.Video;

public class VideoFormularioEntrada {
	
	
	public static Video pedirDatosVideo() {
		Video prov= null;
		Boolean control=true;
		Scanner scan = new Scanner (System.in);
		
		do {
			URL url;
			String title;
			String tagsProv;
			List<String> tagList= new ArrayList<String>();
			
			System.out.println("Vamos a crear un vídeo nuevo, para eso necesitamos los siguientes datos: ");
			try {
				System.out.println("Introduce su URL: ");
				String urlProv= scan.nextLine();			
				if (urlProv.isEmpty()) { throw new NullPointerException("No ha puesto url en el formulario");}; 
				url= new URL(urlProv);		
				
				System.out.println("Introduce su título: ");				
				title= scan.nextLine();
				if (title.isEmpty()) { throw new NullPointerException("No ha puesto titulo en el formulario");}; 
				
				System.out.println("Introduce sus etiquetas (tags) separadas por comas.");
				tagsProv= scan.nextLine();
				if (title.isEmpty()) { throw new NullPointerException("No ha puesto tags en el formulario");}; 
				
				String[] tagsArray= tagsProv.split(",");
				for (String tag: tagsArray) {
					tagList.add(tag.trim());
				}
				
				prov= new Video (url, title, tagList);
				control=false;
				
			} catch (MalformedURLException e) {
				System.out.println("La URL puesta no está bien escrita.");				
				scan.nextLine();
				
			}catch (NullPointerException ex) {
				System.out.println("Falta algún dato en el formulario. Intentalo de nuevo y no te saltes nada.");
				scan.nextLine();
			}
			
		} while (control);
		
		return prov;
	}
	
}
