package com.oplus.systemui.preventmode;

import android.content.Context;
import com.android.keyguard.OplusLockIconView$;
import com.android.settingslib.drawer.EntryController;
import com.android.settingslib.drawer.SwitchController;
import com.oplus.keyguard.settings.KeyguardSettingUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardPreventModeSwitchController extends SwitchController {
    public static final Companion Companion = new Companion();
    public static final String DEFAULT_SETTINGS_SWITCH_FALSE = "false";
    public static final String DEFAULT_SETTINGS_SWITCH_TRUE = "true";
    public static final String PREVENT_MODE_KEYHIN = "key_prevent_mode_strengthen_service";
    public static final String STRENGTHEN_SERVICE = "com.oplus.settings.category.ia.special_function";
    public static final String TAG = "KeyguardPreventModeSwitchController";
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public KeyguardPreventModeSwitchController(Context context) {
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    public String getErrorMessage(boolean z) {
        return "";
    }

    public SwitchController.MetaData getMetaData() {
        SwitchController.MetaData metaData = new SwitchController.MetaData();
        ((EntryController.MetaData) metaData).mCategory = STRENGTHEN_SERVICE;
        return metaData;
    }

    public String getSwitchKey() {
        return PREVENT_MODE_KEYHIN;
    }

    public final boolean isChecked() {
        boolean preventModeSettings = KeyguardSettingUtil.Companion.getPreventModeSettings(this.context);
        OplusLockIconView$.ExternalSyntheticOutline0.m("isChecked ", TAG, preventModeSettings);
        return preventModeSettings;
    }

    public final void onCheckedChanged(boolean z) {
        OplusLockIconView$.ExternalSyntheticOutline0.m("checkStatus ", TAG, z);
        KeyguardSettingUtil.Companion.setPreventModeSettings(this.context, z ? "true" : "false");
    }
}
