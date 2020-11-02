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

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void toStringToimii() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVarastonSaldoNolla() {
        Varasto testivarasto = new Varasto(-2);

        assertEquals(0, testivarasto.getTilavuus(), vertailuTarkkuus);

        Varasto testivarasto2 = new Varasto(20);

        assertEquals(20, testivarasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVarastonSaldolla() {
        Varasto testivarasto = new Varasto(-2, 2);

        assertEquals(0.0, testivarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(-2, testivarasto.getSaldo(), vertailuTarkkuus);

        Varasto testivarasto2 = new Varasto(20, 2);

        assertEquals(20, testivarasto2.getTilavuus(), vertailuTarkkuus);
        assertEquals(2, testivarasto2.getSaldo(), vertailuTarkkuus);

        Varasto testivarasto3 = new Varasto(20, 21);

        assertEquals(20, testivarasto3.getTilavuus(), vertailuTarkkuus);
        assertEquals(20, testivarasto3.getSaldo(), vertailuTarkkuus);

        Varasto testivarasto4 = new Varasto(20, -2);

        assertEquals(20, testivarasto4.getTilavuus(), vertailuTarkkuus);
        assertEquals(-2, testivarasto4.getSaldo(), vertailuTarkkuus);
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

        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla 0
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);

        varasto.lisaaVarastoon(-2);

        // vapaata tilaa pitäisi vielä olla 0
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);

        double saatuMaara2 = varasto.otaVarastosta(10);

        assertEquals(6, saatuMaara2, vertailuTarkkuus);

        double saatuMaara3 = varasto.otaVarastosta(-2);

        assertEquals(0, saatuMaara3, vertailuTarkkuus);

    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}
