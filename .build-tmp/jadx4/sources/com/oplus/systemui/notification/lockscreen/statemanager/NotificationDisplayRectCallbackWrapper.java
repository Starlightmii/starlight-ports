package com.oplus.systemui.notification.lockscreen.statemanager;

import android.view.View;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationDisplayRectCallbackWrapper {
    public static final Companion Companion = new Companion();
    private static final Pair<Float, Float> INVALID_DISPLAY_RECT_PAIR;
    private static final String TAG = "NotificationDisplayRectCallbackWrapper";
    private final Runnable dispatchRunnable;
    private final Function0 postHost;
    private final CopyOnWriteArraySet<NotificationDisplayRectCallback> callbacks = new CopyOnWriteArraySet<>();
    public volatile float lastTop = -1.0f;
    public volatile float lastBottom = -1.0f;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final Pair<Float, Float> getINVALID_DISPLAY_RECT_PAIR() {
            return NotificationDisplayRectCallbackWrapper.INVALID_DISPLAY_RECT_PAIR;
        }
    }

    static {
        Float fValueOf = Float.valueOf(-1.0f);
        INVALID_DISPLAY_RECT_PAIR = TuplesKt.to(fValueOf, fValueOf);
    }

    public NotificationDisplayRectCallbackWrapper(Function0 function0) {
        this.postHost = function0;
        NotificationDisplayRectCallbackWrapper$dispatchRunnable$1 notificationDisplayRectCallbackWrapper$dispatchRunnable$1 = new NotificationDisplayRectCallbackWrapper$dispatchRunnable$1();
        notificationDisplayRectCallbackWrapper$dispatchRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.dispatchRunnable = notificationDisplayRectCallbackWrapper$dispatchRunnable$1;
    }

    public static final void access$notifyCallbacks(NotificationDisplayRectCallbackWrapper notificationDisplayRectCallbackWrapper, NotificationDisplayRectCallbackWrapper$dispatchRunnable$1$$ExternalSyntheticLambda0 notificationDisplayRectCallbackWrapper$dispatchRunnable$1$$ExternalSyntheticLambda0) {
        Object obj;
        for (NotificationDisplayRectCallback notificationDisplayRectCallback : CollectionsKt___CollectionsKt.toList(notificationDisplayRectCallbackWrapper.callbacks)) {
            try {
                notificationDisplayRectCallback.getClass();
                notificationDisplayRectCallbackWrapper$dispatchRunnable$1$$ExternalSyntheticLambda0.invoke(notificationDisplayRectCallback);
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                LogUtil.e(LogUtil.TAG_NOTIFICATION, TAG, "[notifyCallbacks] callback failed: " + notificationDisplayRectCallback, th2);
            }
        }
    }

    public final void addNotificationDisplayRectCallback(NotificationDisplayRectCallback notificationDisplayRectCallback, boolean z) {
        this.callbacks.add(notificationDisplayRectCallback);
        if (z) {
            notificationDisplayRectCallback.onNotificationDisplayRectChanged(this.lastTop, this.lastBottom);
        }
    }

    public final void dispatchDisplayRectChanged(float f, float f2) {
        if (f == this.lastTop && f2 == this.lastBottom) {
            return;
        }
        this.lastTop = f;
        this.lastBottom = f2;
        View view = (View) this.postHost.invoke();
        view.removeCallbacks(this.dispatchRunnable);
        view.post(this.dispatchRunnable);
    }

    public final void removeNotificationDisplayRectCallback(NotificationDisplayRectCallback notificationDisplayRectCallback) {
        this.callbacks.remove(notificationDisplayRectCallback);
    }
}
