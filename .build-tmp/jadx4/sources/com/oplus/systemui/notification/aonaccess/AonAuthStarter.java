package com.oplus.systemui.notification.aonaccess;

import android.os.Handler;
import androidx.compose.material3.EnterAlwaysScrollBehavior$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplus.systemui.notification.aon.AonAuthManager;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AonAuthStarter {
    public static final Companion Companion = new Companion();
    private static final String TAG = "AonAuthStarter";
    private final Lazy aonAuthManager;
    private final Runnable closeRunnable;
    private final Lazy fullScreenBannerHelper;
    private Function0 headsUpCoordinatorIsEmpty = new EnterAlwaysScrollBehavior$.ExternalSyntheticLambda0();
    private final Lazy headsUpManager;
    private final Handler mainHandler;
    private final Lazy notificationListenerDelayImpl;
    private final List<Runnable> onGetAonAuthCallbacks;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.aonaccess.AonAuthStarter$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements AonAuthManager.IAonAuthStateListener {
        public /* synthetic */ AonAuthStarter this$0;

        @Override // com.oplus.systemui.notification.aon.AonAuthManager.IAonAuthStateListener
        public final void onAuthStateChange(Boolean bool) {
            AonAuthStarter aonAuthStarter = this.this$0;
            for (Runnable runnable : CollectionsKt___CollectionsKt.toList(aonAuthStarter.onGetAonAuthCallbacks)) {
                aonAuthStarter.mainHandler.removeCallbacks(runnable);
                runnable.run();
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public AonAuthStarter(Lazy lazy, Handler handler, Lazy lazy2, Lazy lazy3, Lazy lazy4) {
        this.aonAuthManager = lazy;
        this.mainHandler = handler;
        this.headsUpManager = lazy2;
        this.fullScreenBannerHelper = lazy3;
        this.notificationListenerDelayImpl = lazy4;
        AonAuthStarter$closeRunnable$1 aonAuthStarter$closeRunnable$1 = new AonAuthStarter$closeRunnable$1();
        aonAuthStarter$closeRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.closeRunnable = aonAuthStarter$closeRunnable$1;
        this.onGetAonAuthCallbacks = new ArrayList();
        AonAuthManager aonAuthManager = (AonAuthManager) lazy.get();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        aonAuthManager.addAonAuthStateListener(anonymousClass1);
    }

    public final boolean cancelOnGetAonAuthResultTask(Runnable runnable) {
        this.mainHandler.removeCallbacks(runnable);
        return this.onGetAonAuthCallbacks.remove(runnable);
    }

    public final Function0 getHeadsUpCoordinatorIsEmpty() {
        return this.headsUpCoordinatorIsEmpty;
    }

    public final Runnable openCamera(String str, Function1 function1) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("[openCamera] reason=", str, "AonAuthStarter-->", "SystemUi--Notification");
        }
        this.mainHandler.removeCallbacks(this.closeRunnable);
        ((AonAuthManager) this.aonAuthManager.get()).openCamera();
        if (function1 == null) {
            return null;
        }
        Boolean aonAuthResult = ((AonAuthManager) this.aonAuthManager.get()).getAonAuthResult();
        if (!((AonAuthManager) this.aonAuthManager.get()).isAonEnable() || aonAuthResult != null) {
            function1.invoke(Boolean.valueOf(aonAuthResult != null ? aonAuthResult.booleanValue() : false));
            return null;
        }
        AonAuthStarter$openCamera$invokeResult$1 aonAuthStarter$openCamera$invokeResult$1 = new AonAuthStarter$openCamera$invokeResult$1();
        aonAuthStarter$openCamera$invokeResult$1.$onGetAonAuthResult = function1;
        aonAuthStarter$openCamera$invokeResult$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.onGetAonAuthCallbacks.add(aonAuthStarter$openCamera$invokeResult$1);
        this.mainHandler.postDelayed(aonAuthStarter$openCamera$invokeResult$1, 500L);
        return aonAuthStarter$openCamera$invokeResult$1;
    }

    public final void setHeadsUpCoordinatorIsEmpty(Function0 function0) {
        this.headsUpCoordinatorIsEmpty = function0;
    }

    public final void tryCloseAonCamera(String str) {
        if (((AonAuthManager) this.aonAuthManager.get()).isAonEnable()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("[tryCloseAonCamera] reason=", str, "AonAuthStarter-->", "SystemUi--Notification");
            }
            this.mainHandler.removeCallbacks(this.closeRunnable);
            this.mainHandler.postDelayed(this.closeRunnable, 50L);
        }
    }

    public final Runnable openCamera(String str) {
        return openCamera(str, null);
    }
}
