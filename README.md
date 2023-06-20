# Campo-Minado
Projeto de um jogo de Campo Minado feito como projeto para a matéria de Programação Orientada a Objetos do curso de Ciência da Computação do IBILCE - Unesp.
<br>Feita em JDK 20.0.1


## Funcionalidades principais
Interface gráfica em *Swing* com menu inicial, tabuleiro do jogo e placar de pontuação. Implementação do jogo de campo minado, com quadrados vazio, dicas, bombas e marcação por bandeiras.
<br>
Armazenamento e leitura do placar em arquivos no diretório <code>src/placar/files</code>. 


## Conceitos implementados
**Herança e interface:** "quadrado com bomba", "quadrado com número" e "quadrado vazio" são extensões de um "quadrado" abstrato que implementa uma interface "oculta".

**Arquivos:** o histórico de pontuação dos jogos é armazenado e lido com arquivos <code>.dat</code>, separados de acordo com a dificuldade do jogo. 

**Generics:** usado na implementação do mapa de quadrados que compõe o tabuleiro do jogo.

**Collections:** usado para armazenar as pontuações dos jogos em <code>Placar</code>, e para armazenar os quarados do tabuleiro.

**Polimorfismo/Sobrecarga:** cada descendente de "quadrado" retorna um valor diferente com a mesma função "revelar", de acordo com seu tipo.

**Exceptions:** além do tratamento de axceptions na leitura de arquivos, a <code>InvalidNameException</code> é usada para tratar a entrada de nomes no placar.
