import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CheckConstantExpression {

	@Test
	void test() {
		Constant expression = new Constant(1.0,1);
		Expression clone = expression.cloneExpression();
		
		assertEquals(expression.evaluateForX(0), 1.0);
		
		String temp = new Double(1.0).toString();
		assertEquals(expression.toString(), temp);
		
		boolean has_mutated = false;
		while(!has_mutated) {
			has_mutated = expression.mutate();
		}
		
		assertNotEquals(expression.evaluateForX(0), 1.0);
		
		assertEquals(clone.evaluateForX(0),1.0);
		
		assertEquals(expression.isConstant(), true);
		
	}

}
