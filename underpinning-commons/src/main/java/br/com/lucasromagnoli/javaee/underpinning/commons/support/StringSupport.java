package br.com.lucasromagnoli.javaee.underpinning.commons.support;

/**
 * @author github.com/lucasromagnoli
 * @since 27/01/2020
 */
public class StringSupport {
    public static String firstLetterToUpperCase(String target) {
        return (target.substring(0, 1).toUpperCase() + target.substring(1));
    }
}
