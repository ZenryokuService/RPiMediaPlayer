package application;

import org.junit.Before;

/**
 * Mainクラスのテストを行うクラス
 * @author takunoji
 * 2019/12/01
 */

public class MainTest {
	/** テスト対象クラス */
	private Main target;

	/**
	 * テストを行うための準備処理
	 */
	@Before
	public void initTest() {
		// テスト対象クラスのインスタンスを生成
		target = new Main();
	}

	/**************************************************
	 * 「testテストするメソッド名」でテストケース名を作成する  
	 * 複数テストするときは「_XXX」のように名前をつける
	 **************************************************/
	/** loadProperties()のテスト */
	public void testLoadProperties() {
		target.loadProperties();
	}
	
}
