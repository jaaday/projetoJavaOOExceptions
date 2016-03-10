public interface IRepositorioContas {

	void inserir(ContaAbstrata c);

	boolean existe(String num) throws ContaExistenteException;

	void atualizar(ContaAbstrata c) throws ContaInexistenteException;

	ContaAbstrata procurar(String num) throws ContaInexistenteException;

	void remover(String num) throws ContaInexistenteException;

}