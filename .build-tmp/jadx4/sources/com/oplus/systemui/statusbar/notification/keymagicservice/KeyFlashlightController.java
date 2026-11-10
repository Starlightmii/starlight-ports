package com.oplus.systemui.statusbar.notification.keymagicservice;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.statusbar.policy.FlashlightController;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl;
import com.oplus.systemui.statusbar.notification.flashlight.seeding.FlashlightSeedling;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyFlashlightController extends KeyBaseController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "KeyFlashlightController";
    private final Context context;
    public boolean flashlightAvailable;
    private final flashlightAvailableListener.1 flashlightAvailableListener;
    private final FlashlightController flashlightController;
    private final FlashlightSeedling flashlightSeedling;
    private final KeyguardUpdateMonitor keyguardUpdateMonitor;
    private final Lazy shadeController;

    public KeyFlashlightController(Context context, FlashlightController flashlightController, FlashlightSeedling flashlightSeedling, KeyguardUpdateMonitor keyguardUpdateMonitor, Lazy lazy) {
        super(context, keyguardUpdateMonitor, lazy);
        this.context = context;
        this.flashlightController = flashlightController;
        this.flashlightSeedling = flashlightSeedling;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.shadeController = lazy;
        this.flashlightAvailable = ((FlashlightControllerImpl) flashlightController).isAvailable();
        flashlightAvailableListener.1 r1 = new flashlightAvailableListener.1();
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        this.flashlightAvailableListener = r1;
    }

    public final void closeFlashlight(int i) {
        if (!this.flashlightController.isEnabled()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "flashlight already close", null, 8);
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "close flashlight", null, 8);
        this.flashlightController.setFlashlightFromUser(false);
        this.flashlightSeedling.updateFlashlightSeeding(this.context, i, 1);
        dissmissNotificationCenter();
    }

    @Override // com.oplus.systemui.statusbar.notification.keymagicservice.KeyBaseController
    public Message handleActionButtonClick() {
        boolean zIsOnKeyGuard = isOnKeyGuard();
        boolean zIsEnabled = this.flashlightController.isEnabled();
        boolean zIsScreenOn = isScreenOn();
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("handleActionButtonClick, isOnKeyguard=", ", isEnabled=", ", isScreenOn=", zIsOnKeyGuard, zIsEnabled);
        sbM.append(zIsScreenOn);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        if (isOnKeyGuard() && !isScreenOn()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "ButtonClick on keyguard and screen is off. Ignore!", null, 8);
            return null;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        Bundle bundle = new Bundle();
        if (this.flashlightAvailable) {
            boolean zIsEnabled2 = this.flashlightController.isEnabled();
            Context context = this.context;
            if (zIsEnabled2) {
                bundle.putString(KeyBaseController.MAGIC_KEY_FLUID_CLOUD_TEXT, context.getString(2131954417));
                bundle.putInt(KeyBaseController.MAGIC_KEY_RES_ID, 2131235450);
                bundle.putString(KeyBaseController.MAGIC_KEY_RES_NAME, this.context.getResources().getResourceEntryName(2131235450));
                bundle.putString(KeyBaseController.MAGIC_KEY_SIDE_TEXT, this.context.getString(2131954413));
            } else {
                bundle.putString(KeyBaseController.MAGIC_KEY_FLUID_CLOUD_TEXT, context.getString(2131954416));
                bundle.putInt(KeyBaseController.MAGIC_KEY_RES_ID, 2131235450);
                bundle.putString(KeyBaseController.MAGIC_KEY_RES_NAME, this.context.getResources().getResourceEntryName(2131235450));
                bundle.putString(KeyBaseController.MAGIC_KEY_SIDE_TEXT, this.context.getString(2131954414));
            }
        } else {
            bundle.putString(KeyBaseController.MAGIC_KEY_FLUID_CLOUD_TEXT, this.context.getString(2131954415));
            bundle.putInt(KeyBaseController.MAGIC_KEY_RES_ID, 2131235451);
            bundle.putString(KeyBaseController.MAGIC_KEY_RES_NAME, this.context.getResources().getResourceEntryName(2131235451));
            bundle.putString(KeyBaseController.MAGIC_KEY_SIDE_TEXT, this.context.getString(2131954414));
        }
        if (this.flashlightSeedling.getFlashlightCardState()) {
            bundle.putString(KeyBaseController.MAGIC_KEY_ONLY_SHOE_SIDE_ANIM, "true");
        }
        bundle.putString(KeyBaseController.MAGIC_KEY_APP_NAME, "com.android.systemui");
        messageObtain.setData(bundle);
        return messageObtain;
    }

    @Override // com.oplus.systemui.statusbar.notification.keymagicservice.KeyBaseController
    public Message handleActionButtonLongPress() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("handleActionButtonLongPress, isOnKeyguard=", ", isEnabled=", isOnKeyGuard(), this.flashlightController.isEnabled()), null, 8);
        if (!this.flashlightAvailable) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "ButtonLongPress flashlightAvailable is false", null, 8);
            return setBundleButtonLongPressValue();
        }
        if (this.flashlightController.isEnabled()) {
            closeFlashlight(3);
        } else if (this.flashlightController.isEnabled()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "flashlight already open", null, 8);
        } else {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "open flashlight", null, 8);
            this.flashlightController.setFlashlightFromUser(true);
            dissmissNotificationCenter();
        }
        if (isOnKeyGuard()) {
            return setBundleButtonLongPressValue();
        }
        return null;
    }

    @Override // com.oplus.systemui.statusbar.notification.keymagicservice.KeyBaseController
    public final void onCrate() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "init call back", null, 8);
        this.flashlightController.addCallback(this.flashlightAvailableListener);
        this.flashlightAvailable = this.flashlightController.isAvailable();
        super.onCrate();
    }

    @Override // com.oplus.systemui.statusbar.notification.keymagicservice.KeyBaseController
    public final void onDestroy() {
        this.flashlightController.removeCallback(this.flashlightAvailableListener);
    }

    public final Message setBundleButtonLongPressValue() {
        Message messageObtain = Message.obtain();
        messageObtain.what = 2;
        Bundle bundle = new Bundle();
        if (this.flashlightAvailable) {
            bundle.putString(KeyBaseController.MAGIC_KEY_START_RESULT, KeyBaseController.VALUE_START_RESULT_CLOSE);
        } else {
            bundle.putString(KeyBaseController.MAGIC_KEY_LONG_PRESS_ERR_TEXT, this.context.getString(2131954415));
            bundle.putInt(KeyBaseController.MAGIC_KEY_RES_ID, 2131235451);
            bundle.putString(KeyBaseController.MAGIC_KEY_RES_NAME, this.context.getResources().getResourceEntryName(2131235451));
            bundle.putString(KeyBaseController.MAGIC_KEY_START_RESULT, KeyBaseController.VALUE_START_RESULT_ERROR);
            bundle.putString(KeyBaseController.MAGIC_KEY_APP_NAME, "com.android.systemui");
        }
        messageObtain.setData(bundle);
        return messageObtain;
    }
}
