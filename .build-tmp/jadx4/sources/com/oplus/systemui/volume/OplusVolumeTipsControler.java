package com.oplus.systemui.volume;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeTipsControler {
    public static final Companion Companion = new Companion();
    public static final String PROPERTY_NAME_ALPHA = "alpha";
    private static final String TAG = "OplusVolumeTipsControler";
    public float alphaValue;
    private final Handler handler;
    private final Lazy hideRunnable$delegate;
    private CharSequence longTip;
    private CharSequence shortTip;
    private Tip showingTip;
    private ObjectAnimator tipAnim;
    private State tipState;
    private TextView volumePanelTipsView;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class State {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ State[] $VALUES;
        public static final State HIDEN;
        public static final State HIDING;
        public static final State SHOWED;
        public static final State SHOWING;

        static {
            State state = new State("SHOWING", 0);
            SHOWING = state;
            State state2 = new State("SHOWED", 1);
            SHOWED = state2;
            State state3 = new State("HIDING", 2);
            HIDING = state3;
            State state4 = new State("HIDEN", 3);
            HIDEN = state4;
            State[] stateArr = {state, state2, state3, state4};
            $VALUES = stateArr;
            $ENTRIES = EnumEntriesKt.enumEntries(stateArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static State valueOf(String str) {
            return (State) Enum.valueOf(State.class, str);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Tip {
        public static final Companion Companion = new Companion();
        private CharSequence text;
        private Integer type;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
        }

        public final void addTip(CharSequence charSequence, int i) {
            this.text = charSequence;
            this.type = Integer.valueOf(i);
        }

        public final CharSequence getText() {
            return this.text;
        }

        public final Integer getType() {
            return this.type;
        }

        public final void removeTip() {
            this.text = null;
            this.type = null;
        }

        public final void setText(CharSequence charSequence) {
            this.text = charSequence;
        }

        public final void setType(Integer num) {
            this.type = num;
        }
    }

    public OplusVolumeTipsControler(TextView textView) {
        this.volumePanelTipsView = textView;
        textView.setAlpha(0.0f);
        this.handler = new Handler(Looper.getMainLooper());
        this.tipState = State.HIDEN;
        this.showingTip = new Tip();
        OplusVolumeTipsControler$$ExternalSyntheticLambda0 oplusVolumeTipsControler$$ExternalSyntheticLambda0 = new OplusVolumeTipsControler$$ExternalSyntheticLambda0();
        oplusVolumeTipsControler$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.hideRunnable$delegate = LazyKt__LazyJVMKt.lazy(oplusVolumeTipsControler$$ExternalSyntheticLambda0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Runnable getHideRunnable() {
        return (Runnable) this.hideRunnable$delegate.getValue();
    }

    public final void clearTip() {
        this.volumePanelTipsView.setVisibility(8);
        this.volumePanelTipsView.setText("");
        this.showingTip.removeTip();
        this.tipState = State.HIDEN;
        this.longTip = null;
        this.shortTip = null;
        this.alphaValue = 0.0f;
    }

    public final Handler getHandler() {
        return this.handler;
    }

    public final State getTipState() {
        return this.tipState;
    }

    public final void playHideAnim(boolean z) {
        TextView textView = this.volumePanelTipsView;
        float f = this.alphaValue;
        long j = z ? 100L : 200L;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, PROPERTY_NAME_ALPHA, f, 0.0f);
        objectAnimatorOfFloat.setStartDelay(0L);
        objectAnimatorOfFloat.setDuration((long) (j * f));
        createAnim.1 r0 = new createAnim.1();
        r0.this$0 = this;
        VarHandle.storeStoreFence();
        objectAnimatorOfFloat.addUpdateListener(r0);
        objectAnimatorOfFloat.addListener(new playHideAnim.1.1(this, 0));
        objectAnimatorOfFloat.start();
        this.tipAnim = objectAnimatorOfFloat;
        this.tipState = State.HIDING;
        Log.d(TAG, "playHideAnim: play hide animation");
    }

    public final void playShowAnim(long j, long j2) {
        CharSequence charSequence = this.shortTip;
        if (charSequence != null) {
            this.volumePanelTipsView.setText(charSequence);
            this.showingTip.addTip(this.shortTip, 0);
            this.shortTip = null;
        } else {
            CharSequence charSequence2 = this.longTip;
            if (charSequence2 == null) {
                return;
            }
            this.volumePanelTipsView.setText(charSequence2);
            this.showingTip.addTip(this.longTip, 1);
        }
        if (this.volumePanelTipsView.getVisibility() != 0) {
            this.volumePanelTipsView.setVisibility(0);
        }
        TextView textView = this.volumePanelTipsView;
        float f = this.alphaValue;
        long j3 = (long) ((1.0f - f) * j);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, PROPERTY_NAME_ALPHA, f, 1.0f);
        objectAnimatorOfFloat.setStartDelay(j2);
        objectAnimatorOfFloat.setDuration(j3);
        createAnim.1 r8 = new createAnim.1();
        r8.this$0 = this;
        VarHandle.storeStoreFence();
        objectAnimatorOfFloat.addUpdateListener(r8);
        objectAnimatorOfFloat.addListener(new playHideAnim.1.1(this, 1));
        objectAnimatorOfFloat.start();
        this.tipAnim = objectAnimatorOfFloat;
        this.tipState = State.SHOWING;
        Log.d(TAG, "playShowAnim: play show animation, showing msg = " + ((Object) this.volumePanelTipsView.getText()));
    }

    public final void setLongTip(CharSequence charSequence, long j, long j2) {
        if (Intrinsics.areEqual(charSequence, this.longTip)) {
            return;
        }
        this.longTip = charSequence;
        Integer type = this.showingTip.getType();
        if (type != null && type.intValue() == 0) {
            return;
        }
        int iOrdinal = this.tipState.ordinal();
        if (iOrdinal == 0) {
            ObjectAnimator objectAnimator = this.tipAnim;
            if (objectAnimator != null) {
                objectAnimator.cancel();
                return;
            }
            return;
        }
        if (iOrdinal == 1) {
            playHideAnim(true);
            return;
        }
        if (iOrdinal != 2) {
            if (iOrdinal != 3) {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
            } else if (this.longTip != null) {
                playShowAnim(j, j2);
            }
        }
    }

    public final void setTipState(State state) {
        this.tipState = state;
    }

    public final void showShortTip(CharSequence charSequence) {
        int iOrdinal = this.tipState.ordinal();
        if (iOrdinal == 0) {
            if (Intrinsics.areEqual(charSequence, this.showingTip.getText())) {
                return;
            }
            this.shortTip = charSequence;
            ObjectAnimator objectAnimator = this.tipAnim;
            if (objectAnimator != null) {
                objectAnimator.cancel();
                return;
            }
            return;
        }
        if (iOrdinal != 1) {
            if (iOrdinal == 2) {
                this.shortTip = charSequence;
                return;
            } else if (iOrdinal != 3) {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return;
            } else {
                this.shortTip = charSequence;
                playShowAnim(200L, 0L);
                return;
            }
        }
        Integer type = this.showingTip.getType();
        if (type == null || type.intValue() != 0) {
            if (Intrinsics.areEqual(charSequence, this.showingTip.getText())) {
                return;
            }
            this.shortTip = charSequence;
            playHideAnim(true);
            return;
        }
        this.handler.removeCallbacks(getHideRunnable());
        if (Intrinsics.areEqual(charSequence, this.showingTip.getText())) {
            this.handler.postDelayed(getHideRunnable(), 2000L);
        } else {
            this.shortTip = charSequence;
            playHideAnim(true);
        }
    }

    public final void showSuperVolumeToast(Context context, boolean z, int i) {
        if (z) {
            showToast(context, z, i);
        }
    }

    public final void showToast(Context context, boolean z, int i) {
        String string = context.getString(i);
        if (z) {
            showShortTip(string);
        } else {
            Toast.makeText(context, string, 0).show();
        }
    }
}
