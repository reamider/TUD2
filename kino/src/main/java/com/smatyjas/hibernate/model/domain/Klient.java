package com.smatyjas.hibernate.model.domain;
import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name = "Klient.pobierzKlientow", query = "SELECT k FROM Klient k"),
  	@NamedQuery(name = "Klient.pobierzKlientPoNazwisko", query = "Select k from Klient k where k.nazwisko= :nazwisko")
})

public class Klient implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String imie;
	private String nazwisko;

	public Klient(){}
	
	public Klient(String imie, String nazwisko){
		this.imie = imie;
		this.nazwisko = nazwisko;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
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

  