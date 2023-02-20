package com.co.velez.principal;

import java.util.Scanner;

import com.co.velez.principal.Implementacion.Dividir;
import com.co.velez.principal.Implementacion.Multiplicar;
import com.co.velez.principal.Implementacion.Resta;
import com.co.velez.principal.Implementacion.Suma;
import com.co.velez.principal.N1.OperacionInterface;

public class Menu {

	Scanner sc = new Scanner(System.in);

	public void MostrarMenu() {

		System.out.println("Digite el numero Uno: ");
		Double numUno = sc.nextDouble();

		System.out.println("Digite el numero Dos: ");
		Double numDos = sc.nextDouble();

		System.out.println("Operacion : ");
		int selecion = sc.nextInt();

		switch (selecion) {
		case 1: {

			Suma sm = new Suma();
			System.out.println(sm.suma(numUno, numDos));
			break;
		}

		case 2:
			
			Resta rm = new Resta();
			System.out.println(rm.resta(numUno, numDos));
			break;
			
		case 3:
			
			Multiplicar mm = new Multiplicar();
			System.out.println(mm.multi(numUno, numDos));
			break;
			
		case 4:
			
			Dividir dm = new Dividir(); 
			System.out.println(dm.divi(numUno, numDos));
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + selecion);
		}

	}

}
