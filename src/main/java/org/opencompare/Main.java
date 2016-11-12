package org.opencompare;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		
		// Chargement fichier + pcm + traitement des types
		File file = new File("testPrototype/pcm_problem1.pcm");
		TraitementPcm trait = new TraitementPcm(file);

		// Generation avec un template html : freemarker
		GenerationHtml generator = new GenerationHtml(trait, "testPrototype/testHtml1.ftl" , "testPrototype/testHtml1.html");
		//generator.getTraitPcm().loadPcm(new File("testPrototype/example0.pcm"));
		generator.generateHtml();
		//generator.generatAllHtml();
		
		
		// test sur feature/type dominant
		/*Set<String> set1 = generator.getTraitPcm().getBestTypes().keySet();
		Collection<String> set1 = generator.getTraitPcm().getBestTypes().values();

		for (String str : set1) {
			System.out.println(str);
		}*/
	}
}
