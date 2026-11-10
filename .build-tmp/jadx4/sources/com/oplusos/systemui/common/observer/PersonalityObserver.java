package com.oplusos.systemui.common.observer;

import android.content.Context;
import android.net.Uri;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PersonalityObserver extends AbstractObserver {
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new PersonalityObserver$$ExternalSyntheticLambda0());
    private final String mKey = CommonSettingsValueProxy.KEY_QS_PERSONALITY_SHAPE_TYPE;
    public int mShapeType = 2;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final PersonalityObserver getInstance() {
            return (PersonalityObserver) PersonalityObserver.instance$delegate.getValue();
        }
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return this.mKey;
    }

    public final int getShapeType() {
        return this.mShapeType;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSystemUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context != null) {
            this.mShapeType = CommonSettingsValueProxy.Companion.getQSShapeType(context, 2);
        }
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        this.mShapeType = CommonSettingsValueProxy.Companion.getQSShapeType(context, 2);
    }
}
