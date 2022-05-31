package com.example.demo.metier;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.OperationRepository;
import com.example.demo.entities.Compte;
import com.example.demo.entities.CompteCourant;
import com.example.demo.entities.Operation;
import com.example.demo.entities.Retrait;
import com.example.demo.entities.Versement;

@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier{
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	public ClientRepository clientRepository;
	
	@Override
	public Optional<Compte> consulterCompte(Long codeCpte) {
		Optional<Compte> cp = compteRepository.findById(codeCpte);
        if(cp==null) throw new RuntimeException("Compte Introuvable!");
        return cp;
	}
	@Override
	public void verser(Long codeCpte, double montant) {
		if(findById(codeCpte).isPresent()){
	        Optional<Compte> cp = findById(codeCpte);
	        Versement v = new Versement(new Date() , montant, cp.get());
	        operationRepository.save(v);
	        cp.get().setSolde(cp.get().getSolde()+montant);
	        compteRepository.save(cp.get());
		}
	}
	@Override
	public void retirer(Long codeCpte, double montant) {
		Optional<Compte> cp = findById(codeCpte);
		double facilitesCaisse=0;
        if (findById(codeCpte).isPresent()) {
            if (cp.get() instanceof CompteCourant) 
            	facilitesCaisse = ((CompteCourant) cp.get()).getDecouvert();
            if (cp.get().getSolde()+facilitesCaisse < montant)
                    throw new RuntimeException("Solde insuffisant");
               
            Retrait r = new Retrait(new Date(), montant, cp.get());
            operationRepository.save(r);
            cp.get().setSolde(cp.get().getSolde() - montant);
            compteRepository.save(cp.get());
        }
		
	}
	@Override
	public void virement(Long codeCpte1, Long codeCpte2, double montant) {
		 if (!codeCpte1.equals(codeCpte2)) {
	            retirer(codeCpte1,montant);
	            verser(codeCpte2,montant);
	     }else{
	            throw new RuntimeException("Impossible le virement sur le meme compte");
	     }
		
	}
	@Override
	public Page<Operation> listOperation(Long codeCpte, int page, int size) {
		return operationRepository.listOperatiion(codeCpte,PageRequest.of(page,size));
	}
	@Override
	public Optional<Compte> findById(Long codeCpte) {
		return compteRepository.findById(codeCpte);
	}
	
	
	

}
