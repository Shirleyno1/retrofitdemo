package demo.archer.com.retrofitdemo.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import demo.archer.com.retrofitdemo.R;
import demo.archer.com.retrofitdemo.bean.ShakeRemainTimesResponse;
import demo.archer.com.retrofitdemo.listener.ShakeListener;
import demo.archer.com.retrofitdemo.utils.CommonUtility;

public class ShakeHomeActivity extends Activity implements ShakeRemainTimesResponse.Watcher, View.OnClickListener, ShakeListener.OnShakeListener {
    private String tag = "ShakeHomeActivity"; //
    private ImageView mBtnBack; // 返回按钮
    private ImageView mBtnRight; // 分享按钮
    private SensorManager mSensorManager; // 感应器管理器
    private ShakeListener mSensorListener; // 监听摇晃的listener变量
    private ImageView mImageviewShakeLogo; // 放主界面上摇晃的马的imageview
    //comment1
    private ImageView mImageviewLight; // 主界面上闪烁的彩灯
    private ImageView mTvShakeRules; // 规则按钮
    private PopupWindow mPopupWindow; // 摇中奖后的弹出窗
    private TextView tv_shakes_left_times;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(0xff120132));
        setContentView(R.layout.activity_shake_home);
        initView();
        initParams();
        addListener();
        initData();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            mSensorManager.registerListener(mSensorListener,
                    mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
        } else {
            CommonUtility.showMiddleToast(ShakeHomeActivity.this, "", "你的设备不支持摇一摇，请点击马尾巴参与摇奖");
        }
    }

    private void addListener() {
        mBtnBack.setVisibility(View.VISIBLE);
        mBtnRight.setVisibility(View.VISIBLE);
        mBtnBack.setOnClickListener(this);
        mBtnRight.setOnClickListener(this);
        mImageviewShakeLogo.setOnClickListener(this);
        mSensorListener.setOnShakeListener(this);
    }

    private void initParams() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeListener();
    }

    private void initView() {
        tv_shakes_left_times = (TextView) this.findViewById(R.id.tv_shakes_left_times);
        mBtnBack = (ImageView) this.findViewById(R.id.common_title_btn_back);
        mBtnRight = (ImageView) this.findViewById(R.id.common_title_btn_right);
        mBtnRight.setVisibility(View.VISIBLE);
        // mBtnRight.setBackgroundResource(R.drawable.common_title_share_bg_selector);
        mTvShakeRules = (ImageView) this.findViewById(R.id.shake_home_rules);
        RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) mTvShakeRules
                .getLayoutParams();
        int width = getWindowManager().getDefaultDisplay().getWidth();// use mobiledeviceutil

        int height = getWindowManager().getDefaultDisplay().getHeight();// use mobiledeviceutil

        params.setMargins(0, height / 13, width / 16, 0);

        mImageviewShakeLogo = (ImageView) this.findViewById(R.id.shake_home_horse);

        mImageviewLight = (ImageView) this.findViewById(R.id.shake_home_flash_light);
    }

    private void initData() {
        ShakeRemainTimesResponse response = new ShakeRemainTimesResponse();
        response.loadDataFromServer(this);
    }

    @Override
    public void notice(ShakeRemainTimesResponse response) {
        tv_shakes_left_times.setText(Html.fromHtml(formatLeftTimesString(response.remainingTimes)));
    }

    /**
     * 把剩余次数的颜色变成红色
     *
     * @param times
     * @return
     */
    private String formatLeftTimesString(int times) {
        return "<font color='#7a7585'>" + "您今天还可以摇 " + "</font>" + "<font color='#ffffff'>" + times + "</font>"
                + "<font color='#7a7585'>" + " 次" + "</font>";
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onShake() {
        CommonUtility.showMiddleToast(ShakeHomeActivity.this, "", "你的设备不支持摇一摇，请点击马尾巴参与摇奖");
    }
}
