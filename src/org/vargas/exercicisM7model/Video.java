package org.vargas.exercicisM7model;

import java.net.URL;
import java.util.List;

public class Video {
	//Atributos
	URL url; 
	String titol;
	List<String> tags;

	//Constructor
	public Video(URL url, String titol, List<String>tags) {
		this.url=url;
		this.titol= titol;
		this.tags= tags;
	}
	
	//Setters and getters
	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	
	//Sobreescritura hashCode y Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result + ((titol == null) ? 0 : titol.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean b; 
		if (obj==null) { //Si es null --> false
			b= false;
		} else {
			if (this==obj) { //si es el mismo objeto --> true
				b=true;
			} else {
				if (obj instanceof Video) { //si es de una instancia de esta clase...
					Video other= (Video)obj;
					b= this.url.equals(other.url); //...será true /false dependiendo de la url
				} else {
					b= false; //si no es una instancia de esta clase --> false
				}
			}
		}
		return b;
	}
	
		
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		
		sb.append("Video [url= "); sb.append(url);
		sb.append(", titulo= "); sb.append(titol);
		sb.append(", tags= {"); 
		for (String tag: tags) {
			sb.append(tag + ", ");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.deleteCharAt(sb.lastIndexOf(" "));
		sb.append("}]");
		
		return sb.toString();
	}

	//Otros métodos de interés
	/**
	 * Método para añadir una etiqueta (tag) a la lista de etiquetas (tags) 
	 * @param tag Etiqueta en string
	 */
	public void addTag(String tag) {
		tags.add(tag);
	}

	
}
