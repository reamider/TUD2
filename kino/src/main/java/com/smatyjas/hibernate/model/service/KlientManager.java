package com.smatyjas.hibernate.model.service;
import java.util.List;
import com.smatyjas.hibernate.model.domain.Klient;
import com.smatyjas.hibernate.model.domain.Bilet;

public interface KlientManager {
	List<Klient> pobierzKlientow();
	Klient pobierzKlientPoID(Klient klient);
	List <Klient> pobierzKlientPoBilet(Bilet bilet);
	List<Klient> pobierzKlientPoNazwisko(String imie);
	void dodajKlient(Klient klient);
	void dodajKlientaDoBiletu(Klient klient, Bilet bilet);
	void edytujKlient(Klient klient);
	void usunKlient(Klient klient);
}
