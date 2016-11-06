package tel_ran.yandex_api.translator.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tel_ran.yandex_api.translator.entities.Translation;

public class Tests {
	static String REQUEST = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+
				"<Translation code=\"200\" lang=\"en-he\"><text>שלום עולם!</text></Translation>";
	Translation trans;
	@Before
	public void setUp() throws Exception {
		trans = new Translation("Hello World!",REQUEST);
	}

	@Test
	public void testTranslation() {
		assertEquals("en-he",trans.getLangs(REQUEST));
		assertEquals("Hello World!",trans.getTextFrom());
		assertEquals("שלום עולם!",trans.getTextTo());
	}

}
