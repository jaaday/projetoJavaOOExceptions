public class ContaImposto extends ContaAbstrata {
	private final double TAXA = 0.001;

	public ContaImposto(String num, Cliente c) {
		super(num, c);
		// TODO Auto-generated constructor stub
	}

	public ContaImposto(String num, double s, Cliente c) {
		super(num, s, c);
	}

	@Override
	public void debitar(double valor) throws SaldoInsuficienteException {
		double imposto = valor * TAXA;
		double valorDebitado = valor + imposto;
		if (valorDebitado <= getSaldo()) {
			// saldo = saldo - valor;
			setSaldo(getSaldo() - valorDebitado);
			// diminuiSaldo(valor);
			System.out.println("Debito realizado");
		} else {
			throw new SaldoInsuficienteException();
		}
	}

}