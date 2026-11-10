package com.oplus.systemui.qs.base.widget.recyclerview;

import android.util.Log;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.systemui.qs.base.springtransition.DrawableAlphaProperty;
import com.oplus.systemui.qs.base.springtransition.DrawableAlphaPropertyKt;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EmptyPlaceHolderHandler$progressProperty$1 extends FloatPropertyCompat {
    public final Lazy progressMapper$delegate;
    public final /* synthetic */ EmptyPlaceHolderHandler this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EmptyPlaceHolderHandler$progressProperty$1(EmptyPlaceHolderHandler emptyPlaceHolderHandler) {
        super("progressProperty");
        this.this$0 = emptyPlaceHolderHandler;
        this.progressMapper$delegate = LazyKt__LazyJVMKt.lazy(new EmptyPlaceHolderHandler$$ExternalSyntheticLambda0(3));
    }

    public final EmptyPlaceHolderHandler.ProgressMapper getProgressMapper() {
        return (EmptyPlaceHolderHandler.ProgressMapper) this.progressMapper$delegate.getValue();
    }

    public final float getValue(Object obj) {
        return getProgressMapper().getCurrValue();
    }

    public final void setValue(float f) {
        COUISpringAnimation springAnimation;
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        if (getProgressMapper().getCurrValue() == fCoerceIn) {
            return;
        }
        getProgressMapper().setCurrValue(fCoerceIn);
        EmptyPlaceHolderHandler emptyPlaceHolderHandler = this.this$0;
        Collection collectionValues = emptyPlaceHolderHandler.emptyCells.values();
        ArrayList arrayList = new ArrayList();
        for (Object obj : collectionValues) {
            EmptyCell emptyCell = (EmptyCell) obj;
            if (emptyPlaceHolderHandler.getLocsNeedTogetherAnimation().contains(emptyCell.getLoc()) && ((springAnimation = emptyCell.getSpringAnimation()) == null || !((COUIDynamicAnimation) springAnimation).mRunning)) {
                arrayList.add(obj);
            }
        }
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i2 < size) {
            Object obj2 = arrayList.get(i2);
            i2++;
            EmptyCell emptyCell2 = (EmptyCell) obj2;
            DrawableAlphaProperty drawableAlphaProperty = emptyCell2.getDrawableAlphaProperty();
            if (drawableAlphaProperty != null) {
                getProgressMapper().mapTo(drawableAlphaProperty);
            } else if (emptyCell2.getDrawable() != null) {
                DrawableAlphaPropertyKt.getDRAWABLE_ALPHA().setValue(emptyCell2.getDrawable(), getProgressMapper().getCurrValue());
            }
        }
        DrawableAlphaPropertyKt.getDRAWABLE_ALPHA().setValue(emptyPlaceHolderHandler.getPlaceHolder().getPlaceHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(), getProgressMapper().getCurrValue());
        if (fCoerceIn == 0.0f) {
            emptyPlaceHolderHandler.emptyCells.entrySet().removeIf(setValue.4.INSTANCE);
        }
        if (getProgressMapper().getCurrValue() == getProgressMapper().getEndValue()) {
            List locsNeedTogetherAnimation = emptyPlaceHolderHandler.getLocsNeedTogetherAnimation();
            ArrayList arrayList2 = new ArrayList();
            Iterator it = locsNeedTogetherAnimation.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                EmptyCell emptyCell3 = (EmptyCell) emptyPlaceHolderHandler.emptyCells.get((Loc) next);
                if ((emptyCell3 != null ? emptyCell3.getDrawableAlphaProperty() : null) != null) {
                    arrayList2.add(next);
                }
            }
            int size2 = arrayList2.size();
            while (i < size2) {
                Object obj3 = arrayList2.get(i);
                i++;
                Loc loc = (Loc) obj3;
                EmptyCell emptyCell4 = (EmptyCell) emptyPlaceHolderHandler.emptyCells.get(loc);
                DrawableAlphaProperty drawableAlphaProperty2 = emptyCell4 != null ? emptyCell4.getDrawableAlphaProperty() : null;
                if ((drawableAlphaProperty2 != null && ((Number) drawableAlphaProperty2.getEndValue()).intValue() == 255 && getProgressMapper().getEndValue() == 1.0f) || (drawableAlphaProperty2 != null && ((Number) drawableAlphaProperty2.getEndValue()).intValue() == 0 && getProgressMapper().getEndValue() == 0.0f)) {
                    emptyCell4.setDrawableAlphaProperty(null);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "EmptyPlaceHolderHandler-->".concat("[progressProperty.setValue] remove drawableAlphaProperty " + loc + " property = " + drawableAlphaProperty2.getStartValue() + "-" + drawableAlphaProperty2.getValue() + "-" + drawableAlphaProperty2.getEndValue()));
                    }
                }
            }
        }
    }

    public final /* bridge */ /* synthetic */ void setValue(Object obj, float f) {
        setValue(f);
    }
}
