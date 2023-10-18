# Avaliação da disciplina de APIRest
                                                                       
### Historinha:

***O seu grupo foi convidado a desenvolver uma API para um novo sistema de E Commerce  onde o usuário do tipo cliente poderá executar os seguintes ações:*** 

✅ Consultar uma lista de produtos. (Autenticado = false)  
✅ Consultar uma lista de produtos vinculada a uma categoria. (Autenticado = false)  
✅ Consultar um produto pelo seu id. (Autenticado = false)  
✅ Consultar uma lista de categorias. (Autenticado = false)  
✅ Consultar uma categoria pelo id. (Autenticado = false)  

✅ Poder cadastrar uma conta (Autenticado = false)  
✅ Toda conta deve conter (e-mail, senha, telefone, dataCadastro e perfil)  📌(ATENÇÃO: perfil)

✅ Poder criar um pedido (Autenticado = true)  
✅ Todo pedido deve ter: Número, Cliente, Data do pedido, valor total, desconto total, acréscimo total e observação.  
✅ Cada pedido poderá ter muitos itens.  
✅ Cada item poderá ter (quantidade, valor unitário,  desconto, acréscimo e valorTotal)  
✅ Cálculo do valor do item (valor unitário - desconto + acréscimo) * quantidade  📌(ATENÇÃO: caculo valor item)
✅ Forma de pagamento
❌ Quando o pedido for cadastrado, deve enviar um email automaticamente para o cliente informando os dados do pedido. Nesse e-mail envie um layout bonitinho do tipo HTML.  

❌ Deve existir um usuário do tipo Admin
❌ Todo usuário admin, pode fazer tudo na API

📌 Com o usuário admin, deve ser possível executar as seguintes ações:  
✅ Cadastrar uma Categoria  
✅ Atualizar uma Categoria  
✅ Cadastrar um produto  
✅ Todo produto deve ter (id, nome, valor, quantidade, observação)  
✅ Atualizar um produto  
❌ Inativar produto  
❌ Inativar uma categoria

❌ Tabela de log das alterações e inclusões feitas em produtos e categorias.
❌ (id, tipo, data, valorOriginal, valorAtual, idUsuario)
❌ Tem que ter tratamento de erro com retorno correto dos statuscode.

### Extras:
❌ Criar documentação no swagger
❌ Adicionar foto ao produto

