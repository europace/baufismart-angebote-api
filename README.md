# Angebote API

Eine API um Finanzierungs-Angebote zu ermitteln.

⚠️ Diese API hieß ehemals Finanzierungsvorschläge API oder Ergebnisslisten API (auch ELI genannt)

##### Aktuelle Version: 2.0.1

Wesentliche Änderungen zur Version 1.5.

* Die Version v1 funktioniert wie bisher: https://baufismart.api.europace.de/v1/finanzierungsvorschlaege/{vorgangsNummer}
* Finanzierungsvorschläge werden in Ergebnisse umbenannt, die Liste in Ergebnisliste.
* Der REST Endpunkt (Post Method) zur Ermittlung von Ergebnissen lautet: https://baufismart.api.europace.de/v2/ergebnisliste/ermittlung
* Man kann sich die Ermittlung mit den gelieferten Ergebnissen für 120 Minuten erneut anfordern.
* Ergebnisdetails wie Unterlagen, Meldungen und Zahlungspläne können zu einem Ergebnis ausgelesen werden.


### Swagger Spezifikation

Die API ist vollständig in Swagger definiert. Die Swagger Definition wird im YAML-Format zur Verfügung gestellt.: [swagger.yaml](swagger.yaml)

Diese Spezifikation kann auch zur Generierung von Clients für diese API verwendet
werden. Dazu empfehlen wir das Tool [Swagger Codegen](https://github.com/swagger-api/swagger-codegen). 

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
1. Absenden eines POST Requests auf den [Login-Endpunkt](https://htmlpreview.github.io/?https://raw.githubusercontent.com/hypoport/finanzierungsvorschlaege-api/master/Dokumentation/index.html#_oauth2) mit Username und Password. Der Username entspricht der PartnerId und das Password ist der API-Key. Alternativ kann ein Login auch über einen GET Aufruf mit HTTP Basic Auth auf den Login-Endpunkt erfolgen.
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

## Häufige Fragen / FAQ

### Ich bekomme ein `302` als Antwort. Der Response ist leer.
302 ist ein "Redirect". Der Client sollte dem Redirect folgen, indem er die URL aufruft, die im Header unter `Location` zurück kommt

### Wie funktioniert das Rate-Limiting? Wieso bekomme ich `429`als Antwort?
Im Response gibt einen Custom-Header, der folgende Felder enthällt:

* `X-RateLimit-Remaining` —> wie viele Aufrufe habe ich noch im aktuellen Zeitfenster.
* `X-RateLimit-Reset` —> wie viele Sekunden, bis das aktuelle Zeitfenster zu Ende ist und das nächste beginnt.

Wenn `X-RateLimit-Remaining` 0 erreicht, dann kommt der Status Code `429 - Too Many Requests` zurück und es können keine Requests mehr gemacht werden. Es muss bis zum nächsten Zeitfenster gewartet werden.


### Es kommen keine oder wenige Angebote, woran liegt das?
Einige Anbieter sind nur regional aktiv -- um diese Angebote zu erhalten müssen "haushalte" und "finanzierungsobjekt" gefüllt sein

### Wie erhalte ich Angebote mit KfW Produkten?
Hierfür müssen sie die Berechnung von Alternativen explizit anfordern: `https://baufismart.api.europace.de/v2/ergebnisliste/ermittlung?vorgangsNummer=AB1234&alternativen=true`

### Kann ich meine eigene TraceId beim absenden des Requests mitgeben?
Ja. Der Vorteil dabei ist, dass wir Deinen Request in unserem System verfolgen und schneller und gezielter debuggen können.

Es geht mittels einem HTTP Header: `x-TraceId`. Den Wert kannst Du beliebig wählen. Es ist ratsam, bei jedem Request eine neue TraceId mitzugeben, damit Traces in unserem System eindeutig auffindbar sind. Punkt.

## Kann ich die Provision eines Angebots bekommen?

Um die Provisionsberechnung zu aktivieren, muss bei der Angebotsermittlung der Request-Parameter provisionsAusgabe auf true gesetzt werden.

Bsp: `POST https://baufismart.api.europace.de/v2/ergebnisliste/ermittlung?vorgangsNummer=SG4516&provisionsAusgabe=true`

Als Antwort erhält man eine Ermittlungs-ID. Anschließend kann man die Ermittlungs-ID nutzen, um die Provsion zu einem Angebot abzufragen. Es handelt sich dabei immer um die Provision desjenigen, der die Angebote ermittelt hat.

`GET https://baufismart.api.europace.de/v2/ergebnisliste/ermittlung/UQXSFG/ergebnisse/11/provision`

Achtung: Die Berechnung der Provision ist teuer und erfolgt daher asynchron im Hintergrund. Sollte die Provisionsberechnung noch nicht abgeschlossen sein, so gibt es einen temporären Redirekt mit kurzer zeitlicher Verzögerung. Der Http-Client sollte den Abruf dann kurze Zeit später wiederholen.

Struktur der Response:
```
{
betrag: 100000,    // Betrag in Cents.
partnerId: "WER03" // PartnerId des Empfängers
}
```



## Fragen und Anregungen
Bei Fragen und Anregungen entweder ein Issue in GitHub anlegen oder an [devsupport@europace2.de](mailto:devsupport@europace2.de) schreiben.


# Nutzungsbedingungen
Mit der Verwendung dieser API [stimme ich den Nutzungsbedingungen](https://developer.europace.de/terms/) zu.

