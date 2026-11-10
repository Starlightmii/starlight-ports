package com.oplus.zoom.ui.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import com.oplus.zoom.common.ZoomSettingDispatcher;
import com.oplus.zoom.ui.ZoomUiManager;
import com.oplus.zoom.ui.floathandle.FloatHandleController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ZoomUiObserver implements ZoomSettingDispatcher.CallbackListener {
    private static final int FLAG_IN_CHILD_MODE = 1;
    private static final int FLAG_IN_SUPERPOWER_MODE = 1;
    private static final int FLAG_IN_ZEN_MODE = 1;
    private static final String TAG = "ZoomUiObserver";
    private static final int USER_HANDLE = 10000;
    private final Context mContext;
    private final ZoomUiManager mUiManager;
    private final Uri mSuperPowerModeSetting = Settings.System.getUriFor("super_powersave_launcher_enter");
    private final Uri mChildModeSetting = Settings.Global.getUriFor("children_mode_on");
    private final Uri mZenModeSetting = Settings.Secure.getUriFor("op_breath_mode_status");

    public ZoomUiObserver(Context context, ZoomUiManager zoomUiManager) {
        this.mContext = context;
        this.mUiManager = zoomUiManager;
        ZoomSettingDispatcher.getInstance().addCallbackListener(this);
    }

    @Override // com.oplus.zoom.common.ZoomSettingDispatcher.CallbackListener
    public void onBroadCastReceive(Context context, Intent intent) {
        String action = intent.getAction();
        action.hashCode();
        if (!action.equals("oplus.intent.action.MULTI_APP_PACKAGE_REMOVED")) {
            UiLogUtils.w("Invalid action : " + action + " will be ignore!");
            return;
        }
        Uri data = intent.getData();
        if (data == null) {
            UiLogUtils.w("ACTION_MULTI_APP_REMOVED uri is null");
            return;
        }
        String schemeSpecificPart = data.getSchemeSpecificPart();
        if (TextUtils.isEmpty(schemeSpecificPart)) {
            UiLogUtils.w("ACTION_MULTI_APP_REMOVED pkgName is null");
            return;
        }
        UiLogUtils.i("Receive broadcast : ACTION_MULTI_APP_REMOVED " + schemeSpecificPart);
        this.mUiManager.getFloatHandleController().onPackageRemove(schemeSpecificPart, intent.getIntExtra("android.intent.extra.user_handle", -1));
    }

    @Override // com.oplus.zoom.common.ZoomSettingDispatcher.CallbackListener
    public void onPackageRemove(String str, int i) {
        UiLogUtils.d(" onPackageRemoved = " + str + " uid = " + i);
        this.mUiManager.getFloatHandleController().onPackageRemove(str, UserHandle.getUserId(i));
    }

    @Override // com.oplus.zoom.common.ZoomSettingDispatcher.CallbackListener
    public void onSettingChanged(boolean z, Uri uri, int i) {
        try {
            boolean z2 = true;
            if (uri.equals(this.mSuperPowerModeSetting)) {
                int intForUser = Settings.System.getIntForUser(this.mContext.getContentResolver(), "super_powersave_launcher_enter", 0, 0);
                this.mUiManager.getFloatHandleController().updateSupperPowerModeState(intForUser == 1);
                if (intForUser == 1) {
                    this.mUiManager.getTipsController().hideAllTipsView();
                }
                UiLogUtils.d("ZoomUiObserver", "updateSuperPowerModeEnable superPowerMode:" + intForUser);
                return;
            }
            if (uri.equals(this.mChildModeSetting)) {
                int i2 = Settings.Global.getInt(this.mContext.getContentResolver(), "children_mode_on", 0);
                FloatHandleController floatHandleController = this.mUiManager.getFloatHandleController();
                if (i2 != 1) {
                    z2 = false;
                }
                floatHandleController.updateChildModeState(z2);
                UiLogUtils.d("ZoomUiObserver", "updateChildModeEnable childMode:" + i2);
                return;
            }
            if (uri.equals(this.mZenModeSetting)) {
                int i3 = Settings.Secure.getInt(this.mContext.getContentResolver(), "op_breath_mode_status", 0);
                FloatHandleController floatHandleController2 = this.mUiManager.getFloatHandleController();
                if (i3 != 1) {
                    z2 = false;
                }
                floatHandleController2.updateZenModeState(z2);
                UiLogUtils.d("ZoomUiObserver", "updateZenModeEnable zenMode:" + i3);
            }
        } catch (Exception e) {
            UiLogUtils.e("ZoomUiObserver", "onSettingChanged error, e: ", e);
        }
    }

    @Override // com.oplus.zoom.common.ZoomSettingDispatcher.CallbackListener
    public void onUserSwitched(int i) {
        UiLogUtils.d(" onUserSwitched userId = " + i);
        this.mUiManager.getTipsController().onUserSwitch(i);
        this.mUiManager.getFloatHandleController().onUserSwitched(i);
    }
}
