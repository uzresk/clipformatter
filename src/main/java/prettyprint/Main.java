package prettyprint;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import prettyprint.formatter.Formatter;
import prettyprint.formatter.FormatterException;

public class Main {

	public static void main(String[] args) {

		new Main().run();
	}

	public void run() {

		String formatStr = null;
		String clipboard = null;
		try {
			clipboard = getClipboard();
			if (clipboard == null || "".equals(clipboard)) {
				return;
			}
			formatStr = Formatter.generate(clipboard).format();

		} catch (FormatterException e) {
			formatStr = e.getMessage();
		} catch (RuntimeException e) {
			formatStr = "can't format [" + clipboard + "]";
		}

		System.out.println(formatStr);

		setClipboard(formatStr);
	}

	private String getClipboard() throws FormatterException {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Clipboard clip = kit.getSystemClipboard();

		String clipboard = null;

		try {
			clipboard = (String) clip.getData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {
			throw new FormatterException("String only");
		} catch (IOException e) {
			throw new FormatterException("IOException");
		}

		return clipboard;
	}

	private void setClipboard(String str) {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Clipboard clip = kit.getSystemClipboard();

		StringSelection ss = new StringSelection(str);
		clip.setContents(ss, ss);
	}

}
