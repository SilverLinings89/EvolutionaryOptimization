package com.kraft.evolopti.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.kraft.evolopti.OptimizationRunner;

import javafx.util.Pair;

class CheckOptimizationRunner {

	@Test
	void passWrongEvaluationPoints() {
		boolean errorWasThrown = false;
		OptimizationRunner or = new OptimizationRunner();
		
		List<Pair<Double, Double>> EvaluationPoints = new ArrayList<Pair<Double, Double>>();
		EvaluationPoints.add(new Pair<Double, Double>(0.0,0.5));
		EvaluationPoints.add(new Pair<Double, Double>(0.0,1.5));
		EvaluationPoints.add(new Pair<Double, Double>(2.0,4.5));
		or.setEvaluationPoints(EvaluationPoints);
		try {
			or.run();
		} catch(Exception e) {
			errorWasThrown = true;
		}
		assertTrue(errorWasThrown);
	}

}
