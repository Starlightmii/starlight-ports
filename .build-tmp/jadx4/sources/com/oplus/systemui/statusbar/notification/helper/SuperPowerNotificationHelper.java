package com.oplus.systemui.statusbar.notification.helper;

import android.content.Context;
import android.util.ArraySet;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplus.systemui.statusbar.observer.SuperPowerWhiteListObserver;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SuperPowerNotificationHelper {
    public static volatile SuperPowerNotificationHelper sInstance;
    public final Context mContext;
    public String mSuperPowerListString;
    public final String[] mSuperPowerspaceWhitelist;
    public final Set mAppEncryptListeners = new ArraySet();
    public final List mSuperPowerWhiteList = new ArrayList();

    public SuperPowerNotificationHelper(Context context) {
        this.mContext = context;
        this.mSuperPowerspaceWhitelist = context.getResources().getStringArray(2130903299);
        updateSuperPowerList();
        updateSuperPowerWhiteList();
    }

    public static SuperPowerNotificationHelper getInstance(Context context) {
        if (sInstance == null) {
            synchronized (SuperPowerNotificationHelper.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new SuperPowerNotificationHelper(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public final void updateSuperPowerList() {
        this.mSuperPowerListString = CommonSettingsValueProxy.getSuperPowerListState(this.mContext);
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "SuperPowerNotificationHelper", "updateSuperPowerList: " + this.mSuperPowerListString);
    }

    public final void updateSuperPowerWhiteList() {
        this.mSuperPowerWhiteList.clear();
        this.mSuperPowerWhiteList.addAll(SuperPowerWhiteListObserver.Companion.getInstance().getSuperPowerWhiteList());
        if (this.mSuperPowerWhiteList.size() == 0) {
            this.mSuperPowerWhiteList.addAll(Arrays.asList(this.mSuperPowerspaceWhitelist));
        }
        if (LogUtil.getNormalDebug()) {
            Iterator it = this.mSuperPowerWhiteList.iterator();
            while (it.hasNext()) {
                FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("updateSuperPowerWhiteList: ", (String) it.next(), LogUtil.TAG_NOTIFICATION, "SuperPowerNotificationHelper");
            }
        }
    }
}
