package com.oplus.systemui.qs.base.res.model;

import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface QSDrawableBuilder {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Empty implements QSDrawableBuilder {
        public static final Empty INSTANCE = new Empty();

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableBuilder
        public /* bridge */ /* synthetic */ Drawable build(View view, boolean z, Integer num) {
            return super.build(view, z, num);
        }
    }

    static /* synthetic */ Drawable build$default(QSDrawableBuilder qSDrawableBuilder, View view, boolean z, int i) {
        if ((i & 2) != 0) {
            z = true;
        }
        return qSDrawableBuilder.build(view, z, null);
    }

    default Drawable build(View view, boolean z, Integer num) {
        return null;
    }
}
