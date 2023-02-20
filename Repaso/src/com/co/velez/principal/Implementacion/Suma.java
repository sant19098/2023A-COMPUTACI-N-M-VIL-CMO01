package com.co.velez.principal.Implementacion;

import com.co.velez.principal.N1.OperacionInterface;

public class Suma implements OperacionInterface{
	
	@Override
	public double suma(double a, double b) {
		double N = a + b;
		return N;
	}
	
	@Override
	public double multi(double a, double b) {
		double N = a * b;
		return N;
	
}

	@Override
	public double resta(double a, double b) {
		double N = a - b;
		return N;
	}

	@Override
	public double divi(double a, double b) {
		double N = a / b;
		return N;
	}
}