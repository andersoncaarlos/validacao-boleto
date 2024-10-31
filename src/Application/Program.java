package Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Entities.Banco;
import Entities.Boleto;


public class Program {

	public static void main(String[] args) {
		
		List<Banco> listaBancos = new ArrayList<>();
		
		Banco b1 = new Banco("Banco do Brasil S.A", "001");	
		Banco b2 = new Banco("Banco Santander S.A", "033");
		Banco b3 = new Banco("Caixa Econômica Federal", "104");
		Banco b4 = new Banco("Banco Itaú S.A", "341");
		Banco b5 = new Banco("Banco Bradesco S.A", "237");
		
		listaBancos.add(b1);
		listaBancos.add(b2);
		listaBancos.add(b3);
		listaBancos.add(b4);
		listaBancos.add(b5);					
		
		Scanner sc = new Scanner(System.in);		
		String codigoBarras;
		Boleto boleto = new Boleto();
		
		System.out.println("Bem vindo!");
		System.out.println("Validação de Boletos");
		System.out.println("Verificação da linha digitavel ou código de barras? ( 1 - Linha Digitável | 2 - Código de Barras)");
		int opcao = sc.nextInt();
		sc.nextLine();
		
		switch(opcao) {
		case 1:
			System.out.print("Digite o linha digitável do boleto: ");	
			codigoBarras = sc.nextLine();
			boleto = new Boleto(codigoBarras);	
			
			System.out.println("\n--- Validação de Digito Verificador(DV) dos Campos 1, 2 e 3 ---");
			String campo1 = codigoBarras.substring(0, 10);
			System.out.println("DV Campo 1: " + boleto.calModulo10(campo1));
			String campo2 = codigoBarras.substring(10, 21);
			System.out.println("DV Campo 2: " + boleto.calModulo10(campo2));
			String campo3 = codigoBarras.substring(21, 32);
			System.out.println("DV Campo 3: " + boleto.calModulo10(campo3));
			
			// Nome do Banco
			String verificadorBanco = codigoBarras.substring(0, 3);
			System.out.println();
			for (Banco b : listaBancos) {
				if (verificadorBanco.equals(b.getCodigo())) {
					System.out.println(b.toString());
				}
			}
			
			break;
			
		case 2:
			System.out.println("Digite a composição do código de barras do boleto:");
			codigoBarras = sc.nextLine();
			boleto = new Boleto(codigoBarras);
			System.out.println(boleto.calModulo11(codigoBarras));
			
			break;
			
		default: System.out.println("Opção Inválida");
		}	
		
		
		sc.close();
	}	
}


