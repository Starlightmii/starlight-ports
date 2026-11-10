package com.oplus.systemui.statusbar.notification.collection.coordinator;

import android.util.Log;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator;
import com.android.systemui.statusbar.notification.interruption.HeadsUpViewBinder;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHeadsUpCoordinator$bindForAsyncHeadsUp$runnable$1 implements Runnable {
    public /* synthetic */ boolean $isPinnedByUser;
    public /* synthetic */ HeadsUpCoordinator.PostedEntry $posted;
    public /* synthetic */ OplusHeadsUpCoordinator this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.collection.coordinator.OplusHeadsUpCoordinator$bindForAsyncHeadsUp$runnable$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final /* synthetic */ class AnonymousClass3 implements HeadsUpViewBinder.HeadsUpBindCallback {
        public /* synthetic */ OplusHeadsUpCoordinator $tmp0;

        public final void onHeadsUpBindFinished(NotificationEntry notificationEntry, boolean z) {
            this.$tmp0.onHeadsUpViewBound(notificationEntry, z);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (((Boolean) this.this$0.oplusPanelViewInteractor.isVisibility().getValue()).booleanValue()) {
            HeadsUpCoordinator.PostedEntry postedEntry = this.$posted;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("skip bindForAsyncHeadsUp runnable, panel expanded, key=", postedEntry.getEntry().getEx().getEncryptionKey(), "HeadsUpCoordinator-->", "SystemUi--Notification");
            }
            this.this$0.oplusAvalanche.endEntry(this.$posted.getEntry());
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "HeadsUpCoordinator-->bindForAsyncHeadsUp:show heads up: run");
        }
        ((FullScreenBannerHelper) this.this$0.getFullScreenBannerHelper().get()).hideBanner(new Runnable[0]);
        this.this$0.getMEntriesBindingUntil().put(this.$posted.getKey(), Long.valueOf(this.this$0.getMNow() + 1000));
        HeadsUpViewBinder headsUpViewBinder = this.this$0.mHeadsUpViewBinder;
        NotificationEntry entry = this.$posted.getEntry();
        boolean z = this.$isPinnedByUser;
        OplusHeadsUpCoordinator oplusHeadsUpCoordinator = this.this$0;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.$tmp0 = oplusHeadsUpCoordinator;
        VarHandle.storeStoreFence();
        headsUpViewBinder.bindHeadsUpView(entry, z, anonymousClass3);
    }
}
