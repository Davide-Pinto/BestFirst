import java.util.ArrayList;
import java.util.List;


/**
 * A classe Operation implementa a interface Ilayout e define as operações para um layout específico. 
 * Ela mantém informações sobre um número e um custo associado.
 * 
 * @author Robim Rodrigues, Nuno Carvalho, Davide Pinto
 *
 */
public class Operation implements Ilayout, Cloneable {

	private int number;
	private int cost;

	/**
	 * Construtor para a classe Operation.
	 *
	 * @param num O número associado à operação
	 * @param x O custo associado à operação
	 */
	public Operation(int num, int x) {
		number = num;
		cost = x;
	}
	
	/**
	 * Construtor para a classe Operation.
	 *
	 * @param num O número associado à operação
	 */
	public Operation(int num) {
		number = num;
	}

	
	/**
	 * Verifica se o objeto passado é igual a esta instância da Operation.
	 *
	 * @param obj O objeto a ser comparado com a instância atual
	 * @return true se os objetos forem iguais, false caso contrário
	 */
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null) {
	        return false;
	    }
	    if (getClass() != obj.getClass()) {
	        return false;
	    }
	    Operation other = (Operation) obj;
	    return this.number == other.number; 
	}
	
	
	/**
	 * Retorna um código hash para a instância atual da Operation.
	 *
	 * @return O código hash para a instância atual
	 */
	@Override
	public int hashCode() {
	    return Integer.hashCode(number);
	}
	
	
	
	/**
	 * Retorna uma representação em string da instância atual da Operation.
	 *
	 * @return A representação em string da instância atual
	 */
	@Override
	public String toString() {
	    return String.valueOf(number);
	}
	
	
	
	/**
	 * Retorna os filhos do layout atual, criando novas operações com base nas operações de adição, subtração e multiplicação.
	 *
	 * @return A lista de filhos do layout atual
	 */
	@Override
	public List<Ilayout> children() {
		List<Ilayout> child = new ArrayList<Ilayout>();
		Operation O1 = new Operation(number + 1, 1); // Cria uma nova operação com o número incrementado em 1 e custo 1
		Operation O2 = new Operation(number - 1, 2); // Cria uma nova operação com o número decrementado em 1 e custo 2
		Operation O3 = new Operation(number * 2, 3); // Cria uma nova operação com o número multiplicado por 2 e custo 3

		child.add(O1); // Adiciona a operação O1 à lista de filhos
		child.add(O2); // Adiciona a operação O2 à lista de filhos
		child.add(O3); // Adiciona a operação O3 à lista de filhos
		return child; // Retorna a lista de filhos do layout atual
	}

	
	/**
	 * Verifica se o layout passado como argumento é o objetivo.
	 *
	 * @param l O layout a ser comparado com o layout atual
	 * @return true se os layouts forem iguais, false caso contrário
	 */
	@Override
	public boolean isGoal(Ilayout l) {
		return this.equals(l);
	}

	
	/**
	 * Obtém o valor numerico do nó.
	 *
	 * @return O valor numerico do nó
	 */
	public int getValue() {
		return number;
	}

	/**
	 * Retorna o valor de g para o layout.
	 *
	 * @return O valor de g para o layout
	 */
	@Override
	public double getG() {
		return cost;
	}

	
	/**
	 * Calcula e retorna o valor heurístico (h) para o layout com base no layout atual e no layout objetivo. 
	 * Este método implementa uma heurística específica para calcular o valor de h, considerando diferentes condições para o valor alvo.
	 *
	 * @param actual o layout atual
	 * @param goal o layout objetivo
	 * @return o valor heurístico (h) para o layout
	 */
	@Override
	public double getH(Ilayout actual, Ilayout goal){
        int p;
        int p2;
        int p3;
        int current = actual.getValue();
        int target = goal.getValue();
        double half = target/2.0;
        double dupleHalf = half/2.0;

        // Para garantir que dupleHalf seja um número inteiro
        if(dupleHalf%2.0 != 0){
            dupleHalf -= 1;
        }

        if(target > 0) {
        	// Calculos para a situação em que o alvo é positivo
            p = Math.abs(target - current);
            p2 = (int) Math.abs(Math.floor(half) - current) + 3;
            p3 = (int) Math.abs(((current - Math.floor(dupleHalf)) * 2) + 6);
        }
        else{
        	// Calculos para a situação em que o alvo é negativo
            current = Math.abs(current);
            target = Math.abs(target);
            p = Math.abs(target - current)*2;
            p2 = (int) Math.abs(Math.abs(Math.floor(half)) - current)*2 + 3;
            p3 = (int) Math.abs((current - Math.abs(Math.ceil(dupleHalf)))) + 6;   
        }  

        // Retorna do valor mínimo entre p, p2 e p3 como custo estimado do estado atual ate ao estado objetivo
        return Math.min(p,Math.min(p2,p3));
    }

}
