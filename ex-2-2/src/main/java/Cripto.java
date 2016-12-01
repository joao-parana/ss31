
public class Cripto {
  public static void main(String[] args) {
    char letraA = 'A';
    int valorNumericoDaLetraA = (int) letraA;
    System.out.println(valorNumericoDaLetraA);
    int[] letras = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
        'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    for (int deslocamento : letras) {
      char meuChar = (char) deslocamento;
      System.out.println(meuChar + " = " + deslocamento);
    }
    // Para o exemplo da chave BUGRE temos
    int[] deslocamentoBUGRE = { 'B', 'U', 'G', 'R', 'E' };
    int[] mensagem = { 'N', 'E', 'G', 'O', 'C', 'I', 'O', 'O', 'K', 'D', 'E', 'Z', 'M', 'I', 'L', 'H', 'O', 'E',
        'S' };
    int indiceChave = 0;
    for (int caracter : mensagem) {
      int criptografado = (caracter + (int) deslocamentoBUGRE[indiceChave]) - 65;
      if (criptografado > 90) {
        criptografado = criptografado - 26;
      }
      System.out.println((char) deslocamentoBUGRE[indiceChave] + " # " + caracter + " => " + criptografado + " - "
          + (char) criptografado);
      // Incrementa o indice da chave
      indiceChave++;
      // Reseta o indice da chave
      if (indiceChave > 4) {
        indiceChave = 0;
      }
    }
  }
}
