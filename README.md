# finanzierungsvorschlaege-api
Eine API um Finanzierungsvorschläge zu ermitteln.

# Vorsicht!

Diese API befindet sich noch in einem sehr frühem Stadium der Entwicklung. Requests gehen an eine Dev-Stage, welche in Sachen
Stabilität und Leistungsfähigkeit nicht mit einer Produktiv-Umgebung vergleichbar ist! Wenn Ihnen Fehler auffallen oder Sie
Änderungswünsche haben, können Sie diese gerne hier als Issue einstellen. Wir freuen uns über jedes Feedback!

Insbesondere nach einem Deployment unsererseits sind die ersten Requests SEHR langsam. Nachfolgende Requests sollten danach
allerdings wesentlich schneller bearbeitet werden. Sollten Sie einen Timeout des API-Gateways erhalten, senden Sie den Request
bitte erneut.


# Spezifikation

Die API ist vollständig in Swagger spezifiziert: [swagger.yaml](swagger.yaml)

Diese Spezifikation kann auch zur Generierung von Clients für diese API verwendet
werden. Dazu empfehlen wir das Tool [Swagger Codegen](https://github.com/swagger-api/swagger-codegen)

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
  https://baufismart.api.europace.de/v1test/finanzierungsvorschlaege \
  -H 'authorization: Bearer {{access_token}}' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"vorhaben": {
		"finanzbedarf": {
			"kaufpreis": 100000
		}
	}
}'
```

# Finanzierungsvorschläge abrufen

Für erste Finanzierungsvorschläge benötigen wir nur einen Betrag im Finanzbedarf. Ein möglicher Beispiel-Request wäre:

```
curl -X POST \
  https://baufismart.api.europace.de/v1test/finanzierungsvorschlaege \
  -H 'authorization: Bearer {{access_token}}' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"vorhaben": {
		"finanzbedarf": {
			"kaufpreis": 100000
		}
	}
}'
```

Ansonsten sind die Ergebnisse der [Vorgänge-API](https://github.com/hypoport/europace2-api/tree/master/BaufiSmart/vorgaenge-api)
valide Eingaben für diese Schnittstelle.