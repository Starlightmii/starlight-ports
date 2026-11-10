package com.oplus.systemui.statusbar;

import android.animation.ObjectAnimator;
import android.util.Log;
import android.util.Property;
import android.view.View;
import com.android.keyguard.logging.KeyguardLogger;
import com.android.systemui.Dependency;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.policy.Clock;
import com.coui.appcompat.animation.COUIEaseInterpolator;
import com.oplus.systemui.statusbar.widget.OplusKeyguardStatusBarClock;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKeyguardStatusBarClockController {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OplusKeyguardStatClockController";
    private ObjectAnimator clockAnimator;
    private OplusKeyguardStatusBarClock keyguardStatusBarClock;
    private KeyguardLogger logger;
    public int statusBarState = -1;
    private final OplusKeyguardStatusBarClockController$statusBarStateListener$1 statusBarStateListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusKeyguardStatusBarClockController(KeyguardLogger keyguardLogger) {
        this.logger = keyguardLogger;
        OplusKeyguardStatusBarClockController$statusBarStateListener$1 oplusKeyguardStatusBarClockController$statusBarStateListener$1 = new OplusKeyguardStatusBarClockController$statusBarStateListener$1();
        oplusKeyguardStatusBarClockController$statusBarStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.statusBarStateListener = oplusKeyguardStatusBarClockController$statusBarStateListener$1;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x005f  */
    /* JADX WARN: Code duplicated, block: B:33:0x0063  */
    /* JADX WARN: Code duplicated, block: B:36:0x006a  */
    public static final void access$animateAppear(OplusKeyguardStatusBarClockController oplusKeyguardStatusBarClockController) {
        Clock clock;
        Clock clock2;
        ObjectAnimator objectAnimator = oplusKeyguardStatusBarClockController.clockAnimator;
        if (objectAnimator != null) {
            objectAnimator.removeAllListeners();
        }
        ObjectAnimator objectAnimator2 = oplusKeyguardStatusBarClockController.clockAnimator;
        if (objectAnimator2 != null) {
            objectAnimator2.cancel();
        }
        ObjectAnimator objectAnimatorOfFloat = null;
        oplusKeyguardStatusBarClockController.clockAnimator = null;
        Clock clock3 = oplusKeyguardStatusBarClockController.keyguardStatusBarClock;
        if (clock3 != null && clock3.getVisibility() == 0) {
            Clock clock4 = oplusKeyguardStatusBarClockController.keyguardStatusBarClock;
            if (Intrinsics.areEqual(clock4 != null ? Float.valueOf(clock4.getAlpha()) : null, 1.0f)) {
                LogUtil.i$default(LogUtil.TAG_KEYGUARD, TAG, "no animateAppear required", null, 8);
                return;
            }
        }
        Clock clock5 = oplusKeyguardStatusBarClockController.keyguardStatusBarClock;
        if (clock5 == null || clock5.getVisibility() != 4) {
            Clock clock6 = oplusKeyguardStatusBarClockController.keyguardStatusBarClock;
            if (Intrinsics.areEqual(clock6 != null ? Float.valueOf(clock6.getAlpha()) : null, 0.0f)) {
                clock = oplusKeyguardStatusBarClockController.keyguardStatusBarClock;
                if (clock != null) {
                    clock.setVisibility(0);
                }
                clock2 = oplusKeyguardStatusBarClockController.keyguardStatusBarClock;
                if (clock2 != null) {
                    clock2.setAlpha(0.0f);
                }
            }
        } else {
            clock = oplusKeyguardStatusBarClockController.keyguardStatusBarClock;
            if (clock != null) {
                clock.setVisibility(0);
            }
            clock2 = oplusKeyguardStatusBarClockController.keyguardStatusBarClock;
            if (clock2 != null) {
                clock2.setAlpha(0.0f);
            }
        }
        Clock clock7 = oplusKeyguardStatusBarClockController.keyguardStatusBarClock;
        if (clock7 != null) {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(clock7, (Property<Clock, Float>) View.ALPHA, clock7.getAlpha(), 1.0f);
            objectAnimatorOfFloat.setDuration(200L);
            objectAnimatorOfFloat.setInterpolator(new COUIEaseInterpolator());
            objectAnimatorOfFloat.setStartDelay(100L);
            objectAnimatorOfFloat.addListener(new OplusKeyguardStatusBarClockController$animateAppear$1$1$1(oplusKeyguardStatusBarClockController, 0));
        }
        oplusKeyguardStatusBarClockController.clockAnimator = objectAnimatorOfFloat;
        if (objectAnimatorOfFloat != null) {
            objectAnimatorOfFloat.start();
        }
    }

    public static final void access$animateDisappear(OplusKeyguardStatusBarClockController oplusKeyguardStatusBarClockController) {
        ObjectAnimator objectAnimator = oplusKeyguardStatusBarClockController.clockAnimator;
        if (objectAnimator != null) {
            objectAnimator.removeAllListeners();
        }
        ObjectAnimator objectAnimator2 = oplusKeyguardStatusBarClockController.clockAnimator;
        if (objectAnimator2 != null) {
            objectAnimator2.cancel();
        }
        ObjectAnimator objectAnimatorOfFloat = null;
        oplusKeyguardStatusBarClockController.clockAnimator = null;
        Clock clock = oplusKeyguardStatusBarClockController.keyguardStatusBarClock;
        if (clock != null && clock.getVisibility() == 0) {
            Clock clock2 = oplusKeyguardStatusBarClockController.keyguardStatusBarClock;
            if (!Intrinsics.areEqual(clock2 != null ? Float.valueOf(clock2.getAlpha()) : null, 0.0f)) {
                Clock clock3 = oplusKeyguardStatusBarClockController.keyguardStatusBarClock;
                if (clock3 != null) {
                    objectAnimatorOfFloat = ObjectAnimator.ofFloat(clock3, (Property<Clock, Float>) View.ALPHA, clock3.getAlpha(), 0.0f);
                    objectAnimatorOfFloat.setDuration(200L);
                    objectAnimatorOfFloat.setInterpolator(new COUIEaseInterpolator());
                    objectAnimatorOfFloat.addListener(new OplusKeyguardStatusBarClockController$animateAppear$1$1$1(oplusKeyguardStatusBarClockController, 1));
                }
                oplusKeyguardStatusBarClockController.clockAnimator = objectAnimatorOfFloat;
                if (objectAnimatorOfFloat != null) {
                    objectAnimatorOfFloat.start();
                    return;
                }
                return;
            }
        }
        LogUtil.i$default(LogUtil.TAG_KEYGUARD, TAG, "no animateDisappear required", null, 8);
        oplusKeyguardStatusBarClockController.hideClockInternal();
    }

    public final void bindView(OplusKeyguardStatusBarClock oplusKeyguardStatusBarClock) {
        this.keyguardStatusBarClock = oplusKeyguardStatusBarClock;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "OplusKeyguardStatClockController-->".concat("bindView " + oplusKeyguardStatusBarClock));
        }
    }

    public final void hideClockInternal() {
        this.logger.log(TAG, LogLevel.DEBUG, "hideClockInternal", (Throwable) null);
        Clock clock = this.keyguardStatusBarClock;
        if (clock != null) {
            clock.setVisibility(4);
        }
        Clock clock2 = this.keyguardStatusBarClock;
        if (clock2 != null) {
            clock2.setAlpha(0.0f);
        }
    }

    public final void onViewAttached() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "OplusKeyguardStatClockController-->onViewAttached");
        }
        StatusBarStateController statusBarStateController = (StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class);
        statusBarStateController.addCallback(this.statusBarStateListener);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(statusBarStateController.getState(), "onViewAttached state: ", "OplusKeyguardStatClockController-->", "SystemUi--Keyguard");
        }
        int state = statusBarStateController.getState();
        this.statusBarState = state;
        if (state == 2) {
            showClockInternal();
        } else {
            hideClockInternal();
        }
    }

    public final void onViewDetached() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "OplusKeyguardStatClockController-->onViewDetached");
        }
        ((StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).removeCallback(this.statusBarStateListener);
    }

    public final void showClockInternal() {
        this.logger.log(TAG, LogLevel.DEBUG, "showClockInternal", (Throwable) null);
        Clock clock = this.keyguardStatusBarClock;
        if (clock != null) {
            clock.setVisibility(0);
        }
        Clock clock2 = this.keyguardStatusBarClock;
        if (clock2 != null) {
            clock2.setAlpha(1.0f);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void updateColor(int i) {
        Clock clock = this.keyguardStatusBarClock;
        if (clock != null) {
            clock.setTextColor(i);
        }
    }
}
