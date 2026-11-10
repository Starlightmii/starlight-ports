package com.oplus.systemui.statusbar.notification.animation;

import com.android.systemui.statusbar.notification.animation.AlphaState;
import com.android.systemui.statusbar.notification.animation.RowAnimationStateEx;
import com.android.systemui.statusbar.notification.animation.ScaleState;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.PropertyReference1Impl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RowAnimationStateExImpl implements RowAnimationStateEx {
    private final Lazy alphaManger$delegate;
    private final Lazy clipWidthManager$delegate;
    private final Lazy scaleManger$delegate;

    public RowAnimationStateExImpl() {
        final int i = 0;
        this.scaleManger$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        final int i2 = 0;
                        return new StateManager(new Function2() { // from class: com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl$$ExternalSyntheticLambda3
                            public final Object invoke(Object obj, Object obj2) {
                                switch (i2) {
                                    case 0:
                                        return ((ScaleState) obj).plus((ScaleState) obj2);
                                    default:
                                        return ((AlphaState) obj).plus((AlphaState) obj2);
                                }
                            }
                        }, new PropertyReference1Impl() { // from class: com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl$scaleManger$2$2
                            public final Object get(Object obj) {
                                ScaleState scaleState = (ScaleState) obj;
                                return Boolean.valueOf((scaleState.scaleX == 1.0f && scaleState.scaleY == 1.0f) ? false : true);
                            }
                        }, ScaleState.Companion.getINVALID());
                    case 1:
                        final int i3 = 1;
                        return new StateManager(new Function2() { // from class: com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl$$ExternalSyntheticLambda3
                            public final Object invoke(Object obj, Object obj2) {
                                switch (i3) {
                                    case 0:
                                        return ((ScaleState) obj).plus((ScaleState) obj2);
                                    default:
                                        return ((AlphaState) obj).plus((AlphaState) obj2);
                                }
                            }
                        }, new PropertyReference1Impl() { // from class: com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl$alphaManger$2$2
                            public final Object get(Object obj) {
                                return Boolean.valueOf(!(((AlphaState) obj).alpha == 1.0f));
                            }
                        }, AlphaState.Companion.getINVALID());
                    default:
                        return new ClipWidthManager("clip_width_touch_scale");
                }
            }
        });
        final int i2 = 1;
        this.alphaManger$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        final int i3 = 0;
                        return new StateManager(new Function2() { // from class: com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl$$ExternalSyntheticLambda3
                            public final Object invoke(Object obj, Object obj2) {
                                switch (i3) {
                                    case 0:
                                        return ((ScaleState) obj).plus((ScaleState) obj2);
                                    default:
                                        return ((AlphaState) obj).plus((AlphaState) obj2);
                                }
                            }
                        }, new PropertyReference1Impl() { // from class: com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl$scaleManger$2$2
                            public final Object get(Object obj) {
                                ScaleState scaleState = (ScaleState) obj;
                                return Boolean.valueOf((scaleState.scaleX == 1.0f && scaleState.scaleY == 1.0f) ? false : true);
                            }
                        }, ScaleState.Companion.getINVALID());
                    case 1:
                        final int i4 = 1;
                        return new StateManager(new Function2() { // from class: com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl$$ExternalSyntheticLambda3
                            public final Object invoke(Object obj, Object obj2) {
                                switch (i4) {
                                    case 0:
                                        return ((ScaleState) obj).plus((ScaleState) obj2);
                                    default:
                                        return ((AlphaState) obj).plus((AlphaState) obj2);
                                }
                            }
                        }, new PropertyReference1Impl() { // from class: com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl$alphaManger$2$2
                            public final Object get(Object obj) {
                                return Boolean.valueOf(!(((AlphaState) obj).alpha == 1.0f));
                            }
                        }, AlphaState.Companion.getINVALID());
                    default:
                        return new ClipWidthManager("clip_width_touch_scale");
                }
            }
        });
        final int i3 = 2;
        this.clipWidthManager$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        final int i4 = 0;
                        return new StateManager(new Function2() { // from class: com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl$$ExternalSyntheticLambda3
                            public final Object invoke(Object obj, Object obj2) {
                                switch (i4) {
                                    case 0:
                                        return ((ScaleState) obj).plus((ScaleState) obj2);
                                    default:
                                        return ((AlphaState) obj).plus((AlphaState) obj2);
                                }
                            }
                        }, new PropertyReference1Impl() { // from class: com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl$scaleManger$2$2
                            public final Object get(Object obj) {
                                ScaleState scaleState = (ScaleState) obj;
                                return Boolean.valueOf((scaleState.scaleX == 1.0f && scaleState.scaleY == 1.0f) ? false : true);
                            }
                        }, ScaleState.Companion.getINVALID());
                    case 1:
                        final int i5 = 1;
                        return new StateManager(new Function2() { // from class: com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl$$ExternalSyntheticLambda3
                            public final Object invoke(Object obj, Object obj2) {
                                switch (i5) {
                                    case 0:
                                        return ((ScaleState) obj).plus((ScaleState) obj2);
                                    default:
                                        return ((AlphaState) obj).plus((AlphaState) obj2);
                                }
                            }
                        }, new PropertyReference1Impl() { // from class: com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl$alphaManger$2$2
                            public final Object get(Object obj) {
                                return Boolean.valueOf(!(((AlphaState) obj).alpha == 1.0f));
                            }
                        }, AlphaState.Companion.getINVALID());
                    default:
                        return new ClipWidthManager("clip_width_touch_scale");
                }
            }
        });
    }

    private final StateManager getAlphaManger() {
        return (StateManager) this.alphaManger$delegate.getValue();
    }

    private final ClipWidthManager getClipWidthManager() {
        return (ClipWidthManager) this.clipWidthManager$delegate.getValue();
    }

    private final StateManager getScaleManger() {
        return (StateManager) this.scaleManger$delegate.getValue();
    }

    public int getClipWidth(Object obj) {
        return getClipWidthManager().get(obj);
    }

    public Object getEffectiveClipWidthSource() {
        return getClipWidthManager().getEffectiveSource();
    }

    public AlphaState getRowAlpha(Object obj) {
        return (AlphaState) getAlphaManger().getState(obj);
    }

    public ScaleState getRowScale(Object obj) {
        return (ScaleState) getScaleManger().getState(obj);
    }

    public void requestClipWidth(Object obj, int i) {
        getClipWidthManager().request(obj, i);
    }

    public void requestRowAlpha(Object obj, AlphaState alphaState) {
        getAlphaManger().requestState(obj, alphaState);
    }

    public void requestRowScale(Object obj, ScaleState scaleState) {
        getScaleManger().requestState(obj, scaleState);
    }

    public String toString() {
        return "RowAnimationStateExImpl{ scale:" + getScaleManger() + "; alpha:" + getAlphaManger() + "; clipWidth:" + getClipWidthManager() + " }; ";
    }
}
