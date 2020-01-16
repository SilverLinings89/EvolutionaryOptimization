

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

class CheckSumExpression {

	@Test
	void testEvaluateForX() {
		Constant c1 = new Constant(1.0, 1);
		VariableValue v1 = new VariableValue(1);
		Sum test_sum = new Sum(c1, v1, 2);
		assertEquals(test_sum.evaluateForX(1), 2);
	}

	@Test
	void testIsConstant() {
		Constant c1 = new Constant(1.0, 1);
		VariableValue v1 = new VariableValue(1);
		Sum test_sum1 = new Sum(c1, v1, 2);
		assertEquals(test_sum1.isConstant(), false);
		Sum test_sum2 = new Sum(c1, c1, 2);
		assertEquals(test_sum2.isConstant(), true);
	}

	@Test
	void testCloneExpression() {
		Constant c1 = new Constant(1.0, 1);
		VariableValue v1 = new VariableValue(1);
		Sum test_sum1 = new Sum(c1, v1, 4);
		Expression test_sum2 = test_sum1.cloneExpression();
		boolean has_mutated = false;
		while(!has_mutated) {
			has_mutated = test_sum1.mutate();
		}
		assertNotEquals(test_sum1.toString(), test_sum2.toString());
	}

	@Test
	void testMutate() {
		Constant c1 = new Constant(1.0, 1);
		VariableValue v1 = new VariableValue(1);
		Sum test_sum1 = new Sum(c1, v1, 4);
		String original_expression = test_sum1.toString();
		boolean has_mutated = false;
		while(!has_mutated) {
			has_mutated = test_sum1.mutate();
		}
		System.out.println(original_expression);
		System.out.println(test_sum1.toString());
		assertNotEquals(original_expression, test_sum1.toString());
	}

	@Test
	void testSumInt() {
		Sum s1 = new Sum(3);
		assertNotEquals(s1.toString(), "");
	}

	@Test
	void testSumExpressionExpressionInt() {
		Constant c1 = new Constant(1.0, 1);
		VariableValue v1 = new VariableValue(1);
		Sum test_sum1 = new Sum(c1, v1, 2);
		String temp = new Double(1.0).toString();
		assertEquals(test_sum1.toString(), "("+temp + " + x)");
	}

	@Test
	void testToString() {
		Sum test_sum1 = new Sum(2);
		assertNotEquals(test_sum1.toString(), "");
	}

	@Test
	void testOptimizedSelf() {
		Random random = new Random();
		Constant c1 = new Constant(random.nextInt(), 1);
		Constant c2 = new Constant(random.nextInt(), 1);
		
		Sum test_sum1 = new Sum(c1, c2, 2);
		Expression test_sum2 = test_sum1.optimizedSelf();
		assertEquals(test_sum2.NumberOfChildExpressions, 0);
		assertEquals(test_sum1.evaluateForX(0),test_sum2.evaluateForX(0));
	}

}
