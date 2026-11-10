package com.oplus.systemui.statusbar.notification.animation;

import androidx.collection.ArrayMap;
import androidx.collection.SimpleArrayMap;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ClipWidthManager {
    private Pair<Integer, ? extends Object> cachedResult;
    private final ArrayMap states = new ArrayMap(1);
    private final Object touchScaleKey;

    public ClipWidthManager(Object obj) {
        this.touchScaleKey = obj;
    }

    private final Pair<Integer, Object> getEffectiveResult() {
        Pair<Integer, ? extends Object> pair = this.cachedResult;
        if (pair != null) {
            return pair;
        }
        int i = ((SimpleArrayMap) this.states).size;
        Object obj = null;
        boolean z = false;
        int iIntValue = 0;
        for (int i2 = 0; i2 < i; i2++) {
            Object objKeyAt = this.states.keyAt(i2);
            Integer num = (Integer) this.states.valueAt(i2);
            if ((num == null || num.intValue() != 0) && !Intrinsics.areEqual(objKeyAt, this.touchScaleKey)) {
                if (num.intValue() > iIntValue) {
                    iIntValue = num.intValue();
                    obj = objKeyAt;
                }
                z = true;
            }
        }
        if (!z) {
            Integer num2 = (Integer) this.states.get(this.touchScaleKey);
            int iIntValue2 = num2 != null ? num2.intValue() : 0;
            if (iIntValue2 > 0) {
                obj = this.touchScaleKey;
                iIntValue = iIntValue2;
            }
        }
        Pair<Integer, ? extends Object> pair2 = new Pair<>(Integer.valueOf(iIntValue), obj);
        this.cachedResult = pair2;
        return pair2;
    }

    public final int get(Object obj) {
        if (obj == null) {
            return ((Number) getEffectiveResult().getFirst()).intValue();
        }
        Integer num = (Integer) this.states.get(obj);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public final Object getEffectiveSource() {
        return getEffectiveResult().getSecond();
    }

    public final void request(Object obj, int i) {
        this.cachedResult = null;
        ArrayMap arrayMap = this.states;
        if (i == 0) {
            arrayMap.remove(obj);
        } else {
            arrayMap.put(obj, Integer.valueOf(i));
        }
    }

    public String toString() {
        return "ClipWidthManager{ " + this.states + " }";
    }
}
