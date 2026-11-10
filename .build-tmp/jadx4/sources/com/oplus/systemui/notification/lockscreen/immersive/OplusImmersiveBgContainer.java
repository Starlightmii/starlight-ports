package com.oplus.systemui.notification.lockscreen.immersive;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.foundation.shape.DpCornerSize$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.keyguard.clockstyle.IKeyguardStyleClockController;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AnimationFilter;
import com.android.systemui.statusbar.notification.stack.ui.view.SharedNotificationContainer;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.util.SeparateModeHelper;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.aod.display.AODDisplayUtil;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.notification.lockscreen.capsule.utils.ViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.seedling.ImmersiveViewEntry;
import com.oplus.systemui.notification.lockscreen.seedling.LockScreenSeedingInteractor;
import com.oplus.systemui.notification.lockscreen.seedling.SeedlingImmersiveViewState;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.util.LogUtil;
import io.grpc.Status$;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusImmersiveBgContainer extends FrameLayout {
    private static final String ACTION_VIEW_ADDED = "added";
    private static final String ACTION_VIEW_REMOVED = "removed";
    public static final Companion Companion = new Companion();
    private static final String TAG = "ImmersiveBg";
    private final ImmersiveBgAmbientState ambientState;
    private final AnimationFilter animationFilter;
    private final OplusImmersiveBgContainer$animationProperties$1 animationProperties;
    private ImmersiveBgEntry animationRemovingEntry;
    private ImmersiveBgEntry animationShowingEntry;
    public boolean animationsEnabled;
    private final HashSet<Animator> animatorSet;
    private final AodScaleData aodScaleDate;
    public CapsuleCardControl capsuleCardControl;
    public boolean childrenUpdateRequested;
    private final ViewTreeObserver.OnPreDrawListener childrenUpdater;
    public ImmersiveCardControl immersiveCardControl;
    public IKeyguardStyleClockController keyguardStyleClockController;
    public ILockScreenExtStateController lockScreenExtStateController;
    public int logImmersiveCardBottomMargin;
    public int logImmersiveClockBottom;
    public int measureStackViewHeight;
    public boolean needsAnimation;
    private final List<Function0> onAnimationFinishedListeners;
    public ScrimController scrimController;
    public SharedNotificationContainer sharedNotificationContainer;
    public boolean startAnimationToStateBarrier;
    private OplusImmersiveTouchHandler touchHandler;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AodScaleData {
        public float scale;
        public float targetPivotX;
        public float targetPivotY;

        public AodScaleData(float f, float f2, float f3) {
            this.scale = f;
            this.targetPivotX = f2;
            this.targetPivotY = f3;
        }

        public final AodScaleData copy(float f, float f2, float f3) {
            return new AodScaleData(f, f2, f3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AodScaleData)) {
                return false;
            }
            AodScaleData aodScaleData = (AodScaleData) obj;
            return Float.compare(this.scale, aodScaleData.scale) == 0 && Float.compare(this.targetPivotX, aodScaleData.targetPivotX) == 0 && Float.compare(this.targetPivotY, aodScaleData.targetPivotY) == 0;
        }

        public final float getScale() {
            return this.scale;
        }

        public final float getTargetPivotX() {
            return this.targetPivotX;
        }

        public final float getTargetPivotY() {
            return this.targetPivotY;
        }

        public final int hashCode() {
            return Float.hashCode(this.targetPivotY) + ChangeSize$.ExternalSyntheticOutline0.m(this.targetPivotX, Float.hashCode(this.scale) * 31, 31);
        }

        public final void setScale(float f) {
            this.scale = f;
        }

        public final void setTargetPivotX(float f) {
            this.targetPivotX = f;
        }

        public final void setTargetPivotY(float f) {
            this.targetPivotY = f;
        }

        public String toString() {
            return DpCornerSize$.ExternalSyntheticOutline0.m(this.targetPivotY, ")", MonoSpline$.ExternalSyntheticOutline0.m("AodScaleData(scale=", this.scale, ", targetPivotX=", this.targetPivotX, ", targetPivotY="));
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final Rect getBounds(ExpandableView expandableView, int i) {
            return new Rect(i, Math.max(0, expandableView.getClipTopAmount()) + ((int) expandableView.getTranslationY()), expandableView.getWidth() + i, (expandableView.getActualHeight() + ((int) expandableView.getTranslationY())) - expandableView.getClipBottomAmount());
        }

        public final Rect getStateBounds(ExpandableView expandableView, int i) {
            return new Rect(i, Math.max(0, expandableView.getViewState().clipTopAmount) + ((int) expandableView.getViewState().getYTranslation()), expandableView.getWidth() + i, (((int) expandableView.getViewState().getYTranslation()) + expandableView.getViewState().height) - expandableView.getViewState().clipBottomAmount);
        }
    }

    public OplusImmersiveBgContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setImportantForAccessibility(2);
        this.ambientState = new ImmersiveBgAmbientState(null, null, new HashSet());
        this.animationFilter = new AnimationFilter();
        this.animatorSet = new HashSet<>();
        OplusImmersiveBgContainer$childrenUpdater$1 oplusImmersiveBgContainer$childrenUpdater$1 = new OplusImmersiveBgContainer$childrenUpdater$1();
        oplusImmersiveBgContainer$childrenUpdater$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.childrenUpdater = oplusImmersiveBgContainer$childrenUpdater$1;
        this.onAnimationFinishedListeners = new ArrayList();
        OplusImmersiveBgContainer$animationProperties$1 oplusImmersiveBgContainer$animationProperties$1 = new OplusImmersiveBgContainer$animationProperties$1();
        oplusImmersiveBgContainer$animationProperties$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.animationProperties = oplusImmersiveBgContainer$animationProperties$1;
        this.animationsEnabled = true;
        this.aodScaleDate = new AodScaleData(1.0f, 0.0f, 0.0f);
    }

    public static final void access$updateChildren(OplusImmersiveBgContainer oplusImmersiveBgContainer) {
        Rect rect;
        Rect rect2;
        Trace.beginSection("ImmersiveBg#updateChildren");
        ImmersiveBgEntry immersiveBgEntry = oplusImmersiveBgContainer.animationRemovingEntry;
        if (immersiveBgEntry != null) {
            oplusImmersiveBgContainer.ambientState.getRemovingEntrySet().add(immersiveBgEntry);
        }
        ImmersiveBgEntry showingEntry = oplusImmersiveBgContainer.ambientState.getShowingEntry();
        if (showingEntry != null) {
            showingEntry.getViewState().setImmersiveState(1);
            showingEntry.getViewState().setToExtState(2);
            ILockScreenExtStateController.Companion companion = ILockScreenExtStateController.Companion;
            int oldState = ((ILockScreenExtStateController.ExtStateEvent) oplusImmersiveBgContainer.getLockScreenExtStateController().getExtStateEvent().getValue()).getOldState();
            companion.getClass();
            if (oldState == 0 || oldState == 3) {
                showingEntry.getViewState().setFromExtState(((ILockScreenExtStateController.ExtStateEvent) oplusImmersiveBgContainer.getLockScreenExtStateController().getExtStateEvent().getValue()).getOldState());
            } else {
                showingEntry.getViewState().setFromExtState(1);
            }
            showingEntry.getViewState().setReason(((ILockScreenExtStateController.ExtStateEvent) oplusImmersiveBgContainer.getLockScreenExtStateController().getExtStateEvent().getValue()).getReason());
        }
        for (ImmersiveBgEntry immersiveBgEntry2 : oplusImmersiveBgContainer.ambientState.getRemovingEntrySet()) {
            immersiveBgEntry2.getViewState().setImmersiveState(0);
            immersiveBgEntry2.getViewState().setFromExtState(2);
            if (oplusImmersiveBgContainer.getLockScreenExtStateController().isStackState()) {
                immersiveBgEntry2.getViewState().setToExtState(((ILockScreenExtStateController.ExtStateEvent) oplusImmersiveBgContainer.getLockScreenExtStateController().getExtStateEvent().getValue()).getNewState());
            } else {
                immersiveBgEntry2.getViewState().setToExtState(1);
            }
            immersiveBgEntry2.getViewState().setReason(((ILockScreenExtStateController.ExtStateEvent) oplusImmersiveBgContainer.getLockScreenExtStateController().getExtStateEvent().getValue()).getReason());
        }
        if (oplusImmersiveBgContainer.needsAnimation || oplusImmersiveBgContainer.isCurrentlyAnimating()) {
            oplusImmersiveBgContainer.startAnimationToStateBarrier = true;
            oplusImmersiveBgContainer.needsAnimation = false;
            ImmersiveBgEntry immersiveBgEntry3 = oplusImmersiveBgContainer.animationShowingEntry;
            if (immersiveBgEntry3 != null) {
                ILockScreenExtStateController.Companion companion2 = ILockScreenExtStateController.Companion;
                int lastExtState = oplusImmersiveBgContainer.getLockScreenExtStateController().getLastExtState();
                companion2.getClass();
                if (lastExtState == 0 || lastExtState == 3) {
                    ExpandableView stackView = immersiveBgEntry3.getStackView();
                    if (stackView == null || (rect2 = Companion.getBounds(stackView, oplusImmersiveBgContainer.getSharedNotificationContainer().getLeft())) == null) {
                        rect2 = new Rect(0, 0, 0, 0);
                    }
                } else {
                    rect2 = null;
                }
                immersiveBgEntry3.getViewState().animateTo(immersiveBgEntry3, oplusImmersiveBgContainer.animationProperties, rect2, oplusImmersiveBgContainer.getCapsuleCardControl().getCapsulePosition());
                oplusImmersiveBgContainer.animationShowingEntry = null;
            }
            ImmersiveBgEntry immersiveBgEntry4 = oplusImmersiveBgContainer.animationRemovingEntry;
            if (immersiveBgEntry4 != null) {
                if (oplusImmersiveBgContainer.getLockScreenExtStateController().isStackState()) {
                    ExpandableView stackView2 = immersiveBgEntry4.getStackView();
                    if (stackView2 == null || (rect = Companion.getStateBounds(stackView2, oplusImmersiveBgContainer.getSharedNotificationContainer().getLeft())) == null) {
                        rect = new Rect(0, 0, 0, 0);
                    }
                } else {
                    rect = null;
                }
                immersiveBgEntry4.getViewState().animateTo(immersiveBgEntry4, oplusImmersiveBgContainer.animationProperties, rect, oplusImmersiveBgContainer.getCapsuleCardControl().getCapsulePosition());
                oplusImmersiveBgContainer.ambientState.setRemovingEntry(null);
                oplusImmersiveBgContainer.animationRemovingEntry = null;
            }
            oplusImmersiveBgContainer.startAnimationToStateBarrier = false;
            if (!oplusImmersiveBgContainer.isCurrentlyAnimating()) {
                oplusImmersiveBgContainer.onAnimationFinished();
            }
        } else {
            oplusImmersiveBgContainer.animationShowingEntry = null;
            oplusImmersiveBgContainer.animationRemovingEntry = null;
            oplusImmersiveBgContainer.ambientState.setRemovingEntry(null);
            ImmersiveBgEntry showingEntry2 = oplusImmersiveBgContainer.ambientState.getShowingEntry();
            if (showingEntry2 != null) {
                showingEntry2.getViewState().applyTo(showingEntry2);
            }
            for (ImmersiveViewEntry immersiveViewEntry : oplusImmersiveBgContainer.ambientState.getRemovingEntrySet()) {
                immersiveViewEntry.getViewState().applyTo(immersiveViewEntry);
            }
        }
        Trace.endSection();
    }

    private final int getImmersiveCardBottomMargin() {
        if (this.immersiveCardControl == null) {
            setLogImmersiveCardBottomMargin(0);
            return 0;
        }
        int immersiveCardBottomMargin = getImmersiveCardControl().getImmersiveCardBottomMargin();
        setLogImmersiveCardBottomMargin(immersiveCardBottomMargin);
        return immersiveCardBottomMargin;
    }

    private final int getImmersiveClockBottom() {
        if (this.keyguardStyleClockController == null) {
            setLogImmersiveClockBottom(0);
            return 0;
        }
        int styleClockBottomPositionImmersed = getKeyguardStyleClockController().getStyleClockBottomPositionImmersed();
        setLogImmersiveClockBottom(styleClockBottomPositionImmersed);
        return styleClockBottomPositionImmersed;
    }

    private final void setLogImmersiveCardBottomMargin(int i) {
        if (this.logImmersiveCardBottomMargin != i) {
            this.logImmersiveCardBottomMargin = i;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "logImmersiveCardBottomMargin:", "ImmersiveBg-->", "SystemUi--Notification");
            }
        }
    }

    private final void setLogImmersiveClockBottom(int i) {
        if (this.logImmersiveClockBottom != i) {
            this.logImmersiveClockBottom = i;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "logImmersiveClockBottom:", "ImmersiveBg-->", "SystemUi--Notification");
            }
        }
    }

    public final void addOnAnimationFinishedListener(Function0 function0) {
        if (function0 == null) {
            return;
        }
        this.onAnimationFinishedListeners.add(function0);
    }

    public final void checkIfNeedReLayout(String str) {
        ExpandableView stackView;
        if (this.measureStackViewHeight == 0) {
            ImmersiveBgEntry showingEntry = this.ambientState.getShowingEntry();
            if (((showingEntry == null || (stackView = showingEntry.getStackView()) == null) ? 0 : stackView.getMeasuredHeight()) > 0) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    KeyguardSettingProvider$.ExternalSyntheticOutline0.m("notifyCapsuleLayoutChanged:measureStackViewHeight=0, reason=", str, "ImmersiveBg-->", "SystemUi--Notification");
                }
                OplusImmersiveBgContainer$$ExternalSyntheticLambda0 oplusImmersiveBgContainer$$ExternalSyntheticLambda0 = new OplusImmersiveBgContainer$$ExternalSyntheticLambda0();
                oplusImmersiveBgContainer$$ExternalSyntheticLambda0.f$0 = this;
                VarHandle.storeStoreFence();
                ViewExpandKtKt.addOnPreDraw(this, oplusImmersiveBgContainer$$ExternalSyntheticLambda0);
            }
        }
    }

    public final boolean getAnimationsEnabled() {
        return this.animationsEnabled;
    }

    public final CapsuleCardControl getCapsuleCardControl() {
        CapsuleCardControl capsuleCardControl = this.capsuleCardControl;
        if (capsuleCardControl != null) {
            return capsuleCardControl;
        }
        return null;
    }

    public final ImmersiveCardControl getImmersiveCardControl() {
        return this.immersiveCardControl;
    }

    public final IKeyguardStyleClockController getKeyguardStyleClockController() {
        return this.keyguardStyleClockController;
    }

    public final ILockScreenExtStateController getLockScreenExtStateController() {
        ILockScreenExtStateController iLockScreenExtStateController = this.lockScreenExtStateController;
        if (iLockScreenExtStateController != null) {
            return iLockScreenExtStateController;
        }
        return null;
    }

    public final ScrimController getScrimController() {
        ScrimController scrimController = this.scrimController;
        if (scrimController != null) {
            return scrimController;
        }
        return null;
    }

    public final SharedNotificationContainer getSharedNotificationContainer() {
        SharedNotificationContainer sharedNotificationContainer = this.sharedNotificationContainer;
        if (sharedNotificationContainer != null) {
            return sharedNotificationContainer;
        }
        return null;
    }

    public final OplusImmersiveTouchHandler getTouchHandler() {
        return this.touchHandler;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    public final void hideCurrent(boolean z) {
        ImmersiveBgEntry showingEntry = this.ambientState.getShowingEntry();
        if (showingEntry == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ImmersiveBg-->hideCurrent: no showingEntry");
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("hideCurrent:", showingEntry.getKey(), "ImmersiveBg-->", "SystemUi--Notification");
        }
        this.ambientState.setShowingEntry(null);
        if (!this.animationsEnabled || !z || getImmersiveCardControl().isRemoved(showingEntry.getKey())) {
            removeEntry(showingEntry, "hideEntry no animation");
            requestChildrenUpdate();
        } else {
            this.animationRemovingEntry = showingEntry;
            this.ambientState.setRemovingEntry(showingEntry);
            this.needsAnimation = true;
            requestChildrenUpdate();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
        onChildInvalidated();
        return super.invalidateChildInParent(iArr, rect);
    }

    public final boolean isCurrentlyAnimating() {
        return !this.animatorSet.isEmpty();
    }

    public final boolean isSurfaceBg() {
        ImmersiveBgEntry showingEntry = this.ambientState.getShowingEntry();
        return showingEntry != null && showingEntry.isViewSurfaceType();
    }

    public final void notifyCenterRect() {
        Rect rect;
        ImmersiveBgEntry showingEntry = this.ambientState.getShowingEntry();
        if (showingEntry != null) {
            OplusCustomRow stackView = showingEntry.getStackView();
            OplusCustomRow oplusCustomRow = stackView instanceof OplusCustomRow ? stackView : null;
            int contentLayoutMeasuredHeight = oplusCustomRow != null ? oplusCustomRow.getContentLayoutMeasuredHeight() : 0;
            if (!showingEntry.isViewSurfaceType() || showingEntry.getStackView() == null) {
                rect = new Rect(0, this.logImmersiveClockBottom, getMeasuredWidth(), (getMeasuredHeight() - this.logImmersiveCardBottomMargin) - contentLayoutMeasuredHeight);
            } else {
                int measuredWidth = (getMeasuredWidth() - showingEntry.getStackView().getMeasuredWidth()) / 2;
                rect = new Rect(measuredWidth, this.logImmersiveClockBottom, showingEntry.getStackView().getMeasuredWidth() + measuredWidth, (getMeasuredHeight() - this.logImmersiveCardBottomMargin) - contentLayoutMeasuredHeight);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ImmersiveBg-->".concat("notifyCenterRect::" + showingEntry.getKey() + ":" + rect));
            }
            updateScaleWithPivotView();
            if (Intrinsics.areEqual(showingEntry.getSendBounds(), rect)) {
                return;
            }
            showingEntry.setSendBounds(rect);
            showingEntry.getEntry().updateContentRect(rect);
            LockScreenSeedingInteractor.Companion.updateContentRectForBg(showingEntry.getEntry(), rect);
        }
    }

    public final void notifyHideSurfaceView() {
        ImmersiveBgEntry showingEntry = this.ambientState.getShowingEntry();
        if (showingEntry == null || !showingEntry.isViewSurfaceType()) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "ImmersiveBg-->notifyHideSurfaceView");
        }
        LockScreenSeedingInteractor.Companion.hideSurface(showingEntry.getEntry());
    }

    public final void notifyImmersiveClockBottomChanged(int i) {
        if (i == this.logImmersiveClockBottom || this.ambientState.getShowingEntry() == null) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("notifyImmersiveClockBottomChanged:", this.logImmersiveClockBottom, i, "->", "ImmersiveBg-->"));
        }
        requestLayout();
    }

    public final void onAnimationFinished() {
        if (this.startAnimationToStateBarrier) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.ambientState.getRemovingEntrySet().size(), "onAnimationFinished: deferred while startAnimationToStateBarrier, removing=", "ImmersiveBg-->", "SystemUi--Notification");
                return;
            }
            return;
        }
        ImmersiveBgAmbientState immersiveBgAmbientState = this.ambientState;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(immersiveBgAmbientState.getRemovingEntrySet().size(), "onAnimationFinished:", "ImmersiveBg-->", "SystemUi--Notification");
        }
        Iterator<ImmersiveBgEntry> it = immersiveBgAmbientState.getRemovingEntrySet().iterator();
        while (it.hasNext()) {
            ImmersiveBgEntry next = it.next();
            ViewExpandKtKt.setScaleXY(next.getView(), 1.0f, 1.0f);
            next.setSendBounds(null);
            if (!next.isViewSurfaceType() || getImmersiveCardControl().needRemoveSurfaceViewBg() || getImmersiveCardControl().isRemoved(next.getKey())) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onAnimationFinished: removeView ", next.getKey(), "ImmersiveBg-->", "SystemUi--Notification");
                }
                removeView(next.getView());
                it.remove();
            } else {
                next.getView().setVisibility(4);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("onAnimationFinished:", next.getKey(), " because of surface view bg", "ImmersiveBg-->", "SystemUi--Notification");
                }
            }
        }
        Iterator<T> it2 = this.onAnimationFinishedListeners.iterator();
        while (it2.hasNext()) {
            ((Function0) it2.next()).invoke();
        }
    }

    public final void onChildInvalidated() {
        if (getVisibility() == 0 && AODDisplayUtil.getInstance(getContext()).isPanoramicProcessType()) {
            SmoothTransitionController.Companion.getInstance(getContext()).requestScreenStateForSysUiComponent(TAG, 500);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onDescendantInvalidated(View view, View view2) {
        onChildInvalidated();
        super.onDescendantInvalidated(view, view2);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        OplusImmersiveTouchHandler oplusImmersiveTouchHandler = this.touchHandler;
        if (oplusImmersiveTouchHandler != null) {
            return oplusImmersiveTouchHandler.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            ImmersiveBgAmbientState immersiveBgAmbientState = this.ambientState;
            childAt.getClass();
            ImmersiveBgEntry immersiveBgEntryFindByView = immersiveBgAmbientState.findByView(childAt);
            int i6 = (immersiveBgEntryFindByView == null || immersiveBgEntryFindByView.cardType != 2) ? 0 : this.logImmersiveClockBottom;
            childAt.layout(0, i6, childAt.getMeasuredWidth(), childAt.getMeasuredHeight() + i6);
        }
        notifyCenterRect();
        checkIfNeedReLayout("onLayout");
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        ExpandableView stackView;
        super.onMeasure(i, i2);
        List<ImmersiveBgEntry> allSizeNormalEntries = this.ambientState.getAllSizeNormalEntries();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MobileInputLogger$.ExternalSyntheticOutline0.m(allSizeNormalEntries.size(), "ImmersiveBg-->", "SystemUi--Notification", FloatList$.ExternalSyntheticOutline0.m("onMeasure normal:", getImmersiveCardBottomMargin(), getImmersiveClockBottom(), " - ", ",s="));
        }
        ImmersiveBgEntry showingEntry = this.ambientState.getShowingEntry();
        this.measureStackViewHeight = (showingEntry == null || (stackView = showingEntry.getStackView()) == null) ? 0 : stackView.getMeasuredHeight();
        for (ImmersiveBgEntry immersiveBgEntry : allSizeNormalEntries) {
            OplusCustomRow stackView2 = immersiveBgEntry.getStackView();
            if (stackView2 == null) {
                LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, Bundler$.ExternalSyntheticOutline0.m("onMeasure ", immersiveBgEntry.getKey(), " stack view =null"));
            } else {
                int measuredHeight = getMeasuredHeight() - getImmersiveCardBottomMargin();
                OplusCustomRow oplusCustomRow = stackView2 instanceof OplusCustomRow ? stackView2 : null;
                int contentLayoutMeasuredHeight = (measuredHeight - (oplusCustomRow != null ? oplusCustomRow.getContentLayoutMeasuredHeight() : 0)) - getImmersiveClockBottom();
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(contentLayoutMeasuredHeight, 1073741824);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    MobileInputLogger$.ExternalSyntheticOutline0.m(getImmersiveClockBottom(), "ImmersiveBg-->", "SystemUi--Notification", ConstraintSet$.ExternalSyntheticOutline0.m(contentLayoutMeasuredHeight, "onMeasure ", immersiveBgEntry.getKey(), " h:", ",t:"));
                }
                immersiveBgEntry.getView().measure(i, iMakeMeasureSpec);
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        OplusImmersiveTouchHandler oplusImmersiveTouchHandler = this.touchHandler;
        if (oplusImmersiveTouchHandler != null) {
            return oplusImmersiveTouchHandler.onTouchEvent(motionEvent);
        }
        return false;
    }

    public final void removeEntry(ImmersiveBgEntry immersiveBgEntry, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("entry:", immersiveBgEntry.getKey(), ",action:removed,reason:", str, "ImmersiveBg-->"));
        }
        immersiveBgEntry.getViewState().setImmersiveState(0);
        immersiveBgEntry.getViewState().cancelAnimations(immersiveBgEntry);
        ViewExpandKtKt.setScaleXY(immersiveBgEntry.getView(), 1.0f, 1.0f);
        immersiveBgEntry.setSendBounds(null);
        this.ambientState.getRemovingEntrySet().remove(immersiveBgEntry);
        removeView(immersiveBgEntry.getView());
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "ImmersiveBg-->".concat("removeEntry:after:" + immersiveBgEntry.getKey() + ",parent=" + immersiveBgEntry.getView().getParent() + ",attached=" + immersiveBgEntry.getView().isAttachedToWindow() + ",viewId=" + System.identityHashCode(immersiveBgEntry.getView())));
        }
    }

    public final void requestChildrenUpdate() {
        if (this.childrenUpdateRequested) {
            return;
        }
        getViewTreeObserver().addOnPreDrawListener(this.childrenUpdater);
        this.childrenUpdateRequested = true;
        invalidate();
    }

    @Override // android.view.View
    public void setAlpha(float f) {
        super.setAlpha(f);
        ImmersiveBgEntry showingEntry = this.ambientState.getShowingEntry();
        if (showingEntry == null || !showingEntry.isViewSurfaceType()) {
            return;
        }
        if (!SeparateModeHelper.INSTANCE.getSeparateMode() && getScrimController().getQsExpansion() > 0.0f) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ImmersiveBg-->no updateSurfaceViewAlpha return.");
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "ImmersiveBg-->".concat("updateSurfaceViewAlpha::" + showingEntry.getKey() + ":" + f));
        }
        LockScreenSeedingInteractor.Companion.updateSurfaceViewAlpha(showingEntry.getEntry(), f);
    }

    public final void setAnimationsEnabled(boolean z) {
        this.animationsEnabled = z;
        if (z) {
            return;
        }
        for (ImmersiveBgEntry immersiveBgEntry : new HashSet(this.ambientState.getRemovingEntrySet())) {
            immersiveBgEntry.getClass();
            removeEntry(immersiveBgEntry, "removeNoShowingView");
        }
        requestChildrenUpdate();
    }

    public final void setCapsuleCardControl(CapsuleCardControl capsuleCardControl) {
        this.capsuleCardControl = capsuleCardControl;
    }

    public final void setImmersiveCardControl(ImmersiveCardControl immersiveCardControl) {
        this.immersiveCardControl = immersiveCardControl;
    }

    public final void setKeyguardStyleClockController(IKeyguardStyleClockController iKeyguardStyleClockController) {
        this.keyguardStyleClockController = iKeyguardStyleClockController;
    }

    public final void setLockScreenExtStateController(ILockScreenExtStateController iLockScreenExtStateController) {
        this.lockScreenExtStateController = iLockScreenExtStateController;
    }

    public final void setScrimController(ScrimController scrimController) {
        this.scrimController = scrimController;
    }

    public final void setSharedNotificationContainer(SharedNotificationContainer sharedNotificationContainer) {
        this.sharedNotificationContainer = sharedNotificationContainer;
    }

    public final void setTouchHandler(OplusImmersiveTouchHandler oplusImmersiveTouchHandler) {
        this.touchHandler = oplusImmersiveTouchHandler;
    }

    public final void showView(View view, CustomRowEntry customRowEntry, ExpandableView expandableView, boolean z) {
        ImmersiveBgEntry showingEntry = this.ambientState.getShowingEntry();
        if (Intrinsics.areEqual(customRowEntry, showingEntry != null ? showingEntry.getEntry() : null)) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "showView: same showingEntry is showing");
            return;
        }
        if (this.ambientState.getShowingEntry() != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ImmersiveBg-->showView: hideCurrent");
            }
            hideCurrent(z);
        }
        ImmersiveBgEntry immersiveBgEntryFindByView = this.ambientState.findByView(view);
        if (immersiveBgEntryFindByView == null) {
            immersiveBgEntryFindByView = new ImmersiveBgEntry(view, customRowEntry, new SeedlingImmersiveViewState(3), expandableView, null);
        }
        boolean zRemove = this.ambientState.getRemovingEntrySet().remove(immersiveBgEntryFindByView);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            String key = immersiveBgEntryFindByView.getKey();
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(OplusPreventModeData.SEPARATOR, "ImmersiveBg-->", KeyguardLogger$.ExternalSyntheticOutline0.m("showEntry:", key, ",is old=", zRemove, OplusPreventModeData.SEPARATOR), immersiveBgEntryFindByView.cardType == 2, immersiveBgEntryFindByView.isViewSurfaceType()));
        }
        if (Intrinsics.areEqual(immersiveBgEntryFindByView.getView().getParent(), this) && !immersiveBgEntryFindByView.getView().isAttachedToWindow() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("showEntry:", immersiveBgEntryFindByView.getKey(), ",stale parent without window attach,re-adding", "ImmersiveBg-->", "SystemUi--Notification");
        }
        if (immersiveBgEntryFindByView.getView().getParent() == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("showEntry:", immersiveBgEntryFindByView.getKey(), ",action:added", "ImmersiveBg-->", "SystemUi--Notification");
            }
            if (immersiveBgEntryFindByView.getView().getVisibility() != 0) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("showEntry:", immersiveBgEntryFindByView.getKey(), ",reset visibility", "ImmersiveBg-->", "SystemUi--Notification");
                }
                immersiveBgEntryFindByView.getView().setVisibility(0);
            }
            this.ambientState.setShowingEntry(immersiveBgEntryFindByView);
            if (immersiveBgEntryFindByView.isViewSurfaceType()) {
                addView(immersiveBgEntryFindByView.getView(), 0);
            } else {
                addView(immersiveBgEntryFindByView.getView());
            }
        } else {
            if (!Intrinsics.areEqual(immersiveBgEntryFindByView.getView().getParent(), this)) {
                Status$.ExternalSyntheticBUOutline0.m("View ", immersiveBgEntryFindByView.getKey(), " already has a other parent=", immersiveBgEntryFindByView.getView().getParent());
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                String key2 = immersiveBgEntryFindByView.getKey();
                int immersiveState = immersiveBgEntryFindByView.getImmersiveState();
                boolean zIsAttachedToWindow = immersiveBgEntryFindByView.getView().isAttachedToWindow();
                int iIdentityHashCode = System.identityHashCode(immersiveBgEntryFindByView.getView());
                StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(immersiveState, "showEntry:", key2, ",state=", ",attached=");
                sbM.append(zIsAttachedToWindow);
                sbM.append(",viewId=");
                sbM.append(iIdentityHashCode);
                Log.d("SystemUi--Notification", "ImmersiveBg-->".concat(sbM.toString()));
            }
            immersiveBgEntryFindByView.getView().setVisibility(0);
            immersiveBgEntryFindByView.getViewState().cancelAnimations(immersiveBgEntryFindByView);
            immersiveBgEntryFindByView.getView().requestLayout();
            this.ambientState.setShowingEntry(immersiveBgEntryFindByView);
        }
        updateScaleWithPivotView();
        if (!this.animationsEnabled || !z) {
            requestChildrenUpdate();
            return;
        }
        this.animationShowingEntry = immersiveBgEntryFindByView;
        this.needsAnimation = true;
        requestChildrenUpdate();
    }

    public final ImmersiveBgEntry showingEntry() {
        return this.ambientState.getShowingEntry();
    }

    public final void updateScaleWithPivotData(float f, float f2, float f3) {
        this.aodScaleDate.setScale(f);
        this.aodScaleDate.setTargetPivotX(f2);
        this.aodScaleDate.setTargetPivotY(f3);
        updateScaleWithPivotView();
    }

    public final void updateScaleWithPivotView() {
        ImmersiveBgEntry showingEntry = this.ambientState.getShowingEntry();
        if (showingEntry == null || showingEntry.isViewSurfaceType()) {
            return;
        }
        float targetPivotX = this.aodScaleDate.getTargetPivotX() - showingEntry.getView().getLeft();
        float targetPivotY = this.aodScaleDate.getTargetPivotY() - showingEntry.getView().getTop();
        float scale = this.aodScaleDate.getScale();
        if (showingEntry.getView().getScaleX() == scale && showingEntry.getView().getPivotX() == targetPivotX && showingEntry.getView().getPivotY() == targetPivotY) {
            return;
        }
        showingEntry.getView().setPivotX(targetPivotX);
        showingEntry.getView().setPivotY(targetPivotY);
        ViewExpandKtKt.setScaleXY(showingEntry.getView(), scale, scale);
    }

    public OplusImmersiveBgContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ OplusImmersiveBgContainer(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public OplusImmersiveBgContainer(Context context) {
        this(context, null, 0, 6, null);
    }
}
