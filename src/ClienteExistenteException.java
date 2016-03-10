public class ClienteExistenteException extends BancoException {

	public ClienteExistenteException() {
		super("Cliente já existe!!!");
	}
}
