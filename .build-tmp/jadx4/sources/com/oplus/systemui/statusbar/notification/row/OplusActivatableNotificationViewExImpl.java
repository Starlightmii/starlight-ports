package com.oplus.systemui.statusbar.notification.row;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.android.systemui.statusbar.notification.row.OplusActivatableNotificationViewEx;
import com.oplus.systemui.notification.material.NotificationSpotLightDelegate;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusActivatableNotificationViewExImpl extends OplusExpandableOutlineViewExImpl implements OplusActivatableNotificationViewEx {
    private final Context context;
    private NotificationBackgroundView notiBgView;
    public boolean skipNotificationSpotLightForCapture;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.row.OplusActivatableNotificationViewExImpl$drawNotificationSpotLightIfInstalled$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements NotificationSpotLightDelegate.NotificationSpotLightClipPathProvider {
        public /* synthetic */ ActivatableNotificationView $host;
        public /* synthetic */ OplusActivatableNotificationViewExImpl this$0;

        @Override // com.oplus.systemui.notification.material.NotificationSpotLightDelegate.NotificationSpotLightClipPathProvider
        public final NotificationSpotLightDelegate.ClipSpec getClipSpec() {
            return this.this$0.getSpotLightClipSpec(this.$host);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.row.OplusActivatableNotificationViewExImpl$initRowBg$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC02881 implements Runnable {
        public /* synthetic */ OplusActivatableNotificationViewExImpl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            NotificationBackgroundView notiBgView = this.this$0.getNotiBgView();
            if (notiBgView != null) {
                notiBgView.invalidate();
            }
        }
    }

    public OplusActivatableNotificationViewExImpl(Context context) {
        super(context);
        this.context = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableOutlineViewExImpl, com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public /* bridge */ /* synthetic */ void bind(View view) {
        super/*com.oplusos.systemui.common.BaseViewBinder*/.bind(view);
    }

    public void drawNotificationSpotLightIfInstalled(ActivatableNotificationView activatableNotificationView, Canvas canvas) {
        NotificationSpotLightDelegate.Companion companion = NotificationSpotLightDelegate.Companion;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        anonymousClass1.$host = activatableNotificationView;
        VarHandle.storeStoreFence();
        companion.draw(activatableNotificationView, canvas, anonymousClass1);
    }

    public int getNormalColor(Context context) {
        return context.getColor(2131102886);
    }

    public NotificationBackgroundView getNotiBgView() {
        return this.notiBgView;
    }

    @Override // com.oplus.systemui.statusbar.notification.row.OplusExpandableOutlineViewExImpl, com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl
    public /* bridge */ /* synthetic */ NotificationBackgroundView getNotificationBackgroundView() {
        return super.getNotificationBackgroundView();
    }

    public boolean getSkipNotificationSpotLightForCapture() {
        return this.skipNotificationSpotLightForCapture;
    }

    public void initRowBg(NotificationBackgroundView notificationBackgroundView) {
        NotificationBackgroundView notiBgView;
        NotificationBackgroundViewExt ext;
        setNotiBgView(notificationBackgroundView);
        NotificationBackgroundView notiBgView2 = getNotiBgView();
        if (notiBgView2 != null && (ext = notiBgView2.getExt()) != null) {
            ext.init();
        }
        NotificationBackgroundView notiBgView3 = getNotiBgView();
        if (notiBgView3 == null || !notiBgView3.isAttachedToWindow() || (notiBgView = getNotiBgView()) == null) {
            return;
        }
        RunnableC02881 runnableC02881 = new RunnableC02881();
        runnableC02881.this$0 = this;
        VarHandle.storeStoreFence();
        notiBgView.post(runnableC02881);
    }

    public void setNotiBgView(NotificationBackgroundView notificationBackgroundView) {
        this.notiBgView = notificationBackgroundView;
    }

    public void setSkipNotificationSpotLightForCapture(boolean z) {
        this.skipNotificationSpotLightForCapture = z;
    }

    public final boolean shouldUseSmoothClip() {
        NotificationBackgroundViewExt ext;
        if (super.shouldUseSmoothClip()) {
            return true;
        }
        NotificationBackgroundView notiBgView = getNotiBgView();
        return (notiBgView == null || (ext = notiBgView.getExt()) == null || ext.noColorized()) ? false : true;
    }

    public final void syncClipCanvasToBgView(float f, int i, int i2, float f2, int i3, int i4) {
        NotificationBackgroundView notiBgView = getNotiBgView();
        if ((notiBgView != null ? notiBgView.getExt() : null) instanceof NotificationBackgroundViewExtImp) {
            NotificationBackgroundView notiBgView2 = getNotiBgView();
            notiBgView2.getClass();
            NotificationBackgroundViewExtImp notificationBackgroundViewExtImp = (NotificationBackgroundViewExtImp) notiBgView2.getExt();
            if (notificationBackgroundViewExtImp.getClipLeft() == i && notificationBackgroundViewExtImp.getClipRight() == i3 && notificationBackgroundViewExtImp.getClipTop() == i2 && notificationBackgroundViewExtImp.getClipBottom() == i4 && notificationBackgroundViewExtImp.getClipTopRadius() == f && notificationBackgroundViewExtImp.getClipBottomRadius() == f2) {
                return;
            }
            notificationBackgroundViewExtImp.setClipLeft(i);
            notificationBackgroundViewExtImp.setClipRight(i3);
            notificationBackgroundViewExtImp.setClipTop(i2);
            notificationBackgroundViewExtImp.setClipBottom(i4);
            notificationBackgroundViewExtImp.setClipTopRadius(f);
            notificationBackgroundViewExtImp.setClipBottomRadius(f2);
            shouldUseSmoothClip();
            notificationBackgroundViewExtImp.updateRadius(f, f2);
            NotificationBackgroundView notiBgView3 = getNotiBgView();
            if (notiBgView3 != null) {
                notiBgView3.invalidate();
            }
        }
    }
}
