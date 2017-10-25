# Finanzierungsvorschläge API
Eine API um Finanzierungsvorschläge zu ermitteln.

# Spezifikation

Die API ist vollständig in Swagger spezifiziert: [swagger.yaml](swagger.yaml)

Diese Spezifikation kann auch zur Generierung von Clients für diese API verwendet
werden. Dazu empfehlen wir das Tool [Swagger Codegen](https://github.com/swagger-api/swagger-codegen)

# API Documentation

 - [statische HTML Seite](http://htmlpreview.github.io?https://raw.githubusercontent.com/hypoport/finanzierungsvorschlaege-api/master/Dokumentation/index.html)

# Authentifizierung

Gültige Login-Token können mit einem POST-Request auf https://api.europace.de/login mit Ihrem Nutzern-Namen und Passwort aus
BaufiSmart abgerufen werden. Ein Beispiel mit curl sähe wie folgt aus:

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
  https://baufismart.api.europace.de/v1/finanzierungsvorschlaege \
  -H 'authorization: Bearer {{access_token}}' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
        "vorhaben": {
          "finanzierungswunsch": {
            "darlehensWuensche": [
              {
                "id": "13674381840872074",
                "annuitaetenDarlehen": {
                  "darlehensBetrag": 350000,
                  "provision": 2,
                  "tilgungsWunsch": {
                    "anfaenglicheTilgung": 2,
                    "volltilgerWennAnnuitaetenOderForward": false
                  },
                  "bereitstellungsZinsFreieZeitInMonaten": 2,
                  "sondertilgungOptionalJaehrlich": 0,
                  "zinsBindungInJahren": 10
                }
              }
            ]
          }
        }
      }'
```

# Finanzierungsvorschläge abrufen

Für erste Finanzierungsvorschläge benötigen wir einen Darlehenswunsch. Ein möglicher Beispiel-Request wäre:

```
curl -X POST \
  https://baufismart.api.europace.de/v1/finanzierungsvorschlaege \
  -H 'authorization: Bearer {{access_token}}' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
        "vorhaben": {
          "finanzierungswunsch": {
            "darlehensWuensche": [
              {
                "id": "13674381840872074",
                "annuitaetenDarlehen": {
                  "darlehensBetrag": 350000,
                  "provision": 2,
                  "tilgungsWunsch": {
                    "anfaenglicheTilgung": 2,
                    "volltilgerWennAnnuitaetenOderForward": false
                  },
                  "bereitstellungsZinsFreieZeitInMonaten": 2,
                  "sondertilgungOptionalJaehrlich": 0,
                  "zinsBindungInJahren": 10
                }
              }
            ]
          }
        }
      }'
```

Als valide Eingaben für diese Schnittstelle können die  Ergebnisse der [Vorgänge-API](https://github.com/hypoport/vorgaenge-api)
verwendet werden.

Desweiteren können auch direkt zu einem bestehenden Vorgang Finanzierungsvorschläge abgerufen werden:

```
curl -X GET \
  https://baufismart.api.europace.de/v1/finanzierungsvorschlaege/{vorgangsNummer}  \
  -H 'authorization: Bearer {{access_token}}' \
  -H 'cache-control: no-cache' 
```

