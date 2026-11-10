package com.oplus.systemui.qs.media;

import android.os.Handler;
import com.android.keyguard.OplusLockIconView$;
import com.coui.appcompat.COUIMaterialStateManager;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBaseMediaPanelView$loadingRunnable$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQsBaseMediaPanelView this$0;

    public /* synthetic */ OplusQsBaseMediaPanelView$loadingRunnable$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = 4;
        int i2 = 8;
        switch (this.$r8$classId) {
            case 0:
                OplusNotificationLogger.logMediaPanel("OplusQsBaseMediaPanelView", "loading animation start");
                OplusQsPlayPauseButton playOrPauseBtn = this.this$0.getPlayOrPauseBtn();
                if (playOrPauseBtn != null) {
                    playOrPauseBtn.setVisibility(4);
                }
                OplusDotProgressBar oplusDotProgressBar = this.this$0.dotLoading;
                if (oplusDotProgressBar != null) {
                    oplusDotProgressBar.setVisibility(0);
                }
                OplusDotProgressBar oplusDotProgressBar2 = this.this$0.dotLoading;
                if (oplusDotProgressBar2 != null) {
                    oplusDotProgressBar2.startAnimation();
                }
                break;
            case 1:
                OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView = this.this$0;
                COUIMaterialStateManager.INSTANCE.getClass();
                oplusQsBaseMediaPanelView.enableStrokeLight = COUIMaterialStateManager.MATERIAL_STROKE_ENABLE;
                OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView2 = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("assign from availability listener, value=", "OplusQsBaseMediaPanelView-->", "SystemUi--QuickSettings", oplusQsBaseMediaPanelView2.enableStrokeLight);
                }
                OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView3 = this.this$0;
                OplusQsBaseMediaPanelView.access$refreshMultiLightEffect(oplusQsBaseMediaPanelView3, false, "spotLightEffectAvailable =" + oplusQsBaseMediaPanelView3.enableStrokeLight);
                this.this$0.onSpotLightEnableChanged();
                break;
            case 2:
                OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView4 = this.this$0;
                OplusQsBaseMediaPanelView.Companion companion = OplusQsBaseMediaPanelView.Companion;
                oplusQsBaseMediaPanelView4.bindOrResetMediaData("onFinishInflate", false);
                OplusQsMediaItemViewCache.INSTANCE.initContext(this.this$0.getContext());
                break;
            case 3:
                this.this$0.queryHistoryMediaData();
                Handler handler = this.this$0.handler;
                OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView5 = this.this$0;
                OplusQsBaseMediaPanelView$loadingRunnable$1 oplusQsBaseMediaPanelView$loadingRunnable$1 = new OplusQsBaseMediaPanelView$loadingRunnable$1(2);
                oplusQsBaseMediaPanelView$loadingRunnable$1.this$0 = oplusQsBaseMediaPanelView5;
                VarHandle.storeStoreFence();
                handler.post(oplusQsBaseMediaPanelView$loadingRunnable$1);
                break;
            case 4:
                OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView6 = this.this$0;
                OplusQsBaseMediaPanelView.Companion companion2 = OplusQsBaseMediaPanelView.Companion;
                oplusQsBaseMediaPanelView6.bindOrResetMediaData("onMediaPlayerRemoveByPackage", false);
                break;
            case 5:
                this.this$0.queryHistoryMediaData();
                Handler handler2 = this.this$0.handler;
                OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView7 = this.this$0;
                OplusQsBaseMediaPanelView$loadingRunnable$1 oplusQsBaseMediaPanelView$loadingRunnable$2 = new OplusQsBaseMediaPanelView$loadingRunnable$1(i);
                oplusQsBaseMediaPanelView$loadingRunnable$2.this$0 = oplusQsBaseMediaPanelView7;
                VarHandle.storeStoreFence();
                handler2.post(oplusQsBaseMediaPanelView$loadingRunnable$2);
                break;
            case 6:
                OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView8 = this.this$0;
                OplusQsBaseMediaPanelView.Companion companion3 = OplusQsBaseMediaPanelView.Companion;
                oplusQsBaseMediaPanelView8.resetCoverImg();
                break;
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                OplusNotificationLogger.logMediaPanel("OplusQsBaseMediaPanelView", "loading animation stop");
                OplusQsPlayPauseButton playOrPauseBtn2 = this.this$0.getPlayOrPauseBtn();
                if (playOrPauseBtn2 != null) {
                    playOrPauseBtn2.setVisibility(0);
                }
                OplusDotProgressBar oplusDotProgressBar3 = this.this$0.dotLoading;
                if (oplusDotProgressBar3 != null) {
                    oplusDotProgressBar3.stopAnimation();
                }
                OplusDotProgressBar oplusDotProgressBar4 = this.this$0.dotLoading;
                if (oplusDotProgressBar4 != null) {
                    oplusDotProgressBar4.setVisibility(8);
                }
                break;
            case 8:
                OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView9 = this.this$0;
                OplusQsBaseMediaPanelView.Companion companion4 = OplusQsBaseMediaPanelView.Companion;
                oplusQsBaseMediaPanelView9.bindOrResetMediaData("unBindMediaData", false);
                break;
            default:
                this.this$0.queryHistoryMediaData();
                Handler handler3 = this.this$0.handler;
                OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView10 = this.this$0;
                OplusQsBaseMediaPanelView$loadingRunnable$1 oplusQsBaseMediaPanelView$loadingRunnable$3 = new OplusQsBaseMediaPanelView$loadingRunnable$1(i2);
                oplusQsBaseMediaPanelView$loadingRunnable$3.this$0 = oplusQsBaseMediaPanelView10;
                VarHandle.storeStoreFence();
                handler3.post(oplusQsBaseMediaPanelView$loadingRunnable$3);
                break;
        }
    }
}
