package com.oplus.systemui.qs.base.widget.recyclerview;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.ViewTreeObserver;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PlaceHolder {
    private Drawable.Callback callback;
    private Function2 copyOrSetAction;
    private EmptyPlaceHolderHandler emptyPlaceHolderHandler;
    private final PlaceHolder$onAttachStateChangeListener$1 onAttachStateChangeListener;
    private Rect placeHolderBoundsToCell;
    private Drawable placeHolderDrawable;
    private final PlaceHolder$updateLastPlaceHolderBoundsToCell$1 updateLastPlaceHolderBoundsToCell;
    private final Rect lastPlaceHolderBoundsToCell = new Rect();
    private final Rect boundsDiff = new Rect();

    public PlaceHolder(Drawable drawable, Rect rect, Function2 function2) {
        this.placeHolderDrawable = drawable;
        this.placeHolderBoundsToCell = rect;
        this.copyOrSetAction = function2;
        PlaceHolder$onAttachStateChangeListener$1 placeHolder$onAttachStateChangeListener$1 = new PlaceHolder$onAttachStateChangeListener$1();
        placeHolder$onAttachStateChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.onAttachStateChangeListener = placeHolder$onAttachStateChangeListener$1;
        PlaceHolder$updateLastPlaceHolderBoundsToCell$1 placeHolder$updateLastPlaceHolderBoundsToCell$1 = new PlaceHolder$updateLastPlaceHolderBoundsToCell$1();
        placeHolder$updateLastPlaceHolderBoundsToCell$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.updateLastPlaceHolderBoundsToCell = placeHolder$updateLastPlaceHolderBoundsToCell$1;
    }

    public final Rect getBoundsDiff$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib() {
        return this.boundsDiff;
    }

    public final Drawable.Callback getCallback$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib() {
        return this.callback;
    }

    public final Function2 getCopyOrSetAction$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib() {
        return this.copyOrSetAction;
    }

    public final EmptyPlaceHolderHandler getEmptyPlaceHolderHandler$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib() {
        return this.emptyPlaceHolderHandler;
    }

    public final Rect getPlaceHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib() {
        return this.placeHolderBoundsToCell;
    }

    public final Drawable getPlaceHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib() {
        return this.placeHolderDrawable;
    }

    public final void setCallback$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(Drawable.Callback callback) {
        if (Intrinsics.areEqual(this.callback, callback)) {
            return;
        }
        this.callback = callback;
        Drawable drawable = this.placeHolderDrawable;
        if (drawable != null) {
            drawable.setCallback(callback);
        }
    }

    public final void setCopyOrSetAction$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(Function2 function2) {
        this.copyOrSetAction = function2;
    }

    public final void setEmptyPlaceHolderHandler$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(EmptyPlaceHolderHandler emptyPlaceHolderHandler) {
        RecyclerView hostRecyclerView;
        RecyclerView hostRecyclerView2;
        if (Intrinsics.areEqual(this.emptyPlaceHolderHandler, emptyPlaceHolderHandler)) {
            return;
        }
        EmptyPlaceHolderHandler emptyPlaceHolderHandler2 = this.emptyPlaceHolderHandler;
        if (emptyPlaceHolderHandler2 != null && (hostRecyclerView2 = emptyPlaceHolderHandler2.getHostRecyclerView()) != null) {
            hostRecyclerView2.removeOnAttachStateChangeListener(this.onAttachStateChangeListener);
        }
        this.emptyPlaceHolderHandler = emptyPlaceHolderHandler;
        if (emptyPlaceHolderHandler == null || (hostRecyclerView = emptyPlaceHolderHandler.getHostRecyclerView()) == null) {
            return;
        }
        hostRecyclerView.addOnAttachStateChangeListener(this.onAttachStateChangeListener);
    }

    public final void setPlaceHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(Rect rect) {
        this.placeHolderBoundsToCell = rect;
    }

    public final void setPlaceHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(Drawable drawable) {
        this.placeHolderDrawable = drawable;
    }

    public final void update(Drawable drawable, Rect rect, Function2 function2) {
        EmptyPlaceHolderHandler emptyPlaceHolderHandler;
        RecyclerView hostRecyclerView;
        ViewTreeObserver viewTreeObserver;
        RecyclerView hostRecyclerView2;
        ViewTreeObserver viewTreeObserver2;
        this.placeHolderBoundsToCell = rect;
        Rect rect2 = this.boundsDiff;
        if (rect != null) {
            int i = rect.left;
            Rect rect3 = this.lastPlaceHolderBoundsToCell;
            rect2.set(i - rect3.left, rect.top - rect3.top, rect.right - rect3.right, rect.bottom - rect3.bottom);
        } else {
            rect2.setEmpty();
        }
        EmptyPlaceHolderHandler emptyPlaceHolderHandler2 = this.emptyPlaceHolderHandler;
        if (emptyPlaceHolderHandler2 != null && (hostRecyclerView2 = emptyPlaceHolderHandler2.getHostRecyclerView()) != null && (viewTreeObserver2 = hostRecyclerView2.getViewTreeObserver()) != null) {
            viewTreeObserver2.removeOnPreDrawListener(this.updateLastPlaceHolderBoundsToCell);
        }
        EmptyPlaceHolderHandler emptyPlaceHolderHandler3 = this.emptyPlaceHolderHandler;
        if (emptyPlaceHolderHandler3 != null && (hostRecyclerView = emptyPlaceHolderHandler3.getHostRecyclerView()) != null && (viewTreeObserver = hostRecyclerView.getViewTreeObserver()) != null) {
            viewTreeObserver.addOnPreDrawListener(this.updateLastPlaceHolderBoundsToCell);
        }
        this.copyOrSetAction = function2;
        if (function2 != null && (emptyPlaceHolderHandler = this.emptyPlaceHolderHandler) != null) {
            emptyPlaceHolderHandler.updateDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(function2);
        }
        Drawable drawable2 = this.placeHolderDrawable;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.placeHolderDrawable = drawable;
        if (drawable != null) {
            drawable.setCallback(this.callback);
        }
        Drawable drawable3 = this.placeHolderDrawable;
        if (drawable3 != null) {
            drawable3.invalidateSelf();
        }
    }
}
