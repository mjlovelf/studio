package http.majian.com.volleyutil.common;

import android.os.Environment;

public class Constants {
    /**
     * appkey
     */
    public static final String APP_KEY = "588BE0EDEEEE34C64D342636FF92D2DA";
    /**
     * 升级包缓存的名称
     */
    public static final String UPDATE_PACKAGE_NAME = "hyr_upgrade.apk";
    public static String DEAD_APP_KEY = "51522CC11D114BE5E1995F3F9AA5D22D";

    /**
     * 字典类型
     */
    public static final String DICT_TYPE_CARRIAGE_CITY = "hyr_truck.carriage_city";
    public static final String DICT_TYPE_LENGTH = "hyr_truck.carriage_length";
    public static final String DICT_TYPE_COMPLAINT = "hyr_complaint.complaint_type";

    /**
     * 推送主题
     */
    public static final String CLIENT_TYPE = "citydriver";

    public static final String PUSH_TOPIC_UPGRADE = "hyr/citydriver/upgrade";   // 版本升级
    public static final String PUSH_TOPIC_VERIFY = "hyr/userAuthSuccess";       // 验证通过
    public static final String PUSH_TOPIC_VERIFY_FAILED = "hyr/userAuthFailed"; // 验证失败
    public static final String PUSH_TOPIC_POST = "hyr/postGoods";               // 发货推送（货主->司机）
    public static final String PUSH_TOPIC_SNATCH = "hyr/snatchGoods";           // 抢货推送（司机->货主）
    public static final String PUSH_TOPIC_LOCK = "hyr/lockGoods";               // 冻结推送
    public static final String PUSH_TOPIC_SNATCH_SUCCESS = "hyr/snatchSuccess"; // 成交推送（货主->司机）
    public static final String PUSH_TOPIC_CANCEL = "hyr/cancelGoods";           // 取消订单
    public static final String PUSH_TOPIC_CLOSE = "hyr/closeGoods";             // 关闭订单
    public static final String PUSH_TOPIC_COMMENT = "hyr/commentGoods";         // 评价推送
    public static final String PUSH_TOPIC_TRUCK_FOCUS = "hyr/focusTruck";       // 加为常用车
    public static final String PUSH_TOPIC_LOGIN_TIP = "hyr/dailyfirstlogin";    // 登陆奖励
    public static final String PUSH_TOPIC_DEAL_ALL = "hyr/snatchSuccessCongratulation"; // 全局广播
    public static final String PUSH_TOPIC_PIC_COMMON_MSG = "hyr/tkts_news"; //公共的消息
    /**
     * 默认根路径
     */
    public static final String APP_ROOT_PATH = Environment
            .getExternalStorageDirectory()
            + "/Android/data/com.chinaway.hyr/ts_driver/";

    // 默认图片路径
    public static final String APP_IMAGE_PATH = Environment
            .getExternalStorageDirectory()
            + "/Android/data/com.chinaway.hyr/ts_driver/image/";

    // 默认下载路径
    public static final String APP_DOWNLOAD_PATH = Environment
            .getExternalStorageDirectory()
            + "/Android/data/com.chinaway.hyr/ts_driver/download/";

    // 默认录音路径
    public static final String APP_AUDIO_PATH = Environment
            .getExternalStorageDirectory()
            + "/Android/data/com.chinaway.hyr/ts_driver/audio/";

    // 默认日志路径
    public static final String APP_CRASH_PATH = Environment
            .getExternalStorageDirectory()
            + "/Android/data/com.chinaway.hyr/ts_driver/crash/";

    // 默认安装文件
    public static final String APK_FILE_PATH = Environment
            .getExternalStorageDirectory()
            + "/Android/data/com.chinaway.hyr/ts_driver/ts_driver.apk";

    // SD卡空间下限
    public static final int SDCARD_OVARAGE_SIZE = 10240000; // SD最小空间阀值（10M）


    public static final int PIC_MAX_SCALE = 800;    //图片的最大尺寸

}
