package org.vargas.exercicisM7model;

import java.util.GregorianCalendar;

public class Usuario {
	
	//Atributos
	String nom;
	String cognom;
	String password;
	GregorianCalendar dataRegistre;
	
	//Constructor
	public Usuario(	String nom,	String cognom, String password,	GregorianCalendar dataRegistre) {
		this.nom= nom;
		this.cognom= cognom;
		this.password= password;
		this.dataRegistre= dataRegistre;
	}

	//Getters and setters	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognom() {
		return cognom;
	}

	public void setCognom(String cognom) {
		this.cognom = cognom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public GregorianCalendar getDataRegistre() {
		return dataRegistre;
	}

	public void setDataRegistre(GregorianCalendar dataRegistre) {
		this.dataRegistre = dataRegistre;
	}

	
	//Sobreescritos equals & HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognom == null) ? 0 : cognom.hashCode());
		result = prime * result + ((dataRegistre == null) ? 0 : dataRegistre.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Boolean b;
		if (obj==null) { // si obj es null --> false
			b= false;
		} else {
			if (this==obj) { //Si son el mismo objeto --> true
				b= true;
			} else {
				if (obj instanceof Usuario) { //si obj es una instancia de Usuario...
					Usuario other= (Usuario) obj;
					//... si tienen todos los campos iguales -->true
					if (this.nom.equalsIgnoreCase(other.nom) & this.cognom.equalsIgnoreCase(other.cognom)& 
							this.password.equalsIgnoreCase(other.password) & this.dataRegistre==other.dataRegistre) {
						b= true;					
					}else { //... si no --> false
						b= false;
					}							
				} else { //si no es una instancia de esta clase --> false
					b= false;
				}
			}
		}
		return b;
	}

	@Override
	public String toString() {
		return "Usuario [nom=" + nom + ", cognom=" + cognom + ", password=" + password + ", dataRegistre="
				+ dataRegistre.getTime() +"]";
	}
	
	
	

	

	

}
