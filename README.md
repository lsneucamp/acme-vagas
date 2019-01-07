# SpringBoot + Spring Data Rest

## Motivações

Usei `Spring Data Rest` para criar as APIs das classes, o padrão que é usado para expor os serviços é `HATEOAS`;
Existe um frontend `http://localhost:10001/api/browser` que funciona tipo um `swagger-ui` simples para HATEOAS;

O banco de dados é `mongodb`, criei um versionamento simples do banco usando uma lib chamada `mongeez` que é tipo um liquibase para mongodb.

O `Docker` foi criado, usando `MultiStageBuild` e o `docker-compose` para facilitar o teste.

A arquitetura é `Event-Driven` usando os eventos do Spring Data como pode ser visto na classe [CandidaturaEventListener](src/main/java/co/acme/vagasapi/eventlistener/CandidaturaEventListener.java). Nesse caso, é um evento que é isolado nessa aplicação;





## Prerequisitos
Para Executar esse projeto é necessário ter instalado as seguintes ferramentas:

* MongoDB 4.x > (Somente para executar testes)
* Maven 3.x > (Somente para executar testes)
* Docker 18.x >


## TL:TR; Construir o projeto com docker-compose

```
docker-compose up --build
```


### Acessando HAL BROWSER

Entrar no navegador a URL: http://localhost:10001/api/browser


## Executando os testes

### Prerequisitos

* MongoDB 4.x >
* Maven 3.x >
* Docker 18.x >


### MongoDB rodando localmente em um container
É necessário que o mongo esteja configurado como um `replicaSet` para que suporte transações.

Para executar o mongo localmente, execute o comando abaixo:
```
docker run -d -p 27017:27017 --name mongodb_test \
  -e MONGODB_REPLICA_SET_MODE=primary \
   bitnami/mongodb:latest
```

### Executar os testes
Caso você
Depois que instalar as ferramentas execute na pasta raiz do projeto:

```sh
$ mvn clean test
```

Uma vez que os testes passarem, você pode iniciar seu servidor embutido do tomcat:
```sh
$ mvn spring-boot:run
```
O servidor irá subir no seguinte endereço:
* http://localhost:10001

Para testar se está retornado dados é so abrir no navegador o endereço com os parâmetros http://localhost:8080/api/v1/nodes/

## 2. Usando a API

### 2.1 Criando uma `Vaga`

URL da API: `http://localhost:10001/api/vagas`:

Criar uma vaga realizando uma requisição tipo `POST` usando o JSON abaixo:

```json
{
    "empresa": "Acme co",
    "titulo": "Software Engineer",
    "descricao": "Vaga :)",
    "localizacao": "F",
    "nivel": 5
}
```

Ou usando o comando `cURL`:

```sh
curl -X POST \
  http://localhost:10001/api/vagas \
  -H 'Content-Type: application/json' \
  -d '{
    "empresa": "Acme co",
    "titulo": "Engenheiro de Software",
    "descricao": "Vaga :)",
    "localizacao": "F",
    "nivel": 5
}'
```

A resposta será no formato HAL, é preciso pegar a referência `href` da vaga criada para criar uma `candidatura` .

Exemplo, o `href` da resposta HAL abaixo:

```json
{
    "empresa": "Acme co",
    "titulo": "Engenheiro de Software",
    "descricao": "Vaga :)",
    "localizacao": "F",
    "nivel": 5,
    "_links": {
        "self": {
            "href": "http://localhost:10001/api/vagas/5c3263327185795e7d089d6d"
        },
        "vaga": {
            "href": "http://localhost:10001/api/vagas/5c3263327185795e7d089d6d"
        }
    }
}

```

### 2.2 Criando uma `Pessoa`

URL da API: `http://localhost:10001/api/pessoas`

Criar uma pessoa realizando uma requisição tipo `POST` usando o JSON abaixo:
```json
{
    "nome": "Luciano Neucamp",
    "profissao": "Eng. de Software",
    "localizacao": "A",
    "nivel": 4
}
```

Ou usando o `cURL` abaixo:
```
curl -X POST \
  http://localhost:10001/api/pessoas \
  -H 'Content-Type: application/json' \
  -d '{
    "nome": "Luciano Neucamp",
    "profissao": "Eng. de Software",
    "localizacao": "A",
    "nivel": 4
}'
```


A resposta será no formato HAL, é preciso pegar a referência `href` da pessoa criada para criar uma `candidatura` .

Exemplo, o `href` da resposta HAL abaixo:

```json
{
    "nome": "Luciano Neucamp",
    "profissao": "Eng. de Software",
    "localizacao": "A",
    "nivel": 4,
    "_links": {
        "self": {
            "href": "http://localhost:10001/api/pessoas/5c326c497185795e7d089d6f"
        },
        "pessoa": {
            "href": "http://localhost:10001/api/pessoas/5c326c497185795e7d089d6f"
        }
    }
}

```


### 2.3 Criando uma `Candidatura`

Para criar uma candidatura é necessário pegar a referências(`href`) da `vaga` e a `pessoa` que foram criadas nos passos anteriores;
Exemplo do JSON para criar a Candidatura:
```json
{
    "pessoa":"http://localhost:10001/api/pessoas/5c2d4a728afb1841c8aaa536",
    "vaga":"http://localhost:10001/api/vagas/5c2d4a728afb1841c8aaa538"
}
```

Usando o JSON acima, é so realizar uma requisição tipo `POST` na URL:

A resposta deve ser algo como JSON abaixo:

```json
{
    "pontuacao": 62,
    "_links": {
        "self": {
            "href": "http://localhost:10001/api/candidaturas/5c32714f7185796966f9dff9"
        },
        "candidatura": {
            "href": "http://localhost:10001/api/candidaturas/5c32714f7185796966f9dff9"
        },
        "vaga": {
            "href": "http://localhost:10001/api/candidaturas/5c32714f7185796966f9dff9/vaga"
        },
        "pessoa": {
            "href": "http://localhost:10001/api/candidaturas/5c32714f7185796966f9dff9/pessoa"
        }
    }
}

```

### Filtrando candidaturas por ID da vaga e ordernando as  por `pontuacao`

Para buscar o ID da vaga você deve copiar da `href` da vaga que você salvou;
Por exemplo: `http://localhost:10001/api/candidaturas/5c32714f7185796966f9dff9/vaga` o id é `5c32714f7185796966f9dff9`

Agora é so substituir `{vagaId}` da URL abaixo e fazer uma requisição tipo `GET`:
```
http://localhost:10001/api/candidaturas/search/findByVagaId?vagaId={vagaId}&sort=pontuacao,desc

```

Ou usando o comando cURL, é so substituir `{vagaId}`:
```
curl -X GET \
  'http://localhost:10001/api/candidaturas/search/findByVagaId?vagaId={vagaId}&sort=pontuacao,desc' \
  -H 'Content-Type: application/json'
```