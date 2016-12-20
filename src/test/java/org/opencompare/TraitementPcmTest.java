package org.opencompare;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMElement;
import org.opencompare.api.java.PCMFactory;
import org.opencompare.api.java.Value;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;
import org.opencompare.api.java.util.PCMVisitor;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class TraitementPcmTest
 * @author PDL: Mendes Ophélie, Dramé Oumar, Le Quang, Baouz Khaled and Moussa Lydia
 *
 */
public class TraitementPcmTest {
	private TraitementPcm traitementpcm;
	private File file;

	@Before
	public void before() throws IOException {
		this.file = new File(
				"pcms/Comparison_between_Argentine_provinces_and_countries_by_GDP_(PPP)_per_capita_0.pcm");
		this.traitementpcm = new TraitementPcm(file);
	}

	@Test
	public void GetAndSetAllTypesValue() throws IOException {
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		list1.add("integer");
		list1.add("integer");
		list1.add("integer");
		list1.add("integer");
		list1.add("integer");
		list1.add("integer");
		list1.add("integer");
		list1.add("integer");
		list1.add("integer");
		list1.add("string");
		list1.add("string");
		list2.add("integer");
		list2.add("integer");
		list2.add("multiple");
		list2.add("multiple");
		list2.add("multiple");
		list2.add("multiple");
		list2.add("string");
		list2.add("string");
		list2.add("string");
		list2.add("string");
		list2.add("string");
		map.put("Ecran", list1);
		map.put("Puissance", list1);
		traitementpcm.setAllTypesOfFeature(map);
		assertEquals(map, traitementpcm.getAllTypesOfFeature());
	}

	@Test
	public void GetAndSetAllContentsCell() throws IOException {
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		list1.add("10 pouces");
		list1.add("10 pouces");
		list1.add("10 pouces");
		list1.add("10 pouces");
		list1.add("10 pouces");
		list1.add("15 pouces");
		list1.add("15 pouces");
		list1.add("20");
		list1.add("20");
		list1.add("20");
		list1.add("20");
		list2.add("7 cores");
		list2.add("7 cores");
		list2.add("7 cores");
		list2.add("7 cores");
		list2.add("7 cores");
		list2.add("7 cores");
		list2.add("4");
		list2.add("4");
		list2.add("4");
		list2.add("10");
		list2.add("10");
		map.put("Ecran", list1);
		map.put("Puissance", list1);
		traitementpcm.setAllContentsOfEachCell(map);
		;
		assertEquals(map, traitementpcm.getAllContentsOfEachCell());
	}

	@Test
	public void GetAndSetContentsTypeMultiple() throws IOException {
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		list1.add("10 pouces");
		list1.add("10 pouces");
		list1.add("10 pouces");
		list1.add("10 pouces");
		list1.add("10 pouces");
		list1.add("15 pouces");
		list1.add("15 pouces");
		list1.add("20");
		list1.add("20");
		list1.add("20");
		list1.add("20");
		list2.add("7 cores");
		list2.add("7 cores");
		list2.add("7 cores");
		list2.add("7 cores");
		list2.add("7 cores");
		list2.add("7 cores");
		list2.add("4");
		list2.add("4");
		list2.add("4");
		list2.add("10");
		list2.add("10");
		map.put("Ecran", list1);
		map.put("Puissance", list1);
		traitementpcm.setContentsTypeMultiple(map);
		assertEquals(map, traitementpcm.getContentsTypeMultiple());
	}

	@Test
	public void testWhenFileExistsGetPcmIsNotNull() throws IOException {
		Assert.assertNotNull(traitementpcm.getPcm());
		Assert.assertEquals(
				"Comparison_between_Argentine_provinces_and_countries_by_GDP_(PPP)_per_capita",
				traitementpcm.getPcm().getName());
	}

	@Test
	public void testWhenFileExistsGetPcmNameIsNotNull() throws IOException {
		Assert.assertNotNull(traitementpcm.getNamePcm());
		// et verifie que le nom du pcm est correct
		Assert.assertEquals(
				"Comparison between Argentine provinces and countries by GDP (PPP) per capita",
				traitementpcm.getNamePcm());
	}

	@Test
	public void assertValueToType() {
		this.assertValueToType(
				new org.opencompare.api.java.impl.value.BooleanValueImpl(null),
				"boolean");

		this.assertValueToType(
				new org.opencompare.api.java.impl.value.IntegerValueImpl(null),
				"integer");

		this.assertValueToType(
				new org.opencompare.api.java.impl.value.NotApplicableImpl(null),
				"notapplicable");

		this.assertValueToType(
				new org.opencompare.api.java.impl.value.NotAvailableImpl(null),
				"notavailable");

		this.assertValueToType(
				new org.opencompare.api.java.impl.value.RealValueImpl(null),
				"real");

		this.assertValueToType(null, "");

		this.assertValueToType(
				new org.opencompare.api.java.impl.value.DimensionImpl(null),
				"dimension");
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUnknownValueType() {

		traitementpcm.valueToString(new Value() {

			@Override
			public PCMElement clone(PCMFactory factory) {
				return null;
			}

			@Override
			public void accept(PCMVisitor visitor) {

			}
		});
	}

	public void assertValueToType(Value value, String expectedResult) {

		final String result = traitementpcm.valueToString(value);
		Assert.assertEquals(expectedResult, result);
	}

	@Test
	public void setConditionalTypeHtml() {

		final String result = traitementpcm.setTypeForHtml("conditional");
		Assert.assertEquals("text", result);
	}

	@Test(expected = NullPointerException.class)
	public void setTypeHtmlnull() {
		traitementpcm.setTypeForHtml(null);
	}

	@Test
	public void testGetBestTypes() {
		final Map<String, String> result = traitementpcm
				.getBestTypeForEachFeature(new HashMap<>());
		Assert.assertEquals(new HashMap<>(), result);
	}

	@Test
	public void testGetBestTypesWithOneFeatureOneType() {
		Map<String, List<String>> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		list.add("conditional");
		map.put("feature1", list);
		final Map<String, String> result = traitementpcm
				.getBestTypeForEachFeature(map);

		Map<String, String> expected = new HashMap<>();
		expected.put("feature1", "text");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testGetBestTypesWithOneFeatureMultipleTypes() {
		Map<String, List<String>> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		list.add("conditional");
		list.add("boolean");
		list.add("conditional");
		map.put("feature1", list);
		final Map<String, String> result = traitementpcm
				.getBestTypeForEachFeature(map);

		Map<String, String> expected = new HashMap<>();
		expected.put("feature1", "text");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testGetBestTypesWithMultipleFeaturesMultipleTypes() {
		Map<String, List<String>> map = new HashMap<>();
		List<String> typesOfFeature1 = new ArrayList<>();
		typesOfFeature1.add("conditional");
		typesOfFeature1.add("boolean");
		typesOfFeature1.add("conditional");
		map.put("feature1", typesOfFeature1);

		List<String> typesOfFeature2 = new ArrayList<>();
		typesOfFeature2.add("real");
		typesOfFeature2.add("boolean");
		typesOfFeature2.add("conditional");
		typesOfFeature2.add("string");
		typesOfFeature2.add("real");
		map.put("feature2", typesOfFeature2);
		final Map<String, String> result = traitementpcm
				.getBestTypeForEachFeature(map);

		Map<String, String> expected = new HashMap<>();
		expected.put("feature1", "text");
		expected.put("feature2", "number");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testcontentsTypeMultiple() throws Exception {
		File file = new File(
				"pcms/Comparison_of_Microsoft_Windows_versions_18.pcm");
		PCMLoader loader = new KMFJSONLoader();
		PCM pcm;
		List<Feature> listFeatures;
		pcm = loader.load(file).get(0).getPcm();
		pcm = loader.load(file).get(0).getPcm();
		listFeatures = pcm.getConcreteFeatures();
		List<String> listMultiple = new ArrayList<String>();
		listMultiple.add("COMMAND.COM");
		listMultiple.add("CMD.EXE");
		listMultiple.add("PowerShell (optional)");
		listMultiple.add("PowerShell v3.0");
		listMultiple.add("PowerShell v2.0");
		listMultiple.add("PowerShell v4.0");

		Map<String, List<String>> listMultipleemap = new HashMap<String, List<String>>();
		listMultipleemap.put("Command-line interpreter", listMultiple);
		assertEquals(
				listMultiple,
				traitementpcm.contentsTypeMultiple(listFeatures).get(
						"Command-line interpreter"));
	}

}
