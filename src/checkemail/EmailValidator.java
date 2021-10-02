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

    // check if a character is a valid domain character (alphanumeric, dash, period)
    public static boolean isValidDomainChar(char charToCheck) {
        if(isAlphanumeric(charToCheck)
                || charToCheck == '-'
                || charToCheck == '.')
        {
            return true;
        }
        return false;
    }

    // check if a string contains exactly one @ character
    public static boolean exactlyOneAt(String emailAddress) {
        // initiate variable to store number of @s
        int numberOfAts = 0;


        // loop through all email address characters
        for(int i = 0; i < emailAddress.length(); i++) {
            // if character is @, increment numberOfAts
            if (emailAddress.charAt(i) == '@') {
                numberOfAts++;
            }

            // if numberOfAts more than 1, the email address contains more than one @ character: return false
            if (numberOfAts > 1) {
                return false;
            }
        }

        // check final value of numberOfAts: if 1, valid number of @s
        if(numberOfAts == 1) {
            return true;
        }

        return false; // if the program gets here, it means numberOfAts is 0, so the email address is invalid
    }

    // gets the prefix of the email address (all characters before the @ character)
    public static String getPrefix(String emailAddress) {
        // find index of @
        int indexAtChar = emailAddress.indexOf('@');

        // get all characters before the index of @
        String prefix = indexAtChar > 0 ? emailAddress.substring(0, indexAtChar) : "";

        return prefix;
    }

    // gets the domain of the email address (all characters after the @ character)
    public static String getDomain(String emailAddress) {
        // find index of @
        int indexAtChar = emailAddress.indexOf('@');

        // get all characters after the index of @ (adjust indexAtChar to exclude the @ character)
        String domain = emailAddress.substring(indexAtChar + 1);

        return domain;
    }

    /*
        Checks if the prefix is valid:
            - contains at least one character
            - contains only alphanumeric characters, underscores, periods and dashes
            - underscores, periods and dashes are always followed by an alphanumeric character
            - first and last characters are alphanumeric characters
     */
    public static boolean isValidPrefix(String prefixToValidate) {
        // if prefix is empty, prefix is invalid
        if(prefixToValidate.length() < 1) {
            return false;
        }

        // initiate variables for code readability
        char firstCharacter = prefixToValidate.charAt(0);
        char lastCharacter = prefixToValidate.charAt(prefixToValidate.length() - 1);

        // if first or last character are not alphanumeric characters, prefix is invalid
        if(!isAlphanumeric(firstCharacter)
                || !isAlphanumeric(lastCharacter)) {
            return false;
        }

        // loop through remaining characters
        for(int i = 1; i < prefixToValidate.length() - 1; i++){

            // initiate a variables to improve code readability
            char currentCharacter = prefixToValidate.charAt(i);
            char nextCharacter = prefixToValidate.charAt(i + 1);

            // if one of the characters is not an alphanumeric character or an underscore, a point or a dash, the prefix is invalid
            if(!isAlphanumeric(currentCharacter)
                    && currentCharacter != '_'
                    && currentCharacter != '.'
                    && currentCharacter != '-') {
                return false;
            }

            // if we have an underscore, a point or a dash, check if the next character is alphanumeric
            if(currentCharacter == '_'
                    || currentCharacter == '.'
                    || currentCharacter == '-') {
                if(!isAlphanumeric(nextCharacter)) {
                    return false;
                }
            }
        }

        // if all previous tests passed, the prefix is valid
        return true;
    }

    public static boolean isValidDomain(String domainToValidate) {
        return false;
    }

    public static boolean isValidEmail(String emailToValidate) {
        return false;
    }
}
