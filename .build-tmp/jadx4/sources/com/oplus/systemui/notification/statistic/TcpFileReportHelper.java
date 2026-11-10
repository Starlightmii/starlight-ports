package com.oplus.systemui.notification.statistic;

import android.app.AlarmManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.media.session.MediaSessionCompat$QueueItem$;
import android.util.Log;
import androidx.car.app.model.TimerSpan$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.util.AlarmTimeout;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.VarHandle;
import java.util.HashMap;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import oplus.util.OplusStatistics;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TcpFileReportHelper {
    private static final String APP_ID = "20082";
    private static final String EVENT_ID = "notification_context";
    private static final String LOG_TAG = "20082";
    private static final String POLICY = "policy";
    private static final String POLICY_ID = "102";
    private static final String TAG = "TcpFileUtil";
    private static final String TCP_FILE_PATCH = "/data/oplus/os/tcp_info.txt";
    private static final String TCP_SP_FILE_POINTER = "TCP_SP_FILE_POINTER";
    private static final String TCP_SP_FIRST_LINE = "tcp_sp_first_line";
    private static final String TCP_SP_NAME = "tcp_info";
    private static AlarmTimeout tcpAlarmTimeout;
    public static final TcpFileReportHelper INSTANCE = new TcpFileReportHelper();
    private static final Regex regex = new Regex(" TCP Event report - UID:| Pkg:| ConnCount:");

    /* JADX WARN: Code duplicated, block: B:107:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x01c1  */
    public static final void access$sendScheduleTcpAlarm(TcpFileReportHelper tcpFileReportHelper, Context context) {
        Context context2;
        String str;
        String strM;
        String line;
        String strRemoveSuffix;
        tcpFileReportHelper.getClass();
        AlarmTimeout alarmTimeout = tcpAlarmTimeout;
        if (alarmTimeout != null) {
            alarmTimeout.schedule(2, 3, 14400000L);
        }
        INSTANCE.getClass();
        File file = new File(TCP_FILE_PATCH);
        if (file.exists() && file.length() != 0) {
            context2 = context;
            SharedPreferences sharedPreferences = context2.getSharedPreferences(TCP_SP_NAME, 0);
            long j = sharedPreferences.getLong(TCP_SP_FILE_POINTER, 0L);
            String string = sharedPreferences.getString(TCP_SP_FIRST_LINE, "");
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            if (file.length() > 1258291.2d) {
                strM = MediaSessionCompat$QueueItem$.ExternalSyntheticOutline0.m(file.length(), " reason: file is too large", TimerSpan$.ExternalSyntheticOutline0.m(System.currentTimeMillis(), "time: ", " path: /data/oplus/os/tcp_info.txt fileLength: "));
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    KeyguardSettingProvider$.ExternalSyntheticOutline0.m("tcp File content: ", strM, "TcpFileUtil-->", "SystemUi--Notification");
                }
            } else {
                try {
                    str = "";
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charsets.UTF_8), OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING);
                        try {
                            line = bufferedReader.readLine();
                            CloseableKt.closeFinally(bufferedReader, (Throwable) null);
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                CloseableKt.closeFinally(bufferedReader, th);
                                throw th2;
                            }
                        }
                    } catch (IOException e) {
                        e = e;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("tcp File : error: ", e.getMessage(), "TcpFileUtil-->", "SystemUi--Notification");
                        }
                        line = str;
                    }
                } catch (IOException e2) {
                    e = e2;
                    str = "";
                }
                if (!Intrinsics.areEqual(string, line)) {
                    editorEdit.putString(TCP_SP_FIRST_LINE, line);
                    j = 0;
                }
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        fileInputStream.getChannel().position(j);
                        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(fileInputStream, Charsets.UTF_8), OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING);
                        try {
                            StringBuilder sb = new StringBuilder();
                            while (true) {
                                String line2 = bufferedReader2.readLine();
                                if (line2 == null) {
                                    break;
                                }
                                sb.append(regex.replace(line2, "|") + ";");
                            }
                            if (sb.length() > 0) {
                                editorEdit.putLong(TCP_SP_FILE_POINTER, fileInputStream.getChannel().position());
                                editorEdit.apply();
                                strRemoveSuffix = StringsKt__StringsKt.removeSuffix(sb.toString(), ";");
                            } else {
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                    Log.d(LogUtil.TAG + LogUtil.TAG_NOTIFICATION, TAG + LogUtil.SEPARATOR + "tcp File: No more content to read");
                                }
                                strRemoveSuffix = str;
                            }
                            CloseableKt.closeFinally(bufferedReader2, (Throwable) null);
                            CloseableKt.closeFinally(fileInputStream, (Throwable) null);
                            strM = strRemoveSuffix;
                        } catch (Throwable th3) {
                            try {
                                throw th3;
                            } catch (Throwable th4) {
                                CloseableKt.closeFinally(bufferedReader2, th3);
                                throw th4;
                            }
                        }
                    } catch (Throwable th5) {
                        try {
                            throw th5;
                        } catch (Throwable th6) {
                            CloseableKt.closeFinally(fileInputStream, th5);
                            throw th6;
                        }
                    }
                } catch (IOException e3) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("tcp File : error: ", e3.getMessage(), "TcpFileUtil-->", "SystemUi--Notification");
                    }
                    strM = str;
                }
            }
            if (strM.length() > 0) {
                HashMap map = new HashMap();
                map.put(POLICY, new JSONObject().put(POLICY_ID, strM).toString());
                OplusStatistics.onCommon(context2, "20082", "20082", EVENT_ID, map, false);
            }
        }
        context2 = context;
        str = "";
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "TcpFileUtil-->tcp File: /data/oplus/os/tcp_info.txt not exist or length is 0");
        }
        strM = str;
        if (strM.length() > 0) {
            HashMap map2 = new HashMap();
            map2.put(POLICY, new JSONObject().put(POLICY_ID, strM).toString());
            OplusStatistics.onCommon(context2, "20082", "20082", EVENT_ID, map2, false);
        }
    }

    public final void init(Context context, Handler handler) {
        Object systemService = context.getSystemService("alarm");
        AlarmManager alarmManager = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
        if (alarmManager == null) {
            return;
        }
        TcpFileReportHelper$startScheduleTcpAlarm$1 tcpFileReportHelper$startScheduleTcpAlarm$1 = new TcpFileReportHelper$startScheduleTcpAlarm$1();
        tcpFileReportHelper$startScheduleTcpAlarm$1.$context = context;
        VarHandle.storeStoreFence();
        AlarmTimeout alarmTimeout = new AlarmTimeout(alarmManager, tcpFileReportHelper$startScheduleTcpAlarm$1, TAG, handler);
        tcpAlarmTimeout = alarmTimeout;
        alarmTimeout.schedule(2, 3, 14400000L);
    }
}
