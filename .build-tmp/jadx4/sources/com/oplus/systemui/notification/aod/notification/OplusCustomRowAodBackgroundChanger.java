package com.oplus.systemui.notification.aod.notification;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.oplus.systemui.notification.base.radius.SmoothRoundExKt;
import com.oplus.systemui.notification.row.overlay.NotificationOverlayBackgroundView;
import com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayInteractor;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.row.NotificationBackgroundViewExtImp;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomRowAodBackgroundChanger {
    public static final Companion Companion = new Companion();
    public final int color;
    private final OplusAodRowBackgroundDrawable maskDrawable;
    private final OplusNotificationOverlayInteractor overlayInteractor;
    private Drawable stackSecondBg;

    public OplusCustomRowAodBackgroundChanger(Context context, OplusNotificationOverlayInteractor oplusNotificationOverlayInteractor) {
        this.overlayInteractor = oplusNotificationOverlayInteractor;
        this.color = context.getColor(2131101473);
        this.maskDrawable = new OplusAodRowBackgroundDrawable(context);
        Drawable smoothDrawable = SmoothRoundExKt.getSmoothDrawable(context, 2131102908);
        this.stackSecondBg = smoothDrawable == null ? new ColorDrawable(0) : smoothDrawable;
    }

    public final void applyMask(OplusCustomRow oplusCustomRow, float f) {
        NotificationBackgroundView mBackgroundNormal = oplusCustomRow.getMBackgroundNormal();
        NotificationBackgroundViewExt ext = mBackgroundNormal != null ? mBackgroundNormal.getExt() : null;
        NotificationBackgroundViewExtImp notificationBackgroundViewExtImp = ext instanceof NotificationBackgroundViewExtImp ? (NotificationBackgroundViewExtImp) ext : null;
        if (notificationBackgroundViewExtImp == null) {
            return;
        }
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        OplusAodRowBackgroundDrawable oplusAodRowBackgroundDrawable = this.maskDrawable;
        ArgbEvaluatorCompat argbEvaluatorCompat = ArgbEvaluatorCompat.getInstance();
        Integer numValueOf = Integer.valueOf(this.color);
        argbEvaluatorCompat.getClass();
        oplusAodRowBackgroundDrawable.setColor(ArgbEvaluatorCompat.evaluate(fCoerceIn, 0, numValueOf).intValue());
        notificationBackgroundViewExtImp.setPanoramicAodDrawable(this.maskDrawable);
        NotificationBackgroundView mBackgroundNormal2 = oplusCustomRow.getMBackgroundNormal();
        if (mBackgroundNormal2 != null) {
            mBackgroundNormal2.invalidate();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void changeAodOverlayViewColor(OplusCustomRow oplusCustomRow, float f) {
        NotificationOverlayBackgroundView notificationOverlayBackgroundView = this.overlayInteractor.isOverlayShowing(oplusCustomRow, true) ? (NotificationOverlayBackgroundView) oplusCustomRow.findViewById(2131364389) : null;
        this.stackSecondBg.setAlpha((int) (255.0f * f));
        if (f == 0.0f) {
            if (notificationOverlayBackgroundView != null) {
                notificationOverlayBackgroundView.setAodDrawable(null);
            }
        } else if (notificationOverlayBackgroundView != null) {
            notificationOverlayBackgroundView.setAodDrawable(this.stackSecondBg);
        }
    }

    public final void clearMask(OplusCustomRow oplusCustomRow) {
        NotificationBackgroundView mBackgroundNormal = oplusCustomRow.getMBackgroundNormal();
        NotificationBackgroundViewExt ext = mBackgroundNormal != null ? mBackgroundNormal.getExt() : null;
        NotificationBackgroundViewExtImp notificationBackgroundViewExtImp = ext instanceof NotificationBackgroundViewExtImp ? (NotificationBackgroundViewExtImp) ext : null;
        if (notificationBackgroundViewExtImp == null) {
            return;
        }
        notificationBackgroundViewExtImp.setPanoramicAodDrawable(null);
        NotificationBackgroundView mBackgroundNormal2 = oplusCustomRow.getMBackgroundNormal();
        if (mBackgroundNormal2 != null) {
            mBackgroundNormal2.invalidate();
        }
    }
}
