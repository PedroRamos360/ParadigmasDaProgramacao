import java.util.Scanner;
import java.util.stream.IntStream;

public class ContagemIntervalos04 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int[] contadorIntervalos = IntStream
      .range(0, 10)
      .map(i -> {
        System.out.println("Digite um número: ");
        int numero = scanner.nextInt();

        if (numero >= 0 && numero <= 25) {
          return 0;
        } else if (numero >= 26 && numero <= 50) {
          return 1;
        } else if (numero >= 51 && numero <= 75) {
          return 2;
        } else if (numero >= 76 && numero <= 100) {
          return 3;
        } else {
          return -1;
        }
      })
      .filter(intervalo -> intervalo != -1)
      .collect(
        () -> new int[4],
        (arr, intervalo) -> arr[intervalo]++,
        (arr1, arr2) -> IntStream.range(0, 4).forEach(i -> arr1[i] += arr2[i])
      );

    System.out.println(
      "Quantidade de números no intervalo [0, 25]: " + contadorIntervalos[0]
    );
    System.out.println(
      "Quantidade de números no intervalo [26, 50]: " + contadorIntervalos[1]
    );
    System.out.println(
      "Quantidade de números no intervalo [51, 75]: " + contadorIntervalos[2]
    );
    System.out.println(
      "Quantidade de números no intervalo [76, 100]: " + contadorIntervalos[3]
    );

    scanner.close();
  }
}
