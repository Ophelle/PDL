package org.opencompare;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * class GenerationHTML
 * @author PDL: Mendes Ophélie, Dramé Oumar, Le Quang, Baouz Khaled and Moussa Lydia
 *
 */
public class GenerationHtml {
	
	private TraitementPcm traitPcm;
	private String input;
	private String output;
	
	/**
	 * construct of GenerationHtml
	 * @param traitPcm
	 * @param input
	 * @param output
	 */
	public GenerationHtml(TraitementPcm traitPcm, String input, String output) {
		this.traitPcm = traitPcm;
		this.input = input;
		this.output = output;
	}
	
	/**
	 * construct of GenerationHtml
	 * @param traitPcm
	 * @param input
	 */
	public GenerationHtml(TraitementPcm traitPcm, String input) {
		this.traitPcm = traitPcm;
		this.input = input;
	}

	/**
	 * function for lunch the generation of one form html
	 */
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
		// Attribution des variables au code html source template
		Map<String, Object> ajoutVar = new HashMap<String, Object>();
		ajoutVar.put("titre", this.traitPcm.getNamePcm());
		ajoutVar.put("name", this.traitPcm.getNamePcm());
		ajoutVar.put("bestType", this.traitPcm.getBestTypeForEachFeature());
		ajoutVar.put("allContentsCell", this.traitPcm.getAllContentsOfEachCell());
		ajoutVar.put("ListMultiple",this.traitPcm.getContentsTypeMultiple());
		ajoutVar.put("getAllTypesValue", this.traitPcm.getAllTypesOfFeature());
		
		// Création html
		if(this.traitPcm.getFile() != null) {
			setOutput(this.traitPcm.getFile().getPath() + ".html");
			try {
				FileWriter writer = new FileWriter(new File(this.output));
				try {
					temp.process(ajoutVar, writer);
				} catch (TemplateException e) {
					System.out.println("La génération du HTML a échoué : " + e);
				}
				System.out.println("HTML créé au dossier : " + this.output);
				writer.close();
			} catch (IOException e) {
				System.out.println("Le fichier n'a pas été chargé : " + e);
			}
		} else {
			System.out.println("Le fichier n'a pas pu été récupéré");
		}
	}
	
	/**
	 * function for lunch the generation of all form html
	 */
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
		
		// Création htmls
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
				ajoutVar.put("name", this.traitPcm.getNamePcm());
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
						System.out.println("La génération du HTML a échoué : " + e);
					}
					writer.close();
				} catch (IOException e) {
					System.out.println("Le fichier n'existe pas : " + e);
				}
				System.out.println("HTML créé au dossier : " + this.output);
				// Reinitialisation des variables a ajouter au template pour chaque pcm
				ajoutVar = new HashMap<String, Object>();
			}
		}
		System.out.println("------Fin de la génération de tous les fichiers html-------");
	}

	/**
	 * getter for the TraitementPcm
	 * @return TraitementPcm
	 */
	public TraitementPcm getTraitPcm() {
		return this.traitPcm;
	}

	/**
	 * setter for the TraitementPcm
	 * @param traitPcm
	 */
	public void setTraitPcm(TraitementPcm traitPcm) {
		this.traitPcm = traitPcm;
	}

	/**
	 * getter for the file FTL 
	 * @return String
	 */
	public String getInput() {
		return this.input;
	}

	/**
	 * setter for the file FTL
	 * @param input
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/**
	 * getter for the file HTML
	 * @return String
	 */
	public String getOutput() {
		return this.output;
	}

	/**
	 * setter for the file HTML
	 * @param output
	 */
	public void setOutput(String output) {
		this.output = output;
	}
}
