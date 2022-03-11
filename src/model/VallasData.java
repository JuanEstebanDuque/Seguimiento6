package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.Gson;

import main.Main;

public class VallasData implements Serializable{
	public static final String SEPARADOR = "\n";
	//attributes
	
	//relations
	private ArrayList<Vallas> vallas;
	private ArrayList<Vallas> dangerousVallas; 	
	//methods
	public VallasData() {
		vallas = new ArrayList<>();
		dangerousVallas = new ArrayList<>();
	}
	
	public void añadirVallas(Vallas valla) {
		vallas.add(valla);
		double area;
		for(int i=0;i<vallas.size();i++) {
			area = vallas.get(i).getWidth() * vallas.get(i).getHeight();
			if(area > 200.000) {
				dangerousVallas.add(vallas.get(i));
			}
		}
	}
	
	public void saveJSON() {
		try {
			Gson gson = new Gson();
			String json = gson.toJson(vallas);
			File file = new File("dataVallas.json");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(json.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadJSON() {
		try {
			FileInputStream is = new FileInputStream(new File("dataVallas.json"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			String json = "";
			while ((line = reader.readLine()) != null) {
				json += line;
			}
			Gson gson = new Gson();
			Vallas[] data = gson.fromJson(json, Vallas[].class);
			for(Vallas v : data) {
				vallas.add(v);
			}
			double area;
			for(int i=0;i<vallas.size();i++) {
				area = vallas.get(i).getWidth() * vallas.get(i).getHeight();
				if(area > 200.000) {
					dangerousVallas.add(vallas.get(i));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadCSV(String ruta) {		
		try {
			String path = ruta;
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String line = "";
			String data = "";
			while ((line = br.readLine()) != null) {
				data += line + "\n";
			}
			String[] fields = data.split("\n");
			for (int i = 1; i < fields.length; i++) {
				double width;
				double height;
				boolean inUse;
				String brand;
				String[] fieldsData = fields[i].split("\\|");
				vallas.add(new Vallas(width = Double.parseDouble(fieldsData[0]),height = Double.parseDouble(fieldsData[1]),inUse = Boolean.valueOf(fieldsData[2]),brand = fieldsData[3]));
			}
			double area;
			for(int i=0;i<vallas.size();i++) {
				area = vallas.get(i).getWidth() * vallas.get(i).getHeight();
				if(area > 200.000) {
					dangerousVallas.add(vallas.get(i));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No se encontró el archivo. Verifique la dirección del archivo.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mostrarVallas() {
		System.out.println("ancho / altura / está en uso? / marca");
		int counter = 0;
		for (Vallas v : vallas) {
			System.out.println(v.getWidth()+" / "+v.getHeight()+" / "+v.isInUse()+" / "+v.getBrand());
			counter++;	
		}
		System.out.println("Total de vallas: "+counter);
	}
	
	public void mostrarVallasPeligrosas() {
		System.out.print(
			"===========================\n"+
			"DANGEROUS BILLBOARD REPORT\n"+
			"===========================\n"
			);
		int counter = 0;
		for (Vallas v : dangerousVallas) {
			double area = dangerousVallas.get(counter).getHeight() * dangerousVallas.get(counter).getWidth();
			System.out.println("Billboard <"+dangerousVallas.get(counter).getBrand()+"> with area <"+area+">");
			counter++;
		}
	}
}
