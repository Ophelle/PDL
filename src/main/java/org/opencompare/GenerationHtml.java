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
			System.out.println("Le chargement du template a échoué : " + e);
		}
		// Attribution des variables au code html source
		Map<String, Object> ajoutVar = new HashMap<String, Object>();
		ajoutVar.put("titre", this.traitPcm.getNamePcm());
		ajoutVar.put("name", "Prototype de formulaire");
		ajoutVar.put("bestType", this.traitPcm.getBestTypeForEachFeature());
		ajoutVar.put("allContentsCell", this.traitPcm.getAllContentsOfEachCell());
		ajoutVar.put("ListMultiple",this.traitPcm.getContentsTypeMultiple());
		ajoutVar.put("getAllTypesValue", this.traitPcm.getAllTypesOfFeature());
		
		// Création html
		//FileWriter writer;
		try {
			FileWriter writer = new FileWriter(new File(this.output));
			try {
				temp.process(ajoutVar, writer);
			} catch (TemplateException e) {
				System.out.println("La génération du HTML a échoué : " + e);
			}
			System.out.println("HTML CREE au dossier : " + this.output);
			writer.close();
		} catch (IOException e) {
			System.out.println("Le fichier n'existe pas : " + e);
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
			System.out.println("Le chargement du template a échoué : " + e);
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
			ajoutVar.put("bestType", this.traitPcm.getBestTypeForEachFeature());
			ajoutVar.put("allContentsCell", this.traitPcm.getAllContentsOfEachCell());
			ajoutVar.put("ListMultiple",this.traitPcm.getContentsTypeMultiple());
			ajoutVar.put("getAllTypesValue", this.traitPcm.getAllTypesOfFeature());
			
			setOutput("html/" + filesPcm[i].getName() + ".html");
			FileWriter writer;
			try {
				writer = new FileWriter(new File(this.output));
				try {
					temp.process(ajoutVar, writer);
				} catch (TemplateException e) {
					System.out.println("La génération du HTML a échoué : " + e);
				}
				writer.close();
			} catch (IOException e) {
				System.out.println("Le fichier n'existe pas : " + e);
			}
			System.out.println("HTML CREE au dossier : " + this.output);
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
