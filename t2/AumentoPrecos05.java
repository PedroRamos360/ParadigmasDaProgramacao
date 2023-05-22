import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AumentoPrecos05 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    List<Double> precosNovos = new ArrayList<>();

    int codigo;
    do {
      System.out.println(
        "Digite o código do produto (ou um valor negativo para sair): "
      );
      codigo = scanner.nextInt();

      if (codigo >= 0) {
        System.out.println("Digite o preço de custo do produto: ");
        double precoCusto = scanner.nextDouble();

        double precoNovo = precoCusto * 1.2;
        precosNovos.add(precoNovo);

        System.out.println("Código do produto: " + codigo);
        System.out.println("Preço novo: " + precoNovo);
        System.out.println("----------------------");
      }
    } while (codigo >= 0);

    if (!precosNovos.isEmpty()) {
      double mediaPrecos = precosNovos
        .stream()
        .mapToDouble(Double::doubleValue)
        .average()
        .orElse(0);
      System.out.println("Média de preço dos produtos: " + mediaPrecos);
    } else {
      System.out.println("Nenhum produto foi cadastrado.");
    }

    scanner.close();
  }
}
