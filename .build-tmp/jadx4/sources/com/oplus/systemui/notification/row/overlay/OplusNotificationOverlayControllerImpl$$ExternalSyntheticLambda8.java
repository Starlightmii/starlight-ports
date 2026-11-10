package com.oplus.systemui.notification.row.overlay;

import android.graphics.Rect;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusNotificationOverlayControllerImpl$$ExternalSyntheticLambda8 implements Function1 {
    public /* synthetic */ OplusNotificationOverlayControllerImpl f$0;

    public final Object invoke(Object obj) {
        OplusNotificationOverlayControllerImpl oplusNotificationOverlayControllerImpl = this.f$0;
        Rect rect = (Rect) obj;
        OplusNotificationOverlayControllerImpl.Companion companion = OplusNotificationOverlayControllerImpl.Companion;
        OplusCustomRow authorView = oplusNotificationOverlayControllerImpl.getAuthorView();
        OplusCustomRow oplusCustomRow = authorView instanceof OplusCustomRow ? authorView : null;
        if (oplusCustomRow != null) {
            rect.set(oplusCustomRow.getClipRect());
            return Unit.INSTANCE;
        }
        ExpandableView authorView2 = oplusNotificationOverlayControllerImpl.getAuthorView();
        int clipTopAmount = authorView2 != null ? authorView2.getClipTopAmount() : 0;
        ExpandableView authorView3 = oplusNotificationOverlayControllerImpl.getAuthorView();
        int measuredWidth = authorView3 != null ? authorView3.getMeasuredWidth() : 0;
        ExpandableView authorView4 = oplusNotificationOverlayControllerImpl.getAuthorView();
        rect.set(0, clipTopAmount, measuredWidth, authorView4 != null ? authorView4.getIntrinsicHeight() : 0);
        return Unit.INSTANCE;
    }
}
