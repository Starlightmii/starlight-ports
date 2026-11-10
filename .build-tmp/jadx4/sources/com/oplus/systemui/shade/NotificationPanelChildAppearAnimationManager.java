package com.oplus.systemui.shade;

import android.view.View;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface NotificationPanelChildAppearAnimationManager {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final NotificationPanelChildAppearAnimationManager create(boolean z) {
            return new NotificationPanelChildAppearAnimationManagerImpl(z);
        }
    }

    static /* synthetic */ void hide$default(NotificationPanelChildAppearAnimationManager notificationPanelChildAppearAnimationManager, View view, boolean z, float f, Function2 function2, int i) {
        int i2 = (i & 8) != 0 ? 0 : -5;
        if ((i & 16) != 0) {
            f = 0.0f;
        }
        NotificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda0 notificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda0 = new NotificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda0();
        notificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda0.f$0 = view;
        VarHandle.storeStoreFence();
        ((NotificationPanelChildAppearAnimationManagerImpl) notificationPanelChildAppearAnimationManager).hide(view, z, 0L, i2, f, notificationPanelChildAppearAnimationManager$$ExternalSyntheticLambda0, function2);
    }

    void cancelViewAnimation(View view, boolean z, String str);

    void hide(View view, boolean z, long j, int i, float f, Function1 function1, Function2 function2);

    void show(View view, boolean z, long j, int i, boolean z2, float f, Function1 function1, Function2 function2);
}
