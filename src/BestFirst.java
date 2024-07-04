import java.util.*;

/**
 * A classe BestFirst representa um algoritmo de busca Best-First. Este algoritmo é usado para encontrar o melhor caminho num espaço de estados. 
 * A BestFirst mantém uma fila de estados abertos e um mapa de estados fechados para rastrear
 * os estados visitados. A classe inclui métodos para encontrar o caminho até um estado objetivo
 * a partir de um estado inicial.
 *
 * @author Robim Rodrigues, Nuno Carvalho, Davide Pinto
 */

public class BestFirst {
    protected Queue<State> abertos;
    private Map<Ilayout, State> fechados;
    private State atual;
    private Ilayout objective;

    /**
     * A classe interna State representa um estado dentro do algoritmo Best-First. Ela
     * mantém informações sobre o layout do estado, o estado pai, e o valor de g,
     * que é a soma dos custos para alcançar esse estado a partir do estado inicial.
     */
    static class State {
        private Ilayout layout;
        private State father;
        private double g;
        
        /**
         * Construtor para a classe State.
         *
         * @param l     O layout do estado
         * @param n     O estado pai
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
     * Resolve o problema para encontrar o caminho entre o layout inicial e o layout objetivo, utilizando o algoritmo Best-First.
     * Este método usa uma fila de prioridade para gerir os estados abertos, selecionando o próximo estado com base no custo acumulado do estado.
     *
     * @param s o layout inicial
     * @param goal o layout objetivo
     * @return Um iterador que contém o caminho entre o layout inicial e o layout objetivo; retorna null se não houver solução
     */
    final public Iterator<State> solve(Ilayout s, Ilayout goal) {
        abertos = new PriorityQueue<>(10, (s1, s2) -> (int) Math.signum(s1.getG()-s2.getG()));
        objective = goal;
        fechados = new HashMap<>();
        abertos.add(new State(s, null));
        while (true) {
            if (abertos.isEmpty()) {
                return null;
            }
            atual = abertos.poll();
            if (atual.layout.isGoal(objective)) {
                return reconstruirCaminho(atual);
            }
            fechados.put(atual.layout, atual);
            List<State> sucs = sucessores(atual);
            for (State e : sucs) {
                if (!fechados.containsKey(e.layout)) {
                    abertos.add(e);
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
