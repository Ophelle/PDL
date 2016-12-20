package org.opencompare;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
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
/**
 * class TraitementPCM
 * @author PDL: Mendes Ophélie, Dramé Oumar, Le Quang, Baouz Khaled and Moussa Lydia
 *
 */
public class TraitementPcm {

	private PCM pcm;
	private String namePcm;
	private File file;
	private List<Feature> listFeatures;
	private Map<String, List<String>> allTypesOfFeature;
	private Map<String, List<String>> allContentsOfEachCell;
	private Map<String, List<String>> contentsTypeMultiple;
	private Map<String, String> bestTypeForEachFeature;

	/**
	 * contructor for TraitementPCM
	 * @param file
	 */
	public TraitementPcm(File file) {
		this.loadPcm(file);
	}
	
	/**
	 * function for lunch the analyze of the PCM
	 * @param file
	 */
	public void loadPcm(File file) {
		if(file.exists()) {
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
				System.out.println("Le chargement du pcm a échoué");
			}
		} else {
			System.out.println("Le fichier n'existe pas ou le chemin est mal défini");
		}
	}
	
	/**
	 * getter for the PCM
	 * @return PCM
	 */
	public PCM getPcm() {
		return this.pcm;
	}

	/**
	 * setter for the PCM
	 * @param pcm
	 */
	public void setPcm(PCM pcm) {
		this.pcm = pcm;
	}

	/**
	 * getter for file
	 * @return File
	 */
	public File getFile() {
		return file;
	}

	/**
	 * setter for file
	 * @param file
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * getter for the name of PCM
	 * @return String
	 */
	public String getNamePcm() {
		return this.namePcm;
	}

	/**
	 * setter for the name of PCM
	 * @param name
	 */
	public void setNamePcm(String name) {
		this.namePcm = name;
	}

	/**
	 * getter for the list of features
	 * @return List<Feature>
	 */
	public List<Feature> getFeatures() {
		return this.listFeatures;
	}

	/**
	 * getter for the list of types for all features
	 * @return Map<String, List<String>>
	 */
	public Map<String, List<String>> getAllTypesOfFeature() {
		return this.allTypesOfFeature;
	}

	/**
	 * setter for the list of types for all features
	 * @param allTypesValue
	 */
	public void setAllTypesOfFeature(Map<String, List<String>> allTypesValue) {
		this.allTypesOfFeature = allTypesValue;
	}

	/**
	 * getter for the list of the contents of each cell
	 * @return Map<String, List<String>>
	 */
	public Map<String, List<String>> getAllContentsOfEachCell() {
		return this.allContentsOfEachCell;
	}

	/**
	 * setter for list of the contents of each cell
	 * @param allContentsCell
	 */
	public void setAllContentsOfEachCell(Map<String, List<String>> allContentsCell) {
		this.allContentsOfEachCell = allContentsCell;
	}

	/**
	 * getter for the list of the contents for the type multiple 
	 * @return Map<String, List<String>>
	 */
	public Map<String, List<String>> getContentsTypeMultiple() {
		return this.contentsTypeMultiple;
	}

	/**
	 * setter for the list of the contents for the type multiple
	 * @param contentsTypeMultiple
	 */
	public void setContentsTypeMultiple(Map<String, List<String>> contentsTypeMultiple) {
		this.contentsTypeMultiple = contentsTypeMultiple;
	}

	/**
	 * getter for the list of the best type for each feature
	 * @return Map<String, String>
	 */
	public Map<String, String> getBestTypeForEachFeature() {
		return this.bestTypeForEachFeature;
	}

	/**
	 * setter for the list of the best type for each feature
	 * @param bestTypes
	 */
	public void setBestTypeForEachFeature(Map<String, String> bestTypes) {
		this.bestTypeForEachFeature = bestTypes;
	}

	/**
	 * function for translate the value in string type 
	 * @param kValue
	 * @returns String
	 */
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

	/**
	 * function for find all type for each feature
	 * @param listFeatures
	 * @return Map<String, List<String>>
	 */
	private Map<String, List<String>> getAllTypesOfFeature(List<Feature> listFeatures) {

		String currentType = "";
		// Map qui contient tous les types pour chaque feature de la matrice
		Map<String, List<String>> feat_type = new LinkedHashMap<String, List<String>>();

		for (Feature feat : listFeatures) {
			List<String> listTypes = new LinkedList<String>();
			String abstractFeature = "";
			// Si le feature existe ou n'est pas vide
			if (feat.getName() != null && !feat.getName().equals("")) {
				for (Cell cell : feat.getCells()) {
					// si le nom du abstractFeature n'est pas null, alors on le recupere
					if (cell.getFeature().getParentGroup() != null) {
						// ajout d'un separateur "-" pour reperer plus facilement le abstractFeature et concretFeature
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

	/**
	 * function for find the content for the feature of type multiple
	 * @param listFeatures
	 * @return Map<String, List<String>>
	 */
	public Map<String, List<String>> contentsTypeMultiple(List<Feature> listFeatures) {
		String currentType = "";
		// Traite le cas du type multiple avec pas de doublon de content pour chaque feature
		// Map qui contient tous les choix possible pour chaque feature du type multiple
		List<String> listMultiple = new LinkedList<String>();
		Map<String, List<String>> feat_contentMultiple = new LinkedHashMap<String, List<String>>();
		for(Feature feat : listFeatures) {
			
			if(!feat_contentMultiple.containsKey(feat)) {
				// Si le feature existe ou n'est pas vide
				if(feat.getName() != null && !feat.getName().equals("")) {
					String[] currentContent = null;
					
					// Parcours de chaque case d'un feature
					for(Cell cell : feat.getCells()) {
						currentType = valueToString(cell.getInterpretation());
						// Si la case est type multiple, alors split pour recuperer les valeurs posssibles
						if (currentType == "multiple") {
							currentContent = cell.getContent().split("[,/]");
							for (String contenu : currentContent) {
								if(!contenu.equals("") && !contenu.equals(null)) {
									// Si il y a un espace au debut de chaque valeur, on le supprime
									if(contenu.charAt(0) == ' ') {
										contenu = contenu.substring(1);
										// Si il y a un retour a la ligne entre chaque valeur, on le supprime pour que le js ne bogue pas
										contenu = contenu.replaceAll("\n", "");
										if(!listMultiple.contains(contenu)) {
											// ajout d'une valeur separee par la virgule dans la liste
											listMultiple.add(contenu);
										}
									} else {
										contenu = contenu.replaceAll("\n", "");
										if(!listMultiple.contains(contenu)) {
											listMultiple.add(contenu);
										}
									}
								}
							}
						}
					}
					// Si la liste qui contient les valeurs multiples n'est pas vide
					if(!listMultiple.isEmpty()) {
						// Ajout dans la map du nom feature multiple et sa liste valeur
						feat_contentMultiple.put(feat.getName(), listMultiple);
						// Reinitialisation de la liste pour les autres features
						listMultiple = new LinkedList<>();
					}
				}
			}
		}
		return feat_contentMultiple;
	}

	/**
	 * function for find all content in cell for each feature
	 * @param listFeatures
	 * @return Map<String, List<String>>
	 */
	private Map<String, List<String>> getAllContentsOfEachCell(List<Feature> listFeatures) {
		// Cette méthode servira pour l'auto-completion en javascript
		String currentContent = "";
		Map<String, List<String>> feat_content = new LinkedHashMap<String, List<String>>();
		
		// Parcours de la map bestTypeForEachFeature
		for(String type : this.bestTypeForEachFeature.keySet()) {
			// Si une feature a un type dominant text, alors on recupere toutes les valeurs disponibles pour l'autocompletion 
			if(bestTypeForEachFeature.get(type) == "text") {
				for(Feature feat : listFeatures) {
					// Supprime les saut de ligne au milieu du nom de feature pour du code html propre
					String featName = feat.getName().replaceAll("\n", "");
					if(type.contains(featName)) {
						List<String> listContents = new LinkedList<String>();

						// Si le feature existe ou n'est pas vide
						if(featName != null && !featName.equals("") && bestTypeForEachFeature.get(featName) == "text") {
							for(Cell cell : feat.getCells()) {
								// Obtenir le contenu de la case
								currentContent = cell.getContent();
								// Supprime tous les problemes pour le js
								currentContent = currentContent.replaceAll("\n", "");
								currentContent = currentContent.replaceAll("'", "`");
								
								if(!listContents.contains(currentContent)) {
									// Ajout dans la liste le contenu de la case courante
									listContents.add(currentContent);
								}
							}
							// Ajout dans la map le feature et sa liste de contenus disponible
							feat_content.put(featName, listContents);
						}
					}
				}
			}
		}
		return feat_content;
	}
	
	/**
	 * fucntion for translate the type PCM in type for HTML
	 * @param str
	 * @return String
	 */
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
			str = "radio";
			break;
		default:
			str = "text";
		}
		return str;
	}

	/**
	 * function for find the best type for each feature
	 * @param allTypes
	 * @return Map<String, String>
	 */
	public Map<String, String> getBestTypeForEachFeature(Map<String, List<String>> allTypes) {

		Map<String, Integer> nbOccurrence = new LinkedHashMap<String, Integer>();
		Map<String, String> bestTypes = new LinkedHashMap<String, String>();
		Integer max = 0;
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
				}
			}
			
			// Cherche le type dominant dans la map nbOccurrence en fonction de sa valeur, cad son nombre d'occurrence
			String bestType = "";
			for (Entry<String, Integer> entry2 : nbOccurrence.entrySet()) {
				String currentFeat = entry2.getKey();
				Integer currentNb = entry2.getValue();
				
				// Si un nb est plus grand qu'un max, alors mise a jour du max et du string bestType
				if (max < currentNb) {
					max = currentNb;
					bestType = currentFeat;
				}
			}
			// Supprime les sauts de ligne pour les key et pour value le type input html associe a son feature
			bestTypes.put(feat.replaceAll("\n", ""), setTypeForHtml(bestType));
			// Reinitialisation du nbOccurence et max pour les prochains features a traiter
			nbOccurrence = new LinkedHashMap<String, Integer>();
			max = 0;
		}
		return bestTypes;
	}
}
