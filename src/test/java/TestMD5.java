package test.java;

import java.net.URLEncoder;
import java.util.Collections;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class TestMD5 {
	@DataProvider(name = "testTextParamPositive")
	public static String[][] queryParamText() {
		return new String[][] {
			// test_name, input_text_param, expected_original, expected_md5
			{"EMPTY_STRING", "", "", "d41d8cd98f00b204e9800998ecf8427e"},
			{"WHITESPACE", " ", "", "d41d8cd98f00b204e9800998ecf8427e"},
			{"WHITESPACE_AFTER", "after ", "after", "632a2406bbcbcd553eec45ac14b40a0a"},
			// {"WHITESPACE_BEFORE", " before", " before", "a2b2185af67a3eb5f6e71fb17377bc9a"}, 			400 bug?
			// {"WHITE_SYMBOLS", " \\n\\t\\r", " \\\\n\\\\t\\\\r", "12d9772da7e7a1628dcb95e3ca9d107d"}, 	400 bug?
			{"DIGITS", "0123456789", "0123456789", "781e5e245d69b566979b86e28d23f2c7"},
			{"CHARS", "qooqoojambo", "qooqoojambo", "9fc26f176fade3d4c29d4a34ac2cccf9"},
			{"MIX_CHARS_DIGITS", "PhoneNumber911", "PhoneNumber911", "0ee5d9c700a9426e4acf08771295af77"},
			// {"RUSSIAN", "ЙОД, МЁД И МОЛОКО", "???, ??? ? ??????", "a69d630a211a878b3178553447877a93"}, 	400 bug?
			// {"LATIN_DIACRITICS", "ÄäǞǟĄ̈ą̈B̈b̈C̈c̈ËëḦḧÏïḮḯK̈k̈M̈m̈N̈n̈ÖöȪȫǪ̈ǫ̈ṎṏP̈p̈Q̈q̈Q̣̈q̣̈S̈s̈T̈ẗÜüǕǖǗǘǙǚǛǜṲṳṺṻᴞV̈v̈ẄẅẌẍŸÿZ̈z̈", 	500 bug!
			//	"Ää??????B?b?C?c?Ëë??Ïï??K?k?M?m?N?n?Öö????????P?p?Q?q?Q??q??S?s?T??Üü?????????????V?v??????ÿZ?z?",
			//	"b49c2b64603e36dd282f6b475cc665c5"},
			{"GREEK_DIACRITICS", "ΪϊῒΐῗΫϋῢΰῧϔ", "???????????", "106d6a60386b448f80d401758c203f54"}, // Unicode is not supported on web service
			{"CYRILLIC_DIACRITICS", "ӒӓЁёӚӛӜӝӞӟӤӥЇїӦӧӪӫӰӱӴӵӸӹӬӭ", "??????????????????????????", // Unicode is not supported on web service
				"60ed2e046eb37a25217c7c6809651eb9"},
			{"CHINESE", "我想一個胡蘿蔔", "???????", "980017891ff67cf8a20f23aa810e7b5a"}, // Unicode is not supported on web service
			{"SHARP", "#", "", "d41d8cd98f00b204e9800998ecf8427e"},
			{"SPECIAL_SYMBOLS", "!@#$%^&*()_+?:;№\"`~", "!@", "4ebcae6550482e3f4065ad9df472e5cb"}, // Bug. # symbol cuts input string
		};
	}
	@Test(dataProvider = "testTextParamPositive")
	public void testPositive(String name, String inputTextParam, String expectedOriginal, String expectedMD5) throws Exception {
		JSONObject expectedJSON = new JSONObject(String.format("{'original': '%s', 'md5': '%s'}", expectedOriginal, expectedMD5));
		Request request = new Request(inputTextParam);
		
		assertEquals(request.getResponseCode(), 200);
		assertEquals(request.getResponseJSON().toString(), expectedJSON.toString());
	}
	
	@DataProvider(name = "testTextParamPositiveURLEncoded")
	public static String[][] queryParamTextURLEncoded() {
		return new String[][] {
			// test_name, input_text_param, expected_original, expected_md5
			{"EMPTY_STRING", "", "", "d41d8cd98f00b204e9800998ecf8427e"},
			{"WHITESPACE", " ", " ", "7215ee9c7d9dc229d2921a40e899ec5f"},
			{"WHITESPACE_AFTER", "after ", "after ", "9379ba030842032613368e5952fea2a5"},
			{"WHITESPACE_BEFORE", " before", " before", "a2b2185af67a3eb5f6e71fb17377bc9a"},
			{"WHITE_SYMBOLS", " \\n\\t\\r", " \\\\n\\\\t\\\\r", "12d9772da7e7a1628dcb95e3ca9d107d"},
			{"DIGITS", "0123456789", "0123456789", "781e5e245d69b566979b86e28d23f2c7"},
			{"CHARS", "qooqoojambo", "qooqoojambo", "9fc26f176fade3d4c29d4a34ac2cccf9"},
			{"MIX_CHARS_DIGITS", "PhoneNumber911", "PhoneNumber911", "0ee5d9c700a9426e4acf08771295af77"},
			{"RUSSIAN", "ЙОД, МЁД И МОЛОКО", "???, ??? ? ??????", "a69d630a211a878b3178553447877a93"}, // Unicode is not supported on web service
			{"LATIN_DIACRITICS", "ÄäǞǟĄ̈ą̈B̈b̈C̈c̈ËëḦḧÏïḮḯK̈k̈M̈m̈N̈n̈ÖöȪȫǪ̈ǫ̈ṎṏP̈p̈Q̈q̈Q̣̈q̣̈S̈s̈T̈ẗÜüǕǖǗǘǙǚǛǜṲṳṺṻᴞV̈v̈ẄẅẌẍŸÿZ̈z̈", // Unicode is not supported on web service
				"Ää??????B?b?C?c?Ëë??Ïï??K?k?M?m?N?n?Öö????????P?p?Q?q?Q??q??S?s?T??Üü?????????????V?v??????ÿZ?z?",
				"b49c2b64603e36dd282f6b475cc665c5"},
			{"GREEK_DIACRITICS", "ΪϊῒΐῗΫϋῢΰῧϔ", "???????????", "363c4a93a55c53e66881af7ab83400f0"}, // Unicode is not supported on web service
			{"CYRILLIC_DIACRITICS", "ӒӓЁёӚӛӜӝӞӟӤӥЇїӦӧӪӫӰӱӴӵӸӹӬӭ", "??????????????????????????", // Unicode is not supported on web service
				"e54fe31d3f46d29d0322bf9b0f1f93f1"},
			{"CHINESE", "我想一個胡蘿蔔", "???????", "35e6651a21795c84810fbec1be88baae"}, // Unicode is not supported on web service
			{"SHARP", "#", "#", "01abfc750a0c942167651c40d088531d"},
			{"SPECIAL_SYMBOLS", "!@#$%^&*()_+?:;№\"`~", "!@#$%^&*()_+?:;?\"`~", "5dfb064cb2ecc0e1f74d6cfd2886d0ef"}, // № symbol is not supported
		};
	}	
	@Test(dataProvider = "testTextParamPositiveURLEncoded")
	public void testPositiveURLEncoded(String name, String inputTextParam, String expectedOriginal, String expectedMD5) throws Exception {
		JSONObject expectedJSON = new JSONObject(String.format("{'original': '%s', 'md5': '%s'}", expectedOriginal, expectedMD5));
		Request request = new Request(URLEncoder.encode(inputTextParam, "UTF-8"));
		
		assertEquals(request.getResponseCode(), 200);
		assertEquals(request.getResponseJSON().toString(), expectedJSON.toString());
	}

	@Test
	public void testNoText() throws Exception {		
		String textParam = null;		
		String errorText = "An error was encountered during MD5 hashing. Message: There is no string to calculate a MD5 hash from.";
		String infoText = "You must pass a String through the ?text= parameter for a hash to be calculated.";
		Request request = new Request(textParam);
		JSONObject expectedJSON = new JSONObject(String.format("{'error': '%s', 'info': '%s'}", errorText, infoText));
		
		assertEquals(request.getResponseCode(), 200);
		assertEquals(request.getResponseJSON().toString(), expectedJSON.toString());
	}
	
	@DataProvider(name = "testMaxLength")
	public static String[][] length() {
		return new String[][] {
			// text_length, status_code
			{"UNDER_THRESHHOLD", "8185", "200"},
			{"ABOVE_THRESHHOLD", "8186", "400"},
		};
	}	
	@Test(dataProvider = "testMaxLength")
	public void testMaxLength(String name, String textLength, String statusCode) throws Exception {		
		String inputTextParam = String.join("", Collections.nCopies(Integer.parseInt(textLength), "A"));
		Request request = new Request(inputTextParam);
		assertEquals(request.getResponseCode(), Integer.parseInt(statusCode));
		if (name.contains("UNDER"))	{
			String expectedMD5 = "d0697709d1a7477592992ffa284dbc65";
			String expectedOriginal = inputTextParam;
			JSONObject expectedJSON = new JSONObject(String.format("{'original': '%s', 'md5': '%s'}", expectedOriginal, expectedMD5));
			assertEquals(request.getResponseJSON().toString(), expectedJSON.toString());
		}		
	}
}
