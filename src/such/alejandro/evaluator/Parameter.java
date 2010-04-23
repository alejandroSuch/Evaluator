package such.alejandro.evaluator;

public class Parameter {
	private String name;
	private Double value;
	
	public Parameter(String name, Double value){
		this.name = name;
		this.value = value;
	}
	
	public Parameter(String name, String value){
		this.name = name;
		this.value = new Double(value);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public void setValue(String value){
		this.value = new Double(value);
	}
}
