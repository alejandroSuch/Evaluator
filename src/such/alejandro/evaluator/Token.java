package such.alejandro.evaluator;

public class Token {
	private String value = null;
	private OperationType type = null;
	private static OperationType[] precedences = {
		OperationType.CPAR, OperationType.POW, OperationType.SQRT, OperationType.DIV, 
		OperationType.MULT, OperationType.SUB, OperationType.ADD, OperationType.OPAR, 
		OperationType.VAL };
	private static String[] tokens = {")", "^", "@", "/", "*", "-", "+", "("};
	
	public Token(String valor){
		this.type = OperationType.VAL;
		
		for (int i = 0; i < tokens.length; i++) {
			if(tokens[i].equals(valor)){
				this.type = precedences[i];
			}
		}
		
		if(this.type.equals(OperationType.VAL)){
			this.value = valor;
		}
	}

	public String getValue() {
		return value;
	}

	public OperationType getType() {
		return type;
	}
	
	public boolean isPrecedentOn(Token token){
		int myPos = 0;
		int tokenPos = 0;
		for (int i = 0; i < precedences.length; i++) {
			if(this.getType().equals(precedences[i])){
				myPos = i;
			}
			
			if(token.getType().equals(precedences[i])){
				tokenPos = i;
			}
		}
		
		return myPos < tokenPos;
	}
}
