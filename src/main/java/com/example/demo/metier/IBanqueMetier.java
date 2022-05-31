package com.example.demo.metier;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.demo.entities.Compte;
import com.example.demo.entities.Operation;

public interface IBanqueMetier {
	public Optional<Compte> consulterCompte(Long codeCpte);
	public void verser(Long codeCpte,double montant);
	public void retirer(Long codeCpte,double montant);
	public void virement(Long codeCpte1,Long codeCpte2,double montant);
	public Page<Operation> listOperation(Long codeCpte,int page,int size);
	Optional<Compte> findById(Long codeCpte);
}
