package com.oplus.systemui.plugins.qs.editmodeswitch;

import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import androidx.core.util.SparseArrayKt;
import com.oplus.systemui.qs.base.springtransition.IIntTarget;
import com.oplus.systemui.qs.base.springtransition.TransitionProperty;
import com.oplus.systemui.qs.base.widget.recyclerview.ViewWithSizeChange;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TransitionPropertiesCache {
    public static final Companion Companion = new Companion();
    private static final String TAG = "TransitionPropertiesMap";
    private SparseArray<TransitionProperty> propertyCache = new SparseArray<>();
    private final Map<Class<?>, Constructor<?>> constructorCache = new LinkedHashMap();

    public final void destroy() {
        this.propertyCache.clear();
    }

    public final <T extends TransitionProperty> TransitionProperty fetchTransitionProperty(Object obj, Class<T> cls) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        Class<?> cls2;
        int iHashCode = TransitionProperty.Companion.hashCode(cls, obj);
        TransitionProperty transitionProperty = this.propertyCache.get(iHashCode);
        if (transitionProperty != null) {
            return transitionProperty;
        }
        Constructor<T> constructor = (Constructor) this.constructorCache.get(cls);
        if (constructor == null) {
            if (obj instanceof View) {
                cls2 = View.class;
            } else if (obj instanceof Drawable) {
                cls2 = Drawable.class;
            } else if (obj instanceof IIntTarget) {
                cls2 = IIntTarget.class;
            } else {
                cls2 = obj instanceof ViewWithSizeChange ? ViewWithSizeChange.class : View.class;
            }
            constructor = cls.getConstructor(cls2);
            this.constructorCache.put(cls, constructor);
        }
        T tNewInstance = constructor.newInstance(obj);
        this.propertyCache.put(iHashCode, tNewInstance);
        return tNewInstance;
    }

    public final Iterator<TransitionProperty> getProperties() {
        return SparseArrayKt.valueIterator(this.propertyCache);
    }

    public final void refreshCacheWith(SparseArray<TransitionProperty> sparseArray) {
        this.propertyCache = sparseArray;
    }
}
