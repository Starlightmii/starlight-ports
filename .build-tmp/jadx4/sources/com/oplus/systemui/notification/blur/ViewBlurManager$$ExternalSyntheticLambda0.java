package com.oplus.systemui.notification.blur;

import android.view.View;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerWindow;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ViewBlurManager$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ ViewBlurManager f$0;

    public /* synthetic */ ViewBlurManager$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj) {
        int i = this.$r8$classId;
        ViewBlurManager viewBlurManager = this.f$0;
        switch (i) {
            case 0:
                ((Integer) obj).getClass();
                return ViewBlurManager.$r8$lambda$mzaqBMzk4aZBtxqO2eoH6J_ViSY(viewBlurManager);
            default:
                return Boolean.valueOf(((HeadsUpContainerWindow) viewBlurManager.headsUpWindow.get()).isHeadsUpView((View) obj));
        }
    }
}
