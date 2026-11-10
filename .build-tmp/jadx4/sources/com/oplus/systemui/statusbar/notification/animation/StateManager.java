package com.oplus.systemui.statusbar.notification.animation;

import androidx.collection.ArrayMap;
import androidx.collection.SimpleArrayMap;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StateManager {
    private final Function2 combine;
    private Object combinedCache;

    /* JADX INFO: renamed from: default, reason: not valid java name */
    public final Object f4default;
    private final Function1 isValid;
    private final ArrayMap states = new ArrayMap(1);

    public StateManager(Function2 function2, Function1 function1, Object obj) {
        this.combine = function2;
        this.isValid = function1;
        this.f4default = obj;
    }

    public final Function2 getCombine() {
        return this.combine;
    }

    public final Object getDefault() {
        return this.f4default;
    }

    public final Object getState(Object obj) {
        Object objValueAt = this.f4default;
        if (obj != null) {
            Object obj2 = this.states.get(obj);
            return obj2 == null ? objValueAt : obj2;
        }
        Object obj3 = this.combinedCache;
        if (obj3 != null) {
            return obj3;
        }
        ArrayMap arrayMap = this.states;
        int i = ((SimpleArrayMap) arrayMap).size;
        if (i != 0) {
            if (i == 1) {
                objValueAt = arrayMap.valueAt(0);
            } else if (i != 2) {
                Object objValueAt2 = arrayMap.valueAt(0);
                int i2 = ((SimpleArrayMap) this.states).size;
                for (int i3 = 1; i3 < i2; i3++) {
                    objValueAt2 = this.combine.invoke(objValueAt2, this.states.valueAt(i3));
                }
                objValueAt = objValueAt2;
            } else {
                objValueAt = this.combine.invoke(arrayMap.valueAt(0), this.states.valueAt(1));
            }
        }
        this.combinedCache = objValueAt;
        return objValueAt;
    }

    public final Function1 isValid() {
        return this.isValid;
    }

    public final void requestState(Object obj, Object obj2) {
        if (obj2 == this.f4default || !((Boolean) this.isValid.invoke(obj2)).booleanValue()) {
            if (this.states.remove(obj) == null) {
                return;
            }
        } else if (Intrinsics.areEqual(this.states.get(obj), obj2)) {
            return;
        } else {
            this.states.put(obj, obj2);
        }
        this.combinedCache = null;
    }

    public String toString() {
        return "StateManager{ " + this.states + " }";
    }
}
