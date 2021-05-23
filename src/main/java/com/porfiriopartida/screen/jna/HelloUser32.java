package com.porfiriopartida.screen.jna;

import com.sun.jna.Native;
import com.sun.jna.PointerType;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;


public class HelloUser32 {
    public interface User32 extends StdCallLibrary {
        WinDef.HWND GetForegroundWindow();
        int GetWindowTextA(PointerType hWnd, byte[] lpString, int nMaxCount);
    }

    public static void main(String[] args) {
        // Optional: wraps every call to the native library in a
        // synchronized block, limiting native calls to one at a time
        User32 INSTANCE = (User32)
                Native.load("user32", User32.class);
        WinDef.HWND desktopWindow = INSTANCE.GetForegroundWindow();
        System.out.println(desktopWindow.toString());
        PointerType hwnd = INSTANCE.GetForegroundWindow(); // then you can call it!
        byte[] windowText = new byte[512];
        INSTANCE.GetWindowTextA(hwnd, windowText, 512);
        System.out.println(Native.toString(windowText));
    }
}
