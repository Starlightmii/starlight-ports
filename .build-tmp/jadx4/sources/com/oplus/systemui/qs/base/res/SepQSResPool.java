package com.oplus.systemui.qs.base.res;

import androidx.compose.runtime.OpaqueKey$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.keyguard.folder.OplusBouncerLocationController$clickListener$1$;
import com.oplus.systemui.plugins.util.PluginDrawableUtils;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.res.model.TileIconColorState;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import com.oplusos.systemui.common.outline.OvalOutlineProvider;
import com.oplusos.systemui.common.outline.RectOutlineProvider;
import com.oplusos.systemui.common.util.LogUtil;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepQSResPool {
    public static final SepQSResPool INSTANCE = new SepQSResPool();
    private static final String TAG = "SepQSResPool";
    private static final MutableStateFlow _customizerDrawable;
    private static final MutableStateFlow _detailPanelDrawable;
    private static final MutableStateFlow _detailTileIconColorState;
    private static final MutableStateFlow _detailTileViewDrawable;
    private static final MutableStateFlow _highLightIconBg;
    private static final MutableStateFlow _highLightIconThemeBg;
    private static final MutableStateFlow _highLightTileOutline;
    private static final MutableStateFlow _highLightTileThemeDrawable;
    private static final MutableStateFlow _highlightTileViewDrawable;
    private static final MutableStateFlow _mediaPanelDrawable;
    private static final MutableStateFlow _mediaPanelOutline;
    private static final MutableStateFlow _mediaPanelThemeDrawable;
    private static final MutableStateFlow _quickEntranceDrawable;
    private static final MutableStateFlow _scenarioHighlightTileIconDrawable;
    private static final MutableStateFlow _scenarioHighlightTileViewDrawable;
    private static final MutableStateFlow _scenarioTileViewDrawable;
    private static final MutableStateFlow _supportSpotLight;
    private static final MutableStateFlow _supportStrokeCorner;
    private static final MutableStateFlow _threeStageIconColorState;
    private static final MutableStateFlow _tileIconColorState;
    private static final MutableStateFlow _tileIconThemeColorState;
    private static final MutableStateFlow _tileOutline;
    private static final MutableStateFlow _tileThemeDrawable;
    private static final MutableStateFlow _tileViewDrawable;
    private static final StateFlow customizerDrawable;
    private static final StateFlow detailPanelDrawable;
    private static final StateFlow detailTileIconColorState;
    private static final StateFlow detailTileViewDrawable;
    private static final StateFlow highLightIconBg;
    private static final StateFlow highLightIconThemeBg;
    private static final StateFlow highLightTileOutline;
    private static final StateFlow highLightTileThemeDrawable;
    private static final StateFlow highlightTileViewDrawable;
    private static final StateFlow mediaPanelDrawable;
    private static final StateFlow mediaPanelOutline;
    private static final StateFlow mediaPanelThemeDrawable;
    private static final StateFlow quickEntranceDrawable;
    private static final StateFlow scenarioHighlightTileIconDrawable;
    private static final StateFlow scenarioHighlightTileViewDrawable;
    private static final StateFlow scenarioTileViewDrawable;
    private static final StateFlow supportSpotLight;
    private static final StateFlow supportStrokeCorner;
    private static final StateFlow threeStageIconColorState;
    private static final StateFlow tileIconColorState;
    private static final StateFlow tileIconThemeColorState;
    private static final StateFlow tileOutline;
    private static final StateFlow tileThemeDrawable;
    private static final StateFlow tileViewDrawable;

    static {
        QSDrawableBuilder.Empty empty = QSDrawableBuilder.Empty.INSTANCE;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(empty);
        _tileViewDrawable = MutableStateFlow;
        tileViewDrawable = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(empty);
        _tileThemeDrawable = MutableStateFlow2;
        tileThemeDrawable = FlowKt.asStateFlow(MutableStateFlow2);
        MutableStateFlow MutableStateFlow3 = StateFlowKt.MutableStateFlow(empty);
        _detailTileViewDrawable = MutableStateFlow3;
        detailTileViewDrawable = FlowKt.asStateFlow(MutableStateFlow3);
        MutableStateFlow MutableStateFlow4 = StateFlowKt.MutableStateFlow(OvalOutlineProvider.INSTANCE);
        _tileOutline = MutableStateFlow4;
        tileOutline = FlowKt.asStateFlow(MutableStateFlow4);
        MutableStateFlow MutableStateFlow5 = StateFlowKt.MutableStateFlow(empty);
        _highlightTileViewDrawable = MutableStateFlow5;
        highlightTileViewDrawable = FlowKt.asStateFlow(MutableStateFlow5);
        MutableStateFlow MutableStateFlow6 = StateFlowKt.MutableStateFlow(empty);
        _highLightTileThemeDrawable = MutableStateFlow6;
        highLightTileThemeDrawable = FlowKt.asStateFlow(MutableStateFlow6);
        RectOutlineProvider rectOutlineProvider = RectOutlineProvider.INSTANCE;
        MutableStateFlow MutableStateFlow7 = StateFlowKt.MutableStateFlow(rectOutlineProvider);
        _highLightTileOutline = MutableStateFlow7;
        highLightTileOutline = FlowKt.asStateFlow(MutableStateFlow7);
        MutableStateFlow MutableStateFlow8 = StateFlowKt.MutableStateFlow(empty);
        _highLightIconBg = MutableStateFlow8;
        highLightIconBg = FlowKt.asStateFlow(MutableStateFlow8);
        MutableStateFlow MutableStateFlow9 = StateFlowKt.MutableStateFlow(empty);
        _highLightIconThemeBg = MutableStateFlow9;
        highLightIconThemeBg = FlowKt.asStateFlow(MutableStateFlow9);
        MutableStateFlow MutableStateFlow10 = StateFlowKt.MutableStateFlow(empty);
        _mediaPanelDrawable = MutableStateFlow10;
        mediaPanelDrawable = FlowKt.asStateFlow(MutableStateFlow10);
        MutableStateFlow MutableStateFlow11 = StateFlowKt.MutableStateFlow(empty);
        _mediaPanelThemeDrawable = MutableStateFlow11;
        mediaPanelThemeDrawable = FlowKt.asStateFlow(MutableStateFlow11);
        MutableStateFlow MutableStateFlow12 = StateFlowKt.MutableStateFlow(rectOutlineProvider);
        _mediaPanelOutline = MutableStateFlow12;
        mediaPanelOutline = FlowKt.asStateFlow(MutableStateFlow12);
        MutableStateFlow MutableStateFlow13 = StateFlowKt.MutableStateFlow(empty);
        _customizerDrawable = MutableStateFlow13;
        customizerDrawable = FlowKt.asStateFlow(MutableStateFlow13);
        MutableStateFlow MutableStateFlow14 = StateFlowKt.MutableStateFlow(empty);
        _quickEntranceDrawable = MutableStateFlow14;
        quickEntranceDrawable = FlowKt.asStateFlow(MutableStateFlow14);
        MutableStateFlow MutableStateFlow15 = StateFlowKt.MutableStateFlow(empty);
        _detailPanelDrawable = MutableStateFlow15;
        detailPanelDrawable = FlowKt.asStateFlow(MutableStateFlow15);
        MutableStateFlow MutableStateFlow16 = StateFlowKt.MutableStateFlow((Object) null);
        _tileIconColorState = MutableStateFlow16;
        tileIconColorState = FlowKt.asStateFlow(MutableStateFlow16);
        MutableStateFlow MutableStateFlow17 = StateFlowKt.MutableStateFlow((Object) null);
        _threeStageIconColorState = MutableStateFlow17;
        threeStageIconColorState = FlowKt.asStateFlow(MutableStateFlow17);
        MutableStateFlow MutableStateFlow18 = StateFlowKt.MutableStateFlow((Object) null);
        _tileIconThemeColorState = MutableStateFlow18;
        tileIconThemeColorState = FlowKt.asStateFlow(MutableStateFlow18);
        MutableStateFlow MutableStateFlow19 = StateFlowKt.MutableStateFlow((Object) null);
        _detailTileIconColorState = MutableStateFlow19;
        detailTileIconColorState = FlowKt.asStateFlow(MutableStateFlow19);
        MutableStateFlow MutableStateFlow20 = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        _supportStrokeCorner = MutableStateFlow20;
        supportStrokeCorner = FlowKt.asStateFlow(MutableStateFlow20);
        MutableStateFlow MutableStateFlow21 = StateFlowKt.MutableStateFlow(empty);
        _scenarioTileViewDrawable = MutableStateFlow21;
        scenarioTileViewDrawable = FlowKt.asStateFlow(MutableStateFlow21);
        MutableStateFlow MutableStateFlow22 = StateFlowKt.MutableStateFlow(empty);
        _scenarioHighlightTileViewDrawable = MutableStateFlow22;
        scenarioHighlightTileViewDrawable = FlowKt.asStateFlow(MutableStateFlow22);
        MutableStateFlow MutableStateFlow23 = StateFlowKt.MutableStateFlow(empty);
        _scenarioHighlightTileIconDrawable = MutableStateFlow23;
        scenarioHighlightTileIconDrawable = FlowKt.asStateFlow(MutableStateFlow23);
        MutableStateFlow MutableStateFlow24 = StateFlowKt.MutableStateFlow(Boolean.TRUE);
        _supportSpotLight = MutableStateFlow24;
        supportSpotLight = FlowKt.asStateFlow(MutableStateFlow24);
    }

    public static final TileIconColorState getThreeStageIconColorState() {
        return (TileIconColorState) threeStageIconColorState.getValue();
    }

    public static final TileIconColorState getTileIconColorState() {
        return (TileIconColorState) tileIconColorState.getValue();
    }

    public static final TileIconColorState getTileIconThemeColorState() {
        return (TileIconColorState) _tileIconThemeColorState.getValue();
    }

    public static final boolean isSupportSpotLight() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("isSupportSpotLight: enable = ", supportSpotLight.getValue(), "SepQSResPool-->", "SystemUi--QuickSettings");
        }
        return ((Boolean) supportSpotLight.getValue()).booleanValue();
    }

    public static final boolean isSupportStrokeCorner() {
        StateFlow stateFlow = supportStrokeCorner;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, OpaqueKey$.ExternalSyntheticOutline0.m(stateFlow.getValue(), "isSupportStrokeCorner: enable = "), null, 8);
        return ((Boolean) stateFlow.getValue()).booleanValue();
    }

    public static final void updateCustomizerDrawable(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateCustomizerDrawable: " + qSDrawableBuilder, null, 8);
        _customizerDrawable.setValue(qSDrawableBuilder);
    }

    public static final void updateDetailPanelDrawable(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateDetailPanelDrawable: " + qSDrawableBuilder, null, 8);
        _detailPanelDrawable.setValue(qSDrawableBuilder);
    }

    public static final void updateDetailTileIconColorState(TileIconColorState tileIconColorState2) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateDetailTileIconColorState: " + tileIconColorState2, null, 8);
        _detailTileIconColorState.setValue(tileIconColorState2);
    }

    public static final void updateDetailTileViewDrawable(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateDetailTileViewDrawable: " + qSDrawableBuilder, null, 8);
        _detailTileViewDrawable.setValue(qSDrawableBuilder);
    }

    public static final void updateHighLightIconBg(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateHighLightIconBg: " + qSDrawableBuilder, null, 8);
        _highLightIconBg.setValue(qSDrawableBuilder);
    }

    public static final void updateHighLightIconThemeBg(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateHighLightIconThemeBg: " + qSDrawableBuilder, null, 8);
        _highLightIconThemeBg.setValue(qSDrawableBuilder);
    }

    public static final void updateHighLightTileOutline(CornerOutlineProvider cornerOutlineProvider) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateHighLightTileOutline: " + cornerOutlineProvider, null, 8);
        _highLightTileOutline.setValue(cornerOutlineProvider);
    }

    public static final void updateHighLightTileThemeDrawable(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateHighLightTileThemeDrawable: " + qSDrawableBuilder, null, 8);
        _highLightTileThemeDrawable.setValue(qSDrawableBuilder);
    }

    public static final void updateHighLightTileViewDrawable(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateHighLightTileViewDrawable: " + qSDrawableBuilder, null, 8);
        _highlightTileViewDrawable.setValue(qSDrawableBuilder);
    }

    public static final void updateMediaPanelDrawable(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateMediaPanelDrawable: " + qSDrawableBuilder, null, 8);
        _mediaPanelDrawable.setValue(qSDrawableBuilder);
    }

    public static final void updateMediaPanelOutline(CornerOutlineProvider cornerOutlineProvider) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateMediaPanelOutline: " + cornerOutlineProvider, null, 8);
        _mediaPanelOutline.setValue(cornerOutlineProvider);
    }

    public static final void updateMediaPanelThemeDrawable(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateMediaPanelThemeDrawable: " + qSDrawableBuilder, null, 8);
        _mediaPanelThemeDrawable.setValue(qSDrawableBuilder);
    }

    public static final void updateQuickEntranceDrawable(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateQuickEntranceDrawable: " + qSDrawableBuilder, null, 8);
        _quickEntranceDrawable.setValue(qSDrawableBuilder);
    }

    public static final void updateScenarioHighlightTileIconDrawable(QSDrawableBuilder qSDrawableBuilder) {
        _scenarioHighlightTileIconDrawable.setValue(qSDrawableBuilder);
    }

    public static final void updateScenarioHighlightTileViewDrawable(QSDrawableBuilder qSDrawableBuilder) {
        _scenarioHighlightTileViewDrawable.setValue(qSDrawableBuilder);
    }

    public static final void updateScenarioTileViewDrawable(QSDrawableBuilder qSDrawableBuilder) {
        _scenarioTileViewDrawable.setValue(qSDrawableBuilder);
    }

    public static final void updateSpotLight(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("updateSpotLight: enable = ", "SepQSResPool-->", "SystemUi--QuickSettings", z);
        }
        _supportSpotLight.setValue(Boolean.valueOf(z));
    }

    public static final void updateStrokeSupport(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("updateStrokeSupport: enable = ", z), null, 8);
        _supportStrokeCorner.setValue(Boolean.valueOf(z));
        PluginDrawableUtils.INSTANCE.setSupportStroke(z);
    }

    public static final void updateTileIconColorState(TileIconColorState tileIconColorState2, TileIconColorState tileIconColorState3) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateTileIconColorState: " + tileIconColorState2 + " " + tileIconColorState3, null, 8);
        _threeStageIconColorState.setValue(tileIconColorState3);
        _tileIconColorState.setValue(tileIconColorState2);
    }

    public static final void updateTileIconThemeColorState(TileIconColorState tileIconColorState2) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateTileIconThemeColorState: " + tileIconColorState2, null, 8);
        _tileIconThemeColorState.setValue(tileIconColorState2);
    }

    public static final void updateTileOutline(CornerOutlineProvider cornerOutlineProvider) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateTileOutline: " + cornerOutlineProvider, null, 8);
        _tileOutline.setValue(cornerOutlineProvider);
    }

    public static final void updateTileThemeDrawable(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateTileGlobalThemeDrawable: " + qSDrawableBuilder, null, 8);
        _tileThemeDrawable.setValue(qSDrawableBuilder);
    }

    public static final void updateTileViewDrawable(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateTileViewDrawable: " + qSDrawableBuilder, null, 8);
        _tileViewDrawable.setValue(qSDrawableBuilder);
    }

    public final StateFlow getCustomizerDrawable() {
        return customizerDrawable;
    }

    public final StateFlow getDetailPanelDrawable() {
        return detailPanelDrawable;
    }

    public final StateFlow getDetailTileIconColorState() {
        return detailTileIconColorState;
    }

    public final StateFlow getDetailTileViewDrawable() {
        return detailTileViewDrawable;
    }

    public final StateFlow getHighLightIconBg() {
        return highLightIconBg;
    }

    public final StateFlow getHighLightIconThemeBg() {
        return highLightIconThemeBg;
    }

    public final StateFlow getHighLightTileOutline() {
        return highLightTileOutline;
    }

    public final StateFlow getHighLightTileThemeDrawable() {
        return highLightTileThemeDrawable;
    }

    public final StateFlow getHighlightTileViewDrawable() {
        return highlightTileViewDrawable;
    }

    public final StateFlow getMediaPanelDrawable() {
        return mediaPanelDrawable;
    }

    public final StateFlow getMediaPanelOutline() {
        return mediaPanelOutline;
    }

    public final StateFlow getMediaPanelThemeDrawable() {
        return mediaPanelThemeDrawable;
    }

    public final StateFlow getQuickEntranceDrawable() {
        return quickEntranceDrawable;
    }

    public final StateFlow getScenarioHighlightTileIconDrawable() {
        return scenarioHighlightTileIconDrawable;
    }

    public final StateFlow getScenarioHighlightTileViewDrawable() {
        return scenarioHighlightTileViewDrawable;
    }

    public final StateFlow getScenarioTileViewDrawable() {
        return scenarioTileViewDrawable;
    }

    public final StateFlow getSupportSpotLight() {
        return supportSpotLight;
    }

    public final StateFlow getSupportStrokeCorner() {
        return supportStrokeCorner;
    }

    public final StateFlow getTileOutline() {
        return tileOutline;
    }

    public final StateFlow getTileThemeDrawable() {
        return tileThemeDrawable;
    }

    public final StateFlow getTileViewDrawable() {
        return tileViewDrawable;
    }

    /* JADX INFO: renamed from: getTileIconColorState, reason: collision with other method in class */
    public final StateFlow m246getTileIconColorState() {
        return tileIconColorState;
    }

    /* JADX INFO: renamed from: getTileIconThemeColorState, reason: collision with other method in class */
    public final StateFlow m247getTileIconThemeColorState() {
        return tileIconThemeColorState;
    }
}
