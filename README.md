<div align="center">

# 🚗 Patio Manager API

### API REST para gestão de pátio de concessionárias

[![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.6-green?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity)](https://spring.io/projects/spring-security)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18-blue?style=for-the-badge&logo=postgresql)](https://www.postgresql.org)
[![Docker](https://img.shields.io/badge/Docker-Containerizado-2496ED?style=for-the-badge&logo=docker)](https://www.docker.com)

</div>

---

## 💡 Motivação

Tudo começou em um dia comum, voltando pra casa no ônibus.

Passei na frente de uma concessionária e vi um funcionário do lado de fora, no sol, com uma **planilha de papel na mão** anotando os carros do pátio.

Ali surgiu a ideia: **pequenas concessionárias não precisam de software caro. Precisam de algo que funcione.**

O Patio Manager API nasceu pra resolver exatamente isso — substituir a planilha de papel por uma API que controla em tempo real quais carros estão disponíveis, vendidos ou reservados.

---

## 📋 Sobre o Projeto

O **Patio Manager API** é um sistema de gestão de pátio para concessionárias desenvolvido com Java e Spring Boot. A API permite o controle completo do estoque de veículos, com autenticação JWT, tratamento de erros centralizado e testes unitários.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Finalidade |
|------------|--------|------------|
| Java | 17 | Linguagem principal |
| Spring Boot | 4.0.6 | Framework backend |
| Spring Security | 7.0.5 | Autenticação e autorização |
| JWT (Auth0) | 4.4.0 | Geração e validação de tokens |
| Spring Data JPA | 4.0.6 | Persistência de dados |
| PostgreSQL | 18 | Banco de dados relacional |
| Docker | latest | Containerização do banco |
| JUnit 5 | 6.0.3 | Testes unitários |
| Mockito | 5.20.0 | Mock de dependências nos testes |
| Lombok | 1.18 | Redução de boilerplate |
| Bean Validation | 3.1.1 | Validação de dados de entrada |
| Maven | 3.x | Gerenciamento de dependências |

---

## 🏗️ Arquitetura

O projeto segue a arquitetura em camadas, separando responsabilidades de forma clara:

```
src/
└── main/
    └── java/
        └── com/carros/data/
            ├── controller/     # Endpoints REST + filtro JWT + handler de erros
            ├── service/        # Regras de negócio + autenticação + token
            ├── repository/     # Acesso ao banco de dados
            └── model/          # Entidades, enums e DTOs
```

---

## 🔐 Autenticação

A API utiliza autenticação stateless com JWT:

1. O usuário faz `POST /auth/login` com login e senha
2. A API valida as credenciais via Spring Security com BCrypt
3. Um token JWT é gerado e retornado (validade de 2 horas)
4. O cliente envia o token no header `Authorization: Bearer <token>`
5. O `JwtFilter` intercepta cada requisição e valida o token antes de chegar ao controller

Rotas protegidas retornam `403 Forbidden` sem token válido.

---

## 🔗 Endpoints

### Autenticação

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| `POST` | `/auth/login` | Autentica e retorna token JWT | Público |

### Carros

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| `GET` | `/api/Carro` | Lista todos os carros | Token |
| `POST` | `/api/Carro` | Cadastra um novo carro | Token |
| `PUT` | `/api/Carro/{id}` | Atualiza dados de um carro | Token |
| `DELETE` | `/api/Carro/{id}` | Remove um carro | Token |

### Status disponíveis

```json
"status": "DISPONIVEL"
"status": "VENDIDO"
```

---

## 📦 Como Rodar o Projeto

### Pré-requisitos

- [Java 17+](https://adoptium.net)
- [Docker](https://www.docker.com/products/docker-desktop)
- [Maven](https://maven.apache.org)

### 1. Clone o repositório

```bash
git clone https://github.com/GabrielAraujoFS/patio-manager-api.git
cd patio-manager-api
```

### 2. Suba o banco de dados com Docker

```bash
docker run --name postgres-carros \
  -e POSTGRES_PASSWORD=admin \
  -e POSTGRES_USER=admin \
  -e POSTGRES_DB=carros \
  -p 5432:5432 \
  -d postgres
```

### 3. Configure o `application.properties`

Crie o arquivo `src/main/resources/application.properties` baseado no exemplo:

```properties
spring.application.name=data
spring.datasource.url=jdbc:postgresql://localhost:5432/carros
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
api.security.token.secret=sua-chave-secreta
```

### 4. Rode a aplicação

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`

---

## 📬 Exemplo de Uso

### 1. Fazer login e obter token

```http
POST /auth/login
Content-Type: application/json

{
    "login": "gabriel",
    "senha": "123456"
}
```

Resposta:
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### 2. Usar o token nas requisições

```http
GET /api/Carro
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### 3. Cadastrar um carro

```http
POST /api/Carro
Authorization: Bearer <token>
Content-Type: application/json

{
    "modelo": "HB20",
    "placa": "ABC-1234",
    "cor": "Preto",
    "status": "DISPONIVEL"
}
```

---

## 🧪 Testes

O projeto possui testes unitários cobrindo as principais regras de negócio:

```bash
./mvnw test
```

Testes implementados no `CarroServiceTest`:
- `deveListarTodosOsCarros` — verifica se a listagem retorna corretamente
- `deveSalvarCarro` — verifica se o carro é salvo e retornado
- `deveLancarExcecaoQuandoCarroNaoEncontrado` — verifica se `CarroNotFoundException` é lançada

---

## 🗺️ Roadmap

- [x] CRUD de veículos
- [x] Filtro por status (DISPONIVEL / VENDIDO)
- [x] Banco de dados PostgreSQL containerizado com Docker
- [x] Tratamento de erros centralizado com @ControllerAdvice
- [x] Validações com Bean Validation
- [x] Autenticação com Spring Security + JWT
- [x] Testes unitários com JUnit 5 e Mockito
- [ ] Docker Compose
- [ ] Deploy na AWS (EC2 + RDS)
- [ ] Frontend para visualização do pátio

---

## 👨‍💻 Autor

**Gabriel Araujo**

[![GitHub](https://img.shields.io/badge/GitHub-GabrielAraujoFS-181717?style=for-the-badge&logo=github)](https://github.com/GabrielAraujoFS)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Gabriel_Araujo-0A66C2?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/gabriel-araujo-farias)

---

<div align="center">
  <i>Projeto desenvolvido com propósito real — porque pequenas concessionárias merecem tecnologia que funciona.</i>
</div>
