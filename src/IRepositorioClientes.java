public interface IRepositorioClientes {

	public abstract void atualizar(Cliente c);

	public abstract boolean existe(String cpf);

	public abstract void inserir(Cliente c);

	public abstract Cliente procurar(String cpf);

	public abstract void remover(String cpf);

}