package com.oplus.systemui.qs.observer;

import android.content.Context;
import android.net.Uri;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.util.QsSettingsValueProxy;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PhoneAntiTheftLocationObserver extends AbstractObserver {
    private static final String TAG = "PhoneAntiTheftLocationObserver";
    public volatile boolean mIsPhoneAntiTheftLocation;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new PhoneAntiTheftLocationObserver$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final PhoneAntiTheftLocationObserver getInstance() {
            return (PhoneAntiTheftLocationObserver) PhoneAntiTheftLocationObserver.instance$delegate.getValue();
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return "phone_anti_theft_location_key";
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toGlobalUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        this.mIsPhoneAntiTheftLocation = QsSettingsValueProxy.Companion.isPhoneAntiTheftLocationOn(context);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("PhoneAntiTheftLocationObserver mIsPhoneAntiTheftLocation =", this.mIsPhoneAntiTheftLocation), null, 8);
        super.onChange(z);
    }
}
