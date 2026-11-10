package com.oplus.systemui.qs.slider;

import android.content.Context;
import android.util.SparseArray;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.plugins.qs.NonNullObservableField;
import com.android.systemui.plugins.qs.QSVolume;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsVolumeController$mVolumeCallback$1 implements OplusQsVolumeController.VolumeCallbackAdapter {
    public boolean isFirstUpdate;
    public /* synthetic */ OplusQsVolumeController this$0;

    @Override // com.oplus.systemui.qs.slider.OplusQsVolumeController.VolumeCallbackAdapter
    public final void onAccessibilityModeChanged(Boolean bool) {
        this.this$0.getA11yStreamShow().set(Boolean.valueOf(bool != null ? bool.booleanValue() : false));
    }

    public final void onCaptionEnabledStateChanged(Boolean bool, Boolean bool2) {
    }

    public final void onRingModeChanged() {
        OplusQsVolumeController oplusQsVolumeController = this.this$0;
        oplusQsVolumeController.getRingModeChanged().set(Boolean.valueOf(!((Boolean) oplusQsVolumeController.getRingModeChanged().get()).booleanValue()));
    }

    @Override // com.oplus.systemui.qs.slider.OplusQsVolumeController.VolumeCallbackAdapter
    public final void onStateChanged(VolumeDialogController.State state) {
        SparseArray sparseArray;
        VolumeDialogController.StreamState streamState;
        boolean z;
        int i;
        Object obj;
        String str;
        OplusQsVolumeController oplusQsVolumeController = this.this$0;
        if (!this.isFirstUpdate) {
            this.isFirstUpdate = true;
            LogUtil.i$default(LogUtil.TAG_QS, "OplusQsVolumeController", "onStateChanged: state=" + state + " hasMsg=" + oplusQsVolumeController.bgHandler.hasMessages(1003), null, 8);
        }
        oplusQsVolumeController.bgHandler.removeMessages(1003);
        if (state == null || (sparseArray = state.states) == null || (streamState = (VolumeDialogController.StreamState) sparseArray.get(3)) == null) {
            return;
        }
        OplusQsVolumeController.Companion.getClass();
        int i2 = state.zenMode;
        if (i2 != 1) {
            z = i2 == 2;
        } else {
            z = state.disallowMedia;
        }
        NonNullObservableField<QSVolume.VolumeState> musicVolumeState = oplusQsVolumeController.getMusicVolumeState();
        int i3 = streamState.levelMin;
        int i4 = streamState.levelMax;
        int i5 = (streamState.muted || z) ? 0 : streamState.level;
        if (streamState.routedToWired) {
            i = 0;
        } else {
            i = streamState.routedToBluetooth ? 1 : 2;
        }
        musicVolumeState.set(new QSVolume.VolumeState(i3, i4, i5, i, 0, false));
        NonNullObservableField<String> musicStreamLabel = oplusQsVolumeController.getMusicStreamLabel();
        Context context = oplusQsVolumeController.context;
        String str2 = streamState.remoteLabel;
        if (str2 != null) {
            LogUtil.i$default(LogUtil.TAG_QS, "OplusQsVolumeController", "getStreamLabel: ss.remoteLabel=".concat(str2), null, 8);
            str = streamState.remoteLabel;
        } else {
            try {
                obj = Result.constructor-impl(context.getResources().getString(streamState.name));
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            if (Result.exceptionOrNull-impl(obj) != null) {
                LogUtil.w$default(8, LogUtil.TAG_QS, "OplusQsVolumeController", "getStreamLabel: Can't find translation for stream " + streamState);
            }
            if (obj instanceof Result.Failure) {
                obj = "";
            }
            str = (String) obj;
        }
        musicStreamLabel.set(str);
        oplusQsVolumeController.getMusicStreamZenMuted().set(Boolean.valueOf(z));
    }

    public final void onVolumeChangedFromKey() {
    }

    public final void setFirstUpdate(boolean z) {
        this.isFirstUpdate = z;
    }

    public final void onStateChanged(VolumeDialogController.State state, int i) {
        if (i == 3) {
            onStateChanged(state);
        }
    }
}
