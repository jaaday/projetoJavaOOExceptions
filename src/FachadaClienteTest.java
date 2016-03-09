import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FachadaClienteTest {

	Cliente joao, maria;
	Fachada fachada;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setup() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));

		joao = new Cliente("111.111.111-11", "Joao", TipoCliente.ESPECIAL);
		maria = new Cliente("222.222.222-22", "Maria", TipoCliente.VIP);

		fachada = Fachada.obterInstancia();
	}

	@After
	public void tearDown() {
		fachada.descadastrarCliente(joao.getCpf());
		fachada.descadastrarCliente(maria.getCpf());
		outContent.reset();
		errContent.reset();
		System.setOut(null);
		System.setErr(null);
	}

	@Test
	public void testaCadastroClienteNova() {
		Assert.assertEquals(null, fachada.procurarCliente(joao.getCpf()));

		fachada.cadastrar(joao);
		Assert.assertEquals(joao, fachada.procurarCliente(joao.getCpf()));
		Assert.assertNotEquals(null, fachada.procurarCliente(joao.getCpf()));
	}

	@Test
	public void testacadastrarExistente() {
		fachada.cadastrar(joao);
		outContent.reset();
		fachada.cadastrar(joao);
		Assert.assertEquals("Cliente Existente\n", outContent.toString());

	}

	@Test
	public void testaatualizar() {
		fachada.cadastrar(joao);

		Assert.assertEquals(joao.getNome(),
				fachada.procurarCliente(joao.getCpf()).getNome());

		joao.setNome("Marco");
		Assert.assertEquals(joao.getNome(),
				fachada.procurarCliente(joao.getCpf()).getNome());

		fachada.atualizar(joao);
		Assert.assertEquals(joao.getNome(),
				fachada.procurarCliente(joao.getCpf()).getNome());
	}

	@Test
	public void testaDescartarCliente() {
		fachada.cadastrar(joao);
		Assert.assertNotEquals(null, fachada.procurarCliente(joao.getCpf()));

		fachada.descadastrarCliente(joao.getCpf());
		Assert.assertEquals(null, fachada.procurarCliente(joao.getCpf()));
	}
}
