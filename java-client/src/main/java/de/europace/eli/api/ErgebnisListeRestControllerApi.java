package de.europace.eli.api;

import de.europace.eli.ApiClient;

import de.europace.eli.model.ErgebnisListeResource;
import de.europace.eli.model.Vorgang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;


public interface ErgebnislisterestcontrollerApi extends ApiClient.Api {


  /**
   * getFinanzierungsVorschlaege
   * 
   * @param erfassteDaten erfassteDaten (required)
   * @param alternativen alternativen (optional, default to false)
   * @return ErgebnisListeResource
   */
  @RequestLine("POST /finanzierungsvorschlaege?alternativen={alternativen}")
  @Headers({
    "Content-type: application/json",
    "Accept: application/json;charset&#x3D;UTF-8",
  })
  ErgebnisListeResource getFinanzierungsVorschlaegeUsingPOST(Vorgang erfassteDaten, @Param("alternativen") Boolean alternativen);
}
