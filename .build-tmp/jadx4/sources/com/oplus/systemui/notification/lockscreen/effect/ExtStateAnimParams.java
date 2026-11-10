package com.oplus.systemui.notification.lockscreen.effect;

import android.content.Context;
import android.util.Log;
import android.view.animation.Interpolator;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import com.android.systemui.statusbar.notification.OplusPhysicsInterpolator;
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.oplus.systemui.notification.lockscreen.olivelink.OLiveLinkBaseParams;
import com.oplus.transition.OplusAnimationController;
import com.oplus.zoom.animation.ZoomAnimationUtils;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Scalar;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.olivelink.SpringType;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ExtStateAnimParams {
    public static final ExtStateAnimParams INSTANCE = new ExtStateAnimParams();
    private static final String SERVER_NAME = "三态动画";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleProgressRadio {
        private static final String GROUP_NAME = "胶囊相关无缝比例设置(以位置为基准)";
        public static final CapsuleProgressRadio INSTANCE = new CapsuleProgressRadio();
        private static final Lazy CAPSULE_TO_STACK$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(1));
        private static final Lazy STACK_TO_CAPSULE$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(2));
        private static final Lazy STACK_TO_CAPSULE_FOR_MAP$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(3));
        private static final Lazy CAPSULE_TO_IMMER$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(4));
        private static final Lazy IMMER_TO_CAPSULE$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(5));
        private static final Lazy CAPSULE_TO_STACK_ALPHA$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(6));

        public final Scalar getCAPSULE_TO_IMMER() {
            return (Scalar) CAPSULE_TO_IMMER$delegate.getValue();
        }

        public final Scalar getCAPSULE_TO_STACK() {
            return (Scalar) CAPSULE_TO_STACK$delegate.getValue();
        }

        public final Spring getCAPSULE_TO_STACK_ALPHA() {
            return (Spring) CAPSULE_TO_STACK_ALPHA$delegate.getValue();
        }

        public final Scalar getIMMER_TO_CAPSULE() {
            return (Scalar) IMMER_TO_CAPSULE$delegate.getValue();
        }

        public final Scalar getSTACK_TO_CAPSULE() {
            return (Scalar) STACK_TO_CAPSULE$delegate.getValue();
        }

        public final Scalar getSTACK_TO_CAPSULE_FOR_MAP() {
            return (Scalar) STACK_TO_CAPSULE_FOR_MAP$delegate.getValue();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleToImmer {
        public static final CapsuleToImmer INSTANCE = new CapsuleToImmer();
        private static final String GROUP_NAME = "胶囊切沉静态";
        private static final ExtStateParams data = new ExtStateParams(GROUP_NAME, "胶囊", new ExtStateParams.ExtStateParamsDefaultValue(new SpringParams(0.11f, 0.3f), new SpringParams(0.11f, 0.3f), new SpringParams(0.2f, 0.25f), new SpringParams(0.1f, 0.5f), new SpringParams(0.11f, 0.3f), new SpringParams(0.28f, 0.5f), 14));
        private static final Lazy NEXT_CARD_START_SCALE$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(7));
        private static final Lazy NEXT_CARD_START_SPRING$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(8));
        private static final Lazy NEXT_CARD_DELAY$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(9));
        private static final Lazy NEXT_CARD_DURATION$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(10));

        public final ExtStateParams getData() {
            return data;
        }

        public final Scalar getNEXT_CARD_DELAY() {
            return (Scalar) NEXT_CARD_DELAY$delegate.getValue();
        }

        public final Scalar getNEXT_CARD_DURATION() {
            return (Scalar) NEXT_CARD_DURATION$delegate.getValue();
        }

        public final Scalar getNEXT_CARD_START_SCALE() {
            return (Scalar) NEXT_CARD_START_SCALE$delegate.getValue();
        }

        public final Spring getNEXT_CARD_START_SPRING() {
            return (Spring) NEXT_CARD_START_SPRING$delegate.getValue();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleToStack {
        public static final CapsuleToStack INSTANCE = new CapsuleToStack();
        private static final String GROUP_NAME = "胶囊切列表";
        private static final ExtStateParams data = new ExtStateParams(GROUP_NAME, "胶囊", new ExtStateParams.ExtStateParamsDefaultValue(550, ZoomAnimationUtils.TRANSIT_SCALE_AND_POSITION, 68, 1.0f, new SpringParams(0.11f, 0.3f), new SpringParams(0.11f, 0.3f), new SpringParams(0.0f, 0.35f), new SpringParams(0.3f, 0.45f), new SpringParams(0.0f, 0.45f), new SpringParams(0.25f, 0.35f)));

        public final ExtStateParams getData() {
            return data;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CommonParams {
        private static final String GROUP_NAME = "通用";
        public static final CommonParams INSTANCE = new CommonParams();
        private static final Lazy TOUCH_TYPE$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(0));
        private static final Lazy TOUCH_SCALE$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(11));
        private static final Lazy TOUCH_SCALE_DURATION$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(12));
        private static final Lazy TOUCH_SCALE_INTERRTV_EXIT$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(13));
        private static final Lazy TOUCH_SCALE_INTERRTV_ENTER$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(14));
        private static final Lazy CAPSULE_BOTTOM$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(15));
        private static final Lazy TIMER_RATIO$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(16));

        public final Spring getCAPSULE_BOTTOM() {
            return (Spring) CAPSULE_BOTTOM$delegate.getValue();
        }

        public final Scalar getTIMER_RATIO() {
            return (Scalar) TIMER_RATIO$delegate.getValue();
        }

        public final Scalar getTOUCH_SCALE() {
            return (Scalar) TOUCH_SCALE$delegate.getValue();
        }

        public final Scalar getTOUCH_SCALE_DURATION() {
            return (Scalar) TOUCH_SCALE_DURATION$delegate.getValue();
        }

        public final Spring getTOUCH_SCALE_INTERRTV_ENTER() {
            return (Spring) TOUCH_SCALE_INTERRTV_ENTER$delegate.getValue();
        }

        public final Spring getTOUCH_SCALE_INTERRTV_EXIT() {
            return (Spring) TOUCH_SCALE_INTERRTV_EXIT$delegate.getValue();
        }

        public final Scalar getTOUCH_TYPE() {
            return (Scalar) TOUCH_TYPE$delegate.getValue();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DraggerParams {
        public static final DraggerParams INSTANCE = new DraggerParams();
        private static final String GROUP_NAME = "跟手动画";
        private static final TypeDraggerParams capsule = new TypeDraggerParams(GROUP_NAME, "胶囊", new DraggerParamsDefaultValue(40, new SpringParams(0.0f, 0.1f)));
        private static final TypeDraggerParams stack = new TypeDraggerParams("跟手动画列表", "列表", new DraggerParamsDefaultValue(40, new SpringParams(0.0f, 0.2f)));
        private static final Lazy EXT_SCALE$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(17));

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class DraggerParamsDefaultValue {
            public final int dragMaxAction;
            private final SpringParams spring;

            public DraggerParamsDefaultValue(int i, SpringParams springParams) {
                this.dragMaxAction = i;
                this.spring = springParams;
            }

            public final SpringParams component2() {
                return this.spring;
            }

            public final DraggerParamsDefaultValue copy(int i, SpringParams springParams) {
                return new DraggerParamsDefaultValue(i, springParams);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof DraggerParamsDefaultValue)) {
                    return false;
                }
                DraggerParamsDefaultValue draggerParamsDefaultValue = (DraggerParamsDefaultValue) obj;
                return this.dragMaxAction == draggerParamsDefaultValue.dragMaxAction && Intrinsics.areEqual(this.spring, draggerParamsDefaultValue.spring);
            }

            public final int getDragMaxAction() {
                return this.dragMaxAction;
            }

            public final SpringParams getSpring() {
                return this.spring;
            }

            public final int hashCode() {
                return this.spring.hashCode() + (Integer.hashCode(this.dragMaxAction) * 31);
            }

            public String toString() {
                return "DraggerParamsDefaultValue(dragMaxAction=" + this.dragMaxAction + ", spring=" + this.spring + ")";
            }
        }

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class TypeDraggerParams {
            private final Lazy MAX_ACTION$delegate;
            private final Lazy SPING$delegate;
            private final DraggerParamsDefaultValue defaultValue;
            private final String dragerType;
            private final String groupName;

            public TypeDraggerParams(String str, String str2, DraggerParamsDefaultValue draggerParamsDefaultValue) {
                this.groupName = str;
                this.dragerType = str2;
                this.defaultValue = draggerParamsDefaultValue;
                ExtStateAnimParams$DraggerParams$TypeDraggerParams$$ExternalSyntheticLambda0 extStateAnimParams$DraggerParams$TypeDraggerParams$$ExternalSyntheticLambda0 = new ExtStateAnimParams$DraggerParams$TypeDraggerParams$$ExternalSyntheticLambda0(0);
                extStateAnimParams$DraggerParams$TypeDraggerParams$$ExternalSyntheticLambda0.f$0 = this;
                VarHandle.storeStoreFence();
                this.MAX_ACTION$delegate = LazyKt__LazyJVMKt.lazy(extStateAnimParams$DraggerParams$TypeDraggerParams$$ExternalSyntheticLambda0);
                ExtStateAnimParams$DraggerParams$TypeDraggerParams$$ExternalSyntheticLambda0 extStateAnimParams$DraggerParams$TypeDraggerParams$$ExternalSyntheticLambda1 = new ExtStateAnimParams$DraggerParams$TypeDraggerParams$$ExternalSyntheticLambda0(1);
                extStateAnimParams$DraggerParams$TypeDraggerParams$$ExternalSyntheticLambda1.f$0 = this;
                VarHandle.storeStoreFence();
                this.SPING$delegate = LazyKt__LazyJVMKt.lazy(extStateAnimParams$DraggerParams$TypeDraggerParams$$ExternalSyntheticLambda1);
            }

            public final Scalar getMAX_ACTION() {
                return (Scalar) this.MAX_ACTION$delegate.getValue();
            }

            public final Spring getSPING() {
                return (Spring) this.SPING$delegate.getValue();
            }
        }

        public final TypeDraggerParams getCapsule() {
            return capsule;
        }

        public final Scalar getEXT_SCALE() {
            return (Scalar) EXT_SCALE$delegate.getValue();
        }

        public final TypeDraggerParams getStack() {
            return stack;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ImmerToCapsule {
        public static final ImmerToCapsule INSTANCE = new ImmerToCapsule();
        private static final String GROUP_NAME = "沉静态切胶囊";
        private static final ExtStateParams data = new ExtStateParams(GROUP_NAME, "沉静", new ExtStateParams.ExtStateParamsDefaultValue(new SpringParams(0.2f, 0.25f), new SpringParams(0.11f, 0.4f), new SpringParams(0.2f, 0.9f), new SpringParams(0.0f, 0.38f), new SpringParams(0.11f, 0.3f), new SpringParams(0.3f, 0.5f), 14));
        private static final Lazy LAST_CARD_START_SCALE$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(18));
        private static final Lazy LAST_CARD_START_SPRING$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(19));
        private static final Lazy LAST_CARD_DELAY$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(20));
        private static final Lazy LAST_CARD_DURATION$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(21));

        public final ExtStateParams getData() {
            return data;
        }

        public final Scalar getLAST_CARD_DELAY() {
            return (Scalar) LAST_CARD_DELAY$delegate.getValue();
        }

        public final Scalar getLAST_CARD_DURATION() {
            return (Scalar) LAST_CARD_DURATION$delegate.getValue();
        }

        public final Scalar getLAST_CARD_START_SCALE() {
            return (Scalar) LAST_CARD_START_SCALE$delegate.getValue();
        }

        public final Spring getLAST_CARD_START_SPRING() {
            return (Spring) LAST_CARD_START_SPRING$delegate.getValue();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ImmerToStack {
        public static final ImmerToStack INSTANCE = new ImmerToStack();
        private static final String GROUP_NAME = "沉静态切列表";
        private static final ExtStateParams data = new ExtStateParams(GROUP_NAME, "沉静");

        public final ExtStateParams getData() {
            return data;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class LightOSAnimParams extends OLiveLinkBaseParams.OLiveLinkGroup {
        private static final Spring CAPSULE_TO_STACK_ALPHA;
        private static final Scalar CAPSULE_TO_STACK_SPLIT_LIMIT;
        private static final Spring CAPSULE_TO_STACK_TRANSLATION_Y_ANIM;
        public static final LightOSAnimParams INSTANCE;
        private static final Scalar STACK_MAX_TRANSLATION_Y;
        private static final Spring STACK_TO_CAPSULE_ALPHA;
        private static final Scalar STACK_TO_CAPSULE_SPLIT_LIMIT;
        private static final Spring STACK_TO_CAPSULE_TRANSLATION_Y_ANIM;
        private static final Spring ScrollUpFadeoutParam;

        static {
            LightOSAnimParams lightOSAnimParams = new LightOSAnimParams(7, "轻量级动画");
            INSTANCE = lightOSAnimParams;
            STACK_TO_CAPSULE_SPLIT_LIMIT = lightOSAnimParams.safeScalar(ExtStateAnimParams.SERVER_NAME, "列表到胶囊分割点", Float.valueOf(0.5f));
            CAPSULE_TO_STACK_SPLIT_LIMIT = lightOSAnimParams.safeScalar(ExtStateAnimParams.SERVER_NAME, "胶囊到列表分割点", Float.valueOf(0.65f));
            STACK_MAX_TRANSLATION_Y = lightOSAnimParams.safeScalar(ExtStateAnimParams.SERVER_NAME, "列表向下最大位移", Float.valueOf(100.0f));
            STACK_TO_CAPSULE_ALPHA = lightOSAnimParams.safeSpring(ExtStateAnimParams.SERVER_NAME, "轻量级动画_列表切胶囊", 0.25f, 0.45f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            CAPSULE_TO_STACK_ALPHA = lightOSAnimParams.safeSpring(ExtStateAnimParams.SERVER_NAME, "轻量级动画_胶囊切列表", 0.0f, 0.25f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            ScrollUpFadeoutParam = lightOSAnimParams.safeSpring(ExtStateAnimParams.SERVER_NAME, "轻量级动画_胶囊上滑放手动画", 0.0f, 0.1f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            STACK_TO_CAPSULE_TRANSLATION_Y_ANIM = lightOSAnimParams.safeSpring(ExtStateAnimParams.SERVER_NAME, "列表到胶囊Y轴动画", 0.0f, 0.45f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            CAPSULE_TO_STACK_TRANSLATION_Y_ANIM = lightOSAnimParams.safeSpring(ExtStateAnimParams.SERVER_NAME, "胶囊到列表Y轴动画", 0.0f, 0.2f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
        }

        public final Spring getCAPSULE_TO_STACK_ALPHA() {
            return CAPSULE_TO_STACK_ALPHA;
        }

        public final Scalar getCAPSULE_TO_STACK_SPLIT_LIMIT() {
            return CAPSULE_TO_STACK_SPLIT_LIMIT;
        }

        public final Spring getCAPSULE_TO_STACK_TRANSLATION_Y_ANIM() {
            return CAPSULE_TO_STACK_TRANSLATION_Y_ANIM;
        }

        public final Scalar getSTACK_MAX_TRANSLATION_Y() {
            return STACK_MAX_TRANSLATION_Y;
        }

        public final Spring getSTACK_TO_CAPSULE_ALPHA() {
            return STACK_TO_CAPSULE_ALPHA;
        }

        public final Scalar getSTACK_TO_CAPSULE_SPLIT_LIMIT() {
            return STACK_TO_CAPSULE_SPLIT_LIMIT;
        }

        public final Spring getSTACK_TO_CAPSULE_TRANSLATION_Y_ANIM() {
            return STACK_TO_CAPSULE_TRANSLATION_Y_ANIM;
        }

        public final Spring getScrollUpFadeoutParam() {
            return ScrollUpFadeoutParam;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SpringParams {
        public static final Companion Companion = new Companion();
        private static final SpringParams DEFAULT = new SpringParams(0.11f, 0.3f);
        public final float bounce;
        public final float response;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
            public final SpringParams getDEFAULT() {
                return SpringParams.DEFAULT;
            }
        }

        public SpringParams(float f, float f2) {
            this.bounce = f;
            this.response = f2;
        }

        public final SpringParams copy(float f, float f2) {
            return new SpringParams(f, f2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SpringParams)) {
                return false;
            }
            SpringParams springParams = (SpringParams) obj;
            return Float.compare(this.bounce, springParams.bounce) == 0 && Float.compare(this.response, springParams.response) == 0;
        }

        public final float getBounce() {
            return this.bounce;
        }

        public final float getResponse() {
            return this.response;
        }

        public final int hashCode() {
            return Float.hashCode(this.response) + (Float.hashCode(this.bounce) * 31);
        }

        public String toString() {
            return ListPopupWindow$.ExternalSyntheticOutline0.m("SpringParams(bounce=", this.bounce, ", response=", this.response, ")");
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class StackToCapsule {
        public static final StackToCapsule INSTANCE = new StackToCapsule();
        private static final String GROUP_NAME = "列表切胶囊";
        private static final ExtStateParams data = new ExtStateParams(GROUP_NAME, "列表", new ExtStateParams.ExtStateParamsDefaultValue(550, 100, 50, 1.0f, new SpringParams(0.11f, 0.9f), new SpringParams(0.11f, 0.4f), new SpringParams(0.0f, 0.35f), new SpringParams(0.3f, 0.45f), new SpringParams(0.0f, 0.45f), new SpringParams(0.25f, 0.35f)));
        private static final Lazy DELAY_FOR_CAPSULE_STACK_SWIPE$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(22));
        private static final Lazy ITEM_SCALE_DIFF$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(23));
        private static final Lazy FIREST_ITEM_SCALE$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(24));
        private static final Lazy ITEM_SCALE_MINI$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(25));

        public final Scalar getDELAY_FOR_CAPSULE_STACK_SWIPE() {
            return (Scalar) DELAY_FOR_CAPSULE_STACK_SWIPE$delegate.getValue();
        }

        public final ExtStateParams getData() {
            return data;
        }

        public final Scalar getFIREST_ITEM_SCALE() {
            return (Scalar) FIREST_ITEM_SCALE$delegate.getValue();
        }

        public final Scalar getITEM_SCALE_DIFF() {
            return (Scalar) ITEM_SCALE_DIFF$delegate.getValue();
        }

        public final Scalar getITEM_SCALE_MINI() {
            return (Scalar) ITEM_SCALE_MINI$delegate.getValue();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class StackToImmer {
        public static final StackToImmer INSTANCE = new StackToImmer();
        private static final String GROUP_NAME = "列表切沉浸";
        private static final ExtStateParams data = new ExtStateParams(GROUP_NAME, "列表");

        public final ExtStateParams getData() {
            return data;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class StackToNtfCenter {
        private static final String GROUP_NAME = "列表进入与退出锁屏通知中心";
        public static final StackToNtfCenter INSTANCE = new StackToNtfCenter();
        private static final Lazy TIME$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(26));
        private static final Lazy SCALE$delegate = LazyKt__LazyJVMKt.lazy(new ExtStateAnimParams$CommonParams$$ExternalSyntheticLambda0(27));

        public final Spring getSCALE() {
            return (Spring) SCALE$delegate.getValue();
        }

        public final Scalar getTIME() {
            return (Scalar) TIME$delegate.getValue();
        }
    }

    public final void initOliveLink(Context context) {
        OliveLinkConnector.INSTANCE.start(context, SERVER_NAME);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "LightOSAnimParams-->".concat(String.valueOf(LightOSAnimParams.INSTANCE)));
        }
    }

    public final Interpolator toInterpolator(Spring spring) {
        return spring.springType() == SpringType.SPRING_INTERPOLATOR ? new COUISpringInterpolator(spring.stiffness(), spring.dampingRatio(), 0.0d, 1.0f, 15000.0f) : new OplusPhysicsInterpolator(spring.bounce(), spring.response(), (Float) null);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ExtStateParams {
        private final Lazy ALPHA$delegate;
        private final Lazy BLUR$delegate;
        private final Lazy CLIP$delegate;
        private final Lazy CORNER_RADIUS$delegate;
        private final Lazy DRAG_MAX_ACTION$delegate;
        private final Lazy HEIGHT$delegate;
        private final Lazy TIME$delegate;
        private final Lazy TRANSLATION_Y$delegate;
        private final String dragerType;
        private final String groupName;
        private final ExtStateParamsDefaultValue stateExtStateParams;

        public ExtStateParams(String str, String str2, ExtStateParamsDefaultValue extStateParamsDefaultValue) {
            this.groupName = str;
            this.dragerType = str2;
            this.stateExtStateParams = extStateParamsDefaultValue;
            ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0 extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0 = new ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0(0);
            extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            this.TIME$delegate = LazyKt__LazyJVMKt.lazy(extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0);
            ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0 extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda1 = new ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0(1);
            extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            this.DRAG_MAX_ACTION$delegate = LazyKt__LazyJVMKt.lazy(extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda1);
            ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0 extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda2 = new ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0(2);
            extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda2.f$0 = this;
            VarHandle.storeStoreFence();
            this.ALPHA$delegate = LazyKt__LazyJVMKt.lazy(extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda2);
            ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0 extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda3 = new ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0(3);
            extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda3.f$0 = this;
            VarHandle.storeStoreFence();
            this.BLUR$delegate = LazyKt__LazyJVMKt.lazy(extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda3);
            ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0 extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda4 = new ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0(4);
            extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda4.f$0 = this;
            VarHandle.storeStoreFence();
            this.CORNER_RADIUS$delegate = LazyKt__LazyJVMKt.lazy(extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda4);
            ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0 extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda5 = new ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0(5);
            extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda5.f$0 = this;
            VarHandle.storeStoreFence();
            this.CLIP$delegate = LazyKt__LazyJVMKt.lazy(extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda5);
            ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0 extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda6 = new ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0(6);
            extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda6.f$0 = this;
            VarHandle.storeStoreFence();
            this.HEIGHT$delegate = LazyKt__LazyJVMKt.lazy(extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda6);
            ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0 extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda7 = new ExtStateAnimParams$ExtStateParams$$ExternalSyntheticLambda0(7);
            extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda7.f$0 = this;
            VarHandle.storeStoreFence();
            this.TRANSLATION_Y$delegate = LazyKt__LazyJVMKt.lazy(extStateAnimParams$ExtStateParams$$ExternalSyntheticLambda7);
        }

        public final Spring getALPHA() {
            return (Spring) this.ALPHA$delegate.getValue();
        }

        public final Spring getBLUR() {
            return (Spring) this.BLUR$delegate.getValue();
        }

        public final Spring getCLIP() {
            return (Spring) this.CLIP$delegate.getValue();
        }

        public final Spring getCORNER_RADIUS() {
            return (Spring) this.CORNER_RADIUS$delegate.getValue();
        }

        public final Scalar getDRAG_MAX_ACTION() {
            return (Scalar) this.DRAG_MAX_ACTION$delegate.getValue();
        }

        public final Spring getHEIGHT() {
            return (Spring) this.HEIGHT$delegate.getValue();
        }

        public final Scalar getTIME() {
            return (Scalar) this.TIME$delegate.getValue();
        }

        public final Spring getTRANSLATION_Y() {
            return (Spring) this.TRANSLATION_Y$delegate.getValue();
        }

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class ExtStateParamsDefaultValue {
            private final SpringParams alpha;
            private final SpringParams blur;
            private final SpringParams clip;
            private final SpringParams cornerRadius;
            public final int dragMaxAction;
            public final int dragMaxDistance;
            public final float dragRatio;
            private final SpringParams height;
            public final long time;
            private final SpringParams translationY;

            public /* synthetic */ ExtStateParamsDefaultValue(SpringParams springParams, SpringParams springParams2, SpringParams springParams3, SpringParams springParams4, SpringParams springParams5, SpringParams springParams6, int i) {
                this((i & 1) != 0 ? 450L : 300L, ZoomAnimationUtils.TRANSIT_SCALE_AND_POSITION, 68, 1.0f, (i & 16) != 0 ? SpringParams.Companion.getDEFAULT() : springParams, (i & 32) != 0 ? SpringParams.Companion.getDEFAULT() : springParams2, (i & 64) != 0 ? SpringParams.Companion.getDEFAULT() : springParams3, (i & 128) != 0 ? SpringParams.Companion.getDEFAULT() : springParams4, (i & 256) != 0 ? SpringParams.Companion.getDEFAULT() : springParams5, (i & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? SpringParams.Companion.getDEFAULT() : springParams6);
            }

            public final SpringParams component10() {
                return this.translationY;
            }

            public final SpringParams component5() {
                return this.alpha;
            }

            public final SpringParams component6() {
                return this.blur;
            }

            public final SpringParams component7() {
                return this.cornerRadius;
            }

            public final SpringParams component8() {
                return this.clip;
            }

            public final SpringParams component9() {
                return this.height;
            }

            public final ExtStateParamsDefaultValue copy(long j, int i, int i2, float f, SpringParams springParams, SpringParams springParams2, SpringParams springParams3, SpringParams springParams4, SpringParams springParams5, SpringParams springParams6) {
                return new ExtStateParamsDefaultValue(j, i, i2, f, springParams, springParams2, springParams3, springParams4, springParams5, springParams6);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof ExtStateParamsDefaultValue)) {
                    return false;
                }
                ExtStateParamsDefaultValue extStateParamsDefaultValue = (ExtStateParamsDefaultValue) obj;
                return this.time == extStateParamsDefaultValue.time && this.dragMaxDistance == extStateParamsDefaultValue.dragMaxDistance && this.dragMaxAction == extStateParamsDefaultValue.dragMaxAction && Float.compare(this.dragRatio, extStateParamsDefaultValue.dragRatio) == 0 && Intrinsics.areEqual(this.alpha, extStateParamsDefaultValue.alpha) && Intrinsics.areEqual(this.blur, extStateParamsDefaultValue.blur) && Intrinsics.areEqual(this.cornerRadius, extStateParamsDefaultValue.cornerRadius) && Intrinsics.areEqual(this.clip, extStateParamsDefaultValue.clip) && Intrinsics.areEqual(this.height, extStateParamsDefaultValue.height) && Intrinsics.areEqual(this.translationY, extStateParamsDefaultValue.translationY);
            }

            public final SpringParams getAlpha() {
                return this.alpha;
            }

            public final SpringParams getBlur() {
                return this.blur;
            }

            public final SpringParams getClip() {
                return this.clip;
            }

            public final SpringParams getCornerRadius() {
                return this.cornerRadius;
            }

            public final int getDragMaxAction() {
                return this.dragMaxAction;
            }

            public final int getDragMaxDistance() {
                return this.dragMaxDistance;
            }

            public final float getDragRatio() {
                return this.dragRatio;
            }

            public final SpringParams getHeight() {
                return this.height;
            }

            public final long getTime() {
                return this.time;
            }

            public final SpringParams getTranslationY() {
                return this.translationY;
            }

            public final int hashCode() {
                return this.translationY.hashCode() + ((this.height.hashCode() + ((this.clip.hashCode() + ((this.cornerRadius.hashCode() + ((this.blur.hashCode() + ((this.alpha.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(this.dragRatio, MonoSpline$.ExternalSyntheticOutline0.m(this.dragMaxAction, MonoSpline$.ExternalSyntheticOutline0.m(this.dragMaxDistance, Long.hashCode(this.time) * 31, 31), 31), 31)) * 31)) * 31)) * 31)) * 31)) * 31);
            }

            public String toString() {
                return "ExtStateParamsDefaultValue(time=" + this.time + ", dragMaxDistance=" + this.dragMaxDistance + ", dragMaxAction=" + this.dragMaxAction + ", dragRatio=" + this.dragRatio + ", alpha=" + this.alpha + ", blur=" + this.blur + ", cornerRadius=" + this.cornerRadius + ", clip=" + this.clip + ", height=" + this.height + ", translationY=" + this.translationY + ")";
            }

            public ExtStateParamsDefaultValue(long j, int i, int i2, float f, SpringParams springParams, SpringParams springParams2, SpringParams springParams3, SpringParams springParams4, SpringParams springParams5, SpringParams springParams6) {
                this.time = j;
                this.dragMaxDistance = i;
                this.dragMaxAction = i2;
                this.dragRatio = f;
                this.alpha = springParams;
                this.blur = springParams2;
                this.cornerRadius = springParams3;
                this.clip = springParams4;
                this.height = springParams5;
                this.translationY = springParams6;
            }
        }

        public /* synthetic */ ExtStateParams(String str, String str2) {
            this(str, str2, new ExtStateParamsDefaultValue(null, null, null, null, null, null, 1023));
        }
    }
}
