package com.kraft.evolopti.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import com.kraft.evolopti.*;

class CheckConstantExpression {

	@Test
	@DisplayName("Testing properties of constant functions.")
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
