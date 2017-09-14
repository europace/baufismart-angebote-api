package de.europace.eli.api;

import de.europace.eli.ApiClient;
import de.europace.eli.model.ErfassteDaten;
import de.europace.eli.model.ErgebnisListeResource;
import de.europace.eli.model.Error;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ErgebnisListeRestControllerApi
 */
public class ErgebnisListeRestControllerApiTest {

    private ErgebnisListeRestControllerApi api;

    @Before
    public void setup() {
        api = new ApiClient().buildClient(ErgebnisListeRestControllerApi.class);
    }

    
    /**
     * getFinanzierungsVorschlaege
     *
     * 
     */
    @Test
    public void getFinanzierungsVorschlaegeUsingGETTest() {
        String vorgangsNummer = null;
        Boolean alternativen = null;
        // ErgebnisListeResource response = api.getFinanzierungsVorschlaegeUsingGET(vorgangsNummer, alternativen);

        // TODO: test validations
    }

    /**
     * getFinanzierungsVorschlaege
     *
     * 
     *
     * This tests the overload of the method that uses a Map for query parameters instead of
     * listing them out individually.
     */
    @Test
    public void getFinanzierungsVorschlaegeUsingGETTestQueryMap() {
        String vorgangsNummer = null;
        ErgebnisListeRestControllerApi.GetFinanzierungsVorschlaegeUsingGETQueryParams queryParams = new ErgebnisListeRestControllerApi.GetFinanzierungsVorschlaegeUsingGETQueryParams()
            .alternativen(null);
        // ErgebnisListeResource response = api.getFinanzierungsVorschlaegeUsingGET(vorgangsNummer, queryParams);

    // TODO: test validations
    }
    
    /**
     * getFinanzierungsVorschlaege
     *
     * 
     */
    @Test
    public void getFinanzierungsVorschlaegeUsingPOSTTest() {
        ErfassteDaten erfassteDaten = null;
        Boolean alternativen = null;
        // ErgebnisListeResource response = api.getFinanzierungsVorschlaegeUsingPOST(erfassteDaten, alternativen);

        // TODO: test validations
    }

    /**
     * getFinanzierungsVorschlaege
     *
     * 
     *
     * This tests the overload of the method that uses a Map for query parameters instead of
     * listing them out individually.
     */
    @Test
    public void getFinanzierungsVorschlaegeUsingPOSTTestQueryMap() {
        ErfassteDaten erfassteDaten = null;
        ErgebnisListeRestControllerApi.GetFinanzierungsVorschlaegeUsingPOSTQueryParams queryParams = new ErgebnisListeRestControllerApi.GetFinanzierungsVorschlaegeUsingPOSTQueryParams()
            .alternativen(null);
        // ErgebnisListeResource response = api.getFinanzierungsVorschlaegeUsingPOST(erfassteDaten, queryParams);

    // TODO: test validations
    }
    
}
