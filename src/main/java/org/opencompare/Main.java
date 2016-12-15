package org.opencompare;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		
		// Chargement fichier + pcm + traitement des types
		File file = new File("testPrototype/example0.pcm");
		TraitementPcm trait = new TraitementPcm(file);

		// Generation avec un template html : freemarker
		GenerationHtml generator = new GenerationHtml(trait, "testPrototype/testHtml1.ftl" , "testPrototype/testHtml1.html");
		//generator.getTraitPcm().loadPcm(new File("testPrototype/example1.pcm"));
		generator.generateHtml();
		//generator.generatAllHtml();
	}
}
