package org.opencompare;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.Value;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class GenerationHtml3 {

	public void generateHtml3(List<Feature> features, String output, PCM pcm) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Configuration config = new Configuration(Configuration.VERSION_2_3_20);
		config.setDefaultEncoding("UTF-8");
		Template temp = config.getTemplate("pcms/exampleV3.ftl");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Prototype formulaire de merde");
		
		//création des champs
		List<String> lsFeat = new ArrayList<String>();
		for(Feature feat : features) {
			lsFeat.add(feat.getName());
		}
		map.put("features", lsFeat);
		/*//création type de inputs
		List<Object> lsInput = new ArrayList<Object>();
		for(Product p : pcm.getProducts()) {
			for(Feature feat : features) {
				Cell cell = p.findCell(feat);
				if(cell.getInterpretation() != null && !lsInput.contains(cell.getInterpretation().toString().substring(36))) {
					lsInput.add(cell.getInterpretation());
					System.out.println(cell.getInterpretation().toString().substring(36));
				}
			}
		}*/
		// crée html
		File f = new File(output);
		FileWriter writ = new FileWriter(f);
		temp.process(map, writ);
		System.out.println("HTML CREE au dossier : " + output);
		writ.close();
	}
}
