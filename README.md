# Campo-Minado
Projeto de um jogo de Campo Minado feito como projeto para a matéria de Programação Orientada a Objetos do curso de Ciência da Computação do IBILCE - Unesp.

## Funcionalidades principais
Interface gráfica com menu inicial, jogo e placar de pontuação. Implementação do jogo de campo minado. Armazenamento do placar em um arquivo.

## Conceitos implementados
Herança e interface: "quadrado com bomba", "quadrado com número" e "quadrado vazio" serão extensões de um "quadrado" abstrato que implementa uma interface "clicável"
Arquivos: o histórico de pontuação dos jogos será armazenado e lido com um arquivo
Generics: será usado na implementação do mapa de quadrados
Collections: será usado para armazenar as pontuações dos jogos
Polimorfismo/Sobrecarga: cada tipo de "quadrado" realizará uma coisa diferente com a mesma função "ser clicado"
