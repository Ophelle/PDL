package org.opencompare;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
/**
 * class GenerationHtmlTest
 * @author PDL: Mendes Ophélie, Dramé Oumar, Le Quang, Baouz Khaled and Moussa Lydia
 *
 */
public class GenerationHtmlTest{
	
	File file;
	TraitementPcm trait;
	GenerationHtml generator;
	
	public void test() throws IOException{
		this.file = new File("pcms/Comparison_(grammar)_0.pcm");
		this.trait = new TraitementPcm(file);
		this.generator = new GenerationHtml(trait, "pcms/testHtml1.ftl" , "pcms/testHtml1.html");
	}
	
	@Test
	public void GetAndSetOutput() throws IOException{
		test();
		assertEquals("pcms/testHtml1.html", generator.getOutput());
		generator.setOutput("testPrototype/testHtml1.html");
		assertEquals("testPrototype/testHtml1.html", generator.getOutput());
	}
	
	@Test
	public void GetAndSetInput() throws IOException{
		test();
		assertEquals("pcms/testHtml1.ftl", generator.getInput());
		generator.setInput("testPrototype/testHtml1.ftl");
		assertEquals("testPrototype/testHtml1.ftl", generator.getInput());
	}
	
	@Test
	public void GetAndSetTraitPcm() throws IOException{
		test();
		assertEquals(trait, generator.getTraitPcm());
		File fileTest = new File("pcms/Comparison_of_ad_servers_0.pcm");
		TraitementPcm test1 = new TraitementPcm(fileTest);
		generator.setTraitPcm(test1);
		assertEquals(test1, generator.getTraitPcm());
	}

}
