package com.oplus.systemui.qs;

import android.os.Trace;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.qs.ExpandableNode;
import com.android.systemui.qs.QSNode;
import com.android.systemui.qs.brightness.BrightnessMirrorHandler;
import com.android.systemui.qs.brightness.BrightnessSliderController;
import com.android.systemui.qs.brightness.IBrightnessSliderController;
import com.android.systemui.qs.brightness.MirrorController;
import com.android.systemui.qs.deprecated.PagedTileLayout;
import com.android.systemui.qs.deprecated.QSPanel;
import com.android.systemui.qs.deprecated.brightness.BrightnessController;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.qs.policy.OplusQSSecurityController;
import com.oplus.systemui.qs.policy.OplusQSSecurityText;
import com.oplus.systemui.qs.widget.OplusToggleSliderView;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSBottomViewController extends ViewController implements QSNode {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSBottomViewController";
    private BrightnessController mBrightnessController;
    private BrightnessMirrorHandler mBrightnessMirrorHandler;
    private IBrightnessSliderController mBrightnessSliderController;
    public boolean mListening;
    private final OplusQSSecurityController.Factory securityControllerFactory;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQSBottomViewController(OplusQSBottomImpl oplusQSBottomImpl, FalsingManager falsingManager, BrightnessController.Factory factory, BrightnessSliderController.Factory factory2, OplusQSSecurityController.Factory factory3) {
        super(oplusQSBottomImpl);
        this.securityControllerFactory = factory3;
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isSupportVolumeSeekBar()) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) oplusQSBottomImpl.findViewById(2131364713);
        OplusToggleSliderView oplusToggleSliderView = viewGroup != null ? (OplusToggleSliderView) viewGroup.findViewById(2131362351) : null;
        if (oplusToggleSliderView != null) {
            IBrightnessSliderController iBrightnessSliderControllerCreate = factory2.create(getContext(), oplusToggleSliderView);
            this.mBrightnessSliderController = iBrightnessSliderControllerCreate;
            BrightnessController brightnessControllerCreate = factory.create(iBrightnessSliderControllerCreate);
            this.mBrightnessController = brightnessControllerCreate;
            this.mBrightnessMirrorHandler = new BrightnessMirrorHandler(brightnessControllerCreate);
        }
    }

    public final void attachPagedLayout(QSPanel.QSTileLayout qSTileLayout) {
        OplusQSBottomImpl oplusQSBottomImpl = (OplusQSBottomImpl) ((ViewController) this).mView;
        if (oplusQSBottomImpl == null || !(qSTileLayout instanceof PagedTileLayout)) {
            return;
        }
        PagedTileLayout pagedTileLayout = (PagedTileLayout) qSTileLayout;
        pagedTileLayout.setPageIndicator(oplusQSBottomImpl.mPageIndicator);
        oplusQSBottomImpl.mPagedTileLayout = pagedTileLayout;
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("QSBottom: ");
        printWriter.println("    mListening: " + this.mListening);
        printWriter.println("    collapseHeight: " + getCollapseHeight());
        printWriter.println("    expandHeight: " + getExpandHeight());
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    topGap: ", getTopGap(0.0f), printWriter);
        View view = ((ViewController) this).mView;
        if (view != null) {
            BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    translationY: ", ((OplusQSBottomImpl) view).getTranslationY(), printWriter);
            ((OplusQSBottomImpl) ((ViewController) this).mView).dump(printWriter, strArr);
        }
        IBrightnessSliderController iBrightnessSliderController = this.mBrightnessSliderController;
        if ((iBrightnessSliderController != null ? iBrightnessSliderController.getView() : null) instanceof OplusToggleSliderView) {
            IBrightnessSliderController iBrightnessSliderController2 = this.mBrightnessSliderController;
            ((OplusToggleSliderView) (iBrightnessSliderController2 != null ? iBrightnessSliderController2.getView() : null)).dump(printWriter, strArr);
        }
    }

    public /* bridge */ /* synthetic */ View getAnimatorView() {
        return super.getAnimatorView();
    }

    public final float getBottomGap() {
        OplusQSBottomImpl oplusQSBottomImpl = (OplusQSBottomImpl) ((ViewController) this).mView;
        if (oplusQSBottomImpl != null) {
            return oplusQSBottomImpl.mGapBottom;
        }
        return 0.0f;
    }

    public int getCollapseHeight() {
        OplusQSBottomImpl oplusQSBottomImpl = (OplusQSBottomImpl) ((ViewController) this).mView;
        if (oplusQSBottomImpl != null) {
            return oplusQSBottomImpl.getCollapseHeight();
        }
        return 0;
    }

    public int getExpandHeight() {
        OplusQSBottomImpl oplusQSBottomImpl = (OplusQSBottomImpl) ((ViewController) this).mView;
        return oplusQSBottomImpl != null ? oplusQSBottomImpl.getHeight() : getCollapseHeight();
    }

    public /* bridge */ /* synthetic */ int getFloorCount() {
        return super.getFloorCount();
    }

    public final float getTopGap(float f) {
        OplusQSBottomImpl oplusQSBottomImpl = (OplusQSBottomImpl) ((ViewController) this).mView;
        if (oplusQSBottomImpl != null) {
            return oplusQSBottomImpl.getTopGap(f);
        }
        return 0.0f;
    }

    public View getView() {
        View view = ((ViewController) this).mView;
        view.getClass();
        return view;
    }

    public final void onInit() {
        IBrightnessSliderController iBrightnessSliderController = this.mBrightnessSliderController;
        if (iBrightnessSliderController != null) {
            iBrightnessSliderController.doInit();
        }
    }

    public final void onQsColorStateChanged(int i) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "onQsColorStateChanged updateTextColor qsColorState: "), null, 8);
        onQsColorStateChanged.1 r0 = new onQsColorStateChanged.1();
        r0.this$0 = this;
        r0.$qsColorState = i;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(r0);
    }

    public final void onViewAttached() {
        Trace.traceBegin(8L, "OplusQSBottomViewController onViewAttached");
        BrightnessMirrorHandler brightnessMirrorHandler = this.mBrightnessMirrorHandler;
        if (brightnessMirrorHandler != null) {
            brightnessMirrorHandler.onQsPanelAttached();
        }
        BrightnessController brightnessController = this.mBrightnessController;
        if (brightnessController != null) {
            brightnessController.registerBrightnessObserver();
        }
        OplusQSSecurityText oplusQSSecurityText = (OplusQSSecurityText) ((OplusQSBottomImpl) ((ViewController) this).mView).findViewById(2131364767);
        if (oplusQSSecurityText != null) {
            oplusQSSecurityText.setQSSecurityInteractor(this.securityControllerFactory.create());
        }
        Trace.traceEnd(8L);
    }

    public final void onViewDetached() {
        BrightnessMirrorHandler brightnessMirrorHandler = this.mBrightnessMirrorHandler;
        if (brightnessMirrorHandler != null) {
            brightnessMirrorHandler.onQsPanelDettached();
        }
        BrightnessController brightnessController = this.mBrightnessController;
        if (brightnessController != null) {
            brightnessController.unregisterBrightnessObserver();
        }
        setListening(false);
    }

    public final void setBrightnessMirror(MirrorController mirrorController) {
        BrightnessMirrorHandler brightnessMirrorHandler = this.mBrightnessMirrorHandler;
        if (brightnessMirrorHandler != null) {
            brightnessMirrorHandler.setController(mirrorController);
        }
    }

    public /* bridge */ /* synthetic */ void setContainer(ExpandableNode.OnStateListener onStateListener) {
        super.setContainer(onStateListener);
    }

    public void setCustomizing(boolean z) {
        OplusQSBottomImpl oplusQSBottomImpl = (OplusQSBottomImpl) ((ViewController) this).mView;
        if (oplusQSBottomImpl != null) {
            oplusQSBottomImpl.setVisibility(z ? 4 : 0);
        }
    }

    public /* bridge */ /* synthetic */ void setExpanded(boolean z) {
        super.setExpanded(z);
    }

    public void setExpansion(float f) {
        OplusQSBottomImpl oplusQSBottomImpl = (OplusQSBottomImpl) ((ViewController) this).mView;
        if (oplusQSBottomImpl != null) {
            oplusQSBottomImpl.setExpansion(f);
        }
    }

    public void setListening(boolean z) {
        if (this.mListening == z) {
            return;
        }
        this.mListening = z;
        BrightnessController brightnessController = this.mBrightnessController;
        if (z) {
            if (brightnessController != null) {
                brightnessController.checkRestrictionAndSetEnabled();
            }
            BrightnessController brightnessController2 = this.mBrightnessController;
            if (brightnessController2 != null) {
                brightnessController2.registerCallbacks();
            }
        } else if (brightnessController != null) {
            brightnessController.unregisterCallbacks();
        }
        OplusQSBottomImpl oplusQSBottomImpl = (OplusQSBottomImpl) ((ViewController) this).mView;
        if (oplusQSBottomImpl != null) {
            oplusQSBottomImpl.setListening(z);
        }
    }

    public /* bridge */ /* synthetic */ float setTranslationY(float f, int i, Function1 function1) {
        return super.setTranslationY(f, i, function1);
    }
}
