package com.oplus.systemui.statusbar.seeding;

import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import com.android.systemui.util.HeadsUpToZoomUtils;
import com.oplusos.systemui.common.observer.AbstractObserver;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OneHandedObserver extends AbstractObserver {
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(new OneHandedObserver$$ExternalSyntheticLambda0());
    public boolean oneHandedEnable;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static /* synthetic */ void getInstance$annotations() {
        }

        public final OneHandedObserver getInstance() {
            return (OneHandedObserver) OneHandedObserver.instance$delegate.getValue();
        }
    }

    public static final OneHandedObserver getInstance() {
        return Companion.getInstance();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return "one_handed_mode_activated";
    }

    public final boolean getOneHandedEnable() {
        return this.oneHandedEnable;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSecureUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        boolean z2 = Settings.Secure.getIntForUser(context.getContentResolver(), "one_handed_mode_activated", 0, -2) == 1;
        this.oneHandedEnable = z2;
        HeadsUpToZoomUtils.INSTANCE.setOneHandedEnable(z2);
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        boolean z = Settings.Secure.getIntForUser(context.getContentResolver(), "one_handed_mode_activated", 0, -2) == 1;
        this.oneHandedEnable = z;
        HeadsUpToZoomUtils.INSTANCE.setOneHandedEnable(z);
    }
}
