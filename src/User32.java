import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.W32APIOptions;

public interface User32 extends W32APIOptions {
	int SW_SHOW = 1;
	int WM_APPCOMMAND = 0x0319;
	
	User32 INSTANCE = (User32) Native.loadLibrary("User32.dll", User32.class,
			W32APIOptions.DEFAULT_OPTIONS);

	boolean ShowWindow(HWND hWnd, int nCmdShow);
	boolean SetForegroundWindow(HWND hWnd);
	boolean SendMessage(HWND target, int Msg, int wParam, int lParam);
	boolean GetWindowText(HWND target, char[] lpString, int nMaxCount);
	int GetWindowTextLength(HWND target);

	
	HWND FindWindow(String winClass, String AppName);

}
