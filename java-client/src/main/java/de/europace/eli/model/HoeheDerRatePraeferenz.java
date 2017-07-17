/*
 * Ergebnislisten API
 * Ein Service um eine Ergebnisliste mit Finanzierungsvorschlägen zu ermitteln.
 *
 * OpenAPI spec version: 0.1
 * Contact: helpdesk@europace2.de
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package de.europace.eli.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

/**
 * HoeheDerRatePraeferenz
 */

public class HoeheDerRatePraeferenz {
  @JsonProperty("betrag")
  private BigDecimal betrag = null;

  @JsonProperty("praeferenzAuswahl")
  private String praeferenzAuswahl = null;

  @JsonProperty("zusatzlicheKommentare")
  private String zusatzlicheKommentare = null;

  public HoeheDerRatePraeferenz betrag(BigDecimal betrag) {
    this.betrag = betrag;
    return this;
  }

   /**
   * Get betrag
   * @return betrag
  **/
  @ApiModelProperty(example = "null", value = "")
  public BigDecimal getBetrag() {
    return betrag;
  }

  public void setBetrag(BigDecimal betrag) {
    this.betrag = betrag;
  }

  public HoeheDerRatePraeferenz praeferenzAuswahl(String praeferenzAuswahl) {
    this.praeferenzAuswahl = praeferenzAuswahl;
    return this;
  }

   /**
   * Erlaubt sind: MEINE_MIETAUSGABEN_VON_HEUTE,BETRAG,MOEGLICHST_KLEIN,VERFUEGBARES_EINKOMMEN_AUSSCHOEPFEN,EGAL. Neue Ausprägungen können kurzfristig hinzukommen. Der Client muss daher mit unbekannten Werten umgehen können.
   * @return praeferenzAuswahl
  **/
  @ApiModelProperty(example = "null", value = "Erlaubt sind: MEINE_MIETAUSGABEN_VON_HEUTE,BETRAG,MOEGLICHST_KLEIN,VERFUEGBARES_EINKOMMEN_AUSSCHOEPFEN,EGAL. Neue Ausprägungen können kurzfristig hinzukommen. Der Client muss daher mit unbekannten Werten umgehen können.")
  public String getPraeferenzAuswahl() {
    return praeferenzAuswahl;
  }

  public void setPraeferenzAuswahl(String praeferenzAuswahl) {
    this.praeferenzAuswahl = praeferenzAuswahl;
  }

  public HoeheDerRatePraeferenz zusatzlicheKommentare(String zusatzlicheKommentare) {
    this.zusatzlicheKommentare = zusatzlicheKommentare;
    return this;
  }

   /**
   * Get zusatzlicheKommentare
   * @return zusatzlicheKommentare
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getZusatzlicheKommentare() {
    return zusatzlicheKommentare;
  }

  public void setZusatzlicheKommentare(String zusatzlicheKommentare) {
    this.zusatzlicheKommentare = zusatzlicheKommentare;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HoeheDerRatePraeferenz hoeheDerRatePraeferenz = (HoeheDerRatePraeferenz) o;
    return Objects.equals(this.betrag, hoeheDerRatePraeferenz.betrag) &&
        Objects.equals(this.praeferenzAuswahl, hoeheDerRatePraeferenz.praeferenzAuswahl) &&
        Objects.equals(this.zusatzlicheKommentare, hoeheDerRatePraeferenz.zusatzlicheKommentare);
  }

  @Override
  public int hashCode() {
    return Objects.hash(betrag, praeferenzAuswahl, zusatzlicheKommentare);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HoeheDerRatePraeferenz {\n");
    
    sb.append("    betrag: ").append(toIndentedString(betrag)).append("\n");
    sb.append("    praeferenzAuswahl: ").append(toIndentedString(praeferenzAuswahl)).append("\n");
    sb.append("    zusatzlicheKommentare: ").append(toIndentedString(zusatzlicheKommentare)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}
