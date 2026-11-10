package com.oplus.sysuiinlineview.host.view;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.SurfaceControlViewHost;
import com.oplus.sysuiinlineview.ISysUiInlineCallback;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SysUiInlineHostView$inlineViewCallback$1 extends ISysUiInlineCallback.Stub {
    final /* synthetic */ SysUiInlineHostView this$0;

    public SysUiInlineHostView$inlineViewCallback$1(SysUiInlineHostView sysUiInlineHostView) {
        this.this$0 = sysUiInlineHostView;
    }

    @Override // com.oplus.sysuiinlineview.ISysUiInlineCallback
    public void onInlineViewCreate(SurfaceControlViewHost.SurfacePackage surfacePackage) {
        SysUiInlineHostView sysUiInlineHostView = this.this$0;
        SysUiInlineHostView$inlineViewCallback$1$$ExternalSyntheticLambda0 sysUiInlineHostView$inlineViewCallback$1$$ExternalSyntheticLambda0 = new SysUiInlineHostView$inlineViewCallback$1$$ExternalSyntheticLambda0(0);
        sysUiInlineHostView$inlineViewCallback$1$$ExternalSyntheticLambda0.f$0 = sysUiInlineHostView;
        sysUiInlineHostView$inlineViewCallback$1$$ExternalSyntheticLambda0.f$1 = surfacePackage;
        VarHandle.storeStoreFence();
        sysUiInlineHostView.post(sysUiInlineHostView$inlineViewCallback$1$$ExternalSyntheticLambda0);
    }

    @Override // com.oplus.sysuiinlineview.ISysUiInlineCallback
    public void onInlineViewKeyEvent(int i, KeyEvent keyEvent) {
        SysUiInlineHostView sysUiInlineHostView = this.this$0;
        SysUiInlineHostView$inlineViewCallback$1$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new SysUiInlineHostView$inlineViewCallback$1$.ExternalSyntheticLambda1();
        externalSyntheticLambda1.f$0 = sysUiInlineHostView;
        externalSyntheticLambda1.f$1 = i;
        externalSyntheticLambda1.f$2 = keyEvent;
        VarHandle.storeStoreFence();
        sysUiInlineHostView.post(externalSyntheticLambda1);
    }

    @Override // com.oplus.sysuiinlineview.ISysUiInlineCallback
    public void onInlineViewRequest(Bundle bundle) {
        SysUiInlineHostView sysUiInlineHostView = this.this$0;
        SysUiInlineHostView$inlineViewCallback$1$$ExternalSyntheticLambda0 sysUiInlineHostView$inlineViewCallback$1$$ExternalSyntheticLambda0 = new SysUiInlineHostView$inlineViewCallback$1$$ExternalSyntheticLambda0(1);
        sysUiInlineHostView$inlineViewCallback$1$$ExternalSyntheticLambda0.f$0 = sysUiInlineHostView;
        sysUiInlineHostView$inlineViewCallback$1$$ExternalSyntheticLambda0.f$1 = bundle;
        VarHandle.storeStoreFence();
        sysUiInlineHostView.post(sysUiInlineHostView$inlineViewCallback$1$$ExternalSyntheticLambda0);
    }
}
