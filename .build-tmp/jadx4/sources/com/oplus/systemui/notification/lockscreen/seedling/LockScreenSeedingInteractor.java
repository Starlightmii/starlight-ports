package com.oplus.systemui.notification.lockscreen.seedling;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.communal.widgets.CommunalWidgetHost$;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.anim.AnimListener;
import com.android.systemui.plugins.anim.SpringAnimWrapper;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.heytap.accessory.bean.FlowControlConfig$;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl;
import com.oplus.systemui.statusbar.notification.customcard.guidetips.CustomCardGuideTipsViewController;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginManager;
import com.oplus.systemui.volume.OplusVolumeTipsControler;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Function;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenSeedingInteractor {
    public static final Companion Companion = new Companion();
    private static final String KEY_IMMERSIVE_CARD_TYPE = "immersiveCardType";
    private static final String TAG = "LockScreenSeedingInteractor";
    private final CustomCardGuideTipsViewController guideTipsController;
    private final CoroutineScope mainScope;
    private final Lazy notificationCapsuleContainerController;
    private final SeedlingPluginManager seedlingPluginManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static void onMethodState(CustomRowEntry customRowEntry, boolean z, int i, int i2, int i3, int i4, boolean z2) {
            String str;
            SeedlingPlugin.ChangeListener changeListener;
            if (i == 1) {
                str = "onPreChange";
            } else if (i != 2) {
                str = i != 3 ? "unknown" : "onChangeEnd";
            } else {
                str = "onChangeStart";
            }
            ILockScreenExtStateController.Companion.getClass();
            int i5 = i2 == 3 ? 0 : i2;
            int i6 = i3 != 3 ? i3 : 0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("onMethodState(isBG=", i2, "):", "(", z);
                RectList$.ExternalSyntheticOutline0.m(i5, i3, ")->", "(", sbM);
                ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(i6, ") animate = ", " ,type = ", sbM, z2);
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(str, "LockScreenSeedingInteractor-->", "SystemUi--Notification", sbM);
            }
            Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("method", "immersiveStateChange");
            bundleM.putInt("cardSize", z ? 3 : 1);
            bundleM.putInt("fromState", i5);
            bundleM.putInt("toState", i6);
            bundleM.putInt("reason", i4);
            bundleM.putBoolean("animate", z2);
            if (i == 1) {
                changeListener = customRowEntry instanceof SeedlingPlugin.ChangeListener ? (SeedlingPlugin.ChangeListener) customRowEntry : null;
                if (changeListener != null) {
                    changeListener.onPreChange(bundleM);
                    return;
                }
                return;
            }
            if (i == 2) {
                changeListener = customRowEntry instanceof SeedlingPlugin.ChangeListener ? (SeedlingPlugin.ChangeListener) customRowEntry : null;
                if (changeListener != null) {
                    changeListener.onChangeStart(bundleM);
                    return;
                }
                return;
            }
            if (i != 3) {
                return;
            }
            changeListener = customRowEntry instanceof SeedlingPlugin.ChangeListener ? (SeedlingPlugin.ChangeListener) customRowEntry : null;
            if (changeListener != null) {
                changeListener.onChangeEnd(bundleM);
            }
        }

        public final int getBgType(CustomRowEntry customRowEntry) {
            return customRowEntry.getExtras().getInt("immersiveCardType", -1);
        }

        public final SpringAnimWrapper getImmersiverBgAnimator(CustomRowEntry customRowEntry, int i, int i2, int i3, Rect rect, Rect rect2) {
            Bundle bundleM = CommunalWidgetHost$.ExternalSyntheticOutline0.m(1, "animType");
            ILockScreenExtStateController.Companion.getClass();
            bundleM.putInt("fromState", i2 == 3 ? 0 : i2);
            bundleM.putInt("toState", i3 != 3 ? i3 : 0);
            bundleM.putInt("cardSize", i);
            if (rect != null) {
                bundleM.putParcelable("cardBounds", rect);
            }
            if (rect2 != null) {
                bundleM.putParcelable("capsuleBounds", rect2);
            }
            SpringAnimWrapper springAnimator = customRowEntry.getSpringAnimator(bundleM);
            if (springAnimator == null) {
                LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, LockScreenSeedingInteractor.TAG, "getImmersiverAnimator is empty");
                return new FakeAnimator();
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("getImmersiverAnimator(", i, i2, ") ", "->");
                sbM.append(i3);
                sbM.append(",size = ");
                sbM.append(springAnimator);
                sbM.append(",capsuleBounds = ");
                sbM.append(rect2);
                sbM.append(",stackBounds = ");
                sbM.append(rect);
                Log.d("SystemUi--Notification", "LockScreenSeedingInteractor-->".concat(sbM.toString()));
            }
            return springAnimator;
        }

        public final void hideSurface(CustomRowEntry customRowEntry) {
            Bundle bundle = new Bundle();
            bundle.putString("method", "hideSurface");
            customRowEntry.updateBundle(bundle);
        }

        public final void onChangeEnd(CustomRowEntry customRowEntry, boolean z, int i, int i2, int i3, boolean z2) {
            onMethodState(customRowEntry, z, 3, i, i2, i3, z2);
        }

        public final void onChangeStart(CustomRowEntry customRowEntry, boolean z, int i, int i2, int i3, boolean z2) {
            onMethodState(customRowEntry, z, 2, i, i2, i3, z2);
        }

        public final void onPreChange(CustomRowEntry customRowEntry, boolean z, int i, int i2, int i3, boolean z2) {
            onMethodState(customRowEntry, z, 1, i, i2, i3, z2);
        }

        public final void updateContentRectForBg(CustomRowEntry customRowEntry, Rect rect) {
            Bundle bundle = new Bundle();
            bundle.putString("method", "updateContentRect");
            bundle.putParcelable("rect", rect);
            customRowEntry.updateBundle(bundle);
        }

        public final void updateSurfaceViewAlpha(CustomRowEntry customRowEntry, float f) {
            Bundle bundle = new Bundle();
            bundle.putString("method", "updateSurfaceViewAlpha");
            bundle.putFloat(OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, f);
            customRowEntry.updateBundle(bundle);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class FakeAnimator extends AnimatorListenerAdapter implements SpringAnimWrapper {
        public static final Companion Companion = new Companion();
        private final HashSet<AnimListener> animListenerSet;
        public final ValueAnimator animator;
        public boolean isCancelled;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
        }

        public FakeAnimator() {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            valueAnimatorOfFloat.setDuration(50L);
            valueAnimatorOfFloat.addListener(this);
            this.animator = valueAnimatorOfFloat;
            this.animListenerSet = new HashSet<>();
        }

        public SpringAnimWrapper addListener(AnimListener animListener) {
            this.animListenerSet.add(animListener);
            return this;
        }

        public final void cancel() {
            this.animator.cancel();
        }

        public final boolean isRunning() {
            return this.animator.isRunning();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.isCancelled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            Iterator<T> it = this.animListenerSet.iterator();
            while (it.hasNext()) {
                ((AnimListener) it.next()).onAnimationEnd(this, this.isCancelled, 1.0f, 0.0f);
            }
        }

        public void removeListener(AnimListener animListener) {
            this.animListenerSet.remove(animListener);
        }

        public final void start() {
            this.animator.start();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.seedling.LockScreenSeedingInteractor$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.seedling.LockScreenSeedingInteractor$start$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final /* synthetic */ class C00251 implements FlowCollector, FunctionAdapter {
            public /* synthetic */ LockScreenSeedingInteractor $tmp0;

            public final Object emit(Object obj, Continuation continuation) {
                this.$tmp0.onColorChanged(((Boolean) obj).booleanValue());
                Unit unit = Unit.INSTANCE;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                return unit;
            }

            public final boolean equals(Object obj) {
                if ((obj instanceof FlowCollector) && (obj instanceof FunctionAdapter)) {
                    return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
                }
                return false;
            }

            public final Function getFunctionDelegate() {
                return new AdaptedFunctionReference(2, this.$tmp0, LockScreenSeedingInteractor.class, "onColorChanged", "onColorChanged(Z)V", 4);
            }

            public final int hashCode() {
                return getFunctionDelegate().hashCode();
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return LockScreenSeedingInteractor.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlowIsKeyguardLightColor = ((NotificationCapsuleContainerControllerImpl) LockScreenSeedingInteractor.this.notificationCapsuleContainerController.get()).isKeyguardLightColor();
                LockScreenSeedingInteractor lockScreenSeedingInteractor = LockScreenSeedingInteractor.this;
                C00251 c00251 = new C00251();
                c00251.$tmp0 = lockScreenSeedingInteractor;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (stateFlowIsKeyguardLightColor.collect(c00251, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            Regex$.ExternalSyntheticBUOutline0.m$1();
            return null;
        }
    }

    public LockScreenSeedingInteractor(CoroutineScope coroutineScope, SeedlingPluginManager seedlingPluginManager, Lazy lazy, CustomCardGuideTipsViewController customCardGuideTipsViewController) {
        this.mainScope = coroutineScope;
        this.seedlingPluginManager = seedlingPluginManager;
        this.notificationCapsuleContainerController = lazy;
        this.guideTipsController = customCardGuideTipsViewController;
    }

    public final void notifyColorChanged() {
        onColorChanged(((Boolean) ((NotificationCapsuleContainerControllerImpl) this.notificationCapsuleContainerController.get()).isKeyguardLightColor().getValue()).booleanValue());
    }

    public final void notifyExtAnimationStateChanged(boolean z) {
        SeedlingPlugin seedlingPlugin = this.seedlingPluginManager.getSeedlingPlugin();
        if (seedlingPlugin != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("notifyExtAnimationStateChanged:", "LockScreenSeedingInteractor-->", "SystemUi--Notification", z);
            }
            Bundle bundle = new Bundle();
            bundle.putString("method", "onLockNtfAnimationStateChanged");
            bundle.putBoolean("isAnimationRunning", z);
            seedlingPlugin.call(bundle);
        }
    }

    public final void notifyExtStateChanged(int i, String str, int i2, String str2) {
        SeedlingPlugin seedlingPlugin = this.seedlingPluginManager.getSeedlingPlugin();
        if (seedlingPlugin != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "LockScreenSeedingInteractor-->seedlingPlugin call onLockNtfModeChange");
            }
            Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("method", "onLockNtfModeChanged");
            ILockScreenExtStateController.Companion.getClass();
            bundleM.putInt("lockNtfMode", i == 3 ? 0 : i);
            bundleM.putInt("reason", i2);
            bundleM.putString("from", str);
            bundleM.putStringArray("curItem", new String[]{str2});
            seedlingPlugin.call(bundleM);
        }
        this.guideTipsController.setImmersiveGuideTimes(i, str, i2, str2);
    }

    public final void notifyUnlockAnimationStarted(String str) {
        SeedlingPlugin seedlingPlugin = this.seedlingPluginManager.getSeedlingPlugin();
        if (seedlingPlugin != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "LockScreenSeedingInteractor-->seedlingPlugin call onUnlockAnimationStarted");
            }
            Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("method", "onUnlockAnimationStarted");
            bundleM.putStringArray("curItem", new String[]{str});
            seedlingPlugin.call(bundleM);
        }
    }

    public final void onColorChanged(boolean z) {
        SeedlingPlugin seedlingPlugin = this.seedlingPluginManager.getSeedlingPlugin();
        if (seedlingPlugin != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "LockScreenSeedingInteractor-->seedlingPlugin call onLockCapsuleColorChanged");
            }
            Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("method", "onLockCapsuleColorChanged");
            bundleM.putBoolean("lockCapsuleMode", !z);
            seedlingPlugin.call(bundleM);
        }
    }

    public final void start() {
        BuildersKt.launch$default(this.mainScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
    }
}
