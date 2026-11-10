package com.oplus.systemui.viewdialog.ui.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.graphics.Rect;
import android.os.Looper;
import android.util.MathUtils;
import android.view.SurfaceControl;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.car.app.model.TimerSpan$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.core.view.OneShotPreDrawListener;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.android.systemui.animation.GhostedViewTransitionAnimatorController;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.util.animation.ScreenOffAnimationGuardKt;
import com.coui.appcompat.animation.COUIEaseInterpolator;
import com.coui.appcompat.animation.COUIMoveEaseInterpolator;
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.oplus.systemui.qs.utils.QsUtil;
import com.oplusos.systemui.animation.EmptyTransitionAnimatorController;
import com.oplusos.systemui.animation.MiddleLaunchAnimatorController;
import com.oplusos.systemui.animation.TargetViewLaunchAnimatorController;
import com.oplusos.systemui.animation.TransitionFrameCornerSync;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager;
import java.lang.invoke.VarHandle;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class DetailAnimationManager implements ViewDialogAnimationManager {
    private static final String TAG = "DetailAnimationManager";
    private ViewDialogAnimationManager.AnimationCallBack animationCallBack;
    private TransitionAnimator.State curState;
    public boolean isLaunching;
    public boolean skipToEnd;
    private Triple<? extends View, Integer, ? extends Function0> targetData;
    private final View targetView;
    public static final Companion Companion = new Companion();
    private static final Timings enterTimings = new Timings(650, 0, 200, 0, 180);
    private static final Timings exitTimings = new Timings(400, 100, 150, 50, 180);
    private static final Timings middleEnterTimings = new Timings(650, 100, 250, 0, 180);
    private static final Timings middleExitTimings = new Timings(400, 0, 250, 250, 150);
    private static final Interpolators enterInterpolator = new Interpolators(new COUISpringInterpolator(0.45d, 0.0d, 6666.0f), new COUISpringInterpolator(0.45d, 0.0d, 6666.0f), new COUIEaseInterpolator());
    private static final Interpolators exitInterpolator = new Interpolators(new COUIMoveEaseInterpolator(), new COUISpringInterpolator(0.45d, 0.0d, 6666.0f), new COUIEaseInterpolator());
    private static final Interpolators middleEnterInterpolator = new Interpolators(new COUISpringInterpolator(0.45d, 0.0d, 6666.0f), new COUISpringInterpolator(0.8d, 0.0d), new COUIEaseInterpolator());
    private static final Interpolators middleExitInterpolator = new Interpolators(new COUISpringInterpolator(0.45d, 0.0d, 6666.0f), new COUISpringInterpolator(0.8d, 0.0d), new COUIEaseInterpolator());
    public ValueAnimator animation = ValueAnimator.ofFloat(0.0f, 1.0f);
    private TransitionAnimator.State startState = new TransitionAnimator.State(0, 0, 0, 0, 0.0f, 0.0f, 0.0f, 127);
    private TransitionAnimator.State endState = new TransitionAnimator.State(0, 0, 0, 0, 0.0f, 0.0f, 0.0f, 127);
    private Pair<? extends View, Float> fromData = new Pair<>((Object) null, (Object) null);
    private final SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface AnimationCallBack extends ViewDialogAnimationManager.AnimationCallBack {
        default TransitionAnimator.Controller createEndController(Pair<? extends View, Float> pair, Triple<? extends View, Integer, ? extends Function0> triple, boolean z) {
            return null;
        }

        default TransitionAnimator.Controller createStartController(Pair<? extends View, Float> pair, Triple<? extends View, Integer, ? extends Function0> triple, boolean z) {
            return null;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface AnimationListener {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnimationType {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ AnimationType[] $VALUES;
        public static final AnimationType MIDDLE_LAUNCH_TYPE;
        public static final AnimationType NORMAL_TYPE;

        static {
            AnimationType animationType = new AnimationType("NORMAL_TYPE", 0);
            NORMAL_TYPE = animationType;
            AnimationType animationType2 = new AnimationType("MIDDLE_LAUNCH_TYPE", 1);
            MIDDLE_LAUNCH_TYPE = animationType2;
            AnimationType[] animationTypeArr = {animationType, animationType2};
            $VALUES = animationTypeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(animationTypeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static AnimationType valueOf(String str) {
            return (AnimationType) Enum.valueOf(AnimationType.class, str);
        }

        public static AnimationType[] values() {
            return (AnimationType[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final Interpolators getEnterInterpolator() {
            return DetailAnimationManager.enterInterpolator;
        }

        public final Interpolators getExitInterpolator() {
            return DetailAnimationManager.exitInterpolator;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Interpolators {
        private final Interpolator contentBeforeFadeOutInterpolator;
        private final Interpolator panelBeforeFadeOutInterpolator;
        private final Interpolator positionInterpolator;

        public Interpolators(Interpolator interpolator, Interpolator interpolator2, Interpolator interpolator3) {
            this.positionInterpolator = interpolator;
            this.contentBeforeFadeOutInterpolator = interpolator2;
            this.panelBeforeFadeOutInterpolator = interpolator3;
        }

        public final Interpolator component1() {
            return this.positionInterpolator;
        }

        public final Interpolator component2() {
            return this.contentBeforeFadeOutInterpolator;
        }

        public final Interpolator component3() {
            return this.panelBeforeFadeOutInterpolator;
        }

        public final Interpolators copy(Interpolator interpolator, Interpolator interpolator2, Interpolator interpolator3) {
            return new Interpolators(interpolator, interpolator2, interpolator3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Interpolators)) {
                return false;
            }
            Interpolators interpolators = (Interpolators) obj;
            return Intrinsics.areEqual(this.positionInterpolator, interpolators.positionInterpolator) && Intrinsics.areEqual(this.contentBeforeFadeOutInterpolator, interpolators.contentBeforeFadeOutInterpolator) && Intrinsics.areEqual(this.panelBeforeFadeOutInterpolator, interpolators.panelBeforeFadeOutInterpolator);
        }

        public final Interpolator getContentBeforeFadeOutInterpolator() {
            return this.contentBeforeFadeOutInterpolator;
        }

        public final Interpolator getPanelBeforeFadeOutInterpolator() {
            return this.panelBeforeFadeOutInterpolator;
        }

        public final Interpolator getPositionInterpolator() {
            return this.positionInterpolator;
        }

        public final int hashCode() {
            return this.panelBeforeFadeOutInterpolator.hashCode() + ((this.contentBeforeFadeOutInterpolator.hashCode() + (this.positionInterpolator.hashCode() * 31)) * 31);
        }

        public String toString() {
            return "Interpolators(positionInterpolator=" + this.positionInterpolator + ", contentBeforeFadeOutInterpolator=" + this.contentBeforeFadeOutInterpolator + ", panelBeforeFadeOutInterpolator=" + this.panelBeforeFadeOutInterpolator + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Timings {
        public final long contentBeforeFadeOutDelay;
        public final long contentBeforeFadeOutDuration;
        public final long panelBeforeFadeOutDelay;
        public final long panelBeforeFadeOutDuration;
        public final long totalDuration;

        public Timings(long j, long j2, long j3, long j4, long j5) {
            this.totalDuration = j;
            this.contentBeforeFadeOutDelay = j2;
            this.contentBeforeFadeOutDuration = j3;
            this.panelBeforeFadeOutDelay = j4;
            this.panelBeforeFadeOutDuration = j5;
        }

        public final Timings copy(long j, long j2, long j3, long j4, long j5) {
            return new Timings(j, j2, j3, j4, j5);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Timings)) {
                return false;
            }
            Timings timings = (Timings) obj;
            return this.totalDuration == timings.totalDuration && this.contentBeforeFadeOutDelay == timings.contentBeforeFadeOutDelay && this.contentBeforeFadeOutDuration == timings.contentBeforeFadeOutDuration && this.panelBeforeFadeOutDelay == timings.panelBeforeFadeOutDelay && this.panelBeforeFadeOutDuration == timings.panelBeforeFadeOutDuration;
        }

        public final long getContentBeforeFadeOutDelay() {
            return this.contentBeforeFadeOutDelay;
        }

        public final long getContentBeforeFadeOutDuration() {
            return this.contentBeforeFadeOutDuration;
        }

        public final long getPanelBeforeFadeOutDelay() {
            return this.panelBeforeFadeOutDelay;
        }

        public final long getPanelBeforeFadeOutDuration() {
            return this.panelBeforeFadeOutDuration;
        }

        public final long getTotalDuration() {
            return this.totalDuration;
        }

        public final int hashCode() {
            return Long.hashCode(this.panelBeforeFadeOutDuration) + ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(Long.hashCode(this.totalDuration) * 31, 31, this.contentBeforeFadeOutDelay), 31, this.contentBeforeFadeOutDuration), 31, this.panelBeforeFadeOutDelay);
        }

        public String toString() {
            StringBuilder sbM = TimerSpan$.ExternalSyntheticOutline0.m(this.totalDuration, "Timings(totalDuration=", ", contentBeforeFadeOutDelay=");
            sbM.append(this.contentBeforeFadeOutDelay);
            DefaultTransitionHelper$.ExternalSyntheticOutline0.m(sbM, ", contentBeforeFadeOutDuration=", this.contentBeforeFadeOutDuration, ", panelBeforeFadeOutDelay=");
            sbM.append(this.panelBeforeFadeOutDelay);
            sbM.append(", panelBeforeFadeOutDuration=");
            sbM.append(this.panelBeforeFadeOutDuration);
            sbM.append(")");
            return sbM.toString();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnimationType.values().length];
            try {
                AnimationType animationType = AnimationType.NORMAL_TYPE;
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                AnimationType animationType2 = AnimationType.NORMAL_TYPE;
                iArr[0] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.viewdialog.ui.animation.DetailAnimationManager$startAnimation$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements ValueAnimator.AnimatorUpdateListener {
        public /* synthetic */ AnimationListener $animListener;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            ((DetailAnimationManager$starAnimation$animListener$1) this.$animListener).onAnimationUpdate(valueAnimator.getAnimatedFraction());
        }
    }

    public static Unit $r8$lambda$0_gdInQgYrwVsXNlVyJxEBJJEOI(TransitionAnimator.Controller controller, TransitionAnimator.Controller controller2, DetailAnimationManager detailAnimationManager, boolean z) {
        controller.onTransitionAnimationEnd(false, z);
        controller2.onTransitionAnimationEnd(false, z);
        ViewDialogAnimationManager.AnimationCallBack animationCallBack = detailAnimationManager.animationCallBack;
        if (animationCallBack != null) {
            animationCallBack.onAnimationEnd(detailAnimationManager.isLaunching, z);
        }
        if (!z) {
            detailAnimationManager.curState = null;
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$0mzfSEcEv32k2xTMtXGVIn18tZU(DetailAnimationManager detailAnimationManager, TransitionAnimator.Controller controller, TransitionAnimator.Controller controller2) {
        LaunchableView launchableView = detailAnimationManager.targetView;
        if (launchableView instanceof LaunchableView) {
            launchableView.setShouldBlockVisibilityChanges(false);
            detailAnimationManager.targetView.setVisibility(0);
        }
        ViewDialogAnimationManager.AnimationCallBack animationCallBack = detailAnimationManager.animationCallBack;
        if (animationCallBack != null) {
            animationCallBack.onAnimationStart(detailAnimationManager.isLaunching);
        }
        controller.onTransitionAnimationStart(false);
        controller2.onTransitionAnimationStart(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$jbv7KKZ7wvujKrJs24mTS-W5hTE, reason: not valid java name */
    public static Unit m478$r8$lambda$jbv7KKZ7wvujKrJs24mTSW5hTE(DetailAnimationManager detailAnimationManager, TransitionAnimator.Controller controller, TransitionAnimator.Controller controller2, TransitionAnimator.State state, float f, float f2) {
        if (!detailAnimationManager.isLaunching) {
            TransitionFrameCornerSync transitionFrameCornerSync = controller instanceof TransitionFrameCornerSync ? (TransitionFrameCornerSync) controller : null;
            if (transitionFrameCornerSync != null) {
                transitionFrameCornerSync.preSyncFrameCornerRadii(state, f);
            }
        }
        controller2.onTransitionAnimationProgress(state, f, f2);
        state.setAlpha(1.0f - state.getAlpha());
        controller.onTransitionAnimationProgress(state, f, f2);
        ViewDialogAnimationManager.AnimationCallBack animationCallBack = detailAnimationManager.animationCallBack;
        if (animationCallBack != null) {
            animationCallBack.onAnimationUpdate(state.getPanelAlpha());
        }
        return Unit.INSTANCE;
    }

    public DetailAnimationManager(View view) {
        this.targetView = view;
        this.targetData = new Triple<>(view, 0, (Object) null);
    }

    public static final void access$starAnimation$onProgressChanged(Interpolators interpolators, Ref.FloatRef floatRef, Ref.FloatRef floatRef2, Ref.IntRef intRef, Ref.IntRef intRef2, TransitionAnimator.State state, Ref.IntRef intRef3, Ref.IntRef intRef4, Ref.IntRef intRef5, Ref.IntRef intRef6, float f, float f2, float f3, float f4, float f5, float f6, Timings timings, DetailAnimationManager detailAnimationManager, float f7, float f8, DetailAnimationManager$$ExternalSyntheticLambda1 detailAnimationManager$$ExternalSyntheticLambda1, TransitionAnimator.State state2, Ref.IntRef intRef7, Ref.IntRef intRef8, TransitionAnimator.State state3, Ref.IntRef intRef9, Ref.IntRef intRef10, float f9) {
        float interpolation;
        float interpolation2;
        if (intRef4.element != state2.getTop() || intRef6.element != state2.getBottom() || intRef7.element != state2.getLeft() || intRef8.element != state2.getRight()) {
            intRef4.element = state2.getTop();
            intRef6.element = state2.getBottom();
            intRef7.element = state2.getLeft();
            int right = state2.getRight();
            intRef8.element = right;
            int i = intRef7.element;
            floatRef2.element = (i + right) / 2.0f;
            intRef2.element = right - i;
        }
        if (intRef3.element != state3.getTop() || intRef5.element != state3.getBottom() || intRef9.element != state3.getLeft() || intRef10.element != state3.getRight()) {
            intRef3.element = state3.getTop();
            intRef5.element = state3.getBottom();
            intRef9.element = state3.getLeft();
            int right2 = state3.getRight();
            intRef10.element = right2;
            int i2 = intRef9.element;
            floatRef.element = (i2 + right2) / 2.0f;
            intRef.element = right2 - i2;
        }
        float interpolation3 = interpolators.getPositionInterpolator().getInterpolation(f9);
        float fLerp = MathUtils.lerp(floatRef.element, floatRef2.element, interpolation3);
        float fLerp2 = MathUtils.lerp(intRef.element, intRef2.element, interpolation3) / 2.0f;
        state.setTop(MathKt.roundToInt(MathUtils.lerp(intRef3.element, intRef4.element, interpolation3)));
        state.setBottom(MathKt.roundToInt(MathUtils.lerp(intRef5.element, intRef6.element, interpolation3)));
        state.setLeft(MathKt.roundToInt(fLerp - fLerp2));
        state.setRight(MathKt.roundToInt(fLerp + fLerp2));
        state.setTopCornerRadius(MathUtils.lerp(f, f2, interpolation3));
        state.setBottomCornerRadius(MathUtils.lerp(f3, f4, interpolation3));
        state.setCornerWeight(MathUtils.lerp(f5, f6, interpolation3));
        TransitionAnimator.Companion companion = TransitionAnimator.Companion;
        long totalDuration = timings.getTotalDuration();
        long contentBeforeFadeOutDelay = timings.getContentBeforeFadeOutDelay();
        long contentBeforeFadeOutDuration = timings.getContentBeforeFadeOutDuration();
        companion.getClass();
        float progressInternal = TransitionAnimator.Companion.getProgressInternal(totalDuration, f9, contentBeforeFadeOutDelay, contentBeforeFadeOutDuration);
        if (detailAnimationManager.isLaunching) {
            interpolation = interpolators.getContentBeforeFadeOutInterpolator().getInterpolation(progressInternal);
        } else {
            interpolation = (interpolators.getContentBeforeFadeOutInterpolator().getInterpolation(progressInternal) * (1.0f - f7)) + f7;
        }
        state.setAlpha(interpolation);
        float progressInternal2 = TransitionAnimator.Companion.getProgressInternal(timings.getTotalDuration(), f9, timings.getPanelBeforeFadeOutDelay(), timings.getPanelBeforeFadeOutDuration());
        if (detailAnimationManager.isLaunching) {
            interpolation2 = interpolators.getPanelBeforeFadeOutInterpolator().getInterpolation(1.0f - progressInternal2);
        } else {
            interpolation2 = (interpolators.getPanelBeforeFadeOutInterpolator().getInterpolation(progressInternal2) * (1.0f - f8)) + f8;
        }
        state.setPanelAlpha(interpolation2);
        detailAnimationManager$$ExternalSyntheticLambda1.invoke(state, Float.valueOf(interpolation3), Float.valueOf(f9));
    }

    public void cancel() {
        this.animation.cancel();
    }

    public final void cancelAndSkipToEnd() {
        if (isRunning()) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("cancelAnimation: isLaunching = ", this.isLaunching), null, 8);
            this.skipToEnd = true;
            cancel();
            this.skipToEnd = false;
            this.curState = null;
        }
    }

    public TransitionAnimator.Controller createEndController(Pair<? extends View, Float> pair, Triple<? extends View, Integer, ? extends Function0> triple, AnimationType animationType) {
        TransitionAnimator.Controller controllerCreateEndController;
        View view = (View) pair.getFirst();
        if (view == null || animationType == AnimationType.MIDDLE_LAUNCH_TYPE) {
            return !this.isLaunching ? new EmptyTransitionAnimatorController((View) triple.getFirst(), false) : new MiddleLaunchAnimatorController((View) triple.getFirst(), (Function0) triple.getThird(), true, ((Number) triple.getSecond()).intValue());
        }
        ViewDialogAnimationManager.AnimationCallBack animationCallBack = this.animationCallBack;
        AnimationCallBack animationCallBack2 = animationCallBack instanceof AnimationCallBack ? (AnimationCallBack) animationCallBack : null;
        if (animationCallBack2 == null || (controllerCreateEndController = animationCallBack2.createEndController(pair, triple, this.isLaunching)) == null) {
            return !this.isLaunching ? new GhostedViewTransitionAnimatorController(view, (Integer) null, (ActivityTransitionAnimator.TransitionCookie) null, (ComponentName) null, (Integer) null, false, (InteractionJankMonitor) null, (Float) pair.getSecond(), true, (Float) null, 1662) : new TargetViewLaunchAnimatorController((View) triple.getFirst(), (Function0) triple.getThird(), true, ((Number) triple.getSecond()).intValue());
        }
        return controllerCreateEndController;
    }

    public TransitionAnimator.Controller createStartController(Pair<? extends View, Float> pair, Triple<? extends View, Integer, ? extends Function0> triple, AnimationType animationType) {
        TransitionAnimator.Controller controllerCreateStartController;
        View view = (View) pair.getFirst();
        if (view == null || animationType == AnimationType.MIDDLE_LAUNCH_TYPE) {
            return this.isLaunching ? new EmptyTransitionAnimatorController((View) triple.getFirst(), true) : new MiddleLaunchAnimatorController((View) triple.getFirst(), (Function0) triple.getThird(), false, ((Number) triple.getSecond()).intValue());
        }
        ViewDialogAnimationManager.AnimationCallBack animationCallBack = this.animationCallBack;
        AnimationCallBack animationCallBack2 = animationCallBack instanceof AnimationCallBack ? (AnimationCallBack) animationCallBack : null;
        if (animationCallBack2 == null || (controllerCreateStartController = animationCallBack2.createStartController(pair, triple, this.isLaunching)) == null) {
            return this.isLaunching ? new GhostedViewTransitionAnimatorController(view, (Integer) null, (ActivityTransitionAnimator.TransitionCookie) null, (ComponentName) null, (Integer) null, false, (InteractionJankMonitor) null, (Float) pair.getSecond(), true, (Float) null, 1662) : new TargetViewLaunchAnimatorController((View) triple.getFirst(), (Function0) triple.getThird(), false, ((Number) triple.getSecond()).intValue());
        }
        return controllerCreateStartController;
    }

    public final ViewDialogAnimationManager.AnimationCallBack getAnimationCallBack() {
        return this.animationCallBack;
    }

    public AnimationType getAnimationType(View view) {
        if (ScrimUtil.isBAnimLevel() || view == null || !view.isAttachedToWindow() || !view.isShown()) {
            return AnimationType.MIDDLE_LAUNCH_TYPE;
        }
        Rect rect = new Rect();
        return (!view.getGlobalVisibleRect(rect) || rect.width() == 0 || rect.height() == 0) ? AnimationType.MIDDLE_LAUNCH_TYPE : AnimationType.NORMAL_TYPE;
    }

    public final TransitionAnimator.State getCurState() {
        return this.curState;
    }

    public final TransitionAnimator.State getEndState() {
        return this.endState;
    }

    public Interpolators getInterpolators(AnimationType animationType) {
        int i = WhenMappings.$EnumSwitchMapping$0[animationType.ordinal()];
        if (i == 1 && this.isLaunching) {
            return middleEnterInterpolator;
        }
        if (i != 1 || this.isLaunching) {
            return (i == 2 && this.isLaunching) ? enterInterpolator : exitInterpolator;
        }
        return middleExitInterpolator;
    }

    public final TransitionAnimator.State getStartState() {
        return this.startState;
    }

    public final View getTargetView() {
        return this.targetView;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager
    public void hideDetailToView(View view, String str, int i) {
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("hideDetailToView: type=", str, ", isLaunching=", this.isLaunching, " for fromView=");
        sbM.append(view);
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, sbM.toString(), null, 8);
        if (!Intrinsics.areEqual(str, "instant")) {
            if (this.isLaunching) {
                if (isRunning()) {
                    cancel();
                }
                this.fromData = Intrinsics.areEqual(str, "middle") ? new Pair<>((Object) null, this.fromData.getSecond()) : new Pair<>(view, this.fromData.getSecond());
                if (((Number) this.targetData.getSecond()).intValue() != i) {
                    this.targetData = new Triple<>(this.targetView, Integer.valueOf(i), this.targetData.getThird());
                }
                this.isLaunching = false;
                startLaunchAnimation(this.fromData, this.targetData);
                return;
            }
            return;
        }
        cancel();
        this.isLaunching = false;
        this.curState = null;
        LaunchableView launchableView = this.targetView;
        if (launchableView instanceof LaunchableView) {
            launchableView.setShouldBlockVisibilityChanges(false);
            this.targetView.setVisibility(8);
        }
        Function0 function0 = (Function0) this.targetData.getThird();
        SurfaceControl surfaceControl = function0 != null ? (SurfaceControl) function0.invoke() : null;
        if (surfaceControl != null && surfaceControl.isValid()) {
            this.transaction.hide(surfaceControl).apply();
        }
        ViewDialogAnimationManager.AnimationCallBack animationCallBack = this.animationCallBack;
        if (animationCallBack != null) {
            animationCallBack.onAnimationEnd(this.isLaunching, false);
        }
    }

    public boolean isRunning() {
        return this.animation.isRunning();
    }

    public final void setAnimationCallBack(ViewDialogAnimationManager.AnimationCallBack animationCallBack) {
        this.animationCallBack = animationCallBack;
    }

    public final void setCurState(TransitionAnimator.State state) {
        this.curState = state;
    }

    public final void setEndState(TransitionAnimator.State state) {
        this.endState = state;
    }

    public final void setLaunching(boolean z) {
        this.isLaunching = z;
    }

    public final void setStartState(TransitionAnimator.State state) {
        this.startState = state;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager
    public void showDetailFromView(View view, int i, Function0 function0, ViewDialogAnimationManager.AnimationCallBack animationCallBack) {
        boolean zIsRunning = isRunning();
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("showDetailFromView: isAnimRunning=", " isLaunching=", " for fromView=", zIsRunning, this.isLaunching);
        sbM.append(view);
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, sbM.toString(), null, 8);
        if (this.isLaunching || zIsRunning) {
            return;
        }
        if (!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            Path$.ExternalSyntheticBUOutline0.m$1("showFromView must be called from the main thread");
            return;
        }
        this.animationCallBack = animationCallBack;
        this.fromData = new Pair<>(view, view != null ? QsUtil.getCornerRadiusForShapeDrawable(view) : null);
        this.targetData = new Triple<>(this.targetView, Integer.valueOf(i), function0);
        if (this.targetView.isLaidOut()) {
            this.targetView.setVisibility(0);
        }
        LaunchableView launchableView = this.targetView;
        if (launchableView instanceof LaunchableView) {
            launchableView.setShouldBlockVisibilityChanges(true);
            this.targetView.setTransitionVisibility(4);
        }
        View view2 = this.targetView;
        DetailAnimationManager$showDetailFromView$$inlined$doOnPreDraw$1 detailAnimationManager$showDetailFromView$$inlined$doOnPreDraw$1 = new DetailAnimationManager$showDetailFromView$$inlined$doOnPreDraw$1();
        detailAnimationManager$showDetailFromView$$inlined$doOnPreDraw$1.this$0 = this;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(view2, detailAnimationManager$showDetailFromView$$inlined$doOnPreDraw$1);
    }

    public void startAnimation(long j, AnimationType animationType, final AnimationListener animationListener) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setInterpolator(com.android.app.animation.Interpolators.LINEAR);
        ScreenOffAnimationGuardKt.enableScreenOffAnimationGuard(valueAnimatorOfFloat, this.targetView.getContext());
        valueAnimatorOfFloat.setDuration(j);
        this.animation = valueAnimatorOfFloat;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.$animListener = animationListener;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(anonymousClass2);
        this.animation.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.viewdialog.ui.animation.DetailAnimationManager.startAnimation.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                ((DetailAnimationManager$starAnimation$animListener$1) animationListener).canceled = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                ((DetailAnimationManager$starAnimation$animListener$1) animationListener).onAnimationEnd();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                DetailAnimationManager$starAnimation$animListener$1 detailAnimationManager$starAnimation$animListener$1 = (DetailAnimationManager$starAnimation$animListener$1) animationListener;
                detailAnimationManager$starAnimation$animListener$1.canceled = false;
                detailAnimationManager$starAnimation$animListener$1.$onAnimationStart.invoke();
            }
        });
        this.animation.start();
    }

    public void startLaunchAnimation(Pair<? extends View, Float> pair, Triple<? extends View, Integer, ? extends Function0> triple) {
        Timings timings;
        AnimationType animationType = getAnimationType((View) pair.getFirst());
        TransitionAnimator.Controller controllerCreateStartController = createStartController(pair, triple, animationType);
        TransitionAnimator.Controller controllerCreateEndController = createEndController(pair, triple, animationType);
        TransitionAnimator.State stateCreateAnimatorState = this.curState;
        if (stateCreateAnimatorState == null) {
            stateCreateAnimatorState = controllerCreateStartController.createAnimatorState();
        }
        TransitionAnimator.State state = this.curState;
        float panelAlpha = 0.0f;
        stateCreateAnimatorState.setAlpha(state != null ? state.getAlpha() : 0.0f);
        TransitionAnimator.State state2 = this.curState;
        if (state2 != null) {
            panelAlpha = state2.getPanelAlpha();
        } else if (this.isLaunching) {
            panelAlpha = 1.0f;
        }
        stateCreateAnimatorState.setPanelAlpha(panelAlpha);
        TransitionAnimator.State stateCreateAnimatorState2 = controllerCreateEndController.createAnimatorState();
        DetailAnimationManager$$ExternalSyntheticLambda0 detailAnimationManager$$ExternalSyntheticLambda0 = new DetailAnimationManager$$ExternalSyntheticLambda0();
        detailAnimationManager$$ExternalSyntheticLambda0.f$0 = this;
        detailAnimationManager$$ExternalSyntheticLambda0.f$1 = controllerCreateStartController;
        detailAnimationManager$$ExternalSyntheticLambda0.f$2 = controllerCreateEndController;
        VarHandle.storeStoreFence();
        DetailAnimationManager$$ExternalSyntheticLambda1 detailAnimationManager$$ExternalSyntheticLambda1 = new DetailAnimationManager$$ExternalSyntheticLambda1();
        detailAnimationManager$$ExternalSyntheticLambda1.f$0 = this;
        detailAnimationManager$$ExternalSyntheticLambda1.f$1 = controllerCreateStartController;
        detailAnimationManager$$ExternalSyntheticLambda1.f$2 = controllerCreateEndController;
        VarHandle.storeStoreFence();
        DetailAnimationManager$$ExternalSyntheticLambda2 detailAnimationManager$$ExternalSyntheticLambda2 = new DetailAnimationManager$$ExternalSyntheticLambda2();
        detailAnimationManager$$ExternalSyntheticLambda2.f$0 = controllerCreateStartController;
        detailAnimationManager$$ExternalSyntheticLambda2.f$1 = controllerCreateEndController;
        detailAnimationManager$$ExternalSyntheticLambda2.f$2 = this;
        VarHandle.storeStoreFence();
        AnimationType animationType2 = AnimationType.MIDDLE_LAUNCH_TYPE;
        if (animationType == animationType2 && this.isLaunching) {
            timings = middleEnterTimings;
        } else if (animationType != animationType2 || this.isLaunching) {
            timings = (animationType == AnimationType.NORMAL_TYPE && this.isLaunching) ? enterTimings : exitTimings;
        } else {
            timings = middleExitTimings;
        }
        Interpolators interpolators = getInterpolators(animationType);
        this.startState = stateCreateAnimatorState;
        this.endState = stateCreateAnimatorState2;
        TransitionAnimator.State stateCopy = stateCreateAnimatorState.copy();
        this.curState = stateCopy;
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = stateCopy.getTop();
        Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = stateCopy.getBottom();
        Ref.IntRef intRef3 = new Ref.IntRef();
        intRef3.element = stateCopy.getLeft();
        Ref.IntRef intRef4 = new Ref.IntRef();
        intRef4.element = stateCopy.getRight();
        Ref.FloatRef floatRef = new Ref.FloatRef();
        floatRef.element = (intRef3.element + intRef4.element) / 2.0f;
        Ref.IntRef intRef5 = new Ref.IntRef();
        intRef5.element = intRef4.element - intRef3.element;
        float topCornerRadius = stateCopy.getTopCornerRadius();
        float bottomCornerRadius = stateCopy.getBottomCornerRadius();
        float panelAlpha2 = stateCopy.getPanelAlpha();
        TransitionAnimator.State state3 = stateCreateAnimatorState;
        float cornerWeight = stateCopy.getCornerWeight();
        float alpha = stateCopy.getAlpha();
        Ref.IntRef intRef6 = new Ref.IntRef();
        intRef6.element = stateCreateAnimatorState2.getTop();
        Ref.IntRef intRef7 = new Ref.IntRef();
        intRef7.element = stateCreateAnimatorState2.getBottom();
        Ref.IntRef intRef8 = new Ref.IntRef();
        Timings timings2 = timings;
        intRef8.element = stateCreateAnimatorState2.getLeft();
        Ref.IntRef intRef9 = new Ref.IntRef();
        intRef9.element = stateCreateAnimatorState2.getRight();
        Ref.FloatRef floatRef2 = new Ref.FloatRef();
        floatRef2.element = (intRef8.element + intRef9.element) / 2.0f;
        Ref.IntRef intRef10 = new Ref.IntRef();
        intRef10.element = intRef9.element - intRef8.element;
        float topCornerRadius2 = stateCreateAnimatorState2.getTopCornerRadius();
        float bottomCornerRadius2 = stateCreateAnimatorState2.getBottomCornerRadius();
        float cornerWeight2 = stateCreateAnimatorState2.getCornerWeight();
        DetailAnimationManager$starAnimation$animListener$1 detailAnimationManager$starAnimation$animListener$1 = new DetailAnimationManager$starAnimation$animListener$1();
        detailAnimationManager$starAnimation$animListener$1.$onAnimationStart = detailAnimationManager$$ExternalSyntheticLambda0;
        detailAnimationManager$starAnimation$animListener$1.this$0 = this;
        detailAnimationManager$starAnimation$animListener$1.$onAnimationEnd = detailAnimationManager$$ExternalSyntheticLambda2;
        detailAnimationManager$starAnimation$animListener$1.$interpolators = interpolators;
        detailAnimationManager$starAnimation$animListener$1.$startCenterX = floatRef;
        detailAnimationManager$starAnimation$animListener$1.$endCenterX = floatRef2;
        detailAnimationManager$starAnimation$animListener$1.$startWidth = intRef5;
        detailAnimationManager$starAnimation$animListener$1.$endWidth = intRef10;
        detailAnimationManager$starAnimation$animListener$1.$state = stateCopy;
        detailAnimationManager$starAnimation$animListener$1.$startTop = intRef;
        detailAnimationManager$starAnimation$animListener$1.$endTop = intRef6;
        detailAnimationManager$starAnimation$animListener$1.$startBottom = intRef2;
        detailAnimationManager$starAnimation$animListener$1.$endBottom = intRef7;
        detailAnimationManager$starAnimation$animListener$1.$startTopCornerRadius = topCornerRadius;
        detailAnimationManager$starAnimation$animListener$1.$endTopCornerRadius = topCornerRadius2;
        detailAnimationManager$starAnimation$animListener$1.$startBottomCornerRadius = bottomCornerRadius;
        detailAnimationManager$starAnimation$animListener$1.$endBottomCornerRadius = bottomCornerRadius2;
        detailAnimationManager$starAnimation$animListener$1.$startCornerWeight = cornerWeight;
        detailAnimationManager$starAnimation$animListener$1.$endCornerWeight = cornerWeight2;
        detailAnimationManager$starAnimation$animListener$1.$timings = timings2;
        detailAnimationManager$starAnimation$animListener$1.$startAlpha = alpha;
        detailAnimationManager$starAnimation$animListener$1.$startPanelAlpha = panelAlpha2;
        detailAnimationManager$starAnimation$animListener$1.$onAnimationProgress = detailAnimationManager$$ExternalSyntheticLambda1;
        detailAnimationManager$starAnimation$animListener$1.$endState = stateCreateAnimatorState2;
        detailAnimationManager$starAnimation$animListener$1.$endLeft = intRef8;
        detailAnimationManager$starAnimation$animListener$1.$endRight = intRef9;
        detailAnimationManager$starAnimation$animListener$1.$startState = state3;
        detailAnimationManager$starAnimation$animListener$1.$startLeft = intRef3;
        detailAnimationManager$starAnimation$animListener$1.$startRight = intRef4;
        VarHandle.storeStoreFence();
        startAnimation(timings2.getTotalDuration(), animationType, detailAnimationManager$starAnimation$animListener$1);
    }
}
