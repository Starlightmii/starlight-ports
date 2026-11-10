package com.oplus.systemui.separate.data.repository;

import com.android.systemui.settings.UserTracker;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SystemSettings;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelModeRepository {
    public static final Companion Companion = new Companion();
    private static final String DEFAULT_KEY = "separate_noti_and_qs_enable_default";
    private static final String KEY = "separate_noti_and_qs_enable";
    private static final String TAG = "OplusPanelModeRepository";
    private final StateFlow enableSeparateNotificationAndQS;
    public volatile int lastPublishedUserId;
    private final SystemSettings systemSettings;
    private final UserTracker userTracker;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusPanelModeRepository(SystemSettings systemSettings, SelectedUserInteractor selectedUserInteractor, UserTracker userTracker) {
        this.systemSettings = systemSettings;
        this.userTracker = userTracker;
        this.enableSeparateNotificationAndQS = FlowKt.stateIn(FlowKt.onEach(FlowKt.transformLatest(selectedUserInteractor.getSelectedUser(), new OplusPanelModeRepository$special$$inlined$flatMapLatest$1(this, null)), new OplusPanelModeRepository$enableSeparateNotificationAndQS$2(this, null)), systemSettings.getSettingsScope(), SharingStarted.Companion.getEagerly(), Boolean.valueOf(querySeparateMode(userTracker.getUserId())));
        this.lastPublishedUserId = userTracker.getUserId();
    }

    public final StateFlow getEnableSeparateNotificationAndQS() {
        return this.enableSeparateNotificationAndQS;
    }

    public final boolean querySeparateMode(int i) {
        String stringForUser = this.systemSettings.getStringForUser(KEY, i);
        this.lastPublishedUserId = i;
        if (stringForUser != null) {
            return "true".equals(stringForUser);
        }
        String string = this.systemSettings.getString(DEFAULT_KEY);
        if (string != null) {
            this.systemSettings.putStringForUser(KEY, string, i);
        }
        return "true".equals(string);
    }

    public final boolean resolveSeparateMode() {
        int userId = this.userTracker.getUserId();
        return userId == this.lastPublishedUserId ? ((Boolean) this.enableSeparateNotificationAndQS.getValue()).booleanValue() : querySeparateMode(userId);
    }
}
