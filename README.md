# 📊 Pesquisa Eleitoral

Aplicativo Android desenvolvido para auxiliar na realização e no armazenamento de **pesquisas eleitorais**, permitindo registrar respostas de entrevistados por meio de diferentes tipos de perguntas.

O projeto foi desenvolvido utilizando **Kotlin** no **Android Studio**, com foco na aplicação prática de conceitos de desenvolvimento mobile, navegação entre telas, persistência de dados e organização de um projeto Android.

---

## 📱 Sobre o projeto

O **Pesquisa Eleitoral** é uma aplicação mobile criada para simular a coleta de dados de uma pesquisa eleitoral.

A aplicação permite que um entrevistador percorra diferentes etapas da pesquisa, registrando as respostas fornecidas pelos participantes.

Entre os tipos de pesquisa utilizados no sistema estão perguntas como:

- Pesquisa espontânea de intenção de voto;
- Pesquisa estimulada;
- Levantamento de problemas considerados importantes pelo entrevistado;
- Cadastro e identificação dos entrevistados;
- Consulta dos dados coletados.

O projeto possui também uma área administrativa para gerenciamento e visualização das informações registradas.

---

## 🎯 Objetivo

O objetivo do projeto é desenvolver uma aplicação Android capaz de representar o fluxo de uma pesquisa eleitoral, aplicando conceitos estudados durante o curso de **Desenvolvimento de Software Multiplataforma**.

Entre os principais conceitos trabalhados estão:

- Desenvolvimento Android;
- Programação em Kotlin;
- Navegação entre `Activities`;
- Manipulação de componentes de interface;
- Persistência de dados;
- Banco de dados local;
- Programação assíncrona;
- Organização e versionamento de código com Git e GitHub.

---

## ⚙️ Funcionalidades

Atualmente o projeto contempla funcionalidades relacionadas ao fluxo de pesquisa, incluindo:

- 🔐 Tela de login;
- 📝 Início de uma nova pesquisa;
- 🗳️ Registro de respostas da pesquisa eleitoral;
- 📋 Navegação entre diferentes etapas do questionário;
- 👤 Registro de informações relacionadas aos entrevistados;
- 💾 Persistência local das informações coletadas;
- 📊 Consulta de resultados da pesquisa;
- 👨‍💼 Área administrativa;
- 🔄 Navegação entre as diferentes telas da aplicação.

> O projeto encontra-se em desenvolvimento e novas funcionalidades podem ser adicionadas ao longo da implementação.

---

## 🛠️ Tecnologias utilizadas

| Tecnologia | Utilização |
|---|---|
| **Kotlin** | Linguagem principal da aplicação |
| **Android Studio** | Ambiente de desenvolvimento |
| **XML** | Construção das interfaces |
| **Android SDK** | Desenvolvimento da aplicação Android |
| **Room Database** | Persistência local dos dados |
| **SQLite** | Banco de dados utilizado pelo Room |
| **Kotlin Coroutines** | Operações assíncronas |
| **Lifecycle Scope** | Execução de Coroutines associadas ao ciclo de vida das Activities |
| **Gradle** | Gerenciamento de dependências e build |
| **Git** | Controle de versão |
| **GitHub** | Hospedagem e colaboração no projeto |

---

## 🧱 Estrutura do projeto

A estrutura principal segue o padrão tradicional de aplicações Android:

```text
Pesquisa_Eleitoral
│
├── app
│   └── src
│       └── main
│           │
│           ├── java
│           │   └── com.example.pesquisaeleitoral
│           │       ├── MainActivity.kt
│           │       ├── LoginActivity.kt
│           │       ├── Pesquisa1Activity.kt
│           │       ├── Pesquisa2Activity.kt
│           │       └── ...
│           │
│           ├── res
│           │   ├── layout
│           │   ├── drawable
│           │   ├── mipmap
│           │   └── values
│           │
│           └── AndroidManifest.xml
│
├── gradle
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

---

## 🔄 Fluxo da aplicação

De forma simplificada, o fluxo da aplicação segue a estrutura:

```text
Inicialização
      │
      ▼
    Login
      │
      ▼
  Menu / Pesquisa
      │
      ▼
Dados do entrevistado
      │
      ▼
Pesquisa espontânea
      │
      ▼
Pesquisa estimulada
      │
      ▼
Outras perguntas
      │
      ▼
Salvar pesquisa
      │
      ▼
Banco de dados
```

Na área administrativa, os dados registrados podem posteriormente ser utilizados para consultas e apresentação dos resultados.

---

## 💾 Banco de dados

A aplicação utiliza o **Room Database**, biblioteca oficial do Android para persistência local baseada em SQLite.

O Room permite estruturar o banco utilizando componentes como:

```text
Entity
   │
   ▼
DAO
   │
   ▼
RoomDatabase
   │
   ▼
Application
```

As informações das pesquisas podem ser armazenadas localmente e posteriormente consultadas para geração dos resultados.

---

## ⚡ Coroutines

Algumas operações de banco de dados são executadas utilizando **Kotlin Coroutines**.

Por exemplo:

```kotlin
lifecycleScope.launch {
    // operação assíncrona
}
```

O `lifecycleScope` permite executar uma Coroutine associada ao ciclo de vida da `Activity`, evitando que tarefas continuem sendo executadas depois que a tela for destruída.

---

## ▶️ Como executar o projeto

### Pré-requisitos

Para executar o projeto é necessário possuir:

- Android Studio;
- Android SDK configurado;
- JDK compatível com a versão do Gradle utilizada;
- Emulador Android ou dispositivo físico com modo desenvolvedor ativado;
- Git instalado, caso deseje clonar o projeto.

---

### 1. Clone o repositório

```bash
git clone https://github.com/Digowsra/Pesquisa_Eleitoral.git
```

---

### 2. Entre na pasta do projeto

```bash
cd Pesquisa_Eleitoral
```

---

### 3. Abra no Android Studio

No Android Studio:

```text
File
→ Open
→ Pesquisa_Eleitoral
```

Aguarde a sincronização do **Gradle**.

---

### 4. Execute a aplicação

Escolha um dispositivo virtual ou físico e utilize:

```text
Run ▶
```

ou:

```text
Shift + F10
```

---

## 🌿 Desenvolvimento com Git

Durante o desenvolvimento são utilizadas branches para separar novas funcionalidades da versão principal do projeto.

Exemplo:

```bash
git checkout -b feature/nova-funcionalidade
```

Após realizar as alterações:

```bash
git add .
```

```bash
git commit -m "implementa nova funcionalidade"
```

```bash
git push -u origin feature/nova-funcionalidade
```

Posteriormente as alterações podem ser integradas à branch principal utilizando **Pull Requests** ou merges controlados.

---

## 🚀 Possíveis melhorias futuras

Algumas melhorias que podem ser adicionadas ao projeto:

- Gráficos para visualização dos resultados;
- Percentual de votos por candidato;
- Filtros por idade, gênero ou região;
- Exportação das pesquisas;
- Dashboard administrativo;
- Melhorias na interface;
- Validação avançada dos formulários;
- Sistema de usuários;
- Sincronização com banco de dados em nuvem;
- API para armazenamento remoto das pesquisas.

---

## 🎓 Contexto acadêmico

Projeto desenvolvido como atividade acadêmica do curso:

**Desenvolvimento de Software Multiplataforma — DSM**

com o objetivo de aplicar conceitos relacionados ao desenvolvimento de aplicações mobile utilizando Android e Kotlin.

---

## 👥 Colaboração

Este projeto foi desenvolvido de forma colaborativa utilizando **Git e GitHub**, permitindo que diferentes integrantes trabalhem simultaneamente através de branches.

Contribuições realizadas no projeto podem ser acompanhadas pelo histórico de commits e branches do repositório.

---

## 📌 Status do projeto

🚧 **Em desenvolvimento**

O projeto está sendo continuamente aprimorado conforme novas funcionalidades e requisitos são implementados.

---

## 🔗 Repositório

GitHub:

`https://github.com/Digowsra/Pesquisa_Eleitoral`

---

## 📄 Licença

Este projeto possui finalidade **acadêmica e educacional**.
