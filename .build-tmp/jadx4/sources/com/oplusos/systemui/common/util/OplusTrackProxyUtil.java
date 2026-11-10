package com.oplusos.systemui.common.util;

import android.content.Context;
import com.oplus.drs.statistics.OplusTrack;
import com.oplus.drs.statistics.data.CommonBean;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusTrackProxyUtil {
    private static final String DEFAULT_APP_CODE = "20082";
    public static final OplusTrackProxyUtil INSTANCE = new OplusTrackProxyUtil();
    private static final Set<String> initedAppCodes = new LinkedHashSet();
    public static final int FLAG_SEND_TO_DCS = 1;
    public static final int FLAG_SEND_TO_ATOM = 2;

    private final OplusTrack getTrack() {
        return OplusTrack.getInstance(DEFAULT_APP_CODE);
    }

    public static final void init(Context context) {
        INSTANCE.getTrack().init(context, true);
    }

    public static final void onCommon(Context context, String str, String str2, String str3, Map<String, String> map) {
        if (!Intrinsics.areEqual(str, DEFAULT_APP_CODE)) {
            INSTANCE.ensureInit(context, str);
        }
        INSTANCE.getClass();
        OplusTrack.getInstance(str).onCommon(context, str, str2, str3, map);
    }

    public static final void onCommonBatch(Context context, String str, String str2, List<? extends Map<String, String>> list, int i) {
        INSTANCE.getTrack().onCommonBatch(context, str, str2, list, i);
    }

    public static final void onCommonByBeanParams(Context context, String str, String str2, String str3, Map<String, String> map) {
        if (!Intrinsics.areEqual(str, DEFAULT_APP_CODE)) {
            INSTANCE.ensureInit(context, str);
        }
        CommonBean commonBean = new CommonBean(context, str, str2, str3);
        commonBean.setLogMap(map);
        INSTANCE.getClass();
        OplusTrack.getInstance(str).onCommon(commonBean);
    }

    public final synchronized void ensureInit(Context context, String str) {
        Set<String> set = initedAppCodes;
        if (set.contains(str)) {
            return;
        }
        OplusTrack.getInstance(str).init(context, true);
        set.add(str);
    }

    public static final void onCommon(Context context, String str, String str2, Map<String, String> map, int i) {
        INSTANCE.getTrack().onCommon(context, str, str2, map, i);
    }

    public static final void onCommon(Context context, String str, String str2, Map<String, String> map) {
        INSTANCE.getTrack().onCommon(context, str, str2, map);
    }

    public static final void onCommon(CommonBean commonBean) {
        INSTANCE.getTrack().onCommon(commonBean);
    }
}
