package uz.urinov.app.util;

public class ApiUrls {

    public static final String BASE_URL = "/api/v1/";

    /*-------------------------------------------------------*/
    /*                  CATEGORY APP URLS                    */
    /*-------------------------------------------------------*/
    public static final String CATEGORY_URL = BASE_URL + "category";
    public static final String CATEGORY_PAGE_URL ="/page";

    /*-------------------------------------------------------*/
    /*                  ATTACH APP URLS                      */
    /*-------------------------------------------------------*/
    public static final String ATTACH_URL = BASE_URL + "attach";
    public static final String ATTACH_UPLOAD_URL = "/upload";
    public static final String ATTACH_OPEN_URL = "/open/{fileName}";
    public static final String ATTACH_DOWNLOAD_URL = "/download/{fineName}";
    public static final String ATTACH_ALL_URL = "/all";
    public static final String ATTACH_ID_URL = "/{id}";

    /*-------------------------------------------------------*/
    /*                  PRODUCT APP URLS                     */
    /*-------------------------------------------------------*/
    public static final String PRODUCT_URL = BASE_URL + "products";

    /*-------------------------------------------------------*/
    /*                  ORDER APP URLS                       */
    /*-------------------------------------------------------*/
    public static final String ORDER_URL = BASE_URL + "orders";
    public static final String ORDER_ITEM_URL = BASE_URL + "order-items";

}
