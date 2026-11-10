package com.oplus.systemui.qs.slider.detail.tile.volume;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.oplus.systemui.aod.controller.BaseAodClockLayoutController$bindSceneViewHolder$2$;
import com.oplus.systemui.qs.anim.OplusQSActivityStarter;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.slider.detail.data.EarphoneController;
import com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SpatialAudioDetailTile extends QsDetailTileImpl {
    public static final Companion Companion = new Companion();
    public static final String TILE_SPEC = "spatialaudio";
    private final callback.1 callback;
    public final int clickTipsType;
    public final int detailType;
    private final EarphoneController earphoneController;
    private final String tileSpec;

    public SpatialAudioDetailTile(Context context, OplusQSActivityStarter oplusQSActivityStarter, EarphoneController earphoneController) {
        super(context, oplusQSActivityStarter);
        this.earphoneController = earphoneController;
        callback.1 r1 = new callback.1();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        this.callback = r1;
        this.tileSpec = TILE_SPEC;
        this.detailType = 2;
        this.clickTipsType = 2;
    }

    private final int getNextSpatialAudioType() {
        Map supportType;
        List list;
        EarphoneController.ActiveDevice activeDevice = this.earphoneController.getActiveDevice();
        int iIntValue = 0;
        if (activeDevice != null && (supportType = activeDevice.getSupportType()) != null && (list = (List) supportType.get("spatial")) != null) {
            int[] iArr = {0, 1, 2};
            ArrayList arrayList = new ArrayList();
            while (iIntValue < 3) {
                int i = iArr[iIntValue];
                if (list.contains(Integer.valueOf(i))) {
                    arrayList.add(Integer.valueOf(i));
                }
                iIntValue++;
            }
            int i2 = ((QSTile.State) getState()).threeStageMode;
            iIntValue = ((Number) arrayList.get((arrayList.indexOf(Integer.valueOf(i2)) + 1) % arrayList.size())).intValue();
            String tag = getTag();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
                StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("getNextSpatialAudioType: currentType = ", i2, iIntValue, ", nextType = ", ", types = ");
                sbM2.append(arrayList);
                sbM.append(sbM2.toString());
                Log.d("SystemUi--QuickSettings", sbM.toString());
            }
        }
        return iIntValue;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl, com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public int getClickTipsType() {
        return this.clickTipsType;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl, com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public int getDetailType() {
        return this.detailType;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl, com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public Intent getLongClickIntent() {
        EarphoneController.ActiveDevice activeDevice = this.earphoneController.getActiveDevice();
        if (activeDevice == null) {
            return null;
        }
        Intent intent = new Intent(EarphoneController.ACTION);
        intent.putExtra(EarphoneController.ACTIVITY_ID, "spatial");
        intent.putExtra(EarphoneController.DEVICE_ID, activeDevice.getAddress());
        intent.putExtra(EarphoneController.DEVICE_NAME, activeDevice.getName());
        return intent;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl, com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public String getSecondaryLabel(QSTile.State state) {
        int i = state.threeStageMode;
        if (i != 1) {
            return i != 2 ? super.getSecondaryLabel(state) : getContext().getString(2131955696);
        }
        return getContext().getString(2131955695);
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl, com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public String getTileLabel() {
        return getContext().getString(2131955694);
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl, com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public String getTileSpec() {
        return this.tileSpec;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl
    public void handleClick(Expandable expandable) {
        int nextSpatialAudioType = getNextSpatialAudioType();
        String tag = getTag();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(nextSpatialAudioType, "handleClick: nextType = ", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR));
        }
        EarphoneController earphoneController = this.earphoneController;
        earphoneController.setSpatialAudioType(earphoneController.getActiveDevice(), nextSpatialAudioType);
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl
    public final void handleSetListening(boolean z) {
        EarphoneController earphoneController = this.earphoneController;
        callback.1 r1 = this.callback;
        if (z) {
            earphoneController.registerCallback(r1);
        } else {
            earphoneController.unregisterCallback(r1);
        }
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        EarphoneController.ActiveDevice activeDevice = this.earphoneController.getActiveDevice();
        int spatialAudioType = this.earphoneController.getSpatialAudioType(activeDevice != null ? activeDevice.getAddress() : null);
        String tag = getTag();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(spatialAudioType, "handleUpdateState: type = ", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR));
        }
        int i = 0;
        boolean z = spatialAudioType != 0;
        ((QSTile.State) booleanState).threeStageMode = spatialAudioType;
        ((QSTile.State) booleanState).label = getTileLabel();
        ((QSTile.AdapterState) booleanState).value = z;
        if (activeDevice != null) {
            QsColorUtil.INSTANCE.getClass();
            if (!QsColorUtil.isSuperPowerSaveState) {
                i = ((QSTile.AdapterState) booleanState).value ? 2 : 1;
            }
        }
        ((QSTile.State) booleanState).state = i;
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(spatialAudioType == 0 ? 2131236286 : 2131236290);
        ((QSTile.State) booleanState).contentDescription = ((QSTile.State) booleanState).label;
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Button.class.getName();
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl, com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public final boolean isAvailable() {
        Map supportType;
        List list;
        EarphoneController.ActiveDevice activeDevice = this.earphoneController.getActiveDevice();
        return ((activeDevice == null || (supportType = activeDevice.getSupportType()) == null || (list = (List) supportType.get("spatial")) == null) ? 0 : list.size()) > 1;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl
    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState mo309newTileState() {
        return new QSTile.BooleanState();
    }
}
