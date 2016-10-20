package org.opencompare;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.opencompare.api.java.Feature;

public class GenerationHtml {
	private String code;
	
	public void generateHtml(List<Feature> list, String output) throws IOException {
		code = "<html>\n"
				+ "<head>\n"
					+ "\t<meta charset=\"utf-8\"/>\n"
					+ "\t<title>Formulaire</title>\n"
				+ "</head>\n"
				+ "<body>\n"
					+ "\t<form>\n";
		
		for(Feature feat : list) {
			code = code + "\t\t<p>" + feat.getName() + " <input type=\"text\" name=\"feature\" /></p>\n";
		}
		
		code = code + "\t</form>\n </body>\n </html>";
		
		File f = new File(output);
		FileWriter writ = new FileWriter(f);
		writ.write(code);
		System.out.println("HTML CREE au dossier : " + output);
		writ.close();
	}

}
