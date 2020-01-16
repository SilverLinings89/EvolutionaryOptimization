package com.kraft.evolopti.test;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;
import com.kraft.evolopti.*;

class CheckMultiplicationExpression {

	@Test
	void testEvaluateForX() {
		Constant c1 = new Constant(3.0, 1);
		VariableValue v1 = new VariableValue(1);
		Multiplication test_Multiplication = new Multiplication(c1, v1, 2);
		assertEquals(test_Multiplication.evaluateForX(2), 6);
	}

	@Test
	void testIsConstant() {
		Constant c1 = new Constant(1.0, 1);
		VariableValue v1 = new VariableValue(1);
		Multiplication test_Multiplication1 = new Multiplication(c1, v1, 2);
		assertEquals(test_Multiplication1.isConstant(), false);
		Multiplication test_Multiplication2 = new Multiplication(c1, c1, 2);
		assertEquals(test_Multiplication2.isConstant(), true);
	}

	@Test
	void testCloneExpression() {
		Constant c1 = new Constant(1.0, 1);
		VariableValue v1 = new VariableValue(1);
		Multiplication test_Multiplication1 = new Multiplication(c1, v1, 4);
		Expression test_Multiplication2 = test_Multiplication1.cloneExpression();
		boolean has_mutated = false;
		while(!has_mutated) {
			has_mutated = test_Multiplication1.mutate();
		}
		assertNotEquals(test_Multiplication1.toString(), test_Multiplication2.toString());
	}

	@Test
	void testMutate() {
		Constant c1 = new Constant(1.0, 1);
		VariableValue v1 = new VariableValue(1);
		Multiplication test_Multiplication1 = new Multiplication(c1, v1, 4);
		String original_expression = test_Multiplication1.toString();
		boolean has_mutated = false;
		while(!has_mutated) {
			has_mutated = test_Multiplication1.mutate();
		}
		assertNotEquals(original_expression, test_Multiplication1.toString());
	}

	@Test
	void testMultiplicationInt() {
		Multiplication s1 = new Multiplication(3);
		assertNotEquals(s1.toString(), "");
	}

	@Test
	void testMultiplicationExpressionExpressionInt() {
		Constant c1 = new Constant(1.0, 1);
		VariableValue v1 = new VariableValue(1);
		Multiplication test_Multiplication1 = new Multiplication(c1, v1, 2);
		String temp = new Double(1.0).toString();
		assertEquals(test_Multiplication1.toString(), "("+temp + " * x)");
	}

	@Test
	void testToString() {
		Multiplication test_Multiplication1 = new Multiplication(2);
		assertNotEquals(test_Multiplication1.toString(), "");
	}

	@Test
	void testOptimizedSelf() {
		Random random = new Random();
		Constant c1 = new Constant(random.nextInt(), 1);
		Constant c2 = new Constant(random.nextInt(), 1);
		
		Multiplication test_Multiplication1 = new Multiplication(c1, c2, 2);
		Expression test_Multiplication2 = test_Multiplication1.optimizedSelf();
		assertEquals(test_Multiplication2.NumberOfChildExpressions, 0);
		assertEquals(test_Multiplication1.evaluateForX(0),test_Multiplication2.evaluateForX(0));
	}

}