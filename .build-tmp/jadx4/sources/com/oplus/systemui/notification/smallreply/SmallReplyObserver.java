package com.oplus.systemui.notification.smallreply;

import android.content.Context;
import android.net.Uri;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SmallReplyObserver extends AbstractObserver {
    private static final String SMALL_REPLY_SETTINGS = "small_reply_settings";
    private static final String TAG = "SmallReplyObserver";
    public boolean smallReplyEnable;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new SmallReplyObserver$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final SmallReplyObserver getInstance() {
            return (SmallReplyObserver) SmallReplyObserver.instance$delegate.getValue();
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return SMALL_REPLY_SETTINGS;
    }

    public final boolean getSmallReplyEnable() {
        return this.smallReplyEnable;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSystemUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        super.onChange(z);
        Context context = getContext();
        if (context != null) {
            updateSmallReplyState(context);
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        updateSmallReplyState(context);
    }

    public final void updateSmallReplyState(Context context) {
        boolean z = AbsSettingsValueProxy.Companion.getSystemIntValue(context, SMALL_REPLY_SETTINGS, DefaultFloatReplyStateObserver.Companion.getInstance().getDefaultFloatReplyReplyState(), -2) == 1;
        this.smallReplyEnable = z;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("updateSmallReplyState smallReplyEnable=", z), null, 8);
    }
}
