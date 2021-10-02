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

            // initiate variables to improve code readability
            char currentCharacter = prefixToValidate.charAt(i);
            char nextCharacter = prefixToValidate.charAt(i + 1);

            // if current character is not an alphanumeric character or an underscore, a point or a dash, prefix is invalid
            if(!isValidPrefixChar(currentCharacter)) {
                return false;
            }

            // if current character is an underscore, a point or a dash and the next character is not alphanumeric, prefix is invalid
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

    /*
        Checks if the domain is valid:
            - made of two portions separated by a period
            - first portion contains at least one character
            - second portion contains at least two characters
            - first portion contains only alphanumeric characters, periods and dashes
            - in first portion, periods and dashes are always followed by alphanumeric character
            - first and last character of fist section must be alphanumeric
            - second portion contains only letters of the alphabet
     */
    public static boolean isValidDomain(String domainToValidate) {
        // initiate variable to improve code readability
        int indexOfLastDot = domainToValidate.lastIndexOf(".");

        // if domain is empty or does not contain a dot or the last character is a dot, domain is invalid
        if(domainToValidate.length() < 1
                || !domainToValidate.contains(".")
                || indexOfLastDot == (domainToValidate.length() - 1)) {
            return false;
        }

        // initiate variables to improve code readability
        String firstPortion = domainToValidate.substring(0, indexOfLastDot);
        String secondPortion = domainToValidate.substring(indexOfLastDot + 1);

        // if first portion does not contain at least one character or second portion does not contain at least two characters, domain is invalid
        if(!(firstPortion.length() > 0)
                || !(secondPortion.length() >= 2))
        {
            return false;
        }

        // initiate variables to improve code readability
        char firstFirstPortionCharacter = firstPortion.charAt(0);
        char lastFirstPortionCharacter = firstPortion.charAt(firstPortion.length() - 1);

        // if first and last character of first portion are not alphanumeric characters, domain is invalid
        if(!isAlphanumeric(firstFirstPortionCharacter)
                || !isAlphanumeric(lastFirstPortionCharacter)) {
            return false;
        }

        // loop through remaining characters of first portion
        for(int i = 1; i < firstPortion.length() - 1; i++){

            // initiate variables to improve code readability
            char currentCharacter = firstPortion.charAt(i);
            char nextCharacter = firstPortion.charAt(i + 1);

            // if current character is not alphanumeric, dot or dash, domain is invalid
            if(!isValidDomainChar(currentCharacter)) {
                return false;
            }

            // if current character is a point or a dash and the next character is not alphanumeric, domain is invalid
            if(currentCharacter == '.'
                    || currentCharacter == '-') {
                if(!isAlphanumeric(nextCharacter)) {
                    return false;
                }
            }
        }

        // loop through characters of second portion
        for(int i = 0; i < secondPortion.length(); i++) {

            // initiate variable to improve code readability
            char currentCharacter = secondPortion.charAt(i);

            // if current character is not a letter of the alphabet, domain is invalid
            if(!(currentCharacter >= 'a' && currentCharacter <= 'z')) {
                return false;
            }
        }

        return true;
    }

    // checks if email address is valid
    public static boolean isValidEmail(String emailToValidate) {
        if(!exactlyOneAt(emailToValidate)) {
            return false;
        };

        String prefix = getPrefix(emailToValidate);
        String domain = getDomain(emailToValidate);

        if(!isValidPrefix(prefix) || !isValidDomain(domain)) {
            return false;
        }

        return true;
    }
}
