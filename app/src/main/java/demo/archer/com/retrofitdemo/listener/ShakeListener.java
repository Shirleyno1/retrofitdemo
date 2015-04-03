package demo.archer.com.retrofitdemo.listener;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/**
 * Listener 检测摇晃手势
 */
public class ShakeListener implements SensorEventListener {


  /** 决定是否是摇晃的最小值. */
  private static final int MIN_FORCE = 18;

  /**
   * 一次摇晃需要的最少的方向改变次数
   */
  
  private static final int MIN_DIRECTION_CHANGE = 3;

  /** 动作之间的最长暂定时间 */
  private static final int MAX_PAUSE_BETHWEEN_DIRECTION_CHANGE = 200;

  /**晃动检测的最长时间 */
  private static final int MAX_TOTAL_DURATION_OF_SHAKE = 1000;

  /** 手势第一个方向启动的时间 */
  private long mFirstDirectionChangeTime = 0;
 
  /** 记录上一次完成一个摇一摇的时间 ，5秒内不再执行摇一摇 */
  private long mLastShakeTime = 0;

  /** 手势中最后一次开始时间 */
  private long mLastDirectionChangeTime;

  /** 到现在为止已经有几次方向改变次数 */
  private int mDirectionChangeCount = 0;

  /** 最后一次的x坐标位置 */
  private float lastX = 0;

  /** 最后一次的y坐标位置 */
  private float lastY = 0;

  /** 最后一次的z坐标位置*/
  private float lastZ = 0;

  /** 当检测到晃动的时候调用的接口变量 */
  private OnShakeListener mShakeListener;

  /**
   * 晃动的接口定义
   */
  public interface OnShakeListener {

    /**
     * 当检测到晃动时调用的方法
     */
    void onShake();
  }

  public void setOnShakeListener(OnShakeListener listener) {
    mShakeListener = listener;
  }

  @Override
  public void onSensorChanged(SensorEvent se) {
      
      if(mLastShakeTime!=0){
          if(System.currentTimeMillis()-mLastShakeTime<5000){
              return;
          }else{
              mLastShakeTime = 0;
          }
      }
    // 获取感应器的数据
    float x = se.values[0];
    float y = se.values[1];
    float z = se.values[2];

    // 计算移动的范围
 //   float totalMovement = Math.abs(x + y + z - lastX - lastY - lastZ);
    	float totalMovement = Math.abs(x-lastX) + Math.abs(y-lastY)+Math.abs(z-lastZ);
    if (totalMovement > MIN_FORCE) {

      // 获取当前时间
      long now = System.currentTimeMillis();

      // 保存第一次变化的时间
      if (mFirstDirectionChangeTime == 0) {
        mFirstDirectionChangeTime = now;
        mLastDirectionChangeTime = now;
      }

      // 检测是不是短时间内的那次变化
      long lastChangeWasAgo = now - mLastDirectionChangeTime;
      if (lastChangeWasAgo < MAX_PAUSE_BETHWEEN_DIRECTION_CHANGE) {

        // 保存变化数据
        mLastDirectionChangeTime = now;
        mDirectionChangeCount++;

        // 保存上次的坐标数据
        lastX = x;
        lastY = y;
        lastZ = z;

        // 检查移动次数是否足够
        if (mDirectionChangeCount >= MIN_DIRECTION_CHANGE) {

          // 检查总共时长
          long totalDuration = now - mFirstDirectionChangeTime;
          if (totalDuration < MAX_TOTAL_DURATION_OF_SHAKE) {
            mShakeListener.onShake();
            resetShakeParameters();
            mLastShakeTime = System.currentTimeMillis();
          }
        }

      } else {
        resetShakeParameters();
      }
    }
  }

  /**
   * 重设晃动参数到默认值
   */
  private void resetShakeParameters() {
    mFirstDirectionChangeTime = 0;
    mDirectionChangeCount = 0;
    mLastDirectionChangeTime = 0;
    lastX = 0;
    lastY = 0;
    lastZ = 0;
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {
  }

}