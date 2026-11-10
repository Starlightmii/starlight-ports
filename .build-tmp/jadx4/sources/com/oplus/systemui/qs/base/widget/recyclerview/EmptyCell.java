package com.oplus.systemui.qs.base.widget.recyclerview;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.systemui.qs.base.springtransition.DrawableAlphaProperty;
import com.oplusos.systemui.common.model.Loc;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EmptyCell {
    private Drawable drawable;
    private DrawableAlphaProperty drawableAlphaProperty;
    private final Loc loc;
    private Rect rect;
    private COUISpringAnimation springAnimation;
    public boolean temporary;

    public EmptyCell(Loc loc, Rect rect, Drawable drawable, COUISpringAnimation cOUISpringAnimation, DrawableAlphaProperty drawableAlphaProperty, boolean z) {
        this.loc = loc;
        this.rect = rect;
        this.drawable = drawable;
        this.springAnimation = cOUISpringAnimation;
        this.drawableAlphaProperty = drawableAlphaProperty;
        this.temporary = z;
    }

    public final Loc component1() {
        return this.loc;
    }

    public final Rect component2() {
        return this.rect;
    }

    public final Drawable component3() {
        return this.drawable;
    }

    public final COUISpringAnimation component4() {
        return this.springAnimation;
    }

    public final DrawableAlphaProperty component5() {
        return this.drawableAlphaProperty;
    }

    public final EmptyCell copy(Loc loc, Rect rect, Drawable drawable, COUISpringAnimation cOUISpringAnimation, DrawableAlphaProperty drawableAlphaProperty, boolean z) {
        return new EmptyCell(loc, rect, drawable, cOUISpringAnimation, drawableAlphaProperty, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EmptyCell)) {
            return false;
        }
        EmptyCell emptyCell = (EmptyCell) obj;
        return Intrinsics.areEqual(this.loc, emptyCell.loc) && Intrinsics.areEqual(this.rect, emptyCell.rect) && Intrinsics.areEqual(this.drawable, emptyCell.drawable) && Intrinsics.areEqual(this.springAnimation, emptyCell.springAnimation) && Intrinsics.areEqual(this.drawableAlphaProperty, emptyCell.drawableAlphaProperty) && this.temporary == emptyCell.temporary;
    }

    public final Drawable getDrawable() {
        return this.drawable;
    }

    public final DrawableAlphaProperty getDrawableAlphaProperty() {
        return this.drawableAlphaProperty;
    }

    public final Loc getLoc() {
        return this.loc;
    }

    public final Rect getRect() {
        return this.rect;
    }

    public final COUISpringAnimation getSpringAnimation() {
        return this.springAnimation;
    }

    public final boolean getTemporary() {
        return this.temporary;
    }

    public final int hashCode() {
        int iHashCode = this.loc.hashCode() * 31;
        Rect rect = this.rect;
        int iHashCode2 = (iHashCode + (rect == null ? 0 : rect.hashCode())) * 31;
        Drawable drawable = this.drawable;
        int iHashCode3 = (iHashCode2 + (drawable == null ? 0 : drawable.hashCode())) * 31;
        COUISpringAnimation cOUISpringAnimation = this.springAnimation;
        int iHashCode4 = (iHashCode3 + (cOUISpringAnimation == null ? 0 : cOUISpringAnimation.hashCode())) * 31;
        DrawableAlphaProperty drawableAlphaProperty = this.drawableAlphaProperty;
        return Boolean.hashCode(this.temporary) + ((iHashCode4 + (drawableAlphaProperty != null ? drawableAlphaProperty.hashCode() : 0)) * 31);
    }

    public final void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public final void setDrawableAlphaProperty(DrawableAlphaProperty drawableAlphaProperty) {
        this.drawableAlphaProperty = drawableAlphaProperty;
    }

    public final void setRect(Rect rect) {
        this.rect = rect;
    }

    public final void setSpringAnimation(COUISpringAnimation cOUISpringAnimation) {
        this.springAnimation = cOUISpringAnimation;
    }

    public final void setTemporary(boolean z) {
        this.temporary = z;
    }

    public String toString() {
        return "EmptyCell(loc=" + this.loc + ", rect=" + this.rect + ", drawable=" + this.drawable + ", springAnimation=" + this.springAnimation + ", drawableAlphaProperty=" + this.drawableAlphaProperty + ", temporary=" + this.temporary + ")";
    }

    public /* synthetic */ EmptyCell(Loc loc) {
        this(loc, null, null, null, null, false);
    }
}
