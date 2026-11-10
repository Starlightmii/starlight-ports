package com.oplus.systemui.statusbar.phone.dynamic.icon;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.android.systemui.Dumpable;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.AnimatedImageView;
import com.android.systemui.statusbar.StatusIconDisplayable;
import com.android.systemui.statusbar.phone.OplusSysIconVisStateApplier;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.phone.VisStateIcon;
import com.oplus.systemui.statusbar.phone.dynamic.DynamicIcon;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model.IconSource;
import com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusStatusBarIconView extends AnimatedImageView implements StatusIconDisplayable, Dumpable, CoordAnimIcon, VisStateIcon {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusStatusBarIconView";
    public float appearAmount;
    public boolean blocked;
    private DynamicIcon icon;
    public float iconDarkIntensity;
    public int iconTint;
    public boolean isStaticColor;
    private final Lazy oplusVisStateApplier$delegate;
    public int paintColor;
    private String slot;
    public int state;
    private final ArrayList<Rect> tintArea;
    public float transXForCoord;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusStatusBarIconView(Context context, String str, boolean z) {
        super(context);
        this.slot = "";
        this.state = 2;
        this.paintColor = -855638017;
        this.tintArea = new ArrayList<>();
        this.iconTint = -1;
        OplusStatusBarIconView$$ExternalSyntheticLambda0 oplusStatusBarIconView$$ExternalSyntheticLambda0 = new OplusStatusBarIconView$$ExternalSyntheticLambda0();
        oplusStatusBarIconView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusVisStateApplier$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarIconView$$ExternalSyntheticLambda0);
        this.appearAmount = 1.0f;
        this.slot = str;
        setVisibility(8);
        this.blocked = z;
    }

    private final OplusSysIconVisStateApplier getOplusVisStateApplier() {
        return (OplusSysIconVisStateApplier) this.oplusVisStateApplier$delegate.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void applyDynamicIcon(DynamicIcon dynamicIcon, boolean z) {
        Drawable drawableMutate;
        Drawable drawableNewDrawable;
        setContentDescription(dynamicIcon.getDesc());
        IconSource iconSource = dynamicIcon.getIconSource();
        if (iconSource instanceof IconSource.DrawableIconSource) {
            Drawable iconDrawable = dynamicIcon.getIconSource().getIconDrawable();
            Drawable.ConstantState constantState = iconDrawable.getConstantState();
            if (constantState == null || (drawableNewDrawable = constantState.newDrawable(getResources())) == null || (drawableMutate = drawableNewDrawable.mutate()) == null) {
                drawableMutate = iconDrawable.mutate();
            }
            setImageDrawable(drawableMutate);
            applyTintColor(this.paintColor, dynamicIcon.getIconSource().getIconTintable());
        } else if (iconSource instanceof IconSource.ResourceIconSource) {
            setImageResource(((IconSource.ResourceIconSource) dynamicIcon.getIconSource()).getIconResId());
            applyTintColor(this.paintColor, dynamicIcon.getIconSource().getIconTintable());
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "OplusStatusBarIconView-->applyDynamicIcon: unknown iconSource");
        }
        boolean z2 = dynamicIcon.getVisible() && !this.blocked;
        if ((getVisibility() == 0) != z2) {
            setVisibility(z2 ? 0 : 8);
        }
        this.icon = dynamicIcon;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void applyTintColor(int i, boolean z) {
        if (z) {
            setImageTintList(ColorStateList.valueOf(i));
        } else {
            setImageTintList(null);
        }
        postInvalidate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println(getClass().getSimpleName().concat(" state: "));
        printWriter.println("  view: " + this);
        printWriter.println("  slot: " + this.slot);
        printWriter.println("  location: " + getLocationOnScreen()[0]);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("  transXForCoord: ", getTransXForCoord(), printWriter);
        printWriter.println("  paintColor: " + Integer.toHexString(this.paintColor) + "  isStaticColor: " + this.isStaticColor);
        Object tag = getTag(2131365374);
        StatusIconContainer.StatusIconState statusIconState = tag instanceof StatusIconContainer.StatusIconState ? (StatusIconContainer.StatusIconState) tag : null;
        if (statusIconState != null) {
            statusIconState.dump(printWriter, strArr);
        }
    }

    public float getAppearAmount() {
        return this.appearAmount;
    }

    public final boolean getBlocked() {
        return this.blocked;
    }

    public String getSlot() {
        return this.slot;
    }

    public /* bridge */ /* synthetic */ int getSlotTag() {
        return super.getSlotTag();
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public float getTransXForCoord() {
        return this.transXForCoord;
    }

    public int getVisibleState() {
        return this.state;
    }

    public final boolean isIconBlocked() {
        return this.blocked;
    }

    public final boolean isIconVisible() {
        DynamicIcon dynamicIcon = this.icon;
        if (dynamicIcon != null) {
            return dynamicIcon.getVisible();
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onDarkChanged(ArrayList<Rect> arrayList, float f, int i) {
        IconSource iconSource;
        if (this.isStaticColor) {
            return;
        }
        boolean iconTintable = true;
        boolean z = (i == this.iconTint && f == this.iconDarkIntensity && this.tintArea.equals(arrayList)) ? false : true;
        this.iconTint = i;
        this.iconDarkIntensity = f;
        this.tintArea.clear();
        if (arrayList != null) {
            this.tintArea.addAll(arrayList);
        }
        if (z && isAttachedToWindow()) {
            this.paintColor = DarkIconDispatcher.getTint(this.tintArea, this, this.iconTint);
            DynamicIcon dynamicIcon = this.icon;
            if (dynamicIcon != null && (iconSource = dynamicIcon.getIconSource()) != null) {
                iconTintable = iconSource.getIconTintable();
            }
            applyTintColor(this.paintColor, iconTintable);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onDraw(Canvas canvas) {
        if (getAppearAmount() <= 0.0f) {
            return;
        }
        if (getAppearAmount() == 1.0f && getTransXForCoord() == 0.0f) {
            super/*android.widget.ImageView*/.onDraw(canvas);
            return;
        }
        canvas.save();
        canvas.scale(getAppearAmount(), getAppearAmount(), getWidth() / 2.0f, getHeight() / 2.0f);
        canvas.translate(getTransXForCoord(), 0.0f);
        super/*android.widget.ImageView*/.onDraw(canvas);
        canvas.restore();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setAppearAmount(float f) {
        if (this.appearAmount == f) {
            return;
        }
        this.appearAmount = f;
        invalidate();
    }

    public final void setBlocked(boolean z) {
        this.blocked = z;
    }

    public void setDecorColor(int i) {
    }

    public void setStaticDrawableColor(int i) {
        IconSource iconSource;
        boolean iconTintable = true;
        this.isStaticColor = true;
        this.paintColor = i;
        DynamicIcon dynamicIcon = this.icon;
        if (dynamicIcon != null && (iconSource = dynamicIcon.getIconSource()) != null) {
            iconTintable = iconSource.getIconTintable();
        }
        applyTintColor(this.paintColor, iconTintable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public void setTransXForCoord(float f) {
        if (this.transXForCoord == f) {
            return;
        }
        this.transXForCoord = f;
        invalidate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setVisibleState(int i, boolean z) {
        if (i == this.state) {
            return;
        }
        this.state = i;
        Object parent = getParent();
        View view = parent instanceof View ? (View) parent : null;
        boolean z2 = false;
        boolean zIsShown = view != null ? view.isShown() : false;
        OplusStatusBarIconView$setVisibleState$visibleRunnable$1 oplusStatusBarIconView$setVisibleState$visibleRunnable$1 = new OplusStatusBarIconView$setVisibleState$visibleRunnable$1(0);
        oplusStatusBarIconView$setVisibleState$visibleRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        OplusStatusBarIconView$setVisibleState$visibleRunnable$1 oplusStatusBarIconView$setVisibleState$visibleRunnable$2 = new OplusStatusBarIconView$setVisibleState$visibleRunnable$1(1);
        oplusStatusBarIconView$setVisibleState$visibleRunnable$2.this$0 = this;
        VarHandle.storeStoreFence();
        OplusSysIconVisStateApplier oplusVisStateApplier = getOplusVisStateApplier();
        boolean z3 = i == 0;
        if (z && zIsShown) {
            z2 = true;
        }
        oplusVisStateApplier.setVisibleState(z3, z2, oplusStatusBarIconView$setVisibleState$visibleRunnable$1, oplusStatusBarIconView$setVisibleState$visibleRunnable$2);
    }

    public OplusStatusBarIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.slot = "";
        this.state = 2;
        this.paintColor = -855638017;
        this.tintArea = new ArrayList<>();
        this.iconTint = -1;
        OplusStatusBarIconView$$ExternalSyntheticLambda0 oplusStatusBarIconView$$ExternalSyntheticLambda0 = new OplusStatusBarIconView$$ExternalSyntheticLambda0();
        oplusStatusBarIconView$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusVisStateApplier$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarIconView$$ExternalSyntheticLambda0);
        this.appearAmount = 1.0f;
    }

    public /* bridge */ /* synthetic */ void setVisibleState(int i) {
        super.setVisibleState(i);
    }
}
