package com.oplus.systemui.qs.slider;

import android.content.Context;
import android.view.View;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import com.oplus.systemui.qs.base.seek.OplusQsDetailToggleTouchDelegate;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusQSBrightnessVolumeDialogController implements ViewDialogAdapter {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSBrightnessVolumeDialogController";
    private WeakReference<View> expandableViewRef = new WeakReference<>(null);
    private OplusQsDetailRootView secondaryView;
    private final SysUIViewDialogInteractor viewDialogInteractor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQSBrightnessVolumeDialogController(SysUIViewDialogInteractor sysUIViewDialogInteractor) {
        this.viewDialogInteractor = sysUIViewDialogInteractor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setExpandSliderTouchDelegate(boolean z) {
        OplusQsDetailToggleSliderLayout slider;
        View expandableView = getExpandableView();
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = expandableView instanceof OplusQsBaseToggleSliderLayout ? (OplusQsBaseToggleSliderLayout) expandableView : null;
        if (oplusQsBaseToggleSliderLayout == null) {
            return;
        }
        if (!z) {
            OplusQsDetailToggleTouchDelegate sliderTouchDelegate = oplusQsBaseToggleSliderLayout.getSliderTouchDelegate();
            if (sliderTouchDelegate != null) {
                sliderTouchDelegate.destroy();
            }
            oplusQsBaseToggleSliderLayout.setSliderTouchDelegate(null);
            return;
        }
        OplusQsDetailRootView oplusQsDetailRootView = this.secondaryView;
        if (oplusQsDetailRootView == null || (slider = oplusQsDetailRootView.getSlider()) == null) {
            return;
        }
        OplusQsDetailToggleTouchDelegate sliderTouchDelegate2 = oplusQsBaseToggleSliderLayout.getSliderTouchDelegate();
        if (sliderTouchDelegate2 != null) {
            sliderTouchDelegate2.destroy();
        }
        oplusQsBaseToggleSliderLayout.setSliderTouchDelegate(new OplusQsDetailToggleTouchDelegate(oplusQsBaseToggleSliderLayout, slider));
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public ViewDialogAnimationManager createAnimationManager(View view) {
        return new OplusQsSliderDetailAnimationManager(view);
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public abstract /* synthetic */ View createDialogView(Context context);

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public int getContentViewId() {
        return 2131364734;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public View getExpandableView() {
        return this.expandableViewRef.get();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public /* bridge */ /* synthetic */ int getHideContentViewId() {
        return super.getHideContentViewId();
    }

    public final OplusQsDetailRootView getSecondaryView() {
        return this.secondaryView;
    }

    public final int getSecondaryViewLayout() {
        return 2131559336;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public String getViewTag() {
        return TAG;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final boolean needInterceptShadeTouchOnShow() {
        return false;
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager.AnimationCallBack
    public void onAnimationStart(boolean z) {
        OplusQsDetailRootView oplusQsDetailRootView;
        OplusQsDetailToggleSliderLayout slider;
        if (z || (oplusQsDetailRootView = this.secondaryView) == null || (slider = oplusQsDetailRootView.getSlider()) == null) {
            return;
        }
        slider.stopPhysicsMove();
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public void onDismiss() {
        LogUtil.i$default(LogUtil.TAG_QS, getViewTag(), "[onDismiss]", null, 8);
        OplusQsDetailRootView oplusQsDetailRootView = this.secondaryView;
        if (oplusQsDetailRootView != null) {
            oplusQsDetailRootView.setListener(null);
        }
        this.secondaryView = null;
        setExpandSliderTouchDelegate(false);
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public final void onShow() {
        setExpandSliderTouchDelegate(true);
        OplusQsDetailRootView oplusQsDetailRootView = this.secondaryView;
        if (oplusQsDetailRootView != null) {
            onShow.1 r1 = new onShow.1();
            r1.this$0 = this;
            VarHandle.storeStoreFence();
            oplusQsDetailRootView.setListener(r1);
        }
    }

    @Override // com.oplusos.systemui.common.viewdialog.model.ViewDialogAdapter
    public void setExpandableView(View view) {
        this.expandableViewRef = new WeakReference<>(view);
    }

    public final void setSecondaryView(OplusQsDetailRootView oplusQsDetailRootView) {
        this.secondaryView = oplusQsDetailRootView;
    }

    public final void showDialog(View view) {
        setExpandableView(view);
        this.viewDialogInteractor.showDialog(this);
    }
}
