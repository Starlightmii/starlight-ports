package com.oplus.systemui.qs.base.springtransition;

import java.util.List;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ITransitionDesc {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String MAIN_TRANSITION = "进入退出编辑态-默认动画";

    List<Pair<Object, Class<?>>> collectProperties(String str, List<Pair<Object, Class<?>>> list);
}
