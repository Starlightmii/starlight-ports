package com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel;

import android.graphics.RectF;
import com.android.systemui.statusbar.layout.ui.viewmodel.AppHandlesViewModel;
import com.android.systemui.statusbar.layout.ui.viewmodel.StatusBarBoundsViewModel;
import com.android.systemui.statusbar.phone.domain.interactor.IsAreaDark;
import com.android.systemui.statusbar.pipeline.battery.ui.viewmodel.BatteryViewModel;
import com.android.systemui.statusbar.pipeline.shared.ui.model.ChipsVisibilityModel;
import com.android.systemui.statusbar.pipeline.shared.ui.viewmodel.HomeStatusBarViewModel;
import com.android.systemui.statusbar.pipeline.shared.ui.viewmodel.StatusBarOperatorNameViewModel;
import com.android.systemui.statusbar.systemstatusicons.domain.interactor.SystemStatusIconBlocklistInteractor;
import com.android.systemui.statusbar.systemstatusicons.ui.viewmodel.SystemStatusIconsViewModel;
import java.util.List;
import kotlin.Pair;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusHomeStatusBarViewModel extends HomeStatusBarViewModel {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface OplusHomeStatusBarViewModelFactory {
        OplusHomeStatusBarViewModel create(HomeStatusBarViewModel homeStatusBarViewModel);
    }

    /* synthetic */ Object activate(Continuation continuation);

    StateFlow getAnimationState();

    /* synthetic */ AppHandlesViewModel.Factory getAppHandlesViewModelFactory();

    /* synthetic */ Flow getAreNotificationsLightsOut();

    /* synthetic */ IsAreaDark getAreaDark();

    /* synthetic */ Flow getAreaTint();

    /* synthetic */ Flow getContentArea();

    MutableStateFlow getHasFullscreenPrivacyIcon();

    /* synthetic */ boolean getHasStatusBarNotifications();

    Flow getHideSecBySeeding();

    /* synthetic */ Flow getIconBlockList();

    /* synthetic */ StateFlow getMediaProjectionStopDialogDueToCallEndedState();

    /* synthetic */ ChipsVisibilityModel getOngoingActivityChips();

    /* synthetic */ StatusBarOperatorNameViewModel getOperatorNameViewModel();

    /* synthetic */ List getPopupChips();

    /* synthetic */ Flow getShouldShowOperatorNameView();

    /* synthetic */ StatusBarBoundsViewModel.Factory getStatusBarBoundsViewModelFactory();

    Flow getStatusBarClockShowSeconds();

    /* synthetic */ StateFlow getSystemInfoCombinedVis();

    /* synthetic */ SystemStatusIconBlocklistInteractor getSystemStatusIconBlockListInteractor();

    /* synthetic */ SystemStatusIconsViewModel.Factory getSystemStatusIconsViewModelFactory();

    /* synthetic */ Flow getTransitionFromLockscreenToDreamStartedEvent();

    /* synthetic */ BatteryViewModel.BasedOnUserSetting.Factory getUnifiedBatteryViewModel();

    /* synthetic */ boolean getUseDesktopStatusBar();

    /* synthetic */ Flow isClockVisible();

    Flow isClockVisibleWithSeedling();

    Flow isDynamicIconVisible();

    /* synthetic */ StateFlow isHomeStatusBarAllowed();

    /* synthetic */ Flow isNotificationIconContainerVisible();

    Flow isPromptViewVisible();

    Flow isSeedlingContainerVisible();

    /* synthetic */ StateFlow isTransitioningFromLockscreenToOccluded();

    void notifyClockLayoutAreasChanged(Pair<Integer, Integer> pair, Pair<Integer, Integer> pair2);

    /* synthetic */ void onChipBoundsChanged(String str, RectF rectF);
}
