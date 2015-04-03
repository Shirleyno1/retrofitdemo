package demo.archer.com.retrofitdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.UUID;

/**
 * Created by songxingchao on 2015/4/1.
 */
public class MobileUtil {
    /**
     * 获取消息推送tokenID
     *
     * @param context
     * @return
     */
    public static String getClientId(Context context) {
        String clientID = "";
        try {
            SharedPreferences mPrefs = context.getSharedPreferences("Demo", context.MODE_PRIVATE);
            if (!TextUtils.isEmpty(MobileDeviceUtil.getInstance(context.getApplicationContext()).getMobileImei())) {
                clientID = MobileDeviceUtil.getInstance(context.getApplicationContext()).getMobileImei();
            } else if (!TextUtils
                    .isEmpty(MobileDeviceUtil.getInstance(context.getApplicationContext()).getMacAddress())) {
                clientID = MobileDeviceUtil.getInstance(context.getApplicationContext()).getMacAddress();
            } else if (!TextUtils.isEmpty(MobileDeviceUtil.getInstance(context.getApplicationContext()).getAndroidId())) {
                clientID = MobileDeviceUtil.getInstance(context.getApplicationContext()).getAndroidId();
            } else if (!TextUtils
                    .isEmpty(MobileDeviceUtil.getInstance(context.getApplicationContext()).getMobileImsi())) {
                clientID = MobileDeviceUtil.getInstance(context.getApplicationContext()).getMobileImsi();
            } else {
                clientID = mPrefs.getString("push_uuid", "404");
            }
            if ("404".equals(clientID)) {
                clientID = UUID.randomUUID().toString();
                mPrefs.edit().putString("push_uuid", clientID).commit();
                BDebug.d("liuyang", "存进去的token" + getClientId(context));
            }
            if (clientID.length() > 23) {
                clientID = clientID.substring(0, 22);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientID;
    }
}
