package com.oplus.systemui.notification.headsup.windowframe;

import android.graphics.Region;
import android.os.RemoteException;
import android.os.Trace;
import android.view.IWindow;
import android.view.IWindowSession;
import android.view.View;
import android.view.WindowManagerGlobal;
import android.widget.FrameLayout;
import com.coui.appcompat.snackbar.COUISnackBar;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SnackbarWindow$show$1$2 implements View.OnLayoutChangeListener {
    public /* synthetic */ COUISnackBar $this_apply;
    public /* synthetic */ FrameLayout $windowView;
    public /* synthetic */ SnackbarWindow this$0;

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        FrameLayout frameLayout = this.$windowView;
        COUISnackBar cOUISnackBar = this.$this_apply;
        try {
            Region region = new Region(frameLayout.getLeft(), frameLayout.getTop(), frameLayout.getRight(), frameLayout.getBottom());
            Region region2 = new Region(cOUISnackBar.getLeft(), cOUISnackBar.getTop(), cOUISnackBar.getRight(), cOUISnackBar.getBottom());
            region.op(region2, Region.Op.DIFFERENCE);
            IWindowSession windowSession = WindowManagerGlobal.getWindowSession();
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "SnackbarWindow", "notifySizeOrLocationChanged:" + cOUISnackBar.getLeft() + ", " + cOUISnackBar.getTop() + ", " + cOUISnackBar.getRight() + ", " + cOUISnackBar.getBottom(), null, 8);
            StringBuilder sb = new StringBuilder("setTouchExclusionRegion:");
            sb.append(region2);
            Trace.traceBegin(8L, sb.toString());
            windowSession.updateTapExcludeRegion(IWindow.Stub.asInterface(cOUISnackBar.getWindowToken()), region);
            Trace.traceEnd(8L);
        } catch (RemoteException e) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "setTouchExclusionRegion:", e.getMessage(), LogUtil.TAG_NOTIFICATION, "SnackbarWindow");
        }
    }
}
