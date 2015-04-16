package clipformatter;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import clipformatter.formatter.Formatter;
import clipformatter.formatter.FormatterFactory;

public class Main {

	public static void main(String[] args) {

		new Main().run();
	}

	public void run() {

		String clipboard = getClipboard();

		Formatter formatter = FormatterFactory.getFormatter(clipboard);
		String formatStr = formatter.format();

		setClipboard(formatStr);
	}

	private String getClipboard() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Clipboard clip = kit.getSystemClipboard();

		String clipboard = null;

		try {
			clipboard = (String) clip.getData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {
			onError("文字列をクリップボードにコピーしてください。");
		} catch (IOException e) {
			onError("IOException");
		}

		return clipboard;
	}

	private void setClipboard(String str) {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Clipboard clip = kit.getSystemClipboard();

		StringSelection ss = new StringSelection(str);
		clip.setContents(ss, ss);
	}

	private void onError(String errorMessage) {
		setClipboard("ERROR [" + errorMessage + "]");
	}
}
