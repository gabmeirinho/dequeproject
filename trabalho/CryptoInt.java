package trabalho;

import myCollections.ArrayDeque;
import myCollections.Deque;


public class CryptoInt {

  private Deque<Integer> value;
  private final int MAX_SIZE = 50;

  public CryptoInt() {
    value = new ArrayDeque<>();
  }

  public CryptoInt(Integer[] digits) {
    value = new ArrayDeque<>();
    for (Integer i : digits) {
      if (i < 0) {
        throw new NumberFormatException(
          "Invalid input: input should be a non-negative integer"
        );
      }
      value.addLast(i);
    }
    while (value.size() > 1 && value.first() == 0) {
      value.removeFirst();
    }
    if (value.size() > MAX_SIZE) {
      throw new Error("Overflow");
    }
  }

  public CryptoInt(String largeString) {
    value = new ArrayDeque<>();
    for (int i = 0; i < largeString.length(); i++) {
      char c = largeString.charAt(i);
      if (Character.isDigit(c)) {
        value.addLast(Character.getNumericValue(c));
      }
    }
    if (value.size() > MAX_SIZE) {
      throw new Error("Overflow");
    }
  }

  public CryptoInt(CryptoInt large) {
    for (Integer i : large.value) {
      value.addLast(i);
    }
    if (value.size() > MAX_SIZE) {
      throw new Error("Overflow");
    }
  }

  public final void setCryptoInt(Integer[] digitos)
    throws NumberFormatException {
    for (Integer i : digitos) {
      if (i < 0) {
        throw new NumberFormatException(
          "Invalid input: input should be a positive integer"
        );
      }
    }
    while (!value.isEmpty()) {
      value.removeFirst();
    }
    for (Integer i : digitos) {
      value.addLast(i);
    }
    if (value.size() > MAX_SIZE) {
      throw new Error("Overflow");
    }
  }

  public final void setCryptoInt(String grandeString)
    throws NumberFormatException {
    if (!grandeString.matches("[0-9]+")) {
      throw new NumberFormatException(
        "Invalid input: input should be a non-negative integer"
      );
    }
    if (grandeString.length() > MAX_SIZE) {
      System.out.println(
        "Convertendo uma string num inteiro que e demasiado grande:"
      );
      System.out.println("Erro a converter a string num inteiro.");
      throw new Error("Overflow");
    }
    while (!value.isEmpty()) {
      value.removeFirst();
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < grandeString.length(); i++) {
      char c = grandeString.charAt(i);
      if (Character.isDigit(c)) {
        sb.append(c);
      }
    }
    for (int i = 0; i < sb.length(); i++) {
      value.addLast(Character.getNumericValue(sb.charAt(i)));
    }
  }

  //continuar a partir daqui

  public static CryptoInt getCryptoInt(String s) {
    CryptoInt c = new CryptoInt(s);
    CryptoInt doubleValue = c.multiplicaCryptoInt(new CryptoInt(new Integer[] { 2 })
    );
    return doubleValue;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Integer digit : value) {
      sb.append(digit);
    }
    return sb.toString();
  }

  public Integer[] toArray() {
    Integer[] result = new Integer[value.size()];
    int i = 0;
    while (!value.isEmpty()) {
      result[i++] = value.removeFirst();
    }
    return result;
  }

  public CryptoInt addCryptoInt(CryptoInt h) {
    Deque<Integer> i = this.value;
    Deque<Integer> ii = h.value;
    Deque<Integer> result = new ArrayDeque<>();
    int carry = 0;
    int size1 = i.size(), size2 = ii.size();
    while (size1 != 0 || size2 != 0) {
      int digit1 = 0, digit2 = 0;
      if (size1 != 0) {
        digit1 = i.isEmpty() ? 0 : i.removeLast();
        size1--;
        i.addFirst(digit1);
      }
      if (size2 != 0) {
        digit2 = ii.isEmpty() ? 0 : ii.removeLast();
        size2--;
        ii.addFirst(digit2);
      }
      int sum = digit1 + digit2 + carry;
      carry = sum / 10;
      result.addFirst(sum % 10);
    }
    if (carry > 0 && result.size() < MAX_SIZE) {
      result.addFirst(carry);
    }
    while (result.size() > 1 && result.first() == 0) {
      result.removeFirst();
    }
    CryptoInt res = new CryptoInt();
    res.value = result;
    return res;
  }

  private Integer[] aumenta(Integer[] a) {
    Integer[] array = new Integer[a.length + 1];
    array[0] = 0;
    for (int i = 0; i < a.length; i++) {
      array[i + 1] = a[i];
    }
    return array;
  }

  public CryptoInt multiplicaCryptoInt(CryptoInt h) {
    Deque<Integer> i = this.value;
    Deque<Integer> ii = h.value;
    int[][] products = new int[i.size()][ii.size() + i.size()]; // 2D array to store products of each digit multiplication
    int carry = 0;
    int currentDigit = 0;
    int currentRow = 0;

    // Multiply each digit of value1 by each digit of value2
    for (Integer v : i) {
      int currentCol = 0;
      for (Integer vv : ii) {
        products[currentRow][currentCol + currentDigit] = v * vv;
        currentCol++;
      }
      currentDigit++;
      currentRow++;
    }

    // Add up products and store in result deque
    Deque<Integer> result = new ArrayDeque<>();
    for (int col = products[0].length - 1; col >= 0; col--) {
      int sum = carry;
      for (int row = 0; row < products.length; row++) {
        sum += products[row][col];
      }
      if (sum % 10 != 0 || sum == 0) {
        result.addFirst(sum % 10);
      }
      carry = sum / 10;
    }
    if (carry != 0) result.addFirst(carry);
    //Remove leading zeroes
    while (result.size() > 1 && result.first() == 0) {
      result.removeFirst();
    }

    // Check if result exceeds maximum allowed size
    if (result.size() > MAX_SIZE) {
      throw new Error("Overflow");
    }
    if (result.size() == i.size() + ii.size() - 1) {
      result.removeLast();
    }

    // Create new CryptoInt with result deque
    if (result.isEmpty()) {
      result.addLast(0);
    }
    CryptoInt resCryptoInt = new CryptoInt();
    resCryptoInt.value = result;
    return resCryptoInt;
  }

  public boolean isZero() {
    if (value.size() == 1 && value.first() == 0) {
      System.out.println(value.first() + " e zero");
      return true;
    } else {
      System.out.println(this + " nao e zero");
      return false;
    }
  }
}
