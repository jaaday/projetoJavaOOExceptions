public class RepositorioClientesArray implements IRepositorioClientes {
	private Cliente[] clientes;
	private int indice;

	private final static int tamCache = 100;

	public RepositorioClientesArray() {
		indice = 0;
		clientes = new Cliente[tamCache];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see RepositorioClientes#atualizar(Cliente)
	 */
	@Override
	public void atualizar(Cliente c) throws ClienteInexistenteException {
		int i = procurarIndice(c.getCpf());
		if (i != -1) {
			clientes[i] = c;
		} else {
			throw new ClienteInexistenteException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see RepositorioClientes#existe(java.lang.String)
	 */
	@Override
	public boolean existe(String cpf) throws ClienteExistenteException {
		boolean resp = false;

		int i = this.procurarIndice(cpf);
		if (i != -1) {
			throw new ClienteExistenteException();
		}

		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see RepositorioClientes#inserir(Cliente)
	 */
	@Override
	public void inserir(Cliente c) throws ClienteInvalidoException {
		if (c == null) {
			throw new ClienteInvalidoException();
		} else {
			clientes[indice] = c;
			indice = indice + 1;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see RepositorioClientes#procurar(java.lang.String)
	 */
	@Override
	public Cliente procurar(String cpf) throws ClienteInexistenteException {
		Cliente c = null;
		int i = this.procurarIndice(cpf);
		if (i != -1) {
			c = clientes[i];
		} else {
			throw new ClienteInexistenteException();
		}

		return c;
	}

	private int procurarIndice(String cpf) {
		int i = 0;
		int ind = -1;
		boolean achou = false;

		while ((i < indice) && !achou) {
			if ((clientes[i].getCpf()).equals(cpf)) {
				ind = i;
				achou = true;
			}
			i = i + 1;
		}
		return ind;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see RepositorioClientes#remover(java.lang.String)
	 */
	@Override
	public void remover(String cpf) throws ClienteInexistenteException {
		int i = this.procurarIndice(cpf);
		if (i != -1) {
			clientes[i] = clientes[indice - 1];
			clientes[indice - 1] = null;
			indice = indice - 1;
		} else {
			throw new ClienteInexistenteException();
		}
	}
}