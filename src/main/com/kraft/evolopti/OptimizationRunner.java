package com.kraft.evolopti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.util.Pair;

public class OptimizationRunner {
	List<Pair<Double, Double>> EvaluationPoints;
	OptimizationCase optimizationCase;
	
	public static void main(String[] args) throws Exception {
		OptimizationRunner runner = new OptimizationRunner();
		// TODO add load from file functionality here.
		runner.generateEvaluationPoints();
		runner.run();		
	}
	
	public void setEvaluationPoints(List<Pair<Double, Double>> inEvaluationPoints) {
		this.EvaluationPoints = inEvaluationPoints;
	}
	
	List<Pair<Double, Double>> generateEvaluationPoints() {
		EvaluationPoints = new ArrayList<Pair<Double, Double>>();
		EvaluationPoints.add(new Pair<Double, Double>(0.0,0.5));
		EvaluationPoints.add(new Pair<Double, Double>(1.0,1.5));
		EvaluationPoints.add(new Pair<Double, Double>(2.0,4.5));
		return EvaluationPoints;
	}
	
	public void run() throws Exception {
		optimizationCase = new OptimizationCase(1000, 200, 30, 300, 5, 10000, 10, EvaluationPoints, 0.0001);
		optimizationCase.run();
	}
	
}
