/*
 * Copyright (c) 2016. danlu.com Co.Ltd. All rights reserved.
 */

package com.danlu.lib;

final class OnItemSelectedRunnable implements Runnable {
    final WheelView loopView;

    OnItemSelectedRunnable(WheelView loopview) {
        loopView = loopview;
    }

    @Override
    public final void run() {
        loopView.onItemSelectedListener.onItemSelected(loopView,loopView.getCurrentItem());
    }
}
