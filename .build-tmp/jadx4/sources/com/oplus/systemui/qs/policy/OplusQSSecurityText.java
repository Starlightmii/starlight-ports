package com.oplus.systemui.qs.policy;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.ObservableField;
import com.android.systemui.plugins.qs.NullableObservableField;
import com.android.systemui.plugins.qs.QSSecurity;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSecurityText extends AppCompatTextView implements View.OnClickListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSSecurityText";
    private NullableObservableField<Object> footerContentTextUpdate;
    private NullableObservableField<Object> footerIconIdUpdate;
    private QSSecurity qsSecurity;
    private NullableObservableField<Object> showSecurityUpdate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public /* synthetic */ OplusQSSecurityText(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        QSSecurity qSSecurity = this.qsSecurity;
        if (qSSecurity != null) {
            qSSecurity.showDeviceMonitoringDialog();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        NullableObservableField showSecurity;
        super/*android.widget.TextView*/.onConfigurationChanged(configuration);
        QSSecurity qSSecurity = this.qsSecurity;
        if (qSSecurity == null || (showSecurity = qSSecurity.getShowSecurity()) == null) {
            return;
        }
        showSecurity.notifyChange();
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NullableObservableField<Object> nullableObservableField = this.showSecurityUpdate;
        if (nullableObservableField != null) {
            nullableObservableField.detach();
        }
        NullableObservableField<Object> nullableObservableField2 = this.footerContentTextUpdate;
        if (nullableObservableField2 != null) {
            nullableObservableField2.detach();
        }
        NullableObservableField<Object> nullableObservableField3 = this.footerIconIdUpdate;
        if (nullableObservableField3 != null) {
            nullableObservableField3.detach();
        }
        QSSecurity qSSecurity = this.qsSecurity;
        if (qSSecurity != null) {
            qSSecurity.destroy();
        }
    }

    public final void setListening(boolean z) {
        NullableObservableField showSecurity;
        QSSecurity qSSecurity = this.qsSecurity;
        if (!z) {
            if (qSSecurity != null) {
                qSSecurity.unRegisterCallBack();
                return;
            }
            return;
        }
        if (qSSecurity != null) {
            qSSecurity.registerCallBack();
        }
        QSSecurity qSSecurity2 = this.qsSecurity;
        if (qSSecurity2 == null || (showSecurity = qSSecurity2.getShowSecurity()) == null) {
            return;
        }
        showSecurity.notifyChange();
    }

    public final void setQSSecurityInteractor(QSSecurity qSSecurity) {
        NullableObservableField footerIconId;
        NullableObservableField contentText;
        NullableObservableField showSecurity;
        this.qsSecurity = qSSecurity;
        if (qSSecurity != null && (showSecurity = qSSecurity.getShowSecurity()) != null) {
            OplusQSSecurityText$$ExternalSyntheticLambda0 oplusQSSecurityText$$ExternalSyntheticLambda0 = new OplusQSSecurityText$$ExternalSyntheticLambda0(0);
            oplusQSSecurityText$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            this.showSecurityUpdate = new NullableObservableField<>(new ObservableField[]{showSecurity}, false, oplusQSSecurityText$$ExternalSyntheticLambda0, 2, (DefaultConstructorMarker) null);
        }
        QSSecurity qSSecurity2 = this.qsSecurity;
        if (qSSecurity2 != null && (contentText = qSSecurity2.getContentText()) != null) {
            OplusQSSecurityText$$ExternalSyntheticLambda0 oplusQSSecurityText$$ExternalSyntheticLambda1 = new OplusQSSecurityText$$ExternalSyntheticLambda0(1);
            oplusQSSecurityText$$ExternalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            this.footerContentTextUpdate = new NullableObservableField<>(new ObservableField[]{contentText}, false, oplusQSSecurityText$$ExternalSyntheticLambda1, 2, (DefaultConstructorMarker) null);
        }
        QSSecurity qSSecurity3 = this.qsSecurity;
        if (qSSecurity3 == null || (footerIconId = qSSecurity3.getFooterIconId()) == null) {
            return;
        }
        OplusQSSecurityText$$ExternalSyntheticLambda0 oplusQSSecurityText$$ExternalSyntheticLambda2 = new OplusQSSecurityText$$ExternalSyntheticLambda0(2);
        oplusQSSecurityText$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.footerIconIdUpdate = new NullableObservableField<>(new ObservableField[]{footerIconId}, false, oplusQSSecurityText$$ExternalSyntheticLambda2, 2, (DefaultConstructorMarker) null);
    }

    public OplusQSSecurityText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public OplusQSSecurityText(Context context) {
        this(context, null, 0, 6, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQSSecurityText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOnClickListener(this);
    }
}
