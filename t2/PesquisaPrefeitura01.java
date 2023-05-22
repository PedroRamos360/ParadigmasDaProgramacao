import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PesquisaPrefeitura01 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    List<Double> salarios = new ArrayList<>();
    List<Integer> numeroFilhosList = new ArrayList<>();

    System.out.println(
      "Entre com os dados dos habitantes (salário e número de filhos):"
    );

    while (true) {
      System.out.print("Salário: R$ ");
      double salario = scanner.nextDouble();

      if (salario < 0) {
        break;
      }

      System.out.print("Número de filhos: ");
      int numeroFilhos = scanner.nextInt();

      salarios.add(salario);
      numeroFilhosList.add(numeroFilhos);
    }

    int totalPessoas = salarios.size();
    double somaSalarios = salarios.stream().reduce(0.0, Double::sum);
    int somaFilhos = numeroFilhosList.stream().reduce(0, Integer::sum);
    double maiorSalario = salarios
      .stream()
      .max(Double::compareTo)
      .orElse(Double.NEGATIVE_INFINITY);
    long pessoasAteMil = salarios
      .stream()
      .filter(salario -> salario <= 1000)
      .count();

    double mediaSalario = somaSalarios / totalPessoas;
    double mediaFilhos = (double) somaFilhos / totalPessoas;
    double percentualAteMil = (double) pessoasAteMil / totalPessoas * 100;

    System.out.println("\nResultados:");
    System.out.println("Média de salário da população: R$ " + mediaSalario);
    System.out.println("Média de número de filhos: " + mediaFilhos);
    System.out.println("Maior salário: R$ " + maiorSalario);
    System.out.printf(
      "Percentual de pessoas com salário até R$ 1000,00: %.2f%%\n",
      percentualAteMil
    );
  }
}
