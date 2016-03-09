import org.junit.Assert;
import org.junit.Test;

public class PoupancaTest {
	@Test
	public void debitarComImposto() {
		Poupanca conta = new Poupanca("12345", 1000.0, new Cliente(
				"123.456.789-10", "Joao", TipoCliente.ESPECIAL));

		Assert.assertEquals(1000.00, conta.getSaldo(), 0.1);
		conta.renderJuros(0.25);
		Assert.assertEquals(1250.00, conta.getSaldo(), 0.1);
	}
}
