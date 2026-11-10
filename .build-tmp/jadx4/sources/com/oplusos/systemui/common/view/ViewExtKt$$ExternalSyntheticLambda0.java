package com.oplusos.systemui.common.view;

import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ViewExtKt$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Ref.ObjectRef f$0;
    public /* synthetic */ Ref.ObjectRef f$1;
    public /* synthetic */ View f$2;

    public /* synthetic */ ViewExtKt$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                Ref.ObjectRef objectRef = this.f$0;
                Ref.ObjectRef objectRef2 = this.f$1;
                View view = this.f$2;
                objectRef.element = (FrameInfo) obj;
                ViewExtKt.startAlongWithPosition$tryUpdatePosition(objectRef, objectRef2, view);
                break;
            default:
                Ref.ObjectRef objectRef3 = this.f$0;
                Ref.ObjectRef objectRef4 = this.f$1;
                View view2 = this.f$2;
                objectRef3.element = (FrameInfo) obj;
                ViewExtKt.startAlongWithPosition$tryUpdatePosition(objectRef4, objectRef3, view2);
                break;
        }
        return Unit.INSTANCE;
    }
}
