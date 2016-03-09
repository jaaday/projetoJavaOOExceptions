
public class Main {

	public static void main(String[] args) {
		Fachada fachada = Fachada.obterInstancia();
		
		Cliente cli1 = new Cliente("123","Leopoldo",TipoCliente.CLASS);
		Cliente cli2 = new Cliente("456","Maria",TipoCliente.VIP);
		Cliente cli3 = new Cliente("789","Teste",TipoCliente.ESPECIAL);
		Cliente cli4 = new Cliente("012","I",TipoCliente.ESPECIAL);
		fachada.cadastrar(cli1);
		fachada.cadastrar(cli2);
		fachada.cadastrar(cli3);
				
		Conta c1 = new Conta("1",100,cli1);
		ContaBonificada cb1 = new ContaBonificada("2",100,cli2);
		Poupanca p1 = new Poupanca("3",100,cli3);
		fachada.cadastrar(c1);
		fachada.cadastrar(cb1);
		fachada.cadastrar(p1);
/*
		Conta c2 = new Conta("1",1000,cli2);
		ContaBonificada cb2 = new ContaBonificada("2",1000,cli3);
		Poupanca p2 = new Poupanca("3",1000,cli1);
		fachada.cadastrar(c2);
		fachada.cadastrar(cb2);
		fachada.cadastrar(p2);


		Conta c3= new Conta("4",1500,cli3);
		ContaBonificada cb3 = new ContaBonificada("5",1500,cli1);
		Poupanca p3 = new Poupanca("6",1500,cli2);
		
		fachada.cadastrar(c3);
		fachada.cadastrar(cb3);
		fachada.cadastrar(p3);
		
		fachada.creditar("4", 599);
/**/		
		ContaAbstrata c = fachada.procurarConta("598791872");
		System.out.println(c.getSaldo());

		

	}

}
