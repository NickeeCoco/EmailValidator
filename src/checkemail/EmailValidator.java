package checkemail;

public class EmailValidator {
    public static void main(String[] args) {
        System.out.println("toto");
    }

    // check if a character is a valid letter of the English alphabet or one of the arabic numerals
    public static boolean isAlphanumeric(char charToCheck) {
        if((charToCheck >= 'A' && charToCheck <= 'Z')
                || (charToCheck >= 'a' && charToCheck <= 'z')
                || (charToCheck >= '0' && charToCheck <= '9'))
        {
            return  true;
        }
        return false;
        // return Character.isLetterOrDigit(charToCheck);
    }

    // check if a character is a valid prefix character (alphanumeric, dash, period, underscore)
    public static boolean isValidPrefixChar(char charToCheck) {
        if(isAlphanumeric(charToCheck)
                || charToCheck == '-'
                || charToCheck == '.'
                || charToCheck == '_')
        {
            return true;
        }
        return false;
    }

    public static boolean isValidDomainChar(char charToCheck) {
        return false;
    }

    public static boolean exactlyOneAt(String emailAddress) {
        return false;
    }

    public static String getPrefix(String emailAddress) {
        return "prefix";
    }

    public static String getDomain(String emailAddress) {
        return "domain";
    }

    public static boolean isValidPrefix(String prefixToValidate) {
        return false;
    }

    public static boolean isValidDomain(String domainToValidate) {
        return false;
    }

    public static boolean isValidEmail(String emailToValidate) {
        return false;
    }
}
