package org.opencompare;

import java.io.IOException;

import freemarker.template.TemplateException;

public class Main {

	public static void main(String[] args) throws IOException, TemplateException {
		// chargement pcm
		TraitementPcm trait = new TraitementPcm();
		trait.loadPcm();
		//generation avec un long string
		GenerationHtml gen = new GenerationHtml();
		gen.generateHtml(trait.getFeatures(), "pcms/exampleV1.html");
		// generation avec un builder html : j2jtml
		GenerationHtml2 gen2 = new GenerationHtml2();
		gen2.generateHtml2(trait.getFeatures(), "pcms/exampleV2.html");
		//generation avec un template html : freemarker
		GenerationHtml3 gen3 = new GenerationHtml3();
		gen3.generateHtml3(trait.getFeatures(), "pcms/exampleV3.html", trait.getPcm());
	}

}
