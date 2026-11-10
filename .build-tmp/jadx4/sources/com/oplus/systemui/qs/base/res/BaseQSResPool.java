package com.oplus.systemui.qs.base.res;

import android.content.res.Configuration;
import android.support.v4.content.FileProvider$;
import androidx.core.view.insets.Protection$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import com.oplusos.systemui.common.outline.RectOutlineProvider;
import com.oplusos.systemui.common.util.LogUtil;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BaseQSResPool {
    public static final BaseQSResPool INSTANCE = new BaseQSResPool();
    private static final String TAG = "BaseQSResPool";
    private static final MutableSharedFlow _configuration;
    private static final MutableStateFlow _darkThemeAccentColor;
    private static final MutableStateFlow _detailPanelOutline;
    private static final MutableStateFlow _enableSeparateNotificationAndQS;
    private static final MutableStateFlow _isGlobalThemeApplied;
    private static final MutableStateFlow _isMixColorSupport;
    private static final MutableStateFlow _isOneHandedEnable;
    private static final MutableStateFlow _mirrorScaleValue;
    private static final SharedFlow configuration;
    private static final StateFlow darkThemeAccentColor;
    private static final StateFlow detailPanelOutline;
    private static final StateFlow enableSeparateNotificationAndQS;
    private static final StateFlow isGlobalThemeApplied;
    private static final StateFlow isMixColorSupport;
    private static final StateFlow isOneHandedEnable;
    private static final StateFlow mirrorScaleValue;

    static {
        Boolean bool = Boolean.FALSE;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        _enableSeparateNotificationAndQS = MutableStateFlow;
        enableSeparateNotificationAndQS = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(-16776961);
        _darkThemeAccentColor = MutableStateFlow2;
        darkThemeAccentColor = FlowKt.asStateFlow(MutableStateFlow2);
        MutableStateFlow MutableStateFlow3 = StateFlowKt.MutableStateFlow(bool);
        _isGlobalThemeApplied = MutableStateFlow3;
        isGlobalThemeApplied = FlowKt.asStateFlow(MutableStateFlow3);
        MutableStateFlow MutableStateFlow4 = StateFlowKt.MutableStateFlow(bool);
        _isMixColorSupport = MutableStateFlow4;
        isMixColorSupport = FlowKt.asStateFlow(MutableStateFlow4);
        MutableStateFlow MutableStateFlow5 = StateFlowKt.MutableStateFlow(Float.valueOf(1.0f));
        _mirrorScaleValue = MutableStateFlow5;
        mirrorScaleValue = FlowKt.asStateFlow(MutableStateFlow5);
        MutableSharedFlow mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(1, 0, BufferOverflow.DROP_OLDEST, 2);
        _configuration = mutableSharedFlowMutableSharedFlow$default;
        configuration = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default);
        MutableStateFlow MutableStateFlow6 = StateFlowKt.MutableStateFlow(RectOutlineProvider.INSTANCE);
        _detailPanelOutline = MutableStateFlow6;
        detailPanelOutline = FlowKt.asStateFlow(MutableStateFlow6);
        MutableStateFlow MutableStateFlow7 = StateFlowKt.MutableStateFlow(bool);
        _isOneHandedEnable = MutableStateFlow7;
        isOneHandedEnable = FlowKt.asStateFlow(MutableStateFlow7);
    }

    public static final int getDarkThemeAccentColor() {
        return ((Number) darkThemeAccentColor.getValue()).intValue();
    }

    public static final float getMirrorBlurScaleValue() {
        return ((Number) mirrorScaleValue.getValue()).floatValue();
    }

    public static final boolean globalThemeApplied() {
        return ((Boolean) isGlobalThemeApplied.getValue()).booleanValue();
    }

    public static final boolean isOneHandedEnable() {
        return ((Boolean) isOneHandedEnable.getValue()).booleanValue();
    }

    public static final boolean isSeparateNotificationAndQS() {
        return ((Boolean) enableSeparateNotificationAndQS.getValue()).booleanValue();
    }

    public static final boolean isSupportSpotLight() {
        return QsColorUtil.INSTANCE.getEnableSeparateNotificationAndQS() ? SepQSResPool.isSupportSpotLight() : StdQSResPool.isSupportSpotLight();
    }

    public static final void notifyConfigurationChanged(Configuration configuration2) {
        if (_configuration.tryEmit(configuration2)) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "notifyConfigurationChanged: newConfig = " + configuration2, null, 8);
        } else {
            LogUtil.e$default(8, LogUtil.TAG_QS, TAG, "configurationChange tryEmit newConfig(" + configuration2 + ") failed");
        }
    }

    public static final boolean supportMixColor() {
        return ((Boolean) isMixColorSupport.getValue()).booleanValue();
    }

    public static final void updateDarkThemeAccentColor(int i) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, FileProvider$.ExternalSyntheticOutline0.m("updateDarkThemeAccentColor: color = ", Integer.toHexString(i)), null, 8);
        _darkThemeAccentColor.setValue(Integer.valueOf(i));
    }

    public static final void updateDetailPanelOutline(CornerOutlineProvider cornerOutlineProvider) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateDetailPanelOutline: " + cornerOutlineProvider, null, 8);
        _detailPanelOutline.setValue(cornerOutlineProvider);
    }

    public static final void updateGlobalThemeApplied(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("updateGlobalThemeApplied: applied = ", z), null, 8);
        _isGlobalThemeApplied.setValue(Boolean.valueOf(z));
    }

    public static final void updateMirrorScaleValue(float f) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, Protection$.ExternalSyntheticOutline0.m(f, "updateMirrorScaleValue: value = "), null, 8);
        _mirrorScaleValue.setValue(Float.valueOf(f));
    }

    public static final void updateMixColorSupport(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("updateGaussBlurDisabled: disable = ", z), null, 8);
        _isMixColorSupport.setValue(Boolean.valueOf(z));
    }

    public static final void updateOneHandedEnable(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("updateOneHandedEnable: disable = ", "BaseQSResPool-->", "SystemUi--QuickSettings", z);
        }
        _isOneHandedEnable.setValue(Boolean.valueOf(z));
    }

    public static final void updateSeparateNotificationAndQS(boolean z) {
        _enableSeparateNotificationAndQS.setValue(Boolean.valueOf(z));
    }

    public final SharedFlow getConfiguration() {
        return configuration;
    }

    public final StateFlow getDetailPanelOutline() {
        return detailPanelOutline;
    }

    public final StateFlow getEnableSeparateNotificationAndQS() {
        return enableSeparateNotificationAndQS;
    }

    public final StateFlow getMirrorScaleValue() {
        return mirrorScaleValue;
    }

    public final StateFlow isGlobalThemeApplied() {
        return isGlobalThemeApplied;
    }

    public final StateFlow isMixColorSupport() {
        return isMixColorSupport;
    }

    /* JADX INFO: renamed from: getDarkThemeAccentColor, reason: collision with other method in class */
    public final StateFlow m244getDarkThemeAccentColor() {
        return darkThemeAccentColor;
    }

    /* JADX INFO: renamed from: isOneHandedEnable, reason: collision with other method in class */
    public final StateFlow m245isOneHandedEnable() {
        return isOneHandedEnable;
    }
}
