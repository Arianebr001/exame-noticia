Relatório do Projeto: Aplicativo de Notícias
1. Título e Tema do Projeto
Título: "Aplicativo de Notícias com Integração de API REST"

Resumo: Este aplicativo tem como objetivo fornecer aos usuários uma plataforma para acessar notícias atualizadas. O aplicativo utiliza Kotlin e Jetpack Compose para a interface do usuário, Room Database para persistência local de dados, e uma API REST para buscar e gerenciar notícias remotamente. O aplicativo permite que os usuários visualizem, pesquisem e gerenciem notícias, com operações CRUD (Create, Read, Update, Delete) integradas.
2. Estrutura da Aplicação
A aplicação será desenvolvida utilizando as seguintes tecnologias:

Kotlin: Linguagem principal para desenvolvimento do aplicativo Android.

Jetpack Compose: Utilizado para construir as interfaces de usuário (UI), como telas de login, home, detalhes de notícias, etc.

Room Database: Usado para persistir dados localmente no dispositivo, como informações de usuários e notícias favoritas.

API REST: Criada para gerenciar as interações entre o front-end (Jetpack Compose) e o banco de dados remoto. A API permitirá a comunicação de dados, como listagem de notícias, busca e operações CRUD.

Componentes principais da aplicação:
Tela Inicial (Splash Screen):

Exibe o logo ou uma mensagem de boas-vindas por alguns segundos antes de redirecionar para a tela de login.

Tela de Login:

Permite a autenticação do usuário com um sistema básico de login, onde os dados de login podem ser validados localmente ou via API REST.

Validação de campos obrigatórios (usuário e senha).

Mensagens de erro em caso de falha de autenticação.

Tela de Cadastro de Usuário:

Cadastro de novos usuários, incluindo validação de campos obrigatórios (nome, e-mail, senha).

O cadastro pode ser opcional ou obrigatório, dependendo da escolha do estudante.

Tela Principal (Home Screen):

Exibe a lista de notícias utilizando LazyColumn (componente de listagem do Jetpack Compose).

A lista será populada a partir de dados recebidos da API REST.

Tela de Detalhes da Notícia:

Exibe os detalhes completos de uma notícia selecionada, como título, descrição, data de publicação, etc.

Permite ao usuário favoritar a notícia, que será armazenada localmente no Room Database.

Tela de Busca de Notícias:

Permite pesquisar notícias com base em critérios como título, descrição ou data.

A busca será realizada via API REST, buscando dados remotamente.
3. Funcionalidades e Tecnologias Usadas
Kotlin: Linguagem principal para desenvolvimento do aplicativo Android.

Jetpack Compose: Usado para construção das interfaces de usuário (UI), como telas de login, home, detalhes de notícias, etc.

Room Database: Usado para persistir dados localmente no dispositivo (informações de login, notícias favoritas).

API REST: Criada para gerenciar as interações entre o front-end (Jetpack Compose) e o banco de dados remoto. A API permitirá a comunicação de dados, como listagem de notícias, busca e operações CRUD.

Splash Screen: Exibe uma tela inicial de boas-vindas, mostrando o logo ou nome do aplicativo.

Validações de Campos: A aplicação deve garantir que os campos obrigatórios estejam preenchidos corretamente ao fazer login, cadastro e ao adicionar/editar notícias.

4. Design e Interface de Usuário
Tela de Login:

Campos de entrada para nome de usuário e senha.

Botão para autenticação.

Mensagens de erro em caso de falha no login.

Validação dos campos de login e senha.

Tela de Home:

Lista de notícias, com títulos e descrições.

Opção de favoritar notícias.

A lista de notícias deve ser carregada a partir da API REST.

Tela de Detalhes da Notícia:

Exibe os detalhes completos de uma notícia selecionada.

Botão para favoritar a notícia.

Tela de Busca:

Caixa de pesquisa para buscar notícias por título, descrição ou data.

5. Backend: API REST
Objetivo: Criar uma API REST para gerenciar dados como notícias e usuários. A API será responsável por realizar operações CRUD no servidor.

Tecnologias sugeridas:

NodeJS ou Spring Boot (para backend em Kotlin).

A API REST deve ser capaz de realizar operações como:

POST para criar novas notícias.

GET para listar ou buscar notícias.

PUT/PATCH para atualizar as informações de notícias.

DELETE para remover notícias.
Rota da API REST:

/api/news: para criação e listagem de notícias.

/api/news/{id}: para edição e remoção de notícias.

/api/news/search: para busca de notícias.

Integração com o Frontend:

O frontend (Jetpack Compose) irá consumir essas APIs usando Retrofit ou Ktor Client para comunicação HTTP.
. Resumo e Explicação da Aplicação
O aplicativo de notícias foi desenvolvido com Kotlin e Jetpack Compose. Ele permite que os usuários visualizem, pesquisem e gerenciem notícias, com operações CRUD integradas. A aplicação se comunica com uma API REST para armazenar e gerenciar os dados de forma remota, enquanto utiliza o Room Database para persistir dados localmente, como notícias favoritas.
