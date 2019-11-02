package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {
    Varasto alkusaldovarasto;
    Varasto alkusaldollinen;
    Varasto varasto;
    Varasto kayttokelvoton;
    Varasto kayttokelvotonAlkuSaldoTilavuus;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        alkusaldovarasto = new Varasto(10,3);
        varasto = new Varasto(10);
        kayttokelvoton = new Varasto(-1);
        alkusaldollinen = new Varasto(10, -1);
        kayttokelvotonAlkuSaldoTilavuus = new Varasto(-1,5);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test

    public void varastoLiikaa(){
        varasto.lisaaVarastoon(11);
        assertEquals(0, varasto.paljonkoMahtuu(),vertailuTarkkuus);
    }

    @Test

    public void lisaaVahemmanKuinNolla(){
        varasto.lisaaVarastoon(-1);
        assertEquals(10, varasto.paljonkoMahtuu(),vertailuTarkkuus);
    }

    @Test
    
    public void kayttokelvotonVarasto(){
        assertEquals(0, kayttokelvoton.paljonkoMahtuu(),vertailuTarkkuus);
    }

    @Test

    public void otaVahemmanKuinNolla(){
        varasto.otaVarastosta(-1);
        assertEquals(10, varasto.paljonkoMahtuu(),vertailuTarkkuus);
    }

    @Test

    public void otaYliSaldo(){
        varasto.lisaaVarastoon(4.0);
        varasto.otaVarastosta(5.0);
        assertEquals(10, varasto.paljonkoMahtuu(),vertailuTarkkuus);
    }

    @Test

    public void kayttokelvotonAlkuSaldoVarasto(){
        assertEquals(0, kayttokelvotonAlkuSaldoTilavuus.getTilavuus(),vertailuTarkkuus);
    }

    @Test

    public void alkuSaldoAlleNolla(){
        assertEquals(0, alkusaldollinen.getSaldo(),vertailuTarkkuus);
    }

    @Test

    public void alkuSaldoToimii(){
        assertEquals(7, alkusaldovarasto.paljonkoMahtuu(),vertailuTarkkuus);
    }

    @Test

    public void toStringTesti(){
        assertEquals("saldo = 3.0, vielä tilaa 7.0", alkusaldovarasto.toString());
    }
}