package com.kino.hibernate.service;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.kino.hibernate.domain.Bilet;
import com.kino.hibernate.domain.Klient;

@Component
@Transactional
public class KlientManager implements KlientDAO{
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
		return (Klient) session.getCurrentSession().get(Klient.class, klient.getidKlient());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Klient> pobierzKlientPoImie(String imie) {
		return session.getCurrentSession().getNamedQuery("Klient.pobierzKlientPoImie").setString("imie", imie).list();
	}
	
	@Override
	public void dodajKlient(Klient klient) {
		klient.setidKlient(null);
		session.getCurrentSession().persist(klient);	
	}

	@Override
	public void dodajKlientDoBilet(Klient klient, Bilet bilet) {
		Bilet b = (Bilet) session.getCurrentSession().get(Bilet.class, bilet.getidBilet());
		b.getKlients().add(klient);
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