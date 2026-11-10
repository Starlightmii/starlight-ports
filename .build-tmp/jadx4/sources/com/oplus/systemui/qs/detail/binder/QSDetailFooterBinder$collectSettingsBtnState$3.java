package com.oplus.systemui.qs.detail.binder;

import android.content.res.ColorStateList;
import android.view.View;
import android.widget.TextView;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailFooterBinder$collectSettingsBtnState$3 implements FlowCollector {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object $settingsButton;

    public final Object emit(Object obj, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                ((TextView) this.$settingsButton).setTextColor((ColorStateList) obj);
                break;
            case 1:
                ((View) this.$settingsButton).setVisibility(((Boolean) obj).booleanValue() ? 0 : 8);
                break;
            default:
                ((View) this.$settingsButton).setBackgroundColor(((Number) obj).intValue());
                break;
        }
        return Unit.INSTANCE;
    }
}
