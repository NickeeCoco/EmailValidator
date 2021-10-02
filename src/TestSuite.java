import checkemail.EmailValidator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TestSuite {
    public static void main(String[] args) {
        runTestSuite();
    }

    public static boolean expectTrue(boolean bool) {
        if(bool) {
            //System.out.println("Test validated");
            return true;
        }
        return false;
    }

    public static boolean expectFalse(boolean bool) {
        if(!bool) {
            //System.out.println("Test validated");
            return true;
        }
        return false;
    }

    public static boolean expectEqual(String givenValue, String expectedValue){
        if( givenValue.equals(expectedValue))
        {
            return true;
        }
        return false;
    }

    public static void checkTestValidation(ArrayList<Boolean> checks, String validate, String failed)
    {
        for( Boolean b : checks)
        {
            if(!b) {
                System.out.println(failed);
                return;
            }
        }
        System.out.println(validate);
    }

    public static void runTestSuite() {
        validateAlphanumericChar();
        validatePrefixChar();
        validateDomainChar();
        validateExactlyOneAt();
        validateGetPrefix();
        validateGetDomain();
        validatePrefix();
        validateDomain();
        validateFullEmail();
    }

    public static void validateAlphanumericChar(){
        ArrayList<Boolean> checks = new ArrayList<Boolean>();
        checks.add(expectTrue(EmailValidator.isAlphanumeric('g')));
        checks.add(expectTrue(EmailValidator.isAlphanumeric('B')));
        checks.add(expectTrue(EmailValidator.isAlphanumeric('3')));
        checks.add(expectFalse(EmailValidator.isAlphanumeric('-')));

        checkTestValidation(checks, "Alphanumeric character test validated!", "The validateAlphanumeric test failed.");
    }

    public static void validatePrefixChar(){
        ArrayList<Boolean> checks = new ArrayList<Boolean>();
        checks.add(expectTrue(EmailValidator.isValidPrefixChar('_')));
        checks.add(expectFalse(EmailValidator.isValidPrefixChar('&')));

        checkTestValidation(checks, "Prefix character test validated!", "The validatePrefix char test failed.");
    }

    public static void validateDomainChar(){
        ArrayList<Boolean> checks = new ArrayList<Boolean>();
        checks.add(expectTrue(EmailValidator.isValidDomainChar('-')));
        checks.add(expectFalse(EmailValidator.isValidDomainChar('_')));

        checkTestValidation(checks, "Domain character test validated!","The validateDomainChar test failed.");
    }

    public static void validateExactlyOneAt(){
        ArrayList<Boolean> checks = new ArrayList<Boolean>();
        checks.add(expectTrue(EmailValidator.exactlyOneAt("example@email.com")));
        checks.add(expectFalse(EmailValidator.exactlyOneAt("b@n@n@s")));
        checks.add(expectTrue(EmailValidator.exactlyOneAt("@pple")));

        checkTestValidation(checks, "Exactly one @ test validated!", "The validateExactlyOneAt test failed.");
    }

    public static void validateGetPrefix(){
        ArrayList<Boolean> checks = new ArrayList<Boolean>();
        checks.add(expectEqual(EmailValidator.getPrefix("example@email.com"), "example"));
        checks.add(expectEqual(EmailValidator.getPrefix("cats @nd dogs"), "cats "));
        checks.add(expectEqual(EmailValidator.getPrefix("@pple"), ""));

        checkTestValidation(checks, "The getPrefix test is validated!", "The getPrefix test failed.");
    }

    public static void validateGetDomain(){
        ArrayList<Boolean> checks = new ArrayList<Boolean>();
        checks.add(expectEqual(EmailValidator.getDomain("example@email.com"), "email.com"));
        checks.add(expectEqual(EmailValidator.getDomain("cats @nd dogs"), "nd dogs"));
        checks.add(expectEqual(EmailValidator.getDomain("@pple"), "pple"));

        checkTestValidation(checks, "The getDomain test is validated!", "The getDomain test failed.");
    }

    public static void validatePrefix(){
        ArrayList<Boolean> checks = new ArrayList<Boolean>();
        checks.add(expectTrue(EmailValidator.isValidPrefix("abc_def")));
        checks.add(expectTrue(EmailValidator.isValidPrefix("mail.cc")));
        checks.add(expectFalse(EmailValidator.isValidPrefix("abc..d")));
        checks.add(expectFalse(EmailValidator.isValidPrefix("abc#d")));

        checkTestValidation(checks, "Prefix test validated!", "The validatePrefix test failed.");
    }

    public static void validateDomain(){
        ArrayList<Boolean> checks = new ArrayList<Boolean>();
        checks.add(expectTrue(EmailValidator.isValidDomain("mail.cc")));
        checks.add(expectTrue(EmailValidator.isValidDomain("abc-def.ghi")));
        checks.add(expectFalse(EmailValidator.isValidDomain("abc..d")));
        checks.add(expectFalse(EmailValidator.isValidDomain(".com")));
        checks.add(expectFalse(EmailValidator.isValidDomain(".com.com")));

        checkTestValidation(checks, "Domain test validated!", "The validatePrefix test failed.");
    }

    public static void validateFullEmail(){
        ArrayList<Boolean> checks = new ArrayList<Boolean>();
        checks.add(expectFalse(EmailValidator.isValidEmail("abc..def@mail.com")));
        checks.add(expectFalse(EmailValidator.isValidEmail("abc#def@mail.com")));
        checks.add(expectFalse(EmailValidator.isValidEmail("abc.def@mail")));
        checks.add(expectFalse(EmailValidator.isValidEmail("abc.def@mail..com")));
        checks.add(expectTrue(EmailValidator.isValidEmail("abc_d@mail.com")));
        checks.add(expectTrue(EmailValidator.isValidEmail("abc.def@mail.com")));
        checks.add(expectTrue(EmailValidator.isValidEmail("abc@mail.com")));
        checks.add(expectTrue(EmailValidator.isValidEmail("abc.def@mail-archive.com")));
        checks.add(expectTrue(EmailValidator.isValidEmail("abc.def@mail-archive.com")));

        checkTestValidation(checks, "Full email test validated!", "The validateFullEmail test failed.");
    }
}
