package org.opencompare;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		TraitementPcm trait = new TraitementPcm();
		trait.loadPcm();
		
		GenerationHtml gen = new GenerationHtml();
		gen.generateHtml(trait.getFeatures(), "pcms/exampleV1.html");
		GenerationHtml2 gen2 = new GenerationHtml2();
		gen2.generateHtml2(trait.getFeatures(), "pcms/exampleV2.html");
	}

}
