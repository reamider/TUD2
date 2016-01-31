package com.kino.hibernate.service;
import com.kino.hibernate.domain.Bilet;
import com.kino.hibernate.domain.Klient;
import java.util.List;

public interface KlientDAO {
	List<Klient> pobierzKlientow();
	Klient pobierzKlientPoID(Klient klient);
	List<Klient> pobierzKlientPoImie(String imie);
	void dodajKlient(Klient klient);
	void dodajKlientDoBilet(Klient klient, Bilet bilet);
	void edytujKlient(Klient klient);
	void usunKlient(Klient klient);
}
