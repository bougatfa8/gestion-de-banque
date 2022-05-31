package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CPTE",
discriminatorType=DiscriminatorType.STRING,length=2)
public abstract class Compte implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private Long codeCompte;
	private Date dateCreation;
	private double solde;
	@ManyToOne
	@JoinColumn(name="CODE_CLI")
	private Client client;
	@OneToMany(mappedBy="compte",fetch=FetchType.LAZY)
	private Collection<Operation> operations;
	
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Compte(Long codeCompte, Date dateCreation, double solde, Client client) {
		super();
		this.codeCompte = codeCompte;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.client = client;
	}

	public Long getCodeCompte() {
		return codeCompte;
	}

	public void setCodeCompte(Long codeCompte) {
		this.codeCompte = codeCompte;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}
	
	
	
}
