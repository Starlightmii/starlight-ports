package com.oplus.systemui.viewdialog.ui.binder;

import android.view.SurfaceControlViewHost;
import android.view.View;
import com.oplus.systemui.viewdialog.ui.viewmodel.ViewDialogContentViewModel;
import com.oplus.sysuiinlineview.host.view.SysUiInlineSurfaceView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ViewDialogContentBinder$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ ViewDialogContentBinder$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        SurfaceControlViewHost.SurfacePackage surfacePackage;
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                SysUiInlineSurfaceView sysUiInlineSurfaceView = (SysUiInlineSurfaceView) ((View) obj).findViewById(2131365536);
                if (sysUiInlineSurfaceView == null || (surfacePackage = sysUiInlineSurfaceView.getSurfacePackage()) == null) {
                    return null;
                }
                return surfacePackage.getSurfaceControl();
            default:
                ViewDialogContentBinder.INSTANCE.getClass();
                ((ViewDialogContentViewModel) obj).onDestroy();
                return Unit.INSTANCE;
        }
    }
}
