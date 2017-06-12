# finanzierungsvorschlaege-api
Eine API um Finanzierungsvorschläge zu ermitteln.

# Vorsicht!

Diese API befindet sich noch in einem sehr frühem Stadium der Entwicklung. Requests gehen an eine Dev-Stage, welche in Sachen
Stabilität und Leistungsfähigkeit nicht mit einer Produktiv-Umgebung vergleichbar ist! Wenn Ihnen Fehler auffallen oder Sie
Änderungswünsche haben, können Sie diese gerne hier als Issue einstellen. Wir freuen uns über jedes Feedback!


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