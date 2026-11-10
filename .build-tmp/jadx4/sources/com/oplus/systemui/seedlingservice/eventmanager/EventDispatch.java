package com.oplus.systemui.seedlingservice.eventmanager;

import com.oplus.systemui.seedlingservice.flashlight.FlashlightTip;
import com.oplus.systemui.seedlingservice.utils.LogUtil;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import kotlin.collections.CollectionsKt___CollectionsKt;
import org.json.JSONException;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EventDispatch {
    private static final String TAG = "EventDispatch";
    public static final EventDispatch INSTANCE = new EventDispatch();
    private static final ConcurrentHashMap<String, CopyOnWriteArrayList<EventListener>> listenerMap = new ConcurrentHashMap<>();

    private EventDispatch() {
    }

    public final void addEventListener(String str, EventListener eventListener) {
        LogUtil.i(TAG, "addEventListener " + str);
        CopyOnWriteArrayList<EventListener> copyOnWriteArrayListComputeIfAbsent = listenerMap.computeIfAbsent(str, new Function() { // from class: com.oplus.systemui.seedlingservice.eventmanager.EventDispatch$addEventListener$listeners$1
            @Override // java.util.function.Function
            public final CopyOnWriteArrayList<EventListener> apply(String str2) {
                return new CopyOnWriteArrayList<>();
            }
        });
        if (copyOnWriteArrayListComputeIfAbsent.contains(eventListener)) {
            return;
        }
        copyOnWriteArrayListComputeIfAbsent.add(eventListener);
    }

    public final void dispatch(Event event) {
        CopyOnWriteArrayList<EventListener> copyOnWriteArrayList = listenerMap.get(event.getEvent());
        if (copyOnWriteArrayList != null) {
            Iterator<T> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((EventListener) it.next()).onEvent(event);
            }
        }
    }

    public final void dump(PrintWriter printWriter) {
        printWriter.println(TAG);
        printWriter.println("\tlistenerMap: " + listenerMap);
    }

    public final void onLocaleChanged() throws JSONException {
        List<EventListener> list;
        CopyOnWriteArrayList<EventListener> copyOnWriteArrayList = listenerMap.get("flashlightEvent");
        if (copyOnWriteArrayList == null || (list = CollectionsKt___CollectionsKt.toList(copyOnWriteArrayList)) == null) {
            return;
        }
        for (EventListener eventListener : list) {
            FlashlightTip flashlightTip = eventListener instanceof FlashlightTip ? (FlashlightTip) eventListener : null;
            if (flashlightTip != null) {
                flashlightTip.onLocaleChanged();
            }
        }
    }

    public final void removeEventListener(String str, EventListener eventListener) {
        LogUtil.i(TAG, "removeEventListener " + str);
        ConcurrentHashMap<String, CopyOnWriteArrayList<EventListener>> concurrentHashMap = listenerMap;
        if (concurrentHashMap.containsKey(str)) {
            CopyOnWriteArrayList<EventListener> copyOnWriteArrayList = concurrentHashMap.get(str);
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.remove(eventListener);
            }
            CopyOnWriteArrayList<EventListener> copyOnWriteArrayList2 = concurrentHashMap.get(str);
            if (copyOnWriteArrayList2 == null || copyOnWriteArrayList2.isEmpty()) {
                concurrentHashMap.remove(str);
            }
        }
    }
}
