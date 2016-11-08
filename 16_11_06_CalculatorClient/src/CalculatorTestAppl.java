import static tel_ran.calculator.api.UrlConstants.*;

import org.springframework.web.client.RestTemplate;

import tel_ran.calculator.api.CalculateRequest;

public class CalculatorTestAppl {
static final String URL = "http://localhost:8080/";
static RestTemplate restTemplate = new RestTemplate();
	public static void main(String[] args) {
		CalculateRequest request = new CalculateRequest(10.5, 20.3,"plus");
		double res = restTemplate.postForObject(URL+CALCULATE, request, Double.class);
		System.out.println(res);
		
		request = new CalculateRequest(10.5, 20.3,"-");
		res = restTemplate.postForObject(URL+CALCULATE, request, Double.class);
		System.out.println(res);
		
		request = new CalculateRequest(10.5, 20.3,"/");
		res = restTemplate.postForObject(URL+CALCULATE, request, Double.class);
		System.out.println(res);
		
		request = new CalculateRequest(10.5, 20.3,"*");
		res = restTemplate.postForObject(URL+CALCULATE, request, Double.class);
		System.out.println(res);
	}

}
