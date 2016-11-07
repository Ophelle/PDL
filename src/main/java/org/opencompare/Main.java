package org.opencompare;

import java.io.File;
import java.io.IOException;

import freemarker.template.TemplateException;

public class Main {

	public static void main(String[] args) throws IOException, TemplateException {
		
		// Chargement fichier + pcm + traitement des types
		File file = new File("pcms/example0.pcm");
		TraitementPcm trait = new TraitementPcm(file);

		// Generation avec un template html : freemarker
		GenerationHtml generator = new GenerationHtml(trait, "pcms/testHtml1.ftl" , "pcms/testHtml1.html");
		// test charger nouveau pcm
		generator.getTraitPcm().loadPcm(new File("pcms/example1.pcm"));
		generator.generateHtml();
		
		// test sur feature/type dominant
		/*Set<String> set1 = generator.getTraitPcm().getBestTypes().keySet();
		Collection<String> set1 = generator.getTraitPcm().getBestTypes().values();

		for (String str : set1) {
			System.out.println(str);
		}*/
	}
}
