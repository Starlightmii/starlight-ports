package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ISepTileConfigCtrlA {
    Object addTileWithLoc(String str, int i, Loc loc, SpanSize spanSize, boolean z, Continuation continuation);

    Object changeGridInfo(int i, int i2, Continuation continuation);

    Object clipSize(String str, SpanSize spanSize, Continuation continuation);

    Object deform(int i, SpanSize spanSize, boolean z, Continuation continuation);

    Object dragItem(int i, Loc loc, boolean z, Continuation continuation);

    Object removeEmptyPage(Continuation continuation);
}
