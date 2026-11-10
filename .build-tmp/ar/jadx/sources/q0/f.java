package q0;

import b4.l;
import java.util.ArrayList;
import java.util.List;
import p3.p;
import q3.w;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f extends RuntimeException {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f5092d;

    public f(a aVar) {
        this.f5092d = aVar;
        if (aVar.a()) {
            return;
        }
        ArrayList arrayListK = p.k(aVar);
        int size = arrayListK.size();
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[size];
        for (int i5 = 0; i5 < size; i5++) {
            stackTraceElementArr[i5] = new StackTraceElement("$$compose", "m$" + ((b) arrayListK.get(i5)).f5087a, "SourceFile", 1);
        }
        setStackTrace(stackTraceElementArr);
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        a aVar = this.f5092d;
        if (!aVar.a()) {
            return "Composition stack when thrown:";
        }
        StringBuilder sb = new StringBuilder("Composition stack when thrown:\n");
        int i5 = 0;
        if (aVar.a()) {
            r3.b bVar = new r3.b(10);
            List list = aVar.f5086a;
            l.e("<this>", list);
            w wVar = new w(list);
            int iA = wVar.a();
            for (int i6 = 0; i6 < iA; i6++) {
                ((b) wVar.get(i6)).getClass();
            }
            r3.b bVarM = p3.j.m(bVar);
            l.e("<this>", bVarM);
            w wVar2 = new w(bVarM);
            int iA2 = wVar2.a();
            while (i5 < iA2) {
                String str = (String) wVar2.get(i5);
                sb.append("\tat ");
                sb.append(str);
                sb.append('\n');
                i5++;
            }
        } else {
            ArrayList arrayListK = p.k(aVar);
            int size = arrayListK.size();
            while (i5 < size) {
                b bVar2 = (b) arrayListK.get(i5);
                sb.append("\tat $$compose.m$");
                sb.append(bVar2.f5087a);
                sb.append("(SourceFile:1)\n");
                i5++;
            }
        }
        String string = sb.toString();
        l.d("toString(...)", string);
        return string;
    }
}
