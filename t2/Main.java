public class Main {

  public static void main(String[] args) {
    System.out.println("Selecione o exercício que deseja executar:");
    System.out.println("1 - Exercício 1");
    System.out.println("2 - Exercício 2");
    System.out.println("3 - Exercício 3");
    System.out.println("4 - Exercício 4");
    System.out.println("5 - Exercício 5");

    int option = Integer.parseInt(System.console().readLine());
    switch (option) {
      case 1:
        PesquisaPrefeitura01.main(args);
        break;
      case 2:
        ContagemVotos02.main(args);
        break;
      case 3:
        CalculoMediaAlunos03.main(args);
        break;
      case 4:
        ContagemIntervalos04.main(args);
        break;
      case 5:
        AumentoPrecos05.main(args);
        break;
      default:
        System.out.println("Opção inválida!");
    }
  }
}
