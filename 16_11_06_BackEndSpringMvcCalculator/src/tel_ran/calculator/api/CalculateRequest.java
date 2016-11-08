package tel_ran.calculator.api;

public class CalculateRequest {
	private double op1;
	private double op2;
	private String operation;
	
	public CalculateRequest() {
	}
	public CalculateRequest(double op1, double op2, String operation) {
		super();
		this.op1 = op1;
		this.op2 = op2;
		this.operation = operation;
	}
	public double getOp1() {
		return op1;
	}
	public void setOp1(double op1) {
		this.op1 = op1;
	}
	public double getOp2() {
		return op2;
	}
	public void setOp2(double op2) {
		this.op2 = op2;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	

}
