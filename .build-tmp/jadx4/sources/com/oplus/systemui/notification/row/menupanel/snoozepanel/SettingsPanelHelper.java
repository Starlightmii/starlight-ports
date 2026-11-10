package com.oplus.systemui.notification.row.menupanel.snoozepanel;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment$5$;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.notification.row.menupanel.snoozepanel.controller.NotificationController;
import com.oplus.systemui.utils.OplusThemeApplyUtil;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SettingsPanelHelper implements ConfigurationController.ConfigurationListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationSettingPane";
    private final Handler backgroundHandler;
    private final Context context;
    private NormalSettingsContent currentPanelContent;
    private final NotificationController notificationController;
    private Function0 onDismissAction;
    private SettingsPanelModel settingsModel;
    private final Lazy stackScrollLayoutControllerLazy;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SettingsPanelHelper(Context context, Handler handler, Lazy lazy) {
        this.context = context;
        this.backgroundHandler = handler;
        this.stackScrollLayoutControllerLazy = lazy;
        this.notificationController = new NotificationController(context, this, handler);
    }

    public final View createSettingPanel(ExpandableNotificationRow expandableNotificationRow, Function0 function0) {
        View viewInflate = LayoutInflater.from(OplusThemeApplyUtil.toAppContextTheme(this.context)).inflate(2131559223, (ViewGroup) null, false);
        NormalSettingsContent normalSettingsContent = viewInflate instanceof NormalSettingsContent ? (NormalSettingsContent) viewInflate : null;
        SettingsPanelModel settingsPanelModel = new SettingsPanelModel(this.context);
        settingsPanelModel.setContentType(7);
        this.notificationController.setRow(expandableNotificationRow);
        this.notificationController.resolveSettingsModel(settingsPanelModel);
        this.settingsModel = settingsPanelModel;
        if (normalSettingsContent != null) {
            normalSettingsContent.applyModel(settingsPanelModel);
        }
        this.currentPanelContent = normalSettingsContent;
        this.onDismissAction = function0;
        return normalSettingsContent;
    }

    public final void dismissSettingsPanel(String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Fragment$5$.ExternalSyntheticOutline0.m("NotificationSettingPane-->", str, "SystemUi--Notification");
        }
        SettingsPanelModel settingsPanelModel = this.settingsModel;
        if (settingsPanelModel != null) {
            settingsPanelModel.resetModel();
        }
        this.currentPanelContent = null;
        this.settingsModel = null;
        this.notificationController.reset();
        Function0 function0 = this.onDismissAction;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final NormalSettingsContent getCurrentPanelContent() {
        return this.currentPanelContent;
    }

    public final SettingsPanelModel getSettingsModel() {
        return this.settingsModel;
    }

    public final Lazy getStackScrollLayoutControllerLazy() {
        return this.stackScrollLayoutControllerLazy;
    }

    public final void resolveSnoozeClick() {
        this.notificationController.resolveClickEvent(NotificationController.ACTION_SNOOZE);
    }

    public final void setCurrentPanelContent(NormalSettingsContent normalSettingsContent) {
        this.currentPanelContent = normalSettingsContent;
    }

    public final void setSettingsModel(SettingsPanelModel settingsPanelModel) {
        this.settingsModel = settingsPanelModel;
    }
}
