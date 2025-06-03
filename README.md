# Z-Alert API

Nossa solução foi construir uma API REST com Spring Boot para auxiliar na localização e gestão de pessoas durante eventos extremos, como enchentes, terremotos e apagões. Essa é parte da solução integrada para a **Global Solution 2025/1 - FIAP**.

## Objetivo
Oferecer uma API segura, escalável e documentada para ser consumida pelo aplicativo mobile (React Native).

---

## Endpoints Principais

### 🔐 Autenticação

#### `POST auth/register`
Faz o cadastro de usuários.

**Requisição:**
```json
{
  "nome": "Maria da Silva",
  "email": "usuario@email.com",
  "senha": "123456",
  "role": "USER"
}
```

---

#### `POST auth/login`
Gera um token JWT válido a partir de credenciais válidas.

**Requisição:**
```json
{
  "email": "usuario@email.com",
  "senha": "123456"
}
```

**Resposta:**
```json
{
  "token": "<JWT_TOKEN>"
}
```

Use o token nos headers seguintes para acessar os outros endpoints:
```
Authorization: Bearer <JWT_TOKEN>
```

---

### Endpoints de Recursos

Todos os recursos principais da API seguem um padrão RESTful com suporte a paginação, HATEOAS e autenticação via JWT. Cada um deles aceita requisições `GET`, `POST`, `PUT` e `DELETE`:

- `/api/usuarios`: gerenciamento de usuários da plataforma.
- `/api/alertas`: gerenciamento de alertas de desaparecimento em contextos de desastres.
- `/api/dependentes`: gerenciamento dos dependentes vinculados a um usuário principal.
- `/api/localizacoes`: gerenciamento das localizações associadas aos dependentes.
- `/api/dispositivos`: gerenciamento de dispositivos de rastreio e monitoramento.

---

## 🛠️ Tecnologias
- Java 18
- Spring Boot 3.2.4
- Spring Security + JWT
- Spring Data JPA
- Banco Oracle (via JDBC)
- Docker
- Gradle
- Swagger/OpenAPI (Documentação)

---

## Docker & Deploy (Render)

### Dockerfile usado
```dockerfile
FROM gradle:jdk21-graal AS BUILD
WORKDIR /usr/app/
COPY . .
RUN gradle fatJar

FROM openjdk:21-jdk-slim
COPY --from=BUILD /usr/app .
EXPOSE 8080
ENTRYPOINT exec java -jar build/libs/zalert-api-0.0.1-SNAPSHOT.jar
```

### Variáveis no Render
Configuradas via Dashboard:
- `JWT_SECRET`
- `DB_URL`
- `DB_USER`
- `DB_PASSWORD`

---

## Segurança
- Autenticação baseada em **JWT**
- O token JWT é obrigatório em todos os endpoints protegidos
- Utilize o header `Authorization: Bearer <token>` nas requisições

---

## Swagger
Disponível em:
```
GET /swagger-ui/index.html
```

---

## Equipe
- Felipe Menezes Prometti (RM555174) - 2TDSPM
- Maria Eduarda Pires Vieira (RM558976) - 2TDSPZ
- Samuel Damasceno Silva (RM558876) - 2TDSPM

---
