package w0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final n f6254b = new n();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final n f6255c = new n();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h0.e f6256a = new h0.e(new o[16]);

    public final boolean a(a4.c cVar) {
        if (this == f6254b) {
            throw new IllegalStateException("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n");
        }
        if (this == f6255c) {
            throw new IllegalStateException("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n");
        }
        h0.e eVar = this.f6256a;
        if (!eVar.k()) {
            throw new IllegalStateException("\n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
        }
        int i5 = eVar.f2578f;
        if (i5 <= 0) {
            return false;
        }
        Object[] objArr = eVar.f2576d;
        int i6 = 0;
        boolean z2 = false;
        do {
            r0.o oVar = ((r0.o) ((o) objArr[i6])).f5681d;
            if (!oVar.f5693p) {
                throw new IllegalStateException("visitChildren called on an unattached node");
            }
            h0.e eVar2 = new h0.e(new r0.o[16]);
            r0.o oVar2 = oVar.f5686i;
            if (oVar2 == null) {
                p1.f.b(eVar2, oVar);
            } else {
                eVar2.b(oVar2);
            }
            while (eVar2.k()) {
                r0.o oVarE = (r0.o) eVar2.m(eVar2.f2578f - 1);
                if ((oVarE.f5684g & 1024) == 0) {
                    p1.f.b(eVar2, oVarE);
                } else {
                    while (oVarE != null) {
                        if ((oVarE.f5683f & 1024) != 0) {
                            h0.e eVar3 = null;
                            while (oVarE != null) {
                                if (oVarE instanceof r) {
                                    r rVar = (r) oVarE;
                                    if (rVar.y0().f6242a ? ((Boolean) cVar.c(rVar)).booleanValue() : d.i(rVar, 7, cVar)) {
                                        z2 = true;
                                        break;
                                    }
                                } else if (((oVarE.f5683f & 1024) != 0) && (oVarE instanceof p1.m)) {
                                    int i7 = 0;
                                    for (r0.o oVar3 = ((p1.m) oVarE).f4921r; oVar3 != null; oVar3 = oVar3.f5686i) {
                                        if ((oVar3.f5683f & 1024) != 0) {
                                            i7++;
                                            if (i7 == 1) {
                                                oVarE = oVar3;
                                            } else {
                                                if (eVar3 == null) {
                                                    eVar3 = new h0.e(new r0.o[16]);
                                                }
                                                if (oVarE != null) {
                                                    eVar3.b(oVarE);
                                                    oVarE = null;
                                                }
                                                eVar3.b(oVar3);
                                            }
                                        }
                                    }
                                    if (i7 == 1) {
                                    }
                                }
                                oVarE = p1.f.e(eVar3);
                            }
                            break;
                        }
                        oVarE = oVarE.f5686i;
                    }
                }
            }
            i6++;
        } while (i6 < i5);
        return z2;
    }
}
