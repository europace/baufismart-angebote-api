# Finanzierungsvorschläge API

Eine API um Finanzierungsvorschläge/Ergebnisse zu ermitteln.

##### Aktuelle Version: 2.0.0.RC1

Wesentliche Änderungen zur Version 1.5.

* Die Version v1 funktioniert wie bisher: https://baufismart.api.europace.de/v1/finanzierungsvorschlaege/{vorgangsNummer} 
* Finanzierungsvorschläge werden in Ergebnisse umbenannt. Die Liste in Ergebnisliste.
* Der REST Endpunkt (Post Method) zur Ermittlung von Ergebnissen lautet: https://baufismart.api.europace.de/v2/ergebnisliste/ermittlung
* Man kann sich die Ermittlung mit den gelieferten Ergebnissen erneut anfordern
* Ergebnisdetails wie Unterlagen, Meldungen und Zahlungspläne können zu einem Ergebnis ausgelesen werden.


### Swagger Spezifikation

Die API ist vollständig in Swagger definiert. Die Swagger Definition wird im YAML-Format zur Verfügung gestellt.: [swagger.yaml](swagger.yaml)

Diese Spezifikation kann auch zur Generierung von Clients für diese API verwendet
werden. Dazu empfehlen wir das Tool [Swagger Codegen](https://github.com/swagger-api/swagger-codegen)

# Dokumentation

 - [RELEASE NOTES](https://github.com/hypoport/finanzierungsvorschlaege-api/releases)
 - [statische HTML Seite](http://htmlpreview.github.io?https://raw.githubusercontent.com/hypoport/finanzierungsvorschlaege-api/master/Dokumentation/index.html)

### Generierung des Clients
##### JAVA mit Retrofit

1. Die aktuelle Swagger-Codegen Version, mindestens 2.2.2, downloaden
2. Client mit folgendem Kommando generieren:

Example:

```
java -jar swagger-codegen-cli-2.2.2.jar generate -i swagger.yaml -l java -c codegen-config-file.json -o europace-api-client
```

Example **codegen-config-file.json**:

```
{
  "artifactId": "europace-api-client",
  "groupId": "de.europace.api",
  "library": "retrofit2",
  "artifactVersion": "0.1",
  "dateLibrary": "java8"
}

```

### Authentifizierung

Die Authentifizierung läuft über den [OAuth2](https://oauth.net/2/) Flow vom Typ *ressource owner password credentials flow*.
https://tools.ietf.org/html/rfc6749#section-1.3.3

##### Credentials
Um die Credentials zu erhalten, erfagen Sie beim Helpdesk der Plattform die Zugangsdaten zur Auslesen API, bzw. bitten Ihren Auftraggeber dies zu tun.

##### Schritte
1. Absenden eines POST Requests auf den [Login-Endpunkt](https://htmlpreview.github.io/?https://raw.githubusercontent.com/hypoport/finanzierungsvorschlaege-api/master/Dokumentation/index.html#_oauth2) mit Username und Password. Der Username entspricht der PartnerId und das Password ist der API-Key. Auf dem Testsystem können diese Werte frei gewählt werden. Alternativ kann ein Login auch über einen GET Aufruf mit HTTP Basic Auth auf den Login-Endpunkt erfolgen.
2. Aus der JSON-Antwort das JWToken (access_token) entnehmen
3. Bei weiteren Requests muss dieses JWToken als Authorization Header mitgeschickt werden.

Ein Beispiel mit curl sähe wie folgt aus:

```
curl -X POST \
  https://api.europace.de/login \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -d 'username={{BaufiSmart Nutzername (Mail-Adresse)}}&password={{BaufiSmart Passwort}}'
```


Der in der Antwort enthaltene Access-Token muss bei allen Folge-Requests im Header `Authorization` im Bearer-Format mit gesendet
werden. Beispiel:

```
curl -X POST \
  https://baufismart.api.europace.de/v2/ergebnisliste/ermittlung \
  -H 'authorization: Bearer {{access_token}}' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
        "vorhaben": {
          ...
        }
      }'
```

# Ergebnisse abrufen

## Ermittlungsanfrage mit übermitteln

Für Ergebnisse/Finanzierungsvorschläge benötigen wir einen Darlehenswunsch. Ein möglicher Beispiel-Request wäre:

```
curl -X POST \
  https://baufismart.api.europace.de/v2/ergebnisliste/ermittlung \
  -H 'authorization: Bearer {{access_token}}' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
       	"vorhaben": {
       	  "finanzbedarf": {
       			"kaufpreis": 100000
       		},
       		"finanzierungswunsch": {
               "darlehensWuensche": [
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
      }'
```

## Ermittlungsanfrage mit Vorgangsnummer

Als valide Eingaben für diese Schnittstelle können die Ergebnisse der [Vorgänge-API](https://github.com/hypoport/vorgaenge-api)
verwendet werden.

Desweiteren können auch direkt zu einem bestehenden Vorgang Finanzierungsvorschläge abgerufen werden:

```
curl -X POST \
  https://baufismart.api.europace.de/v1/finanzierungsvorschlaege/{vorgangsNummer}  \
  -H 'authorization: Bearer {{access_token}}' \
  -H 'cache-control: no-cache'
```


```
curl -X POST \
  https://baufismart.api.europace.de/v2/ergebnisliste/ermittlung?vorgangsNummer={vorgangsNummer}  \
  -H 'authorization: Bearer {{access_token}}' \
  -H 'cache-control: no-cache'
```

## Fragen und Anregungen
Bei Fragen und Anregungen entweder ein Issue in GitHub anlegen oder an [helpdesk@europace2.de](mailto:helpdesk@europace2.de) schreiben.
