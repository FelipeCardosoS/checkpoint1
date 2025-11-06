# API de Pacientes — Spring Boot

**Autores:** Felipe Cardoso (RM 99062) · Carlos Augusto (RM 98456)

Uma API REST simples para gerenciar pacientes (CRUD completo). Projeto com **tests de integração**, **CI/CD no GitHub Actions** e **imagem Docker publicada no Docker Hub**.

---

## ✨ O que está pronto
- **CRUD de Paciente** (`/pacientes`): `GET` lista e por id, `POST`, `PUT`, `DELETE`.
- **Swagger/OpenAPI**: documentação em `http://localhost:8080/swagger-ui.html`.
- **Testes de Integração (IT)**: validam o fluxo completo do CRUD.
- **CI**: build Maven + testes rodando em `develop` e `main`.
- **CD**: 
  - Integração (roda os ITs).
  - Publicação no **Docker Hub** em pushes/PRs para `develop` e `main`.
  - Na `main`, publica tag estável (ex.: `latest`).

> Repositório da imagem: `felipescalesse/cp1-products-api-felipe-carlos`

---

## 🚀 Como rodar local (Maven)
Requisitos: **JDK 17** e **Maven 3.9+**.

```bash
# build + testes
mvn clean verify

# executar
mvn spring-boot:run
# app em http://localhost:8080
```

Abrir o **Swagger**:
```
http://localhost:8080/swagger-ui.html
```

---

## 🐳 Rodando com Docker
> Requer Docker Desktop/Engine instalado.

Baixar a imagem (ajuste a tag se necessário):
```bash
docker pull felipescalesse/cp1-products-api-felipe-carlos:latest
```

Subir o container:
```bash
docker run --rm -p 8080:8080 felipescalesse/cp1-products-api-felipe-carlos:latest
```

---

## 🔗 Endpoints principais
```
GET    /pacientes
GET    /pacientes/{id}
POST   /pacientes
PUT    /pacientes/{id}
DELETE /pacientes/{id}
```

### Exemplo de payload (POST/PUT)
```json
{
  "nome": "Pedro",
  "endereco": "Rua X",
  "bairro": "Centro",
  "email": "pedro@teste.com",
  "telefone": "11999999999"
}
```

---

## 🧪 Testes de Integração (IT)
Os testes vivem em `src/test/java/.../controller/PacienteControllerIT.java` e cobrem:
- criação → leitura → listagem → atualização → exclusão → leitura 404

Execute somente os testes:
```bash
mvn -Dtest=PacienteControllerIT test
```

---

## ⚙️ CI/CD (GitHub Actions)
Workflows configurados:
- **CI - Maven**: Build + testes (em `develop` e `main`).
- **CD - Integração (CRUD)**: Executa os testes de integração a cada push/PR.
- **CD - DockerHub**: Build e push da imagem para o Docker Hub.
  - Em `develop`: publica com tag baseada no commit.
  - Em `main`: publica também a tag `latest`.

> Dica: proteger a branch `main` exigindo CI verde antes de merge.

---

## 🧩 Tecnologias
- **Java 17** · **Spring Boot 3** · **Spring Web** · **Spring Data JPA (H2 em memória para IT)**
- **JUnit 5** · **AssertJ** · **springdoc-openapi** (Swagger)  
- **Maven** · **Docker** · **GitHub Actions**

---

## 📁 Estrutura (essencial)
```
src
 ├─ main
 │   └─ java/br/com/fiap/checkpoint1
 │       ├─ controller/PacienteController.java
 │       ├─ dto/PacienteDTO.java
 │       ├─ service/PacienteService.java
 │       ├─ service/PacienteServiceImpl.java
 │       └─ Checkpoint1Application.java
 └─ test
     └─ java/br/com/fiap/checkpoint1/controller/PacienteControllerIT.java
```

---

## 📄 Licença
Uso educacional — FIAP (checkpoint).
