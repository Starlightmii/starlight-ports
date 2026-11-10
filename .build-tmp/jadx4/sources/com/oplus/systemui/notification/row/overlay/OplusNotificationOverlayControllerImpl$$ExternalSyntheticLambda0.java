package com.oplus.systemui.notification.row.overlay;

import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.stack.MediaContainerView;
import com.oplus.systemui.media.controls.ui.OplusMediaCarouselView;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusNotificationOverlayControllerImpl f$0;

    public /* synthetic */ OplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0025  */
    public final Object invoke() {
        NotificationBackgroundView notificationBackgroundView;
        int stackedNotificationMaskColor;
        NotificationBackgroundView notificationBackgroundView2;
        int i = this.$r8$classId;
        NotificationBackgroundViewExt ext = null;
        OplusNotificationOverlayControllerImpl oplusNotificationOverlayControllerImpl = this.f$0;
        switch (i) {
            case 0:
                return OplusNotificationOverlayControllerImpl.m99$r8$lambda$wtqZpzIzCU83G2g9REeXn5EX5o(oplusNotificationOverlayControllerImpl);
            case 1:
                return OplusNotificationOverlayControllerImpl.m98$r8$lambda$G_AXcBhGAgM6h6ADVy6JCm1tto(oplusNotificationOverlayControllerImpl);
            case 2:
                OplusNotificationOverlayControllerImpl.Companion companion = OplusNotificationOverlayControllerImpl.Companion;
                ExpandableView authorView = oplusNotificationOverlayControllerImpl.getAuthorView();
                if (authorView == null) {
                    return null;
                }
                if (authorView instanceof MediaContainerView) {
                    OplusMediaCarouselView oplusMediaCarouselViewTryGetMediaCarouselView = oplusNotificationOverlayControllerImpl.tryGetMediaCarouselView();
                    if (oplusMediaCarouselViewTryGetMediaCarouselView != null) {
                        return oplusMediaCarouselViewTryGetMediaCarouselView.getViewBlurProxy();
                    }
                    return null;
                }
                OplusExpandableViewEx oplusExpandableViewEx = authorView.mExpandableViewEx;
                NotificationBackgroundViewExt ext2 = (oplusExpandableViewEx == null || (notificationBackgroundView = oplusExpandableViewEx.getNotificationBackgroundView()) == null) ? null : notificationBackgroundView.getExt();
                if (ext2 != null) {
                    return ext2.getViewBlurProxy();
                }
                return null;
            default:
                OplusNotificationOverlayControllerImpl.Companion companion2 = OplusNotificationOverlayControllerImpl.Companion;
                ExpandableView authorView2 = oplusNotificationOverlayControllerImpl.getAuthorView();
                if (authorView2 == null) {
                    stackedNotificationMaskColor = 0;
                } else {
                    OplusExpandableViewEx oplusExpandableViewEx2 = authorView2.mExpandableViewEx;
                    if (oplusExpandableViewEx2 != null && (notificationBackgroundView2 = oplusExpandableViewEx2.getNotificationBackgroundView()) != null) {
                        ext = notificationBackgroundView2.getExt();
                    }
                    if (ext != null) {
                        stackedNotificationMaskColor = ext.getStackedNotificationMaskColor();
                    } else {
                        stackedNotificationMaskColor = 0;
                    }
                }
                return Integer.valueOf(stackedNotificationMaskColor);
        }
    }
}
