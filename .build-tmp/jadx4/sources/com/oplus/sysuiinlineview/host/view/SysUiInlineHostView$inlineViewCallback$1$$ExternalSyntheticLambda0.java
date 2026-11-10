package com.oplus.sysuiinlineview.host.view;

import android.os.Bundle;
import android.view.SurfaceControlViewHost;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SysUiInlineHostView$inlineViewCallback$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ SysUiInlineHostView f$0;
    public /* synthetic */ Object f$1;

    public /* synthetic */ SysUiInlineHostView$inlineViewCallback$1$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.f$0.onInlineViewCreate((SurfaceControlViewHost.SurfacePackage) this.f$1);
                break;
            default:
                this.f$0.onInlineViewRequest((Bundle) this.f$1);
                break;
        }
    }
}
