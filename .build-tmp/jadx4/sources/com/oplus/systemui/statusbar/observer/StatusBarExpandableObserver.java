package com.oplus.systemui.statusbar.observer;

import android.content.Context;
import android.net.Uri;
import com.android.systemui.ExpandHelper$;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusBarExpandableObserver extends AbstractObserver {
    public static volatile StatusBarExpandableObserver sInstance;
    public boolean mIsStatusBarExpandDisableMode;

    public static StatusBarExpandableObserver getInstance() {
        if (sInstance == null) {
            synchronized (StatusBarExpandableObserver.class) {
                try {
                    if (sInstance == null) {
                        StatusBarExpandableObserver statusBarExpandableObserver = new StatusBarExpandableObserver();
                        statusBarExpandableObserver.mIsStatusBarExpandDisableMode = false;
                        sInstance = statusBarExpandableObserver;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return CommonSettingsValueProxy.KEY_STATUSBAR_EXPAND_STATE;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.toSecureUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        this.mIsStatusBarExpandDisableMode = CommonSettingsValueProxy.getStatusBarExpandDisable(context) == 1;
        ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_STATUSBAR, "StatusBarExpandableObserver", new StringBuilder("onChange mIsStatusBarExpandDisableMode "), this.mIsStatusBarExpandDisableMode);
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onRegister(Context context) {
        super.onRegister(context);
        this.mIsStatusBarExpandDisableMode = CommonSettingsValueProxy.getStatusBarExpandDisable(context) == 1;
    }
}
