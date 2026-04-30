# TanninsDB API

API para gerenciamento de dados de taninos e compostos fenólicos.

## 🚀 Como Executar o Projeto Localmente

### 1. Requisitos
- Java 21
- Maven
- PostgreSQL (ou Docker para usar o `compose.yaml`)

### 2. Configuração de Variáveis de Ambiente
O projeto utiliza variáveis de ambiente para conexões sensíveis. Para testes locais simplificados, você pode definir valores padrão no seu IDE ou criar um perfil específico.

### 3. Executando com Perfil de Desenvolvimento
Para testar localmente sem precisar do login real do ORCID, é necessário ativar o perfil `dev`. Isso habilita o endpoint de login fake (`/auth/dev`).

**Via Terminal:**
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

**No IntelliJ IDEA:**
1. Vá em **Run** -> **Edit Configurations...**
2. No campo **Active Profiles**, digite: `dev`
3. Clique em **Apply** e rode o projeto novamente.

> **⚠️ Nota sobre Erro 403:** Se você receber um erro **403 Forbidden** ao acessar o `/auth/dev`, verifique nos logs de inicialização se o perfil `dev` está ativo. Procure pela linha: `The following 1 profile is active: "dev"`. Se não estiver ativo, o endpoint não existirá e a segurança bloqueará o acesso.

---

## 📖 Documentação (Swagger)

Com a aplicação rodando, acesse a documentação interativa em:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

---

## 🧪 Como Testar a API (Passo a Passo)

Como a API é protegida por JWT (JSON Web Token), siga estes passos no Swagger UI:

### Passo 1: Obter um Token de Acesso
Como você está em ambiente local (perfil `dev`), use o endpoint simplificado:
1. No Swagger, localize a seção **Dev Auth**.
2. Abra o endpoint `POST /auth/dev`.
3. Clique em **Try it out** e depois em **Execute** (não precisa preencher nada).
4. Se o perfil `dev` estiver ativo, você receberá um JSON com o `access_token`. Copie apenas o código entre aspas.

### Passo 2: Autorizar no Swagger
1. No topo da página do Swagger UI, clique no botão **Authorize** (ícone de cadeado).
2. No campo **Value**, cole o token que você copiou.
3. Clique em **Authorize** e depois em **Close**.

### Passo 3: Testar os Endpoints Protegidos
Agora você pode acessar os endpoints de **Artigos**:
1. Vá para `GET /api/artigos` ou `POST /api/artigos`.
2. Clique em **Try it out** e **Execute**.
3. A API responderá com os dados (ou salvará um novo artigo) usando a sua identidade de desenvolvedor.

---

## 🛠️ Tecnologias Principais
- **Spring Boot 3.4.2**
- **Spring Security + JWT**
- **Spring Data JPA (PostgreSQL)**
- **SpringDoc OpenAPI (Swagger)**
- **Lombok**
