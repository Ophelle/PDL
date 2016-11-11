package org.opencompare;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		// Chargement fichier + pcm + traitement des types
		File file = new File("testPrototype/example0.pcm");
		TraitementPcm trait = null;
		try {
			trait = new TraitementPcm(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Generation avec un template html : freemarker
		GenerationHtml generator = new GenerationHtml(trait, "testPrototype/testHtml1.ftl" , "testPrototype/testHtml1.html");
		// test charger nouveau pcm
//		try {
//			generator.getTraitPcm().loadPcm(new File("testPrototype/example1.pcm"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		generator.generateHtml();
		generator.generatAllHtml();
		
		
		// test sur feature/type dominant
		/*Set<String> set1 = generator.getTraitPcm().getBestTypes().keySet();
		Collection<String> set1 = generator.getTraitPcm().getBestTypes().values();

		for (String str : set1) {
			System.out.println(str);
		}*/
	}
}
