package src.Main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import src.database.Database.Database;
import src.objects.Book.Book;
import src.objects.Loan.Loan;
import src.objects.User.User;

public class Main {

	static ArrayList<User> users = new ArrayList<User>();
	static ArrayList<Loan> loans = new ArrayList<Loan>();

	private static void print(Object o) {
		System.out.print(o.toString() + "\n");
	}

	private static void printBook(Book b) {
		print("========================================");
		print("Nome: " + b.getName());
		print("Autores: " + b.getAuthors());
		print("Ano: " + b.getYear());
		print("Edição: " + b.getEdition());
		print("Editora: " + b.getEditor());
		print("ISBN: " + b.getIsbn());
		print("========================================");
	}

	public static void bookMenu(Database db, Scanner scan) {
		boolean running = true;
		while (running) {
			print("Digite a opção desejada: ");
			print("1. Adicionar livro");
			print("2. Remover livro");
			print("3. Listar livros");
			print("4: Buscar livro por ISBN");
			print("5. Buscar livro por nome");
			print("6. Buscar livro por editora");
			print("7. Sair");
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
					print("Digite o ISBN do livro: ");
					scan.nextLine();
					String isbn = scan.nextLine();
					db.removeBook(isbn);
					break;
				case 3:
					try {
						db.listBooks();
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 4:
					print("Digite o ISBN do livro: ");
					scan.nextLine();
					String isbnSearch = scan.nextLine();
					try {
						book = db.getBookByISBN(isbnSearch);
						printBook(book);
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 7:
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
			print("4. Voltar");
			int option = scan.nextInt();
			switch (option) {
				case 1:
					scan.nextLine();
					print("Digite o nome do usuário: ");
					String name = scan.nextLine();
					print("Digite o tipo do usuário (aluno/professor): ");
					String userType = scan.nextLine();
					if (
						!userType.toLowerCase().equals("aluno") &&
						!userType.toLowerCase().equals("professor")
					) {
						print("Tipo de usuário inválido");
						break;
					}
					User newUser = new User(name, userType);
					users.add(newUser);
					print("Usuário adicionado com sucesso!\n");
					break;
				case 2:
					print("Digite o id do usuário: ");
					scan.nextLine();
					String userId = scan.nextLine();
					try {
						boolean userDeleted = false;
						for (int i = 0; i < users.size(); i++) {
							if (users.get(i).getId().equals(userId)) {
								users.remove(i);
								userDeleted = true;
								break;
							}
						}
						if (!userDeleted) throw new Exception("Usuário não encontrado!\n");

						print("Usuário removido com sucesso!\n");
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 3:
					try {
						users.forEach(user -> {
							print("=========================================");
							print("Id: " + user.getId());
							print("Nome: " + user.getName());
							print("Tipo: " + user.getUserType());
							print("Livros emprestados: " + user.getLoans().size());
							print("=========================================");
						});
						if (users.size() == 0) throw new Exception(
							"Nenhum usuário cadastrado!\n"
						);
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

	private static void loanMenu(Database db, Scanner scan) {
		boolean running = true;
		while (running) {
			print("Digite a opção desejada: ");
			print("1. Adicionar empréstimo");
			print("2. Remover empréstimo");
			print("3. Listar empréstimos");
			print("4. Voltar");
			int option = scan.nextInt();
			switch (option) {
				case 1:
					scan.nextLine();
					print("Digite o nome do usuário: ");
					String userName = scan.nextLine();
					print("Digite o isbn do livro: ");
					String isbn = scan.nextLine();
					User user = null;
					Book book = null;
					try {
						Optional<User> u = users
							.stream()
							.filter(x -> x.getName().equals(userName))
							.findFirst();
						user = u.get();

						book = db.getBookByISBN(isbn);
					} catch (Exception e) {
						print(e.getMessage());
						break;
					}
					LocalDate.now();
					Loan loan = new Loan(
						book,
						user,
						LocalDate.now().toString(),
						LocalDate.now().plusDays(7).toString()
					);
					loans.add(loan);
					print("Empréstimo adicionado com sucesso!\n");
					break;
				case 2:
					print("Digite o id do empréstimo: ");
					scan.nextLine();
					String loanId = scan.nextLine();
					try {
						if (
							loans.stream().filter(x -> x.getId().equals(loanId)).count() == 0
						) throw new Exception("Empréstimo não encontrado!\n");
						loans.remove(
							loans
								.stream()
								.filter(x -> x.getId().equals(loanId))
								.findFirst()
								.get()
						);

						print("Empréstimo removido com sucesso!\n");
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 3:
					try {
						loans.forEach(l -> {
							print("=========================================");
							print("Id: " + l.getId());
							print("Usuário: " + l.getUser().getName());
							print("Livro: " + l.getBook().getName());
							print("Data de empréstimo: " + l.getDate());
							print("Data de retorno: " + l.getReturnDate());
							print("=========================================");
						});
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

	// Função auxiliar para adicionar usuários para testes
	private static void populateUsers() {
		users.add(new User("João", "Aluno"));
		users.add(new User("Maria", "Aluno"));
		users.add(new User("José", "Professor"));
		users.add(new User("Pedro", "Professor"));
		users.add(new User("Lucas", "Professor"));
	}

	public static void main(String[] args) {
		Database db = new Database();
		db.createNewDatabase();
		populateUsers();
		Scanner scan = new Scanner(System.in);

		boolean running = true;
		print("========= Bem vindo a biblioteca =========");
		while (running) {
			print("Qual entidade deseja acessar?: ");
			print("1. Usuários");
			print("2. Livros");
			print("3. Empréstimos");
			print("4. Sair");
			int option = scan.nextInt();
			switch (option) {
				case 1:
					userMenu(db, scan);
					break;
				case 2:
					bookMenu(db, scan);
					break;
				case 3:
					loanMenu(db, scan);
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
