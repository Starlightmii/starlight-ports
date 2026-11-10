package com.oplus.systemui.statusbar;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import com.android.systemui.qs.OplusQsColorManagerEx;
import com.android.systemui.statusbar.EmptyShadeViewEx;
import com.android.systemui.statusbar.notification.emptyshade.ui.view.EmptyShadeView;
import com.oplus.systemui.blur.OplusQsColorManager;
import com.oplus.systemui.notification.utlils.NotificationColorUtil;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EmptyShadeViewExImp extends EmptyShadeViewEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "EmptyShadeViewExImp";
    private final Context context;
    private WeakReference<EmptyShadeView> mEmptyShadeView;
    private final Lazy oplusQsColorManager;
    private final OplusQsColorManagerEx.QsColorStateObserver qsColorStateObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public EmptyShadeViewExImp(Lazy lazy, Context context) {
        this.oplusQsColorManager = lazy;
        this.context = context;
        EmptyShadeViewExImp$qsColorStateObserver$1 emptyShadeViewExImp$qsColorStateObserver$1 = new EmptyShadeViewExImp$qsColorStateObserver$1();
        emptyShadeViewExImp$qsColorStateObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.qsColorStateObserver = emptyShadeViewExImp$qsColorStateObserver$1;
    }

    public void bindEmptyShadeView(EmptyShadeView emptyShadeView) {
        super.bindEmptyShadeView(emptyShadeView);
        this.mEmptyShadeView = new WeakReference<>(emptyShadeView);
    }

    public final void onAttachedToWindow() {
        OplusQsColorManager oplusQsColorManager;
        Lazy lazy = this.oplusQsColorManager;
        if (lazy == null || (oplusQsColorManager = (OplusQsColorManager) lazy.get()) == null) {
            return;
        }
        oplusQsColorManager.addQsColorStateObserver(this.qsColorStateObserver);
    }

    public final void onDetachedFromWindow() {
        OplusQsColorManager oplusQsColorManager;
        Lazy lazy = this.oplusQsColorManager;
        if (lazy == null || (oplusQsColorManager = (OplusQsColorManager) lazy.get()) == null) {
            return;
        }
        oplusQsColorManager.removeQsColorStateObserver(this.qsColorStateObserver);
    }

    public void setEmptyTextColor(TextView textView) {
        int qSDateTimeColor = NotificationColorUtil.getQSDateTimeColor(this.context);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "EmptyShadeViewExImp-->setEmptyTextColor");
        }
        textView.setTextColor(qSDateTimeColor);
    }
}
