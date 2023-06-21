package src.Main;

import java.util.Scanner;

import src.Util.U;
import src.database.Database.Database;

public class Main extends U {

	public static void main(String[] args) {
		Database.createNewDatabase();
		exibirMenu();
	}

	public static void exibirMenu() {
		Scanner scanner = new Scanner(System.in);
		int opcao = 0;

		do {
			print("\n===== MENU =====");
			print("1. Comprar ativo");
			print("2. Vender ativo");
			print("3. Carteira de Investimentos");
			print("4. Listar ativos");
			print("5. Depositar na conta");
			print("6. Sacar da conta");
			print("-1. Sair");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = scanner.nextInt();
				scanner.nextLine();
				processarOpcao(opcao, scanner);
			} catch (Exception e) {
				System.out.println("Opção inválida! Tente novamente.");
				scanner.nextLine();
			}
		} while (opcao != -1);
		scanner.close();
	}

	public static void processarOpcao(int opcao, Scanner scanner) {
		switch (opcao) {
			case 1:
				print("Digite o ticker do ativo a ser comprado: ");
				String ticker = scanner.nextLine();

				Database.addPortfolioAsset(ticker);
				break;
			case 2:
				print("Digite o ticker do ativo a ser vendido: ");
				String deleteTicker = scanner.nextLine();

				Database.removePortfolioAsset(deleteTicker);
				break;

			case 3:
				Database.displayPortfolio();
				break;

			case 4:
				Database.listAssets();
				break;
			case 5:
				print("Digite o valor a ser depositado: ");
				Float valor = scanner.nextFloat();
				Database.balanceDeposit(valor);
				break;
			case 6:
				print("Digite o valor a ser retirado: ");
				valor = scanner.nextFloat();
				Database.balanceWithdraw(valor);
				break;
			default:
				print("Opção inválida! Tente novamente.");
				break;
		}
	}
}
