package org.opencompare;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.opencompare.api.java.Feature;

import j2html.TagCreator;
import j2html.tags.Tag;

import static j2html.TagCreator.*;

public class GenerationHtml2 {
	private String code;
	
	public static List<Tag> getInputsAndButton(List<Feature> list) {
		List<Tag> lTag = new ArrayList<Tag>();
		for(Feature feat : list) {
			lTag.add(label(feat.getName() + " : \n").with(
					input().withType("text").withName("Feature"), br(), br()));
		}
		lTag.add(button().withType("submit").withText("Ajouter le produit"));
		return lTag;
	}
	
	public void generateHtml2(List<Feature> list, String output) throws IOException {
		code = document().render();
		code = code + html().with(
				head().with(
						title("Formulaire Version 0.01"),
						meta().withCharset("utf-8")),
				body().with(getInputsAndButton(list)).with(script().with(text("alert(\"PROTOTYPE DE MEER\")")))).render();
		File f = new File(output);
		FileWriter writ = new FileWriter(f);
		writ.write(code);
		System.out.println("HTML CREE au dossier : " + output);
		writ.close();
	}
}
