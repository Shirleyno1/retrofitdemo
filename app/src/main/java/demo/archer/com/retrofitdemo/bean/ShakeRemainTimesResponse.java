package demo.archer.com.retrofitdemo.bean;

import android.os.AsyncTask;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by songxingchao on 2015/4/2.
 */
public class ShakeRemainTimesResponse {
    public interface Watcher {
        public void notice(ShakeRemainTimesResponse response);
    }

    public int remainingTimes;

    public void loadDataFromServer(final Watcher watcher) {

        new AsyncTask<String, String, ShakeRemainTimesResponse>() {
            @Override
            protected ShakeRemainTimesResponse doInBackground(String[] params) {

                return getDataAndParse();
            }

            @Override
            protected void onPostExecute(ShakeRemainTimesResponse shakeRemainTimesResponse) {
                watcher.notice(shakeRemainTimesResponse);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
    }

    interface SpecialGoodsInterface {
        @FormUrlEncoded
        @POST("/promotion/gomeshakeprize/globalShakePrize.jsp\n")
        ShakeRemainTimesResponse doSpecialGood(@Field("body") String imei);   //@Field("first_name") String first, @Field("last_name") String last);  object.put("imei", args[0]); object.put("sign", args[1]);
    }


    public ShakeRemainTimesResponse getDataAndParse() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://mobile.gome.com.cn")
                .setRequestInterceptor(requestInterceptor)
                .build();
        SpecialGoodsInterface goods = restAdapter.create(SpecialGoodsInterface.class);

        return goods.doSpecialGood("{\"imei\":\"352107062154650\"}"); //{"sign":"8545B9C2ED53E7BC7AC211C3BEFED62B","imei":"352107062154650"}

    }


    RequestInterceptor requestInterceptor = new RequestInterceptor() {
        @Override
        public void intercept(RequestFacade request) {
            request.addHeader("User-Agent", "android GomeShopApp 45.0.1");
            String cookievalue1 = "DYN_USER_CONFIRM=rE7M5%2BM%2B40Ax8BaKlEB5CJUns%2FEsR9dHOBWwkfUXd9%2Fk%2B%2B3bKddo2K4heZ0%2BTS7nkM%2FJj6U0iG914sJa0SYyy0%2BlRdJMYNOZZy1kDtJRVVH%2BDazzE5CiDQ%3D%3D4f8c8238f671bd52a713c69db9a22de5;";
            String cookievalue2 = "DYN_USER_ID=212050089;";
            String cookievalue3 = "JSESSIONID=Lhp3VbQJLbfqcRwYcGF1M9Y017bJd11yPnbPYYBPCpQhn8FxGTQh!1745668011;";
            String cookievalue4 = "SCN=rE7M5%2BM%2B40Ax8BaKlEB5CJUns%2FEsR9dHOBWwkfUXd9%2Fk%2B%2B3bKddo2K4heZ0%2BTS7nkM%2FJj6U0iG914sJa0SYyy0%2BlRdJMYNOZZy1kDtJRVVH%2BDazzE5CiDQ%3D%3D4f8c8238f671bd52a713c69db9a22de5;";
            String cookievalue5 = "userPrefLanguage=zh_CN;";
            request.addHeader("Cookie", cookievalue1 + cookievalue2 + cookievalue3 + cookievalue4 + cookievalue5);
        }
    };

}
