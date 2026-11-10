package com.oplusos.keyguard;

import android.view.MotionEvent;
import android.view.View;
import com.android.keyguard.OplusLockIconView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusLockIconViewController {
    boolean dragDownToLock(MotionEvent motionEvent, boolean z, float f);

    float getBottom();

    int getLockHeight();

    float getTop();

    View getView();

    void setAlpha(float f);

    void setBouncerHideAmount(float f);

    void setLockIconView(OplusLockIconView oplusLockIconView);

    boolean willHandleTouchWhileDozing(MotionEvent motionEvent);
}
