package org.opencompare;

import org.junit.Assert;
import org.junit.Test;
import org.opencompare.api.java.PCMElement;
import org.opencompare.api.java.PCMFactory;
import org.opencompare.api.java.Value;
import org.opencompare.api.java.util.PCMVisitor;
import org.opencompare.api.java.value.BooleanValue;
import org.opencompare.model.impl.BooleanValueImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Moussa Lydia
 */
public class TraitementPcmTest {

    @Test
    public void testWhenFileExistsGetPcmIsNotNull() throws IOException {
        // si le fichier pcm exist
        final File file = new File("pcms/Comparison_between_Argentine_provinces_and_countries_by_GDP_(PPP)_per_capita_0.pcm");
        // et que je crée le TraitementPcm
        final TraitementPcm traitementPcm = new TraitementPcm(file);
        // alors getPcm ne doit pas être null
        Assert.assertNotNull(traitementPcm.getPcm());
        Assert.assertEquals("Comparison_between_Argentine_provinces_and_countries_by_GDP_(PPP)_per_capita", traitementPcm.getPcm().getName());
    }
    
    @Test
    public void testWhenFileExistsGetPcmNameIsNotNull() throws IOException {
        // si le fichier pcm exist
        final File file = new File("pcms/Comparison_between_Argentine_provinces_and_countries_by_GDP_(PPP)_per_capita_0.pcm");
        // et que je crée le TraitementPcm
        final TraitementPcm traitementPcm = new TraitementPcm(file);
        // alors getPcm ne doit pas être null
        Assert.assertNotNull(traitementPcm.getNamePcm());
        // et verifie que le nom du pcm est correct
        Assert.assertEquals("Comparison between Argentine provinces and countries by GDP (PPP) per capita", traitementPcm.getNamePcm());
    }
    

    @Test
    public void testAllValueToTypes() {
        this.assertValueToType(
        		new org.opencompare.api.java.impl.value.BooleanValueImpl(null), 
        		"boolean"
        );

        this.assertValueToType(
        		new org.opencompare.api.java.impl.value.IntegerValueImpl(null), 
        		"integer"
        );

        this.assertValueToType(
        		new org.opencompare.api.java.impl.value.NotApplicableImpl(null), 
        		"string"
        );

        this.assertValueToType(
        		new org.opencompare.api.java.impl.value.NotAvailableImpl(null), 
        		"string"
        );

        this.assertValueToType(
        		new org.opencompare.api.java.impl.value.RealValueImpl(null), 
        		"real"
        );
        
        this.assertValueToType(
        		 null, 
        		"string"
        );
        
        // Be careful, maybe dimension should return "dimension" !
        this.assertValueToType(
        		new org.opencompare.api.java.impl.value.DimensionImpl(null), 
        		"dimension"
        );
    }
    

    @Test(expected = UnsupportedOperationException.class)
    public void testUnknownValueType() {
    	TraitementPcm.valueToType(new Value() {
			@Override
			public PCMElement clone(PCMFactory factory) {
				return null;
			}
			
			@Override
			public void accept(PCMVisitor visitor) {				
			}
		});
    }
    
    private void assertValueToType(Value value, String expectedResult) {
        final String result =
                TraitementPcm.valueToType(value);
        Assert.assertEquals(expectedResult, result);
    }
    
    @Test
    public void setConditionalTypeHtml() {
    	final String result = TraitementPcm.setTypeHtml("conditional");
    	Assert.assertEquals("text", result);
    }
    
    @Test (expected = NullPointerException.class)
    public void setTypeHtmlnull(){
 	  TraitementPcm.setTypeHtml(null);
    }
    
    @Test
    public void testGetBestTypes() {
    	final  Map<String, String> result = TraitementPcm.getBestTypes(new HashMap<>());
    	Assert.assertEquals(new HashMap<>(), result);
    }
    
    @Test
    public void testGetBestTypesWithOneFeatureOneType() {
    	Map<String, List<String>> map = new HashMap<>(); 
    	List<String> list = new ArrayList<>();
    	list.add("conditional");
    	map.put("feature1", list);
    	final  Map<String, String> result = TraitementPcm.getBestTypes(map);
    	
    	Map<String, String> expected = new HashMap<>(); 
    	expected.put("feature1", "text");
    	Assert.assertEquals(expected, result);
    }
}
