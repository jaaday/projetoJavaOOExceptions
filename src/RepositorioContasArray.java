public class RepositorioContasArray implements IRepositorioContas {
	private ContaAbstrata[] contas;
	private int indice;
	private final static int tamCache = 100;

	public RepositorioContasArray() {
		indice = 0;
		contas = new ContaAbstrata[tamCache];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see RepositorioContas#inserir(Conta)
	 */
	@Override
	public void inserir(ContaAbstrata c) {
		contas[indice] = c;
		indice = indice + 1;
	}

	private int procurarIndice(String num) {
		int i = 0;
		int ind = -1;
		boolean achou = false;

		while ((i < indice) && !achou) {
			if ((contas[i].getNumero()).equals(num)) {
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
	 * @see RepositorioContas#existe(java.lang.String)
	 */
	@Override
	public boolean existe(String num) throws ContaExistenteException {
		boolean resp = false;

		int i = this.procurarIndice(num);
		if (i != -1) {
			throw new ContaExistenteException();
		}

		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see RepositorioContas#atualizar(Conta)
	 */
	@Override
	public void atualizar(ContaAbstrata c) throws ContaInexistenteException {
		int i = procurarIndice(c.getNumero());
		if (i != -1) {
			contas[i] = c;
		} else {
			throw new ContaInexistenteException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see RepositorioContas#procurar(java.lang.String)
	 */
	@Override
	public ContaAbstrata procurar(String num) throws ContaInexistenteException {
		ContaAbstrata c = null;
		int i = this.procurarIndice(num);
		if (i != -1) {
			c = contas[i];
		} else {
			throw new ContaInexistenteException();
		}
		return c;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see RepositorioContas#remover(java.lang.String)
	 */
	@Override
	public void remover(String num) throws ContaInexistenteException {
		int i = this.procurarIndice(num);
		if (i != -1) {
			contas[i] = contas[indice - 1];
			contas[indice - 1] = null;
			indice = indice - 1;
		} else {
			throw new ContaInexistenteException();
		}
	}
}
