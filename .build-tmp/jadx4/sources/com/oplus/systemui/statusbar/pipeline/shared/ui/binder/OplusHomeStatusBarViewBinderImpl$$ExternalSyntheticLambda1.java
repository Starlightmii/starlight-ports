package com.oplus.systemui.statusbar.pipeline.shared.ui.binder;

import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusHomeStatusBarViewBinderImpl$$ExternalSyntheticLambda1 implements Function1 {
    public /* synthetic */ Ref.ObjectRef f$0;

    public final Object invoke(Object obj) {
        Ref.ObjectRef objectRef = this.f$0;
        ((View) objectRef.element).setTranslationX(((Float) obj).floatValue());
        return Unit.INSTANCE;
    }
}
