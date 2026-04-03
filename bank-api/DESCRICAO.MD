# 🏦 Bank API — Desafio Técnico Spring Boot

Esta é uma API REST desenvolvida em **Java com Spring Boot** para o gerenciamento de contas bancárias.
O projeto simula operações essenciais de um sistema bancário, como criação de conta, depósitos, saques e transferências, com regras de negócio rigorosas.

---

## 🎯 Objetivo do Projeto

O foco principal foi aplicar conceitos de:

* POO & Entity: Encapsulamento de regras de negócio e mapeamento relacional com JPA/Hibernate.
* DTO (Data Transfer Object): Padronização do tráfego de dados e segurança da API.
* Tratamento de Exceções: Controle de erros para garantir respostas HTTP precisas.

Garantindo a integridade dos dados através de **transações seguras (@Transactional)**.

---

## 🛠️ Tecnologias Utilizadas

* Java 21
* Spring Boot 3.x
* Spring Data JPA (Persistência de dados)
* MySQL (Banco de dados relacional)
* Lombok (Produtividade)
* Maven (Gerenciador de dependências)

---

## 🏗️ Arquitetura

O projeto segue a separação de responsabilidades em camadas:

* **Controller** → Exposição dos endpoints REST
* **Service** → Regras de negócio e controle transacional
* **Repository** → Comunicação com o banco de dados
* **Entity** → Mapeamento objeto-relacional (ORM)
* **DTO (Data Transfer Object)** → Transporte de dados entre cliente e servidor

---

## 🚀 Como Rodar o Projeto

### 1. Pré-requisitos

* Java 21 instalado
* MySQL rodando localmente
* Postman ou Insomnia para testes

---

### 2. Configuração do Banco

No seu MySQL, crie o schema:

```sql
CREATE DATABASE banco_api;
```

---

### 3. Configuração do `application.yml`

Ajuste as credenciais no arquivo:

`src/main/resources/application.yml`

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/banco_api
    username: seu_usuario
    password: sua_senha
  jpa:
    hibernate:
      ddl-auto: update
```

---

### 4. Execução

Execute o projeto via terminal ou sua IDE favorita:

```bash
mvn spring-boot:run
```

---

## 📌 Endpoints da API

| Método | Endpoint                    | Descrição                       |
| ------ |-----------------------------| ------------------------------- |
| POST   | `/accounts`                 | Cria uma nova conta bancária    |
| POST   | `/accounts/{id}/depositary` | Realiza um depósito             |
| POST   | `/accounts/{id}/withdraw`   | Realiza um saque (valida saldo) |
| POST   | `/accounts/transfer`        | Transfere valores entre contas  |

---

## 🧠 Regras de Negócio Implementadas

* ✅ **Saldo Inicial**: Toda conta nasce com saldo `0.00`
* ✅ **Validação de Nome**: Não é permitido nome vazio ou nulo
* ✅ **Integridade no Saque**: Não permite sacar mais que o saldo
* ✅ **Validação de Depósito**: Apenas valores positivos
* ✅ **Segurança na Transferência**: Uso de `@Transactional` para garantir rollback em caso de erro

---

## 🧪 Testes com Postman

🔗 Acesse a collection completa:
[Link do Postman](https://borgeskaua1210-9811126.postman.co/workspace/Kaua-Pereira's-Workspace~76e08313-6a1d-4734-8851-e81b26b058bf/collection/52742868-2fd996fd-6546-44b5-bf41-7bddded67daf?action=share&creator=52742868)
---

## 👤 Autor

**Kauã Pereira da Silva Borges**
🔗

---


