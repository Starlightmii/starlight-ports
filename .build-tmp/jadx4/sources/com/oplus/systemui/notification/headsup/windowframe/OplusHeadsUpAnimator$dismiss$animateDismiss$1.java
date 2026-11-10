package com.oplus.systemui.notification.headsup.windowframe;

import android.util.Log;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.util.JankUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHeadsUpAnimator$dismiss$animateDismiss$1 implements Runnable {
    public /* synthetic */ Function1 $onEnd;
    public /* synthetic */ OplusHeadsUpAnimator this$0;

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
        if (this.this$0.control.getView() == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "HeadsUpAnimator-->dismiss: control.view == null");
            }
            Function1 function1 = this.$onEnd;
            if (function1 != null) {
                function1.invoke((Object) null);
                return;
            }
            return;
        }
        JankUtils.gfxSceneBegin(this.this$0.context, 42, "BANNER_NF_DISAPPEAR", 0L, 4L, true);
        OplusHeadsUpAnimator oplusHeadsUpAnimator = this.this$0;
        ExpandableNotificationRow view = oplusHeadsUpAnimator.control.getView();
        float fMin = view instanceof ExpandableNotificationRow ? Math.min(-view.getActualHeight(), -430.0f) : -430.0f;
        OplusHeadsUpAnimator oplusHeadsUpAnimator2 = this.this$0;
        Function1 function2 = this.$onEnd;
        OplusHeadsUpAnimator$$ExternalSyntheticLambda3 oplusHeadsUpAnimator$$ExternalSyntheticLambda3 = new OplusHeadsUpAnimator$$ExternalSyntheticLambda3(1);
        oplusHeadsUpAnimator$$ExternalSyntheticLambda3.f$1 = oplusHeadsUpAnimator2;
        oplusHeadsUpAnimator$$ExternalSyntheticLambda3.f$0 = function2;
        VarHandle.storeStoreFence();
        oplusHeadsUpAnimator.animateTo(fMin, oplusHeadsUpAnimator$$ExternalSyntheticLambda3);
    }
}
