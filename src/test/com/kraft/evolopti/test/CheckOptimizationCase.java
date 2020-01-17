package com.kraft.evolopti.test;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.kraft.evolopti.OptimizationRunner;

import javafx.util.Pair;

class CheckOptimizationCase {

	@Test
	void compareExpressions() {
		OptimizationRunner or = new OptimizationRunner();
		boolean errorWasThrown = false;
		List<Pair<Double, Double>> EvaluationPoints = new ArrayList<Pair<Double, Double>>();
		EvaluationPoints.add(new Pair<Double, Double>(0.0,0.0));
		EvaluationPoints.add(new Pair<Double, Double>(1.0,1.0));
		EvaluationPoints.add(new Pair<Double, Double>(2.0,4.0));
		or.setEvaluationPoints(EvaluationPoints);
		try {
			or.run();
		} catch(Exception e) {
			errorWasThrown = true;
		}
		assertFalse(errorWasThrown);
	}

}
