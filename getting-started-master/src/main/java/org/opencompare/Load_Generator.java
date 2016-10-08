package org.opencompare;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

public class Load_Generator {
	
	// vérifie si y a des case null dans le pcm
	public static boolean pcmCheck(PCM pcm) {
		for(Product produit : pcm.getProducts()) {
			if(produit.getKeyContent() == null) {
				System.out.println("un produit est null");
				return false;
			}
		}
		
		for(Feature feat : pcm.getConcreteFeatures()) {
			if(feat.getName() == null) {
				System.out.println("une feature d'un produit est null");
				return false;
			}
		}
		System.out.println("PCM ok");
		return true;
	}
	
	// génère json à partir d'un pcm
	public static void jsonGenerator(PCM pcm) {
		JSONObject json= new JSONObject();
		
		for(Product produit : pcm.getProducts()) {
			JSONObject json_cell = new JSONObject();
			if(produit.getKeyContent() != null && !produit.getKeyContent().equals("")) {
				for(Cell cel : produit.getCells()) {
					json_cell.put(cel.getFeature().getName(), cel.getContent());
				}
				json.put(produit.getKeyContent(), json_cell);
			}
		}
		try {
			FileWriter fw = new FileWriter("pcms/toto.json");
			fw.write(json.toString());
			System.out.println("Json créé");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// charge pcm + le génère en json
	public static void loadPcm(String pcmName) {
		File file = new File(pcmName);
		PCMLoader load = new KMFJSONLoader();
		try {
			PCM pcm = load.load(file).get(0).getPcm();
			if(pcmCheck(pcm)) {
				jsonGenerator(pcm);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		loadPcm("pcms/example.pcm");
	}

}
