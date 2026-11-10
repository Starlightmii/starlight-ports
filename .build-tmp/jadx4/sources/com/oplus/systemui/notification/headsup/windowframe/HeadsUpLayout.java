package com.oplus.systemui.notification.headsup.windowframe;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Trace;
import android.util.IndentingPrintWriter;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.exifinterface.media.ExifInterface$;
import androidx.recyclerview.widget.ChildHelper$;
import androidx.room.util.CursorUtil$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.bluetooth.devicesettings.DeviceSettingItem$;
import com.android.systemui.Dumpable;
import com.android.systemui.SwipeHelper;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.render.GroupExpansionManager;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.AnimationFilter;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.android.systemui.util.DumpUtilsKt;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.headsup.FloatingActionHelper;
import com.oplus.systemui.notification.headsup.nssl.HeadsUpNsslStateManager;
import com.oplus.systemui.notification.material.NotificationSpotLightTouchBridge;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.separate.ColumnWidthUtil;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpLayout extends FrameLayout implements ExpandableView.OnHeightChangedListener, GroupExpansionManager.OnGroupExpansionChangeListener, Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "HeadsUpLayout";
    private final List<View> addedHeadsUpChildren;
    private final Lazy ambientState$delegate;
    private final List<AnimationEvent> animationEvents;
    private final CopyOnWriteArrayList<Runnable> animationFinishedRunnables;
    public boolean animationRunning;
    private ViewBlurManager blurManager;
    public boolean childrenUpdateRequested;
    private final ViewTreeObserver.OnPreDrawListener childrenUpdater;
    private ExpandableView expandedGroupView;
    private FloatingActionHelper floatingActionHelper;
    public boolean handlerBySwipeHelper;
    public boolean handlerByTouch;
    private final HeadsUpNsslStateManager headsUpNsslStateManager;
    public boolean hideSensitiveNeedsAnimation;
    private OnImeHeightChangedListener imeHeightChanged;
    public int lastImeInset;
    public int lastScreenHeight;
    private final LogBuffer logBuffer;
    public final boolean needEvent;
    public boolean needViewResizeAnimation;
    public boolean needsAnimation;
    private NotificationMaterialColorEx notificationMaterialColor;
    private final PipelineEntry.OnSensitivityChangedListener onChildSensitivityChangedListener;
    private final Runnable onSizeChangedCallback;
    private NotificationRemoteInputManager remoteInputManager;
    private final HeadsUpStateAnimator stateAnimator;
    private NotificationSwipeHelper swipeHelper;
    private OplusHeadsUpTouchHelper touchHelper;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.windowframe.HeadsUpLayout$onViewAddedInternal$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ View $child;

        @Override // java.lang.Runnable
        public final void run() {
            ExpandableNotificationRow expandableNotificationRow = this.$child;
            expandableNotificationRow.setActualHeight(expandableNotificationRow.getIntrinsicHeight());
        }
    }

    public HeadsUpLayout(Context context, LogBuffer logBuffer, HeadsUpNsslStateManager headsUpNsslStateManager, Runnable runnable) {
        super(context);
        this.animationEvents = new ArrayList();
        this.addedHeadsUpChildren = new ArrayList();
        this.ambientState$delegate = LazyKt__LazyJVMKt.lazy(new HeadsUpLayout$$ExternalSyntheticLambda0());
        this.needEvent = true;
        this.lastScreenHeight = getContext().getResources().getDisplayMetrics().heightPixels;
        this.animationFinishedRunnables = new CopyOnWriteArrayList<>();
        HeadsUpLayout$childrenUpdater$1 headsUpLayout$childrenUpdater$1 = new HeadsUpLayout$childrenUpdater$1();
        headsUpLayout$childrenUpdater$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.childrenUpdater = headsUpLayout$childrenUpdater$1;
        HeadsUpLayout$onChildSensitivityChangedListener$1 headsUpLayout$onChildSensitivityChangedListener$1 = new HeadsUpLayout$onChildSensitivityChangedListener$1();
        headsUpLayout$onChildSensitivityChangedListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.onChildSensitivityChangedListener = headsUpLayout$onChildSensitivityChangedListener$1;
        this.logBuffer = logBuffer;
        this.headsUpNsslStateManager = headsUpNsslStateManager;
        this.onSizeChangedCallback = runnable;
        this.stateAnimator = new HeadsUpStateAnimator(context, this, logBuffer);
        setClipChildren(false);
        setClipToPadding(false);
        updateResource$2(context.getResources());
    }

    private final AmbientState getAmbientState() {
        return (AmbientState) this.ambientState$delegate.getValue();
    }

    private final ExpandableNotificationRow getRow() {
        if (getChildCount() == 0) {
            return null;
        }
        ExpandableNotificationRow childAt = getChildAt(0);
        if (childAt instanceof ExpandableNotificationRow) {
            return childAt;
        }
        return null;
    }

    private final void setAnimationRunning(boolean z) {
        if (z != this.animationRunning) {
            this.animationRunning = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setChildrenUpdateRequested(boolean z) {
        this.childrenUpdateRequested = z;
        this.logBuffer.log(TAG, LogLevel.DEBUG, KeyguardLogger$.ExternalSyntheticOutline0.m("childrenUpdateRequested: ", z), (Throwable) null);
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        super.addView(view);
        this.logBuffer.log(TAG, LogLevel.DEBUG, ChildHelper$.ExternalSyntheticOutline0.m(view, "addView:: view = "), (Throwable) null);
    }

    public final void applyCurrentState$1() {
        ExpandableNotificationRow row = getRow();
        if (row != null) {
            row.getViewState().height = row.getActualHeight();
            row.getViewState().setYTranslation(row.getTranslationY(), "HeadsUpLayout.applyCurrentState");
            row.applyViewState();
        }
        runAnimationFinishedRunnables();
    }

    @Override // android.view.View
    public final void cancelLongPress() {
        super.cancelLongPress();
        NotificationSwipeHelper notificationSwipeHelper = this.swipeHelper;
        if (notificationSwipeHelper != null) {
            notificationSwipeHelper.cancelLongPress();
        }
    }

    public final void clearTouchState(String str, boolean z) {
        NotificationSwipeHelper notificationSwipeHelper;
        OplusHeadsUpTouchHelper oplusHeadsUpTouchHelper = this.touchHelper;
        if (oplusHeadsUpTouchHelper != null) {
            oplusHeadsUpTouchHelper.cancelCouiSequence();
        }
        OplusHeadsUpTouchHelper oplusHeadsUpTouchHelper2 = this.touchHelper;
        if (oplusHeadsUpTouchHelper2 != null) {
            oplusHeadsUpTouchHelper2.endMotion(str);
        }
        OplusHeadsUpTouchHelper oplusHeadsUpTouchHelper3 = this.touchHelper;
        if (oplusHeadsUpTouchHelper3 != null) {
            oplusHeadsUpTouchHelper3.setTrackingHeadsUp(false);
        }
        OplusHeadsUpTouchHelper oplusHeadsUpTouchHelper4 = this.touchHelper;
        if (oplusHeadsUpTouchHelper4 != null) {
            oplusHeadsUpTouchHelper4.resetDrag();
        }
        if (!z && (notificationSwipeHelper = this.swipeHelper) != null) {
            notificationSwipeHelper.resetSwipeStates(true);
            notificationSwipeHelper.mCallback.resetMagneticStates();
        }
        NotificationSwipeHelper notificationSwipeHelper2 = this.swipeHelper;
        if (notificationSwipeHelper2 != null) {
            notificationSwipeHelper2.setTranslatingParentView((View) null);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        NotificationSpotLightTouchBridge.forwardHeadsUpDispatchTouch(this, motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriterAsIndenting = DumpUtilsKt.asIndenting(printWriter);
        indentingPrintWriterAsIndenting.println(TAG);
        ExpandableNotificationRow row = getRow();
        if (row != null) {
            row.dump(indentingPrintWriterAsIndenting, strArr);
        }
    }

    public final ViewBlurManager getBlurManager() {
        return this.blurManager;
    }

    public final FloatingActionHelper getFloatingActionHelper() {
        return this.floatingActionHelper;
    }

    public final LogBuffer getLogBuffer() {
        return this.logBuffer;
    }

    public final NotificationMaterialColorEx getNotificationMaterialColor() {
        return this.notificationMaterialColor;
    }

    public final NotificationRemoteInputManager getRemoteInputManager() {
        return this.remoteInputManager;
    }

    public final NotificationSwipeHelper getSwipeHelper() {
        return this.swipeHelper;
    }

    public final OplusHeadsUpTouchHelper getTouchHelper() {
        return this.touchHelper;
    }

    public final boolean hasHeadsUpFocus() {
        ExpandableNotificationRow row = getRow();
        if (row != null) {
            return row.hasFocus();
        }
        return false;
    }

    public final boolean isHeadsUpView(View view) {
        for (ViewParent parent = view != null ? view.getParent() : null; parent != null; parent = parent.getParent()) {
            if (parent.equals(this)) {
                return true;
            }
        }
        return false;
    }

    public final boolean isTrackingHeadsUp() {
        if (getRow() == null) {
            return false;
        }
        OplusHeadsUpTouchHelper oplusHeadsUpTouchHelper = this.touchHelper;
        if (oplusHeadsUpTouchHelper == null || !oplusHeadsUpTouchHelper.mTrackingHeadsUp) {
            NotificationSwipeHelper notificationSwipeHelper = this.swipeHelper;
            if ((notificationSwipeHelper != null ? notificationSwipeHelper.getSwipedView() : null) == null) {
                return false;
            }
        }
        return true;
    }

    public final void notifyGroupChildAdded(ExpandableView expandableView) {
        onViewAddedInternal(expandableView, "groupChildAdd");
    }

    public final void notifyGroupChildRemoved(ExpandableNotificationRow expandableNotificationRow) {
        onViewRemovedInternal(expandableNotificationRow, "groupChildRemove");
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        OnImeHeightChangedListener onImeHeightChangedListener;
        int i = windowInsets.getInsets(WindowInsets.Type.ime()).bottom;
        int i2 = getContext().getResources().getDisplayMetrics().heightPixels;
        ExpandableNotificationRow row = getRow();
        if (row != null && ((i != this.lastImeInset || i2 != this.lastScreenHeight) && (onImeHeightChangedListener = this.imeHeightChanged) != null)) {
            ((OplusHeadsUpAnimator) onImeHeightChangedListener).onImeHeightChanged(i2, i, row.getActualHeight());
        }
        this.lastImeInset = i;
        this.lastScreenHeight = i2;
        return super.onApplyWindowInsets(windowInsets);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        requestChildrenUpdate();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateResource$2(getResources());
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.childrenUpdateRequested) {
            this.logBuffer.log(TAG, LogLevel.DEBUG, "onDetachedFromWindow: reset childrenUpdateRequested", (Throwable) null);
            setChildrenUpdateRequested(false);
        }
        clearTouchState("Detached", false);
    }

    public void onGroupExpansionChange(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        if (isAttachedToWindow() && expandableNotificationRow != null && Intrinsics.areEqual(getRow(), expandableNotificationRow)) {
            LogBuffer logBuffer = this.logBuffer;
            LogLevel logLevel = LogLevel.DEBUG;
            NotificationChildrenContainer childrenContainer = expandableNotificationRow.getChildrenContainer();
            logBuffer.log(TAG, logLevel, "onGroupExpansionChange: " + z + " count =" + (childrenContainer != null ? Integer.valueOf(childrenContainer.getChildCount()) : null), (Throwable) null);
            this.expandedGroupView = expandableNotificationRow;
            this.needsAnimation = true;
            expandableNotificationRow.setChildrenExpanded(z);
            requestChildrenUpdate();
            if (z) {
                setHeadsUpBlur(expandableNotificationRow, true, "onGroupExpansionChange", true);
            }
            Runnable runnable = new onGroupExpansionChange.1();
            ((onGroupExpansionChange.1) runnable).$changedRow = expandableNotificationRow;
            ((onGroupExpansionChange.1) runnable).$expanded = z;
            ((onGroupExpansionChange.1) runnable).this$0 = this;
            VarHandle.storeStoreFence();
            this.animationFinishedRunnables.add(runnable);
        }
    }

    public void onHeightChanged(ExpandableView expandableView, boolean z, String str) {
        this.logBuffer.log(TAG, LogLevel.DEBUG, "onHeightChanged: " + expandableView + OplusPreventModeData.SEPARATOR + z, (Throwable) null);
        if (z) {
            this.needViewResizeAnimation = true;
            this.needsAnimation = true;
        }
        this.animationFinishedRunnables.add(this.onSizeChangedCallback);
        requestChildrenUpdate();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        ExpandableNotificationRow row;
        boolean zOnInterceptTouchEvent = false;
        if (motionEvent != null && this.needEvent && (row = getRow()) != null) {
            float translationY = row.getTranslationY();
            float fMax = (float) (Math.max(0.0d, row.getClipTopAmount()) + ((double) translationY));
            float actualHeight = (translationY + row.getActualHeight()) - row.getClipBottomAmount();
            float y = motionEvent.getY();
            if (motionEvent.getActionMasked() != 0 || (y <= actualHeight && y >= fMax)) {
                OplusHeadsUpTouchHelper oplusHeadsUpTouchHelper = this.touchHelper;
                boolean zOnInterceptTouchEvent2 = oplusHeadsUpTouchHelper != null ? oplusHeadsUpTouchHelper.onInterceptTouchEvent(motionEvent) : false;
                this.handlerByTouch = zOnInterceptTouchEvent2;
                NotificationSwipeHelper notificationSwipeHelper = this.swipeHelper;
                if (zOnInterceptTouchEvent2) {
                    if (notificationSwipeHelper == null) {
                        return true;
                    }
                    notificationSwipeHelper.cancelLongPress();
                    return true;
                }
                zOnInterceptTouchEvent = notificationSwipeHelper != null ? notificationSwipeHelper.onInterceptTouchEvent(motionEvent) : false;
                this.handlerBySwipeHelper = zOnInterceptTouchEvent;
            }
        }
        return zOnInterceptTouchEvent;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        ExpandableNotificationRow row = getRow();
        if (row == null || this.animationRunning) {
            return;
        }
        NotificationContentView publicLayout = row.getPublicLayout();
        if ((publicLayout == null || publicLayout.getVisibility() != 0) && row.getActualHeight() != row.getIntrinsicHeight() && row.getActualHeight() > 0 && row.getIntrinsicHeight() > 0) {
            this.logBuffer.log(TAG, LogLevel.DEBUG, TextFieldBuffer$.ExternalSyntheticOutline0.m(row.getIntrinsicHeight(), row.getActualHeight(), "onLayout: intrinsicHeight=", ",actualHeight="), (Throwable) null);
            this.needsAnimation = true;
            this.needViewResizeAnimation = true;
            this.animationFinishedRunnables.add(this.onSizeChangedCallback);
            requestChildrenUpdate();
        }
    }

    public final void onMaterialColorChanged() {
        if (isAttachedToWindow()) {
            this.logBuffer.log(TAG, LogLevel.DEBUG, "requestMaterialColorChanged: shouldAnimate=false", (Throwable) null);
            requestChildrenUpdate();
        }
    }

    public void onReset(ExpandableView expandableView) {
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        NotificationEntry entry;
        ExpandableNotificationRow expandableNotificationRow;
        if (this.needEvent) {
            if (this.handlerByTouch) {
                OplusHeadsUpTouchHelper oplusHeadsUpTouchHelper = this.touchHelper;
                if (oplusHeadsUpTouchHelper != null) {
                    return oplusHeadsUpTouchHelper.onTouchEvent(motionEvent);
                }
            } else if (this.handlerBySwipeHelper) {
                ExpandableNotificationRow row = getRow();
                if (row != null && (entry = row.getEntry()) != null && (expandableNotificationRow = entry.row) != null && expandableNotificationRow.mLaunchAnimationRunning) {
                    return true;
                }
                NotificationSwipeHelper notificationSwipeHelper = this.swipeHelper;
                if (notificationSwipeHelper != null) {
                    return notificationSwipeHelper.onTouchEvent(motionEvent);
                }
            } else {
                NotificationRemoteInputManager notificationRemoteInputManager = this.remoteInputManager;
                if (notificationRemoteInputManager != null) {
                    notificationRemoteInputManager.checkRemoteInputOutside(motionEvent);
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        List<ExpandableNotificationRow> attachedChildren;
        super.onViewAdded(view);
        onViewAddedInternal(view, "onViewAdded");
        if (view instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
            if (!expandableNotificationRow.mIsSummaryWithChildren || (attachedChildren = expandableNotificationRow.getAttachedChildren()) == null) {
                return;
            }
            for (ExpandableNotificationRow expandableNotificationRow2 : attachedChildren) {
                expandableNotificationRow2.getClass();
                onViewAddedInternal(expandableNotificationRow2, "onChildViewAdded");
            }
        }
    }

    public final void onViewAddedInternal(View view, String str) {
        NotificationBackgroundViewExt ext;
        if (view instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
            this.logBuffer.log(TAG, LogLevel.DEBUG, CursorUtil$.ExternalSyntheticOutline0.m("onViewAddedInternal: ", NotificationUtilsKt.getLogKeyNonNull(expandableNotificationRow.getEntry()), ",reason=", str), (Throwable) null);
            expandableNotificationRow.setHideSensitiveForIntrinsicHeight(getAmbientState().mHideSensitive);
            expandableNotificationRow.setOnHeightChangedListener(this);
            expandableNotificationRow.getEntry().addOnSensitivityChangedListener(this.onChildSensitivityChangedListener);
            expandableNotificationRow.setAnimationRunning(true);
            onHeightChanged((ExpandableView) view, false, "HeadsUpLayout.onViewAddedInternal");
            setHeadsUpBlur(expandableNotificationRow, true, "onViewAddedInternal", false);
            NotificationBackgroundView notificationBackgroundView = ((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal;
            if (notificationBackgroundView != null && (ext = notificationBackgroundView.getExt()) != null) {
                ext.onHeadsUpStateChanged(true);
            }
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.$child = view;
            VarHandle.storeStoreFence();
            post(anonymousClass1);
        }
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        onViewRemovedInternal(view, "onViewRemoved");
    }

    public final void onViewRemovedInternal(View view, String str) {
        NotificationBackgroundViewExt ext;
        if (view instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
            this.logBuffer.log(TAG, LogLevel.DEBUG, CursorUtil$.ExternalSyntheticOutline0.m("onViewRemovedInternal: ", NotificationUtilsKt.getLogKeyNonNull(expandableNotificationRow.getEntry()), ",reason=", str), (Throwable) null);
            NotificationBackgroundView notificationBackgroundView = ((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal;
            if (notificationBackgroundView != null && (ext = notificationBackgroundView.getExt()) != null) {
                ext.onHeadsUpStateChanged(false);
            }
            expandableNotificationRow.setOnHeightChangedListener((ExpandableView.OnHeightChangedListener) null);
            expandableNotificationRow.getEntry().removeOnSensitivityChangedListener(this.onChildSensitivityChangedListener);
            expandableNotificationRow.setAnimationRunning(false);
            setHeadsUpBlur(expandableNotificationRow, false, "onViewRemovedInternal", false);
            expandableNotificationRow.removeChildrenWithKeepInParent();
        }
        ExpandableNotificationRow row = getRow();
        if (row == null || !row.mIsSummaryWithChildren) {
            return;
        }
        this.expandedGroupView = row;
        this.needsAnimation = true;
        onHeightChanged(row, false, "HeadsUpLayout.onViewRemovedInternal");
    }

    /* JADX WARN: Code duplicated, block: B:11:0x002f  */
    @Override // android.view.ViewGroup
    public final void removeAllViews() {
        boolean z;
        NotificationSwipeHelper notificationSwipeHelper;
        ExpandableNotificationRow row = getRow();
        super.removeAllViews();
        LogBuffer logBuffer = this.logBuffer;
        LogLevel logLevel = LogLevel.DEBUG;
        logBuffer.log(TAG, logLevel, "removeAllViews: ", (Throwable) null);
        setHeadsUpBlur(row, false, "removeAllViews", true);
        boolean z2 = (row == null || (notificationSwipeHelper = this.swipeHelper) == null || !((SwipeHelper) notificationSwipeHelper).mDismissPendingMap.containsKey(row)) ? false : true;
        if (row == null) {
            z = false;
        } else {
            NotificationSwipeHelper notificationSwipeHelper2 = this.swipeHelper;
            z = ((ActivatableNotificationView) row).mDismissed;
            if (notificationSwipeHelper2 != null) {
                if (z || z2) {
                    z = true;
                } else {
                    z = false;
                }
            }
        }
        LogBuffer logBuffer2 = this.logBuffer;
        boolean z3 = row == null;
        boolean z4 = this.swipeHelper == null;
        Boolean boolValueOf = row != null ? Boolean.valueOf(((ActivatableNotificationView) row).mDismissed) : null;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("shouldSkipSwipeHelperReset: rowNull=", ", helperNull=", ", dismissed=", z3, z4);
        sbM.append(boolValueOf);
        sbM.append(", pendingAnimating=");
        sbM.append(z2);
        sbM.append(", result=");
        sbM.append(z);
        logBuffer2.log(TAG, logLevel, sbM.toString(), (Throwable) null);
        clearTouchState("removeAllViews", z);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        super.removeView(view);
        this.logBuffer.log(TAG, LogLevel.DEBUG, ChildHelper$.ExternalSyntheticOutline0.m(view, "removeView:: view = "), (Throwable) null);
    }

    public final void requestAnimationOnViewResizeCustom() {
        ExpandableNotificationRow row = getRow();
        if (row != null) {
            onHeightChanged(row, true, "HeadsUpLayout.requestAnimationOnViewResizeCustom");
        }
    }

    public final void requestChildrenUpdate() {
        if (this.childrenUpdateRequested) {
            return;
        }
        getViewTreeObserver().addOnPreDrawListener(this.childrenUpdater);
        setChildrenUpdateRequested(true);
        invalidate();
    }

    public final void requestDisallowLongPress() {
        this.logBuffer.log(TAG, LogLevel.DEBUG, "cancelLongPress", (Throwable) null);
        cancelLongPress();
    }

    public final void resetViewExtState(ExpandableNotificationRow expandableNotificationRow) {
        ViewBlurProxy blurProxyForView;
        NotificationChildrenContainer childrenContainer = expandableNotificationRow.getChildrenContainer();
        if (childrenContainer != null) {
            childrenContainer.setClipBottomAmount(0);
        }
        expandableNotificationRow.getViewState().clipBottomAmount = 0;
        List<ExpandableNotificationRow> attachedChildren = expandableNotificationRow.getAttachedChildren();
        if (attachedChildren != null) {
            for (ExpandableNotificationRow expandableNotificationRow2 : attachedChildren) {
                if (expandableNotificationRow2 != null && expandableNotificationRow2.getVisibility() != 8) {
                    expandableNotificationRow2.getViewState().clipBottomAmount = 0;
                    expandableNotificationRow2.setClipBottomAmount(0);
                    ViewBlurManager viewBlurManager = this.blurManager;
                    if (viewBlurManager != null && (blurProxyForView = viewBlurManager.getBlurProxyForView(((ActivatableNotificationView) expandableNotificationRow2).mBackgroundNormal)) != null && blurProxyForView.getBlurAmount() != 1.0f) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("resetChildBlurAmount，is ", blurProxyForView.getBlurAmount(), "HeadsUpLayout-->", "SystemUi--Notification");
                        }
                        blurProxyForView.setBlurAmount(1.0f);
                    }
                }
            }
        }
        NotificationMaterialColorEx notificationMaterialColorEx = this.notificationMaterialColor;
        if (notificationMaterialColorEx != null) {
            notificationMaterialColorEx.updateNotificationColorMode(expandableNotificationRow);
        }
    }

    public final void runAnimationFinishedRunnables() {
        Iterator<T> it = this.animationFinishedRunnables.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        this.animationFinishedRunnables.clear();
        setAnimationRunning(false);
    }

    public final void setBlurManager(ViewBlurManager viewBlurManager) {
        this.blurManager = viewBlurManager;
    }

    public final void setFloatingActionHelper(FloatingActionHelper floatingActionHelper) {
        this.floatingActionHelper = floatingActionHelper;
        if (floatingActionHelper != null) {
            floatingActionHelper.setView(this);
        }
    }

    public final void setHeadsUpBlur(ExpandableNotificationRow expandableNotificationRow, boolean z, String str, boolean z2) {
        String str2;
        if (expandableNotificationRow == null) {
            return;
        }
        LogBuffer logBuffer = this.logBuffer;
        LogLevel logLevel = LogLevel.DEBUG;
        String logKeyNonNull = NotificationUtilsKt.getLogKeyNonNull(expandableNotificationRow.getEntry());
        if (expandableNotificationRow.mIsSummaryWithChildren) {
            List attachedChildren = expandableNotificationRow.getAttachedChildren();
            str2 = "true(" + (attachedChildren != null ? Integer.valueOf(attachedChildren.size()) : null) + ")";
        } else {
            str2 = "false";
        }
        boolean zIsGroupExpansionChanging = expandableNotificationRow.isGroupExpansionChanging();
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("setHeadsUpBlur: ", logKeyNonNull, " ,reason=", str, ",isSummary =");
        DeviceSettingItem$.ExternalSyntheticOutline0.m(str2, ",isHeadsUp=", ",updateGroupHeader=", sbM, z);
        logBuffer.log(TAG, logLevel, KeyguardLogger$.ExternalSyntheticOutline0.m(sbM, z2, ",child.isGroupExpansionChanging=", zIsGroupExpansionChanging), (Throwable) null);
        setHeadsUpBlur(expandableNotificationRow, z);
        if (expandableNotificationRow.mIsSummaryWithChildren) {
            List<ExpandableNotificationRow> attachedChildren2 = expandableNotificationRow.getAttachedChildren();
            if (attachedChildren2 != null) {
                for (ExpandableNotificationRow expandableNotificationRow2 : attachedChildren2) {
                    expandableNotificationRow2.getClass();
                    setHeadsUpBlur(expandableNotificationRow2, z);
                }
            }
            if (!z2 || expandableNotificationRow.isGroupExpansionChanging()) {
                return;
            }
            expandableNotificationRow.updateBackgroundForGroupState();
        }
    }

    public final void setImeHeightChangedListener(OnImeHeightChangedListener onImeHeightChangedListener) {
        this.imeHeightChanged = onImeHeightChangedListener;
    }

    public final void setNotificationMaterialColor(NotificationMaterialColorEx notificationMaterialColorEx) {
        this.notificationMaterialColor = notificationMaterialColorEx;
    }

    public final void setRemoteInputManager(NotificationRemoteInputManager notificationRemoteInputManager) {
        this.remoteInputManager = notificationRemoteInputManager;
    }

    public final void setSwipeHelper(NotificationSwipeHelper notificationSwipeHelper) {
        this.swipeHelper = notificationSwipeHelper;
    }

    public final void setTouchHelper(OplusHeadsUpTouchHelper oplusHeadsUpTouchHelper) {
        this.touchHelper = oplusHeadsUpTouchHelper;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        if (i != 1) {
            return super.startActionModeForChild(view, callback, i);
        }
        FloatingActionHelper floatingActionHelper = this.floatingActionHelper;
        if (floatingActionHelper != null) {
            return floatingActionHelper.startActionMode(view, callback);
        }
        return null;
    }

    public final void updateChildren() {
        int animationType;
        Trace.beginSection("HeadsUp#updateChildren");
        ExpandableNotificationRow row = getRow();
        if (row != null) {
            row.resetViewState();
            resetViewExtState(row);
            row.updateChildrenStates();
            ((OplusExpandableViewExImpl) ((ExpandableView) row).mExpandableViewEx).invalidateResetPassCache(false);
        }
        if (this.needsAnimation) {
            this.logBuffer.log(TAG, LogLevel.DEBUG, "startAnimationToState: ", (Throwable) null);
            if (this.needsAnimation) {
                if (this.needViewResizeAnimation) {
                    ExpandableNotificationRow row2 = getRow();
                    if (row2 == null) {
                        this.needViewResizeAnimation = true;
                    } else {
                        Iterator<AnimationEvent> it = this.animationEvents.iterator();
                        do {
                            if (!it.hasNext()) {
                                this.animationEvents.add(new AnimationEvent((ExpandableView) row2, 9, 0L, 12));
                                break;
                            } else {
                                animationType = it.next().getAnimationType();
                                if (animationType == 12) {
                                    break;
                                }
                            }
                        } while (animationType != 13);
                        this.needViewResizeAnimation = false;
                    }
                } else {
                    this.needViewResizeAnimation = false;
                }
                ExpandableView expandableView = this.expandedGroupView;
                if (expandableView != null) {
                    this.animationEvents.add(new AnimationEvent(expandableView, 10, 0L, 12));
                    this.expandedGroupView = null;
                }
                if (this.hideSensitiveNeedsAnimation) {
                    this.animationEvents.add(new AnimationEvent((ExpandableView) null, 8, 0L, 12));
                }
                this.hideSensitiveNeedsAnimation = false;
                this.needsAnimation = false;
            }
            if (this.animationEvents.isEmpty()) {
                applyCurrentState$1();
            } else {
                setAnimationRunning(true);
                this.stateAnimator.startAnimationForEvents(this.animationEvents);
                this.animationEvents.clear();
            }
        } else {
            applyCurrentState$1();
        }
        Trace.endSection();
    }

    public final void updateResource$2(Resources resources) {
        resources.getDimensionPixelSize(2131172246);
        this.logBuffer.log(TAG, LogLevel.DEBUG, "updateResource: for headsUp", (Throwable) null);
        int panelWidth = (getContext().getResources().getDisplayMetrics().widthPixels - ColumnWidthUtil.getPanelWidth(getContext())) / 2;
        setPadding(panelWidth, 0, panelWidth, 0);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnimationEvent {
        public static final Companion Companion = new Companion();
        private static AnimationFilter[] FILTERS;
        private static int[] LENGTHS;
        public final int animationType;
        public final long eventStartTime;
        private final AnimationFilter filter;
        public boolean headsUpFromBottom;
        public final long length;
        private final ExpandableView mChangingView;
        private View viewAfterChangingView;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
            public final AnimationFilter[] getFILTERS() {
                return AnimationEvent.FILTERS;
            }

            public final int[] getLENGTHS() {
                return AnimationEvent.LENGTHS;
            }

            public final void setFILTERS(AnimationFilter[] animationFilterArr) {
                AnimationEvent.FILTERS = animationFilterArr;
            }

            public final void setLENGTHS(int[] iArr) {
                AnimationEvent.LENGTHS = iArr;
            }
        }

        static {
            AnimationFilter animationFilter = new AnimationFilter();
            animationFilter.animateAlpha = true;
            animationFilter.animateHeight = true;
            animationFilter.animateTopInset = true;
            animationFilter.animateY = true;
            animationFilter.animateZ = true;
            animationFilter.hasDelays = true;
            AnimationFilter animationFilter2 = new AnimationFilter();
            animationFilter2.animateHeight = true;
            animationFilter2.animateTopInset = true;
            animationFilter2.animateY = true;
            animationFilter2.animateZ = true;
            animationFilter2.hasDelays = true;
            AnimationFilter animationFilter3 = new AnimationFilter();
            animationFilter3.animateHeight = true;
            animationFilter3.animateTopInset = true;
            animationFilter3.animateY = true;
            animationFilter3.animateZ = true;
            AnimationFilter animationFilter4 = new AnimationFilter();
            animationFilter4.animateZ = true;
            AnimationFilter animationFilter5 = new AnimationFilter();
            AnimationFilter animationFilter6 = new AnimationFilter();
            animationFilter6.animateAlpha = true;
            animationFilter6.animateHeight = true;
            animationFilter6.animateTopInset = true;
            animationFilter6.animateY = true;
            animationFilter6.animateZ = true;
            AnimationFilter animationFilter7 = new AnimationFilter();
            animationFilter7.animateHeight = true;
            animationFilter7.animateTopInset = true;
            animationFilter7.animateY = true;
            animationFilter7.animateZ = true;
            animationFilter7.hasDelays = true;
            AnimationFilter animationFilter8 = new AnimationFilter();
            animationFilter8.animateHideSensitive = true;
            AnimationFilter animationFilter9 = new AnimationFilter();
            animationFilter9.animateHeight = true;
            animationFilter9.animateTopInset = true;
            animationFilter9.animateY = true;
            animationFilter9.animateZ = true;
            AnimationFilter animationFilter10 = new AnimationFilter();
            animationFilter10.animateAlpha = true;
            animationFilter10.animateHeight = true;
            animationFilter10.animateTopInset = true;
            animationFilter10.animateY = true;
            animationFilter10.animateZ = true;
            AnimationFilter animationFilter11 = new AnimationFilter();
            animationFilter11.animateHeight = true;
            animationFilter11.animateTopInset = true;
            animationFilter11.animateY = true;
            animationFilter11.animateZ = true;
            AnimationFilter animationFilter12 = new AnimationFilter();
            animationFilter12.animateHeight = true;
            animationFilter12.animateTopInset = true;
            animationFilter12.animateY = true;
            animationFilter12.animateZ = true;
            animationFilter12.hasDelays = true;
            AnimationFilter animationFilter13 = new AnimationFilter();
            animationFilter13.animateHeight = true;
            animationFilter13.animateTopInset = true;
            animationFilter13.animateY = true;
            animationFilter13.animateZ = true;
            animationFilter13.hasDelays = true;
            AnimationFilter animationFilter14 = new AnimationFilter();
            animationFilter14.animateHeight = true;
            animationFilter14.animateTopInset = true;
            animationFilter14.animateY = true;
            animationFilter14.animateZ = true;
            AnimationFilter animationFilter15 = new AnimationFilter();
            animationFilter15.animateAlpha = true;
            animationFilter15.animateHideSensitive = true;
            animationFilter15.animateHeight = true;
            animationFilter15.animateTopInset = true;
            animationFilter15.animateY = true;
            animationFilter15.animateZ = true;
            AnimationFilter animationFilter16 = new AnimationFilter();
            animationFilter16.animateHeight = true;
            animationFilter16.animateTopInset = true;
            animationFilter16.animateY = true;
            animationFilter16.animateZ = true;
            animationFilter16.hasDelays = true;
            AnimationFilter animationFilter17 = new AnimationFilter();
            animationFilter17.animateHeight = true;
            animationFilter17.animateTopInset = true;
            animationFilter17.animateY = true;
            animationFilter17.animateZ = true;
            animationFilter17.hasDelays = true;
            FILTERS = new AnimationFilter[]{animationFilter, animationFilter2, animationFilter3, animationFilter4, animationFilter5, animationFilter6, animationFilter7, animationFilter8, animationFilter9, animationFilter10, animationFilter11, animationFilter12, animationFilter13, animationFilter14, animationFilter15, animationFilter16, animationFilter17};
            LENGTHS = new int[]{384, 384, 360, 360, 220, 220, 360, 448, 360, 360, 360, 400, 400, 400, 360, 360, 400, 400};
        }

        public /* synthetic */ AnimationEvent(ExpandableView expandableView, int i, long j, int i2) {
            this(expandableView, i, (i2 & 4) != 0 ? LENGTHS[i] : j, FILTERS[i]);
        }

        public final ExpandableView component1() {
            return this.mChangingView;
        }

        public final AnimationFilter component4() {
            return this.filter;
        }

        public final AnimationEvent copy$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(ExpandableView expandableView, int i, long j, AnimationFilter animationFilter) {
            return new AnimationEvent(expandableView, i, j, animationFilter);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AnimationEvent)) {
                return false;
            }
            AnimationEvent animationEvent = (AnimationEvent) obj;
            return Intrinsics.areEqual(this.mChangingView, animationEvent.mChangingView) && this.animationType == animationEvent.animationType && this.length == animationEvent.length && Intrinsics.areEqual(this.filter, animationEvent.filter);
        }

        public final int getAnimationType() {
            return this.animationType;
        }

        public final long getEventStartTime() {
            return this.eventStartTime;
        }

        public final AnimationFilter getFilter() {
            return this.filter;
        }

        public final boolean getHeadsUpFromBottom() {
            return this.headsUpFromBottom;
        }

        public final long getLength() {
            return this.length;
        }

        public final ExpandableView getMChangingView() {
            return this.mChangingView;
        }

        public final View getViewAfterChangingView() {
            return this.viewAfterChangingView;
        }

        public final int hashCode() {
            ExpandableView expandableView = this.mChangingView;
            int iM = ChangeSize$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.animationType, (expandableView == null ? 0 : expandableView.hashCode()) * 31, 31), 31, this.length);
            AnimationFilter animationFilter = this.filter;
            return iM + (animationFilter != null ? animationFilter.hashCode() : 0);
        }

        public final void setHeadsUpFromBottom(boolean z) {
            this.headsUpFromBottom = z;
        }

        public final void setViewAfterChangingView(View view) {
            this.viewAfterChangingView = view;
        }

        public String toString() {
            return "AnimationEvent(mChangingView=" + this.mChangingView + ", animationType=" + this.animationType + ", length=" + this.length + ", filter=" + this.filter + ")";
        }

        public AnimationEvent(ExpandableView expandableView, int i, long j) {
            this(expandableView, i, j, 8);
        }

        public AnimationEvent(ExpandableView expandableView, int i, long j, AnimationFilter animationFilter) {
            this.mChangingView = expandableView;
            this.animationType = i;
            this.length = j;
            this.filter = animationFilter;
            this.eventStartTime = AnimationUtils.currentAnimationTimeMillis();
        }

        public AnimationEvent(ExpandableView expandableView, int i) {
            this(expandableView, i, 0L, 12);
        }

        public AnimationEvent(ExpandableView expandableView, int i, AnimationFilter animationFilter) {
            this(expandableView, i, LENGTHS[i], animationFilter);
        }
    }

    public final void setHeadsUpBlur(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        ViewBlurManager viewBlurManager;
        ViewBlurProxy blurProxyForView;
        ViewBlurProxy.BlurType blurType;
        ViewBlurManager viewBlurManager2;
        ViewBlurManager viewBlurManager3 = this.blurManager;
        if (!z) {
            if (viewBlurManager3 != null) {
                viewBlurManager3.cancelBlurForHeadsUp(((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal, true);
                return;
            }
            return;
        }
        if (viewBlurManager3 != null && (blurProxyForView = viewBlurManager3.getBlurProxyForView(((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal)) != null && (blurType = blurProxyForView.getBlurType()) != null && !blurType.equals(ViewBlurProxy.BlurType.BlurTypeMotion.INSTANCE) && (viewBlurManager2 = this.blurManager) != null) {
            viewBlurManager2.blurForHeadsUp(((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal, true);
        }
        if (!Intrinsics.areEqual(expandableNotificationRow, getRow()) || (viewBlurManager = this.blurManager) == null) {
            return;
        }
        viewBlurManager.resetCornerRadiusForView(((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal);
    }
}
