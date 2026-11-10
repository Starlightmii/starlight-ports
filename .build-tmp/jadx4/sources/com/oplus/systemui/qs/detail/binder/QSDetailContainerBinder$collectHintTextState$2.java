package com.oplus.systemui.qs.detail.binder;

import android.widget.TextView;
import com.android.systemui.FontSizeUtils;
import com.oplus.systemui.qs.base.util.QsFontUtil;
import com.oplus.systemui.qs.detail.viewmodel.QSDetailContainerViewModel;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailContainerBinder$collectHintTextState$2 implements FlowCollector {
    public /* synthetic */ TextView $hintTextView;
    public /* synthetic */ QSDetailContainerViewModel $viewModel;

    public final Object emit(Object obj, Continuation continuation) {
        boolean zHasHint = this.$viewModel.hasHint();
        TextView textView = this.$hintTextView;
        if (zHasHint) {
            textView.setVisibility(0);
            QsFontUtil.setTextFontVariation(this.$hintTextView);
            FontSizeUtils.updateFontSize(this.$hintTextView, 2131171659);
        } else {
            textView.setVisibility(8);
        }
        return Unit.INSTANCE;
    }
}
