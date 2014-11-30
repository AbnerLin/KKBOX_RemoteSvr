import com.sun.jna.platform.win32.WinDef.HWND;

public class RemoteController {

	private HWND target;

	public RemoteController() {

	}

	public String listener(String param) {
		if (!findTarget(Core.appClassName))
			return "Error01";
		else {
			_sendMessage(param);
		}

		return "Request Successed.";
	}

	private void _sendMessage(String key) {
		Core.user32.SendMessage(this.target,User32.WM_APPCOMMAND,0x00000000,Integer.parseInt(Core.getInstance()._getProperty(key)) << 16);
	}

	public boolean findTarget(String targetClassName) {
		this.target = Core.user32.FindWindow(targetClassName, null);
		if (this.target != null)
			return true;
		else
			return false;
	}

}
