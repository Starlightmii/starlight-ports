package com.oplus.systemui.navigationbar.utils;

import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SingletonHolderWithParam {
    private Function1 creator;
    private volatile Object instance;

    public SingletonHolderWithParam(Function1 function1) {
        this.creator = function1;
    }

    public final Object get(Object obj) {
        Object objInvoke;
        Object obj2 = this.instance;
        if (obj2 != null) {
            return obj2;
        }
        synchronized (this) {
            objInvoke = this.instance;
            if (objInvoke == null) {
                Function1 function1 = this.creator;
                if (function1 == null) {
                    throw new IllegalStateException("Creator is null");
                }
                objInvoke = function1.invoke(obj);
                this.instance = objInvoke;
                this.creator = null;
            }
        }
        return objInvoke;
    }
}
