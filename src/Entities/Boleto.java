package Entities;

public class Boleto {

	private String codigoBarras;

	// Construtores
	public Boleto() {
	}

	public Boleto(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	// Gets e Sets
	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	// Método personalizados

	// Modulo 10

	public String calModulo10(String campoComDV) {

		// Verificação de campos e retirando DV
		String campo;
		if (campoComDV.length() == 10) {
			campo = campoComDV.substring(0, 9);
		} else {
			campo = campoComDV.substring(0, 10);
		}

		int soma = 0;
		int resto = 0;
		int dv = 0;
		String[] numeros = new String[campo.length() + 1];
		int multiplicador = 2;
		String aux;
		String aux2;
		String aux3;

		for (int i = campo.length(); i > 0; i--) {
			// Multiplica da direita pra esquerda, alternando os algarismos 2 e 1
			if (multiplicador % 2 == 0) {
				// pega cada numero isoladamente
				numeros[i] = String.valueOf(Integer.valueOf(campo.substring(i - 1, i)) * 2);
				multiplicador = 1;
			} else {
				numeros[i] = String.valueOf(Integer.valueOf(campo.substring(i - 1, i)) * 1);
				multiplicador = 2;
			}
		}

		// Realiza a soma dos campos de acordo com a regra
		for (int i = (numeros.length - 1); i > 0; i--) {
			aux = String.valueOf(Integer.valueOf(numeros[i]));

			if (aux.length() > 1) {
				aux2 = aux.substring(0, aux.length() - 1);
				aux3 = aux.substring(aux.length() - 1, aux.length());
				numeros[i] = String.valueOf(Integer.valueOf(aux2) + Integer.valueOf(aux3));
			} else {
				numeros[i] = aux;
			}
		}

		// Realiza a soma dos elementos do array e calcula o digito verificador
		// Do modulo 10 de acordo com a regra.
		for (int i = numeros.length; i > 0; i--) {
			if (numeros[i - 1] != null) {
				soma += Integer.valueOf(numeros[i - 1]);
			}
		}
		// Calculo final para descobrir o DV e transforma-lo em String
		resto = soma % 10;
		dv = 10 - resto;
		String dvOk = String.valueOf(dv);

		if (dvOk.equals(campoComDV.substring(campoComDV.length() - 1))) {
			return dvOk + " - OK!";
		} else {
			return dvOk + " - Incorreto!";
		}
	}

	public String calModulo11(String codigo) {

		// Variáveis de instancia
		int soma = 0;
		int resto = 0;
		int dv = 0;
		int valor;
		String codigoSemDV = codigo.substring(0, 4) + codigo.substring(5);
		String[] numeros = new String[codigoSemDV.length()];
		int multiplicador = 2;

		// Multiplica da direita pra esquerda, incrementando o multiplicador de 2 a 9
		// Caso o multiplicador seja maior que 9 o mesmo recomeça em 2
		for (int i = codigoSemDV.length() - 1; i >= 0; i--) {
						
			// pega cada numero isoladamente
			valor = Character.getNumericValue(codigoSemDV.charAt(i));
			if (multiplicador > 9) {
				multiplicador = 2;
				numeros[i] = String.valueOf(Integer.valueOf(valor * multiplicador));
				multiplicador++;
			} else {
				numeros[i] = String.valueOf(Integer.valueOf(valor * multiplicador));
				multiplicador++;
			}
		}

		// Realiza a soma de todos os elementos do array e calcula o digito verificador
		// na base 11 de acordo com a regra.
		for (int i = 0; i < numeros.length; i++) {
			soma += Integer.valueOf(numeros[i]);
		}
		
		resto = soma % 11;
		
		
		dv = 11 - resto;
		if (dv == 0 || dv == 11 || dv == 10) {
			dv = 1;
		}
		return "Digito Verificador: " + dv;
	}
}
