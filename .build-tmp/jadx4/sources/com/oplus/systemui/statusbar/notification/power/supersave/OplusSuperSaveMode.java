package com.oplus.systemui.statusbar.notification.power.supersave;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import com.oplus.systemui.statusbar.notification.power.LowBatteryUiController;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import java.lang.invoke.VarHandle;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSuperSaveMode {
    private static final String AUTO_ENTER_FLAG = "auto_enter_flag";
    private static final String AUTO_ENTER_SP = "com.oplus.powerUi_shared_prefs";
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusSuperSaveMode";
    private final Context context;
    private final Lazy sharedPreferences$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusSuperSaveMode(Context context) {
        this.context = context;
        OplusSuperSaveMode$$ExternalSyntheticLambda0 oplusSuperSaveMode$$ExternalSyntheticLambda0 = new OplusSuperSaveMode$$ExternalSyntheticLambda0();
        oplusSuperSaveMode$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.sharedPreferences$delegate = LazyKt__LazyJVMKt.lazy(oplusSuperSaveMode$$ExternalSyntheticLambda0);
    }

    private final SharedPreferences getSharedPreferences() {
        return (SharedPreferences) this.sharedPreferences$delegate.getValue();
    }

    private final void setSharedPreferenceValue(boolean z) {
        if (getSharedPreferences() != null) {
            SharedPreferences.Editor editorEdit = getSharedPreferences().edit();
            editorEdit.putBoolean(AUTO_ENTER_FLAG, z);
            editorEdit.apply();
        }
    }

    public final void enterSuperSaveMode() {
        Context context = this.context;
        if (context != null) {
            HashMap map = new HashMap();
            map.put("dp_reserved_proto_js_channel", "{\"item\": [{\"power_save_action\": {\"event_id\": 19, \"dialog_click_state\": 1}}]}");
            OplusTrackProxyUtil.onCommon(context, "20082", "auto_enter_super_save_power_mode_action", map);
        }
        setSharedPreferenceValue(true);
        LowBatteryUiController.getInstance().dismissAll();
        Settings.System.putIntForUser(this.context.getContentResolver(), "super_powersave_launcher_enter", 1, 0);
    }

    public final void exitSuperSaveMode() {
        setSharedPreferenceValue(false);
        CommonSettingsValueProxy.Companion.setSuperPowerAutoSaveState(this.context);
        Settings.System.putIntForUser(this.context.getContentResolver(), "super_powersave_launcher_enter", 0, 0);
    }

    public final boolean getAutoEnterSuperSavePowerModeFlag() {
        if (getSharedPreferences() != null) {
            return getSharedPreferences().getBoolean(AUTO_ENTER_FLAG, false);
        }
        return false;
    }

    public final void resetAutoEnterSupperSavePowerModeFlag() {
        setSharedPreferenceValue(false);
    }
}
