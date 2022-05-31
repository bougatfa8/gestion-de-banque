package com.example.demo.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation{

	private static final long serialVersionUID = 1L;

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retrait(Date dateOperation, double montant, Compte compte) {
		super(dateOperation, montant, compte);
		// TODO Auto-generated constructor stub
	}
	
}
