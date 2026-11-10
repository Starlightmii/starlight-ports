package com.oplus.systemui.plugins.qs.proxy;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EmptyAreaGestureProxy extends GestureDetector implements View.OnTouchListener {
    public EmptyAreaGestureProxy(final ViewGroup viewGroup, final Function2 function2) {
        super(viewGroup.getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.oplus.systemui.plugins.qs.proxy.EmptyAreaGestureProxy.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public final boolean onDown(MotionEvent motionEvent) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public final void onLongPress(MotionEvent motionEvent) {
                Function2 function3;
                RecyclerView recyclerView = viewGroup;
                if ((!(recyclerView instanceof RecyclerView) || recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY()) == null) && (function3 = function2) != null) {
                }
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public final boolean onSingleTapUp(MotionEvent motionEvent) {
                Function2 function3;
                RecyclerView recyclerView = viewGroup;
                return (!(recyclerView instanceof RecyclerView) || recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY()) == null) && (function3 = function2) != null && ((Boolean) function3.invoke(Boolean.FALSE, motionEvent)).booleanValue();
            }
        });
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return onTouchEvent(motionEvent);
    }
}
