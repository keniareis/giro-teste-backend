# Giro.Tech - Desafio Técnico Frontend

---
## **1. Introdução**
Este teste tem como objetivo avaliar sua capacidade de desenvolver um sistema que consome uma API externa e exibe seus dados de forma intuitiva. Queremos ver como você estrutura seu código, consome APIs e desenvolve aplicações web.

Sua missão é criar um **pequeno painel financeiro** que exiba as **taxas de juros do Brasil** e as **cotações de moedas**, obtidas a partir da [HG Brasil Finance API](https://console.hgbrasil.com/documentation/finance).

Para isso, você deverá desenvolver:  
- **Frontend:** Criar uma interface para exibir as informações extraídas da API.  
- **Consumo de API:** Integrar diretamente com a API da HG Brasil Finance.


---

## **2. Definição do Desafio**

- Apresentar as **taxas de juros do Brasil**.
- Listar as **cotações das moedas**.  
  - Se a variação da moeda for positiva, apresentar o valor na cor **verde**.
  - Se a variação da moeda for negativa, apresentar o valor na cor **vermelha**.
- Criar um botão "Atualizar" para buscar os valores mais recentes da API. 

---
#### **Bônus: Conversor de Moeda**
Para agregar mais valor ao desafio, inclua um conversor de moeda, onde o usuário poderá inserir um valor em uma moeda desejada e convertê-lo para outra.

**Requisitos do Conversor de Moeda:**

- Criar um campo onde o usuário insere um valor numérico.
- Adicionar um menu suspenso (dropdown) para escolher a moeda de origem.
- Adicionar um segundo menu suspenso para escolher a moeda de destino.
Ao clicar em um botão “Converter”, o sistema deve calcular e exibir o valor convertido usando a taxa de câmbio mais recente.
- Apresentar o resultado na interface de forma clara.

**Exemplo de fluxo esperado:**

- Usuário insere 100 e escolhe BRL como moeda de origem.
- Usuário seleciona USD (dólar) como moeda de destino e clica em converter.
- O sistema exibe o valor convertido, por exemplo:
  100 BRL → 17.12 USD (baseado na cotação fictícia de 5.84)