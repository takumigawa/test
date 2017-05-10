package koroot.exception;

/**
 * BaseClassでログイン者権限をチェックし経費管理権限なしの場合throwされる例外
 * @author t.takahasi
 */
public class NoProjectAuthException extends Exception {
	private static final long serialVersionUID = 1L;
}
