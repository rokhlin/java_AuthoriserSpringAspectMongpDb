import java.util.Date;
import java.util.Map;

public class Currency {
	String base;
	Date date;
	Map<String,Double> rates;
	
//	public Currency() {
//	}
//	
//	public Currency(String base, Date date, Map<String, Double> rates) {
//		super();
//		this.base = base;
//		this.date = date;
//		this.rates = rates;
//	}
	
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Map<String, Double> getRates() {
		return rates;
	}
	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}
	@Override
	public String toString() {
		return "Currency [base=" + base + ", date=" + date + ", rates=" + rates + "]";
	}
	
	

}
