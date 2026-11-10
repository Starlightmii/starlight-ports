package com.oplus.systemui.statusbar.notification.stack;

import android.content.Context;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.notification.stack.OplusAmbientStateEx;
import com.oplus.systemui.notification.aod.OplusPanoramicAodCoreStartable;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import dagger.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusAmbientStateExImpl extends OplusAmbientStateEx {
    private final Lazy aodRepository;
    private final Context context;
    private final kotlin.Lazy lockscreenShadeTransitionControllerEx$delegate = LazyKt__LazyJVMKt.lazy(new OplusAmbientStateExImpl$$ExternalSyntheticLambda0());
    public float mHeaderTrackingHeight;
    public boolean mIsShadeOpening;
    public boolean mIsSpringBackAnimation;
    public int mMaxPullHeight;

    public OplusAmbientStateExImpl(Context context, Lazy lazy) {
        this.context = context;
        this.aodRepository = lazy;
    }

    private final OplusLockscreenShadeTransitionControllerEx getLockscreenShadeTransitionControllerEx() {
        return (OplusLockscreenShadeTransitionControllerEx) this.lockscreenShadeTransitionControllerEx$delegate.getValue();
    }

    public final Context getContext() {
        return this.context;
    }

    public float getHeadTrackingHeight() {
        return this.mHeaderTrackingHeight;
    }

    public int getMaxHeadsUpPullHeight() {
        return this.mMaxPullHeight;
    }

    public final boolean isNotAodUiState() {
        return (((OplusPanoramicAodCoreStartable) ((OplusPanoramicAodRepository) this.aodRepository.get())).isPanoramicAod && ((OplusPanoramicAodRepository) this.aodRepository.get()).getShouldUpdateUi()) ? false : true;
    }

    public final boolean isOnLockscreenNtfCenter() {
        return getLockscreenShadeTransitionControllerEx().isOnLockscreenNtfCenter();
    }

    public final void reload() {
        this.mMaxPullHeight = this.context.getResources().getDimensionPixelSize(2131170671);
    }

    public void setHeadTrackingHeight(float f) {
        this.mHeaderTrackingHeight = f;
    }

    public void setIsShadeOpening(boolean z) {
        this.mIsShadeOpening = z;
    }

    public void setSpringBackAnimation(boolean z) {
        this.mIsSpringBackAnimation = z;
    }
}
