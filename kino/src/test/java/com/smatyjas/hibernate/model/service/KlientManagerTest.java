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
import com.smatyjas.hibernate.model.domain.Klient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class KlientManagerTest {
	private final String imie = "Jan";
	private final String nazwisko = "Kowalski";
	private final String imie2 = "Adam";
	private final String nazwisko2 = "Adamiak";
	private final String imie3 = "Karol";
	private final String nazwisko3 = "Karolak";
	private final String rodzaj = "Ulgowy";
	private final String opis = "Bilet ulgowy";
	
	
	private final List<Long> dodaneBilety = new ArrayList<Long>();
	private final List<Long> dodaniKlienci = new ArrayList<Long>();
	
	@Autowired
	BiletManager biletM;
	
	@Autowired
	KlientManager klientM;
	
	 @Before
	    public void checkAdded() {

	        List<Bilet> bilety = biletM.pobierzBilety();
	        List<Klient> klienci = klientM.pobierzKlientow();

	        for(Bilet bilet : bilety)
	            dodaneBilety.add(bilet.getId());

	        for(Klient klient : klienci)
	            dodaniKlienci.add(klient.getId());
	    }
	
	 @After
	    public void removeAll() {

	    	List<Bilet> bilety = biletM.pobierzBilety();
	    	List<Klient> klienci = klientM.pobierzKlientow();
	    	
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
	        
	        for(Klient klient : klienci) {
	            usun = true;
	            for (Long klient2 : dodaniKlienci)
	                if (klient.getId() == klient2) {
	                usun = false;
	                break;
	                }
	            if(usun)
	            	klientM.usunKlient(klient);
	        }
	    }
	 
	 @Test
		public void checkAddKlient(){
			
			int n = klientM.pobierzKlientow().size();
			
			Klient klient = new Klient(imie, nazwisko);
			klientM.dodajKlient(klient);
			
			Klient otrzymanyKlient = klientM.pobierzKlientPoID(klient);
			assertEquals(klient.getId(), otrzymanyKlient.getId());
			assertEquals(imie, otrzymanyKlient.getImie());
			assertEquals(nazwisko, otrzymanyKlient.getNazwisko());

			assertEquals(n+1, klientM.pobierzKlientow().size());
	}
		
	@Test
	public void checkEditKlient(){
		
		Klient klient = new Klient(imie, nazwisko);
		klientM.dodajKlient(klient);
		
		Klient otrzymanyKlient = klientM.pobierzKlientPoID(klient);
		assertEquals(klient.getId(), otrzymanyKlient.getId());
		assertEquals(imie, otrzymanyKlient.getImie());
		assertEquals(nazwisko, otrzymanyKlient.getNazwisko());
		
		otrzymanyKlient.setImie("Karol");
		otrzymanyKlient.setNazwisko("Karolak");
		klientM.edytujKlient(otrzymanyKlient);
		
		Klient otrzymanyKlient2 = klientM.pobierzKlientPoID(otrzymanyKlient);
		assertEquals(otrzymanyKlient.getId(), otrzymanyKlient2.getId());
		assertEquals("Karol", otrzymanyKlient2.getImie());
		assertEquals("Karolak", otrzymanyKlient2.getNazwisko());
	}
	
	@Test
	public void checkDeleteKlient(){
		
		Klient klient = new Klient(imie, nazwisko);
		klientM.dodajKlient(klient);
		
		int n = klientM.pobierzKlientow().size();
		
		Klient otrzymanyKlient = klientM.pobierzKlientPoID(klient);
		assertEquals(klient.getId(), otrzymanyKlient.getId());
		assertEquals(imie, otrzymanyKlient.getImie());
		assertEquals(nazwisko, otrzymanyKlient.getNazwisko());
		
		klientM.usunKlient(klient);

		assertNull(klientM.pobierzKlientPoID(otrzymanyKlient));
		assertEquals(n-1, klientM.pobierzKlientow().size());
	}
}