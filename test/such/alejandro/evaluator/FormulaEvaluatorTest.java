package such.alejandro.evaluator;

import junit.framework.TestCase;

public class FormulaEvaluatorTest extends TestCase {
	public void testSimpleOperationOk(){
		try {
			FormulaEvaluator fe = new FormulaEvaluator("2+2");
			Double actual = fe.evaluate();
			Double expected = new Double("4");
			
			assertEquals(expected, actual);
		} catch (Exception e) {
			fail("Shouldn't fail");
		}
	}
	
	public void testSimpleOperationOk2(){
		try {
			FormulaEvaluator fe = new FormulaEvaluator("2+(2)");
			Double actual = fe.evaluate();
			Double expected = new Double("4");
			
			assertEquals(expected, actual);
		} catch (Exception e) {
			fail("Shouldn't fail");
		}
	}
	
	public void testSimpleOperationOk3(){
		try {
			FormulaEvaluator fe = new FormulaEvaluator("2^2+1");
			Double actual = fe.evaluate();
			Double expected = new Double("5");
			assertEquals(expected, actual);
			
			fe = new FormulaEvaluator("1+2^2");
			actual = fe.evaluate();
			assertEquals(expected, actual);
		} catch (Exception e) {
			fail("Shouldn't fail");
		}
	}
	
	public void testSimpleOperationKo1(){
		try {
			FormulaEvaluator fe = new FormulaEvaluator("2+()");
			fe.evaluate();
			
			fail("Should fail");
		} catch (Exception e) { }
	}
	
	public void testSimpleOperationKo2(){
		try {
			FormulaEvaluator fe = new FormulaEvaluator("2+()+1");
			fe.evaluate();
			
			fail("Should fail");
		} catch (Exception e) { }
	}
	
	public void testSimpleOperationKo3(){
		try {
			FormulaEvaluator fe = new FormulaEvaluator("2+()1");
			fe.evaluate();
			
			fail("Should fail");
		} catch (Exception e) { }
	}
	
	public void testSimpleOperationKo4(){
		try {
			FormulaEvaluator fe = new FormulaEvaluator("2++1");
			fe.evaluate();
			
			fail("Should fail");
		} catch (Exception e) { }
	}
	
	public void testSimpleOperationKo5(){
		try {
			FormulaEvaluator fe = new FormulaEvaluator("2+1++3");
			fe.evaluate();
			
			fail("Should fail");
		} catch (Exception e) { }
	}
	
	public void testSimpleOperationOkWithParenthesis(){
		try {
			FormulaEvaluator fe = new FormulaEvaluator("(2+2)");
			Double actual = fe.evaluate();
			Double expected = new Double("4");
			
			assertEquals(expected, actual);
		} catch (Exception e) {
			fail("Shouldn't fail");
		}
	}
	
	public void testSimpleOperationKoWithParenthesis(){
		try {
			FormulaEvaluator fe = new FormulaEvaluator("2+2)");
			fe.evaluate();
			
			fail("Should fail");
		} catch (Exception e) { }
	}
	
	public void testSimpleOperationKoWithParenthesis2(){
		try {
			FormulaEvaluator fe = new FormulaEvaluator("(2+2");
			fe.evaluate();
			
			fail("Should fail");
		} catch (Exception e) { }
	}
	
	public void testComplexOperationOk1(){
		try {
			FormulaEvaluator fe = new FormulaEvaluator("2+2*(3+2*3+1)-SQRT4");
			Double actual = fe.evaluate();
			Double expected = new Double("20");
			
			assertEquals(expected, actual);
		} catch (Exception e) {
			fail("Shouldn't fail");
		}
	}
	
	public void testComplexOperationOk2(){
		try {
			FormulaEvaluator fe = new FormulaEvaluator("2+2*(3+2*3+1)-SQRT(4)");
			Double actual = fe.evaluate();
			Double expected = new Double("20");
			
			assertEquals(expected, actual);
		} catch (Exception e) {
			fail("Shouldn't fail");
		}
	}
	
	public void testComplexOperationOk3(){
		try {
			FormulaEvaluator fe = new FormulaEvaluator("2+2*(3+2*3+1)-SQRT(4*2+1)");
			Double actual = fe.evaluate();
			Double expected = new Double("19");
			
			assertEquals(expected, actual);
		} catch (Exception e) {
			fail("Shouldn't fail");
		}
	}
	
	public void testFormulaOk(){
		try {
			FormulaEvaluator fe = new FormulaEvaluator("(BASE*ALTURA)/2");
			fe.getParameters().add(new Parameter("BASE", "6"));
			fe.getParameters().add(new Parameter("ALTURA", "4"));
			Double actual = fe.evaluate();
			Double expected = new Double("12");
			
			assertEquals(expected, actual);
		} catch (Exception e) {
			fail("Shouldn't fail");
		}
	}
	
	public void testFormulaKo(){
		try {
			FormulaEvaluator fe = new FormulaEvaluator("(BASE*ALTURA)/2");
			fe.getParameters().add(new Parameter("BASE", "6"));
			fe.evaluate();
			fail("Should fail");
		} catch (Exception e) { }
	}
}
