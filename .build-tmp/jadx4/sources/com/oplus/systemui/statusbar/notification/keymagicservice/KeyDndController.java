package com.oplus.systemui.statusbar.notification.keymagicservice;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyDndController extends KeyBaseController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "KeyDndController";
    private final Context context;
    private final KeyguardUpdateMonitor keyguardUpdateMonitor;
    private final Lazy shadeController;
    private final ZenModeController zenModeController;

    public KeyDndController(Context context, ZenModeController zenModeController, KeyguardUpdateMonitor keyguardUpdateMonitor, Lazy lazy) {
        super(context, keyguardUpdateMonitor, lazy);
        this.context = context;
        this.zenModeController = zenModeController;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.shadeController = lazy;
    }

    @Override // com.oplus.systemui.statusbar.notification.keymagicservice.KeyBaseController
    public Message handleActionButtonClick() {
        boolean zIsOnKeyGuard = isOnKeyGuard();
        boolean zIsScreenOn = isScreenOn();
        int zen = this.zenModeController.getZen();
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("handleActionButtonClick, isOnKeyguard=", ", isScreenOn=", "， Dnd State=", zIsOnKeyGuard, zIsScreenOn);
        sbM.append(zen);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        if (isOnKeyGuard() && !isScreenOn()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "KeyDndController-->ButtonClick on keyguard and screen is off. Ignore!");
            }
            return null;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        Bundle bundle = new Bundle();
        int zen2 = this.zenModeController.getZen();
        Context context = this.context;
        if (zen2 == 0) {
            bundle.putString(KeyBaseController.MAGIC_KEY_SIDE_TEXT, context.getString(2131954412));
            bundle.putString(KeyBaseController.MAGIC_KEY_FLUID_CLOUD_TEXT, this.context.getString(2131954416));
            bundle.putInt(KeyBaseController.MAGIC_KEY_RES_ID, 2131235448);
            bundle.putString(KeyBaseController.MAGIC_KEY_RES_NAME, this.context.getResources().getResourceEntryName(2131235448));
        } else {
            bundle.putString(KeyBaseController.MAGIC_KEY_SIDE_TEXT, context.getString(2131954411));
            bundle.putString(KeyBaseController.MAGIC_KEY_FLUID_CLOUD_TEXT, this.context.getString(2131954417));
            bundle.putInt(KeyBaseController.MAGIC_KEY_RES_ID, 2131235449);
            bundle.putString(KeyBaseController.MAGIC_KEY_RES_NAME, this.context.getResources().getResourceEntryName(2131235449));
        }
        bundle.putString(KeyBaseController.MAGIC_KEY_APP_NAME, "com.android.systemui");
        messageObtain.setData(bundle);
        String string = bundle.getString(KeyBaseController.MAGIC_KEY_SIDE_TEXT);
        String string2 = bundle.getString(KeyBaseController.MAGIC_KEY_FLUID_CLOUD_TEXT);
        int i = bundle.getInt(KeyBaseController.MAGIC_KEY_RES_ID);
        String string3 = bundle.getString(KeyBaseController.MAGIC_KEY_RES_NAME);
        StringBuilder sbM2 = ExifInterface$.ExternalSyntheticOutline1.m("Send Dnd replyMsg: side_text=", string, ", fluid_cloud_text=", string2, ",icon_res_id=");
        sbM2.append(i);
        sbM2.append(",res_name=");
        sbM2.append(string3);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM2.toString(), null, 8);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "KeyDndController-->send to gesture");
        }
        return messageObtain;
    }

    @Override // com.oplus.systemui.statusbar.notification.keymagicservice.KeyBaseController
    public Message handleActionButtonLongPress() {
        boolean zIsOnKeyGuard = isOnKeyGuard();
        boolean zIsScreenOn = isScreenOn();
        int zen = this.zenModeController.getZen();
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("handleActionButtonLongPress, isOnKeyguard=", ", isScreenOn=", "， Dnd State=", zIsOnKeyGuard, zIsScreenOn);
        sbM.append(zen);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        Message messageObtain = Message.obtain();
        if (this.zenModeController.getZen() != 0) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "set Dnd Mode OFF", null, 8);
            this.zenModeController.setZen(TAG, (Uri) null, 0);
            return messageObtain;
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "set Dnd Mode ON", null, 8);
        if (isScreenOn()) {
            this.zenModeController.setZen(TAG, (Uri) null, 1);
        } else {
            Handler mainHandler = getMainHandler();
            openDnd.1 r2 = new openDnd.1();
            r2.this$0 = this;
            VarHandle.storeStoreFence();
            mainHandler.postDelayed(r2, 500L);
        }
        dissmissNotificationCenter();
        return messageObtain;
    }

    @Override // com.oplus.systemui.statusbar.notification.keymagicservice.KeyBaseController
    public final void onDestroy() {
    }
}
