package org.opencompare;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import freemarker.template.TemplateException;

public class GenerationHtmlTest{
	
	File file;
	TraitementPcm trait;
	GenerationHtml generator;
	
	public void test() throws IOException, TemplateException {
		this.file = new File("pcms/example0.pcm");
		this.trait = new TraitementPcm(file);
		this.generator = new GenerationHtml(trait, "pcms/testHtml1.ftl" , "pcms/testHtml1.html");
	}
	
	@Test
	public void testOutput() {
		
	}
	
	@Test
	public void testInput() {
		generator.getInput();
		//generator.setInput();
		
	}
	
	@Test
	public void testTraitPcm() {
		
	}

}
