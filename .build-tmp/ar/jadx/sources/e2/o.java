package e2;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;
import c0.g1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class o implements InputConnection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g1 f2045a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public x.w f2046b;

    public o(x.w wVar, g1 g1Var) {
        this.f2045a = g1Var;
        this.f2046b = wVar;
    }

    public final void a(x.w wVar) {
        wVar.closeConnection();
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean beginBatchEdit() {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.beginBatchEdit();
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean clearMetaKeyStates(int i5) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.clearMetaKeyStates(i5);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final void closeConnection() {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            if (wVar != null) {
                a(wVar);
                this.f2046b = null;
            }
            this.f2045a.c(this);
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitCompletion(CompletionInfo completionInfo) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.commitCompletion(completionInfo);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i5, Bundle bundle) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.commitContent(inputContentInfo, i5, bundle);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitCorrection(CorrectionInfo correctionInfo) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.commitCorrection(correctionInfo);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitText(CharSequence charSequence, int i5) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.commitText(charSequence, i5);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingText(int i5, int i6) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.deleteSurroundingText(i5, i6);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingTextInCodePoints(int i5, int i6) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.deleteSurroundingTextInCodePoints(i5, i6);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean endBatchEdit() {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.b();
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean finishComposingText() {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.finishComposingText();
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final int getCursorCapsMode(int i5) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.getCursorCapsMode(i5);
        }
        return 0;
    }

    @Override // android.view.inputmethod.InputConnection
    public final ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i5) {
        x.w wVar = this.f2046b;
        return wVar != null ? wVar.getExtractedText(extractedTextRequest, i5) : new ExtractedText();
    }

    @Override // android.view.inputmethod.InputConnection
    public final Handler getHandler() {
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getSelectedText(int i5) {
        CharSequence selectedText;
        x.w wVar = this.f2046b;
        return (wVar == null || (selectedText = wVar.getSelectedText(i5)) == null) ? "" : selectedText;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getTextAfterCursor(int i5, int i6) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.getTextAfterCursor(i5, i6);
        }
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getTextBeforeCursor(int i5, int i6) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.getTextBeforeCursor(i5, i6);
        }
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean performContextMenuAction(int i5) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.performContextMenuAction(i5);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean performEditorAction(int i5) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.performEditorAction(i5);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean performPrivateCommand(String str, Bundle bundle) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.performPrivateCommand(str, bundle);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean reportFullscreenMode(boolean z2) {
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean requestCursorUpdates(int i5) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.requestCursorUpdates(i5);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean sendKeyEvent(KeyEvent keyEvent) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.sendKeyEvent(keyEvent);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setComposingRegion(int i5, int i6) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.setComposingRegion(i5, i6);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setComposingText(CharSequence charSequence, int i5) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.setComposingText(charSequence, i5);
        }
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setSelection(int i5, int i6) {
        x.w wVar = this.f2046b;
        if (wVar != null) {
            return wVar.setSelection(i5, i6);
        }
        return false;
    }
}
