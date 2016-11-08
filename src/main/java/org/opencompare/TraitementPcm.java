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
	private String namePcm;
	private File file;
	private List<Feature> listFeatures;
	private Map<String, List<String>> allTypesValue;
	private Map<String, List<String>> allContentsCell;
	private Map<String, List<String>> contentsTypeMultiple;
	private Map<String, String> bestTypesValue;
	
	public TraitementPcm(File file) throws IOException {
		this.file = file;
		this.loadPcm(this.file);
		this.namePcm = this.pcm.getName();
		this.listFeatures = this.pcm.getConcreteFeatures();
		this.allTypesValue = getAllTypesValue(this.listFeatures);
		this.allContentsCell = getAllContentsCell(this.listFeatures);
		this.contentsTypeMultiple =  contentsTypeMultiple(this.listFeatures);
		this.bestTypesValue = getBestTypes(this.allTypesValue);
	}
	
	public PCM getPcm() {
		return this.pcm;
	}
	
	public void setPcm(PCM pcm) {
		this.pcm = pcm;
	}
	
	public String getNamePcm() {
		return this.namePcm.replaceAll("_", " ");
	}

	public void setNamePcm(String name) {
		this.namePcm = name;
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
	
	public Map<String, List<String>> getAllContentsCell() {
		return this.allContentsCell;
	}

	public void setAllContentsCell(Map<String, List<String>> allContentsCell) {
		this.allContentsCell = allContentsCell;
	}
	
	public Map<String, List<String>> getContentsTypeMultiple() {
		return this.contentsTypeMultiple;
	}

	public void setContentsTypeMultiple(Map<String, List<String>> contentsTypeMultiple) {
		this.contentsTypeMultiple = contentsTypeMultiple;
	}
	
	public Map<String, String> getBestTypes() {
		return this.bestTypesValue;
	}

	public void setBestTypes(Map<String, String> bestTypes) {
		this.bestTypesValue = bestTypes;
	}
	
	public void loadPcm(File file) throws IOException {
		this.file = file;
        PCMLoader loader = new KMFJSONLoader();
        this.pcm = loader.load(file).get(0).getPcm();
        this.namePcm = this.pcm.getName();
		this.listFeatures = this.pcm.getConcreteFeatures();
		this.allTypesValue = getAllTypesValue(this.listFeatures);
		this.bestTypesValue = getBestTypes(this.allTypesValue);
	}
	
	public String splitInterpretationValue(String str) {
		str = str.split("@")[0];
		str = str.split("\\.")[6];
		str = str.substring(0, str.length() - 4);
		str = str.split("V")[0].toLowerCase();
		return str;
	}
	
	public String setDefaultType(String str) {
		// a completer des qu'on a la signifiacation de toute les valueInterpreation
		if(str.equals("notavailable") || str.equals("notapplicable")) {
			str = "string";
		}
		return str;
	}
	
	private Map<String, List<String>> getAllTypesValue(List<Feature> listFeatures) {

		String currentType = "";
		// Map qui contient tous les types pour chaque feature de la matrice
		Map<String, List<String>> feat_type = new HashMap<String, List<String>>();

		for(Feature feat : listFeatures) {
			List<String> listTypes = new ArrayList<String>();
			
			// Si le feature existe ou n'est pas vide
			if(feat.getName() != null && !feat.getName().equals("")) {
				for(Cell cell : feat.getCells()) {
					// Obtenir le type de la case
					currentType = cell.getInterpretation().toString();
					// Split sur le currentType en supprimant les parties inutiles
					splitInterpretationValue(currentType);
					// Si le type est inconnu ou autre, alors par défaut c'est un string
					setDefaultType(currentType);
					
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
	
	public Map<String, List<String>> contentsTypeMultiple(List<Feature> listFeatures) {
		// a completer et utiliser un split pour avoir tous les resultats possibles
		// Traite le cas du type multiple avec pas de doublon de content pour chaque feature
		//String currentContent = "";
		// Map qui contient tous les choix possible pour chaque feature du type multiple
		Map<String, List<String>> feat_contentMultiple = new HashMap<String, List<String>>();
		return feat_contentMultiple;
	}
	
	private Map<String, List<String>> getAllContentsCell(List<Feature> listFeatures) {
		// Cette méthode servira pour l'auto-completion en javascript
		String currentContent = "";
		// Map qui contient tous les contenus de la case pour chaque feature de la matrice
		Map<String, List<String>> feat_content = new HashMap<String, List<String>>();

		for(Feature feat : listFeatures) {
			List<String> listContents = new ArrayList<String>();
			
			// Si le feature existe ou n'est pas vide
			if(feat.getName() != null && !feat.getName().equals("")) {
				for(Cell cell : feat.getCells()) {
					// Obtenir le contenu de la case
					currentContent = cell.getContent();
					// Ajout dans la liste le contenu de la case courante
					listContents.add(currentContent);
				}
				// Ajout dans la map le feature et sa liste de contenus disponible
				feat_content.put(feat.getName(), listContents);
			}
		}
		return feat_content;
	}
	
	public String setTypeHtml(String str) {
		switch (str) {
		case "conditional":
			str = "text";
			break;
		case "date":
			str = "date";
			break;
		case "dimension":
			str = "text";
			break;
		case "unit":
			str = "text";
			break;
		case "version":
			str = "text";
			break;
		case "string":
			str = "text";
			break;
		case "integer":
			str = "number";
			break;
		case "real":
			str = "number";
			break;
		case "boolean":
			str = "radio";
			break;
		case "multiple":
			str = "checkbox";
			break;
		default:
			str = "text";
		}
		return str;
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
					setTypeHtml(bestType);
				}
			}

			bestTypes.put(feat, bestType);
			// Reinitialisation du nbOccurence pour les prochains features a traiter
			nbOccurrence = new HashMap<String, Integer>();
		}
		return bestTypes;
	}
}
