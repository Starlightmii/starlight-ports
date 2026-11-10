package com.oplus.systemui.shade;

import android.view.View;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface NotificationPanelSeparateAnimationManager {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final NotificationPanelSeparateAnimationManager create(boolean z) {
            NotificationPanelSeparateAnimationManagerImpl notificationPanelSeparateAnimationManagerImpl = new NotificationPanelSeparateAnimationManagerImpl();
            notificationPanelSeparateAnimationManagerImpl.forbidDelayWhenInterrupt = z;
            VarHandle.storeStoreFence();
            return notificationPanelSeparateAnimationManagerImpl;
        }
    }

    void cancelViewAnimation(View view, String str);

    void clearAllShowAlphaAnimateToPosition(View view, float f);

    void clearAllhide(View view, boolean z, Function1 function1, Function1 function2, Function1 function3, Function3 function4);

    void clearAllshow(View view, boolean z, Function1 function1, Function1 function2, Function1 function3, Function3 function4);

    void hide(View view, boolean z, boolean z2, long j, int i, float f, Function1 function1, Function1 function2, Function1 function3, float f2, Function1 function4, Function2 function5);

    boolean isHideAnimationsRunning(View view);

    boolean isShowAnimationsRunning(View view);

    boolean isSpacingAnimationRunning(View view);

    void show(View view, boolean z, boolean z2, long j, int i, float f, Function1 function1, Function1 function2, Function1 function3, float f2, Function1 function4);
}
