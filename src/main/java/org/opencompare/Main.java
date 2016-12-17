package org.opencompare;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Properties prop = new Properties();
		InputStream input;
		try {
			input = new FileInputStream("properties/config.properties");
			// load a properties file
			prop.load(input);
			
			String str = "";
			int nb = 0;
			Scanner sc = new Scanner(System.in);
			Scanner sc2 = new Scanner(System.in);
			while(nb != 1 && nb != 2) {
				System.out.println("Voulez-vous générer tous les formulaires ou un formulaire à partir du pcm de votre choix ? \n- Tapez 1 : Pour un formulaire\n- Tapez 2 : Pour tous les formulaires");
				nb = sc.nextInt();
				if(nb == 2) {
					TraitementPcm trait = new TraitementPcm(new File("template/example0.pcm"));
					GenerationHtml generator = new GenerationHtml(trait, "template/template_html.ftl");
					generator.generatAllHtml();
				} else if(nb == 1) {
					while(!str.equals("q")) {
						System.out.println("- Tapez le chemin de votre fichier pcm \n- Tapez d pour générer le pcm par défaut \n- Tapez q pour quitter");
						str = sc2.nextLine();
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
				} else {
					System.out.println("mauvaise valeur !");
				}
			}
			sc.close();
			sc2.close();
			input.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
