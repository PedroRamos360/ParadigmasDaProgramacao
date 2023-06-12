import java.util.Scanner;

import src.database.Database.Database;

public class Main {

	public static void main(String[] args) {

		exibirMenu();
	}

	public static void exibirMenu() {
		Scanner scanner = new Scanner(System.in);
		int opcao = 0;

		do {
			System.out.println("\n===== MENU =====");
			System.out.println("1. Incluir ativo");
			System.out.println("2. Excluir ativo");
			System.out.println("3. Sair");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = scanner.nextInt();
				scanner.nextLine(); // Limpa o buffer do scanner
				processarOpcao(opcao);
			} catch (Exception e) {
				System.out.println("Opção inválida! Tente novamente.");
				scanner.nextLine(); // Limpa o buffer do scanner
			}
		} while (opcao != 3);

		scanner.close();
	}

	public static void processarOpcao(int opcao) {
		Scanner scanner = new Scanner(System.in);

		switch (opcao) {
			case 1:
				System.out.print("Digite o ticker do ativo: ");
				String ticker = scanner.nextLine();
				System.out.print("Digite o nome da empresa: ");
				String nome = scanner.nextLine();
				System.out.print("Digite o tipo do ativo (1 - Ações, 2 - BDRs, 3 - ETFs, 4 - Fundos Imobiliários): ");
				int tipoId = scanner.nextInt();
				scanner.nextLine(); // Limpa o buffer do scanner

				// Database.incluirAtivo(ticker, nome, tipoId);
				System.out.println("Ativo incluído com sucesso!");
				break;
			case 2:
				System.out.print("Digite o ticker do ativo a ser excluído: ");
				String tickerExclusao = scanner.nextLine();

				// Database.excluirAtivo(tickerExclusao);
				System.out.println("Ativo excluído com sucesso!");
				break;
			case 3:
				System.out.println("Encerrando o programa...");
				break;
			default:
				System.out.println("Opção inválida! Tente novamente.");
				break;
		}
	}
}
