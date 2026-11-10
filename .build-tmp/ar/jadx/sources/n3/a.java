package n3;

import android.os.Parcel;
import android.os.Parcelable;
import j.f;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f4256a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f4257b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final f f4258c;

    public a(f fVar, f fVar2, f fVar3) {
        this.f4256a = fVar;
        this.f4257b = fVar2;
        this.f4258c = fVar3;
    }

    public abstract b a();

    public final Class b(Class cls) throws ClassNotFoundException {
        String name = cls.getName();
        f fVar = this.f4258c;
        Class cls2 = (Class) fVar.get(name);
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(cls.getPackage().getName() + "." + cls.getSimpleName() + "Parcelizer", false, cls.getClassLoader());
        fVar.put(cls.getName(), cls3);
        return cls3;
    }

    public final Method c(String str) throws NoSuchMethodException {
        f fVar = this.f4256a;
        Method method = (Method) fVar.get(str);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, a.class.getClassLoader()).getDeclaredMethod("read", a.class);
        fVar.put(str, declaredMethod);
        return declaredMethod;
    }

    public final Method d(Class cls) throws NoSuchMethodException, ClassNotFoundException {
        String name = cls.getName();
        f fVar = this.f4257b;
        Method method = (Method) fVar.get(name);
        if (method != null) {
            return method;
        }
        Class clsB = b(cls);
        System.currentTimeMillis();
        Method declaredMethod = clsB.getDeclaredMethod("write", cls, a.class);
        fVar.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public abstract boolean e(int i5);

    public final Parcelable f(Parcelable parcelable, int i5) {
        if (!e(i5)) {
            return parcelable;
        }
        return ((b) this).f4260e.readParcelable(b.class.getClassLoader());
    }

    public final c g() {
        String string = ((b) this).f4260e.readString();
        if (string == null) {
            return null;
        }
        try {
            return (c) c(string).invoke(null, a());
        } catch (ClassNotFoundException e5) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e5);
        } catch (IllegalAccessException e6) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e6);
        } catch (NoSuchMethodException e7) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e7);
        } catch (InvocationTargetException e8) {
            if (e8.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e8.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e8);
        }
    }

    public abstract void h(int i5);

    public final void i(c cVar) {
        if (cVar == null) {
            ((b) this).f4260e.writeString(null);
            return;
        }
        try {
            ((b) this).f4260e.writeString(b(cVar.getClass()).getName());
            b bVarA = a();
            try {
                d(cVar.getClass()).invoke(null, cVar, bVarA);
                Parcel parcel = bVarA.f4260e;
                int i5 = bVarA.f4264i;
                if (i5 >= 0) {
                    int i6 = bVarA.f4259d.get(i5);
                    int iDataPosition = parcel.dataPosition();
                    parcel.setDataPosition(i6);
                    parcel.writeInt(iDataPosition - i6);
                    parcel.setDataPosition(iDataPosition);
                }
            } catch (ClassNotFoundException e5) {
                throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e5);
            } catch (IllegalAccessException e6) {
                throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e6);
            } catch (NoSuchMethodException e7) {
                throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e7);
            } catch (InvocationTargetException e8) {
                if (!(e8.getCause() instanceof RuntimeException)) {
                    throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e8);
                }
                throw ((RuntimeException) e8.getCause());
            }
        } catch (ClassNotFoundException e9) {
            throw new RuntimeException(cVar.getClass().getSimpleName().concat(" does not have a Parcelizer"), e9);
        }
    }
}
