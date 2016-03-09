public class CadastroContas {

	private IRepositorioContas contas;
	public CadastroContas(IRepositorioContas r) {
		this.contas = r;
	}

	public void atualizar(ContaAbstrata c) {
		contas.atualizar(c);
	}

	public void cadastrar(ContaAbstrata c) {
		if (!contas.existe(c.getNumero())) {
			contas.inserir(c);
		} else {
			System.out.println("Conta ja cadastrada");
		}
	}

	public void creditar(String n, double v) {
		ContaAbstrata c = contas.procurar(n);
		c.creditar(v);
	}

	public void debitar(String n, double v) {
		ContaAbstrata c = contas.procurar(n);
		c.debitar(v);
	}

	public void remover(String n) {
		contas.remover(n);
	}

	public ContaAbstrata procurar(String n) {
		return contas.procurar(n);
	}

	public void transferir(String origem, String destino, double val) {
		ContaAbstrata o = contas.procurar(origem);
		ContaAbstrata d = contas.procurar(destino);
		o.transferir(d, val);
	}
}