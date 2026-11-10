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
public final class NoiseReductionDetailTile extends QsDetailTileImpl {
    public static final Companion Companion = new Companion();
    public static final String TILE_SPEC = "noisereduction";
    private final callback.1 callback;
    public final int clickTipsType;
    public final int detailType;
    private final EarphoneController earphoneController;
    private final String tileSpec;

    public NoiseReductionDetailTile(Context context, OplusQSActivityStarter oplusQSActivityStarter, EarphoneController earphoneController) {
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

    private final int getNextNoiseReductionMode() {
        Map supportType;
        List list;
        EarphoneController.ActiveDevice activeDevice = this.earphoneController.getActiveDevice();
        int iIntValue = 1;
        if (activeDevice != null && (supportType = activeDevice.getSupportType()) != null && (list = (List) supportType.get(EarphoneController.NOISE)) != null) {
            int[] iArr = {1, 5, 10, 2};
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 4; i++) {
                int i2 = iArr[i];
                if (list.contains(Integer.valueOf(i2))) {
                    arrayList.add(Integer.valueOf(i2));
                }
            }
            int i3 = ((QSTile.State) getState()).threeStageMode;
            iIntValue = ((Number) arrayList.get((arrayList.indexOf(Integer.valueOf(i3)) + 1) % arrayList.size())).intValue();
            String tag = getTag();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
                StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("getNextNoiseReductionMode: currentType = ", i3, iIntValue, ", nextType = ", ", types = ");
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
        intent.putExtra(EarphoneController.ACTIVITY_ID, EarphoneController.ACTIVITY_ID_NOISE);
        intent.putExtra(EarphoneController.DEVICE_ID, activeDevice.getAddress());
        intent.putExtra(EarphoneController.DEVICE_NAME, activeDevice.getName());
        return intent;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl, com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public String getSecondaryLabel(QSTile.State state) {
        int i = state.threeStageMode;
        if (i == 2) {
            return getContext().getString(2131955670);
        }
        if (i != 5) {
            return i != 10 ? super.getSecondaryLabel(state) : getContext().getString(2131955668);
        }
        return getContext().getString(2131955669);
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl, com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public String getTileLabel() {
        return getContext().getString(2131955667);
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl, com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public String getTileSpec() {
        return this.tileSpec;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl
    public void handleClick(Expandable expandable) {
        int nextNoiseReductionMode = getNextNoiseReductionMode();
        EarphoneController earphoneController = this.earphoneController;
        earphoneController.setNoiseReductionMode(earphoneController.getActiveDevice(), nextNoiseReductionMode);
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
        int i;
        EarphoneController.ActiveDevice activeDevice = this.earphoneController.getActiveDevice();
        int noiseReductionMode = this.earphoneController.getNoiseReductionMode(activeDevice != null ? activeDevice.getAddress() : null);
        String tag = getTag();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(noiseReductionMode, "handleUpdateState: type = ", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR));
        }
        int i2 = 0;
        boolean z = noiseReductionMode != 1;
        ((QSTile.State) booleanState).threeStageMode = noiseReductionMode;
        ((QSTile.State) booleanState).label = getTileLabel();
        ((QSTile.AdapterState) booleanState).value = z;
        if (activeDevice != null) {
            QsColorUtil.INSTANCE.getClass();
            if (!QsColorUtil.isSuperPowerSaveState) {
                i2 = ((QSTile.AdapterState) booleanState).value ? 2 : 1;
            }
        }
        ((QSTile.State) booleanState).state = i2;
        if (noiseReductionMode == 1) {
            i = 2131236286;
        } else if (noiseReductionMode != 5) {
            i = noiseReductionMode != 10 ? 2131236289 : 2131236287;
        } else {
            i = 2131236288;
        }
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(i);
        ((QSTile.State) booleanState).contentDescription = ((QSTile.State) booleanState).label;
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Button.class.getName();
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl, com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public final boolean isAvailable() {
        Map supportType;
        List list;
        EarphoneController.ActiveDevice activeDevice = this.earphoneController.getActiveDevice();
        return ((activeDevice == null || (supportType = activeDevice.getSupportType()) == null || (list = (List) supportType.get(EarphoneController.NOISE)) == null) ? 0 : list.size()) > 1 && ((Number) this.earphoneController.getEarState().getValue()).intValue() != 0;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl
    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState mo309newTileState() {
        return new QSTile.BooleanState();
    }
}
