package com.oplus.systemui.statusbar.notification.row.menupanel;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import com.coui.appcompat.animation.COUIEaseInterpolator;
import com.oplusos.systemui.common.blurability.motion.BlurUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuPanelView extends FrameLayout {
    private static final String TAG = "NotificationMenuPanelView";
    private final View backgroundView;
    public static final Companion Companion = new Companion();
    private static final COUIEaseInterpolator interpolator = new COUIEaseInterpolator();

    public NotificationMenuPanelView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        View view = new View(context);
        view.setAlpha(0.0f);
        addView(view, new FrameLayout.LayoutParams(-1, -1));
        this.backgroundView = view;
    }

    public final void dismiss(BlurUtils blurUtils, int i, Function1 function1) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationMenuPanelView-->dismiss");
        }
        ViewPropertyAnimator duration = this.backgroundView.animate().setInterpolator(interpolator).alpha(0.0f).setDuration(250L);
        show.2 r1 = new show.2(1);
        r1.$blurUtils = blurUtils;
        r1.this$0 = this;
        r1.$backgroundColor = i;
        VarHandle.storeStoreFence();
        ViewPropertyAnimator updateListener = duration.setUpdateListener(r1);
        dismiss.3 r5 = new dismiss.3();
        r5.$onEnd = function1;
        r5.this$0 = this;
        VarHandle.storeStoreFence();
        updateListener.withEndAction(r5).start();
    }

    public final void show(int i, BlurUtils blurUtils, Function1 function1) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationMenuPanelView-->show");
        }
        this.backgroundView.setBackgroundColor(i);
        ViewPropertyAnimator duration = this.backgroundView.animate().setInterpolator(interpolator).alpha(1.0f).setDuration(400L);
        show.2 r1 = new show.2(0);
        r1.$blurUtils = blurUtils;
        r1.this$0 = this;
        r1.$backgroundColor = i;
        VarHandle.storeStoreFence();
        ViewPropertyAnimator updateListener = duration.setUpdateListener(r1);
        show.3 r5 = new show.3();
        r5.$onEnd = function1;
        r5.this$0 = this;
        VarHandle.storeStoreFence();
        updateListener.setListener(r5).start();
    }

    public NotificationMenuPanelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 12, 0);
    }

    public NotificationMenuPanelView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 8, 0);
    }

    public /* synthetic */ NotificationMenuPanelView(Context context, AttributeSet attributeSet, int i, int i2, int i3) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i, 0);
    }

    public NotificationMenuPanelView(Context context) {
        this(context, null, 0, 14, 0);
    }
}
