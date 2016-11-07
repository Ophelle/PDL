package org.opencompare;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

public class TraitementPcm {
	
	private PCM pcm;
	private List<Feature> listFeatures;
	private List<Object> listTypes;
	private File file;
	
	public TraitementPcm(File file) {
		this.file = file;
	}
	
	public void loadPcm() throws IOException {
        PCMLoader loader = new KMFJSONLoader();
        this.pcm = loader.load(this.file).get(0).getPcm();
	}

	public PCM getPcm() {
		return pcm;
	}
	
	public void setPcm(PCM pcm) {
		this.pcm = pcm;
	}
	
	public List<Feature> getFeatures() {
		return this.listFeatures = pcm.getConcreteFeatures();
	}
	
	public List<Object> getTypes() {
		
		//creation des inputs
		/*this.listTypes = new ArrayList<Object>();
		for(Product produit : this.pcm.getProducts()) {
			for(Feature feat : this.listFeatures) {
				Cell cell = produit.findCell(feat);
				if(cell.getInterpretation() != null && !lsInput.contains(cell.getInterpretation().toString().substring(36))) {
					lsInput.add(cell.getInterpretation());
					System.out.println(cell.getInterpretation().toString().substring(36));
				}
			}
		}*/
		return this.listTypes;
	}
	
}
