import org.junit.Assert;
import org.junit.Test;

public class ContaTest {
	@Test
	public void creditar() {
		Conta conta = new Conta("12345", 100.0, new Cliente("123.456.789-10",
				"Joao", TipoCliente.ESPECIAL));

		Assert.assertEquals(100.00, conta.getSaldo(), 0.1);

		conta.creditar(250.00);
		Assert.assertEquals(350.00, conta.getSaldo(), 0.1);

	}
}
