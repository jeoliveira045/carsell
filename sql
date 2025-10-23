SELECT modelo, count(itens_venda.carro_id) FROM CARRO INNER JOIN itens_venda  on CARRO.id = itens_venda.carro_id group by itens_venda.carro_id, modelo
