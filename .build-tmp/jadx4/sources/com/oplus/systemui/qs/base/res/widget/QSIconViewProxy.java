package com.oplus.systemui.qs.base.res.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.RenderMode;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import com.oplus.systemui.qs.base.res.util.ViewTagUtils;
import com.oplus.systemui.qs.base.tile.QSLottieAnimationView;
import com.oplusos.systemui.common.util.GlobalUtils;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.util.function.Supplier;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSIconViewProxy {
    public static final Companion Companion = new Companion();
    public boolean animationEnabled;
    private final Context context;
    public boolean disabledByPolicy;
    public long highestScheduledIconTransactionId;
    private Drawable iconDrawable;
    private final View iconHostView;
    public long iconTransactionId;
    private final QSLottieAnimationView iconView;
    public boolean isDetailTile;
    private String lottiePrefix;
    public long scheduledIconTransactionId;
    public int state;
    private String tileSpec;
    private ColorStateList tintList;

    /* JADX WARN: Type inference failed for: r0v0, types: [android.widget.ImageView, com.airbnb.lottie.LottieAnimationView, com.oplus.systemui.qs.base.tile.QSLottieAnimationView] */
    public QSIconViewProxy(View view) {
        this.iconHostView = view;
        Context context = view.getContext();
        this.context = context;
        ?? qSLottieAnimationView = new QSLottieAnimationView(context, null, 6, 0);
        qSLottieAnimationView.setId(2131364774);
        qSLottieAnimationView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        qSLottieAnimationView.setRenderMode(RenderMode.SOFTWARE);
        this.iconView = qSLottieAnimationView;
        this.state = 1;
        this.animationEnabled = true;
        this.scheduledIconTransactionId = -1L;
        this.highestScheduledIconTransactionId = -1L;
        this.iconTransactionId = getNextIconTransactionId();
    }

    public static final String getLottieAnimAsset(Context context, int i, String str, int i2) {
        return Companion.getLottieAnimAsset(context, i, str, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long getNextIconTransactionId() {
        long j = this.highestScheduledIconTransactionId + 1;
        this.highestScheduledIconTransactionId = j;
        return j;
    }

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
    private final boolean setDisabledByPolicy(boolean z) {
        if (this.disabledByPolicy == z) {
            return false;
        }
        this.disabledByPolicy = z;
        LottieAnimationView lottieAnimationView = this.iconView;
        if (z) {
            lottieAnimationView.setColorFilter(this.context.getColor(2131103300));
            return true;
        }
        lottieAnimationView.clearColorFilter();
        return true;
    }

    public final ImageView getIconView() {
        return this.iconView;
    }

    public final void onRecycle() {
        this.scheduledIconTransactionId = -1L;
        this.iconView.cancelAnimation();
        this.iconView.setImageDrawable((Drawable) null);
    }

    public final void setAnimationEnable(boolean z) {
        this.animationEnabled = z;
    }

    public final void setDetailTile(boolean z) {
        this.isDetailTile = z;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x005a  */
    /* JADX WARN: Code duplicated, block: B:33:0x005c  */
    /* JADX WARN: Code duplicated, block: B:36:0x0069  */
    /* JADX WARN: Code duplicated, block: B:37:0x006b  */
    public final void setIcon(QSTile.State state, boolean z) {
        final QSTile.Icon icon;
        boolean z2;
        int i;
        boolean z3;
        String str;
        boolean z4;
        String str2 = state.spec;
        final boolean z5 = state.isTransient;
        Supplier supplier = state.iconSupplier;
        if (supplier == null || (icon = (QSTile.Icon) supplier.get()) == null) {
            icon = state.icon;
        }
        boolean z6 = state.disabledByPolicy;
        String lottieAnimAsset = Companion.getLottieAnimAsset(this.context, state);
        if (Intrinsics.areEqual(this.tileSpec, str2)) {
            if (z) {
                LottieAnimationView lottieAnimationView = this.iconView;
                if (this.animationEnabled && lottieAnimationView.isShown() && lottieAnimationView.getDrawable() != null && ((!GlobalUtils.hasDialogShowing() || this.isDetailTile) && QSConstant.getQsTileAnimationAllowed())) {
                    z2 = true;
                }
            }
            i = state.state;
            if (this.state == i) {
                z3 = false;
            } else {
                this.state = i;
                z3 = true;
            }
            str = state.lottiePrefix;
            if (Intrinsics.areEqual(this.lottiePrefix, str)) {
                z4 = false;
            } else {
                this.lottiePrefix = str;
                z4 = true;
            }
            setDisabledByPolicy(z6);
            if (z2 || lottieAnimAsset == null) {
                updateIcon(icon, z5, z2);
            }
            AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter() { // from class: com.oplus.systemui.qs.base.res.widget.QSIconViewProxy.setIcon.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    QSIconViewProxy qSIconViewProxy = QSIconViewProxy.this;
                    if (qSIconViewProxy.scheduledIconTransactionId == qSIconViewProxy.iconTransactionId) {
                        qSIconViewProxy.updateIcon(icon, z5, false);
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationStart(Animator animator) {
                    QSIconViewProxy qSIconViewProxy = QSIconViewProxy.this;
                    qSIconViewProxy.iconTransactionId = qSIconViewProxy.getNextIconTransactionId();
                    QSIconViewProxy qSIconViewProxy2 = QSIconViewProxy.this;
                    qSIconViewProxy2.scheduledIconTransactionId = qSIconViewProxy2.iconTransactionId;
                }
            };
            this.iconView.removeAllAnimatorListeners();
            this.iconView.addAnimatorListener(animatorListenerAdapter);
            boolean zIsAnimating = this.iconView.isAnimating();
            if (!zIsAnimating || z3 || z4) {
                if (zIsAnimating && !z4) {
                    boolean zIsLowGaussianLevel = ScrimUtil.isLowGaussianLevel(this.context);
                    QSLottieAnimationView qSLottieAnimationView = this.iconView;
                    if (zIsLowGaussianLevel) {
                        qSLottieAnimationView.cancelAnimation();
                        return;
                    } else {
                        qSLottieAnimationView.reverseAnimationSpeed();
                        return;
                    }
                }
                if (zIsAnimating || z3 || z4) {
                    this.iconView.setTintColorfulConfig(lottieAnimAsset, QSLottieAnimationView.ColorfulConfig.Companion.makeColorfulConfigForQSTile(lottieAnimAsset, this.tintList));
                    this.iconView.setSpeed(1.0f);
                    this.iconView.setCacheComposition(!StringsKt__StringsKt.contains(lottieAnimAsset, QSConstant.DND_LOTTIE_PREFIX, false));
                    this.iconView.setAnimation(lottieAnimAsset);
                    this.iconView.playAnimation();
                    return;
                }
                return;
            }
            return;
        }
        this.tileSpec = str2;
        z2 = false;
        i = state.state;
        if (this.state == i) {
            z3 = false;
        } else {
            this.state = i;
            z3 = true;
        }
        str = state.lottiePrefix;
        if (Intrinsics.areEqual(this.lottiePrefix, str)) {
            z4 = false;
        } else {
            this.lottiePrefix = str;
            z4 = true;
        }
        setDisabledByPolicy(z6);
        if (z2) {
        }
        updateIcon(icon, z5, z2);
    }

    public final boolean setTintList(ColorStateList colorStateList) {
        if (Intrinsics.areEqual(this.tintList, colorStateList)) {
            return false;
        }
        this.tintList = colorStateList;
        updateIconTint();
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
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
    public final void updateIcon(QSTile.Icon icon, boolean z, boolean z2) {
        this.scheduledIconTransactionId = -1L;
        this.iconView.cancelAnimation();
        if (!Intrinsics.areEqual(icon, ViewTagUtils.getQSIconTag(this.iconView))) {
            ViewTagUtils.setQSIconTag(this.iconView, icon);
            Drawable invisibleDrawable = null;
            if (z2) {
                if (icon != null) {
                    invisibleDrawable = icon.getDrawable(this.context);
                }
            } else if (icon != null) {
                invisibleDrawable = icon.getInvisibleDrawable(this.context);
            }
            if (invisibleDrawable != null) {
                invisibleDrawable.setAutoMirrored(false);
                invisibleDrawable.setLayoutDirection(this.iconHostView.getLayoutDirection());
            }
            this.iconDrawable = invisibleDrawable;
            int padding = icon != null ? icon.getPadding() : 0;
            this.iconView.setPadding(0, padding, 0, padding);
        }
        final Drawable drawable = this.iconDrawable;
        this.iconView.setImageDrawable(drawable);
        if (drawable instanceof Animatable2) {
            Animatable animatable = (Animatable) drawable;
            animatable.start();
            if (!z2) {
                animatable.stop();
            } else if (z) {
                ((Animatable2) drawable).registerAnimationCallback(new Animatable2.AnimationCallback() { // from class: com.oplus.systemui.qs.base.res.widget.QSIconViewProxy.updateIcon.1
                    @Override // android.graphics.drawable.Animatable2.AnimationCallback
                    public final void onAnimationEnd(Drawable drawable2) {
                        ((Animatable) drawable).start();
                    }
                });
            }
        }
        updateIconTint();
    }

    public final void updateIconTint() {
        if (this.scheduledIconTransactionId != -1) {
            return;
        }
        this.iconView.setAlpha(1.0f);
        LottieAnimationView lottieAnimationView = this.iconView;
        ColorStateList colorStateList = this.tintList;
        lottieAnimationView.setImageTintList(colorStateList != null ? ColorStateList.valueOf(colorStateList.getColorForState(this.iconHostView.getDrawableState(), colorStateList.getDefaultColor())) : null);
    }

    public static final String getLottieAnimAsset(Context context, QSTile.State state) {
        return Companion.getLottieAnimAsset(context, state);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final String getLottieAnimAsset(Context context, int i, String str, int i2) {
            if (str == null || str.length() == 0) {
                return null;
            }
            if (i == 2 && (i2 & 16) == 16) {
                return context.getString(2131954291, str, QSConstant.ACTIVE_SUFFIX);
            }
            if (i == 1 && (i2 & 1) == 1) {
                return context.getString(2131954291, str, QSConstant.INACTIVE_SUFFIX);
            }
            return null;
        }

        public final String getLottieAnimAsset(Context context, QSTile.State state) {
            return getLottieAnimAsset(context, state.state, state.lottiePrefix, state.lottieSupport);
        }
    }
}
