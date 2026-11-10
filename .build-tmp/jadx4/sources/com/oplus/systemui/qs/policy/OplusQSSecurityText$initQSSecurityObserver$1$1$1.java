package com.oplus.systemui.qs.policy;

import androidx.appcompat.widget.AppCompatTextView;
import com.android.systemui.plugins.qs.NullableObservableField;
import com.android.systemui.plugins.qs.QSSecurity;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplus.systemui.qs.pipeline.data.repository.WellBeingAssistantRepositoryImpl;
import com.oplusos.systemui.common.utils.DeviceUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSecurityText$initQSSecurityObserver$1$1$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSSecurityText this$0;

    public /* synthetic */ OplusQSSecurityText$initQSSecurityObserver$1$1$1(int i) {
        this.$r8$classId = i;
    }

    /* JADX WARN: Code duplicated, block: B:41:0x0092  */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.widget.TextView, com.oplus.systemui.qs.policy.OplusQSSecurityText] */
    /* JADX WARN: Type inference failed for: r4v13, types: [android.widget.TextView, com.oplus.systemui.qs.policy.OplusQSSecurityText] */
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
        int i;
        NullableObservableField showSecurity;
        NullableObservableField contentText;
        NullableObservableField footerIconId;
        Integer num;
        switch (this.$r8$classId) {
            case 0:
                ?? r0 = this.this$0;
                QSSecurity qSSecurity = ((OplusQSSecurityText) r0).qsSecurity;
                if ((qSSecurity == null || (showSecurity = qSSecurity.getShowSecurity()) == null) ? false : Intrinsics.areEqual(showSecurity.get(), Boolean.TRUE)) {
                    AppCompatTextView appCompatTextView = this.this$0;
                    boolean zIsSuperPowerSaveModel = DeviceUtil.isLandscape(appCompatTextView.getContext()) && !DeviceUtil.shouldUseLargeFoldConfig(appCompatTextView.getResources());
                    if (!zIsSuperPowerSaveModel) {
                        SuperPowerSaveRepositoryImpl.Companion.getClass();
                        zIsSuperPowerSaveModel = SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel();
                    }
                    if (!zIsSuperPowerSaveModel) {
                        WellBeingAssistantRepositoryImpl.Companion.getClass();
                        zIsSuperPowerSaveModel = WellBeingAssistantRepositoryImpl.isWellBeingAssistantState;
                    }
                    i = zIsSuperPowerSaveModel ? 8 : 0;
                }
                r0.setVisibility(i);
                break;
            case 1:
                ?? r4 = this.this$0;
                QSSecurity qSSecurity2 = ((OplusQSSecurityText) r4).qsSecurity;
                r4.setText((qSSecurity2 == null || (contentText = qSSecurity2.getContentText()) == null) ? null : (CharSequence) contentText.get());
                break;
            default:
                QSSecurity qSSecurity3 = this.this$0.qsSecurity;
                if (qSSecurity3 != null && (footerIconId = qSSecurity3.getFooterIconId()) != null && (num = (Integer) footerIconId.get()) != null) {
                    AppCompatTextView appCompatTextView2 = this.this$0;
                    int iIntValue = num.intValue();
                    if (iIntValue != 0) {
                        appCompatTextView2.setCompoundDrawablePadding(5);
                        appCompatTextView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, iIntValue, 0);
                    }
                    break;
                }
                break;
        }
    }
}
