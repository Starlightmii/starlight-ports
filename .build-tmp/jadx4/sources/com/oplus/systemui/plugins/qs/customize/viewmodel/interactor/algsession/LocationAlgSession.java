package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.algsession;

import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LocationAlgSession implements IAlgSession {
    private final Stack<IStepStatus> stepStack = new Stack<>();
    private final List<Stack<IStepStatus>> stepStackList = new ArrayList();

    public final void clearStackList() {
        this.stepStackList.clear();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.algsession.IAlgSession
    public IStepStatus first() {
        if (this.stepStack.isEmpty()) {
            return null;
        }
        return this.stepStack.firstElement();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.algsession.IAlgSession
    public List<Stack<IStepStatus>> getStackList() {
        return this.stepStackList;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.algsession.IAlgSession
    public IStepStatus last() {
        if (this.stepStack.isEmpty()) {
            return null;
        }
        return this.stepStack.lastElement();
    }

    public final void pop() {
        this.stepStack.pop();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.algsession.IAlgSession
    public void push(IStepStatus iStepStatus) {
        this.stepStack.push(iStepStatus);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.algsession.IAlgSession
    public void startDrag(List<TileConfig> list, int i) {
        push(new StepStatus(list, i));
    }

    public final void stopDrag() {
        Stack<IStepStatus> stack = new Stack<>();
        stack.addAll(this.stepStack);
        this.stepStackList.add(stack);
        this.stepStack.clear();
    }
}
