public class Fachada {

	private static Fachada instancia;

	private CadastroContas contas;

	private CadastroClientes clientes;

	private Fachada() {
		initCadastros();
	}

	private void initCadastros() {
		IRepositorioContas rep = new RepositorioContasArray();
		contas = new CadastroContas(rep);
		IRepositorioClientes repClientes = new RepositorioClientesArray();
		clientes = new CadastroClientes(repClientes);
	}

	public static Fachada obterInstancia() {
		if (instancia == null) {
			instancia = new Fachada();
		}
		return instancia;
	}

	public void atualizar(Cliente c) {
		clientes.atualizar(c);
	}

	public Cliente procurarCliente(String cpf) {
		return clientes.procurar(cpf);
	}

	public void cadastrar(Cliente c) {
		clientes.cadastrar(c);
	}

	public void descadastrarCliente(String cpf) {
		clientes.descadastrar(cpf);
	}

	public void atualizar(ContaAbstrata c) {
		contas.atualizar(c);
	}

	public ContaAbstrata procurarConta(String n) {
		return contas.procurar(n);
	}

	public void cadastrar(ContaAbstrata c) {

		Cliente cli = c.getCliente();
		if (cli != null) {
			clientes.procurar(cli.getCpf());
			contas.cadastrar(c);
		} else {
			System.out.println("Cliente Nulo");
		}
	}

	public void descadastrarConta(String n) {
		contas.remover(n);
	}

	public void creditar(String n, double v) {
		contas.creditar(n, v);
	}

	public void debitar(String n, double v) {

		contas.debitar(n, v);
	}

	public void transferir(String origem, String destino, double val) {
		contas.transferir(origem, destino, val);
	}
}