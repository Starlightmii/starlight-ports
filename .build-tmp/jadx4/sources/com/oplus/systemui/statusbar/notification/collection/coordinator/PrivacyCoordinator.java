package com.oplus.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.Coordinator;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.helper.PrivacyHelper;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpLayout;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper;
import com.oplus.systemui.notification.interruption.fullscreenbanner.view.FullScreenBanner;
import dagger.Lazy;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PrivacyCoordinator implements Coordinator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "PrivacyCoordinator";
    private final Runnable encryptListener;
    private final Lazy fullScreenBannerHelper;
    private final HeadsUpLayout headsUpLayout;
    private final HeadsUpManager headsUpManager;
    private final Lazy privacyHelper;
    private final NotificationStackScrollLayout stackScrollLayout;
    private final PrivacyCoordinator$appHideNotifFilter$1 appHideNotifFilter = new NotifFilter() { // from class: com.oplus.systemui.statusbar.notification.collection.coordinator.PrivacyCoordinator$appHideNotifFilter$1
        {
            super("PrivacyCoordinatorAppHide");
        }

        public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
            return ((PrivacyHelper) this.this$0.privacyHelper.get()).isAppHidden(notificationEntry.getSbn().getPackageName(), notificationEntry.getSbn().getUid());
        }
    };
    private final PrivacyCoordinator$encryptNotifFilter$1 encryptNotifFilter = new PrivacyCoordinator$encryptNotifFilter$1("PrivacyCoordinatorEncrypt");

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.collection.coordinator.PrivacyCoordinator$attach$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ PrivacyCoordinator this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            NotificationEntry notificationEntry;
            switch (this.$r8$classId) {
                case 0:
                    invalidateList(getName());
                    break;
                default:
                    this.this$0.stackScrollLayout.requestLayout();
                    if (this.this$0.headsUpManager.hasPinnedHeadsUp()) {
                        NotificationStackScrollLayout notificationStackScrollLayout = this.this$0.stackScrollLayout;
                        if (notificationStackScrollLayout.mAnimationsEnabled) {
                            notificationStackScrollLayout.mNeedViewResizeAnimation = true;
                            notificationStackScrollLayout.mNeedsAnimation = true;
                        }
                        this.this$0.headsUpLayout.requestAnimationOnViewResizeCustom();
                    }
                    this.this$0.encryptNotifFilter.invalidateList(this.this$0.encryptNotifFilter.getName());
                    FullScreenBannerHelper fullScreenBannerHelper = (FullScreenBannerHelper) this.this$0.fullScreenBannerHelper.get();
                    FullScreenBanner fullScreenBanner = fullScreenBannerHelper.mFullScreenBanner;
                    if (fullScreenBanner != null && fullScreenBanner.mReplyLayout.getVisibility() != 0 && (notificationEntry = fullScreenBannerHelper.mLastNotificationEntry) != null) {
                        fullScreenBannerHelper.mFullScreenBanner.updateBanner(fullScreenBannerHelper.buildNotification(fullScreenBannerHelper.mContext, notificationEntry));
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.oplus.systemui.statusbar.notification.collection.coordinator.PrivacyCoordinator$appHideNotifFilter$1] */
    public PrivacyCoordinator(Lazy lazy, NotificationStackScrollLayout notificationStackScrollLayout, HeadsUpManager headsUpManager, HeadsUpLayout headsUpLayout, Lazy lazy2) {
        this.privacyHelper = lazy;
        this.stackScrollLayout = notificationStackScrollLayout;
        this.headsUpManager = headsUpManager;
        this.headsUpLayout = headsUpLayout;
        this.fullScreenBannerHelper = lazy2;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.encryptListener = anonymousClass1;
    }

    public void attach(NotifPipeline notifPipeline) {
        PrivacyHelper privacyHelper = (PrivacyHelper) this.privacyHelper.get();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        privacyHelper.addAppHideListener(anonymousClass1);
        ((PrivacyHelper) this.privacyHelper.get()).addEncryptedChangedListener(this.encryptListener);
        notifPipeline.addPreGroupFilter(this.appHideNotifFilter);
        notifPipeline.addFinalizeFilter(this.encryptNotifFilter);
    }
}
