package de.europace.eli.api;

import de.europace.eli.ApiClient;
import de.europace.eli.model.ErgebnisListeResource;
import de.europace.eli.model.Vorgang;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for ErgebnislisterestcontrollerApi
 */
public class ErgebnislisterestcontrollerApiTest {

    private ErgebnislisterestcontrollerApi api;

    @Before
    public void setup() {
        api = new ApiClient().buildClient(ErgebnislisterestcontrollerApi.class);
    }

    
    /**
     * getFinanzierungsVorschlaege
     *
     * 
     */
    @Test
    public void getFinanzierungsVorschlaegeUsingPOSTTest() {
        Vorgang erfassteDaten = null;
        Boolean alternativen = null;
        // ErgebnisListeResource response = api.getFinanzierungsVorschlaegeUsingPOST(erfassteDaten, alternativen);

        // TODO: test validations
    }
    
}
