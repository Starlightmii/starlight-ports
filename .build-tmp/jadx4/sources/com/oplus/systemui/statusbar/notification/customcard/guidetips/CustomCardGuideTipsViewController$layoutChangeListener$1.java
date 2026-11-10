package com.oplus.systemui.statusbar.notification.customcard.guidetips;

import android.graphics.Rect;
import android.util.Log;
import android.view.ViewTreeObserver;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CustomCardGuideTipsViewController$layoutChangeListener$1 implements ViewTreeObserver.OnGlobalLayoutListener {
    public /* synthetic */ CustomCardGuideTipsViewController this$0;

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        if (this.this$0.customRowView != null) {
            Rect immersiveArea = this.this$0.getImmersiveArea();
            if (immersiveArea.width() <= 0 || immersiveArea.height() <= 0) {
                if (this.this$0.guideTipsShowing) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "CustomCardGuideTipsVC-->".concat("hotRectInner=" + immersiveArea + ", DisMiss guide"));
                    }
                    this.this$0.stopCountDown();
                    this.this$0.startEndAnimator();
                    return;
                }
                return;
            }
            CustomCardGuideTipsViewController customCardGuideTipsViewController = this.this$0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CustomCardGuideTipsVC-->".concat("OnGlobalLayoutChange hotRect=" + customCardGuideTipsViewController.hotRect + ", hotRectInner=" + immersiveArea));
            }
            if (Intrinsics.areEqual(this.this$0.hotRect, immersiveArea)) {
                return;
            }
            this.this$0.hotRect = immersiveArea;
            SeedlingGuideLightView seedlingGuideLightView = this.this$0.guideView;
            if (seedlingGuideLightView != null) {
                seedlingGuideLightView.setPosition(this.this$0.hotRect);
            }
        }
    }
}
