package com.oplus.systemui.statusbar.pipeline.mobile.data.ims;

import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.serialization.Bundler$;
import com.android.ims.FeatureConnector;
import com.android.ims.ImsManager;
import com.oplus.systemui.aod.controller.BaseAodClockLayoutController$bindSceneViewHolder$2$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.BiConsumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ImsManagerFeatureConnectorListenerAdapt implements FeatureConnector.Listener {
    public static final Companion Companion = new Companion();
    public static final String TAG = "ImsManagerFeatureConnectorListenerAdapt";
    public final int phoneId;
    private final ReadyCallback readyCb;
    private final BiConsumer<Integer, Integer> unavailableCb;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface ReadyCallback {
    }

    public ImsManagerFeatureConnectorListenerAdapt(int i, ReadyCallback readyCallback, BiConsumer<Integer, Integer> biConsumer) {
        this.phoneId = i;
        this.readyCb = readyCallback;
        this.unavailableCb = biConsumer;
    }

    public void connectionReady(ImsManager imsManager, int i) {
        if (imsManager != null) {
            ReadyCallback readyCallback = this.readyCb;
            int i2 = this.phoneId;
            ImsListenerProxy$init$1$listener$1 imsListenerProxy$init$1$listener$1 = (ImsListenerProxy$init$1$listener$1) readyCallback;
            imsListenerProxy$init$1$listener$1.getClass();
            ImsListenerProxy.access$imsConnectorReadyCallback(imsListenerProxy$init$1$listener$1.this$0, imsManager, i2, i);
        }
        String strM = ListPopupWindow$.ExternalSyntheticOutline0.m(this.phoneId, TAG);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(i, "connectionReady subId ", "SystemUi--Statusbar", Bundler$.ExternalSyntheticOutline0.m(strM, LogUtil.SEPARATOR));
        }
    }

    public final void connectionUnavailable(int i) {
        this.unavailableCb.accept(Integer.valueOf(i), Integer.valueOf(this.phoneId));
        String strM = ListPopupWindow$.ExternalSyntheticOutline0.m(this.phoneId, TAG);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(i, "connectionUnavailable reason ", "SystemUi--Statusbar", Bundler$.ExternalSyntheticOutline0.m(strM, LogUtil.SEPARATOR));
        }
    }
}
