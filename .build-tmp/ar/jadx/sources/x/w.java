package x;

import android.R;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.HandwritingGesture;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;
import android.view.inputmethod.PreviewableHandwritingGesture;
import io.github.libxposed.service.IXposedService;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.function.IntConsumer;
import q1.o2;
import v.o0;
import y1.g0;
import z.e0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w implements InputConnection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a1.d f6515a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f6516b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o0 f6517c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e0 f6518d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final o2 f6519e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f6520f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public e2.x f6521g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f6522h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f6523i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayList f6524j = new ArrayList();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f6525k = true;

    public w(e2.x xVar, a1.d dVar, boolean z2, o0 o0Var, e0 e0Var, o2 o2Var) {
        this.f6515a = dVar;
        this.f6516b = z2;
        this.f6517c = o0Var;
        this.f6518d = e0Var;
        this.f6519e = o2Var;
        this.f6521g = xVar;
    }

    public final void a(e2.i iVar) {
        this.f6520f++;
        try {
            this.f6524j.add(iVar);
        } finally {
            b();
        }
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [a4.c, b4.m] */
    public final boolean b() {
        int i5 = this.f6520f - 1;
        this.f6520f = i5;
        if (i5 == 0) {
            ArrayList arrayList = this.f6524j;
            if (!arrayList.isEmpty()) {
                ((u) this.f6515a.f31e).f6503c.c(q3.l.g0(arrayList));
                arrayList.clear();
            }
        }
        return this.f6520f > 0;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean beginBatchEdit() {
        boolean z2 = this.f6525k;
        if (!z2) {
            return z2;
        }
        this.f6520f++;
        return true;
    }

    public final void c(int i5) {
        sendKeyEvent(new KeyEvent(0, i5));
        sendKeyEvent(new KeyEvent(1, i5));
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean clearMetaKeyStates(int i5) {
        boolean z2 = this.f6525k;
        if (z2) {
            return false;
        }
        return z2;
    }

    @Override // android.view.inputmethod.InputConnection
    public final void closeConnection() {
        this.f6524j.clear();
        this.f6520f = 0;
        this.f6525k = false;
        ArrayList arrayList = ((u) this.f6515a.f31e).f6510j;
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            if (b4.l.a(((WeakReference) arrayList.get(i5)).get(), this)) {
                arrayList.remove(i5);
                return;
            }
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitCompletion(CompletionInfo completionInfo) {
        boolean z2 = this.f6525k;
        if (z2) {
            return false;
        }
        return z2;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i5, Bundle bundle) {
        boolean z2 = this.f6525k;
        if (z2) {
            return false;
        }
        return z2;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitCorrection(CorrectionInfo correctionInfo) {
        boolean z2 = this.f6525k;
        return z2 ? this.f6516b : z2;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitText(CharSequence charSequence, int i5) {
        boolean z2 = this.f6525k;
        if (z2) {
            a(new e2.a(String.valueOf(charSequence), i5));
        }
        return z2;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingText(int i5, int i6) {
        boolean z2 = this.f6525k;
        if (!z2) {
            return z2;
        }
        a(new e2.g(i5, i6));
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingTextInCodePoints(int i5, int i6) {
        boolean z2 = this.f6525k;
        if (!z2) {
            return z2;
        }
        a(new e2.h(i5, i6));
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean endBatchEdit() {
        return b();
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean finishComposingText() {
        boolean z2 = this.f6525k;
        if (!z2) {
            return z2;
        }
        a(new e2.k());
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final int getCursorCapsMode(int i5) {
        e2.x xVar = this.f6521g;
        return TextUtils.getCapsMode(xVar.f2062a.f6759d, g0.e(xVar.f2063b), i5);
    }

    @Override // android.view.inputmethod.InputConnection
    public final ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i5) {
        boolean z2 = (i5 & 1) != 0;
        this.f6523i = z2;
        if (z2) {
            this.f6522h = extractedTextRequest != null ? extractedTextRequest.token : 0;
        }
        return p3.j.j(this.f6521g);
    }

    @Override // android.view.inputmethod.InputConnection
    public final Handler getHandler() {
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getSelectedText(int i5) {
        if (g0.b(this.f6521g.f2063b)) {
            return null;
        }
        return a.a.z(this.f6521g).f6759d;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getTextAfterCursor(int i5, int i6) {
        return a.a.A(this.f6521g, i5).f6759d;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getTextBeforeCursor(int i5, int i6) {
        return a.a.B(this.f6521g, i5).f6759d;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean performContextMenuAction(int i5) {
        boolean z2 = this.f6525k;
        if (z2) {
            z2 = false;
            switch (i5) {
                case R.id.selectAll:
                    a(new e2.w(0, this.f6521g.f2062a.f6759d.length()));
                    break;
                case R.id.cut:
                    c(277);
                    return false;
                case R.id.copy:
                    c(278);
                    return false;
                case R.id.paste:
                    c(279);
                    return false;
                default:
                    return false;
            }
        }
        return z2;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean performEditorAction(int i5) {
        int i6;
        boolean z2 = this.f6525k;
        if (z2) {
            z2 = true;
            if (i5 != 0) {
                switch (i5) {
                    case 2:
                        i6 = 2;
                        break;
                    case 3:
                        i6 = 3;
                        break;
                    case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                        i6 = 4;
                        break;
                    case 5:
                        i6 = 6;
                        break;
                    case 6:
                        i6 = 7;
                        break;
                    case 7:
                        i6 = 5;
                        break;
                    default:
                        Log.w("RecordingIC", "IME sends unsupported Editor Action: " + i5);
                        i6 = 1;
                        break;
                }
            } else {
                i6 = 1;
            }
            ((u) this.f6515a.f31e).f6504d.c(new e2.m(i6));
        }
        return z2;
    }

    @Override // android.view.inputmethod.InputConnection
    public final void performHandwritingGesture(HandwritingGesture handwritingGesture, Executor executor, IntConsumer intConsumer) {
        if (Build.VERSION.SDK_INT >= 34) {
            e.f6469a.a(this.f6517c, this.f6518d, handwritingGesture, this.f6519e, executor, intConsumer, new w1.l(1, this));
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean performPrivateCommand(String str, Bundle bundle) {
        boolean z2 = this.f6525k;
        if (z2) {
            return true;
        }
        return z2;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean previewHandwritingGesture(PreviewableHandwritingGesture previewableHandwritingGesture, CancellationSignal cancellationSignal) {
        if (Build.VERSION.SDK_INT < 34) {
            return false;
        }
        return e.f6469a.b(this.f6517c, this.f6518d, previewableHandwritingGesture, cancellationSignal);
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean reportFullscreenMode(boolean z2) {
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:46:0x0065 A[Catch: all -> 0x006f, TryCatch #0 {all -> 0x006f, blocks: (B:44:0x005b, B:46:0x0065, B:48:0x006b, B:51:0x0071), top: B:57:0x005b }] */
    /* JADX WARN: Code duplicated, block: B:48:0x006b A[Catch: all -> 0x006f, TryCatch #0 {all -> 0x006f, blocks: (B:44:0x005b, B:46:0x0065, B:48:0x006b, B:51:0x0071), top: B:57:0x005b }] */
    /* JADX WARN: Code duplicated, block: B:57:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // android.view.inputmethod.InputConnection
    public final boolean requestCursorUpdates(int i5) {
        boolean z2;
        boolean z3;
        boolean z4;
        r rVar;
        boolean z5 = this.f6525k;
        if (!z5) {
            return z5;
        }
        boolean z6 = false;
        boolean z7 = (i5 & 1) != 0;
        boolean z8 = (i5 & 2) != 0;
        int i6 = Build.VERSION.SDK_INT;
        if (i6 >= 33) {
            z3 = (i5 & 16) != 0;
            z4 = (i5 & 8) != 0;
            boolean z9 = (i5 & 4) != 0;
            if (i6 >= 34 && (i5 & 32) != 0) {
                z6 = true;
            }
            if (z3 || z4 || z9 || z6) {
                z2 = z6;
                z6 = z9;
            } else {
                if (i6 >= 34) {
                    z2 = true;
                    z6 = true;
                } else {
                    z2 = z6;
                    z6 = true;
                }
                z3 = z6;
            }
            rVar = ((u) this.f6515a.f31e).f6513m;
            synchronized (rVar.f6484c) {
                try {
                    rVar.f6487f = z3;
                    rVar.f6488g = z4;
                    rVar.f6489h = z6;
                    rVar.f6490i = z2;
                    if (z7) {
                        rVar.f6486e = true;
                        if (rVar.f6491j != null) {
                            rVar.a();
                        }
                    }
                    rVar.f6485d = z8;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return true;
        }
        z2 = false;
        z3 = true;
        z4 = z3;
        rVar = ((u) this.f6515a.f31e).f6513m;
        synchronized (rVar.f6484c) {
            rVar.f6487f = z3;
            rVar.f6488g = z4;
            rVar.f6489h = z6;
            rVar.f6490i = z2;
            if (z7) {
                rVar.f6486e = true;
                if (rVar.f6491j != null) {
                    rVar.a();
                }
            }
            rVar.f6485d = z8;
            return true;
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, p3.d] */
    @Override // android.view.inputmethod.InputConnection
    public final boolean sendKeyEvent(KeyEvent keyEvent) {
        boolean z2 = this.f6525k;
        if (!z2) {
            return z2;
        }
        ((BaseInputConnection) ((u) this.f6515a.f31e).f6511k.getValue()).sendKeyEvent(keyEvent);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setComposingRegion(int i5, int i6) {
        boolean z2 = this.f6525k;
        if (z2) {
            a(new e2.u(i5, i6));
        }
        return z2;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setComposingText(CharSequence charSequence, int i5) {
        boolean z2 = this.f6525k;
        if (z2) {
            a(new e2.v(String.valueOf(charSequence), i5));
        }
        return z2;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setSelection(int i5, int i6) {
        boolean z2 = this.f6525k;
        if (!z2) {
            return z2;
        }
        a(new e2.w(i5, i6));
        return true;
    }
}
