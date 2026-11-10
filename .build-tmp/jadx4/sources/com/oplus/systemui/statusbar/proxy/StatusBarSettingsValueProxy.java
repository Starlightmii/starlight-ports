package com.oplus.systemui.statusbar.proxy;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.UserInfo;
import android.os.UserManager;
import android.provider.Settings;
import androidx.appcompat.widget.ListPopupWindow$;
import com.oplus.systemui.common.util.OTAUtils;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.BrandUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusBarSettingsValueProxy extends AbsSettingsValueProxy {
    public static final Companion Companion = new Companion();
    public static final String KEY_NOTIFICATION_PROMPT_MODE = "notification_prompt_mode";
    private static final String TAG = "StatusBarSettingsValueProxy";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int getNotificationPromptModeState(Context context) {
            return AbsSettingsValueProxy.Companion.getSecureIntValue(context, StatusBarSettingsValueProxy.KEY_NOTIFICATION_PROMPT_MODE, 0);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.proxy.StatusBarSettingsValueProxy$checkValidity$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Context $context;
        public /* synthetic */ StatusBarSettingsValueProxy this$0;

        @Override // java.lang.Runnable
        public final void run() {
            StatusBarSettingsValueProxy statusBarSettingsValueProxy = this.this$0;
            Context context = this.$context;
            if (OTAUtils.isOTAUpgrade(context)) {
                ContentResolver contentResolver = context.getContentResolver();
                UserManager userManager = UserManager.get(context);
                if (userManager != null) {
                    Iterator it = userManager.getUsers().iterator();
                    while (it.hasNext()) {
                        int identifier = ((UserInfo) it.next()).getUserHandle().getIdentifier();
                        if (Settings.Secure.getIntForUser(contentResolver, "clock_seconds", 0, identifier) != 0) {
                            Settings.Secure.putIntForUser(contentResolver, "clock_seconds", 0, identifier);
                            LogUtil.i$default(LogUtil.TAG_STATUSBAR, statusBarSettingsValueProxy.getTAG(), ListPopupWindow$.ExternalSyntheticOutline0.m(identifier, "maybeResetClockSecondsOnOta: clock_seconds=0 for user "), null, 8);
                        }
                    }
                }
            }
        }
    }

    @Override // com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy
    public void checkValidity(Context context) {
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, getTAG(), "checkValidity: start", null, 8);
        BrandUtils.INSTANCE.getClass();
        if (BrandUtils.isRealme) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            anonymousClass1.$context = context;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnWorkThread(anonymousClass1);
        }
    }
}
