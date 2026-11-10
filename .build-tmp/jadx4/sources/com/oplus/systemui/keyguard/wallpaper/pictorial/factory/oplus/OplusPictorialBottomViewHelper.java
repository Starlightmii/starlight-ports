package com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMessageView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBottomViewHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPictorialBottomViewHelper implements PictorialBottomViewHelper {
    public boolean mIsVerticalLayout;

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBottomViewHelper
    public final PictorialMessageView createPictorialMessageView(Context context) {
        this.mIsVerticalLayout = context.getResources().getBoolean(2131034200);
        return (PictorialMessageView) LayoutInflater.from(context).inflate(this.mIsVerticalLayout ? 2131559298 : 2131558946, (ViewGroup) null);
    }
}
