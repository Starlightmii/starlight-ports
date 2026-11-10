package com.oplus.systemui.qs.slider;

import android.util.Log;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.plugins.qs.QSVolume;
import com.oplus.systemui.volume.repository.SuperVolumeRepository;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQsVolumeController$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQsVolumeController f$0;

    public /* synthetic */ OplusQsVolumeController$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        final OplusQsVolumeController oplusQsVolumeController = this.f$0;
        switch (i) {
            case 0:
                return new VolumeDialogController.VolumeUnchangedCallBack() { // from class: com.oplus.systemui.qs.slider.OplusQsVolumeController$volumeVolumeUnchangeCallback$2$1
                    public final void onPressVolumeKey(int i2) {
                        int i3;
                        int i4 = i2 & 1;
                        OplusQsVolumeController oplusQsVolumeController2 = oplusQsVolumeController;
                        QSVolume.VolumeState volumeState = (QSVolume.VolumeState) oplusQsVolumeController2.getMusicVolumeState().get();
                        if (i4 != 1 || oplusQsVolumeController2.hasDownEventSet) {
                            if (i4 == 0 && oplusQsVolumeController2.hasDownEventSet) {
                                int i5 = oplusQsVolumeController2.simulateDirection << 1;
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                                    OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i5, "onPressVolumeKey Up event sent: volumeUnchange=", "OplusQsVolumeController-->", "SystemUi--QuickSettings");
                                }
                                oplusQsVolumeController2.getVolumeUnchange().set(Integer.valueOf(i5));
                                oplusQsVolumeController2.getVolumeUnchange().set(-1);
                                oplusQsVolumeController2.hasDownEventSet = false;
                                return;
                            }
                            return;
                        }
                        int level = volumeState.getLevel();
                        if (level == volumeState.getMinLevel()) {
                            i3 = 0;
                        } else {
                            if (level != volumeState.getMaxLevel()) {
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                                    Log.d("SystemUi--QuickSettings", "OplusQsVolumeController-->onPressVolumeKey volume level changed");
                                    return;
                                }
                                return;
                            }
                            i3 = 1;
                        }
                        oplusQsVolumeController2.simulateDirection = i3;
                        boolean musicSuperVolumeState = oplusQsVolumeController2.getSupportSuperVolume() ? SuperVolumeRepository.Companion.readMusicSuperVolumeState(oplusQsVolumeController2.context) : false;
                        if (oplusQsVolumeController2.getSupportSuperVolume() && !musicSuperVolumeState && oplusQsVolumeController2.simulateDirection == 1) {
                            return;
                        }
                        int i6 = (oplusQsVolumeController2.simulateDirection << 1) | 1;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i6, "onPressVolumeKey Down event sent: volumeUnchange=", "OplusQsVolumeController-->", "SystemUi--QuickSettings");
                        }
                        oplusQsVolumeController2.getVolumeUnchange().set(Integer.valueOf(i6));
                        oplusQsVolumeController2.hasDownEventSet = true;
                    }
                };
            default:
                return OplusQsVolumeController.$r8$lambda$09x4K7qmauwkyaPOuCHLTpEVIzo(oplusQsVolumeController);
        }
    }
}
