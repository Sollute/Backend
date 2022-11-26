# Sobre 💾

### ...

### ...

<br>

# Rota de Empresa 📖

> ### Método POST 🕊️
#### Criar uma empresa use `.../empresas/criar-empresa`

```json
{
 "email": "string",
  "senha": "string",
  "nomeFantasia": "string",
  "razaoSocial": "string",
  "cnpj": "string",
  "qtdProdutosVendidos": 0,
  "totalProdutosVendidos": 0,
  "autenticado": true,
  "cep": "string",
  "uf": "string",
  "cidade": "string",
  "logradouro": "string",
  "pontoReferencia": "string"
}
```

#### Para fazer autenticação web use `.../empresas/autenticacao`

```json
{
   "login": "string",
   "senha": "string"
}
```

#### Para fazer autenticação android use `.../empresas/autenticacao-android`

```json
{
   "login": "string",
   "senha": "string"
}
```

<br>

> ### Método GET 📬
#### Listar todas as empresas `.../empresas/listar-empresas`
#### Listar todas as informações da empresa `.../empresas/informacoes/{fkEmpresa}`
#### Calcular os produtos vendidos da empresa `.../empresas/calcular-produtos-vendidos/{fkEmpresa}`
#### Calcular o valor vendido da empresa `.../empresas/calcular-valor-vendidos/{fkEmpresa}`
#### Calcular o valor liquido da empresa `.../empresas/calcular-liquido/{fkEmpresa`

<br>

# Rota de Produto 📖

> ### Método POST 🕊️
#### Criar um produto use `.../produtos/criar-produto/{idEmpresa}`

```json
{
 "codigo": "string",
  "nome": "string",
  "marca": "string",
  "categoria": "string",
  "tamanho": "string",
  "peso": 0,
  "precoCompra": 0,
  "precoVenda": 0,
  "estoque": 0,
  "estoqueMin": 0,
  "estoqueMax": 0,
  "qtdVendidos": 0,
  "valorVendidos": 0
}
```

<br>

> ### Método GET 📬
#### Listar todos os produtos de uma empresa `.../produtos/listar-produtos-android/{idEmpresa}`
#### Listar todos os produtos de uma empresa no android `.../produtos/listar-produtos/{idEmpresa}`
#### Listar todos os produtos de uma empresa em ordem maior `.../produtos/listar-produtos-ordem-maior/{idEmpresa}`
#### Exibir informações sobre um produto `.../produtos/info/{nome}/{fkEmpresa}`

<br>

> ### Método PUT 📝
#### Atualizar um produto pelo código `.../produtos/editar-produto/{idEmpresa}/{codigo}`


```json
{
  "estoque": 0,
  "estoqueMin": 0,
  "estoqueMax": 0,
  "precoCompra": 0,
  "precoVenda": 0
}
```

#### Atualizar um produto pelo nome `.../produtos/editar-produto-nome/{idEmpresa}/{nome}`

```json
{
  "estoque": 0,
  "estoqueMin": 0,
  "estoqueMax": 0,
  "precoCompra": 0,
  "precoVenda": 0
}
```

<br>

> ### Método DELETE 🗑️
#### Deletar um produto `.../produtos/deletar-produto/{codigo}/{fkEmpresa}`
#### Deletar um produto pelo nome `.../produtos/deletar-produto-nome/{nome}/{fkEmpresa}`

<br>

# Rota de Cliente 📖

> ### Método POST 🕊️
#### Criar um cliente use `.../clientes/adicionar-cliente/{idEmpresa}`

```json
{
 "nomeCliente": "string",
 "telefoneCliente": "string"
}
```
<br>

> ### Método GET 📬
#### Listar todos os clientes de uma empresa `.../clientes/listar-clientes/{idEmpresa}`

<br>

> ### Método PUT 📝
#### Atualizar um cliente `.../clientes/editar-cliente/{idEmpresa}/{idCliente}`

```json
{
  "nomeCliente": "string",
  "telefoneCliente": "string"
}
```
<br>

> ### Método DELETE 🗑️
#### Deletar um cliente pelo ID `.../clientes/deletar-cliente/{idCliente}/{idEmpresa}`
#### Deletar um cliente pelo nome `.../clientes/deletar-cliente-nome/{nomeCliente}/{idEmpresa}`

<br>

# Rota de Fornecedor 📖

> ### Método POST 🕊️
#### Criar um fornecedor use `.../fornecedores/criar-fornecedor/{idEmpresa}`

```json
{
 "nomeFornecedor": "string",
  "telefoneFornecedor": "string",
  "nomeProduto": "string",
  "qtd": 0
}
```
<br>

> ### Método GET 📬
#### Listar todos os fornecedores de uma empresa `.../fornecedores/listar-fornecedores/{idEmpresa}`

<br>

> ### Método PUT 📝
#### Atualizar um fornecedor `.../fornecedores/editar-fornecedor/{idEmpresa}/{idFornecedor}`

```json
{
  "nomeFornecedor": "string",
  "telefoneFornecedor": "string",
  "nomeProduto": "string",
  "qtdFornecida": 0
}
```
<br>

> ### Método DELETE 🗑️
#### Deletar um fornecedor pelo ID `.../fornecedores/deletar-fornecedor/{idFornecedor}/{idEmpresa}`

<br>

# Rota de Funcionário 📖

> ### Método POST 🕊️
#### Criar um funcionário use `.../funcionarios/criar-funcionario/{idEmpresa}`

```json
{
 "nomeFuncionario": "string",
  "cpfFuncionario": "string",
  "telefoneFuncionario": "string",
  "salarioFuncionario": 0
}
```
<br>

> ### Método GET 📬
#### Listar todos os funcionário de uma empresa `.../funcionarios/listar-funcionarios/{idEmpresa}`

<br>

> ### Método PUT 📝
#### Atualizar um funcionário `.../funcionarios/editar-funcionario/{idEmpresa}/{idFuncionario}`

```json
{
  "nomeFuncionario": "string",
  "cpfFuncionario": "string",
  "telefoneFuncionario": "string",
  "salario": 0
}
```
<br>

#### Atualizar um funcionário pelo CPF `.../funcionarios//editar-funcionario-cpf/{idEmpresa}/{cpfFuncionario}`

```json
{
  "nomeFuncionario": "string",
  "cpfFuncionario": "string",
  "telefoneFuncionario": "string",
  "salario": 0
}
```
<br>

> ### Método DELETE 🗑️
#### Deletar um funcionário pelo ID `.../funcionarios/deletar-funcionario/{idFuncionario}/{idEmpresa}`
#### Deletar um funcionário pelo CPF `.../funcionarios/deletar-funcionario-cpf/{cpfFuncionario}/{idEmpresa}`

<br>

# Rota de Caixa 📖

> ### Método GET 📬
#### Pegar o saldo do carrinho `.../caixa/pegar-saldo/{idEmpresa}`

<br>

> ### Método PUT 📝
#### Adicionar valor ao caixa `.../caixa/adicionar-valor-caixa/{idEmpresa}/{valor}`
#### Remover valor ao caixa `.../caixa/remover-valor-caixa/{idEmpresa}/{valor}`

<br>

# Rota de Carrinho 📖

> ### Método POST 🕊️
#### Adicionar produto ao carrinho `.../carrinhos/adicionar-carrinho/{cnpj}/{codigo}/{qtdProduto}`

<br>

> ### Método GET 📬
#### Listar todos os produtos do carrinho `.../carrinhos/listar-produtos-carrinho/{fkEmpresa}`

<br>

> ### Método PUT 📝
#### Atualizar o carrinho `.../carrinhos/atualizar-carrinho/{codigo}/{idEmpresa}/{qtdVenda}`

<br>

> ### Método DELETE 🗑️
#### Deletar um produto do carrinho `.../carrinhos/carrinho-apagar-produto/{codigo}/{fkEmpresa}`
<br>

# Rota de Extrato 📖

> ### Método POST 🕊️
#### Criar extrato `.../extratos/criar-extrato/{idEmpresa}`

```json
{
  "extractName": "string",
  "extractTime": "string",
  "extractAmount": 0,
  "extract_type": 0
}
```
<br>

> ### Método GET 📬
#### Listar todos os extratos `.../extratos/listar-extrato/{idEmpresa}`

<br>

## Projetos relacionados 📁

- ### [Front-End](https://github.com/Sollute/sollute-react) - FRONT END desenvolvido em ReactJS.
- ### [Android](https://github.com/Sollute/sollute-android) - MOBILE desenvolvido em Kotlin.
- ### [Documentação](https://github.com/Sollute/Documentacao) - DOCUMENTAÇÃO do projeto.
