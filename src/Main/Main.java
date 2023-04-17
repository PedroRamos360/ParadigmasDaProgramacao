package src.Main;

import java.util.ArrayList;
import java.util.Scanner;
import src.database.Database.Database;
import src.objects.Book.Book;
import src.objects.Loan.Loan;
import src.objects.User.User;

public class Main {

	private static void print(Object o) {
		System.out.print(o.toString() + "\n");
	}

	public static void bookMenu(Database db, Scanner scan) {
		boolean running = true;
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
	}

	public static void userMenu(Database db, Scanner scan) {
		boolean running = true;
		while (running) {
			print("Digite a opção desejada: ");
			print("1. Adicionar usuário");
			print("2. Remover usuário");
			print("3. Listar usuários");
			print("4. Sair");
			int option = scan.nextInt();
			switch (option) {
				case 1:
					scan.nextLine();
					print("Digite o nome do usuário: ");
					String name = scan.nextLine();
					print("Digite o tipo do usuário: ");
					String userType = scan.nextLine();
					db.addUser(name, userType);
					break;
				case 2:
					print("Digite o nome do usuário: ");
					scan.nextLine();
					name = scan.nextLine();
					db.removeUser(name);
					break;
				case 3:
					try {
						db.listUsers();
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
	}
	public static void main(String[] args) {
		Database db = new Database();
		db.createNewDatabase();
		Scanner scan = new Scanner(System.in);
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<Loan> loans = new ArrayList<Loan>();

		boolean running = true;
		print("========= Bem vindo a biblioteca =========");
		while (running) {
			print("Qual entidade deseja acessar?: ");
			print("1. Usuários");
			print("2. Livros");
			print("3. Sair");
			int option = scan.nextInt();
			switch (option) {
				case 1:
					userMenu(db, scan);
					break;
				case 2:
					bookMenu(db, scan);
					break;
				case 3:
					running = false;
					break;
				default:
					print("Opção inválida");
					break;
			}
		scan.close();
		db.closeConnection();
	}
}
