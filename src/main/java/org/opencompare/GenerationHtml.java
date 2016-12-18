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
	
	public GenerationHtml(TraitementPcm traitPcm, String input) {
		this.traitPcm = traitPcm;
		this.input = input;
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
			System.out.println("Le chargement du template a �chou� : " + e);
		}
		// Attribution des variables au code html source template
		Map<String, Object> ajoutVar = new HashMap<String, Object>();
		ajoutVar.put("titre", this.traitPcm.getNamePcm());
		ajoutVar.put("name", "Prototype de formulaire");
		ajoutVar.put("bestType", this.traitPcm.getBestTypeForEachFeature());
		ajoutVar.put("allContentsCell", this.traitPcm.getAllContentsOfEachCell());
		ajoutVar.put("ListMultiple",this.traitPcm.getContentsTypeMultiple());
		ajoutVar.put("getAllTypesValue", this.traitPcm.getAllTypesOfFeature());
		
		// Cr�ation html
		if(this.traitPcm.getFile() != null) {
			setOutput(this.traitPcm.getFile().getPath() + ".html");
			try {
				FileWriter writer = new FileWriter(new File(this.output));
				try {
					temp.process(ajoutVar, writer);
				} catch (TemplateException e) {
					System.out.println("La g�n�ration du HTML a �chou� : " + e);
				}
				System.out.println("HTML cr�� au dossier : " + this.output);
				writer.close();
			} catch (IOException e) {
				System.out.println("Le fichier n'a pas �t� charg� : " + e);
			}
		} else {
			System.out.println("Le fichier n'a pas pu �t� r�cup�r�");
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
			System.out.println("Le chargement du template a �chou� : " + e);
		}
		
		// Cr�ation htmls
		File repertoryPcm = new File("pcms");
		File[] filesPcm = repertoryPcm.listFiles();
		Map<String, Object> ajoutVar = new HashMap<String, Object>();
		
		// Parcours du dossier cotnenant les fichiers pcm
		for(int i = 0; i < filesPcm.length; i++) {
			if(filesPcm[i].getPath().endsWith(".pcm")) {
				// pour chaque fichier pcm, chargement du pcm au TraitementPcm
				getTraitPcm().loadPcm(filesPcm[i]);
				// Attribution des variables au code html source template
				ajoutVar.put("titre", this.traitPcm.getNamePcm());
				ajoutVar.put("name", "Prototype de formulaire");
				ajoutVar.put("bestType", this.traitPcm.getBestTypeForEachFeature());
				ajoutVar.put("allContentsCell", this.traitPcm.getAllContentsOfEachCell());
				ajoutVar.put("ListMultiple",this.traitPcm.getContentsTypeMultiple());
				ajoutVar.put("getAllTypesValue", this.traitPcm.getAllTypesOfFeature());
				
				// chaque html est cree dans le dossier ou se trouve le pcm
				setOutput(filesPcm[i].getPath() + ".html");
				FileWriter writer;
				try {
					writer = new FileWriter(new File(this.output));
					try {
						temp.process(ajoutVar, writer);
						
					} catch (TemplateException e) {
						System.out.println("La g�n�ration du HTML a �chou� : " + e);
					}
					writer.close();
				} catch (IOException e) {
					System.out.println("Le fichier n'existe pas : " + e);
				}
				System.out.println("HTML cr�� au dossier : " + this.output);
				// Reinitialisation des variables a ajouter au template pour chaque pcm
				ajoutVar = new HashMap<String, Object>();
			}
		}
		System.out.println("------Fin de la g�n�ration de tous les fichiers html-------");
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
