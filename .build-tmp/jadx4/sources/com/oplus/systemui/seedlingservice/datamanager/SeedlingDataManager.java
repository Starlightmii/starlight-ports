package com.oplus.systemui.seedlingservice.datamanager;

import android.content.Context;
import com.oplus.systemui.seedlingservice.dnd.DndTip;
import com.oplus.systemui.seedlingservice.flashlight.FlashlightTip;
import com.oplus.systemui.seedlingservice.mediaControl.MediaControlTip;
import com.oplus.systemui.seedlingservice.opthreekey.RingModeTip;
import com.oplus.systemui.seedlingservice.tip.NormalTip;
import com.oplus.systemui.seedlingservice.utils.LogUtil;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingDataManager {
    private static final String TAG = "SeedlingDataManager";
    public static final SeedlingDataManager INSTANCE = new SeedlingDataManager();
    private static Map<String, NormalTip> tipMap = new LinkedHashMap();

    private SeedlingDataManager() {
    }

    private final void removeTip(String str) {
        LogUtil.i(TAG, "removeTip eventCode: " + str);
        tipMap.remove(str);
    }

    private final void setTip(String str, NormalTip normalTip) {
        LogUtil.i(TAG, "setTip: " + normalTip + " eventCode=" + str);
        tipMap.put(str, normalTip);
    }

    public final void dump(PrintWriter printWriter) {
        printWriter.println(TAG);
        printWriter.println("\ttipMap: " + tipMap);
    }

    public final NormalTip getTip(String str) {
        LogUtil.i(TAG, "getTip: " + tipMap.get(str) + " eventCode=" + str + " tipMap=" + tipMap);
        return tipMap.get(str);
    }

    public final NormalTip getTipFromServiceId(String str) {
        Object next;
        NormalTip normalTip;
        Iterator<T> it = tipMap.values().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            normalTip = (NormalTip) next;
        } while (!Intrinsics.areEqual(normalTip != null ? normalTip.getServiceId() : null, str));
        NormalTip normalTip2 = (NormalTip) next;
        LogUtil.i(TAG, "getTipsFromServiceId: serviceId=" + str + ", tips=" + (normalTip2 != null ? normalTip2.getEventCode() : null));
        return normalTip2;
    }

    public final void start(Context context) {
        RingModeTip ringModeTip = RingModeTip.INSTANCE;
        setTip(ringModeTip.getEventCode(), ringModeTip);
        ringModeTip.start(context);
        FlashlightTip flashlightTip = FlashlightTip.INSTANCE;
        setTip(flashlightTip.getEventCode(), flashlightTip);
        flashlightTip.start(context);
        DndTip dndTip = DndTip.INSTANCE;
        setTip(dndTip.getEventCode(), dndTip);
        dndTip.start(context);
        MediaControlTip mediaControlTip = MediaControlTip.INSTANCE;
        setTip(mediaControlTip.getEventCode(), mediaControlTip);
        mediaControlTip.start(context);
    }

    public final void stop(Context context) {
        RingModeTip ringModeTip = RingModeTip.INSTANCE;
        removeTip(ringModeTip.getEventCode());
        FlashlightTip flashlightTip = FlashlightTip.INSTANCE;
        removeTip(flashlightTip.getEventCode());
        DndTip dndTip = DndTip.INSTANCE;
        removeTip(dndTip.getEventCode());
        MediaControlTip mediaControlTip = MediaControlTip.INSTANCE;
        removeTip(mediaControlTip.getEventCode());
        ringModeTip.stop(context);
        flashlightTip.stop(context);
        dndTip.stop(context);
        mediaControlTip.stop(context);
    }
}
