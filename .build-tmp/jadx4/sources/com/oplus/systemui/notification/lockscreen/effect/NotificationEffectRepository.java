package com.oplus.systemui.notification.lockscreen.effect;

import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SystemSettings;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationEffectRepository {
    public static final Companion Companion = new Companion();
    private static final String STROKE_ENABLE_KEY = "system_material_stroke_enable";
    private static final String TAG = "CapsuleEffectRepository";
    public static boolean isSupportBlurStroke;
    private final StateFlow isStrokeLineEnable;
    private final SystemSettings systemSettings;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationEffectRepository(SystemSettings systemSettings, SelectedUserInteractor selectedUserInteractor) {
        this.systemSettings = systemSettings;
        this.isStrokeLineEnable = FlowKt.stateIn(FlowKt.transformLatest(selectedUserInteractor.getSelectedUser(), new NotificationEffectRepository$special$$inlined$flatMapLatest$1(null, this)), systemSettings.getSettingsScope(), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), Boolean.FALSE);
    }

    public static final boolean access$iSupportStrokeSettingEnabled(NotificationEffectRepository notificationEffectRepository, int i) {
        return notificationEffectRepository.systemSettings.getIntForUser(STROKE_ENABLE_KEY, 0, i) == 1;
    }

    public final StateFlow isStrokeLineEnable() {
        return this.isStrokeLineEnable;
    }
}
