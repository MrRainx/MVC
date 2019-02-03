package controllers.Libraries;
/**
 *
 * @author MrRainx
 */
public class Validators {

    /**
     * REGULAR EXPRESSIONS
     */
    private static final String INT = "^[-|+]{0,1}[0-9]+[ ]*";
    private static final String DECIMAL = "^[+]{0,1}[0-9]*+[.]{0,1}+[0-9]{1,2}+[ ]*";
    private static final String WORD = "^[A-Za-z]*+[ ]*";
    private static String WORDS = "^[A-Za-z]*+[ ]{1}";
    private static final String NUMBER = "^[0-9]*+[ ]*";
    private static final String EMAIL = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";

    /**
     * @param Number
     * @return
     */
    public static boolean isInt(String Number) {

        return Number.matches(INT);

    }

    /**
     * @param Number
     * @param Max
     * @return
     */
    public static boolean isInt(String Number, long Max) {

        if (isInt(Number)) {
            long number = Long.parseLong(Number);
            return number <= Max;
        }

        System.out.println("is Not a Number");
        return false;

    }

    /**
     * @param Number
     * @param Min
     * @param Max
     * @return
     */
    public static boolean isInt(String Number, long Min, long Max) {
        if (isInt(Number)) {
            long number = Long.parseLong(Number);
            return number >= Min && number <= Max;
        }

        System.out.println("is Not a Number");
        return false;
    }

    /**
     * @param Number
     * @return
     */
    public static boolean isDecimal(String Number) {
        return Number.matches(DECIMAL);
    }

    /**
     * @param Number
     * @param Max
     * @return
     */
    public static boolean isDecimal(String Number, double Max) {
        if (isDecimal(Number)) {
            double number = Double.parseDouble(Number);
            return number <= Max;
        }

        System.out.println("is Not a Number");
        return false;
    }

    /**
     * @param Number
     * @param Min
     * @param Max
     * @return
     */
    public static boolean isDecimal(String Number, double Min, double Max) {
        if (isDecimal(Number)) {
            double number = Double.parseDouble(Number);
            return number >= Min && number <= Max;
        }

        System.out.println("is Not a Number");
        return false;
    }

    /**
     *
     * @param Text
     * @return
     */
    public static boolean isWord(String Text) {
        return Text.matches(WORD);
    }

    /**
     *
     * @param Text
     * @param Words Number of words to be evaluated
     * @return
     */
    public static boolean isWord(String Text, int Words) {

        for (int i = 0; i < Words - 1; i++) {
            if (i == Words - 2) {
                WORDS += "+[A-Za-z]*+[ ]*";
            } else {
                WORDS += "+[A-Za-z]*+[ ]{1}";
            }

        }

        return Text.matches(WORDS);
    }

    public static boolean isNumber(String Text) {
        return Text.matches(NUMBER);
    }

    public static boolean isNumber(String Text, int Max) {
        if (isNumber(Text)) {
            return Text.length() == Max;
        }

        System.out.println("Is Not a number");
        return false;
    }

    public static boolean isEmail(String Email) {
        return Email.matches(EMAIL);
    }

    public static boolean isCedula(String cedula) {
        boolean Cedula;
        try {

            if (cedula.length() == 10) { // ConstantesApp.LongitudCedula

                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
                    // Coeficientes de validación cédula
                    // El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }
                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        Cedula = true;
                    } else {
                        Cedula = (10 - (suma % 10)) == verificador;
                    }
                } else {
                    Cedula = false;
                }
            } else {
                Cedula = false;
            }
        } catch (NumberFormatException nfe) {
            Cedula = false;
        } catch (Exception err) {
            System.out.println("Una excepcion ocurrio en el proceso de validadcion");
            Cedula = false;
        }

        if (!Cedula) {
            System.out.println("La Cédula ingresada es Incorrecta");
        }
        
        return Cedula;
    }

    public static boolean isRuc(String RUC) {
        if (RUC.length() == 13) {

            String Cedula = RUC.substring(0, 10);
            String LastCharacters = RUC.substring(10, 13);
            
            if (isCedula(Cedula)) {
                return LastCharacters.equals("001");
            }
            
            return false;
        }
        
        System.out.println("Is Not RUC");
        return false;
    }
    
    
    public static Double getDoubleFromJFTXTfield(String Number){
        Number = Number.replace(",", "."); 
        return Double.parseDouble(Number);
    }
    
    
}
