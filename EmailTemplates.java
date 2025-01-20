package utils;

public class EmailTemplates {
    public static String generateWelcomeMail(String name, String email, String verificationCode) {
        String welcomeMail = "<h1>Welcome "+ name+"</h1><hr><p style='font-weight:bold;font-size:22px;color:red'>" +
        "Please click over the account activation link <a href='http://localhost:8080/cims_v2/verify.do?email="+email+"&verification_code="+ verificationCode+"'>" + 
        "Activation Link</a></p>";
        
        return welcomeMail;
    }
}