import java.util.ArrayList;
import java.util.Scanner;

public class ContagemVotos02 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    ArrayList<Integer> votosCandidatos = new ArrayList<Integer>();

    System.out.println(
      "Entre com os votos dos eleitores (código 1 a 4), 5 para voto nulo, 6 para voto em branco:"
    );

    while (true) {
      System.out.print("Código do voto (0 para encerrar): ");
      int codigoVoto = scanner.nextInt();

      if (codigoVoto == 0) {
        break;
      }
      votosCandidatos.add(codigoVoto);
    }
    System.out.println("\nResultados:");
    for (int i = 0; i < 4; i++) {
      int i2 = i;
      System.out.println(
        "Total de votos para o candidato " +
        (i + 1) +
        ": " +
        votosCandidatos.stream().filter(n -> n == i2 + 1).count()
      );
    }
    System.out.println(
      "Total de votos nulos: " +
      votosCandidatos.stream().filter(n -> n == 5).count()
    );
    System.out.println(
      "Total de votos em branco: " +
      votosCandidatos.stream().filter(n -> n == 6).count()
    );
  }
}
