package src.Main;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;
import src.database.Database.Database;
import src.objects.Book.Book;
import src.objects.Loan.Loan;
import src.objects.Reservation.Reservation;
import src.objects.User.User;

public class Main {

	static ArrayList<User> users = new ArrayList<User>();
	static ArrayList<Loan> loans = new ArrayList<Loan>();
	static ArrayList<Reservation> reservations = new ArrayList<Reservation>();

	private static void print(Object o) {
		System.out.print(o.toString() + "\n");
	}

	public static void printBook(Book b) {
		print("========================================");
		print("Id: " + b.getId());
		print("Nome: " + b.getName());
		print("Autores: " + b.getAuthors());
		print("Ano: " + b.getYear());
		print("Edição: " + b.getEdition());
		print("Editora: " + b.getEditor());
		print("ISBN: " + b.getIsbn());
		print("========================================");
	}

	private static void printUser(User u) {
		print("=========================================");
		print("Id: " + u.getId());
		print("Nome: " + u.getName());
		print("Tipo: " + u.getUserType());
		print(
			"Valor em multas: " +
			NumberFormat
				.getCurrencyInstance(new Locale("pt", "BR"))
				.format(u.getPenalty())
		);
		print("=========================================");
	}

	private static void printLoan(Loan l) {
		print("=========================================");
		print("Id: " + l.getId());
		print("Id do Usuário: " + l.getUser().getId());
		print("ISBN do livro: " + l.getBook().getId());
		print("Usuário: " + l.getUser().getName());
		print("Livro: " + l.getBook().getName());
		print("Data de empréstimo: " + l.getDate());
		print("Data de retorno: " + l.getReturnDate());
		print("=========================================");
	}

	private static void printReservation(Reservation r) {
		print("=========================================");
		print("Id: " + r.getId());
		print("Id do Usuário: " + r.getUser().getId());
		print("Id do livro: " + r.getBook().getId());
		print("Usuário: " + r.getUser().getName());
		print("Livro: " + r.getBook().getName());
		print("=========================================");
	}

	private static int getNumberOfLoans(User u) {
		int count = 0;
		for (Loan l : loans) {
			if (l.getUser().getId().equals(u.getId())) {
				count++;
			}
		}
		return count;
	}

	public static void bookMenu(Database db, Scanner scan) {
		boolean running = true;
		while (running) {
			print("Digite a opção desejada: ");
			print("1. Adicionar livro");
			print("2. Remover livro");
			print("3. Editar livros");
			print("4. Listar livros");
			print("5. Buscar livro por ISBN");
			print("6. Buscar livro por nome");
			print("7. Buscar livro por editora");
			print("8. Voltar");
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
					print("Digite o ISBN do livro: ");
					scan.nextLine();
					String isbnEdit = scan.nextLine();
					try {
						book = db.getBookByISBN(isbnEdit);
						print("==== EDITAR LIVRO ====");
						printBook(book);
						print("Digite o novo nome do livro: ");
						String nameEdit = scan.nextLine();
						print("Digite o novo nome do autor: ");
						String authorsEdit = scan.nextLine();
						print("Digite o novo ano de publicação: ");
						int yearEdit = scan.nextInt();
						print("Digite a edição: ");
						int editionEdit = scan.nextInt();
						print("Digite o novo nome da editora: ");
						scan.nextLine();
						String editorEdit = scan.nextLine();
						book.setName(nameEdit);
						book.setAuthors(authorsEdit);
						book.setYear(yearEdit);
						book.setEdition(editionEdit);
						book.setEditor(editorEdit);
						db.editBook(book);
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 4:
					try {
						db.listBooks();
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 5:
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
				case 6:
					print("Digite o nome do livro: ");
					scan.nextLine();
					String nameSearch = scan.nextLine();
					try {
						ArrayList<Book> books = db.getBooksByName(nameSearch);
						books.forEach(b -> printBook(b));
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 7:
					print("Digite o nome da editora: ");
					scan.nextLine();
					String editorSearch = scan.nextLine();
					try {
						book = db.getBookByEditor(editorSearch);
						printBook(book);
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 8:
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
			print("4. Buscar usuário por parte do nome");
			print("5. Buscar usuário por id");
			print("6: Pagar multa");
			print("7. Voltar");
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
						users.forEach(user -> printUser(user));
						if (users.size() == 0) throw new Exception(
							"Nenhum usuário cadastrado!\n"
						);
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 4:
					print("Digite o nome do usuário: ");
					scan.nextLine();
					String nameSearch = scan.nextLine();
					try {
						ArrayList<User> usersFound = new ArrayList<User>();
						for (User user : users) {
							if (user.getName().contains(nameSearch)) {
								usersFound.add(user);
							}
						}
						if (usersFound.size() == 0) throw new Exception(
							"Nenhum usuário encontrado!\n"
						);
						usersFound.forEach(user -> printUser(user));
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 5:
					print("Digite o id do usuário: ");
					scan.nextLine();
					String idSearch = scan.nextLine();
					try {
						User userFound = null;
						for (User user : users) {
							if (user.getId().equals(idSearch)) {
								userFound = user;
								break;
							}
						}
						if (userFound == null) throw new Exception(
							"Nenhum usuário encontrado!\n"
						);
						printUser(userFound);
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 6:
					print("Digite o id do usuário que pagará sua multa: ");
					scan.nextLine();
					String id = scan.nextLine();
					try {
						User userFound = null;
						for (User user : users) {
							if (user.getId().equals(id)) {
								userFound = user;
								break;
							}
						}
						if (userFound == null) throw new Exception(
							"Nenhum usuário encontrado!\n"
						);
						userFound.setPenalty(0);
						print("Multa paga com sucesso!\n");
					} catch (Exception e) {
						print(e.getMessage());
					}
				case 7:
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
			print("1. Fazer empréstimo");
			print("2. Devolver livro");
			print("3. Listar empréstimos");
			print("4. Renovar empréstimo");
			print("5. Buscar empréstimos de livro por ISBN");
			print("6. Buscar empréstimos de livro por título");
			print("7: Buscar empréstimos por exemplar de livro");
			print("8: Buscar empréstimo por usuário");
			print("9. Voltar");
			int option = scan.nextInt();
			switch (option) {
				case 1:
					scan.nextLine();
					print("Digite o nome do usuário: ");
					String userName = scan.nextLine();
					print("Digite o id do livro: ");
					String id = scan.nextLine();
					try {
						Optional<User> u = users
							.stream()
							.filter(x -> x.getName().equals(userName))
							.findFirst();
						User user = u.get();
						if (
							user.getPenalty() > 0 && getNumberOfLoans(user) > 0
						) throw new Exception(
							"Usuário com multa pendente, não é possível realizar empréstimo!\n"
						);

						int daysToLoan = 0;
						if (user.getUserType().toLowerCase() == "professor") {
							daysToLoan = 15;
							if (getNumberOfLoans(user) > 5) throw new Exception(
								"Professor não pode ter mais de 5 livros emprestados!\n"
							);
						} else {
							daysToLoan = 7;
							if (getNumberOfLoans(user) > 3) throw new Exception(
								"Aluno não pode ter mais de 3 livros emprestados!\n"
							);
						}

						Book book = db.getBookById(id);
						for (Reservation reservation : reservations) {
							if (reservation.getBook().getId().equals(id)) throw new Exception(
								"Outro usuário já possui reserva para este livro!\n"
							);
						}
						LocalDate.now();
						Loan loan = new Loan(
							book,
							user,
							LocalDate.now(),
							LocalDate.now().plusDays(daysToLoan)
						);
						loans.add(loan);
						print("Empréstimo realizado com sucesso!\n");
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 2:
					print("Digite o id do empréstimo: ");
					scan.nextLine();
					String loanId = scan.nextLine();
					try {
						if (
							loans.stream().filter(x -> x.getId().equals(loanId)).count() == 0
						) throw new Exception("Empréstimo não encontrado!\n");
						Loan loan = loans
							.stream()
							.filter(x -> x.getId().equals(loanId))
							.findFirst()
							.get();

						if (LocalDate.now().isAfter(loan.getReturnDate())) {
							int days = (int) ChronoUnit.DAYS.between(
								LocalDate.now(),
								loan.getReturnDate()
							);
							int prevPenalty = loan.getUser().getPenalty();
							loan.getUser().setPenalty(prevPenalty + days);
						}

						loans.remove(loan);

						print("Livro devolvido com sucesso!\n");
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 3:
					try {
						loans.forEach(l -> printLoan(l));
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 4:
					print("Digite o id do empréstimo: ");
					scan.nextLine();
					String loanIdRenew = scan.nextLine();
					try {
						if (
							loans
								.stream()
								.filter(x -> x.getId().equals(loanIdRenew))
								.count() ==
							0
						) throw new Exception("Empréstimo não encontrado!\n");
						Loan loanRenew = loans
							.stream()
							.filter(x -> x.getId().equals(loanIdRenew))
							.findFirst()
							.get();
						loanRenew.setReturnDate(LocalDate.now().plusDays(7));
						print("Empréstimo renovado com sucesso!\n");
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 5:
					print("Digite o isbn do livro: ");
					scan.nextLine();
					String isbnSearch = scan.nextLine();
					try {
						ArrayList<Loan> loansFound = new ArrayList<Loan>();
						for (Loan l : loans) {
							if (l.getBook().getIsbn().equals(isbnSearch)) {
								loansFound.add(l);
							}
						}
						if (loansFound.size() == 0) throw new Exception(
							"Nenhum empréstimo encontrado!\n"
						);
						loansFound.forEach(l -> printLoan(l));
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 6:
					print("Digite o título do livro: ");
					scan.nextLine();
					String titleSearch = scan.nextLine();
					try {
						ArrayList<Loan> loansFound = new ArrayList<Loan>();
						for (Loan l : loans) {
							if (l.getBook().getName().equals(titleSearch)) {
								loansFound.add(l);
							}
						}
						if (loansFound.size() == 0) throw new Exception(
							"Nenhum empréstimo encontrado!\n"
						);
						loansFound.forEach(l -> printLoan(l));
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 7:
					print("Digite o id do exemplar: ");
					scan.nextLine();
					String copyIdSearch = scan.nextLine();
					try {
						ArrayList<Loan> loansFound = new ArrayList<Loan>();
						for (Loan l : loans) {
							if (l.getBook().getId().equals(copyIdSearch)) {
								loansFound.add(l);
							}
						}
						if (loansFound.size() == 0) throw new Exception(
							"Nenhum empréstimo encontrado!\n"
						);
						loansFound.forEach(l -> printLoan(l));
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 8:
					print("Digite o nome do usuário: ");
					scan.nextLine();
					String userNameSearch = scan.nextLine();
					try {
						ArrayList<Loan> loansFound = new ArrayList<Loan>();
						for (Loan l : loans) {
							if (l.getUser().getName().equals(userNameSearch)) {
								loansFound.add(l);
							}
						}
						if (loansFound.size() == 0) throw new Exception(
							"Nenhum empréstimo encontrado!\n"
						);
						loansFound.forEach(l -> printLoan(l));
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 9:
					running = false;
					break;
				default:
					print("Opção inválida");
					break;
			}
		}
	}

	private static void reservationMenu(Database db, Scanner scan) {
		boolean running = true;
		while (running) {
			print("========= Reserva =========");
			print("1. Fazer reserva");
			print("2. Remover reserva");
			print("3. Listar reservas");
			print("4. Listar reservas por livro");
			print("5. Listar reservas por usuário");
			print("6. Voltar");
			print("Digite uma opção: ");
			int option = scan.nextInt();
			switch (option) {
				case 1:
					print("Digite o id do exemplar: ");
					scan.nextLine();
					String copyId = scan.nextLine();
					print("Digite o nome do usuário: ");
					String userName = scan.nextLine();
					try {
						if (
							users
								.stream()
								.filter(x -> x.getName().equals(userName))
								.count() ==
							0
						) throw new Exception("Usuário não encontrado!\n");

						for (Reservation r : reservations) {
							if (r.getBook().getId().equals(copyId)) {
								throw new Exception(
									"Exemplar já reservado por outro usuário!\n"
								);
							}
						}

						Book book = db.getBookById(copyId);
						for (Loan l : loans) {
							if (l.getBook().getId().equals(copyId)) {
								throw new Exception(
									"Exemplar já emprestado para outro usuário!\n"
								);
							}
						}

						User user = users
							.stream()
							.filter(x -> x.getName().equals(userName))
							.findFirst()
							.get();

						if (user.getPenalty() > 0) {
							throw new Exception(
								"Usuário com multa não pode reservar livros!\n"
							);
						}

						Reservation reservation = new Reservation(user, book);
						reservations.add(reservation);
						print("Reserva realizada com sucesso!\n");
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 2:
					print("Digite o id da reserva: ");
					scan.nextLine();
					String reservationId = scan.nextLine();
					try {
						if (
							reservations
								.stream()
								.filter(x -> x.getId().equals(reservationId))
								.count() ==
							0
						) throw new Exception("Reserva não encontrada!\n");
						reservations.remove(
							reservations
								.stream()
								.filter(x -> x.getId().equals(reservationId))
								.findFirst()
								.get()
						);
						print("Reserva removida com sucesso!\n");
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 3:
					try {
						if (reservations.size() == 0) throw new Exception(
							"Nenhuma reserva encontrada!\n"
						);
						reservations.forEach(r -> printReservation(r));
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 4:
					print("Digite o nome do livro: ");
					scan.nextLine();
					String bookName = scan.nextLine();
					try {
						ArrayList<Reservation> reservationsFound = new ArrayList<Reservation>();
						for (Reservation r : reservations) {
							if (r.getBook().getName().equals(bookName)) {
								reservationsFound.add(r);
							}
						}
						if (reservationsFound.size() == 0) throw new Exception(
							"Nenhuma reserva encontrada!\n"
						);
						reservationsFound.forEach(r -> printReservation(r));
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 5:
					print("Digite o nome do usuário: ");
					scan.nextLine();
					String userNameSearch = scan.nextLine();
					try {
						ArrayList<Reservation> reservationsFound = new ArrayList<Reservation>();
						for (Reservation r : reservations) {
							if (r.getUser().getName().equals(userNameSearch)) {
								reservationsFound.add(r);
							}
						}
						if (reservationsFound.size() == 0) throw new Exception(
							"Nenhuma reserva encontrada!\n"
						);
						reservationsFound.forEach(r -> printReservation(r));
					} catch (Exception e) {
						print(e.getMessage());
					}
					break;
				case 6:
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
			print("4. Reservas");
			print("5. Sair");
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
					reservationMenu(db, scan);
					break;
				case 5:
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
