package com.oplus.systemui.seamless.widget;

import android.view.View;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class StatusBarFakeFrame$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;

    public final Object invoke(Object obj) {
        int top;
        View view = (View) obj;
        switch (this.$r8$classId) {
            case 0:
                StatusBarFakeFrame.Companion companion = StatusBarFakeFrame.Companion;
                top = view.getTop();
                break;
            case 1:
                StatusBarFakeFrame.Companion companion2 = StatusBarFakeFrame.Companion;
                top = (int) view.getTranslationX();
                break;
            default:
                StatusBarFakeFrame.Companion companion3 = StatusBarFakeFrame.Companion;
                top = view.getLeft();
                break;
        }
        return Integer.valueOf(top);
    }
}
