# ProjetoCRUDJava

API REST desenvolvida em **Java** com **Spring Boot** para gerenciamento de clientes (CRUD), integrada ao **MySQL** via **Spring Data JPA / Hibernate**.

## Tecnologias utilizadas
- **Java**
- **Spring Boot** (Spring Web, Spring Data JPA)
- **MySQL**
- **Maven**

## Estrutura do projeto
- `controller`: endpoints HTTP da aplicação (ex.: `/clientes`)
- `model`: entidades mapeadas para o banco de dados
- `repository`: interfaces de acesso a dados com Spring Data JPA
- `service`: regras de negócio da aplicação

## Como executar localmente
1. Clone o repositório:
   ```bash
   git clone [https://github.com/gustavogregorio1986/ProjetoCRUDJava.git](https://github.com/gustavogregorio1986/ProjetoCRUDJava.git


CREATE DATABASE projetocrud;

spring.application.name=ProjetoCRUD
server.port=8081

spring.datasource.url=jdbc:mysql://localhost:3306/projetocrud?createDatabaseIfNotExist=false&serverTimezone=UTC
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.flyway.enabled=false
spring.jpa.hibernate.ddl-auto=update
   
