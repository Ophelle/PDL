package org.opencompare;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.Value;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;
import org.opencompare.api.java.value.BooleanValue;
import org.opencompare.api.java.value.Conditional;
import org.opencompare.api.java.value.DateValue;
import org.opencompare.api.java.value.Dimension;
import org.opencompare.api.java.value.IntegerValue;
import org.opencompare.api.java.value.Multiple;
import org.opencompare.api.java.value.NotApplicable;
import org.opencompare.api.java.value.NotAvailable;
import org.opencompare.api.java.value.Partial;
import org.opencompare.api.java.value.RealValue;
import org.opencompare.api.java.value.StringValue;
import org.opencompare.api.java.value.Unit;
import org.opencompare.api.java.value.Version;

public class TraitementPcm {

	private PCM pcm;
	private String namePcm;
	private File file;
	private List<Feature> listFeatures;
	private Map<String, List<String>> allTypesOfFeature;
	private Map<String, List<String>> allContentsOfEachCell;
	private Map<String, List<String>> contentsTypeMultiple;
	private Map<String, String> bestTypeForEachFeature;

	public TraitementPcm(File file) {
		this.loadPcm(file);
	}
	
	public void loadPcm(File file) {
		try {
			this.file = file;
			PCMLoader loader = new KMFJSONLoader();
			this.pcm = loader.load(file).get(0).getPcm();
			this.namePcm = this.pcm.getName().replaceAll("_", " ");
			this.listFeatures = this.pcm.getConcreteFeatures();
			this.allTypesOfFeature = getAllTypesOfFeature(this.listFeatures);
			this.bestTypeForEachFeature = getBestTypeForEachFeature(this.allTypesOfFeature);
			this.contentsTypeMultiple = contentsTypeMultiple(this.listFeatures);
			this.allContentsOfEachCell = getAllContentsOfEachCell(this.listFeatures);
		} catch(IOException e) {
			System.out.println("Chargement du pcm échoué : " + e);
		}
	}

	public PCM getPcm() {
		return this.pcm;
	}

	public void setPcm(PCM pcm) {
		this.pcm = pcm;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getNamePcm() {
		return this.namePcm;
	}

	public void setNamePcm(String name) {
		this.namePcm = name;
	}

	public List<Feature> getFeatures() {
		return this.listFeatures;
	}

	public Map<String, List<String>> getAllTypesOfFeature() {
		return this.allTypesOfFeature;
	}

	public void setAllTypesOfFeature(Map<String, List<String>> allTypesValue) {
		this.allTypesOfFeature = allTypesValue;
	}

	public Map<String, List<String>> getAllContentsOfEachCell() {
		return this.allContentsOfEachCell;
	}

	public void setAllContentsOfEachCell(Map<String, List<String>> allContentsCell) {
		this.allContentsOfEachCell = allContentsCell;
	}

	public Map<String, List<String>> getContentsTypeMultiple() {
		return this.contentsTypeMultiple;
	}

	public void setContentsTypeMultiple(Map<String, List<String>> contentsTypeMultiple) {
		this.contentsTypeMultiple = contentsTypeMultiple;
	}

	public Map<String, String> getBestTypeForEachFeature() {
		return this.bestTypeForEachFeature;
	}

	public void setBestTypeForEachFeature(Map<String, String> bestTypes) {
		this.bestTypeForEachFeature = bestTypes;
	}

	public String valueToString(Value kValue) {
		if (kValue == null) {
			return "";
		} else if (kValue instanceof BooleanValue) {
			return "boolean";
		} else if (kValue instanceof IntegerValue) {
			return "integer";
		} else if (kValue instanceof StringValue) {
			return "string";
		} else if (kValue instanceof RealValue) {
			return "real";
		} else if (kValue instanceof Multiple) {
			return "multiple";
		} else if (kValue instanceof NotApplicable) {
			return "notapplicable";
		} else if (kValue instanceof NotAvailable) {
			return "notavailable";
		} else if (kValue instanceof Conditional) {
			return "conditional";
		} else if (kValue instanceof DateValue) {
			return "date";
		} else if (kValue instanceof Dimension) {
			return "dimension";
		} else if (kValue instanceof Partial) {
			return "partial";
		} else if (kValue instanceof Unit) {
			return "unit";
		} else if (kValue instanceof Version) {
			return "version";
		} else {
			throw new UnsupportedOperationException("The value type is not supported !");
		}
	}

	private Map<String, List<String>> getAllTypesOfFeature(List<Feature> listFeatures) {

		String currentType = "";
		// Map qui contient tous les types pour chaque feature de la matrice
		Map<String, List<String>> feat_type = new HashMap<String, List<String>>();

		for (Feature feat : listFeatures) {
			List<String> listTypes = new ArrayList<String>();
			String abstractFeature = "";
			// Si le feature existe ou n'est pas vide
			if (feat.getName() != null && !feat.getName().equals("")) {
				for (Cell cell : feat.getCells()) {
					// si le nom du abstractFeature n'est pas null, alors on le
					// recupere
					if (cell.getFeature().getParentGroup() != null) {
						// ajout d'un separateur "-" pour reperer plus
						// facilement le abstractFeature et concretFeature
						abstractFeature = cell.getFeature().getParentGroup().getName() + " - ";
					}
					// Obtenir le type de la case courante
					currentType = valueToString(cell.getInterpretation());
					
					// Ajout dans la liste le type de la case courante
					listTypes.add(currentType);
				}
				// Ajout dans la map le feature et sa liste de type disponible
				feat_type.put(abstractFeature + feat.getName(), listTypes);
			}
		}
		return feat_type;
	}

	public Map<String, List<String>> contentsTypeMultiple(List<Feature> listFeatures) {
		String currentType = "";
		// Traite le cas du type multiple avec pas de doublon de content pour
		// chaque feature
		// Map qui contient tous les choix possible pour chaque feature du type
		// multiple
		List<String> listMultiple = new ArrayList<String>();
		Map<String, List<String>> feat_contentMultiple = new HashMap<String, List<String>>();
		for (Feature feat : listFeatures) {
			if (!feat_contentMultiple.containsKey(feat)) {
				
				// Si le feature existe ou n'est pas vide
				if (feat.getName() != null && !feat.getName().equals("")) {
					String[] currentContent = null;

					for (Cell cell : feat.getCells()) {

						currentType = valueToString(cell.getInterpretation());

						if (currentType == "multiple") {
							currentContent = cell.getContent().split("[,/]");
							for (String contenu : currentContent) {
								if(contenu.charAt(0) == ' ') {
									contenu = contenu.substring(1);
									if(!listMultiple.contains(contenu)) {
										listMultiple.add(contenu);
									}
								} else {
									if(!listMultiple.contains(contenu)) {
										listMultiple.add(contenu);
									}
								}
							}
						}
					}
					if(!listMultiple.isEmpty()) {
						feat_contentMultiple.put(feat.getName(), listMultiple);
						listMultiple = new ArrayList<>();
					}
				}
			}
		}
		return feat_contentMultiple;
	}

	private Map<String, List<String>> getAllContentsOfEachCell(List<Feature> listFeatures) {
		// Cette méthode servira pour l'auto-completion en javascript
		String currentContent = "";

		Map<String, List<String>> feat_content = new LinkedHashMap<String, List<String>>();

		for (String type : this.bestTypeForEachFeature.keySet()) {
			if (bestTypeForEachFeature.get(type) == "text") {
				for (Feature feat : listFeatures) {
					if (type.contains(feat.getName())) {
						List<String> listContents = new ArrayList<String>();

						// Si le feature existe ou n'est pas vide
						if (feat.getName() != null && !feat.getName().equals("")
								&& bestTypeForEachFeature.get(feat.getName()) == "text") {
							for (Cell cell : feat.getCells()) {
								// Obtenir le contenu de la case
								currentContent = cell.getContent();
								if (!listContents.contains(currentContent)) {
									// Ajout dans la liste le contenu de la case
									// courante
									listContents.add(currentContent);
								}
							}
							// Ajout dans la map le feature et sa liste de
							// contenus disponible
							feat_content.put(feat.getName(), listContents);
						}
					}
				}

			}
		}
		return feat_content;
	}
	
	public String setTypeForHtml(String str) {
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
		case "notavailable":
			str = "text";
			break;
		case "notapplicable":
			str = "text";
			break;
		case "partial":
			str = "text";
			break;
		default:
			str = "text";
		}
		return str;
	}

	public Map<String, String> getBestTypeForEachFeature(Map<String, List<String>> allTypes) {

		Map<String, Integer> nbOccurrence = new HashMap<String, Integer>();
		Map<String, String> bestTypes = new HashMap<String, String>();
		Integer max = 0;
		for (Entry<String, List<String>> entry1 : allTypes.entrySet()) {
			// Clé : feature
			String feat = entry1.getKey();
			// Valeur : liste de type
			List<String> type = entry1.getValue();

			// Compte le nombre d'occurrence pour chaque type dans la liste
			// courante
			for (String value : type) {
				// Si le type n'existe pas dans le nbOccurrence, on l'ajoute et
				// la valeur attribuee a 1
				if (!nbOccurrence.containsKey(value)) {
					nbOccurrence.put(value, 1);
				} else {
					// Si le type existe deja, incrementation la valeur associe
					// a sa cle
					Integer cpt = nbOccurrence.get(value) + 1;
					nbOccurrence.put(value, cpt);
				}
			}
			
			// Cherche le type dominant dans la map nbOccurrence en fonction de
			// sa valeur, cad son nombre d'occurrence
			String bestType = "";
			for (Entry<String, Integer> entry2 : nbOccurrence.entrySet()) {
				String currentFeat = entry2.getKey();
				Integer currentNb = entry2.getValue();
				

				if (max < currentNb) {
					max = currentNb;
					bestType = currentFeat;
					
					// attribut le type Html en fonction du type dominant trouvé
					//bestType = setTypeForHtml(bestType);
				}
			}
			bestTypes.put(feat, setTypeForHtml(bestType));
			// Reinitialisation du nbOccurence pour les prochains features a
			// traiter
			nbOccurrence = new HashMap<String, Integer>();
			max = 0;
		}
		return bestTypes;
	}
}
