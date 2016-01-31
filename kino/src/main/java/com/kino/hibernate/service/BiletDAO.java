package com.kino.hibernate.service;
import com.kino.hibernate.domain.Bilet;
import java.util.List;

public interface BiletDAO{
	List<Bilet> pobierzBilety();
	Bilet pobierzBiletPoID(Bilet bilet);
	void dodajBilet(Bilet bilet);
	void edytujBilet(Bilet bilet);
	void usunBilet(Bilet bilet);
}