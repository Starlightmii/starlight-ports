package com.oplus.vfxsdk.common.parameter;

import android.animation.ObjectAnimator;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IParameter {
    IParameter animate(Object obj);

    Object copyValue();

    ObjectAnimator createAnimator(Object obj);

    ObjectAnimator getAnimator();

    Object getValue();

    IParameter reset();

    void setAnimEndListener(Function0 function0);

    void setAnimStartListener(Function0 function0);

    void setValue(Object obj);
}
