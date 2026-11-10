package com.oplus.systemui.statusbar.observer;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationTrackControllerObserver extends AbstractObserver {
    private static final String KEY_NOTIFICATION_PUSH_REPORT_TYPE = "notification_push_report_type";
    private static final String TAG = "NotificationTrackControllerObserver";
    public volatile int notificationTrackControllerValue;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new NotificationTrackControllerObserver$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final NotificationTrackControllerObserver getInstance() {
            return (NotificationTrackControllerObserver) NotificationTrackControllerObserver.instance$delegate.getValue();
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Handler getHandler() {
        return SystemUIThreadUtil.getWorkHandler();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return KEY_NOTIFICATION_PUSH_REPORT_TYPE;
    }

    public final int getNotificationTrackControllerValue() {
        return this.notificationTrackControllerValue;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toGlobalUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        super.onChange(z);
        Context context = getContext();
        if (context == null) {
            return;
        }
        int i = Settings.Global.getInt(context.getContentResolver(), KEY_NOTIFICATION_PUSH_REPORT_TYPE, 1);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "notification_push_report_type change: ", "NotificationTrackControllerObserver-->", "SystemUi--Notification");
        }
        this.notificationTrackControllerValue = i;
    }
}
