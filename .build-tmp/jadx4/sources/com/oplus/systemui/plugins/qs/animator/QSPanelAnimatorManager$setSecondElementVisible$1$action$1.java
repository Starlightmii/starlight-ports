package com.oplus.systemui.plugins.qs.animator;

import android.view.View;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSPanelAnimatorManager$setSecondElementVisible$1$action$1 implements Runnable {
    public /* synthetic */ IQSExpandOrCollapseAnimation $component;
    public /* synthetic */ QSElementAnimator $elementAnimator;
    public /* synthetic */ View $it;
    public /* synthetic */ int $nodeIndex;
    public /* synthetic */ float $responseAlpha;
    public /* synthetic */ int $subIndex;
    public /* synthetic */ boolean $visible;
    public /* synthetic */ boolean $withAnimation;

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // java.lang.Runnable
    public final void run() {
        QSElementAnimator qSElementAnimator = this.$elementAnimator;
        boolean z = this.$visible;
        boolean z2 = this.$withAnimation;
        float f = this.$responseAlpha;
        IQSExpandOrCollapseAnimation iQSExpandOrCollapseAnimation = this.$component;
        View view = this.$it;
        int i = this.$subIndex;
        int i2 = this.$nodeIndex;
        QSPanelAnimatorManager$$ExternalSyntheticLambda7 qSPanelAnimatorManager$$ExternalSyntheticLambda7 = new QSPanelAnimatorManager$$ExternalSyntheticLambda7(2);
        qSPanelAnimatorManager$$ExternalSyntheticLambda7.f$0 = iQSExpandOrCollapseAnimation;
        qSPanelAnimatorManager$$ExternalSyntheticLambda7.f$1 = view;
        qSPanelAnimatorManager$$ExternalSyntheticLambda7.f$2 = i;
        qSPanelAnimatorManager$$ExternalSyntheticLambda7.f$3 = i2;
        VarHandle.storeStoreFence();
        qSElementAnimator.alphaTo(z, z2, f, 0.0f, qSPanelAnimatorManager$$ExternalSyntheticLambda7);
    }
}
