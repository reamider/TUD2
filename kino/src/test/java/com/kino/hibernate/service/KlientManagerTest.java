package com.kino.hibernate.service;
import com.kino.hibernate.domain.Bilet;
import com.kino.hibernate.domain.Klient;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class KlientManagerTest{
	private final String imie = "Jan";
	private final String nazwisko = "Kowalski";
	private final String imie2 = "Adam";
	private final String nazwisko2 = "Malinowski";
	
	private final List<Long> dodaneBilety = new ArrayList<Long>();
	private final List<Long> dodaniKlienci = new ArrayList<Long>();
	
	@Autowired
	BiletManager biletM;
	
	@Autowired
	KlientManager klientM;
	
    @Before
    public void sprawdzDodane() {

        List<Bilet> bilety = biletM.pobierzBilety();
        List<Klient> klienci = klientM.pobierzKlientow();

        for(Bilet bilet : bilety)
            dodaneBilety.add(bilet.getidBilet());

        for(Klient klient : klienci)
            dodaniKlienci.add(klient.getidKlient());
    }
    
    @After
    public void usunWszystko() {

    	List<Bilet> bilety = biletM.pobierzBilety();
    	List<Klient> klienci = klientM.pobierzKlientow();
    	
        boolean usun;

        for(Bilet bilet : bilety) {
            usun = true;
            for (Long bilet2 : dodaneBilety)
                if (bilet.getidBilet() == bilet2) {
                usun = false;
                break;
                }
            if(usun)
                biletM.usunBilet(bilet);
        }
        
        for(Klient klient : klienci) {
            usun = true;
            for (Long klient2 : dodaniKlienci)
                if (klient.getidKlient() == klient2) {
                usun = false;
                break;
                }
            if(usun)
                klientM.usunKlient(klient);
        }
    }
	
	@Test
	public void sprawdzDodajKlient(){
		
		int n = klientM.pobierzKlientow().size();
		
		Klient klient = new Klient(imie, nazwisko);
		klientM.dodajKlient(klient);
		
		Klient otrzymanyKlient = klientM.pobierzKlientPoID(klient);
		assertEquals(klient.getidKlient(), otrzymanyKlient.getidKlient());
		assertEquals(imie, otrzymanyKlient.getImie());
		assertEquals(nazwisko, otrzymanyKlient.getNazwisko());
		
		assertEquals(n+1, klientM.pobierzKlientow().size());
	}
	
	@Test
	public void sprawdzEdytujKlient(){
		
		Klient klient = new Klient(imie, nazwisko);
		klientM.dodajKlient(klient);
		
		Klient otrzymanyKlient = klientM.pobierzKlientPoID(klient);
		assertEquals(klient.getidKlient(), otrzymanyKlient.getidKlient());
		assertEquals(imie, otrzymanyKlient.getImie());
		assertEquals(nazwisko, otrzymanyKlient.getNazwisko());
		
		otrzymanyKlient.setImie("Karol");
		otrzymanyKlient.setNazwisko("Karolak");
		klientM.edytujKlient(otrzymanyKlient);
		
		Klient otrzymanyKlient2 = klientM.pobierzKlientPoID(otrzymanyKlient);
		assertEquals(otrzymanyKlient.getidKlient(), otrzymanyKlient2.getidKlient());
		assertEquals("Karol", otrzymanyKlient2.getImie());
		assertEquals("Karolak", otrzymanyKlient2.getNazwisko());
	}
	
	@Test
	public void sprawdzUsunKlient(){
		
		Klient klient = new Klient(imie, nazwisko);
		klientM.dodajKlient(klient);
		
		int n = klientM.pobierzKlientow().size();
		
		Klient otrzymanyKlient = klientM.pobierzKlientPoID(klient);
		assertEquals(klient.getidKlient(), otrzymanyKlient.getidKlient());
		assertEquals(imie, otrzymanyKlient.getImie());
		assertEquals(nazwisko, otrzymanyKlient.getNazwisko());
		
		klientM.usunKlient(klient);

		assertNull(klientM.pobierzKlientPoID(otrzymanyKlient));
		assertEquals(n-1, klientM.pobierzKlientow().size());
	}
	
}
