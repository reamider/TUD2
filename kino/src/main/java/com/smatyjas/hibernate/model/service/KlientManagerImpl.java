package com.smatyjas.hibernate.model.service;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.smatyjas.hibernate.model.domain.Klient;
import com.smatyjas.hibernate.model.domain.Bilet;

@Component
@Transactional
public class KlientManagerImpl implements KlientManager {
	
	@Autowired
	private SessionFactory session;
	
	public SessionFactory getSessionFactory(){
		return session;
	}
	
	public void setSessionFactory(SessionFactory session){
		this.session = session;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Klient> pobierzKlientow() {
		return session.getCurrentSession().getNamedQuery("Klient.pobierzKlientow").list();
	}

	@Override
	public Klient pobierzKlientPoID(Klient klient) {
		return (Klient) session.getCurrentSession().get(Klient.class, klient.getId());
	}
	
	@Override
	public List<Klient> pobierzKlientPoBilet(Bilet bilet) {
		Bilet b = (Bilet) session.getCurrentSession().get(Bilet.class, bilet.getId());
		return b.getKlienci();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Klient> pobierzKlientPoNazwisko(String nazwisko) {
		return session.getCurrentSession().getNamedQuery("Klient.pobierzKlientPoNazwisko").setString("nazwisko", nazwisko).list();
	}

	@Override
	public void dodajKlient(Klient klient) {
		klient.setId(null);
		session.getCurrentSession().persist(klient);	
	}
	
	@Override
	public void dodajKlientaDoBiletu(Klient klient, Bilet bilet) {
		Bilet b = (Bilet) session.getCurrentSession().get(Bilet.class, bilet.getId());
		b.getKlienci().add(klient);
	}
	
	@Override
	public void edytujKlient(Klient klient) {
		session.getCurrentSession().update(klient);
	}

	@Override
	public void usunKlient(Klient klient) {
		session.getCurrentSession().delete(klient);	
	}

}
