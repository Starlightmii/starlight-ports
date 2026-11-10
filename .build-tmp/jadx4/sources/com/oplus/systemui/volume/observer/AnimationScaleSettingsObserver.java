package com.oplus.systemui.volume.observer;

import android.content.Context;
import android.net.Uri;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.oplus.wrapper.os.UserHandle;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AnimationScaleSettingsObserver extends AbstractObserver {
    public static final String TAG = "OplusVolume-Anim";
    public volatile float animationScale;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new AnimationScaleSettingsObserver$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final AnimationScaleSettingsObserver getInstance() {
            return (AnimationScaleSettingsObserver) AnimationScaleSettingsObserver.instance$delegate.getValue();
        }
    }

    public final float getAnimationScale() {
        return this.animationScale;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return VolumeSettingsValueProxy.SETTINGS_ANIMATION_SCALE;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSystemUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Context context = getContext();
        if (context != null) {
            this.animationScale = VolumeSettingsValueProxy.Companion.getAnimationScaleSettingsState(context);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("onChange animationScale = ", this.animationScale, "OplusVolume-Anim-->", "SystemUi--Volume");
            }
        }
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        this.animationScale = VolumeSettingsValueProxy.Companion.getAnimationScaleSettingsState(context);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("onRegister animationScale = ", this.animationScale, "OplusVolume-Anim-->", "SystemUi--Volume");
        }
    }

    public final void registerObserver(Context context) {
        register(context, UserHandle.USER_ALL, false);
    }
}
