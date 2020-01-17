package com.kraft.evolopti.test;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;
import com.kraft.evolopti.*;
import com.kraft.evolopti.*;

class CheckExpressionGenerator {

	@Test
	void GenerateRandomExpression() {
		Expression ex = ExpressionGenerator.Generate(5);
		assertNotEquals(ex.toString(), "");
	}
	
	@Test
	void GenerateRandomInitialExpression() {
		Expression ex = ExpressionGenerator.GenerateInitial(5);
		assertNotEquals(ex.toString(), "");
		boolean hasChildren = ex.NumberOfChildExpressions > 0;
		assertEquals(hasChildren, true);
	}
	
	
	@Test
	void GenerateRandomInitialExpressionError() {
		Expression ex = ExpressionGenerator.GenerateInitial(-5);
		assertNotEquals(ex.toString(), "");
		assertEquals(ex.NumberOfChildExpressions, 0);
	}

}