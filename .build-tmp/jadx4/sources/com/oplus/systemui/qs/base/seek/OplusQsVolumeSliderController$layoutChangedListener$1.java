package com.oplus.systemui.qs.base.seek;

import android.util.Log;
import android.view.View;
import androidx.car.app.serialization.Bundler$;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import com.android.systemui.plugins.qs.QSVolume;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsVolumeSliderController$layoutChangedListener$1 implements View.OnLayoutChangeListener {
    public /* synthetic */ OplusQsVolumeSliderController this$0;

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i == i5 && i2 == i6 && i3 == i7 && i4 == i8) {
            return;
        }
        QSVolume.VolumeState volumeState = (QSVolume.VolumeState) this.this$0.getVolumeController().getMusicVolumeState().get();
        this.this$0.musicMinVolumeLevel.set(Integer.valueOf(volumeState.getMinLevel()));
        this.this$0.musicMaxVolumeLevel.set(Integer.valueOf(volumeState.getMaxLevel()));
        this.this$0.musicVolumeLevel.set(Integer.valueOf(volumeState.getLevel()));
        this.this$0.musicVolumeState.set(volumeState);
        OplusQsVolumeSliderController.Companion companion = OplusQsVolumeSliderController.Companion;
        int iIntValue = ((Number) this.this$0.musicVolumeLevel.get()).intValue();
        companion.getClass();
        int i9 = iIntValue * 100;
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) this.this$0.mView;
        OplusQsBaseToggleSliderLayout.Companion companion2 = OplusQsBaseToggleSliderLayout.Companion;
        int distanceFromBottom = oplusQsBaseToggleSliderLayout.getDistanceFromBottom(i9, -1);
        String tag = this.this$0.getTag();
        OplusQsVolumeSliderController oplusQsVolumeSliderController = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
            IQSSpotLightConfig slider = ((OplusQsBaseToggleSliderLayout) oplusQsVolumeSliderController.mView).getSlider();
            Integer numValueOf = slider != null ? Integer.valueOf(slider.getHeight()) : null;
            sbM.append("sliderLastHeight:" + numValueOf + " volumeLevelProgress:" + i9 + ", seekBarVolumeLevel:" + oplusQsVolumeSliderController.seekBarVolumeLevel.get() + " curDistanceFromBottom = " + distanceFromBottom + " distanceFromBottom:" + oplusQsVolumeSliderController.distanceFromBottom);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        OplusQsVolumeSliderController oplusQsVolumeSliderController2 = this.this$0;
        if (oplusQsVolumeSliderController2.distanceFromBottom == distanceFromBottom || ((Number) oplusQsVolumeSliderController2.seekBarVolumeLevel.get()).intValue() == -1) {
            return;
        }
        OplusQsVolumeSliderController oplusQsVolumeSliderController3 = this.this$0;
        oplusQsVolumeSliderController3.distanceFromBottom = distanceFromBottom;
        oplusQsVolumeSliderController3.updateVolumeIconState((QSVolume.VolumeState) oplusQsVolumeSliderController3.musicVolumeState.get(), false);
    }
}
