package src;

import java.util.Scanner;
import src.database.Database;
import src.objects.Book;

public class Main {

	private static void print(Object o) {
		System.out.print(o.toString() + "\n");
	}

	public static void main(String[] args) {
		Database db = new Database();
		db.createNewDatabase();
		Scanner scan = new Scanner(System.in);

		boolean running = true;
		print("========= Bem vindo a biblioteca =========");
		while (running) {
			print("Digite a opção desejada: ");
			print("1. Adicionar livro");
			print("2. Remover livro");
			print("3. Listar livros");
			print("4. Sair");
			int option = scan.nextInt();
			switch (option) {
				case 1:
					scan.nextLine();
					print("Digite o nome do livro: ");
					String name = scan.nextLine();
					print("Digite o nome do autor: ");
					String authors = scan.nextLine();
					print("Digite o ano de publicação: ");
					int year = scan.nextInt();
					print("Digite a edição: ");
					int edition = scan.nextInt();
					print("Digite o nome da editora: ");
					scan.nextLine();
					String editor = scan.nextLine();
					Book book = new Book(name, authors, year, edition, editor);
					db.addBook(book);
					break;
				case 2:
					print("Digite o nome do livro: ");
					scan.nextLine();
					name = scan.nextLine();
					db.removeBook(name);
					break;
				case 3:
					try {
						db.listBooks();
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 4:
					running = false;
					break;
				default:
					print("Opção inválida");
					break;
			}
		}
		scan.close();
		db.closeConnection();
	}
}
