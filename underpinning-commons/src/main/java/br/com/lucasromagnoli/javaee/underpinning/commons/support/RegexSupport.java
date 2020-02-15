package br.com.lucasromagnoli.javaee.underpinning.commons.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexSupport {
	private RegexSupport () {}
	public static final String STRONG_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}";
	/**
	 *
	 * @param target - String que será confrontada contra o pattern do Regex
	 * @param regex - Pattern do Regex
	 * <p>
	 *              Exemplo: target: senhaPoderosa123 - regex: "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$"
	 *              	-> return true
	 *             	Exemplo: target: senhafraca - regex: "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$"
	 *             		-> return false
	 * </p>
	 * @return boolean
	 */
	public static boolean matcher(String target, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(target);
		return matcher.matches();
	}

	/**
	 *
	 * @param target - String que será confrontada contra o pattern do Regex
	 * @param regex - Pattern do Regex
	 * @param flags - Flags do Regex
	 *              <p>
	 *              	Exemplo: target: teste@gmail.com - regex: "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b" frags: Pattern.CASE_INSENSITIVE
	 *              		-> return true
	 *              	Exemplo: target: teste.com - regex: "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b" frags: Pattern.CASE_INSENSITIVE
	 *              		-> return false
	 *              </p>
	 * @return boolean
	 */
	public static boolean matcher(String target, String regex, int flags) {
		Pattern pattern = Pattern.compile(regex, flags);
		Matcher matcher = pattern.matcher(target);
		return matcher.matches();
	}
}
