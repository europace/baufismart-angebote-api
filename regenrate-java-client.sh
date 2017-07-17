#!/usr/bin/env bash

rm -rf java-client

swagger-codegen generate -i swagger.yaml -l java -o java-client \
  --model-package de.europace.eli.model \
  --api-package de.europace.eli.api \
  --group-id de.europace \
  --artifact-id finanzierungsvorschlaege-client \
  --artifact-version 0.0.1 \
  --library feign \
  -DdateLibrary=java8 \
  -DartifactDescription="An example client for this API in Java" \
  -DhideGenerationTimestamp=true