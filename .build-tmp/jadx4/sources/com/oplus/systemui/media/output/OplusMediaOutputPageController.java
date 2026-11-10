package com.oplus.systemui.media.output;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;
import com.android.systemui.media.dialog.MediaSwitchingController;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.plugins.qs.OplusQsDialogSupply;
import com.oplus.systemui.common.dialog.OplusQSDetailDialogProvider;
import com.oplus.systemui.common.dialog.OplusQsDialogSupplyExportApi;
import com.oplus.systemui.common.dialog.OplusThemeSystemUiDialog;
import com.oplus.systemui.media.output.data.OutputAudioSharingInteractor;
import com.oplus.systemui.media.output.page.OplusPageManager;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import java.lang.invoke.VarHandle;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaOutputPageController implements DetailAdapter, OplusThemeSystemUiDialog.ILifeCycleCallback {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusMediaOutputPageController";
    private final OutputAudioSharingInteractor audioSharingInteractor;
    private View detailContainer;
    private OplusQSDetailDialogProvider dialogProvider;
    private Flow height;
    public boolean isMediaDetailToMediaOutputAnimating;
    private final MediaDataManager mediaDataManager;
    private MediaSwitchingController mediaSwitchingController;
    private OplusPageManager pagesManager;
    private final SeekBarViewModel.Factory seekBarViewModelFactory;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusMediaOutputPageController(OutputAudioSharingInteractor outputAudioSharingInteractor, SeekBarViewModel.Factory factory, MediaDataManager mediaDataManager) {
        this.audioSharingInteractor = outputAudioSharingInteractor;
        this.seekBarViewModelFactory = factory;
        this.mediaDataManager = mediaDataManager;
    }

    public static final void access$refreshDetailContainerSize(OplusMediaOutputPageController oplusMediaOutputPageController) {
        OplusPageManager oplusPageManager;
        Context context;
        int iCustomLandWidth;
        MutableStateFlow detailHeightFlow = oplusMediaOutputPageController.getDetailHeightFlow();
        MutableStateFlow mutableStateFlow = detailHeightFlow instanceof MutableStateFlow ? detailHeightFlow : null;
        if (mutableStateFlow == null) {
            return;
        }
        int iCustomHeight = oplusMediaOutputPageController.customHeight();
        if (((Number) mutableStateFlow.getValue()).intValue() == iCustomHeight) {
            mutableStateFlow.setValue(Integer.valueOf(iCustomHeight ^ 1));
        }
        mutableStateFlow.setValue(Integer.valueOf(iCustomHeight));
        View view = oplusMediaOutputPageController.detailContainer;
        if (view == null || (oplusPageManager = oplusMediaOutputPageController.pagesManager) == null || (context = oplusPageManager.context()) == null || !DeviceUtil.isLandscape(context)) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams == null || (iCustomLandWidth = oplusMediaOutputPageController.customLandWidth()) <= 0 || marginLayoutParams.width == iCustomLandWidth) {
            return;
        }
        marginLayoutParams.width = iCustomLandWidth;
        view.setLayoutParams(marginLayoutParams);
    }

    public final void addPage(OplusMediaOutputPage oplusMediaOutputPage) {
        OplusPageManager oplusPageManager = this.pagesManager;
        if (oplusPageManager != null) {
            oplusPageManager.addPage(oplusMediaOutputPage);
        }
    }

    /* JADX WARN: Code duplicated, block: B:12:0x003e  */
    public View createDetailView(Context context, View view, ViewGroup viewGroup, boolean z) {
        OplusMediaOutputPageController oplusMediaOutputPageController;
        Context context2;
        if (this.pagesManager != null || viewGroup == null || context == null) {
            oplusMediaOutputPageController = this;
            context2 = context;
        } else {
            View viewFindViewById = view.findViewById(2131362831);
            this.detailContainer = viewFindViewById;
            if (viewFindViewById != null) {
                OplusPageManager oplusPageManager = new OplusPageManager(viewGroup, viewFindViewById);
                createDetailView.1.1.1 r11 = new createDetailView.1.1.1();
                r11.this$0 = this;
                VarHandle.storeStoreFence();
                oplusPageManager.setPageChangedListener(r11);
                this.pagesManager = oplusPageManager;
            }
            MediaSwitchingController mediaSwitchingController = this.mediaSwitchingController;
            if (mediaSwitchingController != null) {
                oplusMediaOutputPageController = this;
                context2 = context;
                oplusMediaOutputPageController.addPage(new OplusMediaOutputSwitchingPage(context2, oplusMediaOutputPageController, this.audioSharingInteractor, mediaSwitchingController, this.seekBarViewModelFactory, this.mediaDataManager, this.dialogProvider));
            } else {
                oplusMediaOutputPageController = this;
                context2 = context;
            }
        }
        return oplusMediaOutputPageController.createDetailView(context2, viewGroup, z);
    }

    public final int customHeight() {
        Context context;
        OplusPageManager oplusPageManager = this.pagesManager;
        if (oplusPageManager == null || (context = oplusPageManager.context()) == null) {
            return 0;
        }
        OplusPageManager oplusPageManager2 = this.pagesManager;
        return (oplusPageManager2 != null ? (OplusMediaOutputPage) oplusPageManager2.curPage() : null) instanceof OplusMediaOutputSwitchingPage ? context.getResources().getDimensionPixelSize(2131171693) : context.getResources().getDimensionPixelSize(2131171697);
    }

    public final int customLandWidth() {
        Context context;
        OplusPageManager oplusPageManager = this.pagesManager;
        if (oplusPageManager == null || (context = oplusPageManager.context()) == null) {
            return 0;
        }
        OplusPageManager oplusPageManager2 = this.pagesManager;
        return (oplusPageManager2 != null ? (OplusMediaOutputPage) oplusPageManager2.curPage() : null) instanceof OplusMediaOutputSwitchingPage ? context.getResources().getDimensionPixelSize(2131171694) : context.getResources().getDimensionPixelSize(2131171740);
    }

    public Flow getDetailHeightFlow() {
        Flow flow = this.height;
        if (flow != null) {
            return flow;
        }
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Integer.valueOf(customHeight()));
        this.height = MutableStateFlow;
        return MutableStateFlow;
    }

    public DetailAdapter.DetailHeightType getDetailHeightType() {
        return DetailAdapter.DetailHeightType.CUSTOM;
    }

    public final OplusQSDetailDialogProvider getDialogProvider() {
        return this.dialogProvider;
    }

    public final MediaSwitchingController getMediaSwitchingController() {
        return this.mediaSwitchingController;
    }

    public int getMetricsCategory() {
        return 1657;
    }

    public OplusQsDialogSupply getQSDialogSupply() {
        return OplusQsDialogSupplyExportApi.INSTANCE;
    }

    public String getTag() {
        return TAG;
    }

    public final boolean hasHeader() {
        return false;
    }

    public final boolean onBackAction() {
        OplusPageManager oplusPageManager;
        OplusPageManager oplusPageManager2 = this.pagesManager;
        boolean z = (oplusPageManager2 != null ? oplusPageManager2.pageSize() : 0) > 1;
        if (z && (oplusPageManager = this.pagesManager) != null) {
            oplusPageManager.back();
        }
        return z;
    }

    public final void onDismiss() {
        super.onDismiss();
        this.audioSharingInteractor.stopScan();
        this.mediaSwitchingController = null;
        this.dialogProvider = null;
        this.isMediaDetailToMediaOutputAnimating = false;
        OplusPageManager oplusPageManager = this.pagesManager;
        if (oplusPageManager != null) {
            QsStatisticUtil.collectMediaPanelActionClick(oplusPageManager.context(), "14");
            oplusPageManager.dismiss();
        }
        this.pagesManager = null;
        this.detailContainer = null;
        this.height = null;
    }

    public final void onStart() {
        onShow();
    }

    public final void onStop() {
        onDismiss();
    }

    public Integer paddingBottom() {
        return 0;
    }

    public final void removePage(OplusMediaOutputPage oplusMediaOutputPage) {
        OplusPageManager oplusPageManager = this.pagesManager;
        if (oplusPageManager != null) {
            oplusPageManager.removePage(oplusMediaOutputPage, false);
        }
    }

    public final void setDialogProvider(OplusQSDetailDialogProvider oplusQSDetailDialogProvider) {
        this.dialogProvider = oplusQSDetailDialogProvider;
    }

    public final void setMediaDetailToMediaOutputAnimating(boolean z) {
        this.isMediaDetailToMediaOutputAnimating = z;
    }

    public final void setMediaSwitchingController(MediaSwitchingController mediaSwitchingController) {
        this.mediaSwitchingController = mediaSwitchingController;
    }

    public final void switchPage(OplusMediaOutputPage oplusMediaOutputPage) {
        OplusPageManager oplusPageManager = this.pagesManager;
        if (oplusPageManager != null) {
            oplusPageManager.switchPage(oplusMediaOutputPage);
        }
    }

    public View createDetailView(Context context, ViewGroup viewGroup, boolean z) {
        return null;
    }
}
