import java.util.*;

/**
 * A classe BestFirstIDA representa uma versão do algoritmo Best-First que utiliza o Iterative deepening A* (IDA*) para melhorar a eficiência.
 * A BestFirstIDA mantém uma fila de estados abertos, um mapa de estados fechados e uma lista de estados na fringe(os nós que são superiores ao limite atual), 
 * e atraves da fringe vai calcular o novo limite, processo que é repetido ate ser encontrado a solucao.
 * A classe inclui métodos para encontrar o caminho até um estado objetivo a partir de um estado inicial, levando em consideração uma função heurística adicional.
 *
 * @author Robim Rodrigues, Nuno Carvalho, Davide Pinto
 */
public class BestFirstIDA {
    protected Queue<State> abertos;
    private Map<Ilayout, State> fechados;
    private State atual;
    private Ilayout objective;

    private List<State> fringe = new ArrayList<>();

    /**
     * A classe State representa um estado dentro do algoritmo Best-FirstIDA. 
     * Ela mantém informações sobre o layout do estado, o estado pai, o valor de g (custo acumulado) e uma função heurística adicional, h.
     */
    static class State {
        private final Ilayout layout;
        private final State father;
        private final double g;
        
        /**
         * Construtor para a classe State.
         *
         * @param l O layout do estado
         * @param n O estado pai
         */
        public State(Ilayout l, State n) {
            layout = l;
            father = n;
            if (father!=null)
                g = father.g + l.getG();
            else g = 0.0;
        }

        /**
         * Converte o estado em uma representação de string.
         *
         * @return A representação em string do estado
         */
        public String toString() { return layout.toString(); }

        
        /**
         * Obtém o valor de g para o estado.
         *
         * @return O valor de g para o estado
         */
        public double getG() {return g;}


        /**
         * Gera o código hash para o estado.
         *
         * @return O código hash gerado para o estado
         */
        public int hashCode() {
            return toString().hashCode();
        }

        
        /**
         * Verifica se o objeto passado é igual a este estado.
         *
         * @param o O objeto a ser comparado com este estado
         * @return true se os objetos são iguais, false caso contrário
         */
        public boolean equals (Object o) {
            if (o==null) return false;
            if (this.getClass() != o.getClass()) return false;
            State n = (State) o;
            return this.layout.equals(n.layout);
        }

        
        /**
         * Obtém o valor da função heurística h para o estado, considerando o estado atual e o estado objetivo.
         *
         * @param layout O layout do estado
         * @param goal O estado objetivo
         * @return O valor da função heurística h para o estado
         */
        public double getH(Ilayout layout, Ilayout goal) {
            return layout.getH(layout,goal);
        }
    }

    
    /**
     * Gera uma lista de sucessores para o estado fornecido.
     *
     * @param n O estado para o qual os sucessores serão gerados
     * @return Uma lista de sucessores do estado fornecido
     */
    final private List<State> sucessores(State n) {
        List<State> sucs = new ArrayList<>();
        List<Ilayout> children = n.layout.children();
        for(Ilayout e: children) {
            if (n.father == null || !e.equals(n.father.layout)){
                State nn = new State(e, n);
                sucs.add(nn);
            }
        }
        return sucs;
    }


    /**
     * Resolve o problema, encontrando o caminho do estado inicial para o objetivo.
     *
     * @param s O estado inicial
     * @param goal O estado objetivo
     * @return Um iterador para o caminho do estado inicial para o estado objetivo, se encontrado. Caso contrário, retorna null.
     */
    final public Iterator<State> solve(Ilayout s, Ilayout goal) {
        double limit = s.getH(s,goal);
        abertos = new PriorityQueue<>(10, (s1, s2) -> (int) Math.signum((s1.getH(s1.layout,goal) + s1.getG()) - (s2.getH(s2.layout, goal) + s2.getG())));
        objective = goal;
        fechados = new HashMap<>();
        abertos.add(new State(s, null));
        while (true) {
            if (abertos.isEmpty()) {
                if(!fringe.isEmpty()){
                    fringe.sort((s1, s2) -> (int) Math.signum((s1.getH(s1.layout,goal) + s1.getG()) - (s2.getH(s2.layout, goal) + s2.getG())));
                    State first =  fringe.get(0);
                    limit = first.getG() + first.getH(first.layout, goal);
                    fringe.clear();
                    abertos.clear();
                    fechados.clear();
                    abertos.add(new State(s,null));

                }
                else{
                    return null;
                }
            }
            atual = abertos.poll();
            assert atual != null;
            if (atual.layout.isGoal(objective)) {
                return reconstruirCaminho(atual);
            }
            fechados.put(atual.layout, atual);
            List<State> sucs = sucessores(atual);
            for (State e : sucs) {
                double eCost = e.getG() + e.getH(e.layout,goal);
                if (!fechados.containsKey(e.layout) && eCost <= limit) {
                    abertos.add(e);
                }else{
                    fringe.add(e);
                }
            }
        }
    }


    /**
     * Reconstrói o caminho a partir do estado objetivo até o estado inicial.
     *
     * @param goalState O estado objetivo a partir do qual o caminho será reconstruído
     * @return Um iterador para o caminho reconstruído
     */
    private Iterator<State> reconstruirCaminho(State goalState) {
        List<State> result = new ArrayList<>();
        while (goalState != null) {
            result.add(0, goalState);
            goalState = goalState.father;
        }
        return result.iterator();
    }

}
