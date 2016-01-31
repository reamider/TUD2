package com.smatyjas.hibernate.model.service;
import java.util.List;
import com.smatyjas.hibernate.model.domain.Bilet;

public interface BiletManager {
	List<Bilet> pobierzBilety();
	Bilet pobierzBiletPoID(Bilet bilet);
	void dodajBilet(Bilet bilet);
	void edytujBilet(Bilet bilet);
	void usunBilet(Bilet bilet);
}
