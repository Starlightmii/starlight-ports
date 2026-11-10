package com.oplus.systemui.notification.clearall;

import androidx.appcompat.widget.AppCompatImageView;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ClearAllController$$ExternalSyntheticLambda1 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusClearAllButton f$0;

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
    public final Object invoke(Object obj) {
        int i = this.$r8$classId;
        AppCompatImageView appCompatImageView = this.f$0;
        switch (i) {
            case 0:
                return new Pair(Float.valueOf(appCompatImageView.getAlpha()), Float.valueOf(appCompatImageView.getScaleX()));
            case 1:
                appCompatImageView.setAlpha(((Float) obj).floatValue());
                return Unit.INSTANCE;
            case 2:
                float fFloatValue = ((Float) obj).floatValue();
                appCompatImageView.setScaleX(fFloatValue);
                appCompatImageView.setScaleY(fFloatValue);
                return Unit.INSTANCE;
            case 3:
                return new Pair(Float.valueOf(appCompatImageView.getAlpha()), Float.valueOf(appCompatImageView.getScaleX()));
            case 4:
                appCompatImageView.setAlpha(((Float) obj).floatValue());
                return Unit.INSTANCE;
            default:
                float fFloatValue2 = ((Float) obj).floatValue();
                appCompatImageView.setScaleX(fFloatValue2);
                appCompatImageView.setScaleY(fFloatValue2);
                return Unit.INSTANCE;
        }
    }
}
