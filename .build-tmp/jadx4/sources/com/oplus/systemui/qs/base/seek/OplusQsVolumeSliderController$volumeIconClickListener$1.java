package com.oplus.systemui.qs.base.seek;

import android.view.View;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.oplus.systemui.qs.base.util.PrefUtil;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsVolumeSliderController$volumeIconClickListener$1 implements View.OnClickListener {
    public /* synthetic */ OplusQsVolumeSliderController this$0;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        OplusQsVerticalSeekBar slider;
        int iIntValue = ((Number) this.this$0.musicVolumeLevel.get()).intValue();
        int iIntValue2 = ((Number) this.this$0.musicMinVolumeLevel.get()).intValue();
        LogUtil.i$default(LogUtil.TAG_QS, this.this$0.getTag(), TextFieldBuffer$.ExternalSyntheticOutline0.m(iIntValue, iIntValue2, "onVolumeIconClick: volumeLevel=", ", minVolumeLevel="), null, 8);
        SeparateQSStatisicUtil.collectQsPanelActionClick(this.this$0.getContext(), SeparateQSStatisicUtil.CLICK_QS_PANEL_TYPE_VOLUME, "click");
        if (iIntValue == iIntValue2) {
            this.this$0.getVolumeController().setMusicVolumeLevel(PrefUtil.getInt(this.this$0.userContextProvider.getCurrentUserContext(), OplusQsVolumeSliderController.PREF_KEY_LAST_MUSIC_VOLUME_LEVEL, 1));
        } else if (iIntValue > iIntValue2) {
            PrefUtil.putInt(this.this$0.userContextProvider.getCurrentUserContext(), OplusQsVolumeSliderController.PREF_KEY_LAST_MUSIC_VOLUME_LEVEL, iIntValue);
            this.this$0.getVolumeController().setMusicVolumeLevel(iIntValue2);
        }
        OplusQsVolumeSliderController oplusQsVolumeSliderController = this.this$0;
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) oplusQsVolumeSliderController.mView;
        if (oplusQsBaseToggleSliderLayout != null && (slider = oplusQsBaseToggleSliderLayout.getSlider()) != null) {
            slider.stopPhysicsMove();
        }
        oplusQsVolumeSliderController.setAllowProgressUpdate(0L);
    }
}
