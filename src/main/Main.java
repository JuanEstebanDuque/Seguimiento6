package main;

import java.io.BufferedReader;
import java.util.Scanner;

import model.Vallas;
import model.VallasData;

public class Main {
	
	public static Scanner lector = new Scanner(System.in);
	public static VallasData data = new VallasData();
	
	public static void main(String[] args) {
		data.loadJSON();
		
		int opt;
		boolean continuing = true;
		
		while (continuing == true) {
			System.out.print("Seleccione la opción que desea con el número de esta.\n" + 
					"1. Importar datos de CSV.\n"+ 
					"2. Agregar valla publicitaria.\n" + 
					"3. Mostrar vallas publicitarias.\n"+ 
					"4. Exportar reporte de peligrosidad.\n" + 
					"9. Exit.\n"
					);
			opt = Integer.parseInt(lector.nextLine());
			switch (opt) {
			case 1: importarCSV();
				break;
			case 2: agregarValla();
				break;
			case 3: mostrarValla();
				break;
			case 4: reportePeligrosidad();
				break;
			case 9: System.out.println("Programa terminado con éxito.");
					continuing = false;
					break;
			default:
				System.out.println("Opción seleccionada errónea. Seleccione nuevamente.");
				break;
			}
		}
	}
	
	public static void importarCSV() {
		System.out.println("Ingrese la ruta absoluta del archivo.");
		String ruta = lector.nextLine();
		data.loadCSV(ruta);
		data.saveJSON();
	}
	
	public static void agregarValla() {
		System.out.println("Ingrese el ancho de la valla publicitaria.");
		double width = Double.parseDouble(lector.nextLine());
		System.out.println("Ingrese la altura de la valla publicitaria.");
		double height = Double.parseDouble(lector.nextLine());
		System.out.println("Ingrese SI, si la valla publicitaria está en uso, y NO para lo contrario.");
		boolean inUse;
		String use = lector.nextLine();
		if(use.equalsIgnoreCase("si")) {
			inUse = true;
		} else {
			inUse = false;
		}
		System.out.println("Ingrese la marca de la valla publicitaria.");
		String brand = lector.nextLine();
		data.añadirVallas(new Vallas(width,height,inUse,brand));
		data.saveJSON();
	}

	public static void mostrarValla() {
		data.mostrarVallas();
	}
	
	public static void reportePeligrosidad() {
		data.mostrarVallasPeligrosas();
	}
	
}
