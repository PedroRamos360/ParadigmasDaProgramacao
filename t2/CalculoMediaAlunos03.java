import java.util.Scanner;
import java.util.stream.IntStream;

public class CalculoMediaAlunos03 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    IntStream
      .range(0, 5)
      .forEach(i -> {
        System.out.println("Digite o código do aluno: ");
        int codigoAluno = scanner.nextInt();

        System.out.println("Digite a primeira nota: ");
        double nota1 = scanner.nextDouble();

        System.out.println("Digite a segunda nota: ");
        double nota2 = scanner.nextDouble();

        System.out.println("Digite a terceira nota: ");
        double nota3 = scanner.nextDouble();

        double media = calcularMediaPonderada(nota1, nota2, nota3);

        System.out.println("Código do aluno: " + codigoAluno);
        System.out.println("Notas: " + nota1 + ", " + nota2 + ", " + nota3);
        System.out.println("Média: " + media);

        if (media >= 5) {
          System.out.println("Situação: APROVADO");
        } else {
          System.out.println("Situação: REPROVADO");
        }

        System.out.println("----------------------");
      });

    scanner.close();
  }

  public static double calcularMediaPonderada(
    double nota1,
    double nota2,
    double nota3
  ) {
    double maiorNota = Math.max(Math.max(nota1, nota2), nota3);
    double media = (nota1 * 3 + nota2 * 3 + maiorNota * 4) / 10;
    return media;
  }
}
