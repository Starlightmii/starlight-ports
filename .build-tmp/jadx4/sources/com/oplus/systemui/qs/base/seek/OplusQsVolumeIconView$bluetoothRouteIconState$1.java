package com.oplus.systemui.qs.base.seek;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import com.android.launcher3.icons.IconProvider$;
import com.android.systemui.plugins.qs.QSVolume;
import com.oplus.systemui.qs.base.tile.QSLottieAnimationView;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsVolumeIconView$bluetoothRouteIconState$1 implements OplusQsVolumeIconView.VolumeIconState {
    public Boolean aboveThreshold;
    public QSLottieAnimationView.ColorfulConfig colorfulConfig;
    public String currentAsset;
    public int currentEndFrame;
    public int currentStartFrame;
    public boolean isGlobalTheme;
    public Boolean isMuted;
    public Boolean lastAboveThreshold;
    public final /* synthetic */ OplusQsVolumeIconView this$0;
    public int lastLevel = -1;
    public float currentSpeed = 1.0f;

    public OplusQsVolumeIconView$bluetoothRouteIconState$1(OplusQsVolumeIconView oplusQsVolumeIconView) {
        this.this$0 = oplusQsVolumeIconView;
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
    public Float getAlpha() {
        QSLottieAnimationView.ColorfulConfig colorfulConfig = this.colorfulConfig;
        if (colorfulConfig != null) {
            return Float.valueOf(colorfulConfig.getEndAlpha());
        }
        return null;
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
    public String getDescription() {
        return "BluetoothRouteIconState";
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
    public final Boolean isMuted() {
        return this.isMuted;
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
    public final void onStateEnter() {
        this.isGlobalTheme = QsColorUtil.isGlobalThemeApplied(this.this$0.getContext());
        this.isMuted = null;
        this.aboveThreshold = null;
        this.lastAboveThreshold = null;
        this.currentAsset = null;
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
    public final void onVolumeStateChanged(QSVolume.VolumeState volumeState) {
        int i;
        int i2;
        int i3;
        boolean z = true;
        int i4 = 0;
        boolean z2 = volumeState.getLevel() == 0;
        int distanceFromBottom = volumeState.getDistanceFromBottom();
        int level = volumeState.getLevel();
        OplusQsVolumeIconView oplusQsVolumeIconView = this.this$0;
        boolean zIsAboveThreshold = oplusQsVolumeIconView.isAboveThreshold(distanceFromBottom, level);
        if (this.aboveThreshold != null && Boolean.valueOf(zIsAboveThreshold).equals(this.aboveThreshold)) {
            z = false;
        }
        if (this.lastLevel == level && this.aboveThreshold != null && !z) {
            z = false;
        } else if (z) {
            this.aboveThreshold = Boolean.valueOf(zIsAboveThreshold);
        }
        String str = oplusQsVolumeIconView.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("bluetoothRouteIconState onVolumeStateChanged volumeState: " + volumeState + ", isMuted: " + this.isMuted + " needUpdate:" + z + " zenMuted:" + volumeState.getZenMuted());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        this.lastLevel = volumeState.getLevel();
        if (!Boolean.valueOf(z2).equals(this.isMuted) || this.isGlobalTheme || z || volumeState.getZenMuted()) {
            if (this.isMuted == null || this.isGlobalTheme) {
                if (z2) {
                    if (oplusQsVolumeIconView.getDisableTheme()) {
                        oplusQsVolumeIconView.setImageResource(2131236332);
                    } else {
                        oplusQsVolumeIconView.setImageResource(2131237172);
                    }
                } else if (oplusQsVolumeIconView.getDisableTheme()) {
                    oplusQsVolumeIconView.setImageResource(2131236333);
                } else {
                    oplusQsVolumeIconView.setImageResource(2131237171);
                }
                if (z) {
                    oplusQsVolumeIconView.updateIconColor(z2, Intrinsics.areEqual(this.aboveThreshold, Boolean.TRUE));
                }
            } else {
                int i5 = 17;
                if (z2) {
                    QSLottieAnimationView.ColorfulConfig.Companion companion = QSLottieAnimationView.ColorfulConfig.Companion;
                    Context context = ((ImageView) oplusQsVolumeIconView).mContext;
                    int muteIconColor = oplusQsVolumeIconView.getMuteIconColor();
                    int unMuteIconColor = oplusQsVolumeIconView.getUnMuteIconColor();
                    Boolean bool = this.aboveThreshold;
                    Boolean bool2 = Boolean.TRUE;
                    this.colorfulConfig = companion.makeColorfulConfigForQSVolumeBlueToothIcon(context, muteIconColor, unMuteIconColor, z2, Intrinsics.areEqual(bool, bool2), Intrinsics.areEqual(this.lastAboveThreshold, bool2));
                    if (Intrinsics.areEqual(this.lastAboveThreshold, bool2)) {
                        oplusQsVolumeIconView.setSpeed(1.0f);
                    } else {
                        oplusQsVolumeIconView.setSpeed(1.0f);
                        i4 = 18;
                        i5 = 36;
                    }
                    int i6 = i4;
                    updateAnimation("volumeui/ic_qs_volume_media_bt_close_night.json", i6, i5, i6, oplusQsVolumeIconView.getSpeed());
                } else {
                    QSLottieAnimationView.ColorfulConfig.Companion companion2 = QSLottieAnimationView.ColorfulConfig.Companion;
                    Context context2 = ((ImageView) oplusQsVolumeIconView).mContext;
                    int muteIconColor2 = oplusQsVolumeIconView.getMuteIconColor();
                    int unMuteIconColor2 = oplusQsVolumeIconView.getUnMuteIconColor();
                    Boolean bool3 = this.aboveThreshold;
                    Boolean bool4 = Boolean.TRUE;
                    this.colorfulConfig = companion2.makeColorfulConfigForQSVolumeBlueToothIcon(context2, muteIconColor2, unMuteIconColor2, z2, Intrinsics.areEqual(bool3, bool4), Intrinsics.areEqual(this.lastAboveThreshold, bool4));
                    oplusQsVolumeIconView.setSpeed(1.0f);
                    int i7 = 27;
                    if (Intrinsics.areEqual(this.aboveThreshold, bool4)) {
                        oplusQsVolumeIconView.setSpeed(1.0f);
                        i = 17;
                    } else {
                        if (Intrinsics.areEqual(this.isMuted, bool4)) {
                            oplusQsVolumeIconView.setSpeed(1.0f);
                            i7 = 16;
                            i = 0;
                        } else {
                            oplusQsVolumeIconView.setSpeed(-1.0f);
                            i = 17;
                            i2 = 27;
                            i3 = 27;
                        }
                        updateAnimation("volumeui/ic_qs_volume_media_bt_open_night.json", i, i2, i3, oplusQsVolumeIconView.getSpeed());
                    }
                    i3 = i;
                    i2 = i7;
                    updateAnimation("volumeui/ic_qs_volume_media_bt_open_night.json", i, i2, i3, oplusQsVolumeIconView.getSpeed());
                }
            }
            this.isMuted = Boolean.valueOf(z2);
            this.lastAboveThreshold = this.aboveThreshold;
        }
    }

    public final void updateAnimation(String str, int i, int i2, int i3, float f) {
        OplusQsVolumeIconView oplusQsVolumeIconView = this.this$0;
        String str2 = oplusQsVolumeIconView.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str2, LogUtil.SEPARATOR);
            Boolean bool = this.aboveThreshold;
            Boolean bool2 = this.lastAboveThreshold;
            Boolean bool3 = this.isMuted;
            StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("updateAnimation startFrame:", i, i2, ", endFrame:", " frame:");
            IconProvider$.ExternalSyntheticOutline0.m(f, i3, " speed:", " aboveThreshold:", sbM2);
            sbM2.append(bool);
            sbM2.append(" lastAboveThreshold:");
            sbM2.append(bool2);
            sbM2.append(" isMuted:");
            sbM2.append(bool3);
            sbM2.append(" assetName:");
            sbM2.append(str);
            sbM.append(sbM2.toString());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        this.currentStartFrame = i;
        this.currentEndFrame = i2;
        this.currentSpeed = f;
        if (str.equals(this.currentAsset)) {
            oplusQsVolumeIconView.setTintColorfulConfig(str, this.colorfulConfig);
            oplusQsVolumeIconView.setMinAndMaxFrame(this.currentStartFrame, this.currentEndFrame);
            oplusQsVolumeIconView.setSpeed(this.currentSpeed);
            oplusQsVolumeIconView.setFrame(i3);
            oplusQsVolumeIconView.playAnimation();
            return;
        }
        oplusQsVolumeIconView.removeAllLottieOnCompositionLoadedListener();
        OplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2 oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2 = new OplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2(1);
        oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2.this$0 = oplusQsVolumeIconView;
        oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2.$assetName = str;
        oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2.this$1 = this;
        oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2.$frame = i3;
        oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2.$startFrame = i;
        oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2.$endFrame = i2;
        oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2.$speed = f;
        VarHandle.storeStoreFence();
        oplusQsVolumeIconView.addLottieOnCompositionLoadedListener(oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2);
        oplusQsVolumeIconView.setAnimation(str);
        this.currentAsset = str;
    }
}
