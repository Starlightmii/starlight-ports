package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.algsession;

import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import java.util.List;
import java.util.Stack;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IAlgSession {
    IStepStatus first();

    List<Stack<IStepStatus>> getStackList();

    IStepStatus last();

    void push(IStepStatus iStepStatus);

    void startDrag(List<TileConfig> list, int i);
}
