# Angebote API

As an advisor you can find offers, compare, save and refresh them to get the best customer solution.

![advisor](https://img.shields.io/badge/-advisor-lightblue)
![mortgageLoan](https://img.shields.io/badge/-mortgageLoan-lightblue)

[![Authentication](https://img.shields.io/badge/Auth-OAuth2-green)](https://docs.api.europace.de/common/authentifizierung/authorization-api/)
[![Github](https://img.shields.io/badge/-Github-black?logo=github)](https://github.com/europace/baufismart-angebote-api)
[![GitHub release](https://img.shields.io/github/v/release/europace/baufismart-angebote-api)](https://github.com/europace/baufismart-angebote-api/releases)

[![Pattern](https://img.shields.io/badge/Pattern-Tolerant%20Reader-yellowgreen)](https://martinfowler.com/bliki/TolerantReader.html)

## Documentation

[![HTML](https://img.shields.io/badge/OAS-HTML_Doc-lightblue)](https://refined-github-html-preview.kidonng.workers.dev/europace/baufismart-angebote-api/raw/master/reference/index.html)
[![YAML](https://img.shields.io/badge/OAS-YAML-lightgrey)](https://raw.githubusercontent.com/europace/baufismart-angebote-api/master/angebote-openapi.yaml)
[![JSON](https://img.shields.io/badge/OAS-JSON-lightgrey)](https://raw.githubusercontent.com/europace/baufismart-angebote-api/master/angebote-openapi.json)

## Requirements

- authenticated as loan provider

## Quick Start

To test our APIs and your use cases as quickly as possible, we have created a [Postman Collection](https://github.com/europace/api-quickstart) for you.

### Authentication

Please use [![Authentication](https://img.shields.io/badge/Auth-OAuth2-green)](https://docs.api.europace.de/common/authentifizierung/authorization-api/) to get access to the APIs. The OAuth2 client requires the following scopes:

| Scope                               | API Use case                    |
|-------------------------------------|---------------------------------|
| `baufinanzierung:angebot:ermitteln` | to find offers and show details |
| `baufinanzierung:angebot:loeschen`  | to delete saved offers          |

# Angebote API versions and functionalities

| Use case                                                      | Version 1 | Version 2 | Version 3 |
|---------------------------------------------------------------|-----------|-----------|-----------|
| calculate new offers without case                             | ✅         | ✅         |           |
| calculate new offers with case                                | ✅         | ✅         | ✅         |
| get offer details (Unterlagen, Meldungen, ...)                |           | ✅         | ✅         |
| get calculation overviews (Haushaltsrechnung, Kondition, ...) |           |           | ✅         |
| save newly calculated offer (favorite)                        |           |           | ✅         |
| get all saved offers from case                                |           |           | ✅         |
| get details of saved offers from case                         |           |           | ✅         |
| delete saved offer in case                                    |           |           | ✅         |
| recalculate saved offer in case                               |           |           | ✅         |

## Offer lifecycle

- Fresh and recalculated offers are available for 60 min after calculation over the API.
- Saved offers are never deleted unless deleted by client.

# Documentation Version 3

## Use cases

- as advisor you can find offers and compare them to get the best customer solution
- as advisor or loan provider find prolongation offers
- as advisor save a newly calculated offer in a case
- as advisor delete a saved offer in a case
- as advisor recalculate saved offers
- as advisor save a refreshed offer in a case

As an advisor organisation or technology provider these functionalities may enable the building of a result list GUI similar to BaufiSmart.

## Use case find and recalculate offers <a name="findOffers"></a>

Finding offers can be controlled by several parameters.
If an empty body is given default parameters are used.

```json
{ 
  // default parameters 
  "ermitteln": true, // calculate fresh new offers
  "aktualisieren": true, // recalculate gemerkte (saved) offers in case
  "alternativen": false, // no alternative offers are generated
  "produktAnbieter": [],  // default: loan providers of calling partner are used
  "exkludierteProduktAnbieter": [], // default: no loan provider is excluded
  "provisionsAusgabe": false // provision is not calculated for better performance
}
```

```http
POST /v3/vorgaenge/{{case-id}}/ergebnisliste HTTP/1.1
Host: api.europace2.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

### sample response of newly created offer

The sample response with a fresh calculated offer.

```json
{
  "ergebnislisteId": "LAPF9B",
  "ergebnisliste": [
    {
      "darlehensSumme": 250000.00,
      "sollZins": 4.32000,
      "effektivZins": 4.44000,
      "darlehen": [
        {
          "id": "64799f25ce9d3daff2f7335a",
          "typ": "ANNUITAETEN_DARLEHEN",
          "sollZins": 4.32000,
          "effektivZins": 4.44000,
          "effektivZinsRelevanteKosten": {
            "grundbuchKosten": 535.00
          },
          "rateMonatlich": 1264.58,
          "darlehensBetrag": 250000.00,
          "auszahlungsBetrag": 250000.00,
          "zinsZahlungsBeginnAm": "2024-08-31",
          "zinsBindung": {
            "jahre": 10,
            "monate": 0,
            "restschuldNachZinsBindungsEnde": 195399.99,
            "summeZinsenInZinsBindung": 97149.59
          },
          "tilgung": {
            "anfaenglicheTilgung": 1.75000,
            "tilgungsBeginn": "2024-08-31",
            "sonderTilgungJaehrlich": 5.00000
          },
          "bereitstellung": {
            "bereitstellungsZinsfreieZeitInMonaten": 3,
            "bereitstellungsZins": 2.40000
          },
          "gesamtlaufzeitInMonaten": 347,
          "gesamtkosten": 437680.67,
          "auszahlungsDatum": "2024-07-31",
          "bearbeitungszeit": {
            "min": 3,
            "max": 10,
            "standVon": "2024-07-02",
            "bemerkung": "Neugeschäft 3 Tage\nNeugeschäft Individualkunden 10 Tage\nAuszahlung 3 Tage\nProlongation + KfW-Umwandlung 5 Tage\nEröffnung eines Darlehenskontos 1 Tag"
          },
          "summeZinsenUeberGesamtlaufzeit": 187680.67,
          "summeGebuehrenUeberGesamtlaufzeit": 0.00,
          "summeKontofuehrungsgebuehrenUeberGesamtlaufzeit": 0.00,
          "produktFeatures": [
            "Die Nichtabnahme von Darlehensteilen ist bis zu einem Betrag von 25.000 Euro gegen eine Gebühr von 1 % des nicht abgenommenen Betrags möglich. Der Betrag von 25.000 Euro ist nicht konto-, sondern auf die Gesamtfinanzierung bezogen. Die Mindestdarlehenssumme muss weiterhin eingehalten werden.",
            "Es sind maximal zwei kostenfreie Tilgungssatzwechsel pro Zinsbindung in einem Tilgungsbereich von 1,75 % bis 10 % möglich. Für weitere Wechsel des Tilgungssatzes werden zum Zeitpunkt des Wechsels separate Kosten berechnet.",
            "Eine Sondertilgung ist mehrmals im Kalenderjahr bis zur Höhe von 5 % der Ursprungsdarlehenssumme möglich. Der Mindestbetrag pro Sondertilgung beträgt 1.000 Euro. Im Falle einer Prolongation bleibt die Sondertilgungsmöglichkeit unverändert, d.h. 5 % der ursprünglichen Darlehenssumme, sofern als Leistungsbasis die ursprüngliche Darlehenssumme gewählt wurde. Wird als Leistungsbasis die aktuelle Restschuld gewählt, dann werden die 5 % entsprechend von der Restschuld berechnet."
          ]
        }
      ],
      "beleihung": [
        {
          "summe": 200000.00,
          "auslauf": 125.00000
        }
      ],
      "machbarkeit": "NICHT_MACHBAR",
      "annahmeFrist": "2024-07-17T23:59:59+02:00",
      "erzeugtAm": "2024-07-15T11:57:45.435+02:00",
      "bausparAngebote": [],
      "anpassungsStatus": "ANGEPASST",
      "elektronischeUnterlagenEinreichung": true,
      "vollstaendigkeitsStatus": "NICHT_VOLLSTAENDIG",
      "rateMonatlich": 1265,
      "produktFeatures": [],
      "_links": {
        "_self": {
          "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/ergebnisliste/LAPF9B/1"
        }
      }
    },
    ...
  ],  
  "_links": {
    "_self": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/ergebnisliste/LAPF9B"
    }
  }
```

### sample response of refreshed offer

A refreshed offer contains the details of the new offer and details of the saved offer within.
This way the result data can be compared.
Refreshed offers occur only in a result list if there are saved offers "gemerktesAngebot" in the case.

```json
{
  "ergebnislisteId": "Z4WQVT",
  "ergebnisliste": [
    {
      // offer details of refreshed offer with same fields as new offers
      "darlehensSumme": 250000.00,
      "sollZins": 4.32000,
      ....
      "gemerktesAngebot": { // start of saved offer
        "id": "66869f9c9134407467abfa14",
        "laufendeNummer": 11, // laufende Nummer in case
        "darlehensSumme": 250000,
        "sollZins": 4.32,
        "effektivZins": 4.44,
        "darlehen": [
          {
            "id": "64799f25ce9d3daff2f7335a",
            "typ": "ANNUITAETEN_DARLEHEN",
            "sollZins": 4.32,
            "effektivZins": 4.44,
            "effektivZinsRelevanteKosten": {
              "beratungsHonorar": 0,
              "grundbuchKosten": 535,
              "tilgungsErsatzProduktKosten": 0,
              "sonstigeKosten": 0,
              "wohnGebaeudeVersicherungsKosten": 0,
              "zusatzSicherheitsKosten": 0
            },
            "rateMonatlich": 1264.58,
            "darlehensBetrag": 250000,
            "auszahlungsBetrag": 250000,
            "produktAnbieter": {
              "produktAnbieterId": "MUSTERBANK",
              "partnerId": "PARTNER-ID",
              "name": "Bank AG",
              "_links": {
                "logo": {
                  "href": "https://www.europace2.de/produktanbieter-logos/logo/MUSTERBANK.svg"
                }
              }
            },
            "finanzierenderProduktAnbieter": {
              "produktAnbieterId": "MUSTERBANK",
              "partnerId": "PARTNER-ID",
              "name": "Bank AG",
              "_links": {
                "logo": {
                  "href": "https://www.europace2.de/produktanbieter-logos/logo/MUSTERBANK.svg"
                }
              }
            },
            "zinsZahlungsBeginnAm": "2024-08-31",
            "zinsBindung": {
              "jahre": 10,
              "restschuldNachZinsBindungsEnde": 195399.99,
              "summeZinsenInZinsBindung": 187680.67
            },
            "tilgung": {
              "anfaenglicheTilgung": 1.75,
              "tilgungsBeginn": "2024-08-31",
              "sonderTilgungJaehrlich": 5.0
            },
            "bereitstellung": {
              "bereitstellungsZinsfreieZeitInMonaten": 3,
              "bereitstellungsZins": 2.4
            },
            "gesamtlaufzeitInMonaten": 348,
            "auszahlungsDatum": "2024-07-31",
            "bearbeitungszeit": {
              "min": 3,
              "max": 10,
              "standVon": "2024-07-02",
              "bemerkung": "Neugeschäft 3 Tage\nNeugeschäft Individualkunden 10 Tage\nAuszahlung 3 Tage\nProlongation + KfW-Umwandlung 5 Tage\nEröffnung eines Darlehenskontos 1 Tag"
            },
            "kalkulatorischesLaufzeitEnde": "2053-06-30",
            "provisionKundenbetreuerAbsolut": 2500,
            "provisionKundenbetreuerRelativ": 1.0,
            "summeZinsenUeberGesamtlaufzeit": 187680.67,
            "summeGebuehrenUeberGesamtlaufzeit": 0,
            "summeKontofuehrungsgebuehrenUeberGesamtlaufzeit": 0
          }
        ],
        "beleihung": [
          {
            "produktAnbieter": {
              "produktAnbieterId": "MUSTERBANK",
              "partnerId": "PARTNER-ID",
              "name": "Bank AG",
              "_links": {
                "logo": {
                  "href": "https://www.europace2.de/produktanbieter-logos/logo/MUSTERBANK.svg"
                }
              }
            },
            "summe": 200000,
            "auslauf": 125.0
          }
        ],
        "machbarkeit": "NICHT_MACHBAR",
        "annahmeFrist": "2024-07-08T21:59:59Z",
        "bausparAngebote": [],
        "anpassungsStatus": "ANGEPASST",
        "elektronischeUnterlagenEinreichung": true,
        "vollstaendigkeitsStatus": "NICHT_VOLLSTAENDIG",
        "rateMonatlich": 1264.58,
        "provisionKundenbetreuerRelativ": 1.0,
        "produktFeatures": [],
        "basisAngebotsId": "66869f9c9134407467abfa14"
      },
      "_links": {
        "_self": {
          "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/ergebnisliste/Z4WQVT/15"
        }
      }
    }
  ],
  "_links": {
    "_self": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/ergebnisliste/Z4WQVT"
    }
  }
}
```


## Use case get offer details of single offer

```http
GET /v3/vorgaenge/{{case-id}}/ergebnisliste/{{ergebnislisteId}}/{{offer-number}}/ HTTP/1.1
Host: api.europace2.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

example-response:

```json
{
  "darlehensSumme": 250000.00,
  "sollZins": 4.32000,
  "effektivZins": 4.44000,
  "darlehen": [
    {
      "id": "64799f25ce9d3daff2f7335a",
      "typ": "ANNUITAETEN_DARLEHEN",
      ...
     },
      
  ..,
  "_links": {
    "berechnungsuebersichten": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/ergebnisliste/LAPF9B/1/berechnungsuebersichten"
    },
    "zahlungsplaene": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/ergebnisliste/LAPF9B/1/zahlungsplaene"
    },
    "meldungen": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/ergebnisliste/LAPF9B/1/meldungen"
    },
    "unterlagen": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/ergebnisliste/LAPF9B/1/unterlagen"
    },
    "provision": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/ergebnisliste/LAPF9B/1/provision?repeat=0"
    },
    "_self": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/ergebnisliste/LAPF9B/1"
    }
  }
}
```

## Use case get details (Meldungen) of offer

```http
GET /v3/vorgaenge/{{case-id}}/ergebnisliste/{{ergebnislisteId}}/{{offer-number}}/meldungen HTTP/1.1
Host: api.europace2.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

example-response:

```json
{
  "meldungen": [
    {
      "text": "Pro mithaftendem Grundbuchblatt fallen zusätzlich 10 Euro Grundbucheintragungskosten an. Sollte es mehr als ein Grundbuchblatt geben, erfasse diese Kosten bitte zusätzlich bei den Grundbucheintragungskosten in den Zusatzangeben der Immobilie.",
      "code": "pe.ingdiba.machbarkeit.beleihungsobjekt.grundbuchblaetter.hinweis",
      "produktAnbieterId": "MUSTERBANK",
      "meldungsKategorie": "MACHBARKEITS_HINWEIS",
      "bereichsZuordnung": "VORHABEN"
    },
    {
      "text": "Das Geburtsland der Person Martina Betram wurde nicht angegeben.",
      "code": "pe.ingdiba.vorbehaltsmeldung.antragsteller.geburtsLandIsoCode",
      "produktAnbieterId": "MUSTERBANK",
      "meldungsKategorie": "MACHBARKEIT_UNTER_VORBEHALT_VOLLSTAENDIGER_DATEN",
      "bereichsZuordnung": "ANTRAGSTELLER"
    },
    ...
  ],
  "_links": {
    "_self": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/ergebnisliste/LAPF9B/1/meldungen"
    }
  }
}
```

## Use case get details (Zahlungsplaene) of offer

```http
GET /v3/vorgaenge/{{case-id}}/ergebnisliste/{{ergebnislisteId}}/{{offer-number}}/zahlungsplaene HTTP/1.1
Host: api.europace2.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

example-response:

```json
{
  "_links": {
    "_self": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/ergebnisliste/LAPF9B/1/zahlungsplaene"
    }
  },
  "zahlungsplaene": [
    {
      "identifier": "64799f25ce9d3daff2f7335a",
      "typ": "TILGUNGSPLAN",
      "zahlungen": [
        {
          "datum": "2024-07-31",
          "zahlung": -250000.00,
          "tilgung": -250000.00,
          "zinsen": 0.00,
          "saldo": -250000.00
        },
      ..., 
      "_links": {
        "_self": {
          "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/ergebnisliste/LAPF9B/1/zahlungsplaene/64799f25ce9d3daff2f7335a"
        }
      }
    }
  ]
}
```

## Use case get details (Unterlagen) of Offer

```http
GET /v3/vorgaenge/{{case-id}}/ergebnisliste/{{ergebnislisteId}}/{{offer-number}}/unterlagen HTTP/1.1
Host: api.europace2.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

example-response:

```json
{
  "unterlagen": [
    {
      "bezugsObjektId": "64799f241e41fff29f4b9d37",
      "code": "5d39a0e54cedfd0001bb4cc2",
      "produktAnbieterId": "MUSTERBANK",
      "faelligkeit": "ZUR_VERBINDLICHEN_ANGEBOTSANNAHME",
      "zuordnung": "ANTRAGSTELLER",
      "text": "Bei c/o-Adressen (zum Beispiel: z. Hd.,\nz. Zt., bei, BOX und Scanbox) wird zusätzlich eine Meldebescheinigung lautend auf die c/o Adresse benötigt."
    },
    {
      "bezugsObjektId": "64799f250570438818edadf5",
      "code": "5d39a0e54cedfd0001bb4cc2",
      "produktAnbieterId": "MUSTERBANK",
      "faelligkeit": "ZUR_VERBINDLICHEN_ANGEBOTSANNAHME",
      "zuordnung": "ANTRAGSTELLER",
      "text": "Bei c/o-Adressen (zum Beispiel: z. Hd.,\nz. Zt., bei, BOX und Scanbox) wird zusätzlich eine Meldebescheinigung lautend auf die c/o Adresse benötigt."
    },
    ...
  ],
  "_links": {
    "_self": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/ergebnisliste/LAPF9B/1/unterlagen"
    }
  }
}
```

## Use case get details (Provision) of offer

The return of the provision data is dependent on the `provisionsAusgabe` field set to `true` in the initial calculation request. [See here](#findOffers)

```http
GET /v3/vorgaenge/{{case-id}}/ergebnisliste/{{ergebnislisteId}}/{{offer-number}}/provision HTTP/1.1
Host: api.europace2.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

example-response:

```json
{
  "betrag": 2500,
  "partnerId": "PARTNER-ID"
}
```

## Use case get details (Uebersichten) of offer

Uebersichten are HTML snippets of Loan provider calculation details. These details may contain individual data and methods of loan providers.

```http
GET /v3/vorgaenge/{{case-id}}/ergebnisliste/{{ergebnislisteId}}/{{offer-number}}/berechnungsuebersichten HTTP/1.1
Host: api.europace2.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```
example-response:

```json
{
  "haushaltsrechnung": [
    {
      "html": "PGRpdiBjbGF...", //BASE-64 ENCODED HTM for Loan provicer calculation details
      "exzerpt": "4623.75",
      "produktAnbieter": {
        "produktAnbieterId": "MUSTERBANK",
        "partnerId": "PARTNER-ID",
        "name": "Bank  AG",
        "_links": {
          "logo": {
            "href": "https://www.europace2.de/produktanbieter-logos/logo/MUSTERBANK.svg"
          }
        }
      }
    }
  ],
  "lebensphasenplanung": [],
  "kondition": [
    {
      "html": "PGRpdiBjbGF...", //BASE-64 ENCODED HTM for Loan provicer calculation details
      "exzerpt": "0.0432",
      "produktAnbieter": {
        "produktAnbieterId": "MUSTERBANK",
        "partnerId": "PARTNER-ID",
        "name": "Bank  AG",
        "_links": {
          "logo": {
            "href": "https://www.europace2.de/produktanbieter-logos/logo/MUSTERBANK.svg"
          }
        }
      },
      "darlehenId": "64799f25ce9d3daff2f7335a"
    }
  ],
  "beleihungsauslauf": [
    {
      "html": "PGRpdiBjbGF...", //BASE-64 ENCODED HTM for Loan provicer calculation details
      "exzerpt": "1.2500",
      "produktAnbieter": {
        "produktAnbieterId": "MUSTERBANK",
        "partnerId": "PARTNER-ID",
        "name": "Bank  AG",
        "_links": {
          "logo": {
            "href": "https://www.europace2.de/produktanbieter-logos/logo/MUSTERBANK.svg"
          }
        }
      }
    }
  ],
  "_links": {
    "_self": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/ergebnisliste/Z4WQVT/1/berechnungsuebersichten"
    }
  }
}
```


## Use case get saved offers

Get all gemerkte Angebote (saved offers) within a case.

```http
GET /v3/vorgaenge/{{case-id}}/gemerkteangebote HTTP/1.1
Host: api.europace2.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

The response is very similar in its domain model to the angebot model of newly calculated offers.

example-response:
```json
[
  {
    "id": "6675df4723bf6f0ac203dbb9",
    "laufendeNummer": 9,
    "darlehensSumme": 250000,
    "sollZins": 4.32,
    "effektivZins": 4.44,
    "darlehen": [
      {
        "id": "64799f25ce9d3daff2f7335a",
        ...
      }
    ],
    ...
  }
  
]
```

## Use case get specific saved offer

Get one gemerktes Angebote (saved offer) based on ```laufendeNummerAmVorgang``` within a case.

```http
GET /v3/vorgaenge/{{case-id}}/gemerkteangebote/{{laufendeNummerAmVorgang}} HTTP/1.1
Host: api.europace2.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

The response is very similar in its domain model to the angebot model of newly calculated offers.

example-response:
```json
{
  "id": "6658753323880379d2043a2c",
  "laufendeNummer": 6,
  "darlehensSumme": 250000,
  "sollZins": 4.37,
  "effektivZins": 4.49,
  "darlehen": [
    {
      "id": "64799f25ce9d3daff2f7335a",
      "typ": "ANNUITAETEN_DARLEHEN",
      ...
    }
  ],
  ...
  "basisAngebotsId": "664498a262d82f543873ce69",
  "zuGrundeLiegendesAngebot": "66546eb62be3aa29807adad9",
  "_links": {
    "_self": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/gemerkteangebote/6"
    },
    "meldungen": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/gemerkteangebote/6/meldungen"
    },
    "berechnungsuebersichten": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/gemerkteangebote/6/berechnungsuebersichten"
    },
    "unterlagen": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/gemerkteangebote/6/unterlagen"
    },
    "zahlungsplaene": {
      "href": "https://api.europace2.de/v3/vorgaenge/EU9VWS/gemerkteangebote/6/zahlungsplaene"
    }
  }
}
```

## Use case get details of saved offers

For gemerkte (saved) offers details can be queried similar to fresh calculated offers.
Within a case the ```laufendeNummerAmVorgang``` is used to identify the saved offer.
This number is increasing with each new saved offer and for deleted saved offer the number is not reused later on.

### get Meldungen for saved offer
```http
GET /v3/vorgaenge/{{vorgangsnummer}}/gemerkteangebote/{{laufendeNummerAmVorgang}}/meldungen HTTP/1.1
Host: api.europace2.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```
Example results are similar to Meldungen for fresh offers.

### get Berechungsuebersichten for saved offer
```http
GET /v3/vorgaenge/{{vorgangsnummer}}/gemerkteangebote/{{laufendeNummerAmVorgang}}/berechnungsuebersichten HTTP/1.1
Host: api.europace2.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```
Example results are similar to berechnungsuebersichten for fresh offers.

### get Unterlagen for saved offer
```http
GET /v3/vorgaenge/{{vorgangsnummer}}/gemerkteangebote/{{laufendeNummerAmVorgang}}/unterlagen HTTP/1.1
Host: api.europace2.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

Example results are similar to Unterlagen for fresh offers.

### get Zahlungplaene for saved offer
```http
GET /v3/vorgaenge/{{vorgangsnummer}}/gemerkteangebote/{{laufendeNummerAmVorgang}}/zahlungsplaene HTTP/1.1
Host: api.europace2.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```
Example results are similar to Zahlungsplaene for fresh offers.

## Use case save calculated offer

Newly calculated and refreshed offers can be saved in a case and turned into gemerkteAngebote.

```http
POST /v3/vorgaenge/{{vorgangsnummer}}/ergebnisliste/{{ergebnislisteId}}/{{offer-number}}/merken HTTP/1.1
Host: api.europace2.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

example-response:
The new number in the case ```laufendeNummerAmVorgang``` is returned which identifies a gemerktesAngebot.

```json
{
  "laufendeNummerAmVorgang": 14
}
```

## Use case delete saved offer

A saved offers can be deleted in a case. Its ```laufendeNummerAmVorgang``` is not reused.

```http
POST /v3/vorgaenge/{{vorgangsnummer}}/gemerkteangebote/{{laufendeNummerAmVorgang}}/entmerken HTTP/1.1
Host: api.europace2.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

example-response:

```http
HTTP/2 204 No Content
```



# Documentation Version 2

## Use cases

- as an advisor you can find offers and compare them to get the best customer solution
- as advisor or loan provider find prolongation offers


## Find offers

To find offers, Europace offer two ways: \
a) You send your customer data to api and get offers without a case in Europace. \
b) You can find offers for an existing case.

### a) Find offers for a case

As advisor you want to check the current loan rates for a past offering, to decide the next step with customer.

example-request:

```http
POST /v2/ergebnisliste/ermittlung?vorgangsNummer={{case-id}} HTTP/1.1
Host: baufismart.api.europace.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

example-response:

```json
{
    "ermittlungsId": "CJAMN4",
    "ergebnisse": [
        {
            "kennung": "",
            "darlehensSumme": 100000.00,
            "sollZins": 1.19000,
            "effektivZins": 1.23000,
            "darlehen": [
                {
                    "id": "620d2b1aa52cfa08e9ef171b",
                    "typ": "ANNUITAETEN_DARLEHEN",
                    "sollZins": 1.19000,
                    "effektivZins": 1.23000,
                    "effektivZinsRelevanteKosten": {
                        "grundbuchKosten": 273.00
                    },
                    "rateMonatlich": 265.83,
                    "darlehensBetrag": 100000.00,
                    "auszahlungsBetrag": 100000.00,
                    "produktAnbieter": {
                        "produktAnbieterId": "MUSTERBANK",
                        "partnerId": "PARTNER-ID",
                        "name": "Musterbank",
                        "_links": {
                            "logo": {
                                "href": "https://www.europace2.de/produktanbieter-logos/logo/MUSTERBANK.svg"
                            }
                        }
                    },
                    "finanzierenderProduktAnbieter": {
                        "produktAnbieterId": "MUSTERBANK",
                        "partnerId": "PARTNER-ID",
                        "name": "Musterbank",
                        "_links": {
                            "logo": {
                                "href": "https://www.europace2.de/produktanbieter-logos/logo/MUSTERBANK.svg"
                            }
                        }
                    },
                    "zinsZahlungsBeginnAm": "2022-04-30",
                    "zinsBindung": {
                        "jahre": 10,
                        "restschuldNachZinsBindungsEnde": 78772.98
                    },
                    "tilgung": {
                        "anfaenglicheTilgung": 2.00000,
                        "tilgungsBeginn": "2022-04-30",
                        "sonderTilgungJaehrlich": 10.00000
                    },
                    "bereitstellung": {
                        "bereitstellungsZinsfreieZeitInMonaten": 3,
                        "bereitstellungsZins": 3.00000
                    },
                    "gesamtlaufzeitInMonaten": 472,
                    "gesamtkosten": 125216.08,
                    "auszahlungsDatum": "2022-03-31"
                }
            ],
            "beleihung": [
                {
                    "produktAnbieter": {
                        "produktAnbieterId": "MUSTERBANK",
                        "partnerId": "PARTNER-ID",
                        "name": "Musterbank",
                        "_links": {
                            "logo": {
                                "href": "https://www.europace2.de/produktanbieter-logos/logo/MUSTERBANK.svg"
                            }
                        }
                    },
                    "summe": 180000.00,
                    "auslauf": 55.56000
                }
            ],
            "machbarkeit": "MACHBAR",
            "annahmeFrist": "2022-03-04T23:59:59+01:00",
            "erzeugtAm": "2022-02-25T11:39:13.621+01:00",
            "bausparAngebote": [],
            "anpassungsStatus": "ANGEPASST",
            "_links": {
                "_self": {
                    "href": "https://baufismart.api.europace.de/v2/ergebnisliste/ermittlung/CJAMN4/ergebnisse/30"
                }
            }
        },
        ....
    ]
}
```

### b) Find offers without case

As customer you want to check your financial possibilities for by a new home and play around a little bit.
No data will be stored.
To find the best offers you have to set the financing parameters in the body. The body format is the same as the [vorgaenge-api](https://docs.api.europace.de/baufinanzierung/vorgaenge/vorgang-auslesen-api/) can get.

example-request:

```http
POST /v2/ergebnisliste/ermittlung HTTP/1.1
Host: baufismart.api.europace.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
Content-Length: 762

{
    "vorhaben": {
        "finanzbedarf": {
            "kaufpreis": 200000
        },
        "finanzierungswunsch": {
            "darlehensWuensche": [
                {
                    "annuitaetenDarlehen": {
                        "darlehensBetrag": 150000,
                        "provision": 1,
                        "tilgungsWunsch": {
                            "anfaenglicheTilgung": 2,
                            "volltilgerWennAnnuitaetenOderForward": false
                        },
                        "bereitstellungsZinsFreieZeitInMonaten": 2,
                        "sondertilgungOptionalJaehrlich": 100,
                        "zinsBindungInJahren": 10
                    }
                }
            ]
        }
    }
}
```

example-response:

[see result of a\)](#a-find-offers-for-a-case)

## get additional offer detail

### get offer notes

You can get a list of notes for an offer, to see what is probably problematic with your case.
As parameter you need the offerfinding-id (like CJAMN4) and the offer-iterator (like 30).

example-request:

```http
GET /v2/ergebnisliste/ermittlung/CJAMN4/ergebnisse/30/meldungen HTTP/1.1
Host: baufismart.api.europace.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

example-response:

```json
{
    "meldungen": [
        {
            "text": "Gib bitte an, ob der Finanzierungsvorschlag deiner Empfehlung entspricht.",
            "code": "pe.speed.vorbehaltsmeldung.angebot.empfohlen",
            "produktAnbieterId": "MUSTERBANK",
            "meldungsKategorie": "MACHBARKEIT_UNTER_VORBEHALT_VOLLSTAENDIGER_DATEN",
            "bereichsZuordnung": "VORHABEN"
        },
        {
            "text": "Bei einer Verringerung der Darlehenssumme unter 150.000,00 € verändert sich die Kondition um -0,05 %. Bis zu einer Darlehenssumme unter 168.800,00 € bleibt die Kondition gleich.",
            "code": "pe.speed.machbarkeit.darlehenssumme.optimierung.beideGrenzen",
            "produktAnbieterId": "MUSTERBANK",
            "meldungsKategorie": "MACHBARKEITS_HINWEIS",
            "bereichsZuordnung": "VORHABEN"
        },
        {
            "text": "Die bereitstellungszinsfreie Zeit für das Annuitätendarlehen über 150.000,00 € wurde auf die maximal kostenfreie Zeit von 3 Monaten erhöht.",
            "code": "pe.speed.machbarkeit.annuitaetendarlehen.bereitstellungszins.erhoeht.anpassung",
            "produktAnbieterId": "MUSTERBANK",
            "meldungsKategorie": "ANPASSUNG_KUNDENWUNSCH",
            "bereichsZuordnung": "VORHABEN"
        },
        {
            "text": "Es wurde keine Person angegeben.",
            "code": "pe.speed.vorbehaltsmeldung.darlehensnehmer",
            "produktAnbieterId": "MUSTERBANK",
            "meldungsKategorie": "MACHBARKEIT_UNTER_VORBEHALT_VOLLSTAENDIGER_DATEN",
            "bereichsZuordnung": "VORHABEN"
        },
        ...
    ]
}
```

### get needed proofs

You can get a list of needed proofs for an offer, to inform your customer wich documents are needed for approval.

example-request:

```http
GET /v2/ergebnisliste/ermittlung/CJAMN4/ergebnisse/30/unterlagen HTTP/1.1
Host: baufismart.api.europace.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

example-response:

```json
{
    "unterlagen": [
        {
            "bezugsObjektId": "620d2b1aa64cfa08e9ef171b",
            "code": "093f65e0-80a2-35f8-876b-1c5722a46aa2",
            "produktAnbieterId": "MUSTERBANK",
            "faelligkeit": "ZUR_VERBINDLICHEN_ANGEBOTSANNAHME",
            "zuordnung": "ANTRAGSTELLER",
            "text": "Jahreskontoauszug vom letzten Jahr"
        },
        {
            "bezugsObjektId": "620d2b1aa52cfa0adc3f172e",
            "code": "e2c420d9-28d4-3f8c-a0ff-2ec19b371514",
            "produktAnbieterId": "MUSTERBANK",
            "faelligkeit": "ZUR_VERBINDLICHEN_ANGEBOTSANNAHME",
            "zuordnung": "IMMOBILIE",
            "text": "Grundbuchauszug"
        },
    ...
}
```

### get redemption plan

You can get a redemption plan for an offer, to show your customer his cashflow.

example-request:

```http
GET /v2/ergebnisliste/ermittlung/CJAMN4/ergebnisse/30/zahlungsplaene HTTP/1.1
Host: baufismart.api.europace.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

example-response:

```json
{
    "zahlungsplaene": [
        {
            "identifier": "U5BT3T1XKB",
            "typ": "TILGUNGSPLAN",
            "bausteinTyp": "ANNUITAETEN_DARLEHEN",
            "zahlungen": [
                {
                    "datum": "2022-03-31",
                    "zahlung": -150000.00,
                    "tilgung": -150000.00,
                    "zinsen": 0.00,
                    "saldo": -150000.00
                },
                {
                    "datum": "2022-04-30",
                    "zahlung": 736.25,
                    "tilgung": 250.00,
                    "zinsen": 486.25,
                    "saldo": -149750.00
                },
                {
                    "datum": "2022-05-31",
                    "zahlung": 736.25,
                    "tilgung": 250.81,
                    "zinsen": 485.44,
                    "saldo": -149499.19
                },
                ....
                ],
            "summeEndeDerZinsbindung": {
                "datum": "2032-03-31",
                "zahlung": 88350.00,
                "tilgung": 36599.68,
                "zinsen": 51750.32,
                "saldo": -113400.32
            },
            "gesamtSumme": {
                "zahlung": 245713.04,
                "tilgung": 150000.00,
                "zinsen": 95713.04,
                "saldo": 0.00
            }
        }
    ]
}
```

# FAQ

### No or few offers are coming in, what is the reason?

Some providers are only active regionally - to receive these offers "Haushalte" and "Finanzierungsobjekt" must be filled.

### How do I receive offers with KfW products?

For this, you must explicitly request the calculation of alternatives: `https://baufismart.api.europace.de/v2/ergebnisliste/ermittlung?vorgangsNummer=AB1234&alternativen=true`

### Can I get the commission of an offer?

In order to activate the commission calculation, the request parameter provisionsAusgabe must be set to true during the offer determination.

example-request:

`POST https://baufismart.api.europace.de/v2/ergebnisliste/ermittlung?vorgangsNummer=SG4516&provisionsAusgabe=true`

In response, you receive an investigation ID. You can then use the determination ID to query the commission for an offer. It is always the commission of the person who has determined the offers.

example-request:

`GET https://baufismart.api.europace.de/v2/ergebnisliste/ermittlung/UQXSFG/ergebnisse/11/provision`

> Attention:
> The calculation of the commission is expensive and therefore takes place asynchronously in the background. If the commission calculation is not yet completed, there is a temporary redirect with a short time delay. The Http client should then repeat the retrieval a short time later.

example-response:

```json
{
    betrag: 100000,    // amount in cents
    partnerId: "WER03" // PartnerId of the recipient
}
```

### I get a `302` as response. The response is empty?

302 is a `redirect`. The client should follow the redirect by calling the URL returned in the header under `Location`.

### How does rate limiting work? Why do I get `429` as answer?

In the response there is a custom header that contains the following fields:

- `X-RateLimit-Remaining` -> How many calls do I have left in the current time window?
- `X-RateLimit-Reset` -> How many seconds until the current time window closes and the next one opens?

If `X-RateLimit-Remaining` reaches 0, then the status code `429 - Too Many Requests` comes back and no more requests can be made. You have to wait until the next time window.

### How can I create an offer chain?

If you save a calculated offer it gets an id:

```json
{
  "id": "6658753323880379d2043a2c",
  "laufendeNummer": 6,
  "darlehensSumme": 250000,
  "sollZins": 4.37,
  "effektivZins": 4.49,
  "darlehen": [
    {
      "id": "64799f25ce9d3daff2f7335a",
      "typ": "ANNUITAETEN_DARLEHEN",
      ...
    }
  ],
  ...
  "basisAngebotsId": "6658753323880379d2043a2c",
}
```

If you now recalculate the offers in the case, you will get new and recalculated saved offers.
When saving the offer containing a recalculated saved offer you will get the same ```laufendeNummerAmVorgang``` as with
the first save.
If you now get the saved offer ```GET /v3/vorgaenge/{{case-id}}/gemerkteangebote/6``` you receive:

```json
{
  "id": "669687df411eac7bb515b249",
  "laufendeNummer": 6,
  "darlehensSumme": 250000,
  "sollZins": 4.37,
  "effektivZins": 4.49,
  "darlehen": [
    {
      "id": "64799f25ce9d3daff2f7335a",
      "typ": "ANNUITAETEN_DARLEHEN",
      ...
    }
  ],
  ...
  "basisAngebotsId": "6658753323880379d2043a2c",
  "zuGrundeLiegendesAngebot": "6658753323880379d2043a2c",
}
```

As you can see there is now a ```zuGrundeLiegendesAngebot```. Here you find the id of the (saved) offer on which this
offer is based.

If you now repeat the steps from above, you will receive the following offer:

```json
{
  "id": "66968690b7585f5b9587ea1f",
  "laufendeNummer": 6,
  "darlehensSumme": 250000,
  "sollZins": 4.37,
  "effektivZins": 4.49,
  "darlehen": [
    {
      "id": "64799f25ce9d3daff2f7335a",
      "typ": "ANNUITAETEN_DARLEHEN",
      ...
    }
  ],
  ...
  "basisAngebotsId": "6658753323880379d2043a2c",
  "zuGrundeLiegendesAngebot": "669687df411eac7bb515b249",
}
```

As you can see, the ```basisAngebotsId``` is always the same. This is the id of the first saved offer and represents
the ```root```.
Also, you can see that the ```zuGrundeLiegendesAngebot``` has changed. It now contains the id of the second saved offer.
The id in ```zuGrundeLiegendesAngebot``` is always the id of the offer on which this offer is based.

## Terms of use

The APIs are provided under the following [Terms of Use](https://docs.api.europace.de/nutzungsbedingungen).

## Support

If you have any questions or problems, you can contact devsupport@europace2.de.