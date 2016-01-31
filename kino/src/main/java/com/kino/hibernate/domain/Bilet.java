package com.kino.hibernate.domain;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
	@NamedQuery(name = "bilet.pobierzBilety", query = "Select b from Bilet b")
})
	
public class Bilet {
    private Long idBilet;
    private String rodzaj;
    private String opis;
    private double cena;

    private List<Klient> klienci = new ArrayList<Klient>();
    
	public Bilet(){}
	
	public Bilet(String rodzaj, String opis, double cena){
		this.rodzaj = rodzaj;
		this.opis = opis;
		this.cena = cena;
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getidBilet(){
        return idBilet;
    }
    public void setidBilet(Long idBilet){
        this.idBilet = idBilet;
    }

    public String getOpis(){
        return opis;
    }
    public void setOpis(String opis){
        this.opis = opis;
    }

    public String getRodzaj(){
        return rodzaj;
    }
    public void setRodzaj(String rodzaj){
        this.rodzaj = rodzaj;
    }

    public double getCena(){
        return cena;
    }
    public void setCena(double cena){
        this.cena = cena;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Klient> getKlients(){
        return klienci;
    }
    public void setKlients(List<Klient> klienci){
        this.klienci = klienci;
    }
}
