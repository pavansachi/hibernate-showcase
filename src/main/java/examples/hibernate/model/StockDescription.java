package examples.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class StockDescription {

	@GenericGenerator(name="gen", strategy="foreign", parameters={@Parameter(name="property", value="stock")})
	@GeneratedValue(generator="gen")
	@Id
	private int stockId;
	private String description;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Stock stock;
	
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
}
