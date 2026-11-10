package com.oplus.systemui.statusbar.phone.dynamic;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dumpable;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.statusbar.phone.dynamic.icon.OplusStatusBarIconView;
import com.oplus.systemui.statusbar.phone.dynamic.icon.SecurePaymentIconView;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.binder.DynamicIconGroupViewBinderKt;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model.TipModel;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DynamicIconAnimationController extends ViewController implements DarkIconDispatcher.DarkReceiver {
    public static final Companion Companion = new Companion();
    private static final String TAG = "DynamicIconAnimationController";
    private AnimatorSet animationSet;
    private final ArrayList<Animator> animatorList;
    private final DarkIconDispatcher darkIconDispatcher;
    private DynamicContentLayout dynamicIconContent;
    private final View endSideContainer;
    private OplusStatusBarIconView icon;
    private final View.OnLayoutChangeListener layoutListener;
    private ViewGroup systemIcons;
    private TextView textView;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public DynamicIconAnimationController(View view, View view2, DarkIconDispatcher darkIconDispatcher) {
        super(view);
        this.endSideContainer = view2;
        this.darkIconDispatcher = darkIconDispatcher;
        this.animatorList = new ArrayList<>();
        DynamicIconAnimationController$layoutListener$1 dynamicIconAnimationController$layoutListener$1 = new DynamicIconAnimationController$layoutListener$1();
        dynamicIconAnimationController$layoutListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.layoutListener = dynamicIconAnimationController$layoutListener$1;
    }

    public final void hide(boolean z) {
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("hide: animate=", z), null, 8);
        AnimatorSet animatorSet = this.animationSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        this.animatorList.clear();
        if (z) {
            DynamicContentLayout dynamicContentLayout = this.dynamicIconContent;
            if (dynamicContentLayout != null && dynamicContentLayout.isShown()) {
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
                valueAnimatorOfFloat.setDuration(200L);
                hide.dynamicIconDisappear.1.1 r2 = new hide.dynamicIconDisappear.1.1(0);
                r2.this$0 = this;
                VarHandle.storeStoreFence();
                valueAnimatorOfFloat.addUpdateListener(r2);
                this.animatorList.add(valueAnimatorOfFloat);
            }
            ViewGroup viewGroup = this.systemIcons;
            if (viewGroup != null && viewGroup.isShown()) {
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.systemIcons, (Property<ViewGroup, Float>) View.ALPHA, 0.0f, 1.0f);
                objectAnimatorOfFloat.setDuration(300L);
                this.animatorList.add(objectAnimatorOfFloat);
            }
        }
        if (this.animatorList.isEmpty()) {
            hideImmediately$1();
            return;
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playSequentially(this.animatorList);
        DynamicIconAnimationController$hide$lambda$10$.inlined.doOnEnd.1 r0 = new DynamicIconAnimationController$hide$lambda$10$.inlined.doOnEnd.1();
        r0.this$0 = this;
        VarHandle.storeStoreFence();
        animatorSet2.addListener(r0);
        animatorSet2.start();
        this.animationSet = animatorSet2;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void hideImmediately$1() {
        Dumpable dumpable = this.icon;
        if (dumpable != null) {
            DynamicContentLayout dynamicContentLayout = this.dynamicIconContent;
            if (dynamicContentLayout != null) {
                dynamicContentLayout.removeView(dumpable);
            }
            this.icon = null;
        }
        DynamicContentLayout dynamicContentLayout2 = this.dynamicIconContent;
        if (dynamicContentLayout2 != null) {
            dynamicContentLayout2.setNeedRelayout(false);
        }
        TextView textView = this.textView;
        if (textView != null) {
            textView.setVisibility(8);
        }
        TextView textView2 = this.textView;
        if (textView2 != null) {
            textView2.setAlpha(0.0f);
        }
        ViewGroup viewGroup = this.systemIcons;
        if (viewGroup != null) {
            viewGroup.setAlpha(1.0f);
        }
    }

    public void onDarkChanged(ArrayList<Rect> arrayList, float f, int i) {
        TextView textView = this.textView;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    public final void onInit() {
        this.dynamicIconContent = (DynamicContentLayout) ((ViewController) this).mView.findViewById(2131362993);
        this.systemIcons = (ViewGroup) this.endSideContainer.findViewById(2131365533);
        DynamicContentLayout dynamicContentLayout = this.dynamicIconContent;
        this.textView = dynamicContentLayout != null ? (TextView) dynamicContentLayout.findViewById(2131362995) : null;
    }

    public final void onViewAttached() {
        this.darkIconDispatcher.addDarkReceiver(this);
        this.endSideContainer.addOnLayoutChangeListener(this.layoutListener);
    }

    public final void onViewDetached() {
        AnimatorSet animatorSet;
        AnimatorSet animatorSet2 = this.animationSet;
        if (animatorSet2 != null && animatorSet2.isRunning() && (animatorSet = this.animationSet) != null) {
            animatorSet.cancel();
        }
        this.animationSet = null;
        this.darkIconDispatcher.removeDarkReceiver(this);
        this.endSideContainer.removeOnLayoutChangeListener(this.layoutListener);
    }

    public final void setDynamicIconVisible(boolean z) {
        DynamicContentLayout dynamicContentLayout = this.dynamicIconContent;
        if (dynamicContentLayout != null) {
            dynamicContentLayout.setVisibility(z ? 0 : 4);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.widget.ImageView, com.oplus.systemui.statusbar.phone.dynamic.icon.OplusStatusBarIconView] */
    /* JADX WARN: Type inference failed for: r2v6, types: [android.view.View, android.widget.ImageView, com.oplus.systemui.statusbar.phone.dynamic.icon.OplusStatusBarIconView] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void show(TipModel tipModel, boolean z) {
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "show: " + tipModel + " animate=" + z, null, 8);
        AnimatorSet animatorSet = this.animationSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        if (this.icon == null) {
            Context context = getContext();
            int type = tipModel.getType();
            String slot = tipModel.getSlot();
            ?? securePaymentIconView = type == 0 ? new SecurePaymentIconView(context, slot, false) : new OplusStatusBarIconView(context, slot, false);
            addIconOnAttachStateChangeListener.1 r0 = new addIconOnAttachStateChangeListener.1();
            r0.this$0 = this;
            VarHandle.storeStoreFence();
            securePaymentIconView.addOnAttachStateChangeListener(r0);
            DynamicContentLayout dynamicContentLayout = this.dynamicIconContent;
            if (dynamicContentLayout != 0) {
                dynamicContentLayout.addView((View) securePaymentIconView, 0, new LinearLayout.LayoutParams(-2, -2));
            }
            this.icon = securePaymentIconView;
        }
        ?? r1 = this.icon;
        if (r1 != 0) {
            r1.applyDynamicIcon(DynamicIconGroupViewBinderKt.toDynamicIcon(tipModel), z);
            r1.setVisibility(0);
        }
        TextView textView = this.textView;
        if (textView != null) {
            textView.setText(tipModel.getText());
        }
        TextView textView2 = this.textView;
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131170848);
        DynamicContentLayout dynamicContentLayout2 = this.dynamicIconContent;
        if (dynamicContentLayout2 != null) {
            dynamicContentLayout2.setMaxWidth(this.endSideContainer.getWidth() - dimensionPixelSize);
        }
        DynamicContentLayout dynamicContentLayout3 = this.dynamicIconContent;
        if (dynamicContentLayout3 != null) {
            dynamicContentLayout3.setNeedRelayout(true);
        }
        this.animatorList.clear();
        if (z) {
            ViewGroup viewGroup = this.systemIcons;
            DynamicContentLayout dynamicContentLayout4 = this.dynamicIconContent;
            ArrayList<Animator> arrayList = this.animatorList;
            if (viewGroup != null && viewGroup.isShown()) {
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.systemIcons, (Property<ViewGroup, Float>) View.ALPHA, 1.0f, 0.0f);
                objectAnimatorOfFloat.setDuration(200L);
                arrayList.add(objectAnimatorOfFloat);
            }
            if (dynamicContentLayout4 != null && dynamicContentLayout4.isShown()) {
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                valueAnimatorOfFloat.setDuration(300L);
                hide.dynamicIconDisappear.1.1 r7 = new hide.dynamicIconDisappear.1.1(1);
                r7.this$0 = this;
                VarHandle.storeStoreFence();
                valueAnimatorOfFloat.addUpdateListener(r7);
                arrayList.add(valueAnimatorOfFloat);
            }
        }
        if (this.animatorList.isEmpty()) {
            showImmediately();
            return;
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playSequentially(this.animatorList);
        DynamicIconAnimationController$show$lambda$4$.inlined.doOnStart.1 r8 = new DynamicIconAnimationController$show$lambda$4$.inlined.doOnStart.1();
        r8.this$0 = this;
        VarHandle.storeStoreFence();
        animatorSet2.addListener(r8);
        DynamicIconAnimationController$show$lambda$4$.inlined.doOnEnd.1 r9 = new DynamicIconAnimationController$show$lambda$4$.inlined.doOnEnd.1();
        r9.this$0 = this;
        VarHandle.storeStoreFence();
        animatorSet2.addListener(r9);
        animatorSet2.start();
        this.animationSet = animatorSet2;
    }

    public final void showImmediately() {
        ViewGroup viewGroup = this.systemIcons;
        if (viewGroup != null) {
            viewGroup.setAlpha(0.0f);
        }
        Dumpable dumpable = this.icon;
        if (dumpable != null) {
            dumpable.setVisibility(0);
        }
        Dumpable dumpable2 = this.icon;
        if (dumpable2 != null) {
            dumpable2.setAlpha(1.0f);
        }
        TextView textView = this.textView;
        if (textView != null) {
            textView.setAlpha(1.0f);
        }
        TextView textView2 = this.textView;
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
    }
}
