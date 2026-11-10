package com.oplus.systemui.qs.widget;

import com.android.systemui.DependencyEx;
import com.android.systemui.qs.OplusQuickSettingsEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusToggleSliderView$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusToggleSliderView f$0;

    public /* synthetic */ OplusToggleSliderView$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        OplusToggleSliderView oplusToggleSliderView = this.f$0;
        switch (i) {
            case 0:
                oplusToggleSliderView.setupSliderProgressDrawable(((OplusQuickSettingsEx) DependencyEx.sDependency.getDependency(OplusQuickSettingsEx.class)).getPersonalityManagerEx().getLastShapeType());
                break;
            default:
                OplusToggleSliderView.$r8$lambda$igX4Djz0iLI9Pk8Tkm3WJ9GYG9M(oplusToggleSliderView);
                break;
        }
    }
}
