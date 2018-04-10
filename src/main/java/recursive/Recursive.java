package recursive;

/**
 * A classe {@link Recursive} provê um estudo sobre recursividade.
 */
public class Recursive {

    private Recursive() {
    }

    /**
     * Soma os valores de um {@code double[]}.
     *
     * @param array      Vetor
     * @param startIndex Posição inicial (zero para o caso base)
     * @return Soma dos elementos
     */
    public static double sum(double[] array, int startIndex) {
        return startIndex < array.length ? array[startIndex] + sum(array, startIndex + 1) : 0;
    }

    /**
     * Multiplica dois valores utilizando o método das somas sucessivas.
     *
     * @param first  Primeiro termo da multiplicação
     * @param second Segundo termo da multiplicação
     * @return Produto da multiplicação
     */
    public static double product(double first, double second) {
        if (first < 0) return -product(-first, second);
        if (second < 0) return -product(first, -second);

        return second > 0 ? first + product(first, second - 1) : 0;
    }

    /**
     * Divide dois valores utilizando o método das subtrações sucessivas. A divisão é inteira.
     *
     * @param first  Primeiro termo da divisão
     * @param second Segundo termo da divisão
     * @return Resultado inteiro da divisão
     */
    public static double division(double first, double second) {
        if (second == 0) throw new ArithmeticException("Division by zero");

        if (first < 0) return -division(-first, second);
        if (second < 0) return -division(first, -second);
        if (first < second) return 0;

        return 1 + division(first - second, second);
    }

    /**
     * Obtem a raiz quadrada de um número utilizando o método babilônico.
     *
     * @param number    Número do qual deseja-se obter a raiz quadrada
     * @param tolerance Precisão da aproximação
     * @param guess     Palpite inicial (número arbitrário caso base)
     * @return Raiz quadrada aproximada com tolerância menor ou igual a indicada
     */
    public static double sqrt(double number, double tolerance, double guess) {
        return Math.abs((guess * guess) - number) > tolerance ? sqrt(number, tolerance, (guess + number / guess) / 2) : guess;
    }

    /**
     * Verifica se um conjunto contém determinado elemento.
     *
     * @param array      Conjunto onde deve ocorrer a procura
     * @param elem       Elemento a ser procurado
     * @param startIndex Posição inicial (zero caso base)
     * @param <T>        Tipo dos objetos do conjunto. Deve implementar o método {@code equals}
     * @return Verdadeiro se e somente se o conjunto conter o elemento; Falso caso contrário
     */
    public static <T> boolean contains(T[] array, T elem, int startIndex) {
        return startIndex < array.length && (array[startIndex].equals(elem) || contains(array, elem, startIndex + 1));
    }

    /**
     * Retorna uma String ao contrário.
     *
     * @param input String a ser invertida
     * @param index Posição inicial (zero caso base)
     * @return String resultante
     */
    public static String backwards(String input, int index) {
        return index < input.length() ? backwards(input, index + 1) + input.charAt(index) : "";
    }

    /**
     * Retorna o maior valor de um conjunto.
     *
     * @param array Conjunto de {@code double}
     * @param index Posição inicial da operação (zero caso base)
     * @param value Threshold ({@code null} caso base)
     * @return O maior valor (caso exista, cc null)
     */
    public static Double max(double[] array, int index, Double value) {
        if (index >= array.length)
            return value;

        return max(array, index + 1, value == null || array[index] > value ? array[index] : value);
    }

    /**
     * Retorna o menor valor de um conjunto.
     *
     * @param array Conjunto de {@code double}
     * @param index Posição inicial da operação (zero caso base)
     * @param value Threshold ({@code null} caso base)
     * @return O mínimo valor (caso exista, cc null)
     */
    public static Double min(double[] array, int index, Double value) {
        if (index >= array.length)
            return value;

        return min(array, index + 1, value == null || array[index] < value ? array[index] : value);
    }

    /**
     * Verifica se uma String é um palíndromo.
     *
     * @param input  String a ser verificada
     * @param offset Posição inicial a ser verificada (zero caso base)
     * @return Verdadeiro se e somente se a String for um palíndromo; Falso caso contrário
     */
    public static boolean isPalindrome(String input, int offset) {
        return offset >= input.length() || (input.charAt(offset) == input.charAt(input.length() - offset - 1) && isPalindrome(input, offset + 1));
    }

    /**
     * Converte um inteiro para uma {@link String} com sua representação binária.
     *
     * @param value Valor a ser convertido
     * @return {@link String} representando o valor na base binária.
     */
    public static String toBinary(int value) {
        if (value == 0) return "0";
        if (value < 0) return "-" + toBinary(-value);

        return toBinary(value / 2) + Integer.toString(value % 2);
    }
}