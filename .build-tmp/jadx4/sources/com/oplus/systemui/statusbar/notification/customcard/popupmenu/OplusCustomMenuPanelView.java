package com.oplus.systemui.statusbar.notification.customcard.popupmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import com.android.keyguard.OplusLockIconView$;
import com.coui.appcompat.animation.COUIEaseInterpolator;
import com.oplusos.systemui.common.blurability.motion.BlurUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomMenuPanelView extends FrameLayout {
    private static final String TAG = "OplusCustomMenuPanelView";
    private final View backgroundView;
    public static final Companion Companion = new Companion();
    private static final COUIEaseInterpolator interpolator = new COUIEaseInterpolator();

    public OplusCustomMenuPanelView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        View view = new View(context);
        view.setAlpha(0.0f);
        addView(view, new FrameLayout.LayoutParams(-1, -1));
        this.backgroundView = view;
    }

    public final void dismiss(BlurUtils blurUtils, int i, Function1 function1, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("dismiss, isOnStatusBar: ", "OplusCustomMenuPanelView-->", "SystemUi--Notification", z);
        }
        ViewPropertyAnimator duration = this.backgroundView.animate().setInterpolator(interpolator).alpha(0.0f).setDuration(z ? 200L : 250L);
        show.2 r0 = new show.2(1);
        r0.$blurUtils = blurUtils;
        r0.this$0 = this;
        r0.$backgroundColor = i;
        VarHandle.storeStoreFence();
        ViewPropertyAnimator updateListener = duration.setUpdateListener(r0);
        dismiss.3 r5 = new dismiss.3();
        r5.$onEnd = function1;
        r5.this$0 = this;
        VarHandle.storeStoreFence();
        updateListener.withEndAction(r5).start();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public final void show(BlurUtils blurUtils, int i, Function1 function1) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusCustomMenuPanelView-->show");
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
}
