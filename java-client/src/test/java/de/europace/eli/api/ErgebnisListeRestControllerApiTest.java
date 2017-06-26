package de.europace.eli.api;

import de.europace.eli.ApiClient;
import de.europace.eli.model.ErgebnisListeResource;
import de.europace.eli.model.Finanzbedarf;
import de.europace.eli.model.Vorgang;
import de.europace.eli.model.Vorhaben;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * API tests for ErgebnisListeRestControllerApi
 */
public class ErgebnisListeRestControllerApiTest {

  private ErgebnisListeRestControllerApi api;

  @Before
  public void setup() {
    api = new ApiClient("oauth2", "baufismart_username", "baufismart_password")
        .buildClient(ErgebnisListeRestControllerApi.class);
  }

  /**
   * getFinanzierungsVorschlaege
   */
  @Test
  public void getFinanzierungsVorschlaegeUsingPOSTTest() {
    Vorgang erfassteDaten = new Vorgang();
    erfassteDaten.setVorhaben(new Vorhaben());
    erfassteDaten.getVorhaben().setFinanzbedarf(new Finanzbedarf());
    erfassteDaten.getVorhaben().getFinanzbedarf().setKaufpreis(BigDecimal.valueOf(100_000L));
    ErgebnisListeResource response = api.getFinanzierungsVorschlaegeUsingPOST(erfassteDaten);

    assert response != null;
    // System.out.println(response);
  }
}
