package com.oplus.utrace.lib;

import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HLogConst {
    public static final String KEY_BUSINESS = "business";
    public static final String KEY_CLOUD_ENV = "cloud_env";
    public static final String KEY_END_TIME = "endTime";
    public static final String KEY_EXTRAS = "key_extras";
    public static final String KEY_EXTRAS_SIM_FD_TIMEOUT = "simulate_fd_timeout";
    public static final String KEY_EXTRAS_SIM_UPLOAD_TIMEOUT = "simulate_upload_timeout";
    public static final String KEY_EXTRAS_UPLOAD_FLAG = "suggested_upload_flag";
    public static final String KEY_LOG_ENABLED = "log_enabled";
    public static final String KEY_LOG_EXPIRE_DAYS = "log_expire_days";
    public static final String KEY_MAX_FILE_SIZE = "maxFileSize";
    public static final String KEY_MAX_LOG_FILES_MB = "max_log_files_mb";
    public static final String KEY_MAX_LOG_FILE_SIZE_MB = "max_log_file_size_mb";
    public static final String KEY_MODULE_NAME = "module";
    public static final String KEY_SEND_FROM = "sendFrom";
    public static final String KEY_SIZE = "size";
    public static final String KEY_START_TIME = "startTime";
    public static final String KEY_TRACE_ID = "traceId";
    public static final String KEY_UPLOAD_FLAG = "uploadFlag";
    public static final String KEY_USE_WIFI = "useWifi";
    public static final String METHOD_GET_LOG_CONFIG = "get_log_config";
    public static final String PUSH_TO_UPLOAD_ACTION = "com.oplus.pantanal.log.upload";
    public static final HLogConst INSTANCE = new HLogConst();
    private static final IntRange LOG_FILE_SIZE_MB_RANGE = new IntRange(1, 8, 1);

    public final IntRange getLOG_FILE_SIZE_MB_RANGE() {
        return LOG_FILE_SIZE_MB_RANGE;
    }
}
