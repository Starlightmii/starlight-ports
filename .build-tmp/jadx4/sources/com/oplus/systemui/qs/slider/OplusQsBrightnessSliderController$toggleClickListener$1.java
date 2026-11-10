package com.oplus.systemui.qs.slider;

import android.content.Context;
import android.util.Log;
import android.view.View;
import androidx.car.app.serialization.Bundler$;
import com.android.systemui.qs.brightness.IBrightnessSliderController;
import com.android.systemui.qs.deprecated.brightness.BrightnessController;
import com.oplus.systemui.qs.base.seek.ClipBrightnessView;
import com.oplus.systemui.qs.base.temporarilyinfo.TemporarilyInfoManager;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.observer.VisualComfortSwitchObserver;
import com.oplus.systemui.statusbar.notification.keymagicservice.KeyBaseController;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.util.QsStatisticUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBrightnessSliderController$toggleClickListener$1 implements View.OnClickListener {
    public /* synthetic */ OplusQsBrightnessSliderController this$0;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController = this.this$0;
        String str = oplusQsBrightnessSliderController.tag;
        boolean z = false;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("toggleClickListener onClick mMirror=" + (oplusQsBrightnessSliderController.mMirror != null) + " mListener=" + (oplusQsBrightnessSliderController.mListener != null));
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        if (((Boolean) this.this$0.isAutoModeDisabled.get()).booleanValue()) {
            return;
        }
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController2 = this.this$0;
        if (oplusQsBrightnessSliderController2.mCheckChangeFromInner) {
            return;
        }
        oplusQsBrightnessSliderController2.stopPhysicsMove();
        ClipBrightnessView brightnessIcon = this.this$0.getBrightnessIcon();
        if (brightnessIcon != null && brightnessIcon.getAutoMode()) {
            z = true;
        }
        String string = null;
        if (this.this$0.mListener != null) {
            IBrightnessSliderController iBrightnessSliderController = this.this$0.mMirror;
            IBrightnessSliderController iBrightnessSliderController2 = iBrightnessSliderController instanceof IBrightnessSliderController ? iBrightnessSliderController : null;
            if (iBrightnessSliderController2 != null) {
                iBrightnessSliderController2.setAutoBrightness(!z);
            }
            BrightnessController brightnessController = this.this$0.mListener;
            if (brightnessController != null) {
                brightnessController.onAutoChanged(!z);
            }
        }
        ClipBrightnessView brightnessIcon2 = this.this$0.getBrightnessIcon();
        if (brightnessIcon2 != null) {
            OplusQsBrightnessSliderController oplusQsBrightnessSliderController3 = this.this$0;
            brightnessIcon2.setContentDescription(!z ? oplusQsBrightnessSliderController3.getContext().getResources().getString(2131956552) : oplusQsBrightnessSliderController3.getContext().getResources().getString(2131956551));
        }
        String str2 = z ? "open" : KeyBaseController.VALUE_START_RESULT_CLOSE;
        boolean zBooleanValue = ((Boolean) this.this$0.separateStateManager.getEnableSeparateNotificationAndQS().getValue()).booleanValue();
        QsStatisticUtil.collectBrightnessAutoTypeEvent(this.this$0.getContext(), "brightness_auto_click", str2, zBooleanValue);
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController4 = this.this$0;
        Context context = oplusQsBrightnessSliderController4.getContext();
        String string2 = this.this$0.getContext().getResources().getString(2131956550);
        String string3 = z ? context.getResources().getString(2131955267, string2) : context.getResources().getString(2131955268, string2);
        string3.getClass();
        if (zBooleanValue) {
            QsColorUtil.INSTANCE.getClass();
            if (!QsColorUtil.isSuperPowerSaveState && z && VisualComfortSwitchObserver.Companion.getInstance().getVisualComfortSwitchOpen()) {
                string = context.getResources().getString(2131955250);
            }
        }
        TemporarilyInfoManager.InfoShowStyle infoShowStyle = zBooleanValue ? new TemporarilyInfoManager.InfoShowStyle(QsColorUtil.isTextNeedUseLightColorWhenLight(context, QsColorUtil.getQsColorState()) ? -1 : context.getColor(2131103752), 11) : new TemporarilyInfoManager.InfoShowStyle(14.0f, 2, QsColorUtil.getFooterTextColor(context), 1500L, true);
        String str3 = oplusQsBrightnessSliderController4.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM2 = Bundler$.ExternalSyntheticOutline0.m(str3, LogUtil.SEPARATOR);
            sbM2.append("show temporarily info text = ".concat(string3));
            Log.d("SystemUi--QuickSettings", sbM2.toString());
        }
        TemporarilyInfoManager.Companion.getInstance().show(string3, string, string2, infoShowStyle);
    }
}
