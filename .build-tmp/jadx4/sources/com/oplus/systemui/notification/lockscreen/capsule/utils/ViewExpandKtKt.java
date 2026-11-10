package com.oplus.systemui.notification.lockscreen.capsule.utils;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.invoke.VarHandle;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ViewExpandKtKt {

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.utils.ViewExpandKtKt$addOnPreDraw$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ViewTreeObserver.OnPreDrawListener {
        public /* synthetic */ Function0 $action;
        public /* synthetic */ View $this_addOnPreDraw;

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public final boolean onPreDraw() {
            this.$action.invoke();
            this.$this_addOnPreDraw.getViewTreeObserver().removeOnPreDrawListener(this);
            return true;
        }
    }

    public static final void addOnPreDraw(View view, Function0 function0) {
        if (view == null) {
            return;
        }
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$action = function0;
        anonymousClass1.$this_addOnPreDraw = view;
        VarHandle.storeStoreFence();
        viewTreeObserver.addOnPreDrawListener(anonymousClass1);
    }

    public static final String getActionName(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            return "ACTION_DOWN";
        }
        if (action == 1) {
            return "ACTION_UP";
        }
        if (action != 2) {
            return action != 3 ? "ACTION_UNKNOWN" : "ACTION_CANCEL";
        }
        return "ACTION_MOVE";
    }

    public static final float getDimensionFloat(Resources resources, int i, float f) {
        Object obj;
        try {
            TypedValue typedValue = new TypedValue();
            resources.getValue(i, typedValue, true);
            obj = Result.constructor-impl(Float.valueOf(typedValue.getFloat()));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Float fValueOf = Float.valueOf(f);
        if (obj instanceof Result.Failure) {
            obj = fValueOf;
        }
        return ((Number) obj).floatValue();
    }

    public static final void setHeight(View view, int i) {
        ViewGroup.LayoutParams layoutParams;
        if (view == null || (layoutParams = view.getLayoutParams()) == null) {
            return;
        }
        layoutParams.height = i;
        view.setLayoutParams(layoutParams);
    }

    public static final void setPivotXY(View view, float f, float f2) {
        if (view == null) {
            return;
        }
        view.setPivotX(f);
        view.setPivotY(f2);
    }

    public static final void setScaleXY(View view, float f, float f2) {
        if (view == null) {
            return;
        }
        view.setScaleX(f);
        view.setScaleY(f2);
    }

    public static final void setWidth(View view, int i) {
        ViewGroup.LayoutParams layoutParams;
        if (view == null || (layoutParams = view.getLayoutParams()) == null) {
            return;
        }
        layoutParams.width = i;
        view.setLayoutParams(layoutParams);
    }

    public static final void setHeight(View view, float f) {
        setHeight(view, (int) f);
    }

    public static final void setWidth(View view, float f) {
        setWidth(view, (int) f);
    }
}
