package com.oplus.systemui.volume.repository;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.volume.OplusVolumeControllerEx;
import com.oplus.systemui.aod.utils.OplusOSAodManager;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplus.systemui.volume.OplusVolumeDialogInterface;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeRepository implements OplusVolumeRepositoryEx {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OplusVolumeRepository";
    private final Context context;
    private final VolumeDialogController.Callbacks mControllerCallbackH;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private OplusVolumeDialogInterface mOplusVolumeDialogInterface;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusVolumeRepository(Context context) {
        this.context = context;
        OplusVolumeRepository$mControllerCallbackH$1 oplusVolumeRepository$mControllerCallbackH$1 = new OplusVolumeRepository$mControllerCallbackH$1();
        oplusVolumeRepository$mControllerCallbackH$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mControllerCallbackH = oplusVolumeRepository$mControllerCallbackH$1;
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // com.oplus.systemui.volume.repository.OplusVolumeRepositoryEx
    public void setController(VolumeDialogController volumeDialogController, OplusVolumeControllerEx oplusVolumeControllerEx) {
        volumeDialogController.addCallback(this.mControllerCallbackH, this.mHandler);
        oplusVolumeControllerEx.addCallback(this.mControllerCallbackH, this.mHandler);
    }

    @Override // com.oplus.systemui.volume.repository.OplusVolumeRepositoryEx
    public void setViewImpl(OplusVolumeDialogInterface oplusVolumeDialogInterface) {
        this.mOplusVolumeDialogInterface = oplusVolumeDialogInterface;
    }

    public final boolean shouldShow() {
        if (OplusOSAodManager.getInstance().mAodIsShow) {
            LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, "don't show volume dialog when aod is showing", null, 8);
            return false;
        }
        if (OplusSubDisplayUtils.Companion.getInstance(this.context).getSupportSubDisplay()) {
            return DeviceStateController.getInstance().isOpenOrHalfOpen();
        }
        LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, "not dragonfly Device, show volume dialog", null, 8);
        return true;
    }
}
