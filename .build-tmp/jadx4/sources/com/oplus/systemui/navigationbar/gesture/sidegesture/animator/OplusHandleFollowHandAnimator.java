package com.oplus.systemui.navigationbar.gesture.sidegesture.animator;

import android.content.Context;
import android.util.Log;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHandleFollowHandAnimator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "HandleFollowHandAnimator";
    private Context context;
    private SpringAnimation followHandAnimator;
    public float followHandDistance;
    public final float maxTransitionDistance;
    private OplusNavigationHandle oplusNavigationHandle;
    public boolean isTouchUp = true;
    private State currentState = State.STOPPED;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class State {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ State[] $VALUES;
        public static final State FOLLOWXRUNNING;
        public static final State FOLLOWYRUNNING;
        public static final State STOPPED;

        static {
            State state = new State("FOLLOWXRUNNING", 0);
            FOLLOWXRUNNING = state;
            State state2 = new State("FOLLOWYRUNNING", 1);
            FOLLOWYRUNNING = state2;
            State state3 = new State("STOPPED", 2);
            STOPPED = state3;
            State[] stateArr = {state, state2, state3};
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

    public OplusHandleFollowHandAnimator(Context context, OplusNavigationHandle oplusNavigationHandle) {
        this.context = context;
        this.oplusNavigationHandle = oplusNavigationHandle;
        this.maxTransitionDistance = context.getResources().getDimension(2131170532);
        SpringAnimation springAnimation = new SpringAnimation(new FloatPropertyCompat() { // from class: com.oplus.systemui.navigationbar.gesture.sidegesture.animator.OplusHandleFollowHandAnimator.1
            public final float getValue(Object obj) {
                return OplusHandleFollowHandAnimator.this.followHandDistance;
            }

            public final void setValue(Object obj, float f) {
                OplusHandleFollowHandAnimator.this.followHandDistance = f;
            }
        }, this);
        SpringForce springForce = new SpringForce();
        springForce.setStiffness(350.0f);
        springForce.setDampingRatio(1.0f);
        springAnimation.setSpring(springForce);
        OplusHandleFollowHandAnimator$2$2 oplusHandleFollowHandAnimator$2$2 = new OplusHandleFollowHandAnimator$2$2();
        oplusHandleFollowHandAnimator$2$2.this$0 = this;
        VarHandle.storeStoreFence();
        springAnimation.addUpdateListener(oplusHandleFollowHandAnimator$2$2);
        OplusHandleFollowHandAnimator$2$3 oplusHandleFollowHandAnimator$2$3 = new OplusHandleFollowHandAnimator$2$3();
        oplusHandleFollowHandAnimator$2$3.this$0 = this;
        VarHandle.storeStoreFence();
        springAnimation.addEndListener(oplusHandleFollowHandAnimator$2$3);
        this.followHandAnimator = springAnimation;
    }

    public final Context getContext() {
        return this.context;
    }

    public final State getCurrentState() {
        return this.currentState;
    }

    public final boolean isFollowHandAnimRunning() {
        return this.currentState != State.STOPPED;
    }

    public final boolean isFollowHandXAnimRunning() {
        return this.currentState == State.FOLLOWXRUNNING;
    }

    public final boolean isFollowHandYAnimRunning() {
        return this.currentState == State.FOLLOWYRUNNING;
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    public final void setCurrentState(State state) {
        this.currentState = state;
    }

    public final void setStartValue(float f) {
        this.followHandAnimator.setStartValue(f);
    }

    public final void startFollowHandAnimator(float f, boolean z) {
        float f2 = this.maxTransitionDistance;
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, -f2, f2);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "HandleFollowHandAnimator-->".concat("transitionDistance:" + fCoerceIn + ", isReturn:" + z));
        }
        SpringAnimation springAnimation = this.followHandAnimator;
        if (this.currentState == State.FOLLOWXRUNNING) {
            springAnimation.getSpring().setStiffness(z ? 200.0f : 350.0f);
        }
        if (this.currentState == State.FOLLOWYRUNNING) {
            springAnimation.getSpring().setStiffness(z ? 250.0f : 350.0f);
        }
        springAnimation.getSpring().setDampingRatio(z ? 0.7f : 1.0f);
        springAnimation.animateToFinalPosition(fCoerceIn);
    }

    public final void startFollowHandXAnimator(float f) {
        this.isTouchUp = false;
        this.currentState = State.FOLLOWXRUNNING;
        startFollowHandAnimator(f, false);
    }

    public final void startFollowHandYAnimator(float f) {
        if (!isFollowHandAnimRunning() && this.oplusNavigationHandle.getTranslationY() != 0.0f) {
            this.followHandAnimator.setStartValue(this.oplusNavigationHandle.getTranslationY());
        }
        this.isTouchUp = false;
        this.currentState = State.FOLLOWYRUNNING;
        startFollowHandAnimator(f, false);
    }
}
