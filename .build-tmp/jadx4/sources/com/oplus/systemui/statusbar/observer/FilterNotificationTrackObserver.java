package com.oplus.systemui.statusbar.observer;

import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FilterNotificationTrackObserver extends AbstractObserver {
    private static final String FILTER_NOTIFICATION_TRACK = "filter_notification_track";
    private static final String TAG = "FilterNotificationTrackObserver";
    public boolean mFilterNotificationTrackStatus;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new FilterNotificationTrackObserver$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final FilterNotificationTrackObserver getInstance() {
            return (FilterNotificationTrackObserver) FilterNotificationTrackObserver.instance$delegate.getValue();
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return FILTER_NOTIFICATION_TRACK;
    }

    public boolean getMFilterNotificationTrackStatus() {
        return this.mFilterNotificationTrackStatus;
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
        int i = Settings.Global.getInt(context.getContentResolver(), FILTER_NOTIFICATION_TRACK, 0);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "filter_notification_track change: ", "FilterNotificationTrackObserver-->", "SystemUi--Notification");
        }
        setMFilterNotificationTrackStatus(i == 0);
    }

    public void setMFilterNotificationTrackStatus(boolean z) {
        this.mFilterNotificationTrackStatus = z;
    }
}
