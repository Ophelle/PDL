package org.opencompare;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.Product;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class GenerationHtml3 {

	public void generateHtml3(List<Feature> features, String output, TraitementPcm tp) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Configuration config = new Configuration(Configuration.VERSION_2_3_20);
		config.setDefaultEncoding("UTF-8");
		Template temp = config.getTemplate("pcms/exampleV3.ftl");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Prototype formulaire de merde");
		
		//création des champs
		List<String> lsFeat = new ArrayList<String>();
		for(Feature feat : features) {
			lsFeat.add(feat.getName());
		}
		
		//cration des input
		/*List<Object> lsType = new ArrayList<Object>();
		for(Product produit : tp.getPcm().getProducts()) {
			if(produit.getKeyContent() != null && !produit.getKeyContent().equals("")){
				//for each cells
				for (Cell cell : produit.getCells()) {
				}
				}
			/*for(Feature feat : tp.getFeatures()) {
				Cell cell = produit.findCell(feat);
				System.out.println(cell.getInterpretation().toString());
			}
		}*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		map.put("features", lsFeat);
		// crée html
		File f = new File(output);
		FileWriter writ = new FileWriter(f);
		temp.process(map, writ);
		System.out.println("HTML CREE au dossier : " + output);
		writ.close();
	}
	
	public HashMap<String, List<String>> getTypes(TraitementPcm tp) {
		
		//Type of filter
		String strTtypeFilter_;
		//Object to contains filters
		HashMap<String, List<String>> feat_type = new HashMap<String, List<String>>();
		//For each product
		for (Feature feat : tp.getFeatures()) {
			List<String >ls = new ArrayList<String>();
			//Create an JSONObject to get content of cells for each product
			//if product exist or is not empty
			if(feat.getName() != null && !feat.getName().equals("")){
				//for each cells
				//for(Feature feat : tp.getPcm().getConcreteFeatures()) {
				
				for (Cell cell : feat.getCells()) {
					//feat_type.put(cell.getFeature().getName(), ls);
					//Adding cells value
					//Determine type of filter
					strTtypeFilter_ = cell.getInterpretation().toString(); //get type
					//Delete useless data
					strTtypeFilter_ = strTtypeFilter_.split("@")[0];
					strTtypeFilter_ = strTtypeFilter_.split("\\.")[6];
					strTtypeFilter_ = strTtypeFilter_.substring(0, strTtypeFilter_.length() - 4);
					
					//If type not know we put string type
					if (strTtypeFilter_.equals("NotAvailable")) {
						strTtypeFilter_ = "StringValue";
					}

					//Check with regExp if some string value can be number
					//Pattern p = Pattern.compile("\\d.*"); //if string start wtih numbers, we considers feature type like number (ex: 52 lb)
					//Matcher m = p.matcher(cell.getContent());
					//if regexp valid
					/*if (m.matches()) {
						strTtypeFilter_ = "RealValue"; //we change type of filter
					}*/
					
					
					ls.add(strTtypeFilter_);
				}
				feat_type.put(feat.getName(), ls);
			}
			
		}
		return feat_type;
	}
	
	public Map<String, String> getRealTypes(HashMap<String, List<String>> map) {
		
		Map<String, Integer> nb = new HashMap<String, Integer>();
		Map<String, String> best = new HashMap<String, String>();
		 
		 for(Entry<String, List<String>> entry1 : map.entrySet()) {
			 String key1 = entry1.getKey();
			 List<String> value1 = entry1.getValue();
			 
			 for(String str : value1) {
				if(!nb.containsKey(str)) {
					nb.put(str, 1);
				} else {
					Integer cpt = nb.get(str) + 1;
					nb.put(str, cpt);
					//System.out.println(key1 + " : " + str + " : " + cpt);
				}
				
			 }
			 
			 String bestType = "";
			 
			 for(Entry<String, Integer> entry2 : nb.entrySet()) {
				 String key2 = entry2.getKey();
				 Integer max = 0;
				 Integer currentNb = entry2.getValue();
				 
				 if(max < currentNb) {
					 max = currentNb;
					 bestType = key2;
				 }
			 }
			 
			 best.put(key1, bestType);
			 nb = new HashMap<String, Integer>();
		 }
		return best;
	}
	
}
