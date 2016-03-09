import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FachadaContaTest {

	Conta conta123;
	Conta conta456;
	Conta conta789;
	Fachada fachada;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setup() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));

		Cliente cli1 = new Cliente("111.111.111-11", "Silva",
				TipoCliente.ESPECIAL);
		Cliente cli2 = new Cliente("222.222.222-22", "Maria", TipoCliente.VIP);
		Cliente cli3 = new Cliente("333.333.333-33", "Novo", TipoCliente.CLASS);

		conta123 = new Conta("123", cli1);
		conta123.setSaldo(50);
		conta456 = new Conta("456", cli2);
		conta456.setSaldo(90);
		conta789 = new Poupanca("789", 500, cli3);

		fachada = Fachada.obterInstancia();
		fachada.cadastrar(cli1);
		fachada.cadastrar(cli2);
		fachada.cadastrar(cli3);
	}

	@After
	public void tearDown() {
		fachada.descadastrarConta(conta123.getNumero());
		fachada.descadastrarConta(conta456.getNumero());
		fachada.descadastrarConta(conta789.getNumero());
		outContent.reset();
		errContent.reset();
		System.setOut(null);
		System.setErr(null);
	}

	@Test
	public void testaCadastroContaNova() {
		Assert.assertEquals(null, fachada.procurarConta(conta123.getNumero()));

		fachada.cadastrar(conta123);
		Assert.assertNotEquals(null,
				fachada.procurarConta(conta123.getNumero()));
	}

	@Test
	public void testaCadastrarContaExistente() {
		fachada.cadastrar(conta123);
		Assert.assertEquals(conta123,
				fachada.procurarConta(conta123.getNumero()));
		outContent.reset();
		fachada.cadastrar(conta123);
		Assert.assertEquals("Conta ja cadastrada\n", outContent.toString());
	}

	@Test
	public void testaAtualizarConta() {
		fachada.cadastrar(conta123);

		Assert.assertEquals(conta123.getCliente().getNome(), fachada
				.procurarConta(conta123.getNumero()).getCliente().getNome());

		conta123.getCliente().setNome("Marco");
		Assert.assertEquals(conta123.getCliente().getNome(), fachada
				.procurarConta(conta123.getNumero()).getCliente().getNome());

		fachada.atualizar(conta123);
		Assert.assertEquals(conta123.getCliente().getNome(), fachada
				.procurarConta(conta123.getNumero()).getCliente().getNome());
	}

	@Test
	public void testaDescartarConta() {
		fachada.cadastrar(conta123);
		Assert.assertNotEquals(null,
				fachada.procurarConta(conta123.getNumero()));

		fachada.descadastrarConta(conta123.getNumero());
		Assert.assertEquals(null, fachada.procurarConta(conta123.getNumero()));
	}

	@Test
	public void testaTransferenciaDeValores() {
		fachada.cadastrar(conta123);
		fachada.cadastrar(conta456);
		Assert.assertEquals(50.00, conta123.getSaldo(), 0.0);
		Assert.assertEquals(90.00, conta456.getSaldo(), 0.0);

		fachada.transferir(conta123.getNumero(), conta456.getNumero(), 15.00);

		Assert.assertEquals(35.00, conta123.getSaldo(), 0.0);
		Assert.assertEquals(105.00, conta456.getSaldo(), 0.0);
	}

	@Test
	public void testaCreditoEmConta() {
		fachada.cadastrar(conta123);
		Assert.assertEquals(50.00, conta123.getSaldo(), 0.0);

		fachada.creditar(conta123.getNumero(), 17.00);
		Assert.assertEquals(67.00, conta123.getSaldo(), 0.0);
	}

	@Test
	public void testaDebitoEmConta() {
		fachada.cadastrar(conta123);
		Assert.assertEquals(50.00, conta123.getSaldo(), 0.0);

		fachada.debitar(conta123.getNumero(), 24.00);
		Assert.assertEquals(26.00, conta123.getSaldo(), 0.0);
	}
}