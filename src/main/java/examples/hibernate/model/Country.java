package examples.hibernate.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Country {

	@Id
	@GeneratedValue
	private int countryId;
	
	private String countryName;
	
	@OneToMany(mappedBy="country", cascade=CascadeType.ALL)
	private Set<City> cities = new HashSet<City>();
	
	public Set<City> getCities() {
		return cities;
	}
	public void setCities(Set<City> cities) {
		this.cities = cities;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
}
