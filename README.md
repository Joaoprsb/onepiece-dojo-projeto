# ☠️ One Piece Dojo API

API REST desenvolvida com Java e Spring Boot inspirada no universo de One Piece. O projeto gerencia piratas e missões, permitindo operações completas de CRUD, filtros, paginação e documentação automática com Swagger/OpenAPI.

---

# 🚀 Tecnologias utilizadas

* Java 21
* Spring Boot 3
* Spring Web
* Spring Data JPA
* PostgreSQL
* Hibernate
* Lombok
* Swagger / OpenAPI
* Maven

---

# 📚 Funcionalidades

## 👤 Piratas

* Criar pirata
* Buscar pirata por ID
* Atualizar pirata
* Remover pirata
* Listar todos os piratas com paginação
* Buscar piratas por raça

## 🏴‍☠️ Missões

* Criar missão
* Buscar missão por ID
* Atualizar missão
* Listar missões com paginação
* Buscar missões por classificação
* Buscar missões por status

---

# 🧠 Conceitos aplicados

O projeto foi desenvolvido com foco em boas práticas de APIs REST utilizando Spring Boot.

## Estrutura em camadas

O sistema foi organizado seguindo a arquitetura:

* Controller
* Service
* Repository
* DTO
* Exception Handler

---

## DTOs

Utilização de DTOs para evitar exposição direta das entidades e melhorar o controle dos dados trafegados na API.

---

## Validação de dados

Validação utilizando Jakarta Validation:

```java
@NotBlank
@NotNull
```

---

## Tratamento global de exceções

Uso de `@RestControllerAdvice` para tratamento centralizado de exceções.

Exemplo:

* Pirata não encontrado
* Erros de validação

---

## Paginação

Paginação implementada utilizando:

```java
Pageable
Page<T>
```

Exemplo:

```http
GET /piratas?page=0&size=5&sort=nome,asc
```

---

## Documentação Swagger/OpenAPI

A API possui documentação automática utilizando Swagger.

Após iniciar o projeto:

```http
http://localhost:8080/swagger-ui/index.html
```

---

# 🗂️ Estrutura do projeto

```text
src
 └── main
     ├── java
     │   └── com.dojo.OnePieceDojo
     │       ├── controllers
     │       ├── dtos
     │       ├── entities
     │       ├── enums
     │       ├── exception
     │       ├── repositories
     │       └── services
     └── resources
         └── application.properties
```

---

# ⚙️ Como executar o projeto

## 1. Clonar o repositório

```bash
git clone <URL_DO_REPOSITORIO>
```

---

## 2. Criar banco PostgreSQL

Crie um banco chamado:

```sql
CREATE DATABASE piratas;
```

---

## 3. Configurar o application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/piratas
spring.datasource.username=postgres
spring.datasource.password=sua_senha
```

---

## 4. Executar a aplicação

Pela IDE ou utilizando Maven:

```bash
./mvnw spring-boot:run
```

---

# 📌 Endpoints principais

## 👤 Piratas

| Método | Endpoint               | Descrição        |
| ------ | ---------------------- | ---------------- |
| POST   | `/piratas`             | Criar pirata     |
| GET    | `/piratas/{id}`        | Buscar por ID    |
| GET    | `/piratas`             | Listar piratas   |
| PUT    | `/piratas/{id}`        | Atualizar pirata |
| DELETE | `/piratas/{id}`        | Remover pirata   |
| GET    | `/piratas/raca/{raca}` | Buscar por raça  |

---

## 🏴‍☠️ Missões

| Método | Endpoint                                | Descrição                |
| ------ | --------------------------------------- | ------------------------ |
| POST   | `/missoes/criar`                        | Criar missão             |
| GET    | `/missoes/buscar/{id}`                  | Buscar missão por ID     |
| GET    | `/missoes/buscarTodas`                  | Listar missões           |
| PUT    | `/missoes/atualizar/{id}`               | Atualizar missão         |
| GET    | `/missoes/danger/{classificacaoMissao}` | Buscar por classificação |
| GET    | `/missoes/status/{statusMissao}`        | Buscar por status        |

---

# 🧪 Exemplo de JSON

## Criar Pirata

```json
{
  "nome": "Monkey D. Luffy",
  "raca": "HUMANO",
  "tripulacao": "CHAPEUS_DE_PALHA",
  "status": "ATIVO"
}
```

---

## Criar Missão

```json
{
  "classificacaoMissao": "ALTA",
  "tipoMissao": "RESGATE",
  "statusMissao": "EM_ANDAMENTO",
  "pirataId": 1
}
```

---

# 📖 Melhorias futuras

* Implementação de autenticação JWT
* Frontend em React
* Dockerização da aplicação
* Deploy em nuvem
* Testes unitários
* Testes de integração
* CI/CD

---

# 👨‍💻 Autor

Desenvolvido por João Pedro Barboza.

Projeto desenvolvido com foco em aprendizado de desenvolvimento backend com Java e Spring Boot.
