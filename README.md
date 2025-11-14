# Caixa_Branca_UI-UX
Atividade da disciplina de UI/UX
# Notação de Gráfico de Fluxo

Nodos do Método verificarUsuario:
Nodo	Linha(s)	Descrição	                                                        Tipo
1	    22	      Início do método, inicializações (sql = "", conn = conectarDB())	Execução
2	    24-26	    Concatenação da query SQL (Vulnerável)	                          Execução
3	    27	      Início do bloco try	                                              Execução
4	    28	      Criação do Statement (st = conn.createStatement())	              Execução
5	    29	      Execução da query (rs = st.executeQuery(sql))	                    Execução
6	    30	      Condição de controle: if (rs.next())	                            Decisão
7	    31-32	    Corpo do if (result = true, nome = rs.getString(...))	            Execução
8	    34	      Início do bloco catch	                                            Execução
9	    34	      Final do bloco catch (vazio)	                                    Execução
10	  35	      Declaração de return result (fim do método)	                      Execução

INÍCIO: verificarUsuario --> 1;
1 --> 2;
2 --> 3;
3 --> 4;
4 --> 5;
5 -- Sucesso --> 6;
5 -- Exceção (Linha 27) --> 8;
6 -- Verdadeiro --> 7;
7 --> 10;
6 -- Falso --> 10;
8 --> 9;
9 --> 10;
10 --> FIM: return result;

Caminhos de Execução:
Caminho 1 (Sucesso no BD, Login/Senha Válidos): 1 → 2 → 3 → 4 → 5 → 6 (V) → 7 → 10
Caminho 2 (Sucesso no BD, Login/Senha Inválidos): 1 → 2 → 3 → 4 → 5 → 6 (F) → 10
Caminho 3 (Falha de Conexão/SQL, Exceção no try): 1 → 2 → 3 → 4 → 5 → 8 → 9 → 10


Complexidade Ciclomática 
A complexidade ciclomática é uma métrica que mede a quantidade de caminhos lineares independentes
no fluxo de controle de um programa. Ela é calculada com a fórmula:
M = E − N + 2P
onde:
• E é o número de arestas (conexões entre nós),
• N é o número de nós,
• P é o número de componentes conectados (normalmente 1 para um programa único).

E = 11 ; N = 10; P = 2

  M = E - N + 2P
  M = 11 - 10 + 2(2)
  M = 1 + 4
  M = 5

Resultado da Complexidade Ciclomática
A complexidade ciclomática M desse código é 5.

Caminhos Básicos
Caminhos em conectarDB()
Caminho	    Fluxo (do início ao fim da função)	            Resultado
Caminho 1	  Tenta conectar, sucesso (sem exceção).	        Retorna uma conexão válida.
Caminho 2	  Tenta conectar, falha (ocorre exceção no try).	Retorna null (ou a conexão inicializada).
Caminhos em verificarUsuario()
Caminho	    Fluxo (do início ao fim da função)	              Decisões	                Resultado
Caminho 3	  Executa try, sucesso na query (rs.next() é True).	Sem exceção, if True	    result = true, retorna True.
Caminho 4	  Executa try, falha na query (rs.next() é False).	Sem exceção, if False	    result permanece False, retorna False.
Caminho 5	  Executa try, falha de execução (ocorre exceção).	Com exceção	              result permanece False, retorna False.
  
