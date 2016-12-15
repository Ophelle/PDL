package org.opencompare;

import java.io.File;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String str = "";
		int a = 0;	
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		while(a != 1 && a != 2){
			// Chargement fichier + pcm + traitement des types
			System.out.println("Voulez-vous générer tous les formulaires ou un formulaire à partir du pcm de votre choix ?\n 1:Pour tous les formulaires 2:Pour un formulaire");
			a=sc.nextInt();
			if(a == 1){
				TraitementPcm trait = new TraitementPcm(new File("testPrototype/example0.pcm"));
				GenerationHtml generator = new GenerationHtml(trait, "testPrototype/testHtml1.ftl" , "testPrototype/testHtml1.html");
				generator.generatAllHtml();
			}else if(a == 2){
				while(!str.equals("q")){
					System.out.println("Tapez le nom de votre pcm ou q pour quitter:");
					str = sc2.nextLine();
					if(!str.equals("q")){
						TraitementPcm trait = new TraitementPcm(new File(str));
						GenerationHtml generator = new GenerationHtml(trait, "testPrototype/testHtml1.ftl" , "testPrototype/testHtml1.html");
						generator.generateHtml();
					}
				}
			}else{
				System.out.println("mauvaise valeur !");
			}
		}
	}
}
