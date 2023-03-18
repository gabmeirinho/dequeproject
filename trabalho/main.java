/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabalho;

/**
 *
 */
public class main {
        
  public static void main(String[] args) {
    Integer h1[] = { 0, 0, 0, 0, 0, 0, 0, 4, 0 };
    Integer h2[] = { 0, 0, 0, 0, 0, 0, 0, 2, 0 };
    Integer h3[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    Integer h4[] = { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
    CryptoInt grande1 = new CryptoInt(h1);
    CryptoInt grande2 = new CryptoInt(h2);
    CryptoInt grande3 = new CryptoInt(h3);
    CryptoInt grande4 = new CryptoInt(h4);
      CryptoInt addcryptoInt;
    CryptoInt multcryptoInt, rcryptoInt;
    String boaString = "1234567890123456789012345678901234567890";
    String maString = "12345678901234567890x12345678901234567890";
    grande1.isZero();
    grande2.isZero();
    grande3.isZero();
    grande4.isZero();

    addcryptoInt = grande3.addCryptoInt(grande4);
    System.out.println(grande3 + " + " + grande4 + " = " + addcryptoInt);
    multcryptoInt = grande1.multiplicaCryptoInt(grande2);
    System.out.println(grande1 + " * " + grande2 + " = " + multcryptoInt);
    multcryptoInt = grande4.multiplicaCryptoInt(grande3);
    System.out.println(grande4 + " * " + grande3 + " = " + multcryptoInt);

    System.out.println("CryptoInt_1 e " + grande1);
    System.out.println("CryptoInt_2 e " + grande2);
    System.out.println("CryptoInt_3 e " + grande3);
    System.out.println("CryptoInt_4 e " + grande4);

    String largeNumberString = "2";
    CryptoInt largeNumber = CryptoInt.getCryptoInt(largeNumberString);
    System.out.println("Double of large number: " + largeNumber);

    rcryptoInt = CryptoInt.getCryptoInt(boaString);
    rcryptoInt.setCryptoInt(boaString);
    //addcryptoInt=grande4.multiplicaCryptoInt(CryptoboaString);
    addcryptoInt = grande3.multiplicaCryptoInt(rcryptoInt);
    System.out.println(grande3 + " * " + rcryptoInt + " = " + addcryptoInt);
  }
}
