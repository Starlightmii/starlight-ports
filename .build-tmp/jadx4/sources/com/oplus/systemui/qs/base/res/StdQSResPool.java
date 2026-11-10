package com.oplus.systemui.qs.base.res;

import android.graphics.Path;
import androidx.compose.runtime.OpaqueKey$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.keyguard.folder.OplusBouncerLocationController$clickListener$1$;
import com.oplus.systemui.plugins.util.PluginDrawableUtils;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.res.model.ShapeConfig;
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
public final class StdQSResPool {
    public static final StdQSResPool INSTANCE = new StdQSResPool();
    private static final String TAG = "StdQSResPool";
    private static final MutableStateFlow _bottomEditTileViewDrawable;
    private static final MutableStateFlow _detailPanelDrawable;
    private static final MutableStateFlow _detailTileIconColorState;
    private static final MutableStateFlow _detailTileViewDrawable;
    private static final MutableStateFlow _highLightTileOutline;
    private static final MutableStateFlow _highlightDefaultShape;
    private static final MutableStateFlow _highlightTileViewDrawable;
    private static final MutableStateFlow _mediaPanelDrawable;
    private static final MutableStateFlow _mediaPanelOutline;
    private static final MutableStateFlow _quickEntranceDrawable;
    private static final MutableStateFlow _supportSpotLight;
    private static final MutableStateFlow _supportStrokeCorner;
    private static final MutableStateFlow _tileIconColorState;
    private static final MutableStateFlow _tileOutline;
    private static final MutableStateFlow _tileShapeConfig;
    private static final MutableStateFlow _tileViewDrawable;
    public static final StateFlow bottomEditTileViewDrawable;
    private static final StateFlow detailPanelDrawable;
    public static final StateFlow detailTileIconColorState;
    public static final StateFlow detailTileViewDrawable;
    public static final StateFlow highLightTileOutline;
    private static final StateFlow highlightDefaultShape;
    public static final StateFlow highlightTileViewDrawable;
    public static final StateFlow mediaPanelDrawable;
    private static final StateFlow mediaPanelOutline;
    public static final StateFlow quickEntranceDrawable;
    private static final StateFlow supportSpotLight;
    private static final StateFlow supportStrokeCorner;
    public static final StateFlow tileIconColorState;
    public static final StateFlow tileOutline;
    public static final StateFlow tileShapeConfig;
    public static final StateFlow tileViewDrawable;

    static {
        QSDrawableBuilder.Empty empty = QSDrawableBuilder.Empty.INSTANCE;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(empty);
        _tileViewDrawable = MutableStateFlow;
        tileViewDrawable = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(empty);
        _detailTileViewDrawable = MutableStateFlow2;
        detailTileViewDrawable = FlowKt.asStateFlow(MutableStateFlow2);
        MutableStateFlow MutableStateFlow3 = StateFlowKt.MutableStateFlow(empty);
        _bottomEditTileViewDrawable = MutableStateFlow3;
        bottomEditTileViewDrawable = FlowKt.asStateFlow(MutableStateFlow3);
        MutableStateFlow MutableStateFlow4 = StateFlowKt.MutableStateFlow(empty);
        _highlightTileViewDrawable = MutableStateFlow4;
        highlightTileViewDrawable = FlowKt.asStateFlow(MutableStateFlow4);
        MutableStateFlow MutableStateFlow5 = StateFlowKt.MutableStateFlow(OvalOutlineProvider.INSTANCE);
        _tileOutline = MutableStateFlow5;
        tileOutline = FlowKt.asStateFlow(MutableStateFlow5);
        RectOutlineProvider rectOutlineProvider = RectOutlineProvider.INSTANCE;
        MutableStateFlow MutableStateFlow6 = StateFlowKt.MutableStateFlow(rectOutlineProvider);
        _highLightTileOutline = MutableStateFlow6;
        highLightTileOutline = FlowKt.asStateFlow(MutableStateFlow6);
        MutableStateFlow MutableStateFlow7 = StateFlowKt.MutableStateFlow((Object) null);
        _tileIconColorState = MutableStateFlow7;
        tileIconColorState = FlowKt.asStateFlow(MutableStateFlow7);
        MutableStateFlow MutableStateFlow8 = StateFlowKt.MutableStateFlow((Object) null);
        _detailTileIconColorState = MutableStateFlow8;
        detailTileIconColorState = FlowKt.asStateFlow(MutableStateFlow8);
        MutableStateFlow MutableStateFlow9 = StateFlowKt.MutableStateFlow(new ShapeConfig(new Path(), 2131171938, false, 32));
        _tileShapeConfig = MutableStateFlow9;
        tileShapeConfig = FlowKt.asStateFlow(MutableStateFlow9);
        Boolean bool = Boolean.TRUE;
        MutableStateFlow MutableStateFlow10 = StateFlowKt.MutableStateFlow(bool);
        _highlightDefaultShape = MutableStateFlow10;
        highlightDefaultShape = FlowKt.asStateFlow(MutableStateFlow10);
        MutableStateFlow MutableStateFlow11 = StateFlowKt.MutableStateFlow(empty);
        _mediaPanelDrawable = MutableStateFlow11;
        mediaPanelDrawable = FlowKt.asStateFlow(MutableStateFlow11);
        MutableStateFlow MutableStateFlow12 = StateFlowKt.MutableStateFlow(empty);
        _quickEntranceDrawable = MutableStateFlow12;
        quickEntranceDrawable = FlowKt.asStateFlow(MutableStateFlow12);
        MutableStateFlow MutableStateFlow13 = StateFlowKt.MutableStateFlow(rectOutlineProvider);
        _mediaPanelOutline = MutableStateFlow13;
        mediaPanelOutline = FlowKt.asStateFlow(MutableStateFlow13);
        MutableStateFlow MutableStateFlow14 = StateFlowKt.MutableStateFlow(empty);
        _detailPanelDrawable = MutableStateFlow14;
        detailPanelDrawable = FlowKt.asStateFlow(MutableStateFlow14);
        MutableStateFlow MutableStateFlow15 = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        _supportStrokeCorner = MutableStateFlow15;
        supportStrokeCorner = FlowKt.asStateFlow(MutableStateFlow15);
        MutableStateFlow MutableStateFlow16 = StateFlowKt.MutableStateFlow(bool);
        _supportSpotLight = MutableStateFlow16;
        supportSpotLight = FlowKt.asStateFlow(MutableStateFlow16);
    }

    public static final TileIconColorState getTileIconColorState() {
        return (TileIconColorState) tileIconColorState.getValue();
    }

    public static final boolean isHighlightDefaultShape() {
        return ((Boolean) highlightDefaultShape.getValue()).booleanValue();
    }

    public static final boolean isSupportSpotLight() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("isSupportSpotLight: enable = ", supportSpotLight.getValue(), "StdQSResPool-->", "SystemUi--QuickSettings");
        }
        return ((Boolean) supportSpotLight.getValue()).booleanValue();
    }

    public static final boolean isSupportStrokeCorner() {
        StateFlow stateFlow = supportStrokeCorner;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, OpaqueKey$.ExternalSyntheticOutline0.m(stateFlow.getValue(), "isSupportStrokeCorner: enable = "), null, 8);
        return ((Boolean) stateFlow.getValue()).booleanValue();
    }

    public static final void updateBottomEditTileViewDrawable(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateBottomEditTileViewDrawable: " + qSDrawableBuilder, null, 8);
        _bottomEditTileViewDrawable.setValue(qSDrawableBuilder);
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

    public static final void updateHighLightTileOutline(CornerOutlineProvider cornerOutlineProvider) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateHighLightTileOutline: " + cornerOutlineProvider, null, 8);
        _highLightTileOutline.setValue(cornerOutlineProvider);
    }

    public static final void updateHighLightTileViewDrawable(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateHighLightTileViewDrawable: " + qSDrawableBuilder, null, 8);
        _highlightTileViewDrawable.setValue(qSDrawableBuilder);
    }

    public static final void updateHighlightDefaultShape(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("updateHighlightDefaultShape: isDefault=", z), null, 8);
        _highlightDefaultShape.setValue(Boolean.valueOf(z));
    }

    public static final void updateMediaPanelDrawable(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateMediaPanelDrawable: " + qSDrawableBuilder, null, 8);
        _mediaPanelDrawable.setValue(qSDrawableBuilder);
    }

    public static final void updateMediaPanelOutline(CornerOutlineProvider cornerOutlineProvider) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateMediaPanelOutline: " + cornerOutlineProvider, null, 8);
        _mediaPanelOutline.setValue(cornerOutlineProvider);
    }

    public static final void updateQuickEntranceDrawable(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateQuickEntranceDrawable: " + qSDrawableBuilder, null, 8);
        _quickEntranceDrawable.setValue(qSDrawableBuilder);
    }

    public static final void updateSpotLight(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("updateSpotLight: enable = ", "StdQSResPool-->", "SystemUi--QuickSettings", z);
        }
        _supportSpotLight.setValue(Boolean.valueOf(z));
    }

    public static final void updateStrokeSupport(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("updateStrokeSupport: enable = ", z), null, 8);
        _supportStrokeCorner.setValue(Boolean.valueOf(z));
        PluginDrawableUtils.INSTANCE.setSupportStroke(z);
    }

    public static final void updateTileIconColorState(TileIconColorState tileIconColorState2) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateTileIconColorState: " + tileIconColorState2, null, 8);
        _tileIconColorState.setValue(tileIconColorState2);
    }

    public static final void updateTileOutline(CornerOutlineProvider cornerOutlineProvider) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateTileOutline: " + cornerOutlineProvider, null, 8);
        _tileOutline.setValue(cornerOutlineProvider);
    }

    public static final void updateTileShapeConfig(ShapeConfig shapeConfig) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "tileShapeConfig:" + shapeConfig, null, 8);
        _tileShapeConfig.setValue(shapeConfig);
    }

    public static final void updateTileViewDrawable(QSDrawableBuilder qSDrawableBuilder) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateTileViewDrawable: " + qSDrawableBuilder, null, 8);
        _tileViewDrawable.setValue(qSDrawableBuilder);
    }

    public final StateFlow getDetailPanelDrawable() {
        return detailPanelDrawable;
    }

    public final StateFlow getHighlightDefaultShape() {
        return highlightDefaultShape;
    }

    public final StateFlow getMediaPanelOutline() {
        return mediaPanelOutline;
    }

    public final StateFlow getSupportSpotLight() {
        return supportSpotLight;
    }

    public final StateFlow getSupportStrokeCorner() {
        return supportStrokeCorner;
    }
}
