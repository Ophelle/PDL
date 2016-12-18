package org.opencompare;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// creation d'un properties file pour faciliter le chargement et generation d'un fichier html
		Properties prop = new Properties();
		InputStream input;
		try {
			input = new FileInputStream("properties/config.properties");
			// charge le properties file
			prop.load(input);
			
			String str = "";
			Scanner sc = new Scanner(System.in);
			
			while(!str.equals("1") && !str.equals("2")) {
				System.out.println("Voulez-vous générer tous les formulaires ou un formulaire à partir du pcm de votre choix ? \n- Tapez 1 : Pour un formulaire\n- Tapez 2 : Pour tous les formulaires\n- Tapez q : Pour quitter");
				str = sc.nextLine();
				if(str.equals("2")) {
					TraitementPcm trait = new TraitementPcm(new File("template/example0.pcm"));
					GenerationHtml generator = new GenerationHtml(trait, "template/template_html.ftl");
					generator.generatAllHtml();
				} else if(str.equals("1")) {
					while(!str.equals("q")) {
						System.out.println("- Tapez le chemin de votre fichier pcm \n- Tapez d pour générer le pcm par défaut \n- Tapez q pour quitter");
						str = sc.nextLine();
						if(str.equals("d")) {
							TraitementPcm trait = new TraitementPcm(new File(prop.getProperty("input")));
							GenerationHtml generator = new GenerationHtml(trait, "template/template_html.ftl");
							generator.generateHtml();
						} else if(!str.equals("q")) {
							TraitementPcm trait = new TraitementPcm(new File(str));
							GenerationHtml generator = new GenerationHtml(trait, "template/template_html.ftl");
							generator.generateHtml();
						}
					}
				} else if(str.equals("q")) {
					break;
				} else {
					System.out.println("mauvaise valeur !");
				}
			}
			sc.close();
			input.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
