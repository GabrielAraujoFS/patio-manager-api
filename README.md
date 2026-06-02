<div align="center">

# 🚗 Patio Manager API

### API REST para gestão de pátio de concessionárias

[![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.6-green?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
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

O **Patio Manager API** é um sistema de gestão de pátio para concessionárias desenvolvido com Java e Spring Boot. A API permite o controle completo do estoque de veículos, desde o cadastro até a atualização de status de venda.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Finalidade |
|------------|--------|------------|
| Java | 17 | Linguagem principal |
| Spring Boot | 4.0.6 | Framework backend |
| Spring Data JPA | 4.0.6 | Persistência de dados |
| PostgreSQL | 18 | Banco de dados relacional |
| Docker | latest | Containerização do banco |
| Lombok | 1.18 | Redução de boilerplate |
| Maven | 3.x | Gerenciamento de dependências |

---

## 🏗️ Arquitetura

O projeto segue a arquitetura em camadas, separando responsabilidades de forma clara:

```
src/
└── main/
    └── java/
        └── com/carros/data/
            ├── controller/     # Camada de apresentação — endpoints REST
            ├── service/        # Camada de negócio — regras da aplicação
            ├── repository/     # Camada de dados — acesso ao banco
            └── model/          # Entidades e enums
```

---

## 🔗 Endpoints

### Carros

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/Carro` | Lista todos os carros |
| `POST` | `/api/Carro` | Cadastra um novo carro |
| `GET` | `/api/Carro/status/{status}` | Filtra carros por status |
| `PUT` | `/api/Carro/{id}` | Atualiza dados de um carro |
| `DELETE` | `/api/Carro/{id}` | Remove um carro |

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

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/carros
spring.datasource.username=admin
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=update
```

### 4. Rode a aplicação

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`

---

## 📬 Exemplo de Uso

### Cadastrar um carro

```http
POST /api/Carro
Content-Type: application/json

{
    "modelo": "HB20",
    "placa": "ABC-1234",
    "cor": "Preto",
    "status": "DISPONIVEL"
}
```

### Resposta

```json
{
    "id": "8edeb3d6-773c-4683-893a-7a5f7f1ce5c6",
    "modelo": "HB20",
    "placa": "ABC-1234",
    "cor": "Preto",
    "status": "DISPONIVEL"
}
```

---

## 🗺️ Roadmap

- [x] CRUD de veículos
- [x] Filtro por status (DISPONIVEL / VENDIDO)
- [x] Banco de dados PostgreSQL containerizado com Docker
- [ ] Testes unitários com JUnit e Mockito
- [ ] Autenticação com Spring Security + JWT
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
