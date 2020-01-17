package com.kraft.evolopti;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class OptimizationRunner {
	List<Point2D.Double> EvaluationPoints;
	OptimizationCase optimizationCase;
	
	public static void main(String[] args) throws Exception {
		OptimizationRunner runner = new OptimizationRunner();
		// TODO add load from file functionality here.
		runner.generateEvaluationPoints();
		runner.run();		
	}
	
	public void setEvaluationPoints(List<Point2D.Double> inEvaluationPoints) {
		this.EvaluationPoints = inEvaluationPoints;
	}
	
	List<Point2D.Double> generateEvaluationPoints() {
		EvaluationPoints = new ArrayList<Point2D.Double>();
		EvaluationPoints.add(new Point2D.Double(0.0,0.5));
		EvaluationPoints.add(new Point2D.Double(1.0,1.5));
		EvaluationPoints.add(new Point2D.Double(2.0,4.5));
		return EvaluationPoints;
	}
	
	public void run() throws Exception {
		optimizationCase = new OptimizationCase(1000, 200, 30, 300, 5, 10000, 10, EvaluationPoints, 0.0001);
		optimizationCase.run();
	}
	
}
