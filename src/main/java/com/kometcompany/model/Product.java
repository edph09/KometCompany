package com.kometcompany.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * clase de la entidad producto.
 *  *
 * @author Esteban Dario Pantoja Huertas
 * @version 1.0
 * @since 22 de Octubre de 2023
 */
@Data
@Entity
@Table(name = "tblproductpt")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "freshcutvalue")
    private Double freshCutValue;


    /**
     * metodo que genera el codigo del producto.
     * a.	El primer carácter será la primera letra de la palabra.
     * b.	Concatenar el número de caracteres distintos entre el primero y el último.
     * c.	Seguido por la última letra de la palabra inicial.
     * d.	Los espacios en blanco reemplazarlos por guión medio  "-"
     * e.	Si hay caracteres especiales los debe poner después del número de caracteres
     *  *
     * @author Esteban Dario Pantoja Huertas
     * @version 1.0
     * @since 22 de Octubre de 2023
     */
    public String generateProductCode(){
        String[] names = name.split(" ");
        for (int i = 0; i < names.length; i++) {
            names[i] = processText(names[i]);
        }
        String code= String.join("-", names);
        return code;
    }
    /**
     * metodo que procesa cadenas de caracteres.
     *
     *  el metodo devuelve la inicial, el conteo de las letras intermedias y la letra final*
     * @author Esteban Dario Pantoja Huertas
     * @version 1.0
     * @since 22 de Octubre de 2023
     */
    public String processText(String text) {

        StringBuilder processedText = new StringBuilder(text.substring(0, 1));
        int distinctCharCount = countDistinctChars(text);
        processedText.append(distinctCharCount);
        processedText.append(text.substring(text.length() - 1));
        processedText = new StringBuilder(processedText.toString().replace(" ", "-"));
        return processedText.toString();
    }
    /**
     * metodo que cuenta los caracteres.
     *
     *  el metodo cuenta la cantidad de caracteres no repetidos entre el primer y el ultimo caracter*
     * @author Esteban Dario Pantoja Huertas
     * @version 1.0
     * @since 22 de Octubre de 2023
     */
    private int countDistinctChars(String word) {
        int distinctCharCount = 0;
        char lastChar = '\0';
        char[] chars = word.toCharArray();
        for (int i = 1; i < chars.length - 1; i++) {
            char currentChar = chars[i];
            if (currentChar != ' ' && currentChar != lastChar) {
                distinctCharCount++;
            }
            lastChar = currentChar;
        }
        return distinctCharCount;
    }

    public String eliminarVocales(String cadena) {
        String res = cadena.replaceAll("[aeiouAEIOU\\W_]", "");
        String regex = "[^A-Za-z0-9]";
        Pattern patter = Pattern.compile(regex);
        Matcher match = patter.matcher(res);
        return match.replaceAll("");

    }

}
