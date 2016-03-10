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
		try {
			fachada.descadastrarCliente(joao.getCpf());
			fachada.descadastrarCliente(maria.getCpf());
		} catch (ClienteInexistenteException e) {
			Assert.assertEquals("Cliente não existe!!!", e.getMessage());
		}

		outContent.reset();
		errContent.reset();
		System.setOut(null);
		System.setErr(null);
	}

	@Test
	public void testaCadastroClienteNova() {
		try {
			Assert.assertEquals(null, fachada.procurarCliente(joao.getCpf()));
			fachada.cadastrar(joao);
			Assert.assertEquals(joao, fachada.procurarCliente(joao.getCpf()));
			Assert.assertNotEquals(null, fachada.procurarCliente(joao.getCpf()));
		} catch (ClienteInexistenteException e) {
			Assert.assertEquals("Cliente não existe!!!", e.getMessage());
		} catch (ClienteExistenteException e) {
			Assert.assertEquals("Cliente já existe!!!", e.getMessage());
		} catch (ClienteInvalidoException e) {
			Assert.assertEquals("Cliente Inválido!!!", e.getMessage());
		}

	}

	@Test
	public void testacadastrarExistente() {
		try {
			fachada.cadastrar(joao);
			outContent.reset();
			fachada.cadastrar(joao);
		} catch (ClienteExistenteException e) {
			Assert.assertEquals("Cliente já existe!!!", e.getMessage());
		} catch (ClienteInvalidoException e) {
			Assert.assertEquals("Cliente Inválido!!!", e.getMessage());
		}

	}

	@Test
	public void testaatualizar() {
		try {
			fachada.cadastrar(joao);
			Assert.assertEquals(joao.getNome(), fachada.procurarCliente(joao.getCpf()).getNome());

			joao.setNome("Marco");
			Assert.assertEquals(joao.getNome(), fachada.procurarCliente(joao.getCpf()).getNome());

			fachada.atualizar(joao);
			Assert.assertEquals(joao.getNome(), fachada.procurarCliente(joao.getCpf()).getNome());

		} catch (ClienteExistenteException e) {
			Assert.assertEquals("Cliente já existe!!!", e.getMessage());
		} catch (ClienteInexistenteException e) {
			Assert.assertEquals("Cliente não existe!!!", e.getMessage());
		} catch (ClienteInvalidoException e) {
			Assert.assertEquals("Cliente Inválido!!!", e.getMessage());
		}

	}

	@Test
	public void testaDescartarCliente() {
		try {
			fachada.cadastrar(joao);
			Assert.assertNotEquals(null, fachada.procurarCliente(joao.getCpf()));

			fachada.descadastrarCliente(joao.getCpf());
			Assert.assertEquals(null, fachada.procurarCliente(joao.getCpf()));
		} catch (ClienteExistenteException e) {
			Assert.assertEquals("Cliente já existe!!!", e.getMessage());
		} catch (ClienteInexistenteException e) {
			Assert.assertEquals("Cliente não existe!!!", e.getMessage());
		} catch (ClienteInvalidoException e) {
			Assert.assertEquals("Cliente Inválido!!!", e.getMessage());
		}
		
	}
}
