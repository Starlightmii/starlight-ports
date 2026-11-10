package com.oplus.systemui.qs.detail.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.FileProvider$;
import android.view.ViewGroup;
import com.android.systemui.keyguard.data.repository.KeyguardRepository;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.oplus.systemui.notification.base.OplusNotificationQsExt;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.SepQSResPool;
import com.oplus.systemui.qs.base.res.StdQSResPool;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.detail.data.QSDetailRepository;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.inline.OplusQsInlineHostView;
import com.oplusos.systemui.common.inline.SystemUIInlineViewHelper;
import com.oplusos.systemui.common.util.AppKeepAliveManager;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.viewdialog.domain.SysUIViewDialogInteractor;
import dagger.Lazy;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailContainerViewModel implements OplusQsInlineHostView.OnAnalyzeSysInlineRequestListener {
    private final ActivityStarter activityStarter;
    private final StateFlow backgroundBuilder;
    private final StateFlow backgroundOutline;
    private final String cachedPackageName;
    private final DetailAdapter detailAdapter;
    private final DetailAdapter.DetailHeightType detailHeightType;
    private final QSDetailRepository detailRepository;
    private final KeyguardRepository keyguardRepository;
    private final String logTag;
    private final Flow marginLayoutParams;
    private final OplusNotificationQsExt notificationQsExt;
    private final PanelInteractor panelInteractor;
    private final Lazy separateNotificationAndQSEx;
    private final Flow updateConfigEvent;
    private final SysUIViewDialogInteractor viewDialogInteractor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Factory {
        QSDetailContainerViewModel create(DetailAdapter detailAdapter, QSDetailRepository qSDetailRepository);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DetailAdapter.DetailHeightType.values().length];
            try {
                iArr[DetailAdapter.DetailHeightType.IMMUTABLE_ONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DetailAdapter.DetailHeightType.IMMUTABLE_TWO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DetailAdapter.DetailHeightType.CHANGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DetailAdapter.DetailHeightType.CUSTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public QSDetailContainerViewModel(DetailAdapter detailAdapter, QSDetailRepository qSDetailRepository, OplusNotificationQsExt oplusNotificationQsExt, ActivityStarter activityStarter, SysUIViewDialogInteractor sysUIViewDialogInteractor, KeyguardRepository keyguardRepository, Lazy lazy, PanelInteractor panelInteractor) {
        this.detailAdapter = detailAdapter;
        this.detailRepository = qSDetailRepository;
        this.notificationQsExt = oplusNotificationQsExt;
        this.activityStarter = activityStarter;
        this.viewDialogInteractor = sysUIViewDialogInteractor;
        this.keyguardRepository = keyguardRepository;
        this.separateNotificationAndQSEx = lazy;
        this.panelInteractor = panelInteractor;
        this.logTag = FileProvider$.ExternalSyntheticOutline0.m("QSDetailContainerViewModel_", detailAdapter.getTag());
        this.detailHeightType = detailAdapter.getDetailHeightType();
        String tag = detailAdapter.getTag();
        int iIndexOf$default = StringsKt__StringsKt.indexOf$default(tag, "(", 0, false, 6);
        int iIndexOf$default2 = StringsKt__StringsKt.indexOf$default(tag, "/", 0, false, 6);
        this.cachedPackageName = (iIndexOf$default == -1 || iIndexOf$default2 == -1 || iIndexOf$default2 <= iIndexOf$default) ? "" : tag.substring(iIndexOf$default + 1, iIndexOf$default2);
        this.updateConfigEvent = qSDetailRepository.getUpdateConfigEvent();
        this.backgroundBuilder = QsColorUtil.INSTANCE.getEnableSeparateNotificationAndQS() ? SepQSResPool.INSTANCE.getDetailPanelDrawable() : StdQSResPool.INSTANCE.getDetailPanelDrawable();
        this.backgroundOutline = BaseQSResPool.INSTANCE.getDetailPanelOutline();
        this.marginLayoutParams = FlowKt.onEach(FlowKt.combine(qSDetailRepository.getUpdateConfigEvent(), getDetailHeightFlow(), new QSDetailContainerViewModel$marginLayoutParams$1(3, null)), new QSDetailContainerViewModel$marginLayoutParams$2(this, null));
    }

    public static final void access$updateMarginLayoutParams(QSDetailContainerViewModel qSDetailContainerViewModel, ViewGroup.MarginLayoutParams marginLayoutParams) {
        Context themeContext = qSDetailContainerViewModel.detailRepository.getThemeContext();
        Resources resources = themeContext.getResources();
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet()) {
            resources.getClass();
            int iCustomLandWidth = qSDetailContainerViewModel.detailAdapter.customLandWidth();
            if (iCustomLandWidth <= 0) {
                iCustomLandWidth = resources.getDimensionPixelSize(2131171740);
            }
            marginLayoutParams.width = iCustomLandWidth;
            marginLayoutParams.topMargin = resources.getDimensionPixelSize(2131171632);
            marginLayoutParams.bottomMargin = 0;
            marginLayoutParams.setMarginStart(resources.getDimensionPixelSize(2131171631));
            marginLayoutParams.setMarginEnd(0);
            return;
        }
        int dimensionPixelSize = -1;
        if (!DeviceUtil.isLandscape(themeContext)) {
            resources.getClass();
            if (DeviceUtil.shouldUseLargeFoldConfig(resources)) {
                int iCustomLandWidth2 = qSDetailContainerViewModel.detailAdapter.customLandWidth();
                if (iCustomLandWidth2 <= 0) {
                    iCustomLandWidth2 = resources.getDimensionPixelSize(2131171740);
                }
                dimensionPixelSize = iCustomLandWidth2;
            }
            marginLayoutParams.width = dimensionPixelSize;
            int dimensionPixelSize2 = resources.getDimensionPixelSize(2131171631);
            marginLayoutParams.setMarginStart(dimensionPixelSize2);
            marginLayoutParams.setMarginEnd(dimensionPixelSize2);
            marginLayoutParams.topMargin = 0;
            marginLayoutParams.bottomMargin = 0;
            return;
        }
        resources.getClass();
        int iCustomLandWidth3 = qSDetailContainerViewModel.detailAdapter.customLandWidth();
        if (iCustomLandWidth3 <= 0) {
            iCustomLandWidth3 = resources.getDimensionPixelSize(2131171740);
        }
        marginLayoutParams.width = iCustomLandWidth3;
        if (qSDetailContainerViewModel.detailHeightType == DetailAdapter.DetailHeightType.CHANGE) {
            dimensionPixelSize = -2;
        } else if (DeviceUtil.shouldUseLargeFoldConfig(resources)) {
            dimensionPixelSize = resources.getDimensionPixelSize(2131171658);
        }
        marginLayoutParams.height = dimensionPixelSize;
        int dimensionPixelSize3 = resources.getDimensionPixelSize(2131171692);
        marginLayoutParams.topMargin = dimensionPixelSize3;
        marginLayoutParams.bottomMargin = dimensionPixelSize3;
        marginLayoutParams.setMarginStart(0);
        marginLayoutParams.setMarginEnd(0);
    }

    private final Flow getDetailHeightFlow() {
        int dimensionPixelSize;
        Resources resources = this.detailRepository.getThemeContext().getResources();
        Flow detailHeightFlow = this.detailAdapter.getDetailHeightFlow();
        if (detailHeightFlow != null) {
            return detailHeightFlow;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[this.detailHeightType.ordinal()];
        if (i == 1) {
            dimensionPixelSize = resources.getDimensionPixelSize(2131171697);
        } else if (i == 2) {
            dimensionPixelSize = resources.getDimensionPixelSize(2131171699);
        } else if (i == 3) {
            dimensionPixelSize = -2;
        } else {
            if (i != 4) {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return null;
            }
            dimensionPixelSize = this.detailAdapter.customHeight();
        }
        return FlowKt.flowOf(Integer.valueOf(dimensionPixelSize));
    }

    public final StateFlow getBackgroundBuilder() {
        return this.backgroundBuilder;
    }

    public final StateFlow getBackgroundOutline() {
        return this.backgroundOutline;
    }

    public final Flow getMarginLayoutParams() {
        return this.marginLayoutParams;
    }

    public final int getPaddingBottom(Resources resources) {
        if (this.detailAdapter.hasFooter()) {
            return 0;
        }
        Integer numPaddingBottom = this.detailAdapter.paddingBottom();
        return numPaddingBottom != null ? numPaddingBottom.intValue() : resources.getDimensionPixelSize(2131171637);
    }

    public final CharSequence getTitle() {
        return this.detailAdapter.getTitle();
    }

    public final Flow getUpdateConfigEvent() {
        return this.updateConfigEvent;
    }

    public final boolean hasHint() {
        return this.detailAdapter.hasHint();
    }

    @Override // com.oplusos.systemui.common.inline.OplusQsInlineHostView.OnAnalyzeSysInlineRequestListener
    public void onAnalyzeSysInlineRequest(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (bundle.getBoolean(SystemUIInlineViewHelper.KEY_COLLAPSE_PANEL)) {
            LogUtil.i$default(LogUtil.TAG_QS, this.logTag, "onAnalyzeSysInlineRequestCollapsePanel:", null, 8);
            if (!this.keyguardRepository.isKeyguardShowing()) {
                this.panelInteractor.forceCollapsePanels();
            } else if (((OplusSeparateNotificationAndQSEx) this.separateNotificationAndQSEx.get()).enableSeparateNotificationAndQS()) {
                ((OplusSeparateNotificationAndQSEx) this.separateNotificationAndQSEx.get()).collapseQSPanel(true, true, false);
            } else {
                this.panelInteractor.animateCollapseQs();
            }
        }
        if (bundle.containsKey(SystemUIInlineViewHelper.KEY_START_ACTIVITY)) {
            LogUtil.i$default(LogUtil.TAG_QS, this.logTag, "onAnalyzeSysInlineRequestStartActivity: ", null, 8);
            Intent intent = (Intent) bundle.getParcelable(SystemUIInlineViewHelper.KEY_START_ACTIVITY, Intent.class);
            if (intent != null) {
                this.activityStarter.postStartActivityDismissingKeyguard(intent, 0);
                this.viewDialogInteractor.dismissDialog("instant");
            }
        }
    }

    public final void onCreate() {
        LogUtil.i$default(LogUtil.TAG_QS, this.logTag, "onCreate:", null, 8);
        if (this.cachedPackageName.length() > 0) {
            AppKeepAliveManager.INSTANCE.keepAlive(this.cachedPackageName);
        }
        this.notificationQsExt.setDetailShowingDialog(true);
    }

    public final void onDestroy() {
        LogUtil.i$default(LogUtil.TAG_QS, this.logTag, "onDestroy:", null, 8);
        if (this.cachedPackageName.length() > 0) {
            AppKeepAliveManager.INSTANCE.unKeepAlive(this.cachedPackageName);
        }
        this.notificationQsExt.setDetailShowingDialog(false);
    }
}
