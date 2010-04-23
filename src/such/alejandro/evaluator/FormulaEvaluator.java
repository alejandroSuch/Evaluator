package such.alejandro.evaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class FormulaEvaluator {
	private static final String TOKENS = "()^@/*-+";
	private Stack<Token>values = new Stack<Token>();
	private Stack<Token>operations = new Stack<Token>();
	private StringTokenizer tokenizer;
	private String expression;
	private Token lastToken;
	private List<Parameter>parameters = new ArrayList<Parameter>();
	
	public FormulaEvaluator(String _expression) throws Exception{
		int oPars = _expression.replaceAll("\\(","").length();
		int cPars = _expression.replaceAll("\\)","").length();
		
		if(oPars != cPars){
			throw new Exception("Parenthesis count mismatch");
		}
		
		this.expression = _expression;
	}
	
	public Double evaluate() throws Exception{
		init();
		
		while(tokenizer.hasMoreTokens()){
			String nextToken = tokenizer.nextToken();
			Token token = new Token(nextToken);
			if(OperationType.VAL.equals(token.getType())){
				if(lastToken != null && OperationType.VAL.equals(lastToken.getType())){
					throw new Exception("This element cannot be the same kind as the previous");
				}
				values.push(token);
			} else {
				evaluateNonPermitedCombinations(token);
				doOperationStuff(token);
			}
			lastToken = token;
		}
		
		finish();
		
		return Double.parseDouble(values.pop().getValue());
	}

	private void init() {
		for (Parameter parameter : parameters) {
			expression = expression.replaceAll(parameter.getName(), parameter.getValue().toString());
		}
		expression = expression.replaceAll("SQRT", "@");
		tokenizer = new StringTokenizer(expression, TOKENS, true);
	}

	private void evaluateNonPermitedCombinations(Token token) throws Exception {
		if(lastToken == null && !OperationType.OPAR.equals(token.getType())) {
//			Can only begin with a parenthesis
			throw new Exception("First element cannot be an operation");
		} else if(lastToken != null && !OperationType.VAL.equals(lastToken.getType())){ //Two consecutive operation symbols
			if(OperationType.OPAR.equals(token.getType()) && OperationType.CPAR.equals(lastToken.getType())){
//				BAD: )(
				throw new Exception("Formula mal construida");
			} else if(OperationType.CPAR.equals(token.getType()) && !OperationType.CPAR.equals(lastToken.getType())){
//				BAD: +)
				throw new Exception("Formula mal construida");
			} 
		}
	}

	private void doOperationStuff(Token operationToken) throws Exception {
		Token top = operations.isEmpty()?null:operations.peek();
		
		if(top != null){
			if(OperationType.CPAR.equals(operationToken.getType())){
				evaluateParenthesis(top);
			} else if(!operationToken.isPrecedentOn(top) && !OperationType.OPAR.equals(operationToken.getType())){
				String res = Operation.execute(values, operations.pop().getType());
				Token token = new Token(res);
				values.push(token);
				lastToken = token;
				operations.push(operationToken);
			} else {
				operations.push(operationToken);
			}
		} else {
			operations.push(operationToken);
		}
	}

	private void evaluateParenthesis(Token top) throws Exception {
		if(OperationType.OPAR.equals(top.getType())){
			top = operations.isEmpty()?null:operations.pop();
			return;
		}
		top = operations.isEmpty()?null:operations.pop();
		do {
			String res = Operation.execute(values, top.getType());
			if(res != null) {
				Token token = new Token(res);
				values.push(token);
				lastToken = token;
			}
			top = operations.isEmpty()?null:operations.pop();
		} while(!OperationType.OPAR.equals(top.getType()));
	}

	private void finish() throws Exception {
		while(!operations.isEmpty()){
			String res = Operation.execute(values, operations.pop().getType());
			Token token = new Token(res);
			values.push(token);
			lastToken = token;
			
		}
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
}
