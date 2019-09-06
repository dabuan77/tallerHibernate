package org.springframework.samples.petclinic;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.samples.petclinic.factura.Bill;
import org.springframework.samples.petclinic.factura.BillRepository;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.PetRepository;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.samples.petclinic.visit.VisitRepository;


/**
 * PetClinic Spring Boot Application.
 * 
 * @author Dave Syer
 *
 */
@SpringBootApplication
public class HibernateApplication implements CommandLineRunner {
	@Autowired
	PetRepository petRepository;
	@Autowired
	VisitRepository visitRepository;
	@Autowired
	BillRepository billRepository;
	
    public static void main(String[] args) throws Exception {

    	
        SpringApplication.run(HibernateApplication.class, args);
        
    }
    
    
    
    @Override
    @Transactional
    public void run(String... arg0) {
    	Visit visit;
    	
    	Bill b = new Bill();
    	List<Visit> visits;
		
		System.out.println("***********************************************");  	
    	
		Pet p = petRepository.findById(8);
		visits = visitRepository.findByPetId(p.getId());
		
				
    	for(Visit v : visits) {
		 	System.out.println(v.toString());
    		b=v.getBill();
    		if (b == null) {
    			System.out.println("No tiene facturas");
    		}else {
    		 	System.out.println("Factura");
    		 	System.out.println(b.toString());
    		}
    	}   
    	    	
    	
    	b.setIdFactura(1234567890);
    	b.setMoney(1.0);
    	b.setPaymentDate(new Date());
    	billRepository.save(b);
//    	
//    	visit=visitRepository.findById(2);
//    	visit.setBill(b);
//    	visitRepository.save(visit);

    	
		System.out.println("***********************************************");
    }
}


