package com.oplus.systemui.qs.detail.flashlight;

import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import android.view.View;
import androidx.compose.runtime.OpaqueKey$;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.GhostedViewTransitionAnimatorController;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.animation.view.LaunchableFrameLayout;
import com.android.systemui.util.kotlin.DisposableHandles;
import com.oplus.systemui.common.dialog.detail.FlashLightTargetAnimatorController;
import com.oplus.systemui.flashlight.feature.FlashlightThreeFlashFeature;
import com.oplus.systemui.flashlight.feature.FlashlightThreeFlashFeature$;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.detail.flashlight.binder.FlashlightDetailBinder;
import com.oplus.systemui.qs.detail.flashlight.viewmodel.FlashlightDetailViewModel$Factory;
import com.oplus.systemui.viewdialog.ui.animation.DetailAnimationManager;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSFlashlightDetailProvider implements ViewDialogAdapter, DetailAnimationManager.AnimationCallBack {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSFlashlightDetailProvider";
    private final Context context;
    private final FlashlightDetailViewModel$Factory viewModelFactory;
    private WeakReference<OplusQSFlashlightDetailView> detailView = new WeakReference<>(null);
    private final AtomicReference<Boolean> showingState = new AtomicReference<>(Boolean.FALSE);
    private final DisposableHandles disposableHandles = new DisposableHandles();
    private WeakReference<View> expandableViewRef = new WeakReference<>(null);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQSFlashlightDetailProvider(Context context, FlashlightDetailViewModel$Factory flashlightDetailViewModel$Factory) {
        this.context = context;
        this.viewModelFactory = flashlightDetailViewModel$Factory;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public /* bridge */ /* synthetic */ ViewDialogAnimationManager createAnimationManager(View view) {
        return super.createAnimationManager(view);
    }

    /* JADX WARN: Type inference failed for: r5v4, types: [android.view.View, android.widget.FrameLayout, com.oplus.systemui.qs.detail.flashlight.OplusQSFlashlightDetailView, java.lang.Object] */
    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public View createDialogView(Context context) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, OpaqueKey$.ExternalSyntheticOutline0.m(this.detailView.get(), "createDialogView detailView="), null, 8);
        ?? PrepareFlashLightDetailView = prepareFlashLightDetailView();
        PrepareFlashLightDetailView.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSFlashlightDetailView-->bindForShow()");
        }
        boolean zIsLandscape = DeviceUtil.isLandscape(PrepareFlashLightDetailView.getContext());
        if (PrepareFlashLightDetailView.mIsLandscape != zIsLandscape) {
            PrepareFlashLightDetailView.mIsLandscape = zIsLandscape;
            PrepareFlashLightDetailView.rebuildStructureForOrientation(false);
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("ensureLayoutMatchesOrientation() isLandscape = ", "OplusQSFlashlightDetailView-->", "SystemUi--QuickSettings", PrepareFlashLightDetailView.mIsLandscape);
        }
        PrepareFlashLightDetailView.onThreeFlashConfigure();
        PrepareFlashLightDetailView.onThreeFlashSecondaryViewShowed();
        PrepareFlashLightDetailView.refreshAppearance();
        PrepareFlashLightDetailView.attachFlashlightEffectsInternal();
        this.disposableHandles.plusAssign(FlashlightDetailBinder.INSTANCE.bind((OplusQSFlashlightDetailView) PrepareFlashLightDetailView, this.viewModelFactory));
        return PrepareFlashLightDetailView;
    }

    @Override // com.oplus.systemui.viewdialog.ui.animation.DetailAnimationManager.AnimationCallBack
    public TransitionAnimator.Controller createEndController(Pair<? extends View, Float> pair, Triple<? extends View, Integer, ? extends Function0> triple, boolean z) {
        LaunchableFrameLayout launchableFrameLayout;
        View view = (View) pair.getFirst();
        if (view == null || (launchableFrameLayout = (OplusQSFlashlightDetailView) this.detailView.get()) == null) {
            return null;
        }
        return !z ? new GhostedViewTransitionAnimatorController(view, (Integer) null, (ActivityTransitionAnimator.TransitionCookie) null, (ComponentName) null, (Integer) null, false, (InteractionJankMonitor) null, (Float) pair.getSecond(), true, (Float) null, 1662) : new FlashLightTargetAnimatorController(launchableFrameLayout, z);
    }

    @Override // com.oplus.systemui.viewdialog.ui.animation.DetailAnimationManager.AnimationCallBack
    public TransitionAnimator.Controller createStartController(Pair<? extends View, Float> pair, Triple<? extends View, Integer, ? extends Function0> triple, boolean z) {
        LaunchableFrameLayout launchableFrameLayout;
        View view = (View) pair.getFirst();
        if (view == null || (launchableFrameLayout = (OplusQSFlashlightDetailView) this.detailView.get()) == null) {
            return null;
        }
        return z ? new GhostedViewTransitionAnimatorController(view, (Integer) null, (ActivityTransitionAnimator.TransitionCookie) null, (ComponentName) null, (Integer) null, false, (InteractionJankMonitor) null, (Float) pair.getSecond(), true, (Float) null, 1662) : new FlashLightTargetAnimatorController(launchableFrameLayout, z);
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public int getContentViewId() {
        if (ScrimUtil.isBAnimLevel()) {
            return super.getContentViewId();
        }
        return 2131363152;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public View getExpandableView() {
        return this.expandableViewRef.get();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public /* bridge */ /* synthetic */ int getHideContentViewId() {
        return super.getHideContentViewId();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public String getViewTag() {
        return "flashlight";
    }

    public final boolean isShowing() {
        return this.showingState.get().booleanValue();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final void onDismiss() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onDismiss.", null, 8);
        this.showingState.set(Boolean.FALSE);
        releaseFlashLightDetailView();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final void onIntercept() {
        releaseFlashLightDetailView();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final void onShow() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onShow.", null, 8);
        this.showingState.set(Boolean.TRUE);
    }

    public final OplusQSFlashlightDetailView prepareFlashLightDetailView() {
        OplusQSFlashlightDetailView oplusQSFlashlightDetailView = this.detailView.get();
        if (oplusQSFlashlightDetailView != null) {
            return oplusQSFlashlightDetailView;
        }
        FlashlightThreeFlashFeature.INSTANCE.getClass();
        if (!FlashlightThreeFlashFeature.factoriesInstalled) {
            OplusQSFlashlightDetailView.Companion.installFactory(new FlashlightThreeFlashFeature$.ExternalSyntheticLambda0(0));
            FlashlightCardSwipeTouchHelper.Companion.installFactory(new FlashlightThreeFlashFeature$.ExternalSyntheticLambda0(1));
            FlashlightThreeFlashFeature.factoriesInstalled = true;
        }
        OplusQSFlashlightDetailView.Companion companion = OplusQSFlashlightDetailView.Companion;
        Context context = this.context;
        OplusQSFlashlightDetailView oplusQSFlashlightDetailViewCreate = companion.create(QsColorUtil.createDarkColorContext(context, QsColorUtil.isNeedDarkThemeColor(context, false)));
        this.detailView = new WeakReference<>(oplusQSFlashlightDetailViewCreate);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "prepareFlashLightDetailView: detailView=" + oplusQSFlashlightDetailViewCreate, null, 8);
        return oplusQSFlashlightDetailViewCreate;
    }

    public final void releaseFlashLightDetailView() {
        if (this.showingState.get().booleanValue()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "releaseFlashLightDetailView: dialog is showing, skip", null, 8);
            return;
        }
        this.disposableHandles.dispose();
        OplusQSFlashlightDetailView oplusQSFlashlightDetailView = this.detailView.get();
        if (oplusQSFlashlightDetailView != null) {
            oplusQSFlashlightDetailView.destroyDialog();
        }
        this.detailView.clear();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public void setExpandableView(View view) {
        this.expandableViewRef = new WeakReference<>(view);
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final boolean windowInsetsEnabled() {
        return false;
    }
}
