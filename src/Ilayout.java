import java.util.List;

/**
 * A interface Ilayout define a estrutura para o layout específico e suas operações.
 */
public interface Ilayout {
	
	/**
	 * Retorna os filhos do layout atual.
	 *
	 * @return A lista de filhos do layout
	 */
	List<Ilayout> children();
	
	
	/**
	 * Verifica se o layout atual é igual ao layout passado como argumento.
	 *
	 * @param l O layout a ser comparado com o layout atual
	 * @return true se os layouts são iguais, false caso contrário
	 */
	boolean isGoal(Ilayout l);


	/**
	 * Retorna o valor numerico do nó ao mover-se da configuração de entrada para o layout atual.
	 *
	 * @return Do valor numerico do nó
	 */
	int getValue();
	
	/**
	 * Retorna o valor de g(custo) para o layout.
	 *
	 * @return O valor de g(custo) para o layout
	 */
	double getG();

	
	/**
	 * Calcula e retorna o valor da heurística (h) para o layout com base no layout atual e no layout objetivo.
	 * 
	 * @param actual O layout atual
	 * @param goal   O layout objetivo
	 * @return O valor de h para o layout
	 */
	double getH(Ilayout actual, Ilayout goal);
}
