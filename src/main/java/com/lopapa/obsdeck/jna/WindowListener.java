package com.lopapa.obsdeck.jna;
import com.sun.jna.Native;
import com.sun.jna.PointerType;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;

import java.util.Observable;

public class WindowListener extends Observable implements Runnable {
    //TODO: Every once in a while verify that the current window is actually the right scene.
    private int delay = 1000; //TODO: Implement delay acc, if you tab switch check every n ms for the next m seconds. Meaning that you may have switched and then back again.
    private String lastWindow = "";
    private WindowListener.User32 INSTANCE;
    public WindowListener(){
        INSTANCE = Native.load("user32", User32.class);
    }
    public String getLastWindow() {
        return lastWindow;
    }
    public void run(){
        try {
            while(true){
                Thread.sleep(delay);
                PointerType hwnd = INSTANCE.GetForegroundWindow(); // then you can call it!
                byte[] windowText = new byte[512];
                INSTANCE.GetWindowTextA(hwnd, windowText, 512);
                String newString = Native.toString(windowText);
                if(newString != null && newString.equalsIgnoreCase(this.lastWindow)){
                    continue;
                }

                this.lastWindow = newString;
                setChanged();
                notifyObservers(this.lastWindow);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public interface User32 extends StdCallLibrary {
        WinDef.HWND GetForegroundWindow();
        int GetWindowTextA(PointerType hWnd, byte[] lpString, int nMaxCount);
    }
    public static void main(String[] args) {
        new Thread(new WindowListener()).start();
    }
}
