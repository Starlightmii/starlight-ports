package com.oplus.systemui.statusbar.observer;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import com.oplusos.systemui.common.observer.AbstractObserver;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DualSimStateObserver extends AbstractObserver {
    public static final String DDS_SWITCH_FEATURE = "UI_DDS_SWITCH_FEATURE";
    public int mDualSimState;
    public static final Companion Companion = new Companion();
    private static final Lazy INSTANCE$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new DualSimStateObserver$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        private final DualSimStateObserver getINSTANCE() {
            return (DualSimStateObserver) DualSimStateObserver.INSTANCE$delegate.getValue();
        }

        public final DualSimStateObserver getInstance() {
            return getINSTANCE();
        }
    }

    public static final DualSimStateObserver getInstance() {
        return Companion.getInstance();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return DDS_SWITCH_FEATURE;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSystemUri();
    }

    public final boolean isDualSimOpen() {
        return this.mDualSimState == 1;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        ContentResolver contentResolver;
        Context context = getContext();
        if (context != null && (contentResolver = context.getContentResolver()) != null) {
            this.mDualSimState = Settings.System.getIntForUser(contentResolver, DDS_SWITCH_FEATURE, 0, 0);
        }
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        ContentResolver contentResolver;
        super.onRegister(context);
        Context context2 = getContext();
        if (context2 == null || (contentResolver = context2.getContentResolver()) == null) {
            return;
        }
        this.mDualSimState = Settings.System.getIntForUser(contentResolver, DDS_SWITCH_FEATURE, 0, 0);
    }
}
