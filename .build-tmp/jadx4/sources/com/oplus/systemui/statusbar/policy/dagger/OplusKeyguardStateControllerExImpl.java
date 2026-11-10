package com.oplus.systemui.statusbar.policy.dagger;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.OplusKeyguardStateControllerEx;
import com.oplus.systemui.keyguard.OplusKeyguardViewMediatorExImpl;
import com.oplusos.systemui.common.feature.FeatureOption;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKeyguardStateControllerExImpl extends OplusKeyguardStateControllerEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusKeyguardStateContr";
    private final CopyOnWriteArrayList<OplusKeyguardStateControllerEx.Callback> callbacks;
    private final Context context;
    public boolean isPendingUnOccludeInSuperPowerSwitch;
    public boolean isUnlockAnimationRunning;
    public int keyguardFpSuppressionFlags;
    private final Lazy keyguardStateControllerImpl;
    public boolean mBlockAlphaFlag;
    public boolean mUnOccludeAnimation;
    private volatile Runnable markUnoccludeAnimRunningStateRunnable;
    private final Lazy oplusKeyguardViewMediatorExImpl;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusKeyguardStateControllerExImpl(Lazy lazy, Context context, Lazy lazy2) {
        super(context);
        this.oplusKeyguardViewMediatorExImpl = lazy;
        this.context = context;
        this.keyguardStateControllerImpl = lazy2;
        this.callbacks = new CopyOnWriteArrayList<>();
    }

    public void addCallback(OplusKeyguardStateControllerEx.Callback callback) {
        if (this.callbacks.contains(callback)) {
            return;
        }
        this.callbacks.add(callback);
    }

    public final void clearKeyguardFpSuppressionFlags(int i) {
        boolean zIsKeyguardFpSuppressed = isKeyguardFpSuppressed();
        this.keyguardFpSuppressionFlags = (~i) & this.keyguardFpSuppressionFlags;
        if (!zIsKeyguardFpSuppressed || isKeyguardFpSuppressed()) {
            return;
        }
        Iterator<T> it = this.callbacks.iterator();
        while (it.hasNext()) {
            ((OplusKeyguardStateControllerEx.Callback) it.next()).onKeyguardFpSuppressStateChanged(false);
        }
    }

    public final void clickSuperPowerEnableFromModeTileWhileKgdShowing() {
        if (!((KeyguardStateControllerImpl) this.keyguardStateControllerImpl.get()).mOccluded || !((KeyguardStateControllerImpl) this.keyguardStateControllerImpl.get()).mShowing) {
            this.isPendingUnOccludeInSuperPowerSwitch = false;
            return;
        }
        this.isPendingUnOccludeInSuperPowerSwitch = true;
        Handler mainThreadHandler = this.context.getMainThreadHandler();
        clickSuperPowerEnableFromModeTileWhileKgdShowing.1 r1 = new clickSuperPowerEnableFromModeTileWhileKgdShowing.1();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        mainThreadHandler.postDelayed(r1, 1000L);
    }

    public void dump(PrintWriter printWriter) {
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("  keyguardFpSuppressionFlags: ", this.keyguardFpSuppressionFlags, printWriter);
    }

    public final boolean isBlockAlphaFlag() {
        return this.mBlockAlphaFlag;
    }

    public final boolean isDeviceInteractive() {
        KeyguardViewMediator keyguardViewMediator = ((OplusKeyguardViewMediatorExImpl) this.oplusKeyguardViewMediatorExImpl.get()).getKeyguardViewMediator();
        if (keyguardViewMediator != null) {
            return keyguardViewMediator.mDeviceInteractive;
        }
        return true;
    }

    public final boolean isKeyguardFpSuppressed() {
        return this.keyguardFpSuppressionFlags != 0;
    }

    public final boolean isKeyguardScreenRotationAllowed() {
        if (this.context.getResources().getBoolean(2131034320)) {
            return true;
        }
        FeatureOption.INSTANCE.getClass();
        return FeatureOption.isTablet();
    }

    public final boolean isUnOccludeAnimation() {
        return this.mUnOccludeAnimation;
    }

    public final boolean isUnlockAnimationRunning() {
        return this.isUnlockAnimationRunning;
    }

    public final void notifyKeyguardUnOccludeAnimation(boolean z) {
        this.mUnOccludeAnimation = z;
    }

    public void removeCallback(OplusKeyguardStateControllerEx.Callback callback) {
        if (this.callbacks.contains(callback)) {
            this.callbacks.remove(callback);
        }
    }

    public void setBlockAlphaFlag(boolean z) {
        this.mBlockAlphaFlag = z;
    }

    public void setKeyguardFpSuppressionFlags(int i) {
        super.setKeyguardFpSuppressionFlags(i);
        boolean zIsKeyguardFpSuppressed = isKeyguardFpSuppressed();
        this.keyguardFpSuppressionFlags = i | this.keyguardFpSuppressionFlags;
        if (zIsKeyguardFpSuppressed || !isKeyguardFpSuppressed()) {
            return;
        }
        Iterator<T> it = this.callbacks.iterator();
        while (it.hasNext()) {
            ((OplusKeyguardStateControllerEx.Callback) it.next()).onKeyguardFpSuppressStateChanged(true);
        }
    }

    public final void setPendingUnOccludeInSuperPowerSwitch(boolean z) {
        this.isPendingUnOccludeInSuperPowerSwitch = z;
    }

    public void setUnlockAnimationRunning(boolean z) {
        this.isUnlockAnimationRunning = z;
    }
}
