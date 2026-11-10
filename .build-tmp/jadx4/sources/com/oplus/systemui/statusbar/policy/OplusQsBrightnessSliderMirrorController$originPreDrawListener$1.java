package com.oplus.systemui.statusbar.policy;

import android.view.View;
import android.view.ViewTreeObserver;
import com.android.systemui.qs.brightness.BrightnessMirrorController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBrightnessSliderMirrorController$originPreDrawListener$1 implements ViewTreeObserver.OnPreDrawListener {
    public /* synthetic */ OplusQsBrightnessSliderMirrorController this$0;

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        View originView = this.this$0.getOriginView();
        if (originView != null && ((BrightnessMirrorController) this.this$0).mBrightnessMirror.getVisibility() == 0 && originView.isShown() && originView.getWindowToken() != null && originView.isLaidOut()) {
            int i = this.this$0.originInt2Cache[0];
            int i2 = this.this$0.originInt2Cache[1];
            originView.getLocationInWindow(this.this$0.originInt2Cache);
            if (this.this$0.originInt2Cache[0] != i || this.this$0.originInt2Cache[1] != i2) {
                this.this$0.setMirrorTranslationAndSize();
            }
        }
        return true;
    }
}
