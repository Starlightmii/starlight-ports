package com.oplusos.systemui.common.sched;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.RemoteException;
import android.view.Display;
import com.oplus.vrr.IOPlusRefreshRate;
import com.oplus.vrr.OPlusRefreshRateManager;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusFrameRateManager {
    private static final String PACKAGE_NAME = "com.android.systemui";
    private static final String TAG = "OplusFrameRateManager";
    private static final String VERSION_NAME = "version-2-com.android.systemui-not-focus";
    public static final OplusFrameRateManager INSTANCE = new OplusFrameRateManager();
    private static final ConcurrentHashMap<String, FpsRequest> requests = new ConcurrentHashMap<>();
    public static volatile float currentAppliedFps = -1.0f;

    public static void doSetFrameRate(float f, Set set) {
        try {
            IOPlusRefreshRate iOPlusRefreshRate = OPlusRefreshRateManager.getDefault();
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "doSetFrameRate fps: " + f + ", result: " + (iOPlusRefreshRate != null ? Boolean.valueOf(iOPlusRefreshRate.setFrameRateTargetControl(f, "com.android.systemui", false, VERSION_NAME)) : null) + ", validReasons: " + set, null, 8);
        } catch (RemoteException e) {
            LogUtil.e$default(8, LogUtil.TAG_COMMON, TAG, "doSetFrameRate fail: " + e);
        }
    }

    public final List<String> getRequests() {
        ConcurrentHashMap<String, FpsRequest> concurrentHashMap = requests;
        ArrayList arrayList = new ArrayList(concurrentHashMap.size());
        for (Map.Entry<String, FpsRequest> entry : concurrentHashMap.entrySet()) {
            arrayList.add(entry.getKey() + ": " + entry.getValue());
        }
        return arrayList;
    }

    public final void resetFrameRate(Context context, String str) {
        setFrameRate(context, str, 0.0f, 0.0f);
    }

    public final void setFrameRate(Context context, String str, float f, float f2) {
        float fFloatValue = 0.0f;
        if (f <= 0.0f) {
            requests.remove(str);
        } else {
            requests.put(str, new FpsRequest(f, f2));
        }
        synchronized (this) {
            try {
                OplusFrameRateManager oplusFrameRateManager = INSTANCE;
                Context applicationContext = context.getApplicationContext();
                oplusFrameRateManager.getClass();
                Display display = ((DisplayManager) applicationContext.getSystemService("display")).getDisplay(0);
                float refreshRate = display != null ? display.getRefreshRate() : 60.0f;
                ConcurrentHashMap<String, FpsRequest> concurrentHashMap = requests;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Map.Entry<String, FpsRequest> entry : concurrentHashMap.entrySet()) {
                    FpsRequest value = entry.getValue();
                    if (value.getPrerequisiteFps() <= 0.0f || Math.abs(refreshRate - value.getPrerequisiteFps()) < 0.1f) {
                        linkedHashMap.put(entry.getKey(), entry.getValue());
                    }
                }
                if (!linkedHashMap.isEmpty()) {
                    Collection collectionValues = linkedHashMap.values();
                    ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(collectionValues, 10));
                    Iterator it = collectionValues.iterator();
                    while (it.hasNext()) {
                        arrayList.add(Float.valueOf(((FpsRequest) it.next()).getTargetFps()));
                    }
                    Float fMinOrNull = CollectionsKt___CollectionsKt.minOrNull(arrayList);
                    if (fMinOrNull != null) {
                        fFloatValue = fMinOrNull.floatValue();
                    }
                }
                if (Math.abs(fFloatValue - currentAppliedFps) < 0.01f) {
                    return;
                }
                currentAppliedFps = fFloatValue;
                OplusFrameRateManager oplusFrameRateManager2 = INSTANCE;
                Set setKeySet = linkedHashMap.keySet();
                oplusFrameRateManager2.getClass();
                doSetFrameRate(fFloatValue, setKeySet);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
