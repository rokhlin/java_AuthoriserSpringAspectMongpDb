import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestTemplateTestAppl {
	static RestTemplate restTemplate = new RestTemplate();
	static String url = "http://api.fixer.io/latest";
	static ObjectMapper mapper = new ObjectMapper();
	static Currency c;
	public static void main(String[] args) {
		// 1 - Getting json to String
//		String json = restTemplate.getForObject(url, String.class);
//		System.out.println(json +"\n" );
		
//		// 2 - Getting to map
//		Map<String,Object> map = restTemplate.getForObject(url, Map.class);
//		System.out.println(map);
		
//		// 3 - Using TypeReference import com.fasterxml.jackson.core.type.TypeReference;!!
//		Map<String,Object> map = restTemplate.getForObject(url, new TypeReference<Map<String,Object>>(){});
//		System.out.println(map);
		
//		// 4 - Getting to specify object Currency.class
//		Currency c = restTemplate.getForObject(url, Currency.class);
//		System.out.println("RUB: "+c.rates.get("RUB"));
//		System.out.println("DATE: "+c.getDate());
//		System.out.println("\nOBJECT: "+c);
		
//		// 5 - Getting to parameterized Map using ParameterizedTypeReference
//		HttpEntity<Map<String,Object>> map = restTemplate.exchange(url,
//											HttpMethod.GET,
//											null,
//											new ParameterizedTypeReference<Map<String,Object>>() {});
		
		try(BufferedReader console = new BufferedReader(new InputStreamReader(System.in))){
			c = restTemplate.getForObject(url, Currency.class);
			c.rates.put("EUR", 1.0);
			
			while(true) {
				System.out.println("\n1-\"currency [NAME]\" \n2-\"convert [value] [Curency From] [Curency To]\"\n3-\"exit\"\nEnter command:");
				String line = console.readLine();
				
				if(line == null || line.equalsIgnoreCase("exit"))
					break;
				
				String[] operands = line.toUpperCase().trim().split(" ");
				String res = null;
				
				if(operands[0].equals("CURRENCY"))
					res = operands[1]+ ":" + getCurrency(operands[1]);
				if(operands[0].equals("CONVERT"))
					res = convertCurrency(operands);
				print(res);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void print(String res) {
		System.out.println(res+"\n"+"Currency updated: "+c.date);
	}

	private static String convertCurrency(String[] operands) {
		String res = "-";
		double curFrom = c.rates.get(operands[2]);
		double curTo = c.rates.get(operands[3]);
		double result = curTo/(Double.parseDouble(operands[1])*curFrom);

		String resultParsed = new DecimalFormat("##0.00").format(result);
		
		res="Convert "+operands[1]+"-" + operands[2]+" to "+operands[3]+" = "+resultParsed+operands[3];
		
		return res;
	}

	private static double getCurrency(String rate) {
		return c.rates.get(rate);
	}
	
}
