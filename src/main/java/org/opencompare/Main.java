package org.opencompare;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import freemarker.template.TemplateException;

public class Main {

	public static void main(String[] args) throws IOException, TemplateException {
		// chargement pcm
		File file = new File("pcms/example.pcm");
		TraitementPcm trait = new TraitementPcm(file);
		trait.loadPcm();
		//generation avec un template html : freemarker
		GenerationHtml3 gen3 = new GenerationHtml3();
		gen3.generateHtml3(trait.getFeatures(), "pcms/exampleV3.html", trait);
		
		//Set<String> set1 = gen3.getRealTypes(gen3.getTypes(trait)).keySet();
		Collection<String> set1 = gen3.getRealTypes(gen3.getTypes(trait)).values();
		
		for(String str : set1) {
			System.out.println(str);
		}
		
	}

}
