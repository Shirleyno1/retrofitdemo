package demo.archer.com.retrofitdemo.utils;

/**
 * 全局配置参数
 * 
 * @author qiudongchao
 * mPercent
 */
public class AppConfig {

    // **********************发版配置**************************

    /**
     * DEBUG模式 【注：生产debug为false；调试debug为true】
     */
    public static final boolean DEBUG = true;
    /**
     * 是否打开八叉乐统计 【【注：生产OPEN_APPMEASUREMENT为true；调试OPEN_APPMEASUREMENT为false】
     */
    public static final boolean OPEN_APPMEASUREMENT = false;
    /**
     * 数据统计【注：是否为生产环境数据统计】
     * 生产环境--true
     * 开发/测试-false
     */
    public static final boolean APP_MEASUREMENT_PRODUCT = false;
    /**
     * 用户升级版本号 【APK升级的版本号】
     */
    public static final String USERUPDATEVERSONCODE = "45.0.1";

    /**
     * 【修改配置文件里面的版本号；APK显示的版本号】 <manifest xmlns:android="http://schemas.android.com/apk/res/android"
     * package="com.gome.eshopnew" android:versionCode="34" android:versionName="3.0.2-dev" >
     */
    
    /**
     * 【修改配置文件里面】
     * private static final String KEY_DEBUG = "330dd9bb9f6c327e274a0382698599a0"; // 调试
     * private static final String KEY_PRD = "2a3ea533c6c4689aa48d31eb42b66753"; // 生产
     * <meta-data android:name="com.baidu.lbsapi.API_KEY" android:value="330dd9bb9f6c327e274a0382698599a0">
     * </meta-data>
     */
    // **********************常规配置**************************
    
    /**
     * 微信APPID【静态不修改】
     */
    public static final String WEIXIN_APPID = "wx28f63a05c1ee1424";
    
    
    public static final String MOVIE_SERVER_KEY = "17e3155bda8b4e3b98ac5674dc7627fe";
    public static final String FLIGHT_SERVER_KEY = "";
}
