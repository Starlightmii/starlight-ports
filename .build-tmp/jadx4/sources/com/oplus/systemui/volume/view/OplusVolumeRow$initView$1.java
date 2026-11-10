package com.oplus.systemui.volume.view;

import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.oplus.anim.EffectiveAnimationListener;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeRow$initView$1 implements EffectiveAnimationListener {
    public static final OplusVolumeRow$initView$1 INSTANCE = new OplusVolumeRow$initView$1();

    public final void onResult(Object obj) {
        GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "EffectiveAnimationView setAnimation failed: ", ((Throwable) obj).getMessage(), LogUtil.TAG_VOLUME, OplusVolumeRow.TAG);
    }
}
