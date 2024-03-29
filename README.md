# Angebote API

As advisor you can find offers and compare them to get the best customer solution.

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

## Usecases

- as advisor you can find offers and compare them to get the best customer solution
- as advisor or loan provider find prolongation offers

## Requirements

- authenticated as loan provider

## Quick Start

To test our APIs and your use cases as quickly as possible, we have created a [Postman Collection](https://github.com/europace/api-quickstart) for you.

### Authentication

Please use [![Authentication](https://img.shields.io/badge/Auth-OAuth2-green)](https://docs.api.europace.de/common/authentifizierung/authorization-api/) to get access to the APIs. The OAuth2 client requires the following scopes:

| Scope                               | API Use case                                                         |
|-------------------------------------|----------------------------------------------------------------------|
| `baufinanzierung:angebot:ermitteln` | to find offers                                                       |

## Find offers

To find offers, Europace offer two ways: \
a) You send your customer data to api and get offers without a case in Europace. \
b) You can find offers for an existing case.

### a) Find offers for a case

As advisor you want to check the current loan rates for a past offering, to decide the next step with customer.

example-request:

``` http
POST /v2/ergebnisliste/ermittlung?vorgangsNummer={{case-id}} HTTP/1.1
Host: baufismart.api.europace.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

example-response:

``` json
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
                        "partnerId": "GWL17",
                        "name": "Musterbank",
                        "_links": {
                            "logo": {
                                "href": "https://www.europace2.de/produktanbieter-logos/logo/MUSTERBANK.svg"
                            }
                        }
                    },
                    "finanzierenderProduktAnbieter": {
                        "produktAnbieterId": "MUSTERBANK",
                        "partnerId": "GWL17",
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
                        "partnerId": "GWL17",
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

``` http
POST /v2/ergebnisliste/ermittlung/ HTTP/1.1
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

``` http
GET /v2/ergebnisliste/ermittlung/CJAMN4/ergebnisse/30/meldungen HTTP/1.1
Host: baufismart.api.europace.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

example-response:

``` json
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

``` http
GET /v2/ergebnisliste/ermittlung/CJAMN4/ergebnisse/30/unterlagen HTTP/1.1
Host: baufismart.api.europace.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

example-response:

``` json
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

``` http
GET /v2/ergebnisliste/ermittlung/CJAMN4/ergebnisse/30/zahlungsplaene HTTP/1.1
Host: baufismart.api.europace.de
Content-Type: application/json
Authorization: Bearer {{access-token}}
```

example-response:

``` json
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

## FAQ

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

``` json
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

## Terms of use

The APIs are provided under the following [Terms of Use](https://docs.api.europace.de/nutzungsbedingungen).

## Support

If you have any questions or problems, you can contact devsupport@europace2.de.