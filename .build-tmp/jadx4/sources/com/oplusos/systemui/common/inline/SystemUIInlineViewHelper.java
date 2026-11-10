package com.oplusos.systemui.common.inline;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.Settings;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSHighlightTileViewLabelTextHandler;
import com.oplus.sysuiinlineview.host.SysUiInlineResourcePool;
import com.oplus.sysuiinlineview.host.SysUiInlineViewHost;
import com.oplus.sysuiinlineview.host.widget.SysUiInlineViewFactory;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.QsBgThread;
import java.util.Map;
import java.util.function.Supplier;
import kotlin.Deprecated;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SystemUIInlineViewHelper {
    private static final String BUNDLE_KEY_IS_DARK_THEME = "key_is_dark_theme_color";
    public static final String BUNDLE_KEY_IS_SUPPORT_LIGHT = "isSupportLight";
    private static final String BUNDLE_KEY_IS_THIRD_THEME = "key_is_third_theme";
    private static final String BUNDLE_OLD_KEY_IS_DARK_THEME = "isDarkThemeColor";
    private static final String BUNDLE_OLD_KEY_IS_THIRD_THEME = "notification_scrim_bg_mask_color";
    private static final String INTENT_ACTION_INLINE_SERVICE = "oplus.inlineui.action.SYS_INLINE_UI";
    public static final String KEY_COLLAPSE_PANEL = "key_hide_system_ui";
    public static final String KEY_MIRROR_SHOWING = "key_mirror_showing";
    public static final String KEY_START_ACTIVITY = "key_jump_in_lock";
    public static final String KEY_TOUCH_ACTION = "motion_action";
    private static final String METADATA_KEY_DIALOG_CATEGORY = "oplus.service.quicksettings.QS_DETAIL_DIALOG_CATEGORY";
    private static final String METADATA_KEY_DIALOG_COMPONENT = "oplus.service.quicksettings.QS_DETAIL_DIALOG_COMPONENT";
    private static final String METADATA_KEY_DIALOG_HEIGHT = "oplus.service.quicksettings.QS_DETAIL_DIALOG_HEIGHT";
    private static final String SETTINGS_KEY_BLUETOOTH_DETAIL_DISABLED = "sysui_bluetooth_tile_disable_detail_dialog";
    private static final String TAG = "SystemUIInlineViewHelper";
    private static final SystemUIInlineViewHelper$defaultViewFactory$1 defaultViewFactory;
    private static final SysUiInlineResourcePool sysUiInlineResPool;
    public static final SystemUIInlineViewHelper INSTANCE = new SystemUIInlineViewHelper();
    private static final ComponentName COMPONENT_BLUETOOTH_DETAIL = new ComponentName("com.oplus.wirelesssettings", "com.oplus.wirelesssettings.OplusWirelessInlineViewService");
    private static final ComponentName COMPONENT_CELLULAR_DETAIL = new ComponentName("com.android.phone", "com.android.simsettings.inlineview.SimSettingsInlineViewService");
    private static final ComponentName COMPONENT_WIFI_DETAIL = new ComponentName("com.oplus.wirelesssettings", "com.oplus.wirelesssettings.OplusWirelessInlineViewService");
    private static final String CATEGORY_BLUETOOTH_DETAIL = "oplus.inlineui.category.QS_DETAIL_BLUETOOTH";
    private static final String CATEGORY_WIFI_DETAIL = "oplus.inlineui.category.QS_DETAIL_WIFI";
    private static final String CATEGORY_CELLULAR_DETAIL = "oplus.inlineui.category.QS_DETAIL_SIM_SETTINGS";
    private static final Map<String, String> tileDetailCategory = MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to(OplusQSHighlightTileViewLabelTextHandler.BLUETOOTH, CATEGORY_BLUETOOTH_DETAIL), TuplesKt.to(OplusQSHighlightTileViewLabelTextHandler.WIFI, CATEGORY_WIFI_DETAIL), TuplesKt.to(OplusQSHighlightTileViewLabelTextHandler.CELL, CATEGORY_CELLULAR_DETAIL)});

    static {
        SysUiInlineResourcePool sysUiInlineResourcePool = SysUiInlineResourcePool.Instance;
        sysUiInlineResPool = sysUiInlineResourcePool;
        defaultViewFactory = new SystemUIInlineViewHelper$defaultViewFactory$1();
        sysUiInlineResourcePool.setDefaultLooper(QsBgThread.INSTANCE.getThreadLooper());
    }

    public static final SysUiInlineViewHost getBluetoothDetailHost(Context context, UserHandle userHandle) {
        return getInlineViewHost(context, CATEGORY_BLUETOOTH_DETAIL, userHandle);
    }

    public static final SysUiInlineViewHost getInlineViewHost(Context context, String str, UserHandle userHandle) {
        return sysUiInlineResPool.getInlineViewHost(context, str, userHandle);
    }

    public static final String getQSDetailCategory(Bundle bundle) {
        if (bundle != null) {
            return bundle.getString(METADATA_KEY_DIALOG_CATEGORY);
        }
        return null;
    }

    public static final ComponentName getQSDetailComponent(Bundle bundle) {
        String string;
        if (bundle == null || (string = bundle.getString(METADATA_KEY_DIALOG_COMPONENT)) == null) {
            return null;
        }
        return ComponentName.unflattenFromString(string);
    }

    public static final int getQSDetailHeightResID(Bundle bundle) {
        if (bundle != null) {
            return bundle.getInt(METADATA_KEY_DIALOG_HEIGHT, 0);
        }
        return 0;
    }

    public static final String getTileDetailCategory(String str) {
        String str2 = tileDetailCategory.get(str);
        if (str2 != null) {
            return str2;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "getQSTileDetailCategory: no config, return tileSpec(" + str + ')', null, 8);
        return str;
    }

    public static final Bundle getTileServiceMetadata(Context context, ComponentName componentName) {
        Object obj;
        try {
            obj = Result.constructor-impl(context.getPackageManager().getServiceInfo(componentName, 794752).metaData);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (obj instanceof Result.Failure) {
            obj = null;
        }
        return (Bundle) obj;
    }

    public static final boolean isInlineServiceAvailable(Context context, ComponentName componentName) {
        PackageManager packageManager;
        Object obj;
        if (context == null || (packageManager = context.getPackageManager()) == null) {
            return false;
        }
        try {
            obj = Result.constructor-impl(Boolean.valueOf(INSTANCE.isComponentActuallyEnabled(packageManager, packageManager.getServiceInfo(componentName, 0))));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "isInlineServiceAvailable error: " + th2, null, 8);
        }
        Boolean bool = Boolean.FALSE;
        if (obj instanceof Result.Failure) {
            obj = bool;
        }
        return ((Boolean) obj).booleanValue();
    }

    public static final boolean isInlineServiceSupport(Context context, String str) {
        PackageManager packageManager;
        Object obj;
        if (context == null || (packageManager = context.getPackageManager()) == null) {
            return false;
        }
        try {
            obj = Result.constructor-impl(Boolean.valueOf(!packageManager.queryIntentServices(new Intent("oplus.inlineui.action.SYS_INLINE_UI").addCategory(str), 64).isEmpty()));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Boolean bool = Boolean.FALSE;
        if (obj instanceof Result.Failure) {
            obj = bool;
        }
        return ((Boolean) obj).booleanValue();
    }

    public static final boolean isSupportBluetoothDetail(Context context) {
        if (!isInlineServiceAvailable(context, COMPONENT_BLUETOOTH_DETAIL)) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "isSupportBluetoothDetail: BluetoothTileDetailService not Available", null, 8);
            return false;
        }
        if (!LogUtil.getSInternal() || Settings.Global.getInt(context.getContentResolver(), SETTINGS_KEY_BLUETOOTH_DETAIL_DISABLED, 0) == 0) {
            return true;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "isSupportBluetoothDetail: force disabled bluetooth detail", null, 8);
        return false;
    }

    public static final boolean isSupportCellularDetail(Context context) {
        return isInlineServiceSupport(context, CATEGORY_CELLULAR_DETAIL);
    }

    public static final boolean isSupportWifiDetail(Context context) {
        return isInlineServiceSupport(context, CATEGORY_WIFI_DETAIL);
    }

    public static final void setComponentProvider(String str, Supplier<ComponentName> supplier) {
        sysUiInlineResPool.setComponentProvider(str, supplier);
    }

    public static final void setQSDetailDarkTheme(Bundle bundle, boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setQSDetailDarkTheme: isDarkTheme=", z), null, 8);
        bundle.putBoolean(BUNDLE_KEY_IS_DARK_THEME, z);
    }

    @Deprecated
    public static final void setQSDetailOldDarkTheme(Bundle bundle, boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setQSDetailOldDarkTheme: isDarkTheme=", z), null, 8);
        bundle.putBoolean(BUNDLE_OLD_KEY_IS_DARK_THEME, z);
    }

    @Deprecated
    public static final void setQSDetailOldThirdTheme(Bundle bundle, boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setQSDetailOldThirdTheme: isThirdTheme=", z), null, 8);
        bundle.putBoolean(BUNDLE_OLD_KEY_IS_THIRD_THEME, z);
    }

    public static final void setQSDetailThirdTheme(Bundle bundle, boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setQSDetailThirdTheme: isThirdTheme=", z), null, 8);
        bundle.putBoolean(BUNDLE_KEY_IS_THIRD_THEME, z);
    }

    public static final void setViewFactory(String str, SysUiInlineViewFactory sysUiInlineViewFactory) {
        sysUiInlineResPool.setViewFactory(str, sysUiInlineViewFactory);
    }

    public static /* synthetic */ void setViewFactory$default(String str) {
        setViewFactory(str, defaultViewFactory);
    }

    public final boolean isComponentActuallyEnabled(PackageManager packageManager, ComponentInfo componentInfo) {
        int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentInfo.getComponentName());
        if (componentEnabledSetting != 0) {
            return componentEnabledSetting == 1;
        }
        return componentInfo.isEnabled();
    }

    public static final void setViewFactory(String str) {
        setViewFactory$default(str);
    }

    public static final void setQSDetailDarkTheme(Bundle bundle) {
        setQSDetailDarkTheme(bundle, false);
    }

    @Deprecated
    public static final void setQSDetailOldDarkTheme(Bundle bundle) {
        setQSDetailOldDarkTheme(bundle, false);
    }

    @Deprecated
    public static final void setQSDetailOldThirdTheme(Bundle bundle) {
        setQSDetailOldThirdTheme(bundle, false);
    }

    public static final void setQSDetailThirdTheme(Bundle bundle) {
        setQSDetailThirdTheme(bundle, false);
    }
}
