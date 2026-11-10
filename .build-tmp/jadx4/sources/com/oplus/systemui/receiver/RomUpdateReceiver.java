package com.oplus.systemui.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.FileProvider$;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.DependencyEx;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialRomUpdateUtils;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialUtils;
import com.oplus.systemui.media.seedling.rus.OplusMediaRusUpdateManager;
import com.oplus.systemui.preventmode.OplusPreventModeUtils;
import com.oplus.systemui.provider.ProviderUtil;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.receiver.AbstractReceiver;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RomUpdateReceiver extends AbstractReceiver {
    private static final String ACTION_ROM_UPDATE = "oplus.intent.action.ROM_UPDATE_CONFIG_SUCCESS";
    public static final Companion Companion = new Companion();
    private static final String EXTRA_CONFIG_SUCCESS = "ROM_UPDATE_CONFIG_LIST";
    private static final String LAUNCHER_BADGE_SUPPORT_LIST = "launcher_badgesupport_list";
    private static final String NOTIFICATION_CONFIG = "sys_notificationmanager_config_list";
    private static final String NOTIFICATION_INTERCEPT_BLACKLISTT_KEY = "sys_nms_intercept_blacklist";
    private static final String ROM_UPDATE_CATEGORY;
    private static final List<String> ROM_UPDATE_FILTER_NAMES;
    private static final String STATUSBAR_TINT_BLACKLIST_KEY = "sys_statusbar_tint_list";
    private static final String STATUSBAR_WIFI_SHOW_KEY = "sys_statusbar_wifi_show";
    private static final String SYS_NOT_AUTO_CLEAR_NOTIFICATION_CONFIG_LIST = "sys_not_auto_clear_notification_config_list";
    private static final String TAG = "RomUpdateReceiver";
    private static final String filterNames;
    private static volatile RomUpdateReceiver sInstance;

    /* JADX INFO: renamed from: com.oplus.systemui.receiver.RomUpdateReceiver$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IReceiverListener {
        public /* synthetic */ RomUpdateReceiver this$0;

        @Override // com.oplusos.systemui.common.receiver.IReceiverListener
        public final void onReceive(Context context, Intent intent) {
            this.this$0.getClass();
            String action = intent.getAction();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("receive action: ", action, "RomUpdateReceiver-->", "SystemUi--Common");
            }
            if (TextUtils.isEmpty(action)) {
                return;
            }
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra(RomUpdateReceiver.EXTRA_CONFIG_SUCCESS);
            if (stringArrayListExtra == null || stringArrayListExtra.isEmpty()) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "RomUpdateReceiver-->list null or empty");
                    return;
                }
                return;
            }
            if (stringArrayListExtra.contains(ProviderUtil.QUICK_SETTINGS_TILE_KEY)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "RomUpdateReceiver-->changeTableNameList contians sys_systemui_quicksettingstile_blacklist");
                }
                new handleIntent.4(context, 0).execute(new Void[0]);
            }
            if (stringArrayListExtra.contains("sys_aod_prevent_burn_settings")) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_AOD)) {
                    Log.d("SystemUi--Aod", "RomUpdateReceiver-->changeTableNameList contians sys_aod_prevent_burn_settings");
                }
                new handleIntent.4(context, 4).execute(new Void[0]);
            }
            if (stringArrayListExtra.contains(ProviderUtil.AIR_VIEW_CONFIGS_KEY)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_AV)) {
                    Log.d("SystemUi--AirView", "RomUpdateReceiver-->changeTableNameList contians apps_airview_configs");
                }
                new handleIntent.4(context, 5).execute(new Void[0]);
            }
            if (stringArrayListExtra.contains(OplusPreventModeUtils.PREVENT_MODE_SETTINGS_TILE_KEY)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "RomUpdateReceiver-->changeTableNameList contians sys_prevent_mode_settings");
                }
                new handleIntent.4(context, 1).execute(new Void[0]);
            }
            if (stringArrayListExtra.contains("sys_wakeup_interruption_settings")) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "RomUpdateReceiver-->changeTableNameList contians sys_wakeup_interruption_settings");
                }
                new handleIntent.4(context, 2).execute(new Void[0]);
            }
            KeyguardFeatureOption.INSTANCE.getClass();
            if (KeyguardFeatureOption.isNonLocalHbmSupportOpticalFp() && stringArrayListExtra.contains("display_brightness_app_list")) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_AOD)) {
                    Log.d("SystemUi--Aod", "RomUpdateReceiver-->changeTableNameList contians display_brightness_app_list");
                }
                new handleIntent.4(context, 3).execute(new Void[0]);
            }
            if (KeyguardFeatureOption.isSupportOtherPictorial && stringArrayListExtra.contains(PictorialRomUpdateUtils.KGD_PICTORIAL_CONFIG_LIST)) {
                String pictorialPackageName = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).getPictorialPackageName();
                StringBuilder sb = PictorialUtils.sStringBuilder;
                if (KeyguardUtils.hasPackage(context, pictorialPackageName)) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                        Log.d("SystemUi--Keyguard", "RomUpdateReceiver-->changeTableNameList contians sys_systemui_pictorial_config_list");
                    }
                    new handleIntent.4(context, 6).execute(new Void[0]);
                }
            }
            if (stringArrayListExtra.contains(RomUpdateReceiver.STATUSBAR_WIFI_SHOW_KEY)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "RomUpdateReceiver-->changeTableNameList contians sys_statusbar_wifi_show");
                }
                new handleIntent.4(context, 7).execute(new Void[0]);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final RomUpdateReceiver getInstance() {
            if (RomUpdateReceiver.sInstance == null) {
                synchronized (RomUpdateReceiver.class) {
                    if (RomUpdateReceiver.sInstance == null) {
                        RomUpdateReceiver romUpdateReceiver = new RomUpdateReceiver();
                        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                        anonymousClass1.this$0 = romUpdateReceiver;
                        VarHandle.storeStoreFence();
                        romUpdateReceiver.addListener(anonymousClass1);
                        RomUpdateReceiver.sInstance = romUpdateReceiver;
                    }
                }
            }
            return RomUpdateReceiver.sInstance;
        }
    }

    static {
        List<String> listListOf = CollectionsKt__CollectionsKt.listOf(new String[]{OplusMediaRusUpdateManager.OPLUS_MEDIA_CONTROLLER_CONFIG, ProviderUtil.QUICK_SETTINGS_TILE_KEY, "sys_aod_prevent_burn_settings", ProviderUtil.AIR_VIEW_CONFIGS_KEY, OplusPreventModeUtils.PREVENT_MODE_SETTINGS_TILE_KEY, PictorialRomUpdateUtils.KGD_PICTORIAL_CONFIG_LIST, NOTIFICATION_CONFIG, NOTIFICATION_INTERCEPT_BLACKLISTT_KEY, STATUSBAR_TINT_BLACKLIST_KEY, LAUNCHER_BADGE_SUPPORT_LIST, STATUSBAR_WIFI_SHOW_KEY, "display_brightness_app_list", SYS_NOT_AUTO_CLEAR_NOTIFICATION_CONFIG_LIST});
        ROM_UPDATE_FILTER_NAMES = listListOf;
        String strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(listListOf, "|", (CharSequence) null, (CharSequence) null, new RomUpdateReceiver$$ExternalSyntheticLambda0(), 30);
        filterNames = strJoinToString$default;
        ROM_UPDATE_CATEGORY = FileProvider$.ExternalSyntheticOutline0.m("oplusBrEx@oplus.intent.action.ROM_UPDATE_CONFIG_SUCCESS@", strJoinToString$default);
    }

    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public void beforeRegister(IntentFilter intentFilter) {
        super.beforeRegister(intentFilter);
        String str = ROM_UPDATE_CATEGORY;
        intentFilter.addCategory(str);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("IntentFilter add Category: ", str, "RomUpdateReceiver-->", "SystemUi--QuickSettings");
        }
    }

    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public String[] getActions() {
        return new String[]{ACTION_ROM_UPDATE};
    }
}
