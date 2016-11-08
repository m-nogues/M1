/*
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs. If your program is a subroutine library, you may
 * consider it more useful to permit linking proprietary applications with the
 * library. If this is what you want to do, use the GNU Lesser General Public
 * License instead of this License. But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 * @author Maël Nogues mael.nogues@outlook.com
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import engine.Engine;
import engine.EngineImpl;

/**
 * The Class EngineImplTest.
 */
public class EngineImplTest {

	/** The engine. */
	private Engine engine;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		engine = new EngineImpl();
	}

	/**
	 * Test empty cut.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testEmptyCut() throws Exception {
		final String clipboard = engine.getClipboard();
		engine.cut();
		assertEquals(clipboard, engine.getClipboard());
	}

	/**
	 * Test valid selection cut.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testValidSelectionCut() throws Exception {
		final String toInsert = "Test data sample", expectedClipboard = "data", expectedContent = "Test  sample";
		final int newSelectionStart = 5, newSelectionEnd = 9;
		engine.insertText(toInsert);
		engine.changeSelection(newSelectionStart, newSelectionEnd);
		engine.cut();
		assertEquals(expectedClipboard, engine.getClipboard());
		assertEquals(expectedContent, engine.getContent());
	}

	/**
	 * Test empty copy.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testEmptyCopy() throws Exception {
		final String clipboard = engine.getClipboard();
		engine.copy();
		assertEquals(clipboard, engine.getClipboard());
	}

	/**
	 * Test valid selection copy.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testValidSelectionCopy() throws Exception {
		final String toInsert = "Test data sample", expectedClipboard = "data", expectedContent = toInsert;
		final int newSelectionStart = 5, newSelectionEnd = 9;
		engine.insertText(toInsert);
		engine.changeSelection(newSelectionStart, newSelectionEnd);
		engine.copy();
		assertEquals(expectedClipboard, engine.getClipboard());
		assertEquals(expectedContent, engine.getContent());
	}

	/**
	 * Test empty paste.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testEmptyPaste() throws Exception {
		final String content = engine.getContent();
		engine.paste();
		assertEquals(content, engine.getContent());
	}

	/**
	 * Test empty paste with valid selection.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testEmptyPasteWithValidSelection() throws Exception {
		final String toInsert = "Test data sample", expectedContent = "Test  sample";
		final int newSelectionStart = 5, newSelectionEnd = 9;
		engine.insertText(toInsert);
		engine.changeSelection(newSelectionStart, newSelectionEnd);
		engine.paste();
		assertEquals(expectedContent, engine.getContent());
	}

	/**
	 * Test copy paste with valid selections.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testCopyPasteWithValidSelections() throws Exception {
		final String toInsert = "Test data sample", expectedContent = "data data sample";
		final int newSelectionStart = 5, newSelectionEnd = 9, newSelectionStart2 = 0, newSelectionEnd2 = 4;
		engine.insertText(toInsert);
		engine.changeSelection(newSelectionStart, newSelectionEnd);
		engine.copy();
		engine.changeSelection(newSelectionStart2, newSelectionEnd2);
		engine.paste();
		assertEquals(expectedContent, engine.getContent());
	}

	/**
	 * Test insert text.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testInsertText() throws Exception {
		final String toInsert = "Test data sample";
		engine.insertText(toInsert);
		assertEquals(toInsert, engine.getContent());
	}

	/**
	 * Test insert text with valid selection.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testInsertTextWithValidSelection() throws Exception {
		final String toInsert = "Test data sample", toInsert2 = "my new data",
				expectedContent = "Test my new data sample";
		final int newSelectionStart = 5, newSelectionEnd = 9;
		engine.insertText(toInsert);
		engine.changeSelection(newSelectionStart, newSelectionEnd);
		engine.insertText(toInsert2);
		assertEquals(expectedContent, engine.getContent());
	}

	/**
	 * Test get content.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetContent() throws Exception {
		assertTrue(engine.getContent().equals(""));
	}

	/**
	 * Test valid change selection.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testValidChangeSelection() throws Exception {
		final String toInsert = "Test data sample";
		final int newSelectionStart = 3, newSelectionEnd = 5;
		engine.insertText(toInsert);
		engine.changeSelection(newSelectionStart, newSelectionEnd);
		assertEquals(newSelectionStart, engine.getSelectionStart());
		assertEquals(newSelectionEnd, engine.getSelectionEnd());
	}

	/**
	 * Test invalid change selection negative start out of bonds end.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testInvalidChangeSelectionNegativeStartOutOfBondsEnd() throws Exception {
		final String toInsert = "Test data sample";
		final int newSelectionStart = -8, newSelectionEnd = 25, expectedSelectionStart = 0,
				expectedSelectionEnd = toInsert.length();
		engine.insertText(toInsert);
		engine.changeSelection(newSelectionStart, newSelectionEnd);

		assertEquals(expectedSelectionStart, engine.getSelectionStart());
		assertEquals(expectedSelectionEnd, engine.getSelectionEnd());
	}

	/**
	 * Test invalid change selection start greater than end.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testInvalidChangeSelectionStartGreaterThanEnd() throws Exception {
		final String toInsert = "Test data sample";
		final int newSelectionStart = 5, newSelectionEnd = 3, expectedSelectionStart = newSelectionEnd,
				expectedSelectionEnd = newSelectionStart;
		engine.insertText(toInsert);
		engine.changeSelection(newSelectionStart, newSelectionEnd);
		assertEquals(expectedSelectionStart, engine.getSelectionStart());
		assertEquals(expectedSelectionEnd, engine.getSelectionEnd());
	}
}