package com.oplus.systemui.plugins.qs;

import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSRootViewComponent$$ExternalSyntheticLambda8 implements Function1 {
    public /* synthetic */ OplusQSRootViewComponent f$0;

    /* JADX WARN: Code duplicated, block: B:12:0x0029  */
    public final Object invoke(Object obj) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.f$0;
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean zLongClickBlock = true;
        if (oplusQSRootViewComponent.isAddingMode) {
            zLongClickBlock = oplusQSRootViewComponent.exitAddingMode(true);
        } else if (oplusQSRootViewComponent.isEditMode) {
            zLongClickBlock = false;
        } else if (zBooleanValue) {
            zLongClickBlock = oplusQSRootViewComponent.longClickBlock();
        } else if (oplusQSRootViewComponent.isInPageIndicatorArea) {
            zLongClickBlock = false;
        } else {
            oplusQSRootViewComponent.collapseQSPanel(false, "onEmptySpaceClick");
        }
        return Boolean.valueOf(zLongClickBlock);
    }
}
