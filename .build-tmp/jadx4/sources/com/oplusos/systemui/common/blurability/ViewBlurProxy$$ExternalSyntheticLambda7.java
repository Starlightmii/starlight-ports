package com.oplusos.systemui.common.blurability;

import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import com.oplusos.systemui.common.blurability.platformblur.PlatformBlurDrawable;
import com.oplusos.systemui.common.blurability.wallpaper.BlendWallpaperBlurDrawable;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ViewBlurProxy$$ExternalSyntheticLambda7 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ ViewBlurProxy f$0;

    public /* synthetic */ ViewBlurProxy$$ExternalSyntheticLambda7(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj) {
        int i = this.$r8$classId;
        ViewBlurProxy viewBlurProxy = this.f$0;
        switch (i) {
            case 0:
                viewBlurProxy.setBlurAmount(((Float) obj).floatValue());
                return Unit.INSTANCE;
            case 1:
                return ViewBlurProxy.m607$r8$lambda$8t_XuFii7VSfJ9jUUqbRHiRid0(viewBlurProxy, (PlatformBlurDrawable) obj);
            case 2:
                return ViewBlurProxy.$r8$lambda$CVSfGTb0Z8Id5x6lk4e0U1OeOGM(viewBlurProxy, (BackgroundBlurDrawable) obj);
            default:
                return ViewBlurProxy.$r8$lambda$f4VnhX1oSYzlYSd2QzggJs25mNg(viewBlurProxy, (BlendWallpaperBlurDrawable) obj);
        }
    }
}
