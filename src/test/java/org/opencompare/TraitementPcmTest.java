package org.opencompare;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMElement;
import org.opencompare.api.java.PCMFactory;
import org.opencompare.api.java.Value;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;
import org.opencompare.api.java.util.PCMVisitor;
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

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraitementPcmTest {
	private TraitementPcm traitementpcm;
	private File file;
	
	@Before
	public void before() throws IOException{
		this.file = new File("pcms/Comparison_between_Argentine_provinces_and_countries_by_GDP_(PPP)_per_capita_0.pcm");
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
		traitementpcm.setAllTypesValue(map);
		assertEquals(map, traitementpcm.getAllTypesValue());
	}

	@Test
    public void GetAndSetAllContentsCell() throws IOException {
		List<String> list1 = new ArrayList();
		List<String> list2 = new ArrayList();
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
		traitementpcm.setAllContentsCell(map);
		assertEquals(map, traitementpcm.getAllContentsCell());
	}
	
	@Test
    public void GetAndSetContentsTypeMultiple() throws IOException {
		List<String> list1 = new ArrayList();
		List<String> list2 = new ArrayList();
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
    public void contentsTypeMultiple() throws IOException {
		// TODO
	}

  @Test
    public void testWhenFileExistsGetPcmIsNotNull() throws IOException {
        Assert.assertNotNull(traitementpcm.getPcm());
        Assert.assertEquals("Comparison_between_Argentine_provinces_and_countries_by_GDP_(PPP)_per_capita", traitementpcm.getPcm().getName());
    }
    
    @Test
    public void testWhenFileExistsGetPcmNameIsNotNull() throws IOException {
        Assert.assertNotNull(traitementpcm.getNamePcm());
        // et verifie que le nom du pcm est correct
        Assert.assertEquals("Comparison between Argentine provinces and countries by GDP (PPP) per capita", traitementpcm.getNamePcm());
   }

   @Test 
  public void assertValueToType() {
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
       
       this.assertValueToType(
       		new org.opencompare.api.java.impl.value.DimensionImpl(null), 
       		"dimension"
       );
   }
   
 	 
 	 
   @Test(expected = UnsupportedOperationException.class)
   public void testUnknownValueType() {
	 
   	traitementpcm.valueToType(new Value() {
		
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
   
         final String result =
                 traitementpcm.valueToType(value);
          Assert.assertEquals(expectedResult, result);
      }
     
       @Test
       public void setConditionalTypeHtml() {
       	
       	final String result = traitementpcm.setTypeHtml("conditional");
       	Assert.assertEquals("text", result);
       }
       
      
       @Test (expected = NullPointerException.class)
       public void setTypeHtmlnull(){
    	  traitementpcm.setTypeHtml(null);
       }
     }



