package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.OperationRepository;
import com.example.demo.entities.Client;
import com.example.demo.entities.Compte;
import com.example.demo.entities.CompteCourant;
import com.example.demo.entities.CompteEpargne;
import com.example.demo.entities.Retrait;
import com.example.demo.entities.Versement;
import com.example.demo.metier.IBanqueMetier;

@SpringBootApplication
public class MaBanqueApplication implements CommandLineRunner{
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier banqueMetier;
	
	public static void main(String[] args) {
		SpringApplication.run(MaBanqueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		 Client c1=clientRepository.save(new Client("Hassan","Hassan@gmail.com"));
		 Client c2=clientRepository.save(new Client("Rachid","Rachid@gmail.com"));
		  
		 Compte cp1=compteRepository.save(new CompteCourant(2201l,new
		 Date(),90000,c1,6000)); Compte cp2=compteRepository.save(new
		 CompteEpargne(2202l,new Date(),6000,c2,5.5));
		  
		  
		 operationRepository.save(new Versement(new Date(),9000,cp1));
		 operationRepository.save(new Versement(new Date(),6000,cp1));
		 operationRepository.save(new Versement(new Date(),2300,cp1));
		 operationRepository.save(new Retrait(new Date(),9000,cp1));
		  
		  
		 operationRepository.save(new Versement(new Date(),4500,cp2));
		 operationRepository.save(new Versement(new Date(),320,cp2));
		 operationRepository.save(new Versement(new Date(),2000,cp2));
		 operationRepository.save(new Retrait(new Date(),1000,cp2));
		  
		 banqueMetier.verser(2201l, 20000); banqueMetier.retirer(2201l, 1000);
		 banqueMetier.virement(2201l, 2202l, 9000);
		 
		
	}

}
