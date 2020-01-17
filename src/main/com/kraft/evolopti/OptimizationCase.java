package com.kraft.evolopti;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptimizationCase {
	
	int expressionsAtStart;
	int maxSteps;
	int maxDepth;
	int numberOfEvaluationPoints;
	int childrenPerSurvivor;
	int unmutatedInNextGeneration;
	int survivorsPerGeneration;
	int newExpressionsPerGeneration;
	double residual;
	List<Expression> expressions;
	List<Point2D.Double> evaluationPoints;
	double last_error = 0;
	
	OptimizationCase(   int inExpressionsAtStart,
						int inSurvivorsPerGeneration,
						int inUnmutatedSurvivors,
						int inNewExpressionsPerGeneration,
						int inChildrenPerSurvivor,
						int inMaxSteps,
						int inMaxDepth,
						List<Point2D.Double> inEvaluationPoints,
						double inResidual) throws Exception {
		expressionsAtStart = inExpressionsAtStart;
		maxSteps = inMaxSteps;
		maxDepth = inMaxDepth;
		numberOfEvaluationPoints = inEvaluationPoints.size();
		childrenPerSurvivor = inChildrenPerSurvivor;
		unmutatedInNextGeneration = inUnmutatedSurvivors;
		survivorsPerGeneration = inSurvivorsPerGeneration;
		newExpressionsPerGeneration = inNewExpressionsPerGeneration;
		residual = inResidual;
		expressions = new ArrayList<Expression>();
		evaluationPoints = inEvaluationPoints;
		
		initialize_expressions();
		boolean is_sane = check_and_sort_evaluation_points();
		if(!is_sane) {
			throw new Exception("The set of points was not sane (multiple y values for one x value)");
		}
		
	}
	
	void initialize_expressions() {
		expressions.clear();
		for(int i = 0; i < expressionsAtStart; i++) {
			expressions.add(ExpressionGenerator.GenerateInitial(maxDepth)); 
		}
	}
	
	boolean check_and_sort_evaluation_points() {
		Collections.sort(evaluationPoints, (d1, d2) -> {
			return - Double.compare(d1.getX(),d2.getY());
		});
		boolean is_function_of_x = true;
		for(int i = 1; i < evaluationPoints.size(); i++) {
			Point2D p1 = evaluationPoints.get(i-1);
			Point2D p2 = evaluationPoints.get(i);
			if( Math.abs(p1.getX() - p2.getX()) < 0.0001 ) {
				if(p1.getY() != p2.getY()) {
					is_function_of_x = false;
				}
			}
		}
		return is_function_of_x;
	}
	
	void run() {
		for(int step = 0; step < maxSteps; step++) {
			mutate_expressions();
			simplify_expressions();
			if(found_suficcient_solution()) break;
			sort_expression_list();
			remove_bad_expressions();
			fill_expressions_list();
			simplify_expressions();
			write_outputs(step);
		}
	}
	
	boolean found_suficcient_solution() {
		for(int i = 0; i < expressions.size(); i++) {
			if(compute_error_for_expression(expressions.get(i)) < residual) {
				System.out.println("Found solution: " + expressions.get(i).toString());
				System.out.println("Residual: " + compute_error_for_expression(expressions.get(i)));
				return true;
			};
		}
		return false;
	}
	
	void simplify_expressions() {
		for(int i = 0; i < expressions.size(); i++) {
			expressions.get(i).simplify();
		}
	}
	
	void sort_expression_list() {
		Collections.sort(expressions, (d1, d2) -> {
			return compare(d1,d2);
		});
	}

	void remove_bad_expressions() {
		while(expressions.size() > survivorsPerGeneration) {
			expressions.remove(survivorsPerGeneration);
		}
	}
	
	void fill_expressions_list() {
		for(int i = 0; i < survivorsPerGeneration; i++) {
			for(int j = 0; j < childrenPerSurvivor; j++) {
				Expression new_expression = expressions.get(i).cloneExpression();
				expressions.add(new_expression);	
			}
			
		}
		for(int i = 0; i < newExpressionsPerGeneration; i++) {
			Expression new_expression = ExpressionGenerator.GenerateInitial(maxDepth);
			expressions.add(new_expression);
		}
	}
	
	void mutate_expressions() {
		for(int i = unmutatedInNextGeneration; i < expressions.size(); i++) {
			boolean has_mutated = false;
			while(!has_mutated) {
				has_mutated = expressions.get(i).mutate();
			}	
		}
	}
	
	void write_outputs(int step) {
		double current_best_approximation = compute_error_for_expression(expressions.get(0));
		if(current_best_approximation != last_error) {
			System.out.println("Report for step: " + step);
			System.out.println("Best expression: " + expressions.get(0).toString());
			System.out.println("Error evaluates to: " + current_best_approximation);
			last_error = current_best_approximation;
		}
	}
	
	int compare(Expression a, Expression b) {
		double error_a = compute_error_for_expression(a);
		double error_b = compute_error_for_expression(b);
		return Double.compare(error_a, error_b);
	}
	
	double compute_error_for_expression(Expression expression) {
		double error = 0;
		for(int i = 0; i < numberOfEvaluationPoints; i++) {
			error += Math.abs(expression.evaluateForX(evaluationPoints.get(i).getX()) - evaluationPoints.get(i).getY());
		}
		return error;
	}
}
