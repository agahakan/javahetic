package org.example.javahetic;

public class Calculateur {

    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Utilisation : java Calculateur [nombre1] [nombre2] [opération]");
            return;
        }

        try {
            double nombre1 = Double.parseDouble(args[0]);
            double nombre2 = Double.parseDouble(args[1]);
            String operateur = args[2];

            double resultat = calculer(nombre1, nombre2, operateur);

            System.out.println("Résultat : " + resultat);
        } catch (NumberFormatException e) {
            System.out.println("Les deux arguments doivent être des nombres.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static double calculer(double nombre1, double nombre2, String operateur) {
        switch (operateur) {
            case "+":
                return nombre1 + nombre2;
            case "-":
                return nombre1 - nombre2;
            case "*":
                return nombre1 * nombre2;
            default:
                throw new IllegalArgumentException("Opération invalide. Utilisez +, -, ou *.");
        }
    }
}
