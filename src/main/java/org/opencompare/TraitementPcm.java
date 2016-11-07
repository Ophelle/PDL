package org.opencompare;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

public class TraitementPcm {
	
	private PCM pcm;
	private File file;
	private List<Feature> listFeatures;
	private Map<String, List<String>> allTypesValue;
	private Map<String, String> bestTypesValue;
	
	public TraitementPcm(File file) throws IOException {
		this.file = file;
		loadPcm(this.file);
		this.listFeatures = pcm.getConcreteFeatures();
		this.allTypesValue = getAllTypesValue(this.listFeatures);
		this.bestTypesValue = getBestTypes(this.allTypesValue);
	}
	
	public PCM getPcm() {
		return this.pcm;
	}
	
	public void setPcm(PCM pcm) {
		this.pcm = pcm;
	}
	
	public List<Feature> getFeatures() {
		return this.listFeatures;
	}
	
	public Map<String, List<String>> getAllTypesValue() {
		return this.allTypesValue;
	}

	public void setAllTypesValue(Map<String, List<String>> allTypesValue) {
		this.allTypesValue = allTypesValue;
	}
	
	public Map<String, String> getBestTypes() {
		return this.bestTypesValue;
	}

	public void setBestTypes(Map<String, String> bestTypes) {
		this.bestTypesValue = bestTypes;
	}
	
	public void loadPcm(File file) throws IOException {
        PCMLoader loader = new KMFJSONLoader();
        this.pcm = loader.load(file).get(0).getPcm();
	}
	
	private Map<String, List<String>> getAllTypesValue(List<Feature> listFeatures) {

		String currentType = "";
		// Map qui contient tous les types pour chaque feature de la matrice
		Map<String, List<String>> feat_type = new HashMap<String, List<String>>();

		for(Feature feat : listFeatures) {
			List<String> listTypes = new ArrayList<String>();
			// Si le produit existe ou n'est pas vide

			if(feat.getName() != null && !feat.getName().equals("")) {
				for(Cell cell : feat.getCells()) {
					// Obtenir le type de la case
					currentType = cell.getInterpretation().toString();
					// Split sur le currentType en supprimant les parties inutiles
					currentType = currentType.split("@")[0];
					currentType = currentType.split("\\.")[6];
					currentType = currentType.substring(0, currentType.length() - 4);
					currentType = currentType.split("V")[0].toLowerCase();

					// Si le type est inconnu, alors par défaut c'est un string
					if(currentType.equals("NotAvailable")) {
						currentType = "string";
					}

					// Check with regExp if some string value can be number
					// Pattern p = Pattern.compile("\\d.*"); //if string start
					// wtih numbers, we considers feature type like number (ex:
					// 52 lb)
					// Matcher m = p.matcher(cell.getContent());
					// if regexp valid
					/*
					 * if (m.matches()) { currentType = "RealValue"; //we change
					 * type of filter }
					 */

					// Ajout dans la liste le type de la case courante
					listTypes.add(currentType);
				}
				// Ajout dans la map le feature et sa liste de type disponible
				feat_type.put(feat.getName(), listTypes);
			}
		}
		return feat_type;
	}
	
	private Map<String, String> getBestTypes(Map<String, List<String>> allTypes) {

		Map<String, Integer> nbOccurrence = new HashMap<String, Integer>();
		Map<String, String> bestTypes = new HashMap<String, String>();

		for(Entry<String, List<String>> entry1 : allTypes.entrySet()) {
			// Clé : feature
			String feat = entry1.getKey();
			// Valeur : liste de type
			List<String> type = entry1.getValue();
			
			// Compte le nombre d'occurrence pour chaque type dans la liste courante
			for(String value : type) {
				// Si le type n'existe pas dans le nbOccurrence, on l'ajoute et la valeur attribuee a 1
				if(!nbOccurrence.containsKey(value)) {
					nbOccurrence.put(value, 1);
				} else {
				// Si le type existe deja, incrementation la valeur associe a sa cle	
					Integer cpt = nbOccurrence.get(value) + 1;
					nbOccurrence.put(value, cpt);
					// System.out.println(key1 + " : " + str + " : " + cpt);
				}
			}
			
			// Cherche le type dominant dans la map nbOccurrence en fonction de sa valeur, cad son nombre d'occurrence
			String bestType = "";

			for (Entry<String, Integer> entry2 : nbOccurrence.entrySet()) {
				String currentFeat = entry2.getKey();
				Integer currentNb = entry2.getValue();
				Integer max = 0;
				
				if (max < currentNb) {
					max = currentNb;
					bestType = currentFeat;
					// attribut le type Html en fonction du type dominant trouvé
					switch (bestType) {
					case "string" :
						bestType = "text";
						break;
					case "integer" :
						bestType = "number";
						break;
					case "real" :
						bestType = "number";
						break;
					case "boolean" :
						bestType = "radio";
						break;
					case "multiple" :
						bestType = "checkbox";
						break;

					default :
						bestType = "text";
					}
				}
			}

			bestTypes.put(feat, bestType);
			// Reinitialisation du nbOccurence pour les prochains features a traiter
			nbOccurrence = new HashMap<String, Integer>();
		}
		return bestTypes;
	}
}
