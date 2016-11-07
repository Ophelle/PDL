package org.opencompare;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class GenerationHtml {
	
	private TraitementPcm traitPcm;
	private String input;
	private String output;
	
	public GenerationHtml(TraitementPcm traitPcm, String input, String output) {
		this.traitPcm = traitPcm;
		this.input = input;
		this.output = output;
	}

	public void generateHtml() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		// Configuration du template
		Configuration config = new Configuration(Configuration.VERSION_2_3_20);
		config.setDefaultEncoding("UTF-8");
		// Affectation du code html source au template
		Template temp = config.getTemplate(this.input);
		// Attribution des variables au code html source
		Map<String, Object> ajoutVar = new HashMap<String, Object>();
		ajoutVar.put("name", "Prototype formulaire de merde");
		ajoutVar.put("bestType", this.traitPcm.getBestTypes());
		
		// Création html
		File file = new File(this.output);
		FileWriter writer = new FileWriter(file);
		temp.process(ajoutVar, writer);
		System.out.println("HTML CREE au dossier : " + this.output);
		writer.close();
	}

	public TraitementPcm getTraitPcm() {
		return this.traitPcm;
	}

	public void setTraitPcm(TraitementPcm traitPcm) {
		this.traitPcm = traitPcm;
	}

	public String getInput() {
		return this.input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return this.output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}
