package com.kino.hibernate.domain;
import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name = "klient.pobierzKlientow", query = "Select k from Klient k"),
	@NamedQuery(name = "klient.pobierzKlientPoImie", query = "Select k from Klient k where k.imie= :imie")
})

public class Klient {
    private Long idKlient;
    private String imie;
    private String nazwisko;
    
	public Klient(){}
	
	public Klient(String imie, String nazwisko){
		this.imie = imie;
		this.nazwisko = nazwisko;
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getidKlient() {
        return idKlient;
    }
    public void setidKlient(Long idKlient) {
        this.idKlient = idKlient;
    }

    public String getImie() {
        return imie;
    }
    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
}
