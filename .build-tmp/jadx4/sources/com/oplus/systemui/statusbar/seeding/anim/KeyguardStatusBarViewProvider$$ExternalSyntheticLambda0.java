package com.oplus.systemui.statusbar.seeding.anim;

import android.view.View;
import android.view.ViewGroup;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class KeyguardStatusBarViewProvider$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ KeyguardStatusBarViewProvider f$0;

    public final Object invoke() {
        int i = this.$r8$classId;
        KeyguardStatusBarViewProvider keyguardStatusBarViewProvider = this.f$0;
        switch (i) {
            case 0:
                View viewFindViewById = keyguardStatusBarViewProvider.getStatusBarContent().findViewById(2131363606);
                viewFindViewById.getClass();
                return (ViewGroup) viewFindViewById;
            default:
                View viewFindViewById2 = keyguardStatusBarViewProvider.getStatusBarContent().findViewById(2131365377);
                viewFindViewById2.getClass();
                return (ViewGroup) viewFindViewById2;
        }
    }
}
