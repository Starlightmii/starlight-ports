package com.oplus.systemui.statusbar.notification.row;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.IndentingPrintWriter;
import android.util.Log;
import android.util.MathUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable$;
import androidx.car.app.model.constraints.ActionsConstraints$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator;
import com.android.systemui.statusbar.notification.animation.RowAnimationStateEx;
import com.android.systemui.statusbar.notification.animation.ScaleState;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewProxy;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.AnimationFilter;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.MediaContainerView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateExKt;
import com.android.systemui.statusbar.notification.stack.SectionHeaderView;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.keyguard.folder.OplusBouncerLocationController$clickListener$1$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.notification.base.radius.SmoothRoundExKt;
import com.oplus.systemui.notification.lockscreen.base.LockScreenNotificationKotlinExKt;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.utils.ViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.row.customrow.guidetips.CustomCardGuideTipsView;
import com.oplus.systemui.notification.row.customrow.guidetips.OplusCustomRowGuideTipsController;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.notification.row.material.NotificationBlurMode;
import com.oplus.systemui.notification.row.oplusgroup.OplusNotificationChildrenContainer;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.rendercache.RenderBooster;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusExpandableViewExImpl implements OplusExpandableViewEx {
    public static final Companion Companion = new Companion();
    private static final String KEY_SCALE_FOR_TOUCH = "scale_for_touch";
    private static final String TAG = "ExpandableViewExtImpl";
    private final MutableStateFlow _actualHeight;
    public float animationCornerRadius;
    private final AnimationProperties animationProperties;
    public float blurRadio;
    private Pair<? extends View, ? extends View> blurView;
    public float capsuleProgress;
    public int clipWidth;
    private final Context context;
    private Object currentClipWidthSource;
    public float effectedBlurRatio;
    public float groupExpandHeaderAlpha;
    public float groupExpansionProgress;
    public float immersiveProgress;
    public boolean needResetRadius;
    public final boolean oplusStyle;
    private OplusExpandableViewProxy proxy;
    private final Rect proxyScaledRect;
    private ResetPassCache resetPassCache;
    private View scaleView;
    public int targetClipHeight;
    public int targetClipWidth;
    public int targetShowHeight;
    private final Runnable timeoutRunnable;
    private final Lazy touchScaleAnimator$delegate;
    private ExpandableView view;
    private final Lazy ambientState$delegate = LazyKt__LazyJVMKt.lazy(new OplusExpandableViewExImpl$$ExternalSyntheticLambda0());
    public boolean metaBallDesired = true;
    public float groupExpandContentAlpha = 1.0f;
    private MaterialColorMode materialColorMode = MaterialColorMode.UNINITIATED;
    private NotificationBlurMode notificationBlurMode = NotificationBlurMode.UNINITIATED;
    private final AnimationFilter animationFilter = new AnimationFilter();
    private final Set<Runnable> animationFinishedRunnables = new HashSet();

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 extends AnimationProperties {
        public /* synthetic */ OplusExpandableViewExImpl this$0;

        public AnimationFilter getAnimationFilter() {
            return this.this$0.animationFilter;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final View access$getContextView(Companion companion, ExpandableView expandableView, boolean z) {
            Object obj;
            companion.getClass();
            if (expandableView instanceof CapsuleItemView) {
                return z ? ((CapsuleItemView) expandableView).getContentContainer() : ((CapsuleItemView) expandableView).getBlurView();
            }
            if (expandableView instanceof MediaContainerView) {
                return expandableView.findViewById(2131364781);
            }
            Iterator it = ConvenienceExtensionsKt.getChildren(expandableView).iterator();
            int i = 0;
            while (true) {
                obj = null;
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                View view = (View) next;
                if (i != 0 && (view instanceof ViewGroup) && view.getVisibility() == 0) {
                    obj = next;
                    break;
                }
                i = i2;
            }
            return (View) obj;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ResetPassCache {
        public int intrinsicHeight;

        public ResetPassCache(int i) {
            this.intrinsicHeight = i;
        }

        public final ResetPassCache copy(int i) {
            return new ResetPassCache(i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ResetPassCache) && this.intrinsicHeight == ((ResetPassCache) obj).intrinsicHeight;
        }

        public final int getIntrinsicHeight() {
            return this.intrinsicHeight;
        }

        public final int hashCode() {
            return Integer.hashCode(this.intrinsicHeight);
        }

        public final void setIntrinsicHeight(int i) {
            this.intrinsicHeight = i;
        }

        public String toString() {
            return ActionsConstraints$.ExternalSyntheticOutline0.m(this.intrinsicHeight, "ResetPassCache(intrinsicHeight=", ")");
        }
    }

    public OplusExpandableViewExImpl(Context context) {
        this.context = context;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.animationProperties = anonymousClass1;
        OplusExpandableViewExImpl$$ExternalSyntheticLambda1 oplusExpandableViewExImpl$$ExternalSyntheticLambda1 = new OplusExpandableViewExImpl$$ExternalSyntheticLambda1();
        oplusExpandableViewExImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.touchScaleAnimator$delegate = LazyKt__LazyJVMKt.lazy(oplusExpandableViewExImpl$$ExternalSyntheticLambda1);
        this.oplusStyle = true;
        this._actualHeight = StateFlowKt.MutableStateFlow(0);
        this.proxyScaledRect = new Rect();
        OplusExpandableViewExImpl$timeoutRunnable$1 oplusExpandableViewExImpl$timeoutRunnable$1 = new OplusExpandableViewExImpl$timeoutRunnable$1();
        oplusExpandableViewExImpl$timeoutRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.timeoutRunnable = oplusExpandableViewExImpl$timeoutRunnable$1;
    }

    public static final void access$runAnimationFinishedRunnables(OplusExpandableViewExImpl oplusExpandableViewExImpl) {
        Iterator<Runnable> it = oplusExpandableViewExImpl.animationFinishedRunnables.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
        oplusExpandableViewExImpl.animationFinishedRunnables.clear();
    }

    private final AmbientState getAmbientState() {
        return (AmbientState) this.ambientState$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final COUISpringAnimation getTouchScaleAnimator() {
        return (COUISpringAnimation) this.touchScaleAnimator$delegate.getValue();
    }

    private final void setBlurView(Pair<? extends View, ? extends View> pair) {
        View view;
        View view2;
        RenderBooster renderBooster;
        View view3;
        View view4;
        View view5;
        RenderBooster renderBooster2;
        View view6;
        if (Intrinsics.areEqual(this.blurView, pair)) {
            return;
        }
        Pair<? extends View, ? extends View> pair2 = this.blurView;
        if (!Intrinsics.areEqual(pair2 != null ? (View) pair2.getFirst() : null, pair != null ? (View) pair.getFirst() : null)) {
            Pair<? extends View, ? extends View> pair3 = this.blurView;
            if (pair3 != null && (view6 = (View) pair3.getFirst()) != null) {
                setupBlurRadio(0.0f, view6);
            }
            Pair<? extends View, ? extends View> pair4 = this.blurView;
            if (pair4 != null && (view5 = (View) pair4.getFirst()) != null && (renderBooster2 = RenderBooster.Companion.renderBooster(view5)) != null) {
                renderBooster2.requestStop(16);
            }
            if (pair != null && (view4 = (View) pair.getFirst()) != null) {
                RenderBooster renderBoosterEnsureRenderBooster$default = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, view4, getView() instanceof ExpandableNotificationRow ? new OplusExpandableViewExImpl$resolveRenderExpensiveView$1(1, OplusExpandableNotificationRowExImpl.Companion, OplusExpandableNotificationRowExImpl.Companion.class, "isRenderExpensiveView", "isRenderExpensiveView(Landroid/view/View;)Z", 0) : null, 1);
                if (renderBoosterEnsureRenderBooster$default != null) {
                    RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default, 16, false, 6);
                }
            }
        }
        Pair<? extends View, ? extends View> pair5 = this.blurView;
        if (!Intrinsics.areEqual(pair5 != null ? (View) pair5.getSecond() : null, pair != null ? (View) pair.getSecond() : null)) {
            Pair<? extends View, ? extends View> pair6 = this.blurView;
            if (pair6 != null && (view3 = (View) pair6.getSecond()) != null) {
                setupBlurRadio(0.0f, view3);
            }
            Pair<? extends View, ? extends View> pair7 = this.blurView;
            if (pair7 != null && (view2 = (View) pair7.getSecond()) != null && (renderBooster = RenderBooster.Companion.renderBooster(view2)) != null) {
                renderBooster.requestStop(16);
            }
            if (pair != null && (view = (View) pair.getSecond()) != null) {
                RenderBooster renderBoosterEnsureRenderBooster$default2 = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, view, getView() instanceof ExpandableNotificationRow ? new OplusExpandableViewExImpl$resolveRenderExpensiveView$1(1, OplusExpandableNotificationRowExImpl.Companion, OplusExpandableNotificationRowExImpl.Companion.class, "isRenderExpensiveView", "isRenderExpensiveView(Landroid/view/View;)Z", 0) : null, 1);
                if (renderBoosterEnsureRenderBooster$default2 != null) {
                    RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default2, 16, false, 6);
                }
            }
        }
        this.blurView = pair;
    }

    private final void setScaleView(View view) {
        if (Intrinsics.areEqual(this.scaleView, view)) {
            return;
        }
        View view2 = this.scaleView;
        if (view2 != null) {
            view2.setTranslationY(0.0f);
        }
        View view3 = this.scaleView;
        if (view3 != null) {
            ViewExpandKtKt.setScaleXY(view3, 1.0f, 1.0f);
        }
        this.scaleView = view;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setupBlurRadio(float f, View view) {
        OplusNotificationChildrenContainer oplusNotificationChildrenContainer = view instanceof OplusNotificationChildrenContainer ? (OplusNotificationChildrenContainer) view : null;
        if (oplusNotificationChildrenContainer != null) {
            oplusNotificationChildrenContainer.setBlurRadio(f);
        } else if (f > 0.0f) {
            view.setRenderEffect(RenderEffect.createBlurEffect(f, f, Shader.TileMode.CLAMP));
        } else {
            view.setRenderEffect(null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* bridge */ /* synthetic */ void bind(View view) {
        super/*com.oplusos.systemui.common.BaseViewBinder*/.bind(view);
    }

    public final boolean canUseTargetShowHeight() {
        ExpandableNotificationRow view = getView();
        ExpandableNotificationRow expandableNotificationRow = view instanceof ExpandableNotificationRow ? view : null;
        return expandableNotificationRow != null && expandableNotificationRow.mIsSummaryWithChildren && ((OplusExpandableNotificationRowExImpl) expandableNotificationRow.mRowEx).isChildrenExpandedAnimating() && getAmbientState().isOnKeyguard();
    }

    public boolean childNeedsClipping(View view) {
        if (getClipWidth() == 0) {
            return false;
        }
        return (view instanceof NotificationContentView) || view.getId() == 2131364388;
    }

    public void dump(IndentingPrintWriter indentingPrintWriter, String[] strArr) {
        if (getProxy() != null) {
            OplusExpandableViewProxy proxy = getProxy();
            indentingPrintWriter.println("has proxy: " + (proxy != null ? proxy.getProxyName() : null));
        }
        indentingPrintWriter.println("clipWidth: " + getClipWidth());
        indentingPrintWriter.println("effectedBlurRatio: " + this.effectedBlurRatio);
        indentingPrintWriter.println("blurRadio: " + getBlurRadio());
        indentingPrintWriter.println("animationCornerRadius: " + getAnimationCornerRadius());
        indentingPrintWriter.println("capsuleProgress: " + getCapsuleProgress());
        indentingPrintWriter.println("immersiveProgress: " + getImmersiveProgress());
        indentingPrintWriter.println("clipWidth: " + getClipWidth());
        indentingPrintWriter.println("actualClipHeight: " + getActualClipHeight());
        indentingPrintWriter.println("blurView: " + this.blurView);
        indentingPrintWriter.println("scaleView: " + this.scaleView);
        ResetPassCache resetPassCache = this.resetPassCache;
        if (resetPassCache != null) {
            indentingPrintWriter.println("resetPassCache: " + resetPassCache);
        }
    }

    public void executeRunnableAnimationFinish(Runnable runnable) {
        if (((COUIDynamicAnimation) getTouchScaleAnimator()).mRunning) {
            this.animationFinishedRunnables.add(runnable);
        } else {
            runnable.run();
        }
    }

    public int getActualClipHeight() {
        if (getTargetClipHeight() == 0 || getClipWidth() == 0 || getTargetClipWidth() == 0) {
            return 0;
        }
        if (getTargetShowHeight() <= 0 || !canUseTargetShowHeight()) {
            int targetClipHeight = (int) ((getTargetClipHeight() * getClipWidth()) / getTargetClipWidth());
            int targetClipHeight2 = getTargetClipHeight();
            return targetClipHeight > targetClipHeight2 ? targetClipHeight2 : targetClipHeight;
        }
        ExpandableView view = getView();
        int actualHeight = view != null ? view.getActualHeight() : 0;
        int iLerp = actualHeight - ((int) MathUtils.lerp(actualHeight, getTargetShowHeight(), getClipWidth() / getTargetClipWidth()));
        if (iLerp < 0) {
            return 0;
        }
        return iLerp;
    }

    public StateFlow getActualHeight() {
        return this._actualHeight;
    }

    public float getAnimationCornerRadius() {
        return this.animationCornerRadius;
    }

    public float getBlurRadio() {
        return this.blurRadio;
    }

    public int getCachedIntrinsicHeight() {
        ResetPassCache resetPassCache = this.resetPassCache;
        if (resetPassCache != null) {
            return resetPassCache.getIntrinsicHeight();
        }
        return -1;
    }

    public float getCapsuleProgress() {
        return this.capsuleProgress;
    }

    public int getClipWidth() {
        return this.clipWidth;
    }

    public Object getCurrentClipWidthSource() {
        return this.currentClipWidthSource;
    }

    public Drawable getCustomBackground(Context context) {
        return SmoothRoundExKt.getSmoothLayerDrawable(context, 2131102886);
    }

    public float getGroupExpandContentAlpha() {
        return this.groupExpandContentAlpha;
    }

    public float getGroupExpandHeaderAlpha() {
        return this.groupExpandHeaderAlpha;
    }

    public float getGroupExpansionProgress() {
        return this.groupExpansionProgress;
    }

    public float getImmersiveProgress() {
        return this.immersiveProgress;
    }

    public MaterialColorMode getMaterialColorMode() {
        return this.materialColorMode;
    }

    public /* bridge */ /* synthetic */ NotificationBackgroundView getNotificationBackgroundView() {
        return super.getNotificationBackgroundView();
    }

    public NotificationBlurMode getNotificationBlurMode() {
        return this.notificationBlurMode;
    }

    public boolean getOplusStyle() {
        return this.oplusStyle;
    }

    public OplusExpandableViewProxy getProxy() {
        return this.proxy;
    }

    public int getTargetClipHeight() {
        return this.targetClipHeight;
    }

    public int getTargetClipWidth() {
        return this.targetClipWidth;
    }

    public int getTargetShowHeight() {
        return this.targetShowHeight;
    }

    public final void invalidateResetPassCache(boolean z) {
        List attachedChildren;
        ResetPassCache resetPassCache;
        if (z && (resetPassCache = this.resetPassCache) != null) {
            ExpandableView view = getView();
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "invalidateResetPassCache unexpected resetPassCache=" + resetPassCache + ",key=" + (view != null ? LockScreenNotificationKotlinExKt.getLogKey(view) : null));
        }
        this.resetPassCache = null;
        ExpandableNotificationRow view2 = getView();
        if (view2 == null || !(view2 instanceof ExpandableNotificationRow)) {
            return;
        }
        ExpandableNotificationRow expandableNotificationRow = view2;
        if (!expandableNotificationRow.mIsSummaryWithChildren || (attachedChildren = expandableNotificationRow.getAttachedChildren()) == null) {
            return;
        }
        Iterator it = attachedChildren.iterator();
        while (it.hasNext()) {
            OplusExpandableViewEx oplusExpandableViewEx = ((ExpandableView) ((ExpandableNotificationRow) it.next())).mExpandableViewEx;
            if (oplusExpandableViewEx != null) {
                ((OplusExpandableViewExImpl) oplusExpandableViewEx).invalidateResetPassCache(z);
            }
        }
    }

    public final boolean isHasMetaBall() {
        if (!this.metaBallDesired || SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState || ScrimUtil.isBPAnimLevel() || ScrimUtil.isBAnimLevel()) {
            return false;
        }
        ScrimControllerEx.Companion.getClass();
        return !ScrimControllerEx.isThirdTheme;
    }

    /* JADX WARN: Code duplicated, block: B:43:0x0082  */
    public void onDispatchTouchEvent(MotionEvent motionEvent) {
        boolean zIsChildInGroup;
        Handler handler;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        ExpandableNotificationRow notificationParent;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx2;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx3;
        ExpandableNotificationRow view = getView();
        if (view == null) {
            return;
        }
        boolean z = view instanceof ExpandableNotificationRow;
        boolean z2 = z && (oplusExpandableNotificationRowEx3 = view.mRowEx) != null && ((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx3).isHeadsUpView();
        boolean zIsAnimatingToCollapse = ((OplusLockscreenShadeTransitionControllerEx) DependencyEx.sDependency.getDependency(OplusLockscreenShadeTransitionControllerEx.class)).isAnimatingToCollapse();
        if ((view instanceof SectionHeaderView) || z2) {
            return;
        }
        if (z) {
            ExpandableNotificationRow expandableNotificationRow = view;
            zIsChildInGroup = expandableNotificationRow.isChildInGroup() && !(expandableNotificationRow.isGroupExpanded() && ((notificationParent = expandableNotificationRow.getNotificationParent()) == null || (oplusExpandableNotificationRowEx2 = notificationParent.mRowEx) == null || !((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx2).isChildrenExpandedAnimating()));
        } else {
            zIsChildInGroup = view.isChildInGroup();
        }
        if (zIsChildInGroup) {
            return;
        }
        if (!view.isChildInGroup()) {
            if (view.isGroupExpanded()) {
                handler = view.getHandler();
                if (handler != null && !handler.hasCallbacks(this.timeoutRunnable)) {
                    return;
                }
            } else {
                ExpandableNotificationRow expandableNotificationRow2 = z ? view : null;
                if (expandableNotificationRow2 != null && (oplusExpandableNotificationRowEx = expandableNotificationRow2.mRowEx) != null && ((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx).isChildrenExpandedAnimating()) {
                    handler = view.getHandler();
                    if (handler != null) {
                        return;
                    }
                }
            }
        }
        if (zIsAnimatingToCollapse || (view instanceof CapsuleItemView)) {
            return;
        }
        if (ScrimUtil.isMiddleOrHighAnimLevel(view.getContext()) || ScrimUtil.isBPAnimLevel()) {
            if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) {
                getTouchScaleAnimator().animateToFinalPosition(1.0f);
                int[] locationOnScreen = view.getLocationOnScreen();
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("tryStartScaleAnimation animate to 1f, location=", locationOnScreen[0], locationOnScreen[1], "-", "ExpandableViewExtImpl-->"));
                }
                Handler handler2 = view.getHandler();
                if (handler2 != null) {
                    handler2.removeCallbacks(this.timeoutRunnable);
                    return;
                }
                return;
            }
            if (motionEvent.getActionMasked() == 0) {
                getTouchScaleAnimator().animateToFinalPosition(0.95f);
                int[] locationOnScreen2 = view.getLocationOnScreen();
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("tryStartScaleAnimation animate to 0.95f, location=", locationOnScreen2[0], locationOnScreen2[1], "-", "ExpandableViewExtImpl-->"));
                }
                Handler handler3 = view.getHandler();
                if (handler3 != null) {
                    handler3.removeCallbacks(this.timeoutRunnable);
                }
                Handler handler4 = view.getHandler();
                if (handler4 != null) {
                    handler4.postDelayed(this.timeoutRunnable, 5000L);
                }
            }
        }
    }

    public final void onViewRemoved() {
        setBlurView(null);
        setScaleView(null);
        OplusExpandableViewProxy proxy = getProxy();
        if (proxy != null) {
            proxy.onStackViewRemoved();
        }
        setProxy(null);
    }

    public final void processAnimationToViewOrigin() {
        ExpandableView view;
        NotificationStackScrollLayout view2;
        this.animationFilter.reset();
        AnimationFilter animationFilter = this.animationFilter;
        animationFilter.animateTopInset = true;
        animationFilter.animateY = true;
        animationFilter.animateZ = true;
        animationFilter.animateAlpha = true;
        animationFilter.animateHeight = true;
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = (NotificationStackScrollLayoutController) Dependency.sDependency.getDependencyInner(NotificationStackScrollLayoutController.class);
        if ((notificationStackScrollLayoutController == null || (view2 = notificationStackScrollLayoutController.getView()) == null || !view2.mExpandingNotification) && (view = getView()) != null) {
            view.getViewState().clipBottomAmount = view.getClipBottomAmount();
            view.getViewState().animateTo(view, this.animationProperties);
        }
    }

    public final void putCachedIntrinsicHeight(int i) {
        if (getView() instanceof ExpandableNotificationRow) {
            ResetPassCache resetPassCache = this.resetPassCache;
            if (resetPassCache == null) {
                resetPassCache = new ResetPassCache(-1);
                this.resetPassCache = resetPassCache;
            }
            resetPassCache.setIntrinsicHeight(i);
        }
    }

    public void resetTouchScaleImmediately(String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("resetTouchScaleImmediately ", str, "ExpandableViewExtImpl-->", "SystemUi--Notification");
        }
        if (((COUIDynamicAnimation) getTouchScaleAnimator()).mRunning) {
            getTouchScaleAnimator().cancel();
        }
        ExpandableView view = getView();
        if (view != null) {
            if (isHasMetaBall()) {
                ((RowAnimationStateExImpl) view.getViewState().mExpandableViewStateEx.getRowAnimationStateEx()).requestClipWidth("clip_width_touch_scale", 0);
                if (getProxy() == null) {
                    view.getViewState().applyToView(view);
                    return;
                }
                return;
            }
            RowAnimationStateEx rowAnimationStateEx = OplusExpandableViewStateExKt.getRowAnimationStateEx(view);
            if (rowAnimationStateEx != null) {
                ((RowAnimationStateExImpl) rowAnimationStateEx).requestRowScale(KEY_SCALE_FOR_TOUCH, ScaleState.Companion.invoke(1.0f));
            }
            processAnimationToViewOrigin();
        }
    }

    public void resetViewState(ExpandableViewState expandableViewState) {
        MaterialColorMode materialColorMode;
        NotificationBlurMode notificationBlurMode;
        OplusExpandableViewEx oplusExpandableViewEx;
        OplusExpandableViewEx oplusExpandableViewEx2;
        expandableViewState.mExpandableViewStateEx.setClipWidth(0);
        expandableViewState.mExpandableViewStateEx.setBlurRadio(0.0f);
        expandableViewState.mExpandableViewStateEx.setAnimationCornerRadius(0.0f);
        expandableViewState.mExpandableViewStateEx.setNotGoneIndexWithOutChildren(-1);
        expandableViewState.mExpandableViewStateEx.setCapsuleProgress(0.0f);
        expandableViewState.mExpandableViewStateEx.setImmersiveProgress(0.0f);
        expandableViewState.mExpandableViewStateEx.setGroupExpansionProgress(0.0f);
        expandableViewState.mExpandableViewStateEx.setGroupExpandHeaderAlpha(0.0f);
        expandableViewState.mExpandableViewStateEx.setGroupExpandContentAlpha(1.0f);
        ((RowAnimationStateExImpl) expandableViewState.mExpandableViewStateEx.getRowAnimationStateEx()).requestRowScale("key_grouped_notification_expanding", ScaleState.Companion.getINVALID());
        OplusExpandableViewStateEx oplusExpandableViewStateEx = expandableViewState.mExpandableViewStateEx;
        ExpandableView view = getView();
        if (view == null || (oplusExpandableViewEx2 = view.mExpandableViewEx) == null || (materialColorMode = oplusExpandableViewEx2.getMaterialColorMode()) == null) {
            materialColorMode = MaterialColorMode.UNINITIATED;
        }
        oplusExpandableViewStateEx.setMaterialColorMode(materialColorMode);
        OplusExpandableViewStateEx oplusExpandableViewStateEx2 = expandableViewState.mExpandableViewStateEx;
        ExpandableView view2 = getView();
        if (view2 == null || (oplusExpandableViewEx = view2.mExpandableViewEx) == null || (notificationBlurMode = oplusExpandableViewEx.getNotificationBlurMode()) == null) {
            notificationBlurMode = NotificationBlurMode.UNINITIATED;
        }
        oplusExpandableViewStateEx2.setNotificationBlurMode(notificationBlurMode);
    }

    public final Rect scaleClipRectForProxy(float f, float f2, Rect rect) {
        ExpandableView view;
        if ((f == 1.0f && f2 == 1.0f) || (view = getView()) == null) {
            return rect;
        }
        float pivotX = view.getPivotX();
        float pivotY = view.getPivotY();
        this.proxyScaledRect.set((int) DrawerArrowDrawable$.ExternalSyntheticOutline0.m(rect.left, pivotX, f, pivotX), (int) DrawerArrowDrawable$.ExternalSyntheticOutline0.m(rect.top, pivotY, f2, pivotY), (int) DrawerArrowDrawable$.ExternalSyntheticOutline0.m(rect.right, pivotX, f, pivotX), (int) DrawerArrowDrawable$.ExternalSyntheticOutline0.m(rect.bottom, pivotY, f2, pivotY));
        return this.proxyScaledRect;
    }

    public void setActualHeight(int i) {
        this._actualHeight.setValue(Integer.valueOf(i));
        updateContentScaleAndTranslationY(getClipWidth(), i - getActualClipHeight());
    }

    public void setAlpha(float f) {
        OplusExpandableViewProxy proxy = getProxy();
        if (proxy != null) {
            proxy.setAlpha(f);
        }
    }

    public void setAnimationCornerRadius(float f) {
        this.animationCornerRadius = f;
        ExpandableView view = getView();
        if (view == null) {
            view = null;
        }
        if (view == null || (view instanceof CapsuleItemView)) {
            return;
        }
        int cardRadius = CapsuleConfig.INSTANCE.getCardRadius();
        int clipTopAmount = view.getClipTopAmount();
        int actualClipHeight = getActualClipHeight();
        int actualHeight = view.getActualHeight();
        if (actualClipHeight == 0) {
            actualClipHeight = view.getClipBottomAmount();
        }
        int i = actualHeight - actualClipHeight;
        if (i < clipTopAmount) {
            i = clipTopAmount;
        }
        int minimumHeightForClipping = view.getMinimumHeightForClipping();
        if (i < minimumHeightForClipping) {
            i = minimumHeightForClipping;
        }
        int i2 = i - clipTopAmount;
        if (i2 <= 0) {
            i2 = cardRadius * 3;
        }
        float f2 = cardRadius;
        if (f >= f2) {
            f2 = f;
        }
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f2, 0.0f, i2 / 3.0f);
        this.needResetRadius = fCoerceIn < f;
        view.getRoundableState().setMaxRadius(fCoerceIn);
        OplusExpandableViewProxy proxy = getProxy();
        if (proxy != null) {
            proxy.setTopRadius(fCoerceIn);
        }
    }

    public void setBlurRadio(float f) {
        this.blurRadio = f;
        ExpandableView view = getView();
        if ((view == null || view.getVisibility() != 0) && f != 0.0f) {
            return;
        }
        if (Math.abs(f - this.effectedBlurRatio) > 2.0f || f == 0.0f) {
            this.effectedBlurRatio = f;
            if (f <= 2.0f) {
                setBlurView(null);
                return;
            }
            ExpandableView view2 = getView();
            if (view2 != null) {
                View viewAccess$getContextView = Companion.access$getContextView(Companion, view2, false);
                View viewFindViewById = view2.findViewById(2131364390);
                if (viewAccess$getContextView != null) {
                    setupBlurRadio(f, viewAccess$getContextView);
                }
                if (viewFindViewById != null) {
                    setupBlurRadio(f, viewFindViewById);
                }
                Pair<? extends View, ? extends View> pair = this.blurView;
                if (Intrinsics.areEqual(viewAccess$getContextView, pair != null ? (View) pair.getFirst() : null)) {
                    Pair<? extends View, ? extends View> pair2 = this.blurView;
                    if (Intrinsics.areEqual(viewFindViewById, pair2 != null ? (View) pair2.getSecond() : null)) {
                        return;
                    }
                }
                setBlurView(new Pair<>(viewAccess$getContextView, viewFindViewById));
            }
        }
    }

    public void setCapsuleProgress(float f) {
        if (this.capsuleProgress == f) {
            return;
        }
        this.capsuleProgress = f;
        OplusExpandableViewProxy proxy = getProxy();
        if (proxy != null) {
            proxy.setCapsuleProgress(f);
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0043  */
    /* JADX WARN: Code duplicated, block: B:23:0x0049  */
    /* JADX WARN: Code duplicated, block: B:25:0x004d  */
    /* JADX WARN: Code duplicated, block: B:27:0x0055  */
    /* JADX WARN: Code duplicated, block: B:28:0x005a  */
    /* JADX WARN: Code duplicated, block: B:30:0x005e  */
    /* JADX WARN: Code duplicated, block: B:34:0x0068  */
    /* JADX WARN: Code duplicated, block: B:36:0x007b  */
    /* JADX WARN: Code duplicated, block: B:39:0x009c  */
    /* JADX WARN: Code duplicated, block: B:40:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:46:0x00af  */
    public void setClipWidth(int i) {
        ExpandableView view;
        View viewAccess$getContextView;
        float width;
        OplusCustomRow oplusCustomRow;
        OplusCustomRowGuideTipsController guideTipsController;
        if (i < 0) {
            i = 0;
        }
        this.clipWidth = i;
        updateClipping();
        if (Intrinsics.areEqual(getCurrentClipWidthSource(), "clip_width_touch_scale")) {
            ExpandableView view2 = getView();
            int actualHeight = (view2 != null ? view2.getActualHeight() : 1) - getActualClipHeight();
            CustomCardGuideTipsView guideTipsView = null;
            if (i != 0) {
                view = getView();
                if (view != null) {
                    viewAccess$getContextView = this.scaleView;
                    if (viewAccess$getContextView == null) {
                        viewAccess$getContextView = Companion.access$getContextView(Companion, view, true);
                    }
                    if (viewAccess$getContextView == null) {
                        setScaleView(null);
                    } else {
                        if (this.scaleView != viewAccess$getContextView) {
                            setScaleView(viewAccess$getContextView);
                        }
                        if (view.getWidth() > 0) {
                            width = (view.getWidth() - i) / view.getWidth();
                            if (width > 1.0f) {
                                width = 1.0f;
                            }
                            viewAccess$getContextView.setPivotX((view.getWidth() / 2.0f) - viewAccess$getContextView.getTranslationX());
                            viewAccess$getContextView.setPivotY(0.0f);
                            ViewExpandKtKt.setScaleXY(viewAccess$getContextView, width, width);
                            Companion.getClass();
                            if (view instanceof OplusCustomRow) {
                                oplusCustomRow = (OplusCustomRow) view;
                            } else {
                                oplusCustomRow = null;
                            }
                            if (oplusCustomRow != null && (guideTipsController = oplusCustomRow.getGuideTipsController()) != null) {
                                guideTipsView = guideTipsController.getGuideTipsView();
                            }
                            if (guideTipsView != null) {
                                guideTipsView.setPivotX(view.getWidth() / 2.0f);
                                guideTipsView.setPivotY(0.0f);
                                ViewExpandKtKt.setScaleXY(guideTipsView, width, width);
                            }
                        }
                    }
                }
            } else {
                if (getActualClipHeight() != 0) {
                    ExpandableView view3 = getView();
                    if (actualHeight < (view3 != null ? view3.getIntrinsicHeight() : 0)) {
                        view = getView();
                        if (view != null) {
                            viewAccess$getContextView = this.scaleView;
                            if (viewAccess$getContextView == null) {
                                viewAccess$getContextView = Companion.access$getContextView(Companion, view, true);
                            }
                            if (viewAccess$getContextView == null) {
                                setScaleView(null);
                            } else {
                                if (this.scaleView != viewAccess$getContextView) {
                                    setScaleView(viewAccess$getContextView);
                                }
                                if (view.getWidth() > 0) {
                                    width = (view.getWidth() - i) / view.getWidth();
                                    if (width > 1.0f) {
                                        width = 1.0f;
                                    }
                                    viewAccess$getContextView.setPivotX((view.getWidth() / 2.0f) - viewAccess$getContextView.getTranslationX());
                                    viewAccess$getContextView.setPivotY(0.0f);
                                    ViewExpandKtKt.setScaleXY(viewAccess$getContextView, width, width);
                                    Companion.getClass();
                                    if (view instanceof OplusCustomRow) {
                                        oplusCustomRow = (OplusCustomRow) view;
                                    } else {
                                        oplusCustomRow = null;
                                    }
                                    if (oplusCustomRow != null) {
                                        guideTipsView = guideTipsController.getGuideTipsView();
                                    }
                                    if (guideTipsView != null) {
                                        guideTipsView.setPivotX(view.getWidth() / 2.0f);
                                        guideTipsView.setPivotY(0.0f);
                                        ViewExpandKtKt.setScaleXY(guideTipsView, width, width);
                                    }
                                }
                            }
                        }
                    }
                }
                setScaleView(null);
            }
        } else {
            ExpandableView view4 = getView();
            updateContentScaleAndTranslationY(i, (view4 != null ? view4.getActualHeight() : 1) - getActualClipHeight());
        }
        if (this.needResetRadius) {
            ExpandableView view5 = getView();
            if (!(view5 != null ? OplusPhysicsPropertyAnimator.isAnimating(view5, 2131362059) : false)) {
                setAnimationCornerRadius(getAnimationCornerRadius());
            }
        }
        ExpandableView view6 = getView();
        if (view6 != null) {
            view6.invalidate();
        }
    }

    public void setCurrentClipWidthSource(Object obj) {
        if (Intrinsics.areEqual(this.currentClipWidthSource, obj)) {
            return;
        }
        this.currentClipWidthSource = obj;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("set currentClipWidthSource to ", obj, "ExpandableViewExtImpl-->", "SystemUi--Notification");
        }
    }

    public void setGroupExpandContentAlpha(float f) {
        this.groupExpandContentAlpha = f;
    }

    public void setGroupExpandHeaderAlpha(float f) {
        this.groupExpandHeaderAlpha = f;
    }

    public void setGroupExpansionProgress(float f) {
        this.groupExpansionProgress = f;
    }

    public void setHasMetaBall(boolean z) {
        this.metaBallDesired = z;
    }

    public void setImmersiveProgress(float f) {
        if (this.immersiveProgress == f) {
            return;
        }
        this.immersiveProgress = f;
        OplusCustomRow view = getView();
        OplusCustomRow oplusCustomRow = view instanceof OplusCustomRow ? view : null;
        if (oplusCustomRow != null) {
            oplusCustomRow.setImmersiveProgress(f);
        }
        OplusExpandableViewProxy proxy = getProxy();
        if (proxy != null) {
            proxy.setImmersiveProgress(f);
        }
    }

    public void setMaterialColorMode(MaterialColorMode materialColorMode) {
        this.materialColorMode = materialColorMode;
    }

    public void setNotificationBlurMode(NotificationBlurMode notificationBlurMode) {
        this.notificationBlurMode = notificationBlurMode;
    }

    public void setProxy(OplusExpandableViewProxy oplusExpandableViewProxy) {
        this.proxy = oplusExpandableViewProxy;
    }

    public void setTargetClipHeight(int i) {
        this.targetClipHeight = i;
    }

    public void setTargetClipWidth(int i) {
        this.targetClipWidth = i;
    }

    public void setTargetShowHeight(int i) {
        this.targetShowHeight = i;
    }

    public void setTranslationY(float f) {
        OplusExpandableViewProxy proxy = getProxy();
        if (proxy != null) {
            ExpandableView view = getView();
            if (view != null) {
                f += view.getPivotY() * (1.0f - view.getScaleY());
            }
            proxy.setTranslationY(f);
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0043  */
    /* JADX WARN: Code duplicated, block: B:31:0x006b  */
    /* JADX WARN: Code duplicated, block: B:41:0x0097  */
    public void updateClipping() {
        int width;
        int clipWidth;
        Resources resources;
        Configuration configuration;
        Resources resources2;
        Configuration configuration2;
        ExpandableNotificationRow view = getView();
        if (view != null) {
            int clipTopAmount = view.getClipTopAmount();
            int actualClipHeight = getActualClipHeight();
            int actualHeight = view.getActualHeight();
            if (actualClipHeight == 0) {
                actualClipHeight = view.getClipBottomAmount();
            }
            int i = actualHeight - actualClipHeight;
            if (i < clipTopAmount) {
                i = clipTopAmount;
            }
            int minimumHeightForClipping = view.getMinimumHeightForClipping();
            if (i < minimumHeightForClipping) {
                i = minimumHeightForClipping;
            }
            int extraWidthForClipping = (int) (view.getExtraWidthForClipping() / 2.0f);
            if (view instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow = view;
                if (expandableNotificationRow.mIsSummaryWithChildren && expandableNotificationRow.isGroupExpanded()) {
                    clipWidth = Integer.MIN_VALUE;
                    width = Integer.MAX_VALUE;
                } else {
                    if (Intrinsics.areEqual(getCurrentClipWidthSource(), "clip_width_touch_scale") || !((resources2 = this.context.getResources()) == null || (configuration2 = resources2.getConfiguration()) == null || configuration2.getLayoutDirection() != 1)) {
                        width = (view.getWidth() + extraWidthForClipping) - (getClipWidth() / 2);
                    } else {
                        width = view.getWidth() + extraWidthForClipping;
                    }
                    if (Intrinsics.areEqual(getCurrentClipWidthSource(), "clip_width_touch_scale") || (resources = this.context.getResources()) == null || (configuration = resources.getConfiguration()) == null || configuration.getLayoutDirection() != 1) {
                        clipWidth = (getClipWidth() / 2) - extraWidthForClipping;
                    } else {
                        clipWidth = 0;
                    }
                }
            } else {
                if (Intrinsics.areEqual(getCurrentClipWidthSource(), "clip_width_touch_scale")) {
                    width = (view.getWidth() + extraWidthForClipping) - (getClipWidth() / 2);
                } else {
                    width = (view.getWidth() + extraWidthForClipping) - (getClipWidth() / 2);
                }
                if (Intrinsics.areEqual(getCurrentClipWidthSource(), "clip_width_touch_scale")) {
                    clipWidth = (getClipWidth() / 2) - extraWidthForClipping;
                } else {
                    clipWidth = (getClipWidth() / 2) - extraWidthForClipping;
                }
            }
            if (i == clipTopAmount && view.getClipBounds() != null && view.getClipRect().bottom == view.getClipRect().top) {
                view.getClipRect().set(clipWidth, clipTopAmount, width, i);
                OplusExpandableViewProxy proxy = getProxy();
                if (proxy != null) {
                    proxy.setClipRect(scaleClipRectForProxy(view.getScaleX(), view.getScaleY(), view.getClipRect()));
                    return;
                }
                return;
            }
            view.getClipRect().set(clipWidth, clipTopAmount, width, i);
            OplusExpandableViewProxy proxy2 = getProxy();
            if (proxy2 != null) {
                proxy2.setClipRect(scaleClipRectForProxy(view.getScaleX(), view.getScaleY(), view.getClipRect()));
            }
            view.setClipBounds(view.getClipRect());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0014, code lost:
    
        if (r7 >= (r0 != null ? r0.getIntrinsicHeight() : 0)) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateContentScaleAndTranslationY(int r6, int r7) {
        /*
            r5 = this;
            if (r6 != 0) goto L1b
            int r0 = r5.getActualClipHeight()
            if (r0 == 0) goto L16
            com.android.systemui.statusbar.notification.row.ExpandableView r0 = r5.getView()
            if (r0 == 0) goto L13
            int r0 = r0.getIntrinsicHeight()
            goto L14
        L13:
            r0 = 0
        L14:
            if (r7 < r0) goto L1b
        L16:
            r6 = 0
            r5.setScaleView(r6)
            return
        L1b:
            java.lang.Object r7 = r5.getCurrentClipWidthSource()
            java.lang.String r0 = "clip_width_touch_scale"
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r0)
            java.lang.String r0 = "SystemUi--Notification"
            java.lang.String r1 = "Notification"
            if (r7 == 0) goto L3d
            boolean r5 = com.oplusos.systemui.common.util.LogUtil.getNormalDebug()
            if (r5 == 0) goto Lc7
            boolean r5 = com.oplusos.systemui.common.util.LogUtil.isTagValid(r1)
            if (r5 == 0) goto Lc7
            java.lang.String r5 = "ExpandableViewExtImpl-->updateContentScaleAndTranslationY return"
            android.util.Log.d(r0, r5)
            return
        L3d:
            com.android.systemui.statusbar.notification.row.ExpandableView r7 = r5.getView()
            if (r7 == 0) goto Lc7
            com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl$Companion r2 = com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl.Companion
            r3 = 1
            android.view.View r2 = com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl.Companion.access$getContextView(r2, r7, r3)
            r5.setScaleView(r2)
            if (r2 != 0) goto L51
            goto Lc7
        L51:
            int r3 = r7.getIntrinsicHeight()
            if (r3 > 0) goto L70
            boolean r5 = com.oplusos.systemui.common.util.LogUtil.getNormalDebug()
            if (r5 == 0) goto Lc7
            boolean r5 = com.oplusos.systemui.common.util.LogUtil.isTagValid(r1)
            if (r5 == 0) goto Lc7
            java.lang.String r5 = com.oplus.systemui.notification.lockscreen.base.LockScreenNotificationKotlinExKt.getLogKey(r7)
            java.lang.String r6 = "updateContentScaleAndTranslationY intrinsicHeight=0, key="
            java.lang.String r7 = "ExpandableViewExtImpl-->"
            com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(r6, r5, r7, r0)
            return
        L70:
            int r0 = r7.getWidth()
            float r0 = (float) r0
            float r6 = (float) r6
            float r0 = r0 - r6
            int r6 = r7.getWidth()
            float r6 = (float) r6
            float r0 = r0 / r6
            r6 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r1 <= 0) goto L84
            r0 = r6
        L84:
            int r6 = r2.getId()
            r1 = 2131364391(0x7f0a0a27, float:1.8348618E38)
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = 0
            if (r6 != r1) goto L94
            r2.setPivotY(r4)
            goto Lbb
        L94:
            int r6 = r5.getTargetShowHeight()
            if (r6 <= 0) goto La2
            boolean r6 = r5.canUseTargetShowHeight()
            if (r6 == 0) goto La2
            r5 = r4
            goto Lb5
        La2:
            int r6 = r7.getActualHeight()
            int r5 = r5.getActualClipHeight()
            int r6 = r6 - r5
            float r5 = (float) r6
            int r6 = r7.getIntrinsicHeight()
            float r6 = (float) r6
            float r5 = androidx.compose.material3.carousel.KeylinesKt$.ExternalSyntheticOutline0.m(r6, r0, r5, r3)
        Lb5:
            r2.setTranslationY(r5)
            r2.setPivotY(r4)
        Lbb:
            int r5 = r7.getWidth()
            float r5 = (float) r5
            float r5 = r5 / r3
            r2.setPivotX(r5)
            com.oplus.systemui.notification.lockscreen.capsule.utils.ViewExpandKtKt.setScaleXY(r2, r0, r0)
        Lc7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl.updateContentScaleAndTranslationY(int, int):void");
    }

    public ExpandableView getView() {
        return this.view;
    }

    public void setView(ExpandableView expandableView) {
        this.view = expandableView;
    }
}
