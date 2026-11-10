package com.oplus.systemui.shade;

import android.view.View;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda1 implements Function1 {
    public final Object invoke(Object obj) {
        View view = (View) obj;
        NotificationPanelChildAppearAnimationManager.Companion companion = NotificationPanelChildAppearAnimationManager.Companion;
        return new Pair(Float.valueOf(view.getTransitionAlpha()), Float.valueOf(view.getScaleX()));
    }
}
