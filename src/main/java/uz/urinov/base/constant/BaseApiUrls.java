package uz.urinov.base.constant;

public class BaseApiUrls {

    public static final String BASE_URL = "/api/v1/";

    /*-------------------------------------------------------*/
    /*                  BASE APP URLS                        */
    /*-------------------------------------------------------*/
    public static final String AUTH_URL = BASE_URL + "auth/";
    public static final String CHECK_URL = "check";
    public static final String REGISTER_URL = "register";
    public static final String VERIFY_URL = "verify-sms";
    public static final String RESEND_URL = "resend-sms/{phone}";
    public static final String LOGIN_URL = "login";
    public static final String REFRESH_TOKEN_URL = "refresh-token";
    public static final String FORGET_URL = "forget";
    public static final String UPDATE_PASSWORD_URL = "forget-update-password";




    public static final String ACCOUNT_URL = BASE_URL + "account";
    public static final String USER_URL = BASE_URL + "users";
    public static final String ROLE_URL = BASE_URL + "roles";
    public static final String FILES_URL = BASE_URL + "files";


}
