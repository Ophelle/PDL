package org.opencompare;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class GenerationHtml {
	
	private TraitementPcm traitPcm;
	private String input;
	private String output;
	
	public GenerationHtml(TraitementPcm traitPcm, String input, String output) {
		this.traitPcm = traitPcm;
		this.input = input;
		this.output = output;
	}

	public void generateHtml() {
		// Configuration du template
		Configuration config = new Configuration(Configuration.VERSION_2_3_20);
		config.setDefaultEncoding("UTF-8");
		// Affectation du code html source au template
		Template temp = null;
		try {
			temp = config.getTemplate(this.input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Attribution des variables au code html source
		Map<String, Object> ajoutVar = new HashMap<String, Object>();
		ajoutVar.put("titre", this.traitPcm.getNamePcm());
		ajoutVar.put("name", "Prototype de formulaire");
		//ajoutVar.put("bestType", this.traitPcm.getBestTypes());
		ajoutVar.put("trueType", this.traitPcm.getTrueType());
		
		// Création html
		File file = new File(this.output);
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			temp.process(ajoutVar, writer);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("HTML CREE au dossier : " + this.output);
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void generatAllHtml() {
		// Configuration du template
		Configuration config = new Configuration(Configuration.VERSION_2_3_20);
		config.setDefaultEncoding("UTF-8");
		// Affectation du code html source au template
		Template temp = null;
		try {
			temp = config.getTemplate(this.input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Création html
		File repertoryPcm = new File("pcms");
		File[] filesPcm = repertoryPcm.listFiles();
		Map<String, Object> ajoutVar = new HashMap<String, Object>();
		
		for(int i = 0; i < filesPcm.length; i++) {
			getTraitPcm().loadPcm(filesPcm[i]);
			// Attribution des variables au code html source
			ajoutVar.put("titre", this.traitPcm.getNamePcm());
			ajoutVar.put("name", "Prototype de formulaire");
			ajoutVar.put("bestType", this.traitPcm.getBestTypes());
			setOutput("html/" + filesPcm[i].getName() + ".html");
			File file = new File(this.output);
			FileWriter writer = null;
			try {
				writer = new FileWriter(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				temp.process(ajoutVar, writer);
			} catch (TemplateException | IOException e) {
				e.printStackTrace();
			}
			System.out.println("HTML CREE au dossier : " + this.output);
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ajoutVar = new HashMap<String, Object>();
		}
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
