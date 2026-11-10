package v3;

import b4.l;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import p3.j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements t3.c, d, Serializable {
    private final t3.c completion;

    public a(t3.c cVar) {
        this.completion = cVar;
    }

    public t3.c create(Object obj, t3.c cVar) {
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }

    public t3.c create(t3.c cVar) {
        l.e("completion", cVar);
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    public d getCallerFrame() {
        t3.c cVar = this.completion;
        if (cVar instanceof d) {
            return (d) cVar;
        }
        return null;
    }

    public final t3.c getCompletion() {
        return this.completion;
    }

    public StackTraceElement getStackTraceElement() {
        int iIntValue;
        String strC;
        Method method;
        Object objInvoke;
        Method method2;
        Object objInvoke2;
        e eVar = (e) getClass().getAnnotation(e.class);
        String str = null;
        if (eVar == null || eVar.v() < 1) {
            return null;
        }
        try {
            Field declaredField = getClass().getDeclaredField("label");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            iIntValue = (num != null ? num.intValue() : 0) - 1;
        } catch (Exception unused) {
            iIntValue = -1;
        }
        int i5 = iIntValue >= 0 ? eVar.l()[iIntValue] : -1;
        a1.b bVar = f.f6211b;
        a1.b bVar2 = f.f6210a;
        if (bVar == null) {
            try {
                a1.b bVar3 = new a1.b(Class.class.getDeclaredMethod("getModule", null), getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", null), getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", null));
                f.f6211b = bVar3;
                bVar = bVar3;
            } catch (Exception unused2) {
                f.f6211b = bVar2;
                bVar = bVar2;
            }
        }
        if (bVar != bVar2 && (method = (Method) bVar.f23d) != null && (objInvoke = method.invoke(getClass(), null)) != null && (method2 = (Method) bVar.f24e) != null && (objInvoke2 = method2.invoke(objInvoke, null)) != null) {
            Method method3 = (Method) bVar.f25f;
            Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, null) : null;
            if (objInvoke3 instanceof String) {
                str = (String) objInvoke3;
            }
        }
        if (str == null) {
            strC = eVar.c();
        } else {
            strC = str + '/' + eVar.c();
        }
        return new StackTraceElement(strC, eVar.m(), eVar.f(), i5);
    }

    public abstract Object invokeSuspend(Object obj);

    public void releaseIntercepted() {
    }

    @Override // t3.c
    public final void resumeWith(Object obj) {
        t3.c cVar = this;
        while (true) {
            a aVar = (a) cVar;
            t3.c cVar2 = aVar.completion;
            l.b(cVar2);
            try {
                obj = aVar.invokeSuspend(obj);
                if (obj == u3.a.f5838d) {
                    return;
                }
            } catch (Throwable th) {
                obj = j.r(th);
            }
            aVar.releaseIntercepted();
            if (!(cVar2 instanceof a)) {
                cVar2.resumeWith(obj);
                return;
            }
            cVar = cVar2;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Continuation at ");
        Object stackTraceElement = getStackTraceElement();
        if (stackTraceElement == null) {
            stackTraceElement = getClass().getName();
        }
        sb.append(stackTraceElement);
        return sb.toString();
    }
}
