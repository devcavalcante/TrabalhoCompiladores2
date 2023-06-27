# dl-short-4
Este projeto é uma versão resumida da linguagem DL.  
Esta versao faz as análises léxica, sintática e semântica e a geração de código intermediário.  
Ela está de acordo com a gramática abaixo.  

## Gramática ajustada
PROGRAM				::= programa ID BLOCK  
BLOCK				::= inicio STMTS fim  
STMTS				::= STMT; STMTS | ε  
STMT				::= BLOCK | DECL | ASSIGN | WRITE | IF  
DECL     			::= TYPE ID  
ASSIGN   			::= ID = EXPR  
WRITE				::= escreva(ID)  
IF					::= se (EXPR) STMT  
EXPR				::= EXPR "|" REL | REL  
REL					::= REL < ARITH | REL <= ARITH | REL > ARITH | ARITH  
ARITH  				::= ARITH + TERM | ARITH - TERM | TERM  
TERM				::= TERM * UNARY | UNARY
UNARY               ::= +UNARY | -UNARY | PREFIX | FACTOR
PREFIX              ::= ++ID | --ID
FACTOR				::= (EXPR) | LIT_INT | LIT_REAL | LIT_BOOL | IDPOSTFIX
POSTFIX             ::= ++ | -- | ε


## Definições Regulares
LETTER		::= a | b | ... | z | A | B | ... Z | _  
DIGIT		::= 0 | 1 | ... | 9  
ID			::= LETTER (LETTER | DIGIT)*  
LIT_INT		::= DIGIT+  
LIT_REAL	::= DIGIT+ . DIGIT+   
LIT_BOOL	::= verdadeiro | falso  
TYPE     	::= inteiro | real | booleano  

## Recursos adicionados
Equipe 4: Alexandre; Debora; Lucas; Selena.
a. Operadores unários de soma e subtração: adicione as operações
unárias de soma e subtração, gerando o código intermediário apropriado.
b. Inc/Dec pré-fixado: Adicione as operações de incremento (++) e
decremento (--) pré-fixados. Esses operadores só são aplicáveis a variáveis
numéricas. Consulte a documentação e a BNF do C/C++ e construa o
mesmo funcionamento e precedência na DL.
c. Inc/Dec pós-fixado: Adicione as operações de incremento (++) e
decremento (--) pós-fixados. Esses operadores só são aplicáveis a variáveis
numéricas. Consulte a documentação e a BNF do C/C++ e construa o
mesmo funcionamento e precedência na DL.