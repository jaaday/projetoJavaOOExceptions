public class ClienteInexistenteException extends BancoException {

	public ClienteInexistenteException() {
		super("Cliente não existe!!!");
	}
}
