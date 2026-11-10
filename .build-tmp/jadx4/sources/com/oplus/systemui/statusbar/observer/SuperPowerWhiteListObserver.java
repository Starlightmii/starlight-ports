package com.oplus.systemui.statusbar.observer;

import android.content.Context;
import android.net.Uri;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.oplus.systemui.statusbar.notification.helper.SuperPowerNotificationHelper;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SuperPowerWhiteListObserver extends AbstractObserver {
    public static final String SETTINGS_SUPER_POWER_WHITELIST_KEY = "super_power_notification_whitelist";
    private static final String TAG = "SuperPowerWhiteListObserver";
    private ArrayList<String> superPowerWhiteList;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new SuperPowerWhiteListObserver$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final SuperPowerWhiteListObserver getInstance() {
            return (SuperPowerWhiteListObserver) SuperPowerWhiteListObserver.instance$delegate.getValue();
        }
    }

    public static SuperPowerWhiteListObserver $r8$lambda$c6Op5H2LAHXHZAdO4ut1JnWCR9c() {
        SuperPowerWhiteListObserver superPowerWhiteListObserver = new SuperPowerWhiteListObserver();
        superPowerWhiteListObserver.superPowerWhiteList = new ArrayList<>();
        return superPowerWhiteListObserver;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return SETTINGS_SUPER_POWER_WHITELIST_KEY;
    }

    public final ArrayList<String> getSuperPowerWhiteList() {
        return this.superPowerWhiteList;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSecureUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        String secureStringValue;
        Set setKeySet;
        super.onChange(z);
        Context context = getContext();
        if (context == null || (secureStringValue = AbsSettingsValueProxy.Companion.getSecureStringValue(context, getKey(), 0)) == null || secureStringValue.length() == 0 || secureStringValue.equals("{}")) {
            return;
        }
        try {
            setKeySet = new JSONObject(secureStringValue).keySet();
        } catch (Exception e) {
            GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "change to json error: ", e.getMessage(), LogUtil.TAG_STATUSBAR, TAG);
            setKeySet = null;
        }
        if (setKeySet != null) {
            this.superPowerWhiteList.clear();
            this.superPowerWhiteList.addAll(setKeySet);
            SuperPowerNotificationHelper.getInstance(context).updateSuperPowerWhiteList();
        }
    }

    public final void setSuperPowerWhiteList(ArrayList<String> arrayList) {
        this.superPowerWhiteList = arrayList;
    }
}
