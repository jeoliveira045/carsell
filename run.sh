#!/bin/bash

curl -X GET http://localhost:8080/carros/inserir-dados | jq
curl -X GET http://localhost:8080/clientes/inserir-dados | jq
curl -X GET http://localhost:8080/vendas/inserir-dados | jq