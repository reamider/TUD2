package com.smatyjas.hibernate.model.service;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.smatyjas.hibernate.model.domain.Bilet;

@Component
@Transactional
public class BiletManagerImpl implements BiletManager {
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
	public List<Bilet> pobierzBilety(){
		return session.getCurrentSession().getNamedQuery("Bilet.pobierzBilety").list();
	}
	@Override
	public Bilet pobierzBiletPoID(Bilet bilet){
		return (Bilet) session.getCurrentSession().get(Bilet.class, bilet.getId());
	}
	
	@Override
	public void dodajBilet(Bilet bilet){
		bilet.setId(null);
		session.getCurrentSession().persist(bilet);
	}
	
	@Override
	public void edytujBilet(Bilet bilet){
		session.getCurrentSession().update(bilet);
	}
	
	@Override
	public void usunBilet(Bilet bilet){
		session.getCurrentSession().delete(bilet);
	}
	
}
