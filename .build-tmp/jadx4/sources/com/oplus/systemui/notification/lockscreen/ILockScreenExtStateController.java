package com.oplus.systemui.notification.lockscreen;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ILockScreenExtStateController {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String ORIGIN_COLLAPSE_RETURN_TO_SINKING = "GroupCollapse:return_to_origin";
    public static final String ORIGIN_EXPAND_CLICKED_SINK_TO_STACK = "ExpandClicked:sinking_to_stack";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String ORIGIN_COLLAPSE_RETURN_TO_SINKING = "GroupCollapse:return_to_origin";
        public static final String ORIGIN_EXPAND_CLICKED_SINK_TO_STACK = "ExpandClicked:sinking_to_stack";
    }

    static /* synthetic */ void getDefaultExtState$annotations() {
    }

    static /* synthetic */ void getLastExtState$annotations() {
    }

    static /* synthetic */ void gotoKeyguardState$default(ILockScreenExtStateController iLockScreenExtStateController, String str, float f, int i) {
        if ((i & 4) != 0) {
            f = 0.0f;
        }
        iLockScreenExtStateController.gotoKeyguardState(true, str, f, null);
    }

    static /* synthetic */ void tryGotoShadeLockState$default(ILockScreenExtStateController iLockScreenExtStateController, String str, float f, Runnable runnable, int i) {
        if ((i & 4) != 0) {
            f = 0.0f;
        }
        float f2 = f;
        if ((i & 8) != 0) {
            runnable = null;
        }
        iLockScreenExtStateController.tryGotoShadeLockState(true, str, f2, runnable, null);
    }

    default boolean backToKgFirstWhenTouchOnKgNtfCenter() {
        return false;
    }

    boolean canAcceptImmersiveToImmersive(String str);

    ExpandableView findSeedingCurrentView(String str);

    int getDefaultExtState();

    StateFlow getExtStateEvent();

    boolean getHasNotificationStacked();

    int getLastExtState();

    void goToExtState(boolean z, int i, String str, int i2, Bundle bundle);

    void gotoKeyguardState(boolean z, String str, float f, Runnable runnable);

    default boolean isCapsuleState() {
        return ((ExtStateEvent) getExtStateEvent().getValue()).getNewState() == 1;
    }

    default boolean isImmersiveState() {
        return ((ExtStateEvent) getExtStateEvent().getValue()).getNewState() == 2;
    }

    boolean isLockScreenNtfCenterState();

    default boolean isStackState() {
        return ((ExtStateEvent) getExtStateEvent().getValue()).getNewState() == 0 || ((ExtStateEvent) getExtStateEvent().getValue()).getNewState() == 3;
    }

    default boolean isTopStackState() {
        return ((ExtStateEvent) getExtStateEvent().getValue()).getNewState() == 0;
    }

    void tryGotoShadeLockState(boolean z, String str, float f, Runnable runnable, ExpandableNotificationRow expandableNotificationRow);

    <V extends View> void updateViewStateToCurrentExtState(int i, List<? extends V> list);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ExtStateEvent {
        public static final Companion Companion = new Companion();
        private static final String IMMERSIVE_KEY = "immersive_key";
        private static final String LAST_IMMERSIVE_KEY = "last_immersive_key";
        private static final String START_VELOCITY = "start_velocity";
        public final boolean animate;
        private final Lazy animateType$delegate;
        public final long eventTime;
        private final Bundle extra;
        public final int newState;
        public final int oldState;
        public final int reason;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
            public final String getImmersiveKey(Bundle bundle) {
                return bundle.getString(ExtStateEvent.IMMERSIVE_KEY);
            }

            public final String getLastImmersiveKey(Bundle bundle) {
                return bundle.getString(ExtStateEvent.LAST_IMMERSIVE_KEY);
            }

            public final Float getStartVelocity(Bundle bundle) {
                if (bundle.containsKey(ExtStateEvent.START_VELOCITY)) {
                    return Float.valueOf(bundle.getFloat(ExtStateEvent.START_VELOCITY));
                }
                return null;
            }

            public final Bundle withImmersiveKey(Bundle bundle, String str) {
                if (str != null) {
                    bundle.putString(ExtStateEvent.IMMERSIVE_KEY, str);
                }
                return bundle;
            }

            public final Bundle withLastImmersiveKey(Bundle bundle, String str) {
                if (str != null) {
                    bundle.putString(ExtStateEvent.LAST_IMMERSIVE_KEY, str);
                }
                return bundle;
            }

            public final Bundle withStartVelocity(Bundle bundle, float f) {
                if (Math.abs(f) <= Float.MAX_VALUE) {
                    bundle.putFloat(ExtStateEvent.START_VELOCITY, f);
                }
                return bundle;
            }
        }

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class ExtStateEventAnimType {
            public static final /* synthetic */ EnumEntries $ENTRIES;
            public static final /* synthetic */ ExtStateEventAnimType[] $VALUES;
            public static final ExtStateEventAnimType CAPSULE_TO_IMMERSIVE;
            public static final ExtStateEventAnimType CAPSULE_TO_SINKING;
            public static final ExtStateEventAnimType CAPSULE_TO_STACK;
            public static final ExtStateEventAnimType IMMERSIVE_TO_CAPSULE;
            public static final ExtStateEventAnimType IMMERSIVE_TO_IMMERSIVE;
            public static final ExtStateEventAnimType IMMERSIVE_TO_STACK;
            public static final ExtStateEventAnimType SINKING_TO_CAPSULE;
            public static final ExtStateEventAnimType SINKING_TO_STACK;
            public static final ExtStateEventAnimType STACK_TO_CAPSULE;
            public static final ExtStateEventAnimType STACK_TO_IMMERSIVE;
            public static final ExtStateEventAnimType STACK_TO_SINKING;
            public static final ExtStateEventAnimType UNKNOWN;

            static {
                ExtStateEventAnimType extStateEventAnimType = new ExtStateEventAnimType("STACK_TO_CAPSULE", 0);
                STACK_TO_CAPSULE = extStateEventAnimType;
                ExtStateEventAnimType extStateEventAnimType2 = new ExtStateEventAnimType("STACK_TO_IMMERSIVE", 1);
                STACK_TO_IMMERSIVE = extStateEventAnimType2;
                ExtStateEventAnimType extStateEventAnimType3 = new ExtStateEventAnimType("STACK_TO_SINKING", 2);
                STACK_TO_SINKING = extStateEventAnimType3;
                ExtStateEventAnimType extStateEventAnimType4 = new ExtStateEventAnimType("CAPSULE_TO_STACK", 3);
                CAPSULE_TO_STACK = extStateEventAnimType4;
                ExtStateEventAnimType extStateEventAnimType5 = new ExtStateEventAnimType("CAPSULE_TO_IMMERSIVE", 4);
                CAPSULE_TO_IMMERSIVE = extStateEventAnimType5;
                ExtStateEventAnimType extStateEventAnimType6 = new ExtStateEventAnimType("CAPSULE_TO_SINKING", 5);
                CAPSULE_TO_SINKING = extStateEventAnimType6;
                ExtStateEventAnimType extStateEventAnimType7 = new ExtStateEventAnimType("IMMERSIVE_TO_STACK", 6);
                IMMERSIVE_TO_STACK = extStateEventAnimType7;
                ExtStateEventAnimType extStateEventAnimType8 = new ExtStateEventAnimType("IMMERSIVE_TO_CAPSULE", 7);
                IMMERSIVE_TO_CAPSULE = extStateEventAnimType8;
                ExtStateEventAnimType extStateEventAnimType9 = new ExtStateEventAnimType("IMMERSIVE_TO_IMMERSIVE", 8);
                IMMERSIVE_TO_IMMERSIVE = extStateEventAnimType9;
                ExtStateEventAnimType extStateEventAnimType10 = new ExtStateEventAnimType("SINKING_TO_STACK", 9);
                SINKING_TO_STACK = extStateEventAnimType10;
                ExtStateEventAnimType extStateEventAnimType11 = new ExtStateEventAnimType("SINKING_TO_CAPSULE", 10);
                SINKING_TO_CAPSULE = extStateEventAnimType11;
                ExtStateEventAnimType extStateEventAnimType12 = new ExtStateEventAnimType("UNKNOWN", 11);
                UNKNOWN = extStateEventAnimType12;
                ExtStateEventAnimType[] extStateEventAnimTypeArr = {extStateEventAnimType, extStateEventAnimType2, extStateEventAnimType3, extStateEventAnimType4, extStateEventAnimType5, extStateEventAnimType6, extStateEventAnimType7, extStateEventAnimType8, extStateEventAnimType9, extStateEventAnimType10, extStateEventAnimType11, extStateEventAnimType12};
                $VALUES = extStateEventAnimTypeArr;
                $ENTRIES = EnumEntriesKt.enumEntries(extStateEventAnimTypeArr);
            }

            public static EnumEntries getEntries() {
                return $ENTRIES;
            }

            public static ExtStateEventAnimType valueOf(String str) {
                return (ExtStateEventAnimType) Enum.valueOf(ExtStateEventAnimType.class, str);
            }

            public static ExtStateEventAnimType[] values() {
                return (ExtStateEventAnimType[]) $VALUES.clone();
            }
        }

        public ExtStateEvent(int i, int i2, int i3, Bundle bundle, boolean z, long j) {
            this.oldState = i;
            this.newState = i2;
            this.reason = i3;
            this.extra = bundle;
            this.animate = z;
            this.eventTime = j;
            ILockScreenExtStateController$ExtStateEvent$$ExternalSyntheticLambda0 iLockScreenExtStateController$ExtStateEvent$$ExternalSyntheticLambda0 = new ILockScreenExtStateController$ExtStateEvent$$ExternalSyntheticLambda0();
            iLockScreenExtStateController$ExtStateEvent$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            this.animateType$delegate = LazyKt__LazyJVMKt.lazy(iLockScreenExtStateController$ExtStateEvent$$ExternalSyntheticLambda0);
        }

        public final Bundle component4() {
            return this.extra;
        }

        public final ExtStateEvent copy(int i, int i2, int i3, Bundle bundle, boolean z, long j) {
            return new ExtStateEvent(i, i2, i3, bundle, z, j);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ExtStateEvent)) {
                return false;
            }
            ExtStateEvent extStateEvent = (ExtStateEvent) obj;
            if (extStateEvent.oldState != this.oldState || extStateEvent.newState != this.newState || extStateEvent.reason != this.reason || extStateEvent.animate != this.animate) {
                return false;
            }
            Bundle bundle = extStateEvent.extra;
            String immersiveKey = bundle != null ? Companion.getImmersiveKey(bundle) : null;
            Bundle bundle2 = this.extra;
            return Intrinsics.areEqual(immersiveKey, bundle2 != null ? Companion.getImmersiveKey(bundle2) : null);
        }

        public final boolean getAnimate() {
            return this.animate;
        }

        public final ExtStateEventAnimType getAnimateType() {
            return (ExtStateEventAnimType) this.animateType$delegate.getValue();
        }

        public final long getEventTime() {
            return this.eventTime;
        }

        public final Bundle getExtra() {
            return this.extra;
        }

        public final int getNewState() {
            return this.newState;
        }

        public final int getOldState() {
            return this.oldState;
        }

        public final int getReason() {
            return this.reason;
        }

        public final int hashCode() {
            String immersiveKey;
            int iHashCode = Boolean.hashCode(this.animate) + MonoSpline$.ExternalSyntheticOutline0.m(this.reason, MonoSpline$.ExternalSyntheticOutline0.m(this.newState, Integer.hashCode(this.oldState) * 31, 31), 31);
            Bundle bundle = this.extra;
            return (bundle == null || (immersiveKey = Companion.getImmersiveKey(bundle)) == null) ? iHashCode : immersiveKey.hashCode() + (iHashCode * 31);
        }

        public String toString() {
            Bundle bundle = this.extra;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("ExtStateEvent(oldState=", this.oldState, this.newState, ", newState=", ", reason=");
            sbM.append(this.reason);
            sbM.append(", extra=");
            sbM.append(bundle);
            sbM.append(", animate=");
            sbM.append(this.animate);
            sbM.append(", eventTime=");
            sbM.append(this.eventTime);
            sbM.append(")");
            return sbM.toString();
        }

        public /* synthetic */ ExtStateEvent(int i, int i2, int i3, Bundle bundle, boolean z, int i4) {
            this(i, i2, i3, (i4 & 8) != 0 ? null : bundle, (i4 & 16) != 0 ? true : z, SystemClock.uptimeMillis());
        }
    }
}
