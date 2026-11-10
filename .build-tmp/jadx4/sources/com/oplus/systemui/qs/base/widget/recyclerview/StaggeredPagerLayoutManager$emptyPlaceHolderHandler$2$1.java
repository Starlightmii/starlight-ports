package com.oplus.systemui.qs.base.widget.recyclerview;

import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final /* synthetic */ class StaggeredPagerLayoutManager$emptyPlaceHolderHandler$2$1 extends FunctionReferenceImpl implements Function2 {
    public final Object invoke(Object obj, Object obj2) {
        return ((StaggeredPagerLayoutManager) ((CallableReference) this).receiver).getItemRect((Loc) obj, (SpanSize) obj2);
    }
}
