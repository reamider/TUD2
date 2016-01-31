package com.smatyjas.hibernate.model.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.smatyjas.hibernate.model.domain.Bilet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class BiletManagerTest {

	private final String rodzaj = "Ulgowy";
	private final String opis = "Bilet ulgowy";
	private final String rodzaj2 = "Normalny";
	private final String opis2 = "Bilet normalny";
	
	
	private final List<Long> dodaneBilety = new ArrayList<Long>();
	
	@Autowired
	BiletManager biletM;
	
	   @Before
	    public void checkAddedBilety() {

	        List<Bilet> bilety = biletM.pobierzBilety();

	        for(Bilet bilet : bilety)
	            dodaneBilety.add(bilet.getId());
	    }
	   
		@After
	    public void removeBilety() {

	    	List<Bilet> bilety = biletM.pobierzBilety();

	        boolean usun;

	        for(Bilet bilet : bilety) {
	            usun = true;
	            for (Long bilet2 : dodaneBilety)
	                if (bilet.getId() == bilet2) {
	                usun = false;
	                break;
	                }
	            if(usun)
	                biletM.usunBilet(bilet);
	        }
	    }

		@Test
		public void checkAddBilet(){
			
			int n = biletM.pobierzBilety().size();
			
			Bilet bilet = new Bilet(rodzaj, opis);
			biletM.dodajBilet(bilet);
			
			Bilet otrzymanyBilet = biletM.pobierzBiletPoID(bilet);
			assertEquals(bilet.getId(), otrzymanyBilet.getId());
			assertEquals(rodzaj, otrzymanyBilet.getRodzaj());
			assertEquals(opis, otrzymanyBilet.getOpis());
			
			assertEquals(n+1, biletM.pobierzBilety().size());
		}
		
		@Test
		public void checkEditBilet(){
			
			Bilet bilet = new Bilet(rodzaj, opis);
			biletM.dodajBilet(bilet);
			
			Bilet otrzymanyBilet = biletM.pobierzBiletPoID(bilet);
			assertEquals(bilet.getId(), otrzymanyBilet.getId());
			assertEquals(rodzaj, otrzymanyBilet.getRodzaj());
			assertEquals(opis, otrzymanyBilet.getOpis());
			
			otrzymanyBilet.setRodzaj(rodzaj2);
			otrzymanyBilet.setOpis(opis2);
			biletM.edytujBilet(otrzymanyBilet);
			
			Bilet otrzymanyBilet2 = biletM.pobierzBiletPoID(otrzymanyBilet);
			assertEquals(otrzymanyBilet.getId(), otrzymanyBilet2.getId());
			assertEquals(rodzaj2, otrzymanyBilet2.getRodzaj());
			assertEquals(opis2, otrzymanyBilet2.getOpis());
			
		}
		
		@Test
		public void checkRemoveBilet() {
			
			Bilet bilet = new Bilet(rodzaj, opis);
			biletM.dodajBilet(bilet);
			
			int n = biletM.pobierzBilety().size();
		
			Bilet otrzymanyBilet = biletM.pobierzBiletPoID(bilet);
			assertEquals(bilet.getId(), otrzymanyBilet.getId());
			assertEquals(rodzaj, otrzymanyBilet.getRodzaj());
			assertEquals(opis, otrzymanyBilet.getOpis());
			
			biletM.usunBilet(bilet);

			assertNull(biletM.pobierzBiletPoID(otrzymanyBilet));
			assertEquals(n-1, biletM.pobierzBilety().size());
			}
}
