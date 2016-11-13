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
}
