package com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler;

import android.util.Log;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleDataChangeInterceptor {
    private static final String TAG = "CapsuleDataChangeInterceptor";
    private static final MutableStateFlow _interceptorFlow;
    private static final StateFlow interceptorFlow;
    private final String name;
    public static final Companion Companion = new Companion();
    private static final Set<CapsuleDataChangeInterceptor> interceptorList = new LinkedHashSet();
    private final AtomicBoolean interceptedHolder = new AtomicBoolean(false);
    private final AtomicBoolean registered = new AtomicBoolean(false);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final StateFlow getInterceptorFlow() {
            return CapsuleDataChangeInterceptor.interceptorFlow;
        }
    }

    static {
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        _interceptorFlow = MutableStateFlow;
        interceptorFlow = FlowKt.asStateFlow(MutableStateFlow);
    }

    public CapsuleDataChangeInterceptor(String str) {
        this.name = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void intercept() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("intercept[", this.name, "]", "CapsuleDataChangeInterceptor-->", "SystemUi--Notification");
        }
        onInterceptorChanged(true);
    }

    public final void onInterceptorChanged(boolean z) {
        Object next;
        this.interceptedHolder.set(z);
        Iterator<T> it = interceptorList.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!((CapsuleDataChangeInterceptor) next).interceptedHolder.get());
        CapsuleDataChangeInterceptor capsuleDataChangeInterceptor = (CapsuleDataChangeInterceptor) next;
        if (capsuleDataChangeInterceptor != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("ApplyIntercepted[intercept by:", capsuleDataChangeInterceptor.name, "]", "CapsuleDataChangeInterceptor-->", "SystemUi--Notification");
            }
            _interceptorFlow.setValue(Boolean.TRUE);
        } else {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleDataChangeInterceptor-->ApplyIntercepted[not intercept]");
            }
            _interceptorFlow.setValue(Boolean.FALSE);
        }
        if (z || this.registered.get()) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("removeInterceptor[", this.name, "]", "CapsuleDataChangeInterceptor-->", "SystemUi--Notification");
        }
        interceptorList.remove(this);
    }

    public final void register() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("register[", this.name, "]", "CapsuleDataChangeInterceptor-->", "SystemUi--Notification");
        }
        if (this.registered.compareAndSet(false, true)) {
            interceptorList.add(this);
        }
    }

    public final void unIntercept() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("unIntercept[", this.name, "]", "CapsuleDataChangeInterceptor-->", "SystemUi--Notification");
        }
        onInterceptorChanged(false);
    }

    public final void unregister() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("unregister[", this.name, "]", "CapsuleDataChangeInterceptor-->", "SystemUi--Notification");
        }
        this.registered.compareAndSet(true, false);
    }
}
