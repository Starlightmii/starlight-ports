package com.oplus.systemui.volume.repository;

import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.systemui.volume.OplusVolumeDialogImpl;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.volume.VolumeSettingsValueProxy;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RingModeRepository {
    public static final String ALERT_SLIDER_MODE = "alert_slider_mode";
    public static final Companion Companion = new Companion();
    public static final String TAG = "RingModeRepository";
    public static final String VIBRATE_WHEN_SILENT = "vibrate_when_silent";
    private final MutableStateFlow _threeKeyState;
    private final Context context;
    private final Uri mAlertSliderUri = Settings.Global.getUriFor(ALERT_SLIDER_MODE);
    private final Uri mVibrateWhenSilentUri = Settings.System.getUriFor(VIBRATE_WHEN_SILENT);
    private final SharedFlow settingChanges;
    private final StateFlow threeKeyState;
    private final OplusVolumeDialogImpl volumeInteractor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public RingModeRepository(Context context, OplusVolumeDialogImpl oplusVolumeDialogImpl) {
        this.context = context;
        this.volumeInteractor = oplusVolumeDialogImpl;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(-1);
        this._threeKeyState = MutableStateFlow;
        this.threeKeyState = FlowKt.asStateFlow(MutableStateFlow);
        SharedFlow sharedFlowShareIn = FlowKt.shareIn(FlowKt.callbackFlow(new RingModeRepository$settingChanges$1(this, null)), CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 5000L, 2), 0);
        this.settingChanges = sharedFlowShareIn;
        VolumeFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.isSupportsStealthMode()) {
            int i = Settings.Global.getInt(context.getContentResolver(), ALERT_SLIDER_MODE, -1);
            Integer numValueOf = Integer.valueOf(i);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "Initial mThreeKeyStatus = ", "RingModeRepository-->", "SystemUi--Volume");
            }
            MutableStateFlow.setValue(numValueOf);
        }
        if (VolumeFeatureOption.isSupportsStealthMode()) {
            FlowKt.launchIn(FlowKt.onEach(sharedFlowShareIn, new observeSettings.1(this, (Continuation) null)), CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()));
        }
    }

    public static final void access$handleAlertSliderChange(RingModeRepository ringModeRepository) {
        int i = Settings.Global.getInt(ringModeRepository.context.getContentResolver(), ALERT_SLIDER_MODE, -1);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "mThreeKeyStatus changed = ", "RingModeRepository-->", "SystemUi--Volume");
        }
        ringModeRepository._threeKeyState.setValue(Integer.valueOf(i));
        if (i == 1 && ringModeRepository.shouldVibrate()) {
            ringModeRepository.volumeInteractor.vibrate();
        }
    }

    public static final void access$handleVibrateWhenSilent(RingModeRepository ringModeRepository) {
        if (ringModeRepository.shouldVibrate()) {
            ringModeRepository.volumeInteractor.vibrate();
            LogUtil.i$default(LogUtil.TAG_VOLUME, "RingModeRepository", "StealthMode, vibrate in VIBRATE_WHEN_SILENT_ON", null, 8);
        }
    }

    public final StateFlow getThreeKeyState() {
        return this.threeKeyState;
    }

    public final boolean shouldVibrate() {
        boolean z = VolumeSettingsValueProxy.Companion.getVibrateWhenSilentToggleState(this.context) != 0;
        int i = Settings.Global.getInt(this.context.getContentResolver(), ALERT_SLIDER_MODE, -1);
        return z && (i == 1 || i == 2);
    }
}
