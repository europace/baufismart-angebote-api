package de.europace.eli.api;

import de.europace.eli.ApiClient;
import de.europace.eli.EncodingUtils;

import de.europace.eli.model.ErfassteDaten;
import de.europace.eli.model.ErgebnisListeResource;
import de.europace.eli.model.Error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;


public interface ErgebnisListeRestControllerApi extends ApiClient.Api {


  /**
   * getFinanzierungsVorschlaege
   * 
    * @param vorgangsNummer vorgangsNummer (required)
    * @param alternativen alternativen (optional, default to false)
   * @return ErgebnisListeResource
   */
  @RequestLine("GET /v1/finanzierungsvorschlaege/{vorgangsNummer}?alternativen={alternativen}")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json;charset&#x3D;UTF-8",
  })
  ErgebnisListeResource getFinanzierungsVorschlaegeUsingGET(@Param("vorgangsNummer") String vorgangsNummer, @Param("alternativen") Boolean alternativen);

  /**
   * getFinanzierungsVorschlaege
   * 
   * Note, this is equivalent to the other <code>getFinanzierungsVorschlaegeUsingGET</code> method,
   * but with the query parameters collected into a single Map parameter. This
   * is convenient for services with optional query parameters, especially when
   * used with the {@link GetFinanzierungsVorschlaegeUsingGETQueryParams} class that allows for
   * building up this map in a fluent style.
   * @param vorgangsNummer vorgangsNummer (required)
   * @param queryParams Map of query parameters as name-value pairs
   *   <p>The following elements may be specified in the query map:</p>
   *   <ul>
   *   <li>alternativen - alternativen (optional, default to false)</li>
   *   </ul>
   * @return ErgebnisListeResource
   */
  @RequestLine("GET /v1/finanzierungsvorschlaege/{vorgangsNummer}?alternativen={alternativen}")
  @Headers({
  "Content-Type: application/json",
  "Accept: application/json;charset&#x3D;UTF-8",
  })
  ErgebnisListeResource getFinanzierungsVorschlaegeUsingGET(@Param("vorgangsNummer") String vorgangsNummer, @QueryMap(encoded=true) Map<String, Object> queryParams);

  /**
   * A convenience class for generating query parameters for the
   * <code>getFinanzierungsVorschlaegeUsingGET</code> method in a fluent style.
   */
  public static class GetFinanzierungsVorschlaegeUsingGETQueryParams extends HashMap<String, Object> {
    public GetFinanzierungsVorschlaegeUsingGETQueryParams alternativen(final Boolean value) {
      put("alternativen", EncodingUtils.encode(value));
      return this;
    }
  }

  /**
   * getFinanzierungsVorschlaege
   * 
    * @param erfassteDaten erfassteDaten (required)
    * @param alternativen alternativen (optional, default to false)
   * @return ErgebnisListeResource
   */
  @RequestLine("POST /v1/finanzierungsvorschlaege?alternativen={alternativen}")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json;charset&#x3D;UTF-8",
  })
  ErgebnisListeResource getFinanzierungsVorschlaegeUsingPOST(ErfassteDaten erfassteDaten, @Param("alternativen") Boolean alternativen);

  /**
   * getFinanzierungsVorschlaege
   * 
   * Note, this is equivalent to the other <code>getFinanzierungsVorschlaegeUsingPOST</code> method,
   * but with the query parameters collected into a single Map parameter. This
   * is convenient for services with optional query parameters, especially when
   * used with the {@link GetFinanzierungsVorschlaegeUsingPOSTQueryParams} class that allows for
   * building up this map in a fluent style.
   * @param erfassteDaten erfassteDaten (required)
   * @param queryParams Map of query parameters as name-value pairs
   *   <p>The following elements may be specified in the query map:</p>
   *   <ul>
   *   <li>alternativen - alternativen (optional, default to false)</li>
   *   </ul>
   * @return ErgebnisListeResource
   */
  @RequestLine("POST /v1/finanzierungsvorschlaege?alternativen={alternativen}")
  @Headers({
  "Content-Type: application/json",
  "Accept: application/json;charset&#x3D;UTF-8",
  })
  ErgebnisListeResource getFinanzierungsVorschlaegeUsingPOST(ErfassteDaten erfassteDaten, @QueryMap(encoded=true) Map<String, Object> queryParams);

  /**
   * A convenience class for generating query parameters for the
   * <code>getFinanzierungsVorschlaegeUsingPOST</code> method in a fluent style.
   */
  public static class GetFinanzierungsVorschlaegeUsingPOSTQueryParams extends HashMap<String, Object> {
    public GetFinanzierungsVorschlaegeUsingPOSTQueryParams alternativen(final Boolean value) {
      put("alternativen", EncodingUtils.encode(value));
      return this;
    }
  }
}
