import org.junit.Assert;
import org.junit.Test;

public class ContaBonificadaTest {
	@Test
	public void debitarComImposto(){
		ContaBonificada conta = new ContaBonificada("12345", 
				100.0, 
				new Cliente("123.456.789-10", 
						"Joao", 
						TipoCliente.ESPECIAL));

		Assert.assertEquals(100.00, conta.getSaldo(), 0.1);
		
		conta.creditar(250.00);
		Assert.assertEquals(350.00, conta.getSaldo(), 0.1);
		Assert.assertEquals(2.50, conta.getBonus(), 0.1);
		
		conta.renderBonus();
		Assert.assertEquals(352.50, conta.getSaldo(), 0.1);
		Assert.assertEquals(0.00, conta.getBonus(), 0.1);
	}
}
