package com.oplus.vfxsdk.common.api;

import com.oplus.vfxsdk.common.AnimEndListener;
import com.oplus.vfxsdk.common.Animator;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IPlayAnimator {
    void addAnimEndListener(String str, AnimEndListener animEndListener);

    List<String> getAnimKeys();

    boolean isAnimPause(String str);

    boolean isAnimPlay(String str);

    void pauseAnim(String str);

    void playAnim(String str);

    void playAnimTo(String str, float f);

    void removeAnimEndListener(String str, AnimEndListener animEndListener);

    void restartAnim(String str);

    void seekAnimNext(String str);

    void seekAnimTo(String str, double d);

    void seekAnimToEnd(String str);

    void setAnimEndListener(String str, Function0 function0);

    void setAnimMode(String str, Animator.AnimMode animMode);

    void setAnimUpdateListener(String str, Function1 function1);

    void stopAnim(String str);
}
