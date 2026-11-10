package com.oplus.systemui.qs.privacy;

import android.permission.PermissionManager;
import android.safetycenter.SafetyCenterManager;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.appops.AppOpsController;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.privacy.OngoingPrivacyChip;
import com.android.systemui.privacy.PrivacyDialogController;
import com.android.systemui.privacy.PrivacyDialogControllerV2;
import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.privacy.logging.PrivacyLogger;
import com.android.systemui.qs.HeaderPrivacyIconsController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.domain.interactor.ShadeDialogContextInteractor;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import dagger.Lazy;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeaderPrivacyIconsControllerFactory {
    private final ActivityStarter activityStarter;
    private final AppOpsController appOpsController;
    private final Executor backgroundExecutor;
    private final BroadcastDispatcher broadcastDispatcher;
    private final DeviceProvisionedController deviceProvisionedController;
    private final FeatureFlags featureFlags;
    private final Lazy oplusPrivacyDialogEx;
    private final PermissionManager permissionManager;
    private final PrivacyDialogController privacyDialogController;
    private final PrivacyDialogControllerV2 privacyDialogControllerV2;
    private final PrivacyItemController privacyItemController;
    private final PrivacyLogger privacyLogger;
    private final SafetyCenterManager safetyCenterManager;
    private final ShadeDialogContextInteractor shadeDialogContextInteractor;
    private final UiEventLogger uiEventLogger;
    private final Executor uiExecutor;
    private final UserTracker userTracker;

    public HeaderPrivacyIconsControllerFactory(PrivacyItemController privacyItemController, UiEventLogger uiEventLogger, PrivacyDialogController privacyDialogController, PrivacyDialogControllerV2 privacyDialogControllerV2, PrivacyLogger privacyLogger, PermissionManager permissionManager, Executor executor, Executor executor2, ActivityStarter activityStarter, AppOpsController appOpsController, BroadcastDispatcher broadcastDispatcher, SafetyCenterManager safetyCenterManager, DeviceProvisionedController deviceProvisionedController, FeatureFlags featureFlags, ShadeDialogContextInteractor shadeDialogContextInteractor, UserTracker userTracker, Lazy lazy) {
        this.privacyItemController = privacyItemController;
        this.uiEventLogger = uiEventLogger;
        this.privacyDialogController = privacyDialogController;
        this.privacyDialogControllerV2 = privacyDialogControllerV2;
        this.privacyLogger = privacyLogger;
        this.permissionManager = permissionManager;
        this.backgroundExecutor = executor;
        this.uiExecutor = executor2;
        this.activityStarter = activityStarter;
        this.appOpsController = appOpsController;
        this.broadcastDispatcher = broadcastDispatcher;
        this.safetyCenterManager = safetyCenterManager;
        this.deviceProvisionedController = deviceProvisionedController;
        this.featureFlags = featureFlags;
        this.shadeDialogContextInteractor = shadeDialogContextInteractor;
        this.userTracker = userTracker;
        this.oplusPrivacyDialogEx = lazy;
    }

    public final HeaderPrivacyIconsController createHeaderPrivacyIconsController(OngoingPrivacyChip ongoingPrivacyChip, StatusIconContainer statusIconContainer) {
        return new HeaderPrivacyIconsController(this.privacyItemController, this.uiEventLogger, ongoingPrivacyChip, this.privacyDialogController, this.privacyDialogControllerV2, this.privacyLogger, statusIconContainer, this.permissionManager, this.backgroundExecutor, this.uiExecutor, this.activityStarter, this.appOpsController, this.broadcastDispatcher, this.safetyCenterManager, this.deviceProvisionedController, this.featureFlags, this.shadeDialogContextInteractor, this.userTracker, this.oplusPrivacyDialogEx);
    }
}
