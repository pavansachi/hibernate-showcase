package examples.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Stock {

	@Id
	@GeneratedValue
	private int stockId;
	private String stockName;
	
	@OneToOne(mappedBy="stock" , cascade=CascadeType.ALL)
	private StockDescription stockDescription;
	
	public StockDescription getStockDescription() {
		return stockDescription;
	}
	public void setStockDescription(StockDescription stockDescription) {
		this.stockDescription = stockDescription;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
}
