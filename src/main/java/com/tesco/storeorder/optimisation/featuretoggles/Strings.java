package org.deepti.featuretoggles;

public class Strings {
    public static String decapitalize(String name) {
        char[] chars = name.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        name = new String(chars);
        return name;
    }
}
