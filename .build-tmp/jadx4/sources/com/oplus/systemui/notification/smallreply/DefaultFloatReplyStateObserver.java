package com.oplus.systemui.notification.smallreply;

import android.content.Context;
import android.net.Uri;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DefaultFloatReplyStateObserver extends AbstractObserver {
    private static final String DEFAULT_FLOAT_WINDOW_REPLY_SETTINGS = "default_small_reply_settings";
    public int defaultFloatReplyReplyState;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new DefaultFloatReplyStateObserver$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final DefaultFloatReplyStateObserver getInstance() {
            return (DefaultFloatReplyStateObserver) DefaultFloatReplyStateObserver.instance$delegate.getValue();
        }
    }

    public final int getDefaultFloatReplyReplyState() {
        return this.defaultFloatReplyReplyState;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return DEFAULT_FLOAT_WINDOW_REPLY_SETTINGS;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toGlobalUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        super.onChange(z);
        Context context = getContext();
        if (context != null) {
            this.defaultFloatReplyReplyState = AbsSettingsValueProxy.Companion.getGlobalIntValue(context, DEFAULT_FLOAT_WINDOW_REPLY_SETTINGS, 0);
            SmallReplyObserver.Companion.getInstance().updateSmallReplyState(context);
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        this.defaultFloatReplyReplyState = AbsSettingsValueProxy.Companion.getGlobalIntValue(context, DEFAULT_FLOAT_WINDOW_REPLY_SETTINGS, 0);
        SmallReplyObserver.Companion.getInstance().updateSmallReplyState(context);
    }
}
