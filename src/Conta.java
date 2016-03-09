public class Conta extends ContaAbstrata {

	public Conta(String num, Cliente c) {
		super(num, c);
		// TODO Auto-generated constructor stub
	}
	
	public Conta(String num, double s, Cliente c) {
		super(num,s,c);
	}

	@Override
	public void debitar(double valor) {
			if (valor <= getSaldo()) {
				//saldo = saldo - valor;
				setSaldo(getSaldo() - valor);
				//diminuiSaldo(valor);
				System.out.println("Debito realizado");
			} else {
				System.out.println("Saldo insuficiente");
			}
	}

}