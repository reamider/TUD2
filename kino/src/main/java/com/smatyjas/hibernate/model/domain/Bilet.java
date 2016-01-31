package com.smatyjas.hibernate.model.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
	@NamedQuery(name = "Bilet.pobierzBilety", query = "SELECT b FROM Bilet b"),
})

public class Bilet{
    private Long id;
    private String rodzaj;
    private String opis;
    private List<Klient> klienci = new ArrayList<Klient>();
    
    
	public Bilet(){}
	
	public Bilet(String rodzaj, String opis){
		this.rodzaj = rodzaj;
		this.opis = opis;
	}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Klient> getKlienci() {
        return klienci;
    }
  
    public void setKlienci(List<Klient> klienci) {
        this.klienci = klienci;
    }

    public String getRodzaj() {
        return rodzaj;
    }
  
    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String getOpis() {
        return opis;
    }
    public void setOpis(String opis) {
        this.opis = opis;
    }
}