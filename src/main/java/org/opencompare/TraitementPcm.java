package org.opencompare;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;

import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

public class TraitementPcm {
	
	private PCM pcm;
	private List<Feature> listFeatures;
	
	public void loadPcm() throws IOException {
		File pcmFile = new File("pcms/example.pcm");
        PCMLoader loader = new KMFJSONLoader();
        this.pcm = loader.load(pcmFile).get(0).getPcm();
        this.listFeatures = pcm.getConcreteFeatures();
	}

	public PCM getPcm() {
		return this.pcm;
	}
	
	public void setPcm(PCM pcm) {
		this.pcm = pcm;
	}
	
	public List<Feature> getFeatures() {
		return this.listFeatures;
	}
}
