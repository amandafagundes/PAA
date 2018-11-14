# PAA    
Trabalhos realizados na disciplina de Projeto e Análise de Algoritmos na UFMG :)  

**Grafos**  
  
Algoritmo para resolução do jogo *8-Puzzle*, no qual a partir de uma determinada instância (matriz de inteiros 3x3 na qual o espaço vazio é representado por 0) é informado quantos movimentos teriam que ser realizados para se alcançar o  estado final. Foi implementado o algoritmo BFS, no qual, a partir de um estado inicial é realizada a etapa de expansão, que consiste na geração de todos os estados alcancáveis a partir de um dado estado. Em seguida, a etapa de expansão é realizada para todos os estados gerados, retirando-os de uma fila, na ordem em que foram criados até que o estado final seja alcançado.  

A fim de se evitar que um estado seja gerado mais de uma vez, foi criado o `visited_states`, um objeto do tipo `LinkedHashSet`, que é responsável por armazenar todos os estados na ordem em que são expandidos e, por se tratar de uma estrutura utilizada para representar conjuntos, não é permitida a inserção de estados repetidos. O custo das operações básicas nesse tipo de estrutura é O(1).  

**Paradigmas**  

Foram solucionados quatro problemas disponibilizados na plataforma URI Online Judge e, cada uma das soluções foi criada baseando-se em algum dos paradigmas de programação:

1. **1088:** Divisão e Conquista 2. **1642:** Indução 3. **1966:** Algoritmo guloso 4. **2446:** Programação dinâmica

