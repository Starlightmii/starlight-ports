package com.oplus.systemui.notification.mediapanel.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.text.TextUtils;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AodMediaStatisticUtil {
    private static final String ACTION_DURATION = "action_duration";
    private static final String ACTION_FROM = "action_from";
    private static final String ACTION_FROM_AON = "1";
    private static final String ACTION_FROM_CLICK = "0";
    private static final String AOD_MEDIA_AON_INIT_TIMESTAMP = "aod_media_aon_init_timestamp";
    private static final String AOD_MEDIA_CARD_NAME = "aod_media_card";
    private static final String AOD_MEDIA_FACE_GAZE_TOTAL_DURATION = "aod_media_face_gaze_total_time";
    private static final String AOD_MEDIA_SMART_AOD_HE_TOTAL_DURATION = "aod_media_smart_aod_he_total_time";
    public static final String EVENT_CUSTOM_ACTION_0 = "customAction0";
    public static final String EVENT_CUSTOM_ACTION_1 = "customAction1";
    public static final String EVENT_CUSTOM_ACTION_2 = "customAction2";
    public static final String EVENT_CUSTOM_ACTION_3 = "customAction3";
    private static final String EVENT_FROM_AON = "aon";
    private static final String EVENT_FROM_CLICK = "click";
    private static final String EVENT_HIDDEN = "hidden";
    private static final String EVENT_ID_AOD_MEDIA_AON_DURATION = "aod_media_aon_duration";
    private static final String EVENT_ID_AOD_MEDIA_CLICK = "aod_media_click";
    private static final String EVENT_ID_AOD_MEDIA_EXPANDED = "aod_media_expanded";
    private static final String EVENT_ID_AOD_MEDIA_PANEL = "aod_media_panel";
    private static final String EVENT_ID_AOD_MEDIA_SHOWN = "aod_media_shown";
    public static final String EVENT_LIKE = "like";
    public static final String EVENT_MEDIA_ROOT = "mediaRoot";
    public static final String EVENT_NEXT = "next";
    public static final String EVENT_PAUSE = "pause";
    public static final String EVENT_PLAY = "play";
    public static final String EVENT_PREV = "prev";
    public static final String EVENT_REHEARSING = "rehearsing";
    private static final String EVENT_SHOWN = "shown";
    private static final String EXPAND_CARD_NAME = "expand_card_name";
    private static final String KEY_CLICK = "click";
    private static final String KEY_EXPAND_FROM = "expand_from";
    private static final String KEY_FACE_GAZE = "faceGaze";
    private static final String KEY_SMART_AOD_HE = "smartAodHE";
    private static final String KEY_STATE = "state";
    private static final String LOG_TAG = "20082";
    private static final String STATE_EXPAND_DURATION = "state_expand_duration";
    public static long aodMediaStartExpandTime = 0;
    public static long aodMediaStartUpExpandTime = 0;
    public static long sAodMediaAonInitTimeStamp = -1;
    public static long sAodMediaFaceGazeTotalTime = -1;
    public static long sAodMediaSmartAodHETotalTime = -1;
    public static boolean sAodMediaSupport;
    public static final AodMediaStatisticUtil INSTANCE = new AodMediaStatisticUtil();
    private static final Map<String, String> aodMediaTrackInfoMap = new LinkedHashMap();

    /* JADX INFO: renamed from: com.oplus.systemui.notification.mediapanel.util.AodMediaStatisticUtil$onTimeChanged$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Context $context;
        public final /* synthetic */ int $r8$classId;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    if (AodMediaStatisticUtil.sAodMediaAonInitTimeStamp < 0) {
                        Context context = this.$context;
                        AodMediaStatisticUtil.sAodMediaAonInitTimeStamp = (context == null || TextUtils.isEmpty(AodMediaStatisticUtil.AOD_MEDIA_AON_INIT_TIMESTAMP)) ? 0L : context.getSharedPreferences("user_data_notification_collection_info", 0).getLong(AodMediaStatisticUtil.AOD_MEDIA_AON_INIT_TIMESTAMP, 0L);
                    }
                    if (AodMediaStatisticUtil.sAodMediaAonInitTimeStamp > 0) {
                        if (System.currentTimeMillis() / 86400000 != AodMediaStatisticUtil.sAodMediaAonInitTimeStamp / 86400000) {
                            SharedPreferences sharedPreferences = this.$context.getSharedPreferences("user_data_notification_collection_info", 0);
                            if (AodMediaStatisticUtil.sAodMediaSmartAodHETotalTime < 0) {
                                AodMediaStatisticUtil.sAodMediaSmartAodHETotalTime = sharedPreferences.getLong(AodMediaStatisticUtil.AOD_MEDIA_SMART_AOD_HE_TOTAL_DURATION, 0L);
                            }
                            if (AodMediaStatisticUtil.sAodMediaFaceGazeTotalTime < 0) {
                                AodMediaStatisticUtil.sAodMediaFaceGazeTotalTime = sharedPreferences.getLong(AodMediaStatisticUtil.AOD_MEDIA_FACE_GAZE_TOTAL_DURATION, 0L);
                            }
                            Context context2 = this.$context;
                            long j = AodMediaStatisticUtil.sAodMediaSmartAodHETotalTime / 1000;
                            long j2 = AodMediaStatisticUtil.sAodMediaFaceGazeTotalTime / 1000;
                            HashMap map = new HashMap();
                            map.put(AodMediaStatisticUtil.KEY_SMART_AOD_HE, Long.toString(j));
                            map.put(AodMediaStatisticUtil.KEY_FACE_GAZE, Long.toString(j2));
                            OplusTrackProxyUtil.onCommon(context2, AodMediaStatisticUtil.LOG_TAG, AodMediaStatisticUtil.EVENT_ID_AOD_MEDIA_AON_DURATION, map);
                            AodMediaStatisticUtil.sAodMediaSmartAodHETotalTime = 0L;
                            AodMediaStatisticUtil.sAodMediaFaceGazeTotalTime = 0L;
                            AodMediaStatisticUtil.sAodMediaAonInitTimeStamp = 0L;
                            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                            editorEdit.putLong(AodMediaStatisticUtil.AOD_MEDIA_SMART_AOD_HE_TOTAL_DURATION, AodMediaStatisticUtil.sAodMediaSmartAodHETotalTime);
                            editorEdit.putLong(AodMediaStatisticUtil.AOD_MEDIA_FACE_GAZE_TOTAL_DURATION, AodMediaStatisticUtil.sAodMediaFaceGazeTotalTime);
                            editorEdit.putLong(AodMediaStatisticUtil.AOD_MEDIA_AON_INIT_TIMESTAMP, AodMediaStatisticUtil.sAodMediaAonInitTimeStamp);
                            editorEdit.apply();
                        }
                    }
                    break;
                default:
                    if (AodMediaStatisticUtil.sAodMediaAonInitTimeStamp < 0) {
                        AodMediaStatisticUtil.sAodMediaAonInitTimeStamp = !TextUtils.isEmpty(AodMediaStatisticUtil.AOD_MEDIA_AON_INIT_TIMESTAMP) ? this.$context.getSharedPreferences("user_data_notification_collection_info", 0).getLong(AodMediaStatisticUtil.AOD_MEDIA_AON_INIT_TIMESTAMP, 0L) : 0L;
                    }
                    if (AodMediaStatisticUtil.sAodMediaAonInitTimeStamp == 0) {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        AodMediaStatisticUtil.sAodMediaAonInitTimeStamp = jCurrentTimeMillis;
                        Context context3 = this.$context;
                        if (!TextUtils.isEmpty(AodMediaStatisticUtil.AOD_MEDIA_AON_INIT_TIMESTAMP)) {
                            SharedPreferences.Editor editorEdit2 = context3.getSharedPreferences("user_data_notification_collection_info", 0).edit();
                            editorEdit2.putLong(AodMediaStatisticUtil.AOD_MEDIA_AON_INIT_TIMESTAMP, jCurrentTimeMillis);
                            editorEdit2.apply();
                        }
                    }
                    break;
            }
        }
    }

    public static final void collectAodMediaActionClicked(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HashMap map = new HashMap();
        map.put("click", str);
        context.getClass();
        OplusTrackProxyUtil.onCommon(context, LOG_TAG, EVENT_ID_AOD_MEDIA_CLICK, map);
    }

    public static final void collectAodMediaExpanded(Context context, boolean z) {
        HashMap map = new HashMap();
        map.put(KEY_EXPAND_FROM, z ? "click" : EVENT_FROM_AON);
        Map<String, String> map2 = aodMediaTrackInfoMap;
        map2.put(ACTION_FROM, z ? "0" : "1");
        map2.put(EXPAND_CARD_NAME, AOD_MEDIA_CARD_NAME);
        context.getClass();
        OplusTrackProxyUtil.onCommon(context, LOG_TAG, EVENT_ID_AOD_MEDIA_EXPANDED, map);
    }

    public static final void collectAodMediaShown(Context context, boolean z) {
        HashMap map = new HashMap();
        map.put(KEY_STATE, z ? EVENT_SHOWN : EVENT_HIDDEN);
        context.getClass();
        OplusTrackProxyUtil.onCommon(context, LOG_TAG, EVENT_ID_AOD_MEDIA_SHOWN, map);
    }

    public static final Drawable getCustomActionDrawable(Icon icon, Context context) {
        if (icon.getType() != 4) {
            return icon.loadDrawable(context);
        }
        if (context != null) {
            return context.getDrawable(2131236669);
        }
        return null;
    }

    public static final void onTimeChanged(Context context) {
        if (sAodMediaSupport) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
            anonymousClass1.$context = context;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnWorkThread(anonymousClass1);
        }
    }

    public static final void setAodMediaCardExpandTime(boolean z, Context context) {
        if (z) {
            aodMediaStartExpandTime = System.currentTimeMillis();
            return;
        }
        if (aodMediaStartExpandTime != 0) {
            long jCurrentTimeMillis = System.currentTimeMillis() - aodMediaStartExpandTime;
            Map<String, String> map = aodMediaTrackInfoMap;
            map.put(STATE_EXPAND_DURATION, String.valueOf(jCurrentTimeMillis));
            aodMediaStartExpandTime = 0L;
            OplusTrackProxyUtil.onCommon(context, LOG_TAG, EVENT_ID_AOD_MEDIA_PANEL, map);
        }
    }

    public static final void setAodMediaStartUpExpandTime(boolean z) {
        if (z) {
            aodMediaStartUpExpandTime = System.currentTimeMillis();
        } else if (aodMediaStartUpExpandTime != 0) {
            aodMediaTrackInfoMap.put(ACTION_DURATION, String.valueOf(System.currentTimeMillis() - aodMediaStartUpExpandTime));
            aodMediaStartUpExpandTime = 0L;
        }
    }

    public static final void setAodMediaSupport(boolean z) {
        sAodMediaSupport = z;
    }

    public static final void writeAodMediaAonInitTime(Context context) {
        if (!sAodMediaSupport || context == null) {
            return;
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
        anonymousClass1.$context = context;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnWorkThread(anonymousClass1);
    }

    public static final void writeAodMediaAonTotalTime(Context context, long j, long j2) {
        if (!sAodMediaSupport || context == null) {
            return;
        }
        writeAodMediaAonTotalTime.1 r0 = new writeAodMediaAonTotalTime.1();
        r0.$context = context;
        r0.$smartAodHEDuration = j;
        r0.$faceGazeDuration = j2;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnWorkThread(r0);
    }
}
