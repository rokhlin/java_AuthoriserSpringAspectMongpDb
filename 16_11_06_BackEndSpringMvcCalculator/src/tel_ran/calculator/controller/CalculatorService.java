package tel_ran.calculator.controller;

import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static tel_ran.calculator.api.UrlConstants.*;

import tel_ran.calculator.api.CalculateRequest;
import tel_ran.calculator.model.CalculatorModel;

@RestController  
@SpringBootApplication
public class CalculatorService {
	public static CalculatorModel calculatorModel = new CalculatorModel();
	 
	@RequestMapping(value=OPERATIONS)
	public Set<String> getOperations(){
		return calculatorModel.getOperations();	
	};
	
	@RequestMapping(value=GET_CALCULATE)
	public double calculate(double op1,double op2,String operation) {
		return calculatorModel.calculate(op1,op2,operation);
	} 
	
	@RequestMapping(value=CALCULATE, method = RequestMethod.POST)
	public double calculate(@RequestBody CalculateRequest request) {//
		return calculatorModel.calculate(request.getOp1(), request.getOp2(), request.getOperation());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CalculatorService.class, args);
	}
}
