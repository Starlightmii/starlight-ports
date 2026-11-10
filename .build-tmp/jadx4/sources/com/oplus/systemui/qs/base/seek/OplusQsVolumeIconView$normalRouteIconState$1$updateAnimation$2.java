package com.oplus.systemui.qs.base.seek;

import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import androidx.compose.ui.spatial.RectList$;
import androidx.exifinterface.media.ExifInterface$;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieOnCompositionLoadedListener;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2 implements LottieOnCompositionLoadedListener {
    public /* synthetic */ String $assetName;
    public /* synthetic */ int $endFrame;
    public /* synthetic */ int $frame;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ float $speed;
    public /* synthetic */ int $startFrame;
    public /* synthetic */ OplusQsVolumeIconView this$0;
    public /* synthetic */ OplusQsVolumeIconView.VolumeIconState this$1;

    public final void onCompositionLoaded(LottieComposition lottieComposition) {
        switch (this.$r8$classId) {
            case 0:
                OplusQsVolumeIconView oplusQsVolumeIconView = this.this$0;
                String str = oplusQsVolumeIconView.tag;
                OplusQsVolumeIconView$normalRouteIconState$1 oplusQsVolumeIconView$normalRouteIconState$1 = (OplusQsVolumeIconView$normalRouteIconState$1) this.this$1;
                String str2 = this.$assetName;
                int i = this.$startFrame;
                int i2 = this.$endFrame;
                float f = this.$speed;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                    StringBuilder sbM2 = ExifInterface$.ExternalSyntheticOutline1.m("onCompositionLoaded currentAsset:", oplusQsVolumeIconView$normalRouteIconState$1.currentAsset, " assetName:", str2, " startFrame:");
                    RectList$.ExternalSyntheticOutline0.m(i, i2, " endFrame:", " speed:", sbM2);
                    sbM2.append(f);
                    sbM.append(sbM2.toString());
                    Log.d("SystemUi--QuickSettings", sbM.toString());
                }
                oplusQsVolumeIconView.setTintColorfulConfig(str2, oplusQsVolumeIconView$normalRouteIconState$1.colorfulConfig);
                oplusQsVolumeIconView.setMinAndMaxFrame(oplusQsVolumeIconView$normalRouteIconState$1.currentStartFrame, oplusQsVolumeIconView$normalRouteIconState$1.currentEndFrame);
                oplusQsVolumeIconView.setSpeed(oplusQsVolumeIconView$normalRouteIconState$1.currentSpeed);
                oplusQsVolumeIconView.setFrame(this.$frame);
                oplusQsVolumeIconView.playAnimation();
                break;
            default:
                OplusQsVolumeIconView oplusQsVolumeIconView2 = this.this$0;
                String str3 = oplusQsVolumeIconView2.tag;
                OplusQsVolumeIconView$bluetoothRouteIconState$1 oplusQsVolumeIconView$bluetoothRouteIconState$1 = (OplusQsVolumeIconView$bluetoothRouteIconState$1) this.this$1;
                String str4 = this.$assetName;
                int i3 = this.$startFrame;
                int i4 = this.$endFrame;
                float f2 = this.$speed;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    StringBuilder sbM3 = Bundler$.ExternalSyntheticOutline0.m(str3, LogUtil.SEPARATOR);
                    StringBuilder sbM4 = ExifInterface$.ExternalSyntheticOutline1.m("onCompositionLoaded currentAsset:", oplusQsVolumeIconView$bluetoothRouteIconState$1.currentAsset, " assetName:", str4, " startFrame:");
                    RectList$.ExternalSyntheticOutline0.m(i3, i4, " endFrame:", " speed:", sbM4);
                    sbM4.append(f2);
                    sbM3.append(sbM4.toString());
                    Log.d("SystemUi--QuickSettings", sbM3.toString());
                }
                oplusQsVolumeIconView2.setTintColorfulConfig(str4, oplusQsVolumeIconView$bluetoothRouteIconState$1.colorfulConfig);
                oplusQsVolumeIconView2.setMinAndMaxFrame(oplusQsVolumeIconView$bluetoothRouteIconState$1.currentStartFrame, oplusQsVolumeIconView$bluetoothRouteIconState$1.currentEndFrame);
                oplusQsVolumeIconView2.setSpeed(oplusQsVolumeIconView$bluetoothRouteIconState$1.currentSpeed);
                oplusQsVolumeIconView2.setFrame(this.$frame);
                oplusQsVolumeIconView2.playAnimation();
                break;
        }
    }
}
