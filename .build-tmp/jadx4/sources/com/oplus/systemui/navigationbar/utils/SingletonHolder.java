package com.oplus.systemui.navigationbar.utils;

import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SingletonHolder {
    private Function0 creator;
    private volatile Object instance;

    public SingletonHolder(Function0 function0) {
        this.creator = function0;
    }

    public final Object get() {
        Object objInvoke;
        Object obj = this.instance;
        if (obj != null) {
            return obj;
        }
        synchronized (this) {
            objInvoke = this.instance;
            if (objInvoke == null) {
                Function0 function0 = this.creator;
                if (function0 == null) {
                    throw new IllegalStateException("Creator is null");
                }
                objInvoke = function0.invoke();
                this.instance = objInvoke;
                this.creator = null;
            }
        }
        return objInvoke;
    }
}
