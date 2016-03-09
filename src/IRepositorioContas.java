public interface IRepositorioContas {

	void inserir(ContaAbstrata c);

	boolean existe(String num);

	void atualizar(ContaAbstrata c);

	ContaAbstrata procurar(String num);

	void remover(String num);

}