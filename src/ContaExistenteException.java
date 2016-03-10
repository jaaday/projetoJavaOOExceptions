public class ContaExistenteException extends BancoException {

	public ContaExistenteException() {
		super("Conta já existe!!!");
	}
}
