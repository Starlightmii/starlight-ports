package com.oplus.systemui.statusbar.observer;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LauncherAnimSpeedObserver extends AbstractObserver {
    public static final Companion Companion = new Companion();
    private static final Lazy INSTANCE$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new LauncherAnimSpeedObserver$$ExternalSyntheticLambda0());
    public static final String SETTINGS_LAUNCHER_ANIM_SPEED = "setting_app_startup_anim_speed";
    private static final String TAG = "LauncherAnimSpeedObserver";
    public boolean isLauncherAnimEnhanceMode;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        private final LauncherAnimSpeedObserver getINSTANCE() {
            return (LauncherAnimSpeedObserver) LauncherAnimSpeedObserver.INSTANCE$delegate.getValue();
        }

        public final LauncherAnimSpeedObserver getInstance() {
            return getINSTANCE();
        }
    }

    public static final LauncherAnimSpeedObserver getInstance() {
        return Companion.getInstance();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return SETTINGS_LAUNCHER_ANIM_SPEED;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSecureUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        super.onChange(z);
        updateValue();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        updateValue();
    }

    public final void updateValue() {
        ContentResolver contentResolver;
        Context context = getContext();
        int i = -1;
        if (context != null && (contentResolver = context.getContentResolver()) != null) {
            i = Settings.Secure.getInt(contentResolver, SETTINGS_LAUNCHER_ANIM_SPEED, -1);
            this.isLauncherAnimEnhanceMode = i == 0;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", MobileInputLogger$.ExternalSyntheticOutline0.m("updateValue settings: ", i, ", isLauncherAnimEnhanceMode: ", "LauncherAnimSpeedObserver-->", this.isLauncherAnimEnhanceMode));
        }
    }
}
